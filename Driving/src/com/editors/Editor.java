package com.editors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.LineData;
import com.Plain;
import com.Point3D;
import com.Point4D;
import com.Polygon3D;
import com.PolygonMesh;
import com.Rib;
import com.SPLine;
import com.SPNode;
import com.SquareMesh;
import com.main.Road;

public class Editor extends JFrame implements MenuListener{
	
	public int ACTIVE_PANEL=0; 
	public int numPanels=2;
	
	public PolygonMesh[] meshes=null;	
	public Stack[] oldMeshes=null;

	
	public LineData polygon=new LineData();
	
	public int MAX_STACK_SIZE=10;
	
	public boolean redrawAfterMenu=false;
	
	public JFileChooser fc= new JFileChooser();
	public File currentDirectory=null;
	public File currentFile=null;
	
	public static int TERRAIN_INDEX=0;
	public static int ROAD_INDEX=1;
	
	public static String TAG[]={"terrain","road"};
	
	public ArrayList splines=null;
	
	public Editor(){
		
		
		meshes=new PolygonMesh[numPanels];
		
		
		oldMeshes=new Stack[numPanels];
			
		for (int i = 0; i < numPanels; i++) {
			
			if(i==TERRAIN_INDEX)
				meshes[i]=new SquareMesh();
			else
				meshes[i]=new PolygonMesh();

			oldMeshes[i]=new Stack();
		}
		
		splines=new ArrayList();
	
	}


	public static void levelSPLinesTerrain(PolygonMesh mesh, Vector splines) {



		int lsize=mesh.polygonData.size();

		for (int i = 0; splines!=null && i < splines.size(); i++) {


			SPLine sp = (SPLine) splines.elementAt(i);

			for (int k = 0; k < sp.ribs.size();k++){

				Rib rib = (Rib) sp.ribs.elementAt(k);
				Point4D[] points=rib.points;

				double mx=(points[0].x+points[1].x)*0.5;
				double my=(points[0].y+points[1].y)*0.5;



				for(int j=0;j<lsize;j++){


					LineData ld=(LineData) mesh.polygonData.get(j);

					Polygon3D p3D=levelGetPolygon(ld,mesh.points);

					if(p3D.contains(mx,my)){

						double zz=interpolate(mx,my,p3D);

						for (int l = 0; l < points.length; l++) {
							points[l].z+=zz;
						}


						break;


					}


				} 

			}
		}



	}


	public static Polygon3D levelGetPolygon(LineData ld, Point3D[] points) {
		
	

		int size=ld.size();

		int[] cx=new int[size];
		int[] cy=new int[size];
		int[] cz=new int[size];


		for(int i=0;i<size;i++){


			int num=ld.getIndex(i);

			Point4D p=(Point4D) points[num];
			


			//bufGraphics.setColor(ZBuffer.fromHexToColor(is[i].getHexColor()));

			cx[i]=(int)p.x;
			cy[i]=(int)p.y;
			cz[i]=(int)p.z;

	

		}

		Polygon3D p_in=new Polygon3D(ld.size(),cx,cy,cz);
		
		return p_in;
	}
	
	
	public static double interpolate(double px, double py, Polygon3D p3d) {
	       
		/*Plain plain=Plain.calculatePlain(p3d);
		return plain.calculateZ(px,py);

		 */
		Polygon3D p1=Polygon3D.extractSubPolygon3D(p3d,3,0);

		if(p1.hasInsidePoint(px,py)){

			Plain plane1=Plain.calculatePlain(p1);

			return plane1.calculateZ(px,py);

		}

		Polygon3D p2=Polygon3D.extractSubPolygon3D(p3d,3,2);

		if(p2.hasInsidePoint(px,py)){

			Plain plane2=Plain.calculatePlain(p2);

			return plane2.calculateZ(px,py);

		}


		return 0;
	}
	
	
	public void buildLines(Vector lines, String str) {

		StringTokenizer sttoken=new StringTokenizer(str," ");

		while(sttoken.hasMoreElements()){
			
			String token=sttoken.nextToken();
			
			LineData ld=new LineData();
			
			if(token.indexOf("]")>0){
				
				String extraData=token.substring(token.indexOf("[")+1,token.indexOf("]"));
				token=token.substring(token.indexOf("]")+1);
				ld.setData(extraData);
				
			}

			String[] vals = token.split(" ");

			for(int i=0;i<vals.length;i++)
				ld.addIndex(Integer.parseInt(vals[i]));


			lines.add(ld);
		}




	}
	
