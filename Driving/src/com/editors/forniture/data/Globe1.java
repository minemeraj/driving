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
import com.main.Renderer3D;

class Globe1 extends Forniture { 
	
	private double radius=0;
	
	private Point3D[][] northernHemisphere=null; 
	private Point3D[][] southernHemisphere=null;
	
	private Point3D northPole=null;
	private Point3D southPole=null;
	
	private double len;
	private double zHeight;

	Globe1(double radius,int N_MERIDIANS,int N_PARALLELS) {
		
		this.radius=radius;
		this.n_meridians=N_MERIDIANS;
		this.n_parallels=N_PARALLELS;
		
		zHeight=2*radius;
		len=4*radius;
		
		northernHemisphere=new Point3D[N_MERIDIANS][N_PARALLELS/2];
		southernHemisphere=new Point3D[N_MERIDIANS][N_PARALLELS/2];
		
	
		double xc=radius;
		double yc=radius;

		northPole=new Point3D(xc,yc,0);

		double dr=radius/(N_PARALLELS/2);
		double dteta=2*pi/(N_MERIDIANS);

		for(int j=0;j<N_PARALLELS/2;j++){

			double rr=dr+dr*j;

			for (int i = 0; i <N_MERIDIANS; i++) {

				double teta=i*dteta;
				
				double x=xc+rr*Math.cos(teta);
				double y=yc+rr*Math.sin(teta);

				northernHemisphere[i][j]=new Point3D(x,y,0);

			}
			
		}	
		
		xc=3*radius;
		yc=radius;
		
		for(int j=0;j<N_PARALLELS/2;j++){

			double rr=radius-dr*j;

			for (int i = 0; i <N_MERIDIANS; i++) {

				double teta=i*dteta;
				
				double x=xc+rr*Math.cos(teta);
				double y=yc+rr*Math.sin(teta);

				southernHemisphere[i][j]=new Point3D(x,y,0);

			}
			
		}	
	
		southPole=new Point3D(xc,yc,0);
		

		
		initMesh();
	}
	
