package com.editors.road;
/**
 * @author Piazza Francesco Giovanni ,Tecnes Milano http://www.tecnes.com
 *
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RepaintManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.CubicMesh;
import com.DrawObject;
import com.LineData;
import com.Point3D;
import com.Point4D;
import com.Polygon3D;
import com.PolygonMesh;
import com.Texture;
import com.ZBuffer;
import com.editors.DoubleTextField;
import com.editors.Editor;
import com.main.HelpPanel;




/**
 * @author Piazza Francesco Giovanni ,Tecnes Milano http://www.tecnes.com
 *
 */

public class RoadEditor extends Editor implements ActionListener,MouseListener,MouseWheelListener,PropertyChangeListener,MouseMotionListener,KeyListener, ItemListener{


	private JPanel center;
	int HEIGHT=640;
	int WIDTH=700;
	int LEFT_BORDER=240;
	int RIGHT_BORDER=240;
	int BOTTOM_BORDER=100;
	int RIGHT_SKYP=10;

	int MOVX=-50;
	int MOVY=100;

	int dx=2;
	int dy=2;

	int deltay=200;
	int deltax=200;

	Vector drawObjects=new Vector();
	Graphics2D g2;
	Graphics2D g2Alias;
	Stack oldObjects=new Stack();
	Stack oldRoads=new Stack();
	int MAX_STACK_SIZE=10;


	private JFileChooser fc;
	private JMenuBar jmb;
	private JMenu jm_file;
	private JPanel right;
	private JTextField coordinatesx;
	private JTextField coordinatesy;
	private JTextField coordinatesz;
	private JCheckBox checkCoordinatesx;
	private JCheckBox checkCoordinatesy;
	private JCheckBox checkCoordinatesz;
	private JButton changePoint;
	private JTextField colorRoadChoice;
	private JButton addObject;
	private JButton delObject;
	private JMenu jm5;
	private JMenuItem jmt51;

	public boolean ISDEBUG=false;
	private JButton deselectAll;
	private JPanel bottom;
	private JLabel screenPoint;

	
	private JCheckBox checkRoadColor;
	private JCheckBox checkMultiplePointsSelection;
	private JButton chooseNextTexture;
	private JButton choosePrevTexture;
	private JButton choosePanelTexture;
	private JLabel objectLabel;
	private JComboBox chooseObject;
	private JButton chooseObjectPanel;
	private JButton choosePrevObject;
	private JButton chooseNextObject;
	private JCheckBox checkMultipleObjectsSelection;

	boolean isUseTextures=true;
	private JLabel textureLabel;
	private JComboBox chooseTexture;
	private JMenu jm3;
	private JCheckBoxMenuItem jmt31;
	
	private JMenu jm4;
	private JMenuItem jmt41;
	private JMenuItem jmt42;	
	private JPanel left;
	
	private DoubleTextField objcoordinatesx;
	private JCheckBox objcheckCoordinatesx;
	private DoubleTextField objcoordinatesy;	
	private JCheckBox objcheckCoordinatesy;
	private DoubleTextField objcoordinatesz;
	private JCheckBox objcheckCoordinatesz;
	
	private JButton changeObject;
	private JTextField colorObjChoice;
	private JCheckBox checkObjColor;
	
	private JTextField objcoordinatesdx;
	private JTextField objcoordinatesdy;
	private JTextField objcoordinatesdz;
	private JCheckBox objcheckCoordinatesdx;
	private JCheckBox objcheckCoordinatesdy;
	private JCheckBox objcheckCoordinatesdz;
	private Rectangle currentRect;
	private boolean isDrawCurrentRect=false;
	private DoubleTextField roadMove;
	private JButton moveRoadUp;
	private JButton moveRoadDown;
	private JButton moveRoadRight;
	private JButton moveRoadLeft;
	private JButton moveRoadTop;
	private JButton moveRoadBottom;
	private DoubleTextField objMove;
	private JButton moveObjUp;
	private JButton moveObjDown;
	private JButton moveObjLeft;
	private JButton moveObjRight;
	private JButton moveObjTop;
	private JButton moveObjBottom;
	private CubicMesh[] objectMeshes;


	

	public static Texture[] worldTextures;
	public static BufferedImage[] worldImages;
	public static BufferedImage[] objectImages;

	public static Color BACKGROUND_COLOR=new Color(0,0,0);
	private JMenuItem jmt52;
	private JMenuItem jmt53;
	private JButton changePolygon;
	private JButton startBuildPolygon;
	private JButton buildPolygon;
	private JButton deleteSelection;
	private JButton addPoint;
	private JButton polygonDetail;
	private JButton deselectAllObjects;
	private JMenu help_jm;
	private AbstractButton jmt54;
	private JMenuItem jmt_load_landscape;
	private JMenuItem jmt_save_landscape;
	private JButton mergeSelectedPoints;
	

