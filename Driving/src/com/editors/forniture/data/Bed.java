package com.editors.forniture.data;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.BPoint;
import com.LineData;
import com.Point3D;
import com.PolygonMesh;
import com.Segments;
import com.main.Renderer3D;

class Bed extends Forniture{

	private int N_FACES=4;
	private int N_PARALLELS=2;
	
	private double len;
	
	private Point3D[] upperBase=null;
	private Point3D[] lowerBase=null;
	private Point3D[][] lateralFaces=null; 
	

	Bed( double x_side, double y_side,double z_side,int forniture_type,
			double legLength, double leg_side,double front_height, double back_height,
			double side_width,double side_length, double side_height,
			double upper_width,double upper_length, double upper_height
			) {
		super();

		this.x_side = x_side;
		this.y_side = y_side;
		this.z_side = z_side;
		this.forniture_type = forniture_type;
		this.leg_length = legLength;
		this.leg_side = leg_side;
		this.back_height = back_height;
		this.front_height = front_height;
		
		this.side_width = side_width;
		this.side_length = side_length;
		this.side_height = side_height;
		
		this.upper_width = upper_width;
		this.upper_length = upper_length;
		this.upper_height = upper_height;
		
		len=2*(x_side+y_side);
		
		upperBase=new Point3D[N_FACES];
		
		upperBase[0]=new Point3D(0,y_side+z_side,0);			
		upperBase[1]=new Point3D(x_side,y_side+z_side,0);	
		upperBase[2]=new Point3D(x_side,y_side+z_side+y_side,0);	
		upperBase[3]=new Point3D(0,y_side+z_side+y_side,0);	
		
		lowerBase=new Point3D[N_FACES];
		
		lowerBase[0]=new Point3D(0,0,0);			
		lowerBase[1]=new Point3D(0,y_side,0);	
		lowerBase[2]=new Point3D(x_side,y_side,0);	
		lowerBase[3]=new Point3D(x_side,0,0);	
		

		
		lateralFaces=new Point3D[N_FACES+1][N_PARALLELS];

		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:
			
			double x=0; 

			for (int i = 0; i <=N_FACES; i++) {

			
				double y=y_side+z_side*j;

				lateralFaces[i][j]=new Point3D(x,y,0);
				
				double dx=x_side;
				
				if(i%2==1)
					dx=y_side;
				
				x+=dx;

			}
			
		}
		
		initMesh();
	}
	

	
	@Override
