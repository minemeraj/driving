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

class Sofa extends Forniture{

	private int N_FACES=4;
	private int N_PARALLELS=2;
	
	private Point3D[] upperBase=null;
	private Point3D[] lowerBase=null;
	private Point3D[][] lateralFaces=null; 
	
	private Point3D[] lowerLegBase=null;
	private Point3D[][] lateralLegFaces=null; 
	
	private Point3D[] upperBackBase=null;
	private Point3D[][] lateralBackFaces=null;
	
	private Point3D[] upperLeftSideBase=null;
	private Point3D[][] lateralLeftSideFaces=null; 
	
	private Point3D[] upperRightSideBase=null;
	private Point3D[][] lateralRightSideFaces=null; 
	
	Sofa(double x_side, double y_side, double z_side,
			double leg_length,double leg_side,
			double back_height,
			double side_width,
			double side_length,
			double side_height			
			) {
		
		this.x_side=x_side;
		this.y_side=y_side;
		this.z_side=z_side;
		this.leg_length=leg_length;
		this.leg_side=leg_side;
		this.back_height=back_height;
		
		this.side_width=side_width;
		this.side_length=side_length;
		this.side_height=side_height;
		
		len=2*(x_side+y_side);
		
		double baseY=leg_side+leg_length+y_side+z_side;
		
		upperBase=new Point3D[N_FACES];
		
		upperBase[0]=new Point3D(0,baseY,0);			
		upperBase[1]=new Point3D(x_side,baseY,0);	
		upperBase[2]=new Point3D(x_side,baseY+y_side,0);	
		upperBase[3]=new Point3D(0,baseY+y_side,0);
		
		
		baseY=leg_side+leg_length;
		
		lowerBase=new Point3D[N_FACES];
		
		lowerBase[0]=new Point3D(0,baseY,0);			
		lowerBase[1]=new Point3D(0,baseY+y_side,0);	
		lowerBase[2]=new Point3D(x_side,baseY+y_side,0);	
		lowerBase[3]=new Point3D(x_side,baseY,0);		

		
		lateralFaces=new Point3D[N_FACES+1][N_PARALLELS];
		
		baseY=leg_side+leg_length+y_side;

		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:
			
			double x=0; 

			for (int i = 0; i <=N_FACES; i++) {

			
				double y=baseY+z_side*j;

				lateralFaces[i][j]=new Point3D(x,y,0);
				
				double dx=x_side;
				
				if(i%2==1)
					dx=y_side;
				
				x+=dx;

			}
			
		}	
		
		lowerLegBase=new Point3D[N_FACES];
		
		lowerLegBase[0]=new Point3D(0,0,0);			
		lowerLegBase[1]=new Point3D(0,leg_side,0);	
		lowerLegBase[2]=new Point3D(leg_side,leg_side,0);	
		lowerLegBase[3]=new Point3D(leg_side,0,0);		

		
		lateralLegFaces=new Point3D[N_FACES+1][N_PARALLELS];
		
		baseY=leg_side;

		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:
			
			double x=0; 