	public RoadEditor(String title){
		
		setTitle(title);
		
		if(!DrawObject.IS_3D)
		   setTitle("Road editor 2D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(10,10);
		setSize(WIDTH+RIGHT_BORDER+LEFT_BORDER+RIGHT_SKYP,HEIGHT+BOTTOM_BORDER);
		center=new JPanel(){
			
			public void paint(Graphics g) {
				super.paint(g);
				displayAll();
			}
		};
		center.setBackground(BACKGROUND_COLOR);
		center.setBounds(LEFT_BORDER,0,WIDTH,HEIGHT);
		center.addMouseListener(this);
		center.addMouseWheelListener(this);
		center.addMouseMotionListener(this);
		addKeyListener(this);
		addPropertyChangeListener(this);
		add(center);
		buildMenuBar();
		buildLeftObjectPanel();
		buildRightRoadPanel();
		buildBottomPanel();

		RepaintManager.setCurrentManager( 
				new RepaintManager(){

					public void paintDirtyRegions() {


						super.paintDirtyRegions();
						firePropertyChange("paintDirtyRegions",false,true);
						//if(redrawAfterMenu ) {displayAll();redrawAfterMenu=false;}
					}

				}				
		);
		
		currentDirectory=new File("lib");

		setVisible(true);


	}



	/**
	 * 
	 */
	public void initialize() {


		g2=(Graphics2D) center.getGraphics();
		g2Alias=(Graphics2D) center.getGraphics();
		g2Alias.setColor(Color.GRAY);
		Stroke stroke=new BasicStroke(0.1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
		g2Alias.setStroke(stroke);
		
		File directoryImg=new File("lib");
		File[] files=directoryImg.listFiles();
		
		Vector vObjects=new Vector();
		
		if(DrawObject.IS_3D)
			for(int i=0;i<files.length;i++){
				if(files[i].getName().startsWith("object3D_")
				   && 	!files[i].getName().startsWith("object3D_texture")	
				){
					
					vObjects.add(files[i]);
					
				}		
			}
		else{
			for(int i=0;i<files.length;i++){
				if(files[i].getName().startsWith("object_")
					
				){
					
					vObjects.add(files[i]);
					
				}		
			}
			
		}
		
		try{	


		
			
			Vector vRoadTextures=new Vector();
			
			for(int i=0;i<files.length;i++){
				if(files[i].getName().startsWith("road_texture_")){
					
					vRoadTextures.add(files[i]);
					
				}		
			}
			
			worldImages=new BufferedImage[vRoadTextures.size()];
			worldTextures=new Texture[vRoadTextures.size()];
			
			
			if(isUseTextures){
				
				
				for(int i=0;i<vRoadTextures.size();i++){
					
					worldImages[i]=ImageIO.read(new File("lib/road_texture_"+i+".jpg"));
					chooseTexture.addItem(new ValuePair(""+i,""+i));
				}
				
				
				
				
				if(isUseTextures){
					
					
					for(int i=0;i<vRoadTextures.size();i++){
						
						worldTextures[i]=new Texture(ImageIO.read(new File("lib/road_texture_"+i+".jpg")));
					}
					
				
					
			
					
				}

				
			}
			
		
			
			objectImages=new BufferedImage[vObjects.size()];
			objectMeshes=new CubicMesh[vObjects.size()];
			
			for(int i=0;i<vObjects.size();i++){
				
				chooseObject.addItem(new ValuePair(""+i,""+i));
				objectImages[i]=ImageIO.read(new File("lib/object_"+i+".gif"));
					
				
				if(DrawObject.IS_3D){
					objectMeshes[i]=CubicMesh.loadMeshFromFile(new File("lib/object3D_"+i));
				}
				
					
					
					
				
			}
						
			
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}





	private void displayObjects(Graphics2D bufGraphics) {

		Rectangle totalVisibleField=new Rectangle(0,0,WIDTH,HEIGHT);

		for(int i=0;i<drawObjects.size();i++){

			DrawObject dro=(DrawObject) drawObjects.elementAt(i);

			int y=convertY(dro.y);
			int x=convertX(dro.x);

			int index=dro.getIndex();

			int dw=(int) (dro.dx/dx);
			int dh=(int) (dro.dy/dy);


			if(!totalVisibleField.intersects(new Rectangle(x,y,dw,dh)) && 
					!totalVisibleField.contains(x,y)	)
				continue;

			if(dro.isSelected())
				bufGraphics.setColor(Color.RED);
			else
				bufGraphics.setColor(Color.WHITE);
			bufGraphics.drawString(""+index,x-5,y+5);
			drawObject(bufGraphics,dro);

		}	
	}




	private void deselectAll() {
		cleanPoints();
		deselectAllPoints();
		displayAll();
		coordinatesx.requestFocus();
		
		polygon=new LineData();
	}
	
	private void startBuildPolygon() {
		
		deselectAll();
		displayAll();
		
		if(!checkMultiplePointsSelection.isSelected())
			checkMultiplePointsSelection.setSelected(true);
	}
	
	private void polygonDetail() {
	
		
		int sizel=lines.size();
		for(int i=0;i<sizel;i++){

			LineData ld=(LineData) lines.elementAt(i);
			if(!ld.isSelected())
				continue;
			
			RoadEditorPolygonDetail repd=new RoadEditorPolygonDetail(this,ld);
	
			
			if(repd.getModifiedLineData()!=null){
				
				lines.setElementAt(repd.getModifiedLineData(),i);
				
			}
			
			break;
		}
		displayAll();
		
	}


	private void displayAll() {


		BufferedImage buf=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

		Graphics2D bufGraphics=(Graphics2D)buf.getGraphics();


		displayRoad(bufGraphics,buf);
		displayObjects(bufGraphics);

		g2.drawImage(buf,0,0,WIDTH,HEIGHT,null);

	}




	private void displayRoad(Graphics2D bufGraphics,BufferedImage buf) {

		bufGraphics.setColor(BACKGROUND_COLOR);
		bufGraphics.fillRect(0,0,WIDTH,HEIGHT);
		
		int lsize=lines.size();

		for(int j=0;j<lsize;j++){
			
			
			LineData ld=(LineData) lines.elementAt(j);

			drawPolygon(ld,bufGraphics,buf);
				
				

		} 
		
		for(int j=0;j<lsize;j++){
			
			
			LineData ld=(LineData) lines.elementAt(j);

			
			if(ld.isSelected()){
				bufGraphics.setColor(Color.RED);
				
				int size=ld.size();
				
				for(int i=0;i<ld.size();i++){


					int num0=ld.getIndex(i);
					int num1=ld.getIndex((i+1)%size);
					Point4D p0=(Point4D) points.elementAt(num0);
					Point4D p1=(Point4D) points.elementAt(num1);
					
				
					bufGraphics.drawLine(
								convertX(p0.x),
								convertY(p0.y),
								convertX(p1.x),
								convertY(p1.y)
					);

					
				}	
				
			}	

		} 

		
		//mark row angles
		
		int size=points.size();
		for(int j=0;j<size;j++){


		    Point4D p=(Point4D) points.elementAt(j);

				int xo=convertX(p.x);
				int yo=convertY(p.y);

				if(p.isSelected()){
					bufGraphics.setColor(Color.RED);

				}	
				else
					bufGraphics.setColor(Color.white);
	
				bufGraphics.fillOval(xo-2,yo-2,5,5);

			}
		
		
		drawCurrentRect(bufGraphics);
	}

	private int convertX(double i) {

		return (int) (i/dx-MOVX);
	}
	private int convertY(double j) {

		return (int) (HEIGHT-(j/dy+MOVY));
	}

	private int invertX(int i) {

		return (i+MOVX)*dx;
	}
	private int invertY(int j) {

		return dy*(HEIGHT-j-MOVY);
	}



	public void paint(Graphics g) {
		super.paint(g);
		displayAll();
	}

	private void drawObject(Graphics2D bufGraphics, DrawObject dro) {

		int[] cx=new int[4];
		int[] cy=new int[4];
		
		int versus=1;
		if(!DrawObject.IS_3D)
			versus=-1;

		cx[0]=convertX(dro.x);
		cy[0]=convertY(dro.y);
		cx[1]=convertX(dro.x);
		cy[1]=convertY(dro.y+versus*dro.dy);
		cx[2]=convertX(dro.x+dro.dx);
		cy[2]=convertY(dro.y+versus*dro.dy);
		cx[3]=convertX(dro.x+dro.dx);
		cy[3]=convertY(dro.y);

		Polygon p_in=new Polygon(cx,cy,4);

		Area totArea=new Area(new Rectangle(0,0,WIDTH,HEIGHT));
		Area partialArea = clipPolygonToArea2D( p_in,totArea);

		if(partialArea.isEmpty())
			return;

		Polygon pTot=Polygon3D.fromAreaToPolygon2D(partialArea);
		//if(cy[0]<0 || cy[0]>HEIGHT || cx[0]<0 || cx[0]>WIDTH) return;
		bufGraphics.setColor(ZBuffer.fromHexToColor(dro.getHexColor()));
		bufGraphics.drawPolygon(pTot);
		
		if(!DrawObject.IS_3D){
	
			bufGraphics.drawImage(DrawObject.fromImageToBufferedImage(objectImages[dro.index],Color.WHITE)
					,cx[0],cy[0],cx[2]-cx[0],cy[2]-cy[0],null);
		}
	}


	private void drawPolygon(LineData ld,Graphics2D bufGraphics,BufferedImage buf) {


		Area totArea=new Area(new Rectangle(0,0,WIDTH,HEIGHT));

		int size=ld.size();

		int[] cx=new int[size];
		int[] cy=new int[size];
		int[] cz=new int[size];

		int[] cxr=new int[size];
		int[] cyr=new int[size];
		int[] czr=new int[size];


		for(int i=0;i<size;i++){


			int num=ld.getIndex(i);

			Point4D p=(Point4D) points.elementAt(num);

			//bufGraphics.setColor(ZBuffer.fromHexToColor(is[i].getHexColor()));

			cx[i]=convertX(p.x);
			cy[i]=convertY(p.y);


			//real coordinates
			cxr[i]=(int)(p.x);
			cyr[i]=(int)(p.y);


		}

		Polygon p_in=new Polygon(cx,cy,ld.size());
		Area partialArea = clipPolygonToArea2D( p_in,totArea);

		if(partialArea.isEmpty())
			return;

		Polygon3D p3d=new Polygon3D(size,cx,cy,cz);
		Polygon3D p3dr=new Polygon3D(size,cxr,cyr,czr);

		//calculate texture angle


		if(isUseTextures){

			//Rectangle rect = p_in.getBounds();

			int index=ld.getTexture_index();//????
			//bufGraphics.setClip(partialArea);
			//bufGraphics.drawImage(worldImages[index],rect.x,rect.y,rect.width,rect.height,null);
			drawTexture(worldTextures[index],p3d,p3dr,buf);
			//bufGraphics.setClip(null);
		}
		else{
			bufGraphics.setColor(ZBuffer.fromHexToColor(ld.getHexColor()));
			bufGraphics.fill(partialArea); 
		}


	}

	private void drawTexture(Texture texture,  Polygon3D p3d, Polygon3D p3dr, BufferedImage buf) {

		Point3D normal=Polygon3D.findNormal(p3dr);



		if(texture!=null){
			
			Rectangle rect = p3d.getBounds();
			
			
			/*int i0=Math.min(p3d.xpoints[0],p3d.xpoints[3]);
			int i1=Math.max(p3d.xpoints[1],p3d.xpoints[2]);
					
			int j0=Math.min(p3d.ypoints[3],p3d.ypoints[2]);
			int j1=Math.max(p3d.ypoints[0],p3d.ypoints[1]);*/
			
			int i0=rect.x;
			int i1=rect.x+rect.width;
					
			int j0=rect.y;
			int j1=rect.y+rect.height;

			//System.out.println(i0+" "+i1+" "+j0+" "+j1);
			
			
			Point3D p0r=new Point3D(p3dr.xpoints[0],p3dr.ypoints[0],p3dr.zpoints[0]);
			Point3D p1r=new Point3D(p3dr.xpoints[1],p3dr.ypoints[1],p3dr.zpoints[1]);
			Point3D pNr=new Point3D(p3dr.xpoints[p3dr.npoints-1],p3dr.ypoints[p3dr.npoints-1],p3dr.zpoints[p3dr.npoints-1]);

			Point3D xDirection = (p1r.substract(p0r)).calculateVersor();
			//Point3D yDirection= (pNr.substract(p0r)).calculateVersor();
			Point3D yDirection=Point3D.calculateCrossProduct(normal,xDirection).calculateVersor();
			
			/*System.out.println(normal.x+" "+normal.y+" "+normal.z);
			System.out.println(xDirection.x+" "+xDirection.y);
			System.out.println(yDirection.x+" "+yDirection.y);*/

			for(int i=i0;i<i1;i++){
				
				//how to calculate the real limits?
				
			
				for (int j = j0; j < j1; j++) {
					
					if(p3d.contains(i,j))
					{	

						
						int ii=invertX(i);
						int jj=invertY(j);
				
						if(i>=0 && i<buf.getWidth() && j>=0 && j<buf.getHeight()){
						

							int rgbColor = ZBuffer.pickRGBColorFromTexture(texture,ii,jj,p3d.zpoints[0],xDirection,yDirection,p0r,0,0);

							buf.setRGB(i,j,rgbColor);
				
						}
						
						
						
					}
					//bufGraphics.setColor(new Color(rgbColor));
					//bufGraphics.fillRect(i,j,1,1);
					


				}

			}

		}

	}



	public Area clipPolygonToArea2D(Polygon p_in,Area area_out){


		Area area_in = new Area(p_in);

		Area new_area_out = (Area) area_out.clone();
		new_area_out.intersect(area_in);

		return new_area_out;

	}


	private void buildMenuBar() {
		jmb=new JMenuBar();
		jm_file=new JMenu("File");
		jm_file.addMenuListener(this);
		jmb.add(jm_file);
		
		jmt_load_landscape = new JMenuItem("Load landscape");
		jmt_load_landscape.addActionListener(this);
		jm_file.add(jmt_load_landscape);
		
		jmt_save_landscape = new JMenuItem("Save landscape");
		jmt_save_landscape.addActionListener(this);
		jm_file.add(jmt_save_landscape);


		jm3=new JMenu("Textures");
		jm3.addMenuListener(this);

		jmt31 = new JCheckBoxMenuItem("Use textures");
		jmt31.setState(true);
		jmt31.addActionListener(this);
		jm3.add(jmt31);

		jmb.add(jm3);

		jm4=new JMenu("Change");
		jm4.addMenuListener(this);
		jmt41 = new JMenuItem("Undo last object");
		jmt41.setEnabled(false);
		jmt41.addActionListener(this);
		jm4.add(jmt41);
		jmt42 = new JMenuItem("Undo last road");
		jmt42.setEnabled(false);
		jmt42.addActionListener(this);
		jm4.add(jmt42);	
		
		jmb.add(jm4);
		
		jm5=new JMenu("Other");
		jm5.addMenuListener(this);
		
		jmt53 = new JMenuItem("New Grid");
		jmt53.addActionListener(this);
		jm5.add(jmt53);
		
		if(DrawObject.IS_3D)
		{
			jmt51 = new JMenuItem("Preview");
			jmt51.addActionListener(this);
			jm5.add(jmt51);
	
			jmt52 = new JMenuItem("Advanced Altimetry");
			jmt52.addActionListener(this);
			jm5.add(jmt52);
		}
		
		
		jmt54 = new JMenuItem("Add bend");
		jmt54.addActionListener(this);
		jm5.add(jmt54);
		
		
		jmb.add(jm5);
		
		help_jm=new JMenu("Help");
		help_jm.addMenuListener(this);		
		jmb.add(help_jm);

		setJMenuBar(jmb);
	}

	private void buildLeftObjectPanel() {

		String header="<html><body>";
		String footer="</body></html>";

		left=new JPanel();
		left.setBounds(0,0,LEFT_BORDER,HEIGHT);
		left.setLayout(null);
        Border leftBorder=BorderFactory.createTitledBorder("Objects");
        left.setBorder(leftBorder);
		        
		int r=5;


		r+=30;
		
		checkMultipleObjectsSelection=new JCheckBox("Multiple selection");
		checkMultipleObjectsSelection.setBounds(30,r,150,20);
		checkMultipleObjectsSelection.addKeyListener(this);
		left.add(checkMultipleObjectsSelection);
		
		r+=30;

		JLabel lx=new JLabel("x:");
		lx.setBounds(5,r,20,20);
		left.add(lx);
		objcoordinatesx=new DoubleTextField(8);
		objcoordinatesx.setBounds(30,r,120,20);
		objcoordinatesx.addKeyListener(this);
		left.add(objcoordinatesx);
		objcheckCoordinatesx=new JCheckBox();
		objcheckCoordinatesx.setBounds(170,r,50,20);
		objcheckCoordinatesx.addKeyListener(this);
		left.add(objcheckCoordinatesx);

		r+=30;

		JLabel ly=new JLabel("y:");
		ly.setBounds(5,r,20,20);
		left.add(ly);
		objcoordinatesy=new DoubleTextField(8);
		objcoordinatesy.setBounds(30,r,120,20);
		objcoordinatesy.addKeyListener(this);
		left.add(objcoordinatesy);
		objcheckCoordinatesy=new JCheckBox();
		objcheckCoordinatesy.setBounds(170,r,50,20);
		objcheckCoordinatesy.addKeyListener(this);
		left.add(objcheckCoordinatesy);

		r+=30;

		JLabel lz=new JLabel("z:");
		lz.setBounds(5,r,20,20);
		left.add(lz);
		objcoordinatesz=new DoubleTextField(8);
		objcoordinatesz.setBounds(30,r,120,20);
		objcoordinatesz.addKeyListener(this);
		left.add(objcoordinatesz);
		objcheckCoordinatesz=new JCheckBox();
		objcheckCoordinatesz.setBounds(170,r,50,20);
		objcheckCoordinatesz.addKeyListener(this);
		left.add(objcheckCoordinatesz);

		r+=30;

		JLabel ldx=new JLabel("dx:");
		ldx.setBounds(5,r,20,20);
		left.add(ldx);
		objcoordinatesdx=new DoubleTextField(8);
		objcoordinatesdx.setBounds(30,r,120,20);
		objcoordinatesdx.addKeyListener(this);
		left.add(objcoordinatesdx);
		objcheckCoordinatesdx=new JCheckBox();
		objcheckCoordinatesdx.setBounds(170,r,50,20);
		objcheckCoordinatesdx.addKeyListener(this);
		left.add(objcheckCoordinatesdx);

		r+=30;

		JLabel ldy=new JLabel("dy:");
		ldy.setBounds(5,r,20,20);
		left.add(ldy);
		objcoordinatesdy=new DoubleTextField(8);
		objcoordinatesdy.setBounds(30,r,120,20);
		objcoordinatesdy.addKeyListener(this);
		left.add(objcoordinatesdy);
		objcheckCoordinatesdy=new JCheckBox();
		objcheckCoordinatesdy.setBounds(170,r,50,20);
		objcheckCoordinatesdy.addKeyListener(this);
		left.add(objcheckCoordinatesdy);

		r+=30;

		JLabel ldz=new JLabel("dz:");
		ldz.setBounds(5,r,20,20);
		left.add(ldz);
		objcoordinatesdz=new DoubleTextField(8);
		objcoordinatesdz.setBounds(30,r,120,20);
		objcoordinatesdz.addKeyListener(this);
		left.add(objcoordinatesdz);
		objcheckCoordinatesdz=new JCheckBox();
		objcheckCoordinatesdz.setBounds(170,r,50,20);
		objcheckCoordinatesdz.addKeyListener(this);
		left.add(objcheckCoordinatesdz);
		
		if(DrawObject.IS_3D){
			
			objcoordinatesdx.setEnabled(false);
			objcoordinatesdy.setEnabled(false);
			objcoordinatesdz.setEnabled(false);
			objcheckCoordinatesdx.setEnabled(false);
			objcheckCoordinatesdy.setEnabled(false);
			objcheckCoordinatesdz.setEnabled(false);
		}

		r+=30;


		chooseObject=new JComboBox();
		chooseObject.addItem(new ValuePair("",""));
		//chooseObject.setBounds(50,r,50,20);
		chooseObject.addItemListener(this);
		chooseObject.addKeyListener(this);
		//left.add(chooseObject);
		
		
		chooseObjectPanel=new JButton("Object");
		chooseObjectPanel.setBounds(10,r,100,20);
		chooseObjectPanel.addActionListener(this);
		chooseObjectPanel.addKeyListener(this);
		left.add(chooseObjectPanel);

		r+=30;

		objectLabel=new JLabel();
		objectLabel.setFocusable(false);
		objectLabel.setBounds(10,r,100,100);

		Border border=BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		objectLabel.setBorder(border);
		left.add(objectLabel);
		
		JPanel moveObject=buildObjectMovePanel(120,r);
		left.add(moveObject);

		r+=100;

		choosePrevObject=new JButton("<");
		choosePrevObject.setBounds(10,r,50,20);
		choosePrevObject.addActionListener(this);
		choosePrevObject.addKeyListener(this);
		left.add(choosePrevObject);

		chooseNextObject=new JButton(">");
		chooseNextObject.setBounds(60,r,50,20);
		chooseNextObject.addActionListener(this);
		chooseNextObject.addKeyListener(this);
		left.add(chooseNextObject);		

		r+=30;
		
		colorObjChoice=new JTextField();
		colorObjChoice.setBounds(30,r,150,20);
		colorObjChoice.addKeyListener(this);
		colorObjChoice.setToolTipText("Opt. background color");
		left.add(colorObjChoice);
		JButton cho = new JButton(">");
		cho.setBorder(new LineBorder(Color.gray,1));
		cho.addActionListener(
				new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						Color tcc = JColorChooser.showDialog(null,"Choose color",null);
						if(tcc!=null) {
							colorObjChoice.setBackground(tcc);
						}

					}


				}
		);
		cho.addKeyListener(this);
		cho.setBounds(5,r,20,20);
		left.add(cho);
		checkObjColor=new JCheckBox();
		checkObjColor.setBounds(200,r,50,20);
		checkObjColor.addKeyListener(this);
		checkObjColor.setOpaque(false);
		left.add(checkObjColor);

		r+=30;

		changeObject=new JButton(header+"Change O<u>b</u>ject"+footer);
		changeObject.addActionListener(this);
		changeObject.setFocusable(false);
		changeObject.setBounds(5,r,150,20);
		left.add(changeObject);

		r+=30;

		addObject=new JButton(header+"<u>I</u>nsert object"+footer);
		addObject.addActionListener(this);
		addObject.setFocusable(false);
		addObject.setBounds(5,r,150,20);
		left.add(addObject);

		r+=30;

		delObject=new JButton("Del object");
		delObject.addActionListener(this);
		delObject.setFocusable(false);
		delObject.setBounds(5,r,150,20);
		left.add(delObject);

		r+=30;
		
		deselectAllObjects=new JButton("Deselect all");
		deselectAllObjects.addActionListener(this);
		deselectAllObjects.setFocusable(false);
		deselectAllObjects.setBounds(5,r,150,20);
		left.add(deselectAllObjects);

		r+=30;

		add(left);

	}

	private void buildRightRoadPanel() {

		String header="<html><body>";
		String footer="</body></html>";

		right=new JPanel();
		right.setBounds(WIDTH+LEFT_BORDER,0,RIGHT_BORDER,HEIGHT);
		right.setLayout(null);
		
		Border leftBorder=BorderFactory.createTitledBorder("Road");
		right.setBorder(leftBorder);

		int r=25;

		checkMultiplePointsSelection=new JCheckBox("Multiple selection");
		checkMultiplePointsSelection.setBounds(30,r,150,20);
		checkMultiplePointsSelection.addKeyListener(this);
		right.add(checkMultiplePointsSelection);

		r+=30;

		JLabel lx=new JLabel("x:");
		lx.setBounds(5,r,20,20);
		right.add(lx);
		coordinatesx=new DoubleTextField(8);
		coordinatesx.setBounds(30,r,120,20);
		coordinatesx.addKeyListener(this);
		right.add(coordinatesx);
		checkCoordinatesx=new JCheckBox();
		checkCoordinatesx.setBounds(180,r,30,20);
		checkCoordinatesx.addKeyListener(this);
		right.add(checkCoordinatesx);

		r+=30;

		JLabel ly=new JLabel("y:");
		ly.setBounds(5,r,20,20);
		right.add(ly);
		coordinatesy=new DoubleTextField(8);
		coordinatesy.setBounds(30,r,120,20);
		coordinatesy.addKeyListener(this);
		right.add(coordinatesy);
		checkCoordinatesy=new JCheckBox();
		checkCoordinatesy.setBounds(180,r,30,20);
		checkCoordinatesy.addKeyListener(this);
		right.add(checkCoordinatesy);

		r+=30;

		JLabel lz=new JLabel("z:");
		lz.setBounds(5,r,20,20);
		right.add(lz);
		coordinatesz=new DoubleTextField(8);
		coordinatesz.setBounds(30,r,120,20);
		coordinatesz.addKeyListener(this);
		right.add(coordinatesz);
		checkCoordinatesz=new JCheckBox();
		checkCoordinatesz.setBounds(180,r,30,20);
		checkCoordinatesz.addKeyListener(this);
		right.add(checkCoordinatesz);

		r+=30;



		chooseTexture=new JComboBox();
		chooseTexture.addItem(new ValuePair("",""));
		/*chooseTexture.setBounds(35,r,50,20);*/
		chooseTexture.addItemListener(this);
		chooseTexture.addKeyListener(this);
		/*right.add(chooseTexture);*/

		choosePanelTexture=new JButton("Texture");
		choosePanelTexture.setBounds(5,r,100,20);
		choosePanelTexture.addActionListener(this);
		choosePanelTexture.addKeyListener(this);
		right.add(choosePanelTexture);
		
		r+=30;

		textureLabel=new JLabel();
		textureLabel.setFocusable(false);
		textureLabel.setBounds(5,r,100,100);
		Border border=BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		textureLabel.setBorder(border);
		right.add(textureLabel);

		JPanel moveRoad=buildRoadMovePanel(120,r);
		right.add(moveRoad);

		r+=100;
		
		choosePrevTexture=new JButton("<");
		choosePrevTexture.setBounds(5,r,50,20);
		choosePrevTexture.addActionListener(this);
		choosePrevTexture.addKeyListener(this);
		right.add(choosePrevTexture);

		chooseNextTexture=new JButton(">");
		chooseNextTexture.setBounds(55,r,50,20);
		chooseNextTexture.addActionListener(this);
		chooseNextTexture.addKeyListener(this);
		right.add(chooseNextTexture);
		
	
		
		r+=30;

		colorRoadChoice=new JTextField();
		colorRoadChoice.setBounds(30,r,120,20);
		colorRoadChoice.addKeyListener(this);
		colorRoadChoice.setToolTipText("Opt. background color");
		right.add(colorRoadChoice);
		JButton cho = new JButton(">");
		cho.setBorder(new LineBorder(Color.gray,1));
		cho.addActionListener(
				new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						Color tcc = JColorChooser.showDialog(null,"Choose color",null);
						if(tcc!=null) {
							colorRoadChoice.setBackground(tcc);
						}

					}


				}
		);
		cho.addKeyListener(this);
		cho.setBounds(5,r,20,20);
		right.add(cho);
		checkRoadColor=new JCheckBox();
		checkRoadColor.setBounds(180,r,30,20);
		checkRoadColor.addKeyListener(this);
		checkRoadColor.setOpaque(false);
		right.add(checkRoadColor);

