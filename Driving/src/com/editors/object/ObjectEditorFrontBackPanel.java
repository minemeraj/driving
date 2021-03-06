package com.editors.object;

/**
 * @author Piazza Francesco Giovanni ,Tecnes Milano http://www.tecnes.com
 *
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import com.LineData;
import com.Point3D;
import com.Polygon3D;
import com.PolygonMesh;



/**
 * @author Piazza Francesco Giovanni ,Tecnes Milano http://www.tecnes.com
 *
 */

class ObjectEditorFrontBackPanel extends ObjectEditorViewPanel {

	
	private int y0=250;
	private int x0=350;


	private double deltay=0.5;
	private double deltax=0.5;


	ObjectEditorFrontBackPanel(ObjectEditorPanel oep){

	    super(oep);

	}


	@Override
	public void displayAll() {


		BufferedImage buf=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

		Graphics2D bufGraphics=(Graphics2D)buf.getGraphics();
        

		
		displayPoints(bufGraphics);
		displayLines(bufGraphics);
		draw2Daxis(bufGraphics,WIDTH,HEIGHT);
		displayCurrentRect(bufGraphics);

		if(g2==null)
			g2=(Graphics2D) getGraphics();
		
		g2.drawImage(buf,0,0,WIDTH,HEIGHT,null);
		objEditorPanel.resetLists();

	}


	private void draw2Daxis(Graphics2D graphics2D, int i, int j) {
		
		int length=60;
	
		
		// x axis
		graphics2D.setColor(Color.GREEN);
        int x1=(int) (calcAssX(0,0,0));
        int y1=(int)(calcAssY(0,0,0));
        int x2=(int)(calcAssX(length,0,0));
        int y2=(int)(calcAssY(length,0,0));
	
        if((y1<=j || y2<=j || y1>=0 || y2>=0  )&& (x1<=i || x2<=i ||  x1>=0 || x2>=0 ) )
		 graphics2D.drawLine(x1,y1,x2,y2);
		
		//z axis
        graphics2D.setColor(Color.BLUE);
		 x1=(int)(calcAssX(0,0,0));
		 y1=(int)(calcAssY(0,0,0));
		 x2=(int)(calcAssX(0,0,length));
		 y2=(int)(calcAssY(0,0,length));
		 
		 if((y1<=j || y2<=j || y1>=0 || y2>=0  )&& (x1<=i || x2<=i ||  x1>=0 || x2>=0 ) )
		  graphics2D.drawLine(x1,y1,x2,y2);
		 
		
	}

	private void displayLines(Graphics2D bufGraphics) {

		ObjectEditor oe = objEditorPanel.objectEditor;
		
		PolygonMesh mesh=oe.getMeshes()[oe.getACTIVE_PANEL()];

		for(int i=0;i<mesh.polygonData.size();i++){

			LineData ld=(LineData) mesh.polygonData.get(i);
			int numLInes=1;
			if(ld.size()>2)
				numLInes=ld.size();


			bufGraphics.setColor(Color.WHITE);


			for(int j=0;j<numLInes;j++){

				Point3D p0=new Point3D(mesh.xpoints[ld.getIndex(j)],
						mesh.ypoints[ld.getIndex(j)],
						mesh.zpoints[ld.getIndex(j)]);
						
						
				Point3D p1=new Point3D(mesh.xpoints[ld.getIndex((j+1)%ld.size())],
						mesh.ypoints[ld.getIndex((j+1)%ld.size())],
						mesh.zpoints[ld.getIndex((j+1)%ld.size())]);


				bufGraphics.drawLine(calcAssX(p0),calcAssY(p0),calcAssX(p1),calcAssY(p1));
			}
			if(oe.isShowNornals())
				showNormals(mesh.xpoints,mesh.ypoints,mesh.zpoints,ld,bufGraphics);

		}	

		for(int i=0;i<mesh.polygonData.size();i++){

			LineData ld=(LineData) mesh.polygonData.get(i);
			int numLInes=1;
			if(ld.size()>2)
				numLInes=ld.size();

			if(!ld.isSelected())
				continue;

			bufGraphics.setColor(Color.RED);

			for(int j=0;j<numLInes;j++){

				Point3D p0=new Point3D(mesh.xpoints[ld.getIndex(j)],
						mesh.ypoints[ld.getIndex(j)],
						mesh.zpoints[ld.getIndex(j)]);
				Point3D p1=new Point3D(
						mesh.xpoints[ld.getIndex((j+1)%ld.size())],
						mesh.ypoints[ld.getIndex((j+1)%ld.size())],
						mesh.zpoints[ld.getIndex((j+1)%ld.size())]);


				bufGraphics.drawLine(calcAssX(p0),calcAssY(p0),calcAssX(p1),calcAssY(p1));
			}
			

		}	

	}








	private void showNormals(double[] xpoints,double[] ypoints,double[] zpoints, LineData ld, Graphics2D bufGraphics) {
		
		Polygon3D p3d=new Polygon3D();
		int numLInes=ld.size();
		
		if(numLInes<3)
			return;
		
		for(int j=0;j<numLInes;j++){

			Point3D p0=new Point3D(xpoints[ld.getIndex(j)],ypoints[ld.getIndex(j)],zpoints[ld.getIndex(j)]);
			p3d.addPoint(p0);
		}
		
		Point3D normal = Polygon3D.findNormal(p3d).calculateVersor();
		normal=normal.multiply(50.0);
		Point3D centroid=Polygon3D.findCentroid(p3d);
		Point3D normalTip=centroid.sum(normal);
		bufGraphics.setColor(Color.GREEN);
		bufGraphics.drawLine(calcAssX(centroid),calcAssY(centroid),calcAssX(normalTip),calcAssY(normalTip));
		
	}