	private void initMesh( ) {

		points=new ArrayList<Point3D>();
		polyData=new ArrayList<LineData>();

		n=0;

		double fi=(2*pi)/(n_meridians);
		double teta=0.5*pi/(n_parallels/2);
		
		BPoint northPole=addBPoint(0,0,radius);

		BPoint[][] nPoints=new BPoint[n_meridians][n_parallels/2];
		BPoint[][] sPoints=new BPoint[n_meridians][n_parallels/2];

		for (int i = 0; i < n_meridians; i++) {

			for (int j = 0; j <n_parallels/2; j++) {
				
				double tt=teta+j*teta;

				double x= radius*Math.cos(i*fi)*Math.sin(tt);
				double y= radius*Math.sin(i*fi)*Math.sin(tt);
				double z= radius*Math.cos(tt);

				nPoints[i][j]=
						addBPoint(x,y,z);

				points.set(nPoints[i][j].getIndex(),nPoints[i][j]);

			}
		}
		
		for (int i = 0; i < n_meridians; i++) {

			for (int j = 0; j <n_parallels/2; j++) {

				double tt=pi*0.5+j*teta;
								
				double x= radius*Math.cos(i*fi)*Math.sin(tt);
				double y= radius*Math.sin(i*fi)*Math.sin(tt);
				double z= radius*Math.cos(tt);

				sPoints[i][j]=
						addBPoint(x,y,z);

				points.set(nPoints[i][j].getIndex(),nPoints[i][j]);

			}
		}
		
		BPoint southPole=addBPoint(0,0,-radius);
		
		texture_points=buildTexturePoints();
		int northPoleIndex=0;

		int count=1;
        
		
		for (int i = 0; i <n_meridians; i++) { 
			
			LineData ld=new LineData();

			int texIndex=count+f(i,0,n_meridians,n_parallels/2);
			//System.out.print(texIndex+"\t");
			ld.addIndex(nPoints[i][0].getIndex(),texIndex,0,0);

			texIndex=count+f((i+1)%n_meridians,0,n_meridians,n_parallels/2);
			//System.out.print(texIndex+"\t");
			ld.addIndex(nPoints[(i+1)%n_meridians][0].getIndex(),texIndex,0,0);

			
			ld.addIndex(northPole.getIndex(),northPoleIndex,0,0);

			ld.setData(""+Renderer3D.getFace(ld,points));



			polyData.add(ld);
		}

		for (int i = 0; i <n_meridians; i++) { 

			for(int j=0;j<n_parallels/2-1;j++){ 

				LineData ld=new LineData();

				int texIndex=count+f(i,j,n_meridians,n_parallels/2);
				//System.out.print(texIndex+"\t");
				ld.addIndex(nPoints[i][j].getIndex(),texIndex,0,0);
				
				texIndex=count+f(i,j+1,n_meridians,n_parallels/2);
				//System.out.print(texIndex+"\t");
				ld.addIndex(nPoints[i][j+1].getIndex(),texIndex,0,0);
				
				texIndex=count+f((i+1)%n_meridians,j+1,n_meridians,n_parallels/2);
				//System.out.print(texIndex+"\t");
				ld.addIndex(nPoints[(i+1)%n_meridians][j+1].getIndex(),texIndex,0,0);

				texIndex=count+f((i+1)%n_meridians,j,n_meridians,n_parallels/2);
				//System.out.print(texIndex+"\t");
				ld.addIndex(nPoints[(i+1)%n_meridians][j].getIndex(),texIndex,0,0);


				ld.setData(""+Renderer3D.getFace(ld,points));



				polyData.add(ld);

			}



		}
		
		count=1+n_meridians*n_parallels/2;

		for (int i = 0; i <n_meridians; i++) { 

			for(int j=0;j<n_parallels/2-1;j++){ 

				LineData ld=new LineData();

				int texIndex=count+f(i,j,n_meridians,n_parallels/2);
				//System.out.print(texIndex+"\t");
				ld.addIndex(sPoints[i][j].getIndex(),texIndex,0,0);
				
				texIndex=count+f(i,j+1,n_meridians,n_parallels/2);
				//System.out.print(texIndex+"\t");
				ld.addIndex(sPoints[i][j+1].getIndex(),texIndex,0,0);
				
				texIndex=count+f((i+1)%n_meridians,j+1,n_meridians,n_parallels/2);
				//System.out.print(texIndex+"\t");
				ld.addIndex(sPoints[(i+1)%n_meridians][j+1].getIndex(),texIndex,0,0);

				texIndex=count+f((i+1)%n_meridians,j,n_meridians,n_parallels/2);
				//System.out.print(texIndex+"\t");
				ld.addIndex(sPoints[(i+1)%n_meridians][j].getIndex(),texIndex,0,0);


				ld.setData(""+Renderer3D.getFace(ld,points));



				polyData.add(ld);

			}



		}
		
		int spIndex=1+n_parallels*(n_meridians);
		
		for (int i = 0; i <n_meridians; i++) { 
			
			LineData ld=new LineData();			

			int texIndex=count+f(i,(n_parallels/2-1),n_meridians,n_parallels/2);
			//System.out.print(texIndex+"\t");
			ld.addIndex(sPoints[i][(n_parallels/2-1)].getIndex(),texIndex,0,0);
			
			ld.addIndex(southPole.getIndex(),spIndex,0,0);

			texIndex=count+f((i+1)%n_meridians,(n_parallels/2-1),n_meridians,n_parallels/2);
			//System.out.print(texIndex+"\t");
			ld.addIndex(sPoints[(i+1)%n_meridians][(n_parallels/2-1)].getIndex(),texIndex,0,0);
		

			ld.setData(""+Renderer3D.getFace(ld,points));



			polyData.add(ld);
		}
	}	
	@Override
	public void saveBaseCubicTexture(PolygonMesh mesh, File file) {
	
		isTextureDrawing=true;
		
	
		
		Color backgroundColor=Color.green;

		
		IMG_WIDTH=(int) len+2*texture_x0;
		IMG_HEIGHT=(int) (zHeight)+2*texture_y0;

		
		BufferedImage buf=new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_BYTE_INDEXED);
	
		try {


				Graphics2D bufGraphics=(Graphics2D)buf.getGraphics();
 
				bufGraphics.setColor(backgroundColor);
				bufGraphics.fillRect(0,0,IMG_WIDTH,IMG_HEIGHT);
				
				bufGraphics.setColor(Color.RED);
				bufGraphics.setStroke(new BasicStroke(0.1f));
		
	
				for (int i = 0; i <n_meridians; i++) { 
						
						double x0=calX(northernHemisphere[i][0].x);
						double y0=calY(northernHemisphere[i][0].y);
						double x1=calX(northernHemisphere[(i+1)%n_meridians][0].x);
						double y1=calY(northernHemisphere[(i+1)%n_meridians][0].y);
						
						double xp=calX(northPole.x);
						double yp=calY(northPole.y);
						
						bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);
						bufGraphics.drawLine((int)x1,(int)y1,(int)xp,(int)yp);
						bufGraphics.drawLine((int)xp,(int)yp,(int)x0,(int)y0);
				}
		
				//lateral surface
				bufGraphics.setColor(new Color(0,0,0));