		r+=30;

		addPoint=new JButton(header+"Insert point"+footer);
		addPoint.addActionListener(this);
		addPoint.setFocusable(false);
		addPoint.setBounds(5,r,150,20);
		right.add(addPoint);

		r+=30;

		changePoint=new JButton(header+"Change <u>P</u>oint"+footer);
		changePoint.addActionListener(this);
		changePoint.setFocusable(false);
		changePoint.setBounds(5,r,150,20);
		right.add(changePoint);

		r+=30;
		

		changePolygon=new JButton(header+"Change Pol<u>y</u>gon"+footer);
		changePolygon.addActionListener(this);
		changePolygon.setFocusable(false);
		changePolygon.setBounds(5,r,150,20);
		right.add(changePolygon);

		r+=30;
		
		startBuildPolygon=new JButton(header+"Start polygo<u>n</u> <br/> points sequence"+footer);
		startBuildPolygon.addActionListener(this);
		startBuildPolygon.addKeyListener(this);
		startBuildPolygon.setFocusable(false);
		startBuildPolygon.setBounds(5,r,150,35);
		right.add(startBuildPolygon);

		r+=45;
			
		buildPolygon=new JButton(header+"Bui<u>l</u>d polygon"+footer);
		buildPolygon.addActionListener(this);
		buildPolygon.addKeyListener(this);
		buildPolygon.setFocusable(false);
		buildPolygon.setBounds(5,r,150,20);
		right.add(buildPolygon);
		