			for (int i = 0; i <=N_FACES; i++) {

			
				double y=baseY+leg_length*j;

				lateralLegFaces[i][j]=new Point3D(x,y,0);
				
				x+=leg_side;

			}
			
		}
		
		baseY=leg_side+leg_length+y_side+z_side+y_side+back_height;
		
		upperBackBase=new Point3D[N_FACES];
		
		upperBackBase[0]=new Point3D(0,baseY,0);			
		upperBackBase[1]=new Point3D(x_side,baseY,0);	
		upperBackBase[2]=new Point3D(x_side,baseY+leg_side,0);	
		upperBackBase[3]=new Point3D(0,baseY+leg_side,0);
				
		lateralBackFaces=new Point3D[N_FACES+1][N_PARALLELS];

		baseY=leg_side+leg_length+y_side+z_side+y_side;
		
		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:
			
			double x=0; 

			for (int i = 0; i <=N_FACES; i++) {

			
				double y=baseY+back_height*j;

				lateralBackFaces[i][j]=new Point3D(x,y,0);
				
				double dx=x_side;
				
				if(i%2==1)
					dx=leg_side;
				
				x+=dx;

			}
			
		}
		
		baseY=leg_side+leg_length+y_side+z_side+y_side+back_height+leg_side+side_height;
		
		upperLeftSideBase=new Point3D[N_FACES];
		
		upperLeftSideBase[0]=new Point3D(0,baseY,0);			
		upperLeftSideBase[1]=new Point3D(side_width,baseY,0);	
		upperLeftSideBase[2]=new Point3D(side_width,baseY+side_length,0);	
		upperLeftSideBase[3]=new Point3D(0,baseY+side_length,0);
				
		lateralLeftSideFaces=new Point3D[N_FACES+1][N_PARALLELS];

		baseY=leg_side+leg_length+y_side+z_side+y_side+back_height+leg_side;
		
		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:
			
			double x=0; 

			for (int i = 0; i <=N_FACES; i++) {

			
				double y=baseY+side_height*j;

				lateralLeftSideFaces[i][j]=new Point3D(x,y,0);
				
				double dx=side_width;
				
				if(i%2==1)
					dx=side_length;
				
				x+=dx;

			}
			
		}
		
		baseY=leg_side+leg_length+y_side+z_side+y_side+back_height+leg_side+side_height+side_height+side_length;
		
		upperRightSideBase=new Point3D[N_FACES];
		
		upperRightSideBase[0]=new Point3D(0,baseY,0);			
		upperRightSideBase[1]=new Point3D(side_width,baseY,0);	
		upperRightSideBase[2]=new Point3D(side_width,baseY+side_length,0);	
		upperRightSideBase[3]=new Point3D(0,baseY+side_length,0);
				
		lateralRightSideFaces=new Point3D[N_FACES+1][N_PARALLELS];

		baseY=leg_side+leg_length+y_side+z_side+y_side+back_height+leg_side+side_height+side_length;
		
		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:
			
			double x=0; 

			for (int i = 0; i <=N_FACES; i++) {

			
				double y=baseY+side_height*j;

				lateralRightSideFaces[i][j]=new Point3D(x,y,0);
				
				double dx=side_width;
				
				if(i%2==1)
					dx=side_length;
				
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
		IMG_HEIGHT=(int) (leg_side*2+leg_length+z_side+y_side*2+back_height+side_height+side_length+side_height+side_length)+2*texture_y0;

		
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
				

				
				//lowerbase
		
				bufGraphics.setColor(Color.BLUE);
				
				for (int j = 0; j <lowerLegBase.length; j++) {

					double x0= calX(lowerLegBase[j].x);
					double y0= calY(lowerLegBase[j].y);
					
					double x1= calX(lowerLegBase[(j+1)%lowerLegBase.length].x);
					double y1= calY(lowerLegBase[(j+1)%lowerLegBase.length].y);

					bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);			

				}


				//lateral surface
				bufGraphics.setColor(new Color(0,0,0));


				for(int j=0;j<N_PARALLELS-1;j++){

					//texture is open and periodical:

					for (int i = 0; i <N_FACES; i++) { 

						double x0=calX(lateralLegFaces[i][j].x);
						double x1=calX(lateralLegFaces[i+1][j].x);
						double y0=calY(lateralLegFaces[i][j].y);
						double y1=calY(lateralLegFaces[i][j+1].y);
						
						bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y0);
						bufGraphics.drawLine((int)x1,(int)y0,(int)x1,(int)y1);
						bufGraphics.drawLine((int)x1,(int)y1,(int)x0,(int)y1);
						bufGraphics.drawLine((int)x0,(int)y1,(int)x0,(int)y0);
					}

				}
				
				
				bufGraphics.setColor(Color.BLUE);
				
				for (int j = 0; j <upperBackBase.length; j++) {

					double x0= calX(upperBackBase[j].x);
					double y0= calY(upperBackBase[j].y);
					
					double x1= calX(upperBackBase[(j+1)%upperBackBase.length].x);
					double y1= calY(upperBackBase[(j+1)%upperBackBase.length].y);

					bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);			

				}


				//lateral surface
				bufGraphics.setColor(new Color(0,0,0));


				for(int j=0;j<N_PARALLELS-1;j++){

					//texture is open and periodical:

					for (int i = 0; i <N_FACES; i++) { 

						double x0=calX(lateralBackFaces[i][j].x);
						double x1=calX(lateralBackFaces[i+1][j].x);
						double y0=calY(lateralBackFaces[i][j].y);
						double y1=calY(lateralBackFaces[i][j+1].y);
						
						bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y0);
						bufGraphics.drawLine((int)x1,(int)y0,(int)x1,(int)y1);
						bufGraphics.drawLine((int)x1,(int)y1,(int)x0,(int)y1);
						bufGraphics.drawLine((int)x0,(int)y1,(int)x0,(int)y0);
					}

				}
				
				
				bufGraphics.setColor(Color.BLUE);
				
				for (int j = 0; j <upperLeftSideBase.length; j++) {

					double x0= calX(upperLeftSideBase[j].x);
					double y0= calY(upperLeftSideBase[j].y);
					
					double x1= calX(upperLeftSideBase[(j+1)%upperLeftSideBase.length].x);
					double y1= calY(upperLeftSideBase[(j+1)%upperLeftSideBase.length].y);

					bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);			

				}


				//lateral surface
				bufGraphics.setColor(new Color(0,0,0));


				for(int j=0;j<N_PARALLELS-1;j++){

					//texture is open and periodical:

					for (int i = 0; i <N_FACES; i++) { 

						double x0=calX(lateralLeftSideFaces[i][j].x);
						double x1=calX(lateralLeftSideFaces[i+1][j].x);
						double y0=calY(lateralLeftSideFaces[i][j].y);
						double y1=calY(lateralLeftSideFaces[i][j+1].y);
						
						bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y0);
						bufGraphics.drawLine((int)x1,(int)y0,(int)x1,(int)y1);
						bufGraphics.drawLine((int)x1,(int)y1,(int)x0,(int)y1);
						bufGraphics.drawLine((int)x0,(int)y1,(int)x0,(int)y0);
					}

				}
				
				
				bufGraphics.setColor(Color.BLUE);
				
				for (int j = 0; j <upperRightSideBase.length; j++) {

					double x0= calX(upperRightSideBase[j].x);
					double y0= calY(upperRightSideBase[j].y);
					
					double x1= calX(upperRightSideBase[(j+1)%upperRightSideBase.length].x);
					double y1= calY(upperRightSideBase[(j+1)%upperRightSideBase.length].y);

					bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);			

				}


				//lateral surface
				bufGraphics.setColor(new Color(0,0,0));


				for(int j=0;j<N_PARALLELS-1;j++){

					//texture is open and periodical:

					for (int i = 0; i <N_FACES; i++) { 

						double x0=calX(lateralRightSideFaces[i][j].x);
						double x1=calX(lateralRightSideFaces[i+1][j].x);
						double y0=calY(lateralRightSideFaces[i][j].y);
						double y1=calY(lateralRightSideFaces[i][j+1].y);
						
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
		
	
		int size=2*N_FACES+	(N_FACES+1)*(N_PARALLELS)+
				  N_FACES+	(N_FACES+1)*(N_PARALLELS)+
				  N_FACES+	(N_FACES+1)*(N_PARALLELS)+
				  N_FACES+	(N_FACES+1)*(N_PARALLELS)+
				  N_FACES+	(N_FACES+1)*(N_PARALLELS);
		
		ArrayList<Point3D> texture_points=new ArrayList<Point3D>(size);
		
		int count=0;
		//upperbase

		for (int j = 0; j <upperBase.length; j++) {

			
			
			double x= calX(upperBase[j].x);
			double y= calY(upperBase[j].y);

			Point3D p=new Point3D(x,y,0);			
			set(texture_points,count++,p);


		}
	
		
		for (int j = 0; j <lowerBase.length; j++) {

			double x= calX(lowerBase[j].x);
			double y= calY(lowerBase[j].y);

			Point3D p=new Point3D(x,y,0);			
			set(texture_points,count++,p);

		}


		//lateral surface


		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:

			for (int i = 0; i <=N_FACES; i++) {

				double x=calX(lateralFaces[i][j].x);
				double y=calY(lateralFaces[i][j].y);

				Point3D p=new Point3D(x,y,0);

				int texIndex=count+f(i,j,N_FACES+1,N_PARALLELS);
				//System.out.print(texIndex+"\t");
				set(texture_points,texIndex,p);
			}
			
		}
		
		count=(2*N_FACES)+(N_FACES+1)*N_PARALLELS;
		
		for (int j = 0; j <lowerLegBase.length; j++) {

			double x= calX(lowerLegBase[j].x);
			double y= calY(lowerLegBase[j].y);

			Point3D p=new Point3D(x,y,0);			
			set(texture_points,count++,p);

		}


		//lateral surface


		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:

			for (int i = 0; i <=N_FACES; i++) {

				double x=calX(lateralLegFaces[i][j].x);
				double y=calY(lateralLegFaces[i][j].y);

				Point3D p=new Point3D(x,y,0);

				int texIndex=count+f(i,j,N_FACES+1,N_PARALLELS);
				//System.out.print(texIndex+"\t");
				set(texture_points,texIndex,p);
			}
			
		}
		
		count=(3*N_FACES)+2*(N_FACES+1)*N_PARALLELS;
		
		for (int j = 0; j <upperBackBase.length; j++) {

			double x= calX(upperBackBase[j].x);
			double y= calY(upperBackBase[j].y);

			Point3D p=new Point3D(x,y,0);			
			set(texture_points,count++,p);

		}


		//lateral surface


		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:

			for (int i = 0; i <=N_FACES; i++) {

				double x=calX(lateralBackFaces[i][j].x);
				double y=calY(lateralBackFaces[i][j].y);

				Point3D p=new Point3D(x,y,0);

				int texIndex=count+f(i,j,N_FACES+1,N_PARALLELS);
				//System.out.print(texIndex+"\t");
				set(texture_points,texIndex,p);
			}
			
		}	
		
		
		count=(4*N_FACES)+3*(N_FACES+1)*N_PARALLELS;
		
		for (int j = 0; j <upperLeftSideBase.length; j++) {

			double x= calX(upperLeftSideBase[j].x);
			double y= calY(upperLeftSideBase[j].y);

			Point3D p=new Point3D(x,y,0);			
			set(texture_points,count++,p);

		}


		//lateral surface


		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:

			for (int i = 0; i <=N_FACES; i++) {

				double x=calX(lateralLeftSideFaces[i][j].x);
				double y=calY(lateralLeftSideFaces[i][j].y);

				Point3D p=new Point3D(x,y,0);

				int texIndex=count+f(i,j,N_FACES+1,N_PARALLELS);
				//System.out.print(texIndex+"\t");
				set(texture_points,texIndex,p);
			}
			
		}
		
		count=(5*N_FACES)+4*(N_FACES+1)*N_PARALLELS;
		
		for (int j = 0; j <upperLeftSideBase.length; j++) {

			double x= calX(upperRightSideBase[j].x);
			double y= calY(upperRightSideBase[j].y);

			Point3D p=new Point3D(x,y,0);			
			set(texture_points,count++,p);

		}


		//lateral surface


		for(int j=0;j<N_PARALLELS;j++){

			//texture is open and periodical:

			for (int i = 0; i <=N_FACES; i++) {

				double x=calX(lateralRightSideFaces[i][j].x);
				double y=calY(lateralRightSideFaces[i][j].y);

				Point3D p=new Point3D(x,y,0);

				int texIndex=count+f(i,j,N_FACES+1,N_PARALLELS);
				//System.out.print(texIndex+"\t");
				set(texture_points,texIndex,p);
			}
			
		}
		
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
	
		
	private PolygonMesh initMesh() {


		texture_points=buildTexturePoints();
		
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
		
		addLine(body[0][0][1],body[1][0][1],body[1][1][1],body[0][1][1],0,1,2,3,Renderer3D.CAR_TOP);		

		addLine(body[0][0][0],body[0][0][1],body[0][1][1],body[0][1][0],11,16,17,12,Renderer3D.CAR_LEFT);				

		addLine(body[1][0][0],body[1][1][0],body[1][1][1],body[1][0][1],9,10,15,14,Renderer3D.CAR_RIGHT);
		
		addLine(body[0][1][0],body[0][1][1],body[1][1][1],body[1][1][0],10,15,16,11,Renderer3D.CAR_FRONT);
		
		addLine(body[0][0][0],body[1][0][0],body[1][0][1],body[0][0][1],8,9,14,13,Renderer3D.CAR_BACK);
		
		addLine(body[0][0][0],body[0][1][0],body[1][1][0],body[1][0][0],4,5,6,7,Renderer3D.CAR_BOTTOM);
		
		
				
		/// back left leg
		
		Segments balLeg=new Segments(0,leg_side,0,leg_side,0,leg_length);
		
		BPoint[][][] blLeg=new BPoint[2][2][2];
				
		blLeg[0][0][0]=addBPoint(0,0,0,balLeg);
		blLeg[1][0][0]=addBPoint(1,0,0,balLeg);
		blLeg[1][1][0]=addBPoint(1,1,0,balLeg);
		blLeg[0][1][0]=addBPoint(0,1,0,balLeg);
		
		
		blLeg[0][0][1]=addBPoint(0,0,1,balLeg);
		blLeg[1][0][1]=addBPoint(1,0,1,balLeg);
		blLeg[1][1][1]=addBPoint(1,1,1,balLeg);
		blLeg[0][1][1]=addBPoint(0,1,1,balLeg);
		
		
		int c0=4+4+10;
		
		addLine(blLeg[0][0][0],blLeg[0][1][0],blLeg[1][1][0],blLeg[1][0][0],c0+0,c0+1,c0+2,c0+3,Renderer3D.CAR_BOTTOM);

		c0=(4+4+10)+4;
		
		addLine(blLeg[0][0][0],blLeg[0][0][1],blLeg[0][1][1],blLeg[0][1][0],c0+3,c0+8,c0+9,c0+4,Renderer3D.CAR_LEFT);

		addLine(blLeg[1][1][0],blLeg[1][1][1],blLeg[1][0][1],blLeg[1][0][0],c0+2,c0+7,c0+6,c0+1,Renderer3D.CAR_RIGHT);				

		addLine(blLeg[0][1][0],blLeg[0][1][1],blLeg[1][1][1],blLeg[1][1][0],c0+2,c0+7,c0+8,c0+3,Renderer3D.CAR_FRONT);
		
		addLine(blLeg[1][0][0],blLeg[1][0][1],blLeg[0][0][1],blLeg[0][0][0],c0+1,c0+6,c0+5,c0+0,Renderer3D.CAR_BACK);
		
		
		
		/// back right leg
		
		Segments barLeg=new Segments(x_side-leg_side,leg_side,0,leg_side,0,leg_length);
		
		BPoint[][][] brLeg=new BPoint[2][2][2];
		
		brLeg[0][0][0]=addBPoint(0,0,0,barLeg);
		brLeg[1][0][0]=addBPoint(1,0,0,barLeg);
		brLeg[1][1][0]=addBPoint(1,1,0,barLeg);
		brLeg[0][1][0]=addBPoint(0,1,0,barLeg);
		
		brLeg[0][0][1]=addBPoint(0,0,1,barLeg);
		brLeg[1][0][1]=addBPoint(1,0,1,barLeg);
		brLeg[1][1][1]=addBPoint(1,1,1,barLeg);
		brLeg[0][1][1]=addBPoint(0,1,1,barLeg);
		
		c0=4+4+10;
		
		addLine(brLeg[0][0][0],brLeg[0][1][0],brLeg[1][1][0],brLeg[1][0][0],c0+0,c0+1,c0+2,c0+3,Renderer3D.CAR_BOTTOM);
		
		c0=(4+4+10)+4;
		
		addLine(brLeg[0][0][0],brLeg[0][0][1],brLeg[0][1][1],brLeg[0][1][0],c0+3,c0+8,c0+9,c0+4,Renderer3D.CAR_LEFT);
		
		addLine(brLeg[1][1][0],brLeg[1][1][1],brLeg[1][0][1],brLeg[1][0][0],c0+2,c0+7,c0+6,c0+1,Renderer3D.CAR_RIGHT);

		addLine(brLeg[0][1][0],brLeg[0][1][1],brLeg[1][1][1],brLeg[1][1][0],c0+2,c0+7,c0+8,c0+3,Renderer3D.CAR_FRONT);

		addLine(brLeg[1][0][0],brLeg[1][0][1],brLeg[0][0][1],brLeg[0][0][0],c0+1,c0+6,c0+5,c0+0,Renderer3D.CAR_BACK);
		
		/// front left leg
		
		Segments frlLeg=new Segments(0,leg_side,y_side-leg_side,leg_side,0,leg_length);
		
		BPoint[][][] flLeg=new BPoint[2][2][2];
		
		flLeg[0][0][0]=addBPoint(0,0,0,frlLeg);
		flLeg[1][0][0]=addBPoint(1,0,0,frlLeg);
		flLeg[1][1][0]=addBPoint(1,1,0,frlLeg);
		flLeg[0][1][0]=addBPoint(0,1,0,frlLeg);
		
		
		flLeg[0][0][1]=addBPoint(0,0,1,frlLeg);
		flLeg[1][0][1]=addBPoint(1,0,1,frlLeg);
		flLeg[1][1][1]=addBPoint(1,1,1,frlLeg);
		flLeg[0][1][1]=addBPoint(0,1,1,frlLeg);
		
		c0=4+4+10;
		
		addLine(flLeg[0][0][0],flLeg[0][1][0],flLeg[1][1][0],flLeg[1][0][0],c0+0,c0+1,c0+2,c0+3,Renderer3D.CAR_BOTTOM);
		
		c0=(4+4+10)+4;
		
		addLine(flLeg[0][0][0],flLeg[0][0][1],flLeg[0][1][1],flLeg[0][1][0],c0+3,c0+8,c0+9,c0+4,Renderer3D.CAR_LEFT);
		
		addLine(flLeg[1][1][0],flLeg[1][1][1],flLeg[1][0][1],flLeg[1][0][0],c0+2,c0+7,c0+6,c0+1,Renderer3D.CAR_RIGHT);

		addLine(flLeg[0][1][0],flLeg[0][1][1],flLeg[1][1][1],flLeg[1][1][0],c0+2,c0+7,c0+8,c0+3,Renderer3D.CAR_FRONT);

		addLine(flLeg[1][0][0],flLeg[1][0][1],flLeg[0][0][1],flLeg[0][0][0],c0+1,c0+6,c0+5,c0+0,Renderer3D.CAR_BACK);
		
		/// front right leg
		
		Segments frrLeg=new Segments(x_side-leg_side,leg_side,y_side-leg_side,leg_side,0,leg_length);
		
		BPoint[][][] frLeg=new BPoint[2][2][2];
		
		frLeg[0][0][0]=addBPoint(0,0,0,frrLeg);
		frLeg[1][0][0]=addBPoint(1,0,0,frrLeg);
		frLeg[1][1][0]=addBPoint(1,1,0,frrLeg);
		frLeg[0][1][0]=addBPoint(0,1,0,frrLeg);
	
		frLeg[0][0][1]=addBPoint(0,0,1,frrLeg);
		frLeg[1][0][1]=addBPoint(1,0,1,frrLeg);
		frLeg[1][1][1]=addBPoint(1,1,1,frrLeg);
		frLeg[0][1][1]=addBPoint(0,1,1,frrLeg);
		
		c0=4+4+10;
		
		addLine(frLeg[0][0][0],frLeg[0][1][0],frLeg[1][1][0],frLeg[1][0][0],c0+0,c0+1,c0+2,c0+3,Renderer3D.CAR_BOTTOM);
		
		c0=(4+4+10)+4;
		
		addLine(frLeg[0][0][0],frLeg[0][0][1],frLeg[0][1][1],frLeg[0][1][0],c0+3,c0+8,c0+9,c0+4,Renderer3D.CAR_LEFT);
		
		addLine(frLeg[1][1][0],frLeg[1][1][1],frLeg[1][0][1],frLeg[1][0][0],c0+2,c0+7,c0+6,c0+1,Renderer3D.CAR_RIGHT);

		addLine(frLeg[0][1][0],frLeg[0][1][1],frLeg[1][1][1],frLeg[1][1][0],c0+2,c0+7,c0+8,c0+3,Renderer3D.CAR_FRONT);

		addLine(frLeg[1][0][0],frLeg[1][0][1],frLeg[0][0][1],frLeg[0][0][0],c0+1,c0+6,c0+5,c0+0,Renderer3D.CAR_BACK);

				
		//chair back: 
		Segments sofa_back=new Segments(0,x_side,0,leg_side,leg_length+z_side,back_height);
		
		BPoint[][][] back0=new BPoint[2][2][2];
		
		back0[0][0][0]=addBPoint(0,0,0,sofa_back);
		back0[1][0][0]=addBPoint(1,0,0,sofa_back);
		back0[1][1][0]=addBPoint(1,1,0,sofa_back);
		back0[0][1][0]=addBPoint(0,1,0,sofa_back);

		back0[0][0][1]=addBPoint(0,0,1,sofa_back);
		back0[1][0][1]=addBPoint(1,0,1,sofa_back);
		back0[1][1][1]=addBPoint(1,1,1,sofa_back);
		back0[0][1][1]=addBPoint(0,1,1,sofa_back);		

		
		c0=(4+4+10)+(4+10);
		
		addLine(back0[0][0][1],back0[1][0][1],back0[1][1][1],back0[0][1][1],c0+0,c0+1,c0+2,c0+3,Renderer3D.CAR_TOP);
		
		c0=(4+4+10)+(4+10)+4;
		
		addLine(back0[0][0][0],back0[0][0][1],back0[0][1][1],back0[0][1][0],c0+3,c0+8,c0+9,c0+4,Renderer3D.CAR_LEFT);

		addLine(back0[0][1][0],back0[0][1][1],back0[1][1][1],back0[1][1][0],c0+2,c0+7,c0+8,c0+3,Renderer3D.CAR_FRONT);

		addLine(back0[1][1][0],back0[1][1][1],back0[1][0][1],back0[1][0][0],c0+2,c0+7,c0+6,c0+1,Renderer3D.CAR_RIGHT);

		addLine(back0[1][0][0],back0[1][0][1],back0[0][0][1],back0[0][0][0],c0+1,c0+6,c0+5,c0+0,Renderer3D.CAR_BACK);

		
		//left_side: 
		Segments leftSide=new Segments(0,side_width,leg_side,side_length,leg_length+z_side,side_height);
		
		BPoint[][][] left_side=new BPoint[2][2][2];
		
		left_side[0][0][0]=addBPoint(0,0,0,leftSide);
		left_side[1][0][0]=addBPoint(1,0,0,leftSide);
		left_side[1][1][0]=addBPoint(1,1,0,leftSide);
		left_side[0][1][0]=addBPoint(0,1,0,leftSide);
				
		left_side[0][0][1]=addBPoint(0,0,1,leftSide);
		left_side[1][0][1]=addBPoint(1,0,1,leftSide);
		left_side[1][1][1]=addBPoint(1,1,1,leftSide);
		left_side[0][1][1]=addBPoint(0,1,1,leftSide);

		c0=(4+4+10)+(4+10)+(4+10);
		
		addLine(left_side[0][0][1],left_side[1][0][1],left_side[1][1][1],left_side[0][1][1],c0+0,c0+1,c0+2,c0+3,Renderer3D.CAR_TOP);
		
		c0=(4+4+10)+(4+10)+(4+10)+4;
		
		addLine(left_side[0][0][0],left_side[0][0][1],left_side[0][1][1],left_side[0][1][0],c0+3,c0+8,c0+9,c0+4,Renderer3D.CAR_LEFT);

		addLine(left_side[0][1][0],left_side[0][1][1],left_side[1][1][1],left_side[1][1][0],c0+2,c0+7,c0+8,c0+3,Renderer3D.CAR_FRONT);

		addLine(left_side[1][1][0],left_side[1][1][1],left_side[1][0][1],left_side[1][0][0],c0+2,c0+7,c0+6,c0+1,Renderer3D.CAR_RIGHT);

		addLine(left_side[1][0][0],left_side[1][0][1],left_side[0][0][1],left_side[0][0][0],c0+1,c0+6,c0+5,c0+0,Renderer3D.CAR_BACK);
		
		
		//right_side: 
		Segments rightSide=new Segments(x_side-side_width,side_width,leg_side,side_length,leg_length+z_side,side_height);
		
		BPoint[][][] right_side=new BPoint[2][2][2];
		
		right_side[0][0][0]=addBPoint(0,0,0,rightSide);
		right_side[1][0][0]=addBPoint(1,0,0,rightSide);
		right_side[1][1][0]=addBPoint(1,1,0,rightSide);
		right_side[0][1][0]=addBPoint(0,1,0,rightSide);
			
		right_side[0][0][1]=addBPoint(0,0,1,rightSide);
		right_side[1][0][1]=addBPoint(1,0,1,rightSide);
		right_side[1][1][1]=addBPoint(1,1,1,rightSide);
		right_side[0][1][1]=addBPoint(0,1,1,rightSide);
	
		c0=(4+4+10)+(4+10)+(4+10)+(4+10);
		
		addLine(right_side[0][0][1],right_side[1][0][1],right_side[1][1][1],right_side[0][1][1],c0+0,c0+1,c0+2,c0+3,Renderer3D.CAR_TOP);
		
		c0=(4+4+10)+(4+10)+(4+10)+(4+10)+4;
		
		addLine(right_side[0][0][0],right_side[0][0][1],right_side[0][1][1],right_side[0][1][0],c0+3,c0+8,c0+9,c0+4,Renderer3D.CAR_LEFT);

		addLine(right_side[0][1][0],right_side[0][1][1],right_side[1][1][1],right_side[1][1][0],c0+2,c0+7,c0+8,c0+3,Renderer3D.CAR_FRONT);

		addLine(right_side[1][1][0],right_side[1][1][1],right_side[1][0][1],right_side[1][0][0],c0+2,c0+7,c0+6,c0+1,Renderer3D.CAR_RIGHT);

		addLine(right_side[1][0][0],right_side[1][0][1],right_side[0][0][1],right_side[0][0][0],c0+1,c0+6,c0+5,c0+0,Renderer3D.CAR_BACK);

		/////////

		//translatePoints(points,nw_x,nw_y);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;
	}
}