				for(int j=0;j<n_parallels/2-1;j++){
					
					//texture is open and periodical:

					for (int i = 0; i <n_meridians; i++) { 

						double x0=calX(northernHemisphere[i][j].x);
						double y0=calY(northernHemisphere[i][j].y);						
						
						double x1=calX(northernHemisphere[(i+1)%n_meridians][j].x);
						double y1=calY(northernHemisphere[(i+1)%n_meridians][j].y);
						
						double x2=calX(northernHemisphere[(i+1)%n_meridians][j+1].x);
						double y2=calY(northernHemisphere[(i+1)%n_meridians][j+1].y);
						
						double x3=calX(northernHemisphere[i][j+1].x);
						double y3=calY(northernHemisphere[i][j+1].y);
						
						bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);
						bufGraphics.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
						bufGraphics.drawLine((int)x2,(int)y2,(int)x3,(int)y3);
						bufGraphics.drawLine((int)x3,(int)y3,(int)x0,(int)y0);
					}

				}	
				
				bufGraphics.setColor(new Color(100,100,100));

				for(int j=0;j<n_parallels/2-1;j++){

					//texture is open and periodical:

					for (int i = 0; i <n_meridians; i++) { 

						double x0=calX(southernHemisphere[i][j].x);
						double y0=calY(southernHemisphere[i][j].y);						
						
						double x1=calX(southernHemisphere[(i+1)%n_meridians][j].x);
						double y1=calY(southernHemisphere[(i+1)%n_meridians][j].y);
						
						double x2=calX(southernHemisphere[(i+1)%n_meridians][j+1].x);
						double y2=calY(southernHemisphere[(i+1)%n_meridians][j+1].y);
						
						double x3=calX(southernHemisphere[i][j+1].x);
						double y3=calY(southernHemisphere[i][j+1].y);
						
						bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);
						bufGraphics.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
						bufGraphics.drawLine((int)x2,(int)y2,(int)x3,(int)y3);
						bufGraphics.drawLine((int)x3,(int)y3,(int)x0,(int)y0);
					}

				}	
				
				bufGraphics.setColor(Color.BLUE);
				bufGraphics.setStroke(new BasicStroke(0.1f));
				
				for (int i = 0; i <n_meridians; i++) { 
					
					double x0=calX(southernHemisphere[i][n_parallels/2-1].x);
					double x1=calX(southernHemisphere[(i+1)%n_meridians][n_parallels/2-1].x);
					double y0=calY(southernHemisphere[i][n_parallels/2-1].y);
					double y1=calY(southernHemisphere[(i+1)%n_meridians][n_parallels/2-1].y);
					
					double xp=calX(southPole.x);
					double yp=calY(southPole.y);
					
					bufGraphics.drawLine((int)x0,(int)y0,(int)x1,(int)y1);
					bufGraphics.drawLine((int)x1,(int)y1,(int)xp,(int)yp);
					bufGraphics.drawLine((int)xp,(int)yp,(int)x0,(int)y0);
			    }
				
			
				ImageIO.write(buf,"gif",file);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

private ArrayList<Point3D> buildTexturePoints() {
	
	isTextureDrawing=false;

	int size=2+	(n_meridians)*(n_parallels);
	
	ArrayList<Point3D> texture_points=new ArrayList<Point3D>(size);
	
	texture_side_dy=(int) (zHeight/(n_parallels-1));
	texture_side_dx=(int) (len/(n_meridians));
	
	IMG_WIDTH=(int) len+2*texture_x0;
	IMG_HEIGHT=(int) (zHeight+radius*2)+2*texture_y0;

	int count=0;

	
	
	double xp=calX(northPole.x);
	double yp=calY(northPole.y);
	
	Point3D pn=new Point3D(xp,yp,0);	
	set(texture_points,count++,pn);

	//lateral surface


	for(int j=0;j<n_parallels/2;j++){

		//texture is open and periodical:

		for (int i = 0; i <n_meridians; i++) {

			double x=calX(northernHemisphere[i][j].x);
			double y=calY(northernHemisphere[i][j].y);

			Point3D p=new Point3D(x,y,0);

			int texIndex=count+f(i,j,n_meridians,n_parallels/2);
			//System.out.print(texIndex+"\t");
			set(texture_points,texIndex,p);
		}
		
	}	
	
	count=1+n_parallels/2*(n_meridians);
	
	for(int j=0;j<n_parallels/2;j++){

		//texture is open and periodical:

		for (int i = 0; i <n_meridians; i++) {

			double x=calX(southernHemisphere[i][j].x);
			double y=calY(southernHemisphere[i][j].y);

			Point3D p=new Point3D(x,y,0);

			int texIndex=count+f(i,j,n_meridians,n_parallels/2);
			//System.out.print(texIndex+"\t");
			set(texture_points,texIndex,p);
		}
		
	}
	
	count=1+n_parallels*(n_meridians);

		
	xp=calX(southPole.x);
	yp=calY(southPole.y);
		
	Point3D ps=new Point3D(xp,yp,0);	
	set(texture_points,count++,ps);

	
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

}