		r+=30;
		
		polygonDetail=new JButton(header+"Polygon detail"+footer);
		polygonDetail.addActionListener(this);
		polygonDetail.addKeyListener(this);
		polygonDetail.setFocusable(false);
		polygonDetail.setBounds(5,r,150,20);
		right.add(polygonDetail);
		

		r+=30;

	

		deselectAll=new JButton(header+"D<u>e</u>select all"+footer);
		deselectAll.addActionListener(this);
		deselectAll.setFocusable(false);
		deselectAll.setBounds(5,r,150,20);
		right.add(deselectAll);
		
		
		r+=30;
		
		deleteSelection=new JButton(header+"<u>D</u>elete selection"+footer);
		deleteSelection.addActionListener(this);
		deleteSelection.addKeyListener(this);
		deleteSelection.setFocusable(false);
		deleteSelection.setBounds(5,r,150,20);
		right.add(deleteSelection);

		r+=30;
		
		mergeSelectedPoints=new JButton(header+"<u>M</u>erge selected<br/>points"+footer);
		mergeSelectedPoints.addActionListener(this);
		mergeSelectedPoints.addKeyListener(this);
		mergeSelectedPoints.setFocusable(false);
		mergeSelectedPoints.setBounds(5,r,150,35);
		right.add(mergeSelectedPoints);