	private void displayPoints(Graphics2D bufGraphics) {
		ObjectEditor oe = objEditorPanel.objectEditor;
		
		PolygonMesh mesh=oe.getMeshes()[oe.getACTIVE_PANEL()];
		
		if(mesh==null || mesh.xpoints==null)
			return;

		for(int i=0;i<mesh.xpoints.length;i++){

			if(mesh.selected[i])
				bufGraphics.setColor(Color.RED);
			else
				bufGraphics.setColor(Color.white);

			//TOP
			bufGraphics.fillOval(
					calcAssX(mesh.xpoints[i],mesh.ypoints[i],mesh.zpoints[i])-2,
					calcAssY(mesh.xpoints[i],mesh.ypoints[i],mesh.zpoints[i])-2,
					5,5);
		


		}



	}




	@Override
	public void selectPoint(int x, int y) {
		
		boolean found=false;
		ObjectEditor oe = objEditorPanel.objectEditor;
		
		PolygonMesh mesh=oe.getMeshes()[oe.getACTIVE_PANEL()];
		if(mesh.xpoints==null)
			return;

		//select point from lines
		if(!objEditorPanel.checkMultipleSelection.isSelected()) 
			objEditorPanel.polygon=new LineData();
		for(int i=0;i<mesh.xpoints.length;i++){

			Point3D p=new Point3D(mesh.xpoints[i],mesh.ypoints[i],mesh.zpoints[i]);



			int xo=calcAssX(p);
			int yo=calcAssY(p);

	
			Rectangle rect=new Rectangle(xo-5,yo-5,10,10);
			if(rect.contains(x,y)){

				mesh.selected[i]=true;
				objEditorPanel.selectPoint(p);

				objEditorPanel.polygon.addIndex(i);
			    
			    
				found=true;

			}
			else if(!objEditorPanel.checkMultipleSelection.isSelected()) 
				mesh.selected[i]=false;
		}
		
		
		if(!found)
			selectPolygon(x,y);

	}
	@Override
	public double calculateScreenDistance(Polygon3D p3d, int x, int y) {

		Point3D centroid=Polygon3D.findCentroid(p3d);
		
		double xx=(cosf*(centroid.y)+sinf*(centroid.x));
		
		return xx;
	}
	@Override
	public void selectPointsWithRectangle() {
		
		int x0=Math.min(currentRect.x,currentRect.x+currentRect.width);
		int x1=Math.max(currentRect.x,currentRect.x+currentRect.width);
		int y0=Math.min(currentRect.y,currentRect.y+currentRect.height);
		int y1=Math.max(currentRect.y,currentRect.y+currentRect.height);
		
		ObjectEditor oe = objEditorPanel.objectEditor;
		
		PolygonMesh mesh=oe.getMeshes()[oe.getACTIVE_PANEL()];
        
        for (int i = 0; i < mesh.xpoints.length; i++) {
        
    	Point3D p = new Point3D(mesh.xpoints[i],mesh.ypoints[i],mesh.zpoints[i]);


    	int x=calcAssX(p);
		int y=calcAssY(p);

			if(x>=x0 && x<=x1 && y>=y0 && y<=y1  ){

				mesh.selected[i]=true;

			}

		}
		
	}




	@Override
	public void zoom(int n){
		
		if(n>0){
			deltay=deltax=deltax/2;
			moveCenter(0.5);
			
		}
		else{
			deltay=deltax=deltax*2;
			moveCenter(2.0);
		}
		
	}

    private void moveCenter(double d) {
		
    	
    	int dx=(int) ((getWidth()/2-x0)*(1-1.0/d));
		int dy=(int) ((getHeight()/2-y0)*(1-1.0/d));
		moveCenter(dx,dy);
		
	}



	private void moveCenter(int dx, int dy) {
    
    	x0+=dx;
    	y0+=dy;
    }
	@Override
	public int calcAssX(double x, double y, double z) {
		 

		
		double xx=(cosf*(x)-sinf*(y));
		double yy=(cosf*(y)+sinf*(x));
		double zz=z;
		
		return (int) (xx/deltax+x0);
	}
	@Override
	public int calcAssY(double x, double y, double z) {
		
		
		double xx=(cosf*(x)-sinf*(y));
		double yy=(cosf*(y)+sinf*(x));
		double zz=z;
		 
		return HEIGHT-(int) (zz/deltay+y0);
	}
	

	
	private int calcAssX(Point3D p) {
		 
		return calcAssX(p.x,p.y,p.z);
	}
	
	private int calcAssY(Point3D p) {
		 
		return  calcAssY(p.x,p.y,p.z);
	}
	@Override
	public void translate(int i, int j) {
	
		x0=x0-i*5;
		y0=y0-j*2;
		displayAll();
	}


	@Override
	Area clipPolygonToArea2D(Polygon p_in, Area area_out) {
		// TODO Auto-generated method stub
		return null;
	}


}