	public boolean forceReading=false;
	
	public void loadPointsFromFile(){	
		
		forceReading=true;

		fc=new JFileChooser();
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setDialogTitle("Load lines ");
		if(currentDirectory!=null)
			fc.setCurrentDirectory(currentDirectory);
		if(currentFile!=null)
			fc.setSelectedFile(currentFile);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			currentDirectory=fc.getCurrentDirectory();
			currentFile=fc.getSelectedFile();
			File file = fc.getSelectedFile();
			loadPointsFromFile(file);
            setTitle(file.getName());

		}
	}
	
	public void loadPointsFromFile(File file){

		if(ACTIVE_PANEL==0)
			meshes[ACTIVE_PANEL]=new  SquareMesh();
		else	
			meshes[ACTIVE_PANEL]=new  PolygonMesh();
		
		oldMeshes[ACTIVE_PANEL]=new Stack();
	

		try {
			BufferedReader br=new BufferedReader(new FileReader(file));


			String str=null;
			int rows=0;
			
			boolean read=forceReading;
			
			int nx=0;
			int ny=0;
			int dx=0;
			int dy=0;
			double x0=0;
			double y0=0;
			
			ArrayList aPoints=new ArrayList();
			ArrayList vTexturePoints=new ArrayList();
			
			while((str=br.readLine())!=null){
				
				
				
				if(str.indexOf("#")>=0 || str.length()==0)
					continue;
				
				if(str.indexOf(TAG[ACTIVE_PANEL])>=0){
					read=!read;
				    continue;
				}	
				
				if(!read)
					continue;
				
				

				if(str.startsWith("v="))
					buildPoint(aPoints,str.substring(2));
				else if(str.startsWith("vt="))
					PolygonMesh.buildTexturePoint(vTexturePoints,str.substring(3));
				else if(str.startsWith("f="))
					buildLine(meshes[ACTIVE_PANEL].polygonData,str.substring(2),vTexturePoints);
				else if(str.startsWith("NX="))					
					nx=Integer.parseInt(str.substring(3)); 
				else if(str.startsWith("NY="))					
					ny=Integer.parseInt(str.substring(3)); 
				else if(str.startsWith("DX="))					
					dx=Integer.parseInt(str.substring(3)); 
				else if(str.startsWith("DY="))					
					dy=Integer.parseInt(str.substring(3)); 
				else if(str.startsWith("X0="))					
					x0=Double.parseDouble(str.substring(3)); 
				else if(str.startsWith("Y0="))					
					y0=Double.parseDouble(str.substring(3)); 


			}
			
			if(meshes[ACTIVE_PANEL] instanceof SquareMesh){
				
				((SquareMesh)meshes[ACTIVE_PANEL]).setNumx(nx); 
				((SquareMesh)meshes[ACTIVE_PANEL]).setNumy(ny); 
				((SquareMesh)meshes[ACTIVE_PANEL]).setDx(dx);
				((SquareMesh)meshes[ACTIVE_PANEL]).setDy(dy);
				((SquareMesh)meshes[ACTIVE_PANEL]).setX0(x0); 
				((SquareMesh)meshes[ACTIVE_PANEL]).setY0(y0); 
				
			}

			br.close();
			
			meshes[ACTIVE_PANEL].setPoints(aPoints);
			meshes[ACTIVE_PANEL].setTexturePoints(vTexturePoints);
			meshes[ACTIVE_PANEL].setLevel(Road.GROUND_LEVEL);
			
			//checkNormals();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		repaint();
	}
	
	public void loadSPLinesFromFile(File file){
		
		splines=loadStaticSPLinesFromFile(file,forceReading);
		repaint();
	}

	
	public static ArrayList loadStaticSPLinesFromFile(File file,boolean forceReading){
		
		ArrayList splines=new ArrayList();
		
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));


			String str=null;
			int rows=0;
			
			boolean read=forceReading;

			double x0=0;
			double y0=0;
			
			
			ArrayList aTexturePoints=new ArrayList();
			
			while((str=br.readLine())!=null){
				
				
				
				if(str.indexOf("#")>=0 || str.length()==0)
					continue;
				
				if(str.indexOf(TAG[ROAD_INDEX])>=0){
					read=!read;
				    continue;
				}	
				
				if(!read)
					continue;
				
				if(str.startsWith("vt=")){
					PolygonMesh.buildTexturePoint(aTexturePoints,str.substring(3));
					continue;
				}else if(str.equals("<spline>")){			
				
					
					
					SPLine sp=new SPLine(aTexturePoints);
					splines.add(sp);
					continue;
				}else if(str.equals("</spline>")){
					continue;
				}
				
				
				SPLine sp=(SPLine) splines.get(splines.size()-1);
				
			
								
				buildSPLine(sp,str);
				

			}
			
			br.close();

			
			//checkNormals();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return splines;
		
	}
	
	public static Point3D loadStartPosition(File file) {
		
		Point3D startPosition=null;

		try {
			BufferedReader br=new BufferedReader(new FileReader(file));

			boolean read=false;

			String str=null;

			while((str=br.readLine())!=null){
				if(str.indexOf("#")>=0 || str.length()==0)
					continue;
				
				if(str.indexOf("startPosition")>=0){
					read=!read;
				    continue;
				}	
				
				if(!read)
					continue;
				

				String[] vals = str.split(" ");

				startPosition=new Point3D();
				
				startPosition.x=Double.parseDouble(vals[0]);
				startPosition.y=Double.parseDouble(vals[1]);
				startPosition.z=Double.parseDouble(vals[2]);
		
				
			}

			br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		if(startPosition==null)
			startPosition=new Point3D(0,0,0);
		
		return startPosition;
	}


	public static void buildSPLine(SPLine sp,String str) {
		
		if(str.startsWith("v=")){
			
			str=str.substring(2);
		
			String[] vals =str.split(" ");
	
			SPNode p=new SPNode();
			
			String texture_index=vals[0];
			texture_index=texture_index.substring(1);
			p.setIndex(Integer.parseInt(texture_index));
			
			p.x=Double.parseDouble(vals[1]);
			p.y=Double.parseDouble(vals[2]);
			p.z=Double.parseDouble(vals[3]);
			p.update();
			
	        sp.addSPNode(p);
        
		}
	}





	public void buildPoint(ArrayList vPoints, String str) {
		PolygonMesh.buildPoint(vPoints,str);
		
	}





	public void buildLine(ArrayList<LineData> polygonData, String str,
			ArrayList vTexturePoints) {
		PolygonMesh.buildLine(polygonData,str,vTexturePoints);
		
	}





	public String decomposePoint(Point3D p) {
		String str="";

		str=p.x+" "+p.y+" "+p.z;
		
		if(p.getData()!=null)
			str=str+" "+p.getData().toString();

		return str;
	}
	
	public String decomposeLineData(LineData ld) {
		
		return decomposeLineData(ld,false);
	}
	
	public String decomposeLineData(LineData ld,boolean isCustom) {

		String str="";
		
		if(ld.data!=null){
			
			str+="["+ld.data+"]";
		    
		}
		for(int j=0;j<ld.size();j++){

			if(j>0)
				str+=" ";
			str+=ld.getIndex(j);
			
			if(isCustom){
				
				int indx=ld.getVertex_texture_index(j);
				str+="/"+indx;
				
			}else{
				
				if(ld.data!=null){
					
					int face=Integer.parseInt(ld.data);
		
					str+="/"+(ld.getIndex(j)*6+face);
				}
				
			}
			
		

		}

		return str;
	}
	
	


	private String decomposeSPLine(SPLine sp) {
		
		String str="<spline>\n";

		SPNode root = sp.getRoot();
		int sz=root.getChildCount();
		
		for(int i=-1;i<sz;i++){
			
			if(i>-1)
				str+="\n";
			Point4D p0=(Point4D) root.getChildAt(i);
			str+="v=T"+p0.getIndex()+" "+p0.getX()+" "+p0.getY()+" "+p0.getZ();
		}
		str+="\n</spline>";
		return str;
	}
	
	public void saveLines() {
		
		saveLines(false);
		
	}
	
	public void saveLines(boolean isCustom) {

		fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setDialogTitle("Save lines");
		if(currentDirectory!=null)
			fc.setCurrentDirectory(currentDirectory);
		if(currentFile!=null)
			fc.setSelectedFile(currentFile);
		
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			
			
			currentDirectory=fc.getCurrentDirectory();
			currentFile=fc.getSelectedFile();
			try{
				PrintWriter pr = new PrintWriter(new FileOutputStream(file));
				saveLines(pr,isCustom);
	            pr.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		} 


	}
	
	public void saveLines(PrintWriter pr) {
		
		saveLines(pr,false);
	}

	public void saveLines(PrintWriter pr,boolean isCustom) {

       

		
		try {
			
			PolygonMesh mesh=meshes[ACTIVE_PANEL];
			
			if(mesh.points==null)
				return; 
			
			if(!forceReading)
				pr.print("<"+TAG[ACTIVE_PANEL]+">\n");
			
			if(mesh instanceof SquareMesh){
				
				SquareMesh sm = (SquareMesh)mesh;
				pr.println("NX="+sm.getNumx());
				pr.println("NY="+sm.getNumy());
				pr.println("DX="+sm.getDx());
				pr.println("DY="+sm.getDy());
				pr.println("X0="+sm.getX0());
				pr.println("Y0="+sm.getY0());
			}
			

			for(int i=0;i<mesh.points.length;i++){

				Point3D p=mesh.points[i];

				pr.print("v=");
				pr.print(decomposePoint(p));				
				pr.println();
			}
			
			decomposeObjVertices(pr,mesh,isCustom);

	
			

			for(int i=0;i<mesh.polygonData.size();i++){

				LineData ld=(LineData) mesh.polygonData.get(i);
				
				//for the transaction phase:
				
				/*Point3D normal = PolygonMesh.getNormal(0,ld,mesh.points);	
				
				int boxFace=Renderer3D.findBoxFace(normal);
				
				ld.setData(""+boxFace);*/
				
				////////////
				pr.print("f=");
				if(isCustom){
					
					pr.println(decomposeLineData(ld,true));
					
				}else
					pr.println(decomposeLineData(ld));
			
			}	
			if(!forceReading)
				pr.print("</"+TAG[ACTIVE_PANEL]+">\n");
				

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void saveSPLines(PrintWriter pr) {
		
		try {
			
			if(!forceReading)
				pr.println("<"+TAG[ACTIVE_PANEL]+">");
			
			decomposeObjVertices(pr,null,false);
			
			for (int i = 0; i < splines.size(); i++) {
				
				SPLine sp = (SPLine) splines.get(i);
				if(i>0)
					pr.println();
				
				pr.print(decomposeSPLine(sp));
				
			}
			
			
			if(!forceReading)
				pr.println("\n</"+TAG[ACTIVE_PANEL]+">");

		} catch (Exception e) {

			e.printStackTrace();
		}

		
	}


	public void decomposeObjVertices(PrintWriter pr,PolygonMesh mesh,boolean isCustom) {
		
		
		
	}


	public Vector clonePoints(Vector oldPoints) {
		
		Vector newPoints=new Vector();
		
		for(int i=0;i<oldPoints.size();i++){
			
			Point3D p=(Point3D) oldPoints.elementAt(i);
			newPoints.add(p.clone());
		}
		
		return newPoints;
	}
	
	public Vector cloneLineData(Vector oldLines) {
		
		Vector newLineData=new Vector();
		
		for(int i=0;i<oldLines.size();i++){

			LineData ld=(LineData) oldLines.elementAt(i);
		
			
			newLineData.add(ld.clone());
		
			
		}
		return newLineData;
	}
	
	public void prepareUndo() {
		
		PolygonMesh mesh=meshes[ACTIVE_PANEL];
		if(mesh.points==null)
			return; 
		
		if(oldMeshes[ACTIVE_PANEL].size()==MAX_STACK_SIZE){
			
			oldMeshes[ACTIVE_PANEL].removeElementAt(0);
		}
		oldMeshes[ACTIVE_PANEL].push(meshes[ACTIVE_PANEL].clone());
	
	}
	
	public void undo() {
		
		if(oldMeshes[ACTIVE_PANEL].size()>0)
			meshes[ACTIVE_PANEL]=(PolygonMesh) oldMeshes[ACTIVE_PANEL].pop();

		
	}
	
	public void preview(){}
	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void menuDeselected(MenuEvent arg0) {
		redrawAfterMenu=true;

	}
	@Override
	public void menuSelected(MenuEvent arg0) {
		redrawAfterMenu=false;

	}

	public Polygon3D buildPolygon(LineData ld, Point3D[] points, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	public int calculateShadowColor(double xi, double yi, double zi, double cosin, int argbs) {

		return argbs;
	
	}
	
	public double calculateCosin(Polygon3D polReal) {
		
		return 1.0;
	}
	
	public int getACTIVE_PANEL() {
		return ACTIVE_PANEL;
	}

	public void setACTIVE_PANEL(int aCTIVE_PANEL) {
		ACTIVE_PANEL = aCTIVE_PANEL;
	}


}