		add(right);


	}


	private JPanel buildRoadMovePanel(int i, int r) {

		JPanel move=new JPanel();
		move.setBounds(i,r,100,100);
		move.setLayout(null);

		Border border = BorderFactory.createEtchedBorder();
		move.setBorder(border);
		
		roadMove=new DoubleTextField();
		roadMove.setBounds(30,40,40,20);
		roadMove.setToolTipText("Position increment");
		move.add(roadMove);
		roadMove.addKeyListener(this);
		
		moveRoadUp=new JButton(new ImageIcon("lib/trianglen.jpg"));
		moveRoadUp.setBounds(40,10,20,20);
		moveRoadUp.addActionListener(this);
		moveRoadUp.setFocusable(false);
		move.add(moveRoadUp);
		
		moveRoadDown=new JButton(new ImageIcon("lib/triangles.jpg"));
		moveRoadDown.setBounds(40,70,20,20);
		moveRoadDown.addActionListener(this);
		moveRoadDown.setFocusable(false);
		move.add(moveRoadDown);
		
		moveRoadLeft=new JButton(new ImageIcon("lib/triangleo.jpg"));
		moveRoadLeft.setBounds(5,40,20,20);
		moveRoadLeft.addActionListener(this);
		moveRoadLeft.setFocusable(false);
		move.add(moveRoadLeft);
		
		moveRoadRight=new JButton(new ImageIcon("lib/trianglee.jpg"));
		moveRoadRight.setBounds(75,40,20,20);
		moveRoadRight.addActionListener(this);
		moveRoadRight.setFocusable(false);
		move.add(moveRoadRight);
		
		moveRoadTop=new JButton(new ImageIcon("lib/up.jpg"));
		moveRoadTop.setBounds(5,70,20,20);
		moveRoadTop.addActionListener(this);
		moveRoadTop.setFocusable(false);
		move.add(moveRoadTop);
		
		moveRoadBottom=new JButton(new ImageIcon("lib/down.jpg"));
		moveRoadBottom.setBounds(75,70,20,20);
		moveRoadBottom.addActionListener(this);
		moveRoadBottom.setFocusable(false);
		move.add(moveRoadBottom);

		return move;

	}

	private JPanel buildObjectMovePanel(int i, int r) {

		JPanel move=new JPanel();
		move.setBounds(i,r,100,100);
		move.setLayout(null);

		Border border = BorderFactory.createEtchedBorder();
		move.setBorder(border);
		
		objMove=new DoubleTextField();
		objMove.setBounds(30,40,40,20);
		objMove.setToolTipText("Position increment");
		move.add(objMove);
		objMove.addKeyListener(this);
		
		moveObjUp=new JButton(new ImageIcon("lib/trianglen.jpg"));
		moveObjUp.setBounds(40,10,20,20);
		moveObjUp.addActionListener(this);
		moveObjUp.setFocusable(false);
		move.add(moveObjUp);
		
		moveObjDown=new JButton(new ImageIcon("lib/triangles.jpg"));
		moveObjDown.setBounds(40,70,20,20);
		moveObjDown.addActionListener(this);
		moveObjDown.setFocusable(false);
		move.add(moveObjDown);
		
		moveObjLeft=new JButton(new ImageIcon("lib/triangleo.jpg"));
		moveObjLeft.setBounds(5,40,20,20);
		moveObjLeft.addActionListener(this);
		moveObjLeft.setFocusable(false);
		move.add(moveObjLeft);
		
		moveObjRight=new JButton(new ImageIcon("lib/trianglee.jpg"));
		moveObjRight.setBounds(75,40,20,20);
		moveObjRight.addActionListener(this);
		moveObjRight.setFocusable(false);
		move.add(moveObjRight);
		
		moveObjTop=new JButton(new ImageIcon("lib/up.jpg"));
		moveObjTop.setBounds(5,70,20,20);
		moveObjTop.addActionListener(this);
		moveObjTop.setFocusable(false);
		move.add(moveObjTop);
		
		moveObjBottom=new JButton(new ImageIcon("lib/down.jpg"));
		moveObjBottom.setBounds(75,70,20,20);
		moveObjBottom.addActionListener(this);
		moveObjBottom.setFocusable(false);
		move.add(moveObjBottom);

		return move;

	}

	private void buildBottomPanel() {
		bottom=new JPanel();
		bottom.setBounds(0,HEIGHT,LEFT_BORDER+WIDTH+RIGHT_BORDER,BOTTOM_BORDER);
		bottom.setLayout(null);
		JLabel lscreenpoint = new JLabel();
		lscreenpoint.setText("Position x,y: ");
		lscreenpoint.setBounds(2,2,100,20);
		bottom.add(lscreenpoint);
		screenPoint=new JLabel();
		screenPoint.setText(",");
		screenPoint.setBounds(120,2,300,20);
		bottom.add(screenPoint);
		add(bottom);
	}

	private void undoObjects() {


		drawObjects=(Vector) oldObjects.pop();
		if(oldObjects.size()==0)
			jmt41.setEnabled(false);
	}

	private void undoRoad() {

		super.undo();
		
		firePropertyChange("RoadEditorUndo", false, true);
	}

	public void prepareUndo() {
		prepareUndoObjects();
		prepareUndoRoad();
	}

	private void prepareUndoRoad() {
		jmt42.setEnabled(true);
		
		super.prepareUndo();


	}

	private void prepareUndoObjects() {
		jmt41.setEnabled(true);
		if(oldObjects.size()==MAX_STACK_SIZE){
			oldObjects.removeElementAt(0);
		}
		oldObjects.push(cloneObjectsVector(drawObjects));
	}



	private Vector cloneObjectsVector(Vector drawObjects) {
		Vector newDrawObjects=new Vector();

		for(int i=0;i<drawObjects.size();i++){

			DrawObject dro=(DrawObject) drawObjects.elementAt(i);
			newDrawObjects.add(dro.clone());

		}

		return newDrawObjects;
	}

	private void changeSelectedObject() {
		
		prepareUndoObjects();

		for(int i=0;i<drawObjects.size();i++){
			
			DrawObject dro=(DrawObject) drawObjects.elementAt(i);

			if(!dro.isSelected())
				continue;

			(dro).setX(Double.parseDouble(objcoordinatesx.getText()));
			(dro).setY(Double.parseDouble(objcoordinatesy.getText()));
			(dro).setZ(Double.parseDouble(objcoordinatesz.getText()));
			
			if(!DrawObject.IS_3D){
				
				(dro).setDx(Double.parseDouble(objcoordinatesdx.getText()));
				(dro).setDy(Double.parseDouble(objcoordinatesdy.getText()));
				(dro).setDz(Double.parseDouble(objcoordinatesdz.getText()));
			 
			}
			ValuePair vp=(ValuePair) chooseObject.getSelectedItem();
			if(vp!=null && !vp.getValue().equals("")){
				
				int index=Integer.parseInt(vp.getId());
				 dro.setIndex(index);
				 
				 if(DrawObject.IS_3D){
					 
				     CubicMesh cm=objectMeshes[index]; 
				     dro.setDx(cm.getDeltaX2()-cm.getDeltaX());
				     dro.setDy(cm.getDeltaY2()-cm.getDeltaY());
				     dro.setDz(cm.getDeltaX());
				 }
			}	 
			dro.setHexColor(ZBuffer.fromColorToHex(colorObjChoice.getBackground()));

			dro.setSelected(false);
		}

		cleanObjects();
		displayAll();

	}
	

	private void moveSelectedObject(int dx, int dy, int dk) { 
		
		String sqty=objMove.getText();
		
		if(sqty==null || sqty.equals(""))
			return;
		
		double qty=Double.parseDouble(sqty);
		
		prepareUndoObjects();
		
		for(int i=0;i<drawObjects.size();i++){
			
			DrawObject dro=(DrawObject) drawObjects.elementAt(i);
			
			if(!dro.isSelected())
				continue;

			dro.setX(dro.getX()+dx*qty);
			dro.setY(dro.getY()+dy*qty);
			dro.setZ(dro.getZ()+dk*qty);
			//dro.setSelected(false);
		}

		//cleanObjects();
		displayAll();
		
	}

	private void changeSelectedRoadPoint() {

		prepareUndoRoad();

		int size=points.size();
		
		for(int j=0;j<size;j++){


			    Point4D p=(Point4D) points.elementAt(j);

				if(p.isSelected()){

					if(!"".equals(coordinatesx.getText()))
						p.x=Double.parseDouble(coordinatesx.getText());
					if(!"".equals(coordinatesy.getText()))
						p.y=Double.parseDouble(coordinatesy.getText());
					if(!"".equals(coordinatesz.getText()))
						p.z=Double.parseDouble(coordinatesz.getText());

					p.setHexColor(ZBuffer.fromColorToHex(colorRoadChoice.getBackground()));

					ValuePair vp=(ValuePair) chooseTexture.getSelectedItem();
					if(!vp.getId().equals(""))
						p.setIndex(Integer.parseInt(vp.getId()));

					p.setSelected(false);
				}

		}	
        firePropertyChange("RoadEditorUpdate", false, true);

		cleanPoints();
		displayAll();
	}
	
	private void changeSelectedRoadPolygon() {
		
		prepareUndoRoad();
		
		int sizel=lines.size();
		for(int j=0;j<sizel;j++){
			
			
			LineData ld=(LineData) lines.elementAt(j);
		    
		    
		    if(ld.isSelected){
		    	
		    	int indx=chooseTexture.getSelectedIndex();
		    	
		    	if(indx!=0){
	
					ValuePair vp=(ValuePair) chooseTexture.getItemAt(indx);
						
						
					ld.setTexture_index(Integer.parseInt(vp.getId()));
				
		    	}
		    	ld.setHexColor(ZBuffer.fromColorToHex(colorRoadChoice.getBackground()));
		    	
		    	ld.setSelected(false);
		    	
		    }
			
		}	
		
	}
	
	private void invertSelectedRoadPolygon() {
		
		prepareUndoRoad();
		
		int sizel=lines.size();
		for(int i=0;i<sizel;i++){
			
			
			LineData ld=(LineData) lines.elementAt(i);
		    
		    
		    if(ld.isSelected){
		    	
		    	LineData invertedLd=new LineData();
				
				for (int j = ld.size()-1; j >=0; j--) {
					invertedLd.addIndex(ld.getIndex(j));
				}
				lines.setElementAt(invertedLd,i);
		    	
		    }
			
		}	
		
	}

	private void mergeSelectedPoints() {
	
		prepareUndoRoad();
		
		Vector newPoints=new Vector();
		Vector newLines=new Vector();

		
		int firstPoint=-1;
		
		for(int i=0;i<points.size();i++){

			Point3D p=(Point3D) points.elementAt(i);
			if(!p.isSelected()) 
				newPoints.add(p);
			else if(firstPoint==-1){
				firstPoint=newPoints.size();
				newPoints.add(p);
			}
			else{
				
				
				
			}
				

		}


		for(int i=0;i<lines.size();i++){

			LineData ld=(LineData)lines.elementAt(i);
			if(ld.isSelected())
				continue;
			LineData newLd = new LineData();
			

			for(int j=0;j<ld.size();j++){

				Point3D p0=(Point3D) points.elementAt(ld.getIndex(j));
				if(!p0.isSelected()) 
					for(int k=0;k<newPoints.size();k++){

						Point3D np=(Point3D) newPoints.elementAt(k);
						if(np.equals(p0))
						{
							newLd.addIndex(k);
							break;
						}
					}
				else
					newLd.addIndex(firstPoint);

			}
			if(newLd.size()>1 )
				newLines.add(newLd);




		}

		points=newPoints;
		lines=newLines;
        deselectAll();
		
		displayAll();
	}
	

	private void deleteSelection() {
		
		prepareUndo();
		
		Vector newPoints=new Vector();
		Vector newLines=new Vector();

		int size=points.size();
		
		for(int i=0;i<size;i++){

			Point3D p=(Point3D) points.elementAt(i);
			if(!p.isSelected()) 
				newPoints.add(p);

		}

		
		int sizel=lines.size();
		for(int i=0;i<sizel;i++){

			LineData ld=(LineData) lines.elementAt(i);
			if(ld.isSelected())
				continue;
			LineData newLd = new LineData();

			boolean gotAllPoint=true;
			
			for(int j=0;j<ld.size();j++){

				Point3D p0=(Point3D) points.elementAt(ld.getIndex(j));
				if(!p0.isSelected()) 
					for(int k=0;k<newPoints.size();k++){

						Point3D np=(Point3D) newPoints.elementAt(k);
						if(np.equals(p0))
						{
							newLd.addIndex(k);
							break;
						}
						
					}
				else
					gotAllPoint=false;
				
				newLd.setTexture_index(ld.getTexture_index());
				newLd.setHexColor(ld.getHexColor());

			}
			if(newLd.size()>1 && gotAllPoint)
				newLines.add(newLd);




		}

		points=newPoints;
		lines=newLines;
        deselectAll();
	
		
	}
	
	private void moveSelectedRoadPoints(int dx, int dy,int dk) {

		String sqty=roadMove.getText();

		if(sqty==null || sqty.equals(""))
			return;

		double qty=Double.parseDouble(sqty);



		prepareUndoRoad();

		int size=points.size();
		for(int j=0;j<size;j++){


			Point4D p=(Point4D) points.elementAt(j);

			if(p.isSelected()){


				p.x+=qty*dx;

				p.y+=qty*dy;

				p.z+=qty*dk;

				//p.setSelected(false);



			}
		}	
		firePropertyChange("RoadEditorUpdate", false, true);

		cleanPoints();
		displayAll();

	}

	private void deleteObject() {

		prepareUndoObjects();
		
		for(int i=0;i<drawObjects.size();i++){
			
			DrawObject dro=(DrawObject) drawObjects.elementAt(i);
			if(dro.isSelected())
				drawObjects.remove(dro);
		}

	}

	private void addObject() {


		int index=0;
		int x=invertX(100);
		int y=invertY(400);
		int z=0;
		int dx=deltax;
		int dy=deltay;
		int dz=100;

		addObject(x,y,z,dx,dy,dz,index);

	}


	private void addObject(MouseEvent arg0) {
		Point p=arg0.getPoint();

		int x=invertX((int)p.getX());
		int y=invertY((int)p.getY());
		cleanObjects();
		int index=0;
		ValuePair vp=(ValuePair) chooseObject.getSelectedItem();
		if(vp!=null && !vp.getValue().equals(""))
			index=Integer.parseInt(vp.getId());
		addObject(x,y,0,deltax,deltay,100,index);

	}

	private void addObject(int x, int y, int z, int dx, int dy, int dz,int index) {

		prepareUndoObjects();

		DrawObject dro=new DrawObject();
		dro.setIndex(index);
		dro.x=x;
		dro.y=y;
		dro.z=z;
		dro.dx=dx;
		dro.dy=dy;
		dro.dz=dz;
		dro.setHexColor("FFFFFF");


		if(!"".equals(objcoordinatesx.getText()))
			dro.x=Double.parseDouble(objcoordinatesx.getText());
		if(!"".equals(objcoordinatesy.getText()))
			dro.y=Double.parseDouble(objcoordinatesy.getText());
		if(!"".equals(objcoordinatesz.getText()))
			dro.z=Double.parseDouble(objcoordinatesz.getText());
		if(!"".equals(objcoordinatesdx.getText()))
			dro.dx=Double.parseDouble(objcoordinatesdx.getText());
		if(!"".equals(objcoordinatesdy.getText()))
			dro.dy=Double.parseDouble(objcoordinatesdy.getText());
		if(!"".equals(objcoordinatesdz.getText()))
			dro.dz=Double.parseDouble(objcoordinatesdz.getText());

		ValuePair vp=(ValuePair) chooseObject.getSelectedItem();
		if(vp!=null && !vp.getValue().equals("")){
			
			
			dro.index=Integer.parseInt(vp.getId());
		
		
		}	
		if(DrawObject.IS_3D){
			
			
			CubicMesh mesh=objectMeshes[dro.index].clone();
			dro.setMesh(mesh);
		}
		dro.setHexColor(ZBuffer.fromColorToHex(colorObjChoice.getBackground()));

		drawObjects.add(dro);


	}



	public void saveObjects() throws FileNotFoundException{
		fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setDialogTitle("Save objects");
		if(currentDirectory!=null)
			fc.setCurrentDirectory(currentDirectory);
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			currentDirectory=fc.getCurrentDirectory();
			File file = fc.getSelectedFile();
			PrintWriter pr = new PrintWriter(new FileOutputStream(file));
			saveObjects(pr);
			pr.close(); 

		} 
	}

	private void saveObjects(PrintWriter pr) {
		
		try {

            pr.println("<objects>");			 
			for(int i=0;i<drawObjects.size();i++){

				DrawObject dro=(DrawObject) drawObjects.elementAt(i);
				String str=dro.getX()+"_"+dro.getY()+"_"+dro.getZ()+"_"+
				dro.getDx()+"_"+dro.getDy()+"_"+dro.getDz()+"_"+dro.getIndex()+"_"+dro.getHexColor();
				pr.println(str);
			}
			pr.println("</objects>");
				

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void loadObjectsFromFile(){	

		fc=new JFileChooser();
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setDialogTitle("Load objects ");
		if(currentDirectory!=null)
			fc.setCurrentDirectory(currentDirectory);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			currentDirectory=fc.getCurrentDirectory();
			File file = fc.getSelectedFile();
			loadObjectsFromFile(file);


		}
	}
	
	

	private void saveLandscape()  {
		
		fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setDialogTitle("Save landscape");
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
				saveLines(pr);
				saveObjects(pr);
				pr.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}



	private void loadLanscape() {
		
		fc=new JFileChooser();
		fc.setDialogType(JFileChooser.OPEN_DIALOG);
		fc.setDialogTitle("Load landscape ");
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
            loadObjectsFromFile(file); 

		}
		
	}

	
	public void buildPoints(Vector points, String str) {

		StringTokenizer sttoken=new StringTokenizer(str,"_");

		while(sttoken.hasMoreElements()){

			String[] vals = sttoken.nextToken().split(",");

			Point4D p=new Point4D();
			
			p.x=Double.parseDouble(vals[0]);
			p.y=Double.parseDouble(vals[1]);
			p.z=Double.parseDouble(vals[2]);

			points.add(p);
		}




	}
	
	public String decomposeLineData(LineData ld) {

		String str="";
		
		str+="T"+ld.texture_index;
		str+=",C"+ld.getHexColor();

		for(int j=0;j<ld.size();j++){
			
			
			
			str+=",";
			
			str+=ld.getIndex(j);

		}

		return str;
	}
	
	public void buildLines(Vector lines, String str) {

		StringTokenizer sttoken=new StringTokenizer(str,"_");

		while(sttoken.hasMoreElements()){

			String[] vals = sttoken.nextToken().split(",");

			LineData ld=new LineData();

			ld.texture_index=Integer.parseInt(vals[0].substring(1));
			
			ld.hexColor=vals[1].substring(1);
			
			for(int i=2;i<vals.length;i++)
				ld.addIndex(Integer.parseInt(vals[i]));


			lines.add(ld);
		}




	}
	


	public void loadObjectsFromFile(File file){

		oldRoads=new Stack();
		drawObjects=new Vector();

		try {
			BufferedReader br=new BufferedReader(new FileReader(file));

			boolean read=false;

			String str=null;

			while((str=br.readLine())!=null){
				if(str.indexOf("#")>=0 || str.length()==0)
					continue;
				
				if(str.indexOf("objects")>=0){
					read=!read;
				    continue;
				}	
				
				if(!read)
					continue;
				
				DrawObject dro=buildDrawObject(str);
				drawObjects.add(dro);

				if(DrawObject.IS_3D){
					CubicMesh mesh=objectMeshes[dro.getIndex()].clone();
					dro.setMesh(mesh);
				}
			}

			br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}



	private DrawObject buildDrawObject(String str) {
		DrawObject dro=new DrawObject();

		StringTokenizer tok=new StringTokenizer(str,"_");
		dro.x=Double.parseDouble(tok.nextToken());
		dro.y=Double.parseDouble(tok.nextToken());
		dro.z=Double.parseDouble(tok.nextToken());
		dro.dx=Double.parseDouble(tok.nextToken());
		dro.dy=Double.parseDouble(tok.nextToken());
		dro.dz=Double.parseDouble(tok.nextToken());
		dro.index=Integer.parseInt(tok.nextToken());
		dro.hexColor=tok.nextToken();
		return dro;
	}



	public void actionPerformed(ActionEvent arg0) {
		Object o=arg0.getSource();

		if(o==jmt_load_landscape){
			
				loadLanscape();
		}
		else if(o==jmt_save_landscape){
			
				saveLandscape();

		}		
		else if(o==jmt31){

			isUseTextures=jmt31.isSelected();
			displayAll();

		}
		else if(o==jmt41){
			undoObjects();
		}
		else if(o==jmt42){
			undoRoad();
		}
		else if(o==jmt51){
			showPreview();
		}
		else if(o==jmt52){
			showAltimetry();
		}
		else if(o==jmt53){
			buildNewGrid();
		}
		else if(o==jmt54){
			addBendMesh();
		}
		else if(o==addObject){
			addObject();
			displayAll();
		}
		else if(o==delObject){
			deleteObject();
			displayAll();
		}
		else if(o==addPoint){
			addPoint();
			displayAll();
		}
		else if(o==mergeSelectedPoints){
			
			
			mergeSelectedPoints();
			
		}
		else if(o==changePoint){
			changeSelectedRoadPoint();
			displayAll();
		}
		else if(o==changePolygon){
			changeSelectedRoadPolygon();
			displayAll();
		}	
		else if(o==startBuildPolygon){
			startBuildPolygon();
			displayAll();
		}	
		else if(o==buildPolygon){
			buildPolygon();
			displayAll();
		}
		else if(o==polygonDetail){
			polygonDetail();
			//displayAll();
		}
		else if(o==changeObject){
			changeSelectedObject();
			displayAll();
		}
		else if(o==deleteSelection){
			deleteSelection();
			displayAll();
		}
		else if(o==deselectAll){
			deselectAll();
		}
		else if(o==deselectAllObjects){
			cleanObjects();
		}
		else if(o==choosePanelTexture){
			
			TexturesPanel tp=new TexturesPanel(worldImages,100,100);
			
			int indx=tp.getSelectedIndex();
			if(indx!=-1)
				chooseTexture.setSelectedIndex(indx+1);
			
		}
		else if(o==chooseNextTexture){
			int indx=chooseTexture.getSelectedIndex();
			if(indx<chooseTexture.getItemCount()-1)
				chooseTexture.setSelectedIndex(indx+1);
		}
		else if(o==chooseObjectPanel){
			
			TexturesPanel tp=new TexturesPanel(objectImages,100,100);
			
			int indx=tp.getSelectedIndex();
			if(indx!=-1)
				chooseObject.setSelectedIndex(indx+1);
		}
		else if(o==choosePrevTexture){
			int indx=chooseTexture.getSelectedIndex();
			if(indx>0)
				chooseTexture.setSelectedIndex(indx-1);
		}
		else if(o==chooseNextObject){
			int indx=chooseObject.getSelectedIndex();
			if(indx<chooseObject.getItemCount()-1)
				chooseObject.setSelectedIndex(indx+1);
		}
		else if(o==choosePrevObject){
			int indx=chooseObject.getSelectedIndex();
			if(indx>0)
				chooseObject.setSelectedIndex(indx-1);
		}
		else if(o==moveRoadUp){

			moveSelectedRoadPoints(0,1,0);

		}
		else if(o==moveRoadDown){

			moveSelectedRoadPoints(0,-1,0);

		}
		else if(o==moveRoadLeft){

			moveSelectedRoadPoints(-1,0,0);

		}
		else if(o==moveRoadRight){

			moveSelectedRoadPoints(+1,0,0);

		}
		else if(o==moveRoadTop){

			moveSelectedRoadPoints(0,0,1);

		}
		else if(o==moveRoadBottom){

			moveSelectedRoadPoints(0,0,-1);

		}
		else if(o==moveObjUp){

			moveSelectedObject(0,1,0);

		}
		else if(o==moveObjDown){

			moveSelectedObject(0,-1,0);

		}
		else if(o==moveObjLeft){

			moveSelectedObject(-1,0,0);

		}
		else if(o==moveObjRight){

			moveSelectedObject(+1,0,0);

		}
		else if(o==moveObjTop){

			moveSelectedObject(0,0,1);

		}
		else if(o==moveObjBottom){

			moveSelectedObject(0,0,-1);

		}
	}




	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	public void menuDeselected(MenuEvent e) {
		redrawAfterMenu=true;

	}

    public void menuSelected(MenuEvent arg0) {
    	
    	super.menuSelected(arg0);
    	
    	Object o = arg0.getSource();
    	
		if(o==help_jm){
			
			help();
			
		}
    }
    
	private void help() {
	
		
		HelpPanel hp=new HelpPanel(300,200,this.getX()+100,this.getY(),HelpPanel.ROAD_EDITOR_HELP_TEXT,this);
		
	}

	public static void main(String[] args) {

		RoadEditor re=new RoadEditor("New road editor");
		re.initialize();
	}
	
	
	private void buildNewGrid() { 
		
		RoadEditorGridManager regm=new RoadEditorGridManager(null);
		
		if(regm.getReturnValue()!=null){
			
			RoadEditorGridManager roadEGM=(RoadEditorGridManager) regm.getReturnValue();
			


			
			double z_value=0;
			
			int numx=roadEGM.NX;
			int numy=roadEGM.NY;
			
			double dx=roadEGM.DX;
			double dy=roadEGM.DY;
			
			int tot=numx*numy;
			
			points=new Vector();
			lines=new Vector();
			
			points.setSize(numy*numx);
			
			
			for(int i=0;i<numx;i++)
				for(int j=0;j<numy;j++)
				{
					
					Point4D p=new Point4D(i*dx,j*dy,z_value);
				
					points.setElementAt(p,i+j*numx);

				}

			
			for(int i=0;i<numx-1;i++)
				for(int j=0;j<numy-1;j++){

	
					//lower base
					int pl1=i+numx*j;
					int pl2=i+numx*(j+1);
					int pl3=i+1+numx*(j+1);
					int pl4=i+1+numx*j;
										
					lines.add(new LineData(pl1, pl4, pl3, pl2));
					
				}
			
			
		}
		
		displayAll();
		
	}
	

	private void addBendMesh() {
		
		
		prepareUndo();
		
		
		Point3D origin=null;
		int originPos=-1;
		
		int size=points.size();
		
		for(int i=0;i<size;i++){

			Point3D p=(Point3D) points.elementAt(i);
			if(p.isSelected()) 
				{	
					origin=p;
					originPos=i;
					break;
				
				}

		}
		
		if(origin==null){
			
			JOptionPane.showMessageDialog(this,"Select a center");
			return;
		}
		
		BendBuilder bb=new BendBuilder(origin);
		
		if(bb.getBendMesh()!=null){
			
			PolygonMesh bm=bb.getBendMesh();
			
			int base=points.size()-1;
			
			//do no double the origin!
			for (int i = 1; i < bm.points.length; i++) {
				
				Point4D p=(Point4D) bm.points[i];
								
				points.add(p);
				
				
			}
			
			for (int i = 0; i < bm.polygonData.size(); i++) {
				LineData ld = (LineData) bm.polygonData.elementAt(i);
				
				for (int j = 0; j < ld.size(); j++) {
					
					int index=ld.getIndex(j);
					
					if(index==0)
						index=originPos;
					else
						index+=base;
					
					ld.setIndex(j,index);
				}
				
				lines.add(ld);
			}
			
			displayAll();
			
		}
		
	}
	
	public void buildPolygon() {
		
		prepareUndo();

		if(polygon.lineDatas.size()<3){
			deselectAll();
			return;
		}	
       lines.add(polygon);

		deselectAll();

	}
	
	private void showPreview() {
		if(points.size()==0)
			return;
		RoadEditorPreviewPanel preview=new RoadEditorPreviewPanel(this);
		
	}
	
	private void showAltimetry() {
		
		if(points.size()==0)
			return;
		RoadAltimetryPanel altimetry=new RoadAltimetryPanel(this);
	}


	public void mouseClicked(MouseEvent arg0) {

		int buttonNum=arg0.getButton();
		//right button click
		if(buttonNum==MouseEvent.BUTTON3)
			//addObject(arg0);
			addPoint(arg0);
		else{
			selectPoint(arg0.getX(),arg0.getY());			
		}	
		displayAll();
	}
	
	private void addPoint(MouseEvent arg0) {
		
		prepareUndo();
		
		Point p=arg0.getPoint();

		int x=invertX((int)p.getX());
		int y=invertY((int)p.getY());
		
		int index=0;
		ValuePair vp=(ValuePair) chooseTexture.getSelectedItem();
		if(vp!=null && !vp.getValue().equals(""))
			index=Integer.parseInt(vp.getId());
		else
			index=0;
		
		Point4D point=new Point4D(x,y,0,LineData.GREEN_HEX,index);
		points.add(point);
		

	}
	

	public void addPoint() {
		
		prepareUndo();
			

		if("".equals(coordinatesx.getText()) |
				"".equals(coordinatesy.getText()) |
				"".equals(coordinatesz.getText())
		)
			return;
		double x=Double.parseDouble(coordinatesx.getText());
		double y=Double.parseDouble(coordinatesy.getText());
		double z=Double.parseDouble(coordinatesz.getText());

		int index=0;
		ValuePair vp=(ValuePair) chooseTexture.getSelectedItem();
		if(vp!=null && !vp.getValue().equals(""))
			index=Integer.parseInt(vp.getId());
		else
			index=0;
		
		Point4D point=new Point4D(x,y,0,LineData.GREEN_HEX,index);
		points.add(point);

	}

	private void deselectAllPoints(){

		
		int size=points.size();
		for(int j=0;j<size;j++){


		    Point4D p=(Point4D) points.elementAt(j);

			p.setSelected(false);
				
		}	
		int sizel=lines.size();
		for(int j=0;j<sizel;j++){
			
			
			LineData ld=(LineData) lines.elementAt(j);
		    ld.setSelected(false);	
		}

	}

	private void deselectAllLines(){
		
		int sizel=lines.size();
		for(int j=0;j<sizel;j++){
			
			
			LineData ld=(LineData) lines.elementAt(j);
		    ld.setSelected(false);	
		}
	}


	private void selectPoint(int x, int y) {
		
		if(!checkMultiplePointsSelection.isSelected()) 
			polygon=new LineData();

		//select point from road
		boolean found=false;
		
		int size=points.size();
		
		for(int j=0;j<size;j++){


		    Point4D p=(Point4D) points.elementAt(j);

				int xo=convertX(p.x);
				int yo=convertY(p.y);

				Rectangle rect=new Rectangle(xo-5,yo-5,10,10);
				if(rect.contains(x,y)){

					if(!checkCoordinatesx.isSelected())
						coordinatesx.setText(""+p.x);
					if(!checkCoordinatesy.isSelected())
						coordinatesy.setText(""+p.y);
					if(!checkCoordinatesz.isSelected())
						coordinatesz.setText(""+p.z);
				

					p.setSelected(true);
					
					polygon.addIndex(j);

					found=true;

				}
				else if(!checkMultiplePointsSelection.isSelected())
					p.setSelected(false);
			

		}
	
		//select object

		for(int i=0;i<drawObjects.size();i++){

			DrawObject dro=(DrawObject) drawObjects.elementAt(i);
			if(!checkMultipleObjectsSelection.isSelected())
				dro.setSelected(false);
			
			int yo=convertY(dro.y)+5;
			int xo=convertX(dro.x)-5;
			Rectangle rect=new Rectangle(xo,yo-10,10,10);
			if(rect.contains(x,y))
			{
				dro.setSelected(true);
				if(!objcheckCoordinatesx.isSelected())
					objcoordinatesx.setText(""+dro.x);
				if(!objcheckCoordinatesy.isSelected())
					objcoordinatesy.setText(""+dro.y);
				if(!objcheckCoordinatesz.isSelected())
					objcoordinatesz.setText(""+dro.z);
				if(!objcheckCoordinatesdx.isSelected())
					objcoordinatesdx.setText(""+dro.dx);
				if(!objcheckCoordinatesdy.isSelected())
					objcoordinatesdy.setText(""+dro.dy);
				if(!objcheckCoordinatesdz.isSelected())
					objcoordinatesdz.setText(""+dro.dz);
				for(int k=0;k<chooseObject.getItemCount();k++){

					ValuePair vp=(ValuePair) chooseObject.getItemAt(k);
					if(vp.getId().equals(""+dro.index) )
						chooseObject.setSelectedItem(vp);
				}
				colorObjChoice.setBackground(ZBuffer.fromHexToColor(dro.hexColor));
				if(checkMultipleObjectsSelection.isSelected()){
					found=true;
					break;
				}	
			}

		}
		if(found){
			deselectAllLines();
			return;
		}
		
		//select polygon
		int sizel=lines.size();
		for(int j=0;j<sizel;j++){
			
			
			LineData ld=(LineData) lines.elementAt(j);
		    Polygon3D pol=buildPolygon(ld,points,false);
		    
		    if(pol.contains(x,y)){
		    	
		    	found=true;
		    	
				for(int k=0;k<chooseTexture.getItemCount();k++){

					ValuePair vp=(ValuePair) chooseTexture.getItemAt(k);
					if(vp.getId().equals(""+ld.getTexture_index())) 
						chooseTexture.setSelectedItem(vp);
				}
				
				if(ld.hexColor!=null)
					colorRoadChoice.setBackground(ZBuffer.fromHexToColor(ld.hexColor));
		    	
		    	ld.setSelected(true);
		    	
		    }
			else if(!checkMultiplePointsSelection.isSelected())
				ld.setSelected(false);
			
		}	
		if(!found)
			deselectAllPoints();
		

	}
	
	public Polygon3D buildPolygon(LineData ld,Vector points, boolean isReal) {



		int size=ld.size();

		int[] cxr=new int[size];
		int[] cyr=new int[size];
		int[] czr=new int[size];


		for(int i=0;i<size;i++){


			int num=ld.getIndex(i);

			Point4D p=(Point4D) points.elementAt(num);


			if(isReal){

				//real coordinates
				cxr[i]=(int)(p.x);
				cyr[i]=(int)(p.y);
				
			}
			else {
				
				cxr[i]=convertX(p.x);
				cyr[i]=convertY(p.y);
				
			}
			


		}


		Polygon3D p3dr=new Polygon3D(size,cxr,cyr,czr);

        return p3dr;

	}
	
	public void deselectAllObjects(){
		
		int size=drawObjects.size();
		
		for(int i=0;i<size;i++){
			
			DrawObject dro=(DrawObject) drawObjects.elementAt(i);
			dro.setSelected(false);
		}
	}

	public void cleanObjects(){
		
		if(!objcheckCoordinatesx.isSelected())	objcoordinatesx.setText("");
		if(!objcheckCoordinatesy.isSelected())objcoordinatesy.setText("");
		if(!objcheckCoordinatesz.isSelected())objcoordinatesz.setText("");
		
		if(!objcheckCoordinatesdx.isSelected())objcoordinatesdx.setText("");
		if(!objcheckCoordinatesdy.isSelected())objcoordinatesdy.setText("");
		if(!objcheckCoordinatesdz.isSelected())objcoordinatesdz.setText("");
		
		
		if(!checkObjColor.isSelected())checkObjColor.setBackground(ZBuffer.fromHexToColor("FFFFFF"));
		
		deselectAllObjects();
	}
	
	public void cleanPoints(){
		
		if(!checkCoordinatesx.isSelected())	coordinatesx.setText("");
		if(!checkCoordinatesy.isSelected())coordinatesy.setText("");
		if(!checkCoordinatesz.isSelected())coordinatesz.setText("");
		
		if(!checkRoadColor.isSelected())checkRoadColor.setBackground(ZBuffer.fromHexToColor("FFFFFF"));
	}
	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mousePressed(MouseEvent arg0) {
	
		  int x = arg0.getX();
	      int y = arg0.getY();
	      currentRect = new Rectangle(x, y, 0, 0);


	}








	private void selectPointsWithRectangle() {

		int x0=Math.min(currentRect.x,currentRect.x+currentRect.width);
		int x1=Math.max(currentRect.x,currentRect.x+currentRect.width);
		int y0=Math.min(currentRect.y,currentRect.y+currentRect.height);
		int y1=Math.max(currentRect.y,currentRect.y+currentRect.height);


		if(!checkCoordinatesx.isSelected())
			coordinatesx.setText("");
		if(!checkCoordinatesy.isSelected())
			coordinatesy.setText("");
		if(!checkCoordinatesz.isSelected())
			coordinatesz.setText("");

		//select point from road
		boolean found=false;

		int size=points.size();
		
		for(int j=0;j<size;j++){


			Point4D p=(Point4D) points.elementAt(j);

			int xo=convertX(p.x);
			int yo=convertY(p.y);


			if(xo>=x0 && xo<=x1 && yo>=y0 && yo<=y1  ){

				p.setSelected(true);
				found=true;


			}
			else if(!checkMultiplePointsSelection.isSelected())
				p.setSelected(false);


		}
		if(found){
			deselectAllObjects();
		    deselectAllLines();  
		}
		

		

	}



	public void keyPressed(KeyEvent arg0) {

		int code =arg0.getKeyCode();
		if(code==KeyEvent.VK_DOWN )
			down();
		else if(code==KeyEvent.VK_UP  )
			up();
		else if(code==KeyEvent.VK_LEFT )
		{	
			MOVX=MOVX-10; 
			displayAll();
		}
		else if(code==KeyEvent.VK_RIGHT  )
		{	 
			MOVX=MOVX+10;   
			displayAll();
		}
		else if(code==KeyEvent.VK_D  )
		{	
			deleteSelection();
			displayAll();
		}
		else if(code==KeyEvent.VK_N  )
		{	
			startBuildPolygon();
			displayAll();
		}
		else if(code==KeyEvent.VK_P  )
		{	
			changeSelectedRoadPoint();
			displayAll();
		}
		else if(code==KeyEvent.VK_B  )
		{	
			changeSelectedObject();
			displayAll();
		}
		else if(code==KeyEvent.VK_I  )
		{ 
			addObject();
			displayAll();
		}
		else if(code==KeyEvent.VK_L  )
		{  
			buildPolygon();
			displayAll();
		}
		else if(code==KeyEvent.VK_Y  )
		{ 
			changeSelectedRoadPolygon();
			displayAll();
		}
		else if(code==KeyEvent.VK_E  )
		{ 
			deselectAll();
		}
		else if(code==KeyEvent.VK_F1  )
		{ 
			zoom(+1);
			displayAll();
		}
		else if(code==KeyEvent.VK_F2  )
		{  
			zoom(-1);
			displayAll();
		}
		else if(code==KeyEvent.VK_LESS )
		{	
			 
			invertSelectedRoadPolygon(); 
			displayAll();
		}

	}




	public void up(){
		MOVY=MOVY-10;
		displayAll();

	}

	public void down(){
		MOVY=MOVY+10;
		displayAll();

	}


	private void zoom(int i) {
		
		
		
		double alfa=1.0;
		if(i>0){
			alfa=0.5;
			
			if(dx==1 || dy==1)
				return;
		}
		else {
			alfa=2.0;
		      	
		}
			
				
		dx=(int) (dx*alfa);
		dy=(int) (dy*alfa);
		
		MOVX+=(int) ((WIDTH/2+MOVX)*(1.0/alfa-1.0));
		MOVY+=(int) ((-HEIGHT/2+MOVY)*(1.0/alfa-1.0));
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}





	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}





	public void mouseWheelMoved(MouseWheelEvent arg0) {
		int pix=arg0.getUnitsToScroll();
		if(pix>0) up();
		else down();

	}





	public void mouseDragged(MouseEvent e) {
		
		
		isDrawCurrentRect=true;
		updateSize(e);
		displayAll();

	}
	
	private void drawCurrentRect(Graphics2D bufGraphics) {
		
		if(!isDrawCurrentRect)
			return;
		//System.out.println(isDrawCurrentRect);
		int x0=Math.min(currentRect.x,currentRect.x+currentRect.width);
		int x1=Math.max(currentRect.x,currentRect.x+currentRect.width);
		int y0=Math.min(currentRect.y,currentRect.y+currentRect.height);
		int y1=Math.max(currentRect.y,currentRect.y+currentRect.height);
		
		bufGraphics.setColor(Color.WHITE);
		bufGraphics.drawRect(x0,y0,x1-x0,y1-y0);
		
	}

	
	public void mouseReleased(MouseEvent arg0) {
		
		isDrawCurrentRect=false;
		updateSize(arg0);
        selectPointsWithRectangle();
        displayAll();
       
	}

    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        currentRect.setSize(x - currentRect.x,
                            y - currentRect.y);
        
   	   
   
    }




	public void mouseMoved(MouseEvent e) {
		Point p=e.getPoint();
		screenPoint.setText(invertX((int)p.getX())+","+invertY((int)p.getY()));

	}

	public void itemStateChanged(ItemEvent arg0) {

		Object o=arg0.getSource();
		if(o==chooseTexture){

			ValuePair val=(ValuePair) chooseTexture.getSelectedItem();
			if(!val.getId().equals("")){

				int num=Integer.parseInt(val.getId());
				
				BufferedImage icon=new BufferedImage(100,100,BufferedImage.TYPE_3BYTE_BGR);
				icon.getGraphics().drawImage(worldImages[num],0,0,objectLabel.getWidth(),objectLabel.getHeight(),null);
				ImageIcon ii=new ImageIcon(icon);
				textureLabel.setIcon(ii);


			}
			else
				textureLabel.setIcon(null);

		}
		else if(o==chooseObject){

			ValuePair val=(ValuePair) chooseObject.getSelectedItem();
			if(!val.getId().equals("")){

				int num=Integer.parseInt(val.getId());
			 
				BufferedImage icon=new BufferedImage(100,100,BufferedImage.TYPE_3BYTE_BGR);
				icon.getGraphics().drawImage(objectImages[num],0,0,objectLabel.getWidth(),objectLabel.getHeight(),null);
				ImageIcon ii=new ImageIcon(icon);
				objectLabel.setIcon(ii);	
				

			}
			else
				{
				objectLabel.setIcon(null);
			     
				}
		}

	}
	
	public void propertyChange(PropertyChangeEvent arg0) {
		
		//System.out.println(arg0.getSource().getClass());
		if("paintDirtyRegions".equals(arg0.getPropertyName()) && redrawAfterMenu)
		{
			 displayAll();
			 redrawAfterMenu=false;
		}
		else if("roadUpdate".equals(arg0.getPropertyName()))
		{
			 displayAll();
		}
		
	}

	public class ValuePair{


		String id;
		String value;

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}

		public ValuePair(String id, String value) {
			super();
			this.id = id;
			this.value = value;
		}


		public String toString() {

			return this.value;
		}


	}

	/**
	 * 
	 * old-> new road migration function 
	 * 
	 * @param fileOut
	 * @param NX
	 * @param NY
	 * @param roadData
	 */
	
	public void migrateRoadToNewFormat(File fileIn,File fileOut,Point4D[][] roadData ) {

		int NX=0;
		int NY=0;
		
		try {
			BufferedReader br=new BufferedReader(new FileReader(fileIn));

			String snx=br.readLine();
			String sny=br.readLine();

			if(snx==null || sny==null) {

				br.close();
				return;
			}

			NX=Integer.parseInt(snx.substring(4));
			NY=Integer.parseInt(sny.substring(4));
			roadData=new Point4D[NY][NX];

			String str=null;
			int rows=0;
			while((str=br.readLine())!=null){

				roadData[rows]=buildRow(str,NX);

				rows++;	

			}

			br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		Vector points=new Vector();
		Vector lines=new Vector();
		
		points.setSize(NX*NY);
		
		
		for(int i=0;i<NX;i++)
			for(int j=0;j<NY;j++)
			{
				
				Point4D p=new Point4D(roadData[j][i].x,roadData[j][i].y,roadData[j][i].z,roadData[j][i].hexColor,roadData[j][i].getIndex());
			
				points.setElementAt(p,i+j*NX);

			}

		
		for(int i=0;i<NX-1;i++)
			for(int j=0;j<NY-1;j++){


				//lower base
				int pl1=i+NX*j;
				int pl2=i+NX*(j+1);
				int pl3=i+1+NX*(j+1);
				int pl4=i+1+NX*j;
									
				lines.add(new LineData(pl1, pl4, pl3, pl2));
				
			}


		PrintWriter pr;
		try {
			
			pr = new PrintWriter(new FileOutputStream(fileOut));
			pr.print("P=");

			int size=points.size();
			
			for(int i=0;i<size;i++){

				Point3D p=(Point3D) points.elementAt(i);

				pr.print(decomposePoint(p));
				if(i<size-1)
					pr.print("_");
			}	

			pr.print("\nL=");

			int sizel=lines.size();
			for(int i=0;i<sizel;i++){

				LineData ld=(LineData) lines.elementAt(i);
				
				Point4D p=(Point4D) points.elementAt(ld.getIndex(0));
				
				ld.setHexColor(p.getHexColor());
                ld.setTexture_index(p.getIndex());
				
				pr.print(decomposeLineData(ld));
				if(i<sizel-1)
					pr.print("_");
			}	

			pr.close(); 	
	

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	private Point4D[] buildRow(String string,int NX) {
		StringTokenizer stk=new StringTokenizer(string,"_");

		Point4D[] row = new Point4D[NX];
		int columns=0;
		while(stk.hasMoreTokens()){

			String[] vals=stk.nextToken().split(",");

			row[columns]=new Point4D();

			row[columns].x=Double.parseDouble(vals[0]);
			row[columns].y=Double.parseDouble(vals[1]);
			row[columns].z=Double.parseDouble(vals[2]);
			row[columns].setHexColor(vals[3]);
			row[columns].setIndex(Integer.parseInt(vals[4]));
			columns++;
		}

		return row;
	}



	public void setRoadData(String string, Vector lines, Vector points) {
		
		
		this.lines=lines;
		this.points=points;
		
		displayAll();
		
	}


}