public void saveBaseCubicTexture(PolygonMesh mesh, File file) {
		
		isTextureDrawing=true;


		
		texture_side_dy=(int) (z_side/(N_PARALLELS-1));
		texture_side_dx=(int) (len/(N_FACES));
		

		Color backgroundColor=Color.green;

		
		IMG_WIDTH=(int) len+2*texture_x0;
		IMG_HEIGHT=(int) (z_side+y_side*2)+2*texture_y0;

		
		BufferedImage buf=new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_BYTE_INDEXED);
	
		try {


				Graphics2D bufGraphics=(Graphics2D)buf.getGraphics();
 
				bufGraphics.setColor(backgroundColor);
				bufGraphics.fillRect(0,0,IMG_WIDTH,IMG_HEIGHT);


				//draw lines for reference

				bufGraphics.setColor(Color.RED);
				bufGraphics.setStroke(new BasicStroke(0.1f));
				
				for (int j = 0; j <upperBase.length; j++) {

					double x0= calX(upperBase[j].x);
					double y0= calY(upperBase[j].y);
					
					double x1= calX(upperBase[(j+1)%upperBase.length].x);
					double y1= calY(upperBase[(j+1)%upperBase.length].y);

					bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);	 

				}

				
				//lowerbase
		
				bufGraphics.setColor(Color.BLUE);
				
				for (int j = 0; j <lowerBase.length; j++) {

					double x0= calX(lowerBase[j].x);
					double y0= calY(lowerBase[j].y);
					
					double x1= calX(lowerBase[(j+1)%lowerBase.length].x);
					double y1= calY(lowerBase[(j+1)%lowerBase.length].y);

					bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);			

				}


				//lateral surface
				bufGraphics.setColor(new Color(0,0,0));


				for(int j=0;j<N_PARALLELS-1;j++){

					//texture is open and periodical:

					for (int i = 0; i <N_FACES; i++) { 

						double x0=calX(lateralFaces[i][j].x);
						double x1=calX(lateralFaces[i+1][j].x);
						double y0=calY(lateralFaces[i][j].y);
						double y1=calY(lateralFaces[i][j+1].y);
						
						bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y0);
						bufGraphics.drawLine((int)x1,(int)y0,(int)x1,(int)y1);
						bufGraphics.drawLine((int)x1,(int)y1,(int)x0,(int)y1);
						bufGraphics.drawLine((int)x0,(int)y1,(int)x0,(int)y0);
					}

				}	
				
			
				ImageIO.write(buf,"gif",file);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private ArrayList<Point3D> buildTexturePoints() {
		
		isTextureDrawing=false;
		
		ArrayList<Point3D> texture_points=new ArrayList<Point3D>();
		


		
		
		return texture_points;
	}
	@Override
	public double calX(double x){
		
		return texture_x0+x;
	}
	@Override
	public double calY(double y){
		if(isTextureDrawing)
			return IMG_HEIGHT-(texture_y0+y);
		else
			return texture_y0+y;
	}
	
	private void initMesh( ) {
		
		points=new ArrayList<Point3D>();
		polyData=new ArrayList<LineData>();

		n=0;

		Segments b0=new Segments(0,x_side,0,y_side,leg_length,z_side);

		BPoint[][][] body=new BPoint[2][2][2];

		body[0][0][0]=addBPoint(0,0,0,b0);
		body[1][0][0]=addBPoint(1.0,0,0,b0);
		body[1][1][0]=addBPoint(1.0,1.0,0,b0);
		body[0][1][0]=addBPoint(0,1.0,0,b0);

		body[0][0][1]=addBPoint(0,0,1.0,b0);
		body[1][0][1]=addBPoint(1.0,0,1.0,b0);
		body[1][1][1]=addBPoint(1.0,1.0,1.0,b0);
		body[0][1][1]=addBPoint(0,1.0,1.0,b0);

		addLine(body[0][0][1],body[1][0][1],body[1][1][1],body[0][1][1],Renderer3D.CAR_TOP);		

		addLine(body[0][0][0],body[0][0][1],body[0][1][1],body[0][1][0],Renderer3D.CAR_LEFT);				

		addLine(body[1][0][0],body[1][1][0],body[1][1][1],body[1][0][1],Renderer3D.CAR_RIGHT);

		addLine(body[0][1][0],body[0][1][1],body[1][1][1],body[1][1][0],Renderer3D.CAR_FRONT);

		addLine(body[0][0][0],body[1][0][0],body[1][0][1],body[0][0][1],Renderer3D.CAR_BACK);

		addLine(body[0][0][0],body[0][1][0],body[1][1][0],body[1][0][0],Renderer3D.CAR_BOTTOM);

		/// footboard

		Segments footEdge=new Segments(0,x_side,-leg_side,leg_side,leg_length,front_height);

		BPoint[][][] footboard=new BPoint[2][2][2];

		footboard[0][0][0]=addBPoint(0,0,0,footEdge);
		footboard[1][0][0]=addBPoint(1,0,0,footEdge);
		footboard[1][1][0]=addBPoint(1,1,0,footEdge);
		footboard[0][1][0]=addBPoint(0,1,0,footEdge);


		addLine(footboard[0][0][0],footboard[0][1][0],footboard[1][1][0],footboard[1][0][0],Renderer3D.CAR_BOTTOM);


		footboard[0][0][1]=addBPoint(0,0,1,footEdge);
		footboard[1][0][1]=addBPoint(1,0,1,footEdge);
		footboard[1][1][1]=addBPoint(1,1,1,footEdge);
		footboard[0][1][1]=addBPoint(0,1,1,footEdge);

		addLine(footboard[0][0][0],footboard[0][0][1],footboard[0][1][1],footboard[0][1][0],Renderer3D.CAR_LEFT);

		addLine(footboard[0][1][0],footboard[0][1][1],footboard[1][1][1],footboard[1][1][0],Renderer3D.CAR_FRONT);

		addLine(footboard[1][1][0],footboard[1][1][1],footboard[1][0][1],footboard[1][0][0],Renderer3D.CAR_RIGHT);

		addLine(footboard[1][0][0],footboard[1][0][1],footboard[0][0][1],footboard[0][0][0],Renderer3D.CAR_BACK);

		addLine(footboard[0][0][1],footboard[1][0][1],footboard[1][1][1],footboard[0][1][1],Renderer3D.CAR_TOP);	




		/// back left leg

		Segments balLeg=new Segments(0,leg_side,-leg_side,leg_side,0,leg_length);

		BPoint[][][] blLeg=new BPoint[2][2][2];

		blLeg[0][0][0]=addBPoint(0,0,0,balLeg);
		blLeg[1][0][0]=addBPoint(1,0,0,balLeg);
		blLeg[1][1][0]=addBPoint(1,1,0,balLeg);
		blLeg[0][1][0]=addBPoint(0,1,0,balLeg);


		addLine(blLeg[0][0][0],blLeg[0][1][0],blLeg[1][1][0],blLeg[1][0][0],Renderer3D.CAR_BOTTOM);


		blLeg[0][0][1]=addBPoint(0,0,1,balLeg);
		blLeg[1][0][1]=addBPoint(1,0,1,balLeg);
		blLeg[1][1][1]=addBPoint(1,1,1,balLeg);
		blLeg[0][1][1]=addBPoint(0,1,1,balLeg);

		addLine(blLeg[0][0][0],blLeg[0][0][1],blLeg[0][1][1],blLeg[0][1][0],Renderer3D.CAR_LEFT);

		addLine(blLeg[0][1][0],blLeg[0][1][1],blLeg[1][1][1],blLeg[1][1][0],Renderer3D.CAR_FRONT);

		addLine(blLeg[1][1][0],blLeg[1][1][1],blLeg[1][0][1],blLeg[1][0][0],Renderer3D.CAR_RIGHT);

		addLine(blLeg[1][0][0],blLeg[1][0][1],blLeg[0][0][1],blLeg[0][0][0],Renderer3D.CAR_BACK);

		/// back right leg

		Segments barLeg=new Segments(x_side-leg_side,leg_side,-leg_side,leg_side,0,leg_length);

		BPoint[][][] brLeg=new BPoint[2][2][2];

		brLeg[0][0][0]=addBPoint(0,0,0,barLeg);
		brLeg[1][0][0]=addBPoint(1,0,0,barLeg);
		brLeg[1][1][0]=addBPoint(1,1,0,barLeg);
		brLeg[0][1][0]=addBPoint(0,1,0,barLeg);


		addLine(brLeg[0][0][0],brLeg[0][1][0],brLeg[1][1][0],brLeg[1][0][0],Renderer3D.CAR_BOTTOM);

		brLeg[0][0][1]=addBPoint(0,0,1,barLeg);
		brLeg[1][0][1]=addBPoint(1,0,1,barLeg);
		brLeg[1][1][1]=addBPoint(1,1,1,barLeg);
		brLeg[0][1][1]=addBPoint(0,1,1,barLeg);

		addLine(brLeg[0][0][0],brLeg[0][0][1],brLeg[0][1][1],brLeg[0][1][0],Renderer3D.CAR_LEFT);

		addLine(brLeg[0][1][0],brLeg[0][1][1],brLeg[1][1][1],brLeg[1][1][0],Renderer3D.CAR_FRONT);

		addLine(brLeg[1][1][0],brLeg[1][1][1],brLeg[1][0][1],brLeg[1][0][0],Renderer3D.CAR_RIGHT);

		addLine(brLeg[1][0][0],brLeg[1][0][1],brLeg[0][0][1],brLeg[0][0][0],Renderer3D.CAR_BACK);


		/// headboard

		Segments headEdge=new Segments(0,x_side,y_side,leg_side,leg_length,back_height);

		BPoint[][][] headboard=new BPoint[2][2][2];

		headboard[0][0][0]=addBPoint(0,0,0,headEdge);
		headboard[1][0][0]=addBPoint(1,0,0,headEdge);
		headboard[1][1][0]=addBPoint(1,1,0,headEdge);
		headboard[0][1][0]=addBPoint(0,1,0,headEdge);


		addLine(headboard[0][0][0],headboard[0][1][0],headboard[1][1][0],headboard[1][0][0],Renderer3D.CAR_BOTTOM);


		headboard[0][0][1]=addBPoint(0,0,1,headEdge);
		headboard[1][0][1]=addBPoint(1,0,1,headEdge);
		headboard[1][1][1]=addBPoint(1,1,1,headEdge);
		headboard[0][1][1]=addBPoint(0,1,1,headEdge);

		addLine(headboard[0][0][0],headboard[0][0][1],headboard[0][1][1],headboard[0][1][0],Renderer3D.CAR_LEFT);

		addLine(headboard[0][1][0],headboard[0][1][1],headboard[1][1][1],headboard[1][1][0],Renderer3D.CAR_FRONT);

		addLine(headboard[1][1][0],headboard[1][1][1],headboard[1][0][1],headboard[1][0][0],Renderer3D.CAR_RIGHT);

		addLine(headboard[1][0][0],headboard[1][0][1],headboard[0][0][1],headboard[0][0][0],Renderer3D.CAR_BACK);

		addLine(headboard[0][0][1],headboard[1][0][1],headboard[1][1][1],headboard[0][1][1],Renderer3D.CAR_TOP);	


		/// front left leg

		Segments frlLeg=new Segments(0,leg_side,y_side,leg_side,0,leg_length);

		BPoint[][][] flLeg=new BPoint[2][2][2];

		flLeg[0][0][0]=addBPoint(0,0,0,frlLeg);
		flLeg[1][0][0]=addBPoint(1,0,0,frlLeg);
		flLeg[1][1][0]=addBPoint(1,1,0,frlLeg);
		flLeg[0][1][0]=addBPoint(0,1,0,frlLeg);


		addLine(flLeg[0][0][0],flLeg[0][1][0],flLeg[1][1][0],flLeg[1][0][0],Renderer3D.CAR_BOTTOM);


		flLeg[0][0][1]=addBPoint(0,0,1,frlLeg);
		flLeg[1][0][1]=addBPoint(1,0,1,frlLeg);
		flLeg[1][1][1]=addBPoint(1,1,1,frlLeg);
		flLeg[0][1][1]=addBPoint(0,1,1,frlLeg);

		addLine(flLeg[0][0][0],flLeg[0][0][1],flLeg[0][1][1],flLeg[0][1][0],Renderer3D.CAR_LEFT);

		addLine(flLeg[0][1][0],flLeg[0][1][1],flLeg[1][1][1],flLeg[1][1][0],Renderer3D.CAR_FRONT);

		addLine(flLeg[1][1][0],flLeg[1][1][1],flLeg[1][0][1],flLeg[1][0][0],Renderer3D.CAR_RIGHT);

		addLine(flLeg[1][0][0],flLeg[1][0][1],flLeg[0][0][1],flLeg[0][0][0],Renderer3D.CAR_BACK);

		/// front right leg

		Segments frrLeg=new Segments(x_side-leg_side,leg_side,y_side,leg_side,0,leg_length);

		BPoint[][][] frLeg=new BPoint[2][2][2];

		frLeg[0][0][0]=addBPoint(0,0,0,frrLeg);
		frLeg[1][0][0]=addBPoint(1,0,0,frrLeg);
		frLeg[1][1][0]=addBPoint(1,1,0,frrLeg);
		frLeg[0][1][0]=addBPoint(0,1,0,frrLeg);


		addLine(frLeg[0][0][0],frLeg[0][1][0],frLeg[1][1][0],frLeg[1][0][0],Renderer3D.CAR_BOTTOM);


		frLeg[0][0][1]=addBPoint(0,0,1,frrLeg);
		frLeg[1][0][1]=addBPoint(1,0,1,frrLeg);
		frLeg[1][1][1]=addBPoint(1,1,1,frrLeg);
		frLeg[0][1][1]=addBPoint(0,1,1,frrLeg);

		addLine(frLeg[0][0][0],frLeg[0][0][1],frLeg[0][1][1],frLeg[0][1][0],Renderer3D.CAR_LEFT);

		addLine(frLeg[0][1][0],frLeg[0][1][1],frLeg[1][1][1],frLeg[1][1][0],Renderer3D.CAR_FRONT);

		addLine(frLeg[1][1][0],frLeg[1][1][1],frLeg[1][0][1],frLeg[1][0][0],Renderer3D.CAR_RIGHT);

		addLine(frLeg[1][0][0],frLeg[1][0][1],frLeg[0][0][1],frLeg[0][0][0],Renderer3D.CAR_BACK);
		
		
		//pillow
		
		double pillow_width=x_side-10;
		double pillow_lenght=pillow_width*0.571;		
		double pillow_height=pillow_width*0.186;
		
		Segments p0=new Segments(x_side*0.5,pillow_width,y_side-pillow_lenght,pillow_lenght,leg_length+z_side,pillow_height);

		int pnumx=2;
		int pnumy=2;
		int pnumz=2;
		
		BPoint[][][] pillow=new BPoint[pnumx][pnumy][pnumz];

		pillow[0][0][0]=addBPoint(-0.5,0,0,p0);
		pillow[1][0][0]=addBPoint(0.5,0,0,p0);
		pillow[1][1][0]=addBPoint(0.5,1.0,0,p0);
		pillow[0][1][0]=addBPoint(-0.5,1.0,0,p0);

		pillow[0][0][1]=addBPoint(-0.5,0,1.0,p0);
		pillow[1][0][1]=addBPoint(0.5,0,1.0,p0);
		pillow[1][1][1]=addBPoint(0.5,1.0,1.0,p0);
		pillow[0][1][1]=addBPoint(-0.5,1.0,1.0,p0);

		for (int i = 0; i < pnumx-1; i++) {


			for (int j = 0; j < pnumy-1; j++) {	

				for (int k = 0; k < pnumz-1; k++) {


					if(i==0){

						LineData leftFrontLD=addLine(pillow[i][j][k],pillow[i][j][k+1],pillow[i][j+1][k+1],pillow[i][j+1][k],Renderer3D.CAR_LEFT);
					}



					if(k+1==pnumz-1){
						LineData topillowLD=addLine(pillow[i][j][k+1],pillow[i+1][j][k+1],pillow[i+1][j+1][k+1],pillow[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					if(k==0){
						LineData bottomFrontLD=addLine(pillow[i][j][k],pillow[i][j+1][k],pillow[i+1][j+1][k],pillow[i+1][j][k],Renderer3D.CAR_BOTTOM);
					}
					
					if(j==0){
						LineData backFrontLD=addLine(pillow[i][j][k],pillow[i+1][j][k],pillow[i+1][j][k+1],pillow[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==pnumy-1){
						LineData frontFrontLD=addLine(pillow[i][j+1][k],pillow[i][j+1][k+1],pillow[i+1][j+1][k+1],pillow[i+1][j+1][k],Renderer3D.CAR_FRONT);
					}


					if(i+1==pnumx-1){

						LineData rightFrontLD=addLine(pillow[i+1][j][k],pillow[i+1][j+1][k],pillow[i+1][j+1][k+1],pillow[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}

				}

			}

		}
		


		//////// texture points
			
	
			
		texture_points=buildTexturePoints();
		

	}
	
}
