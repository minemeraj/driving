package com.editors.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.util.Vector;

import com.BPoint;
import com.Point3D;
import com.editors.cars.data.Car;
import com.main.Renderer3D;

public class Man2Model extends MeshModel{

	double dx = 0;
	double dy = 0;
	double dz = 0;

	private int[][][] faces; 



	int bx=10;
	int by=10;

	public Man2Model(double dx, double dy, double dz) {
		super();
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}

	@Override
	public void initMesh() {

		points=new Vector();
		texturePoints=new Vector();



		BPoint p0= addBPoint(0,0,0);
		BPoint p1= addBPoint(100,0,0);
		BPoint p2= addBPoint(100,0,100);
		BPoint p3= addBPoint(0,0,100);
		
		BPoint p4= addBPoint(100,100,100);
		BPoint p5= addBPoint(0,100,100);

		double deltax=100;
		double deltay=100;
		
		int xNumSections=3;  
		int zNumSections=3;
		
		int  NUMFACES=(xNumSections-1)*(zNumSections-1);

		for (int k = 0; k < zNumSections; k++) { 


			double y=by+deltay*k;

			for (int i = 0; i < xNumSections; i++) {

				double x=bx+deltax*i;


				addTPoint(x, y, 0);

			}

		}


		int[][][] tFaces = new int[NUMFACES][3][4];

		int counter=0;

		buildFace(tFaces,counter++,p0,p1,p2,p3,xNumSections,zNumSections);
		buildFace(tFaces,counter++,p2,p4,p5,p3,xNumSections,zNumSections);

		faces=new int[counter][3][];

		for (int i = 0; i < counter; i++) {

			faces[i] = (int[][]) tFaces[i];

		}


		IMG_WIDTH=(int) (2*bx+deltax*(xNumSections-1));
		IMG_HEIGHT=(int) (2*by+deltay*((zNumSections-1)));
	}




	private void buildFace(int[][][] faces, 
			int counter, 
			BPoint p0, BPoint p1, BPoint p2, BPoint p3, 
			int xNumSections, 
			int zNumSections
			) {
		
		int sz=3;
		if(p3!=null)
			sz=4;


		faces[counter][0][MeshModel.FACE_TYPE_ORIENTATION]=Renderer3D.CAR_BACK;

		int[] pts = new int[sz];
		faces[counter][MeshModel.FACE_TYPE_BODY_INDEXES]=pts;

		pts[0]=p0.getIndex();
		pts[1]=p1.getIndex();
		pts[2]=p2.getIndex();
		if(p3!=null)
			pts[3]=p3.getIndex();
		
		int nx=(xNumSections-1);
		int m=counter/nx;
		int n=counter-m*nx;
		
		int p=(nx+1)*m+n;

		int[] tts = new int[sz];
		faces[counter][MeshModel.FACE_TYPE_TEXTURE_INDEXES]=tts;

		tts[0]=p;
		tts[1]=p+1;
		
		if(p3!=null){
			
			tts[2]=p+1+(nx+1);
			tts[3]=p+(nx+1);
			
		}else{
			
			tts[2]=p+(nx+1);
		}
	}

	@Override
	public void printTexture(Graphics2D bg) {
		//draw lines for reference

		bg.setColor(Color.RED);
		bg.setStroke(new BasicStroke(0.1f));
		printTextureFaces(bg,faces);

	}



	public void printMeshData(PrintWriter pw) {

		super.printMeshData(pw);
		super.printFaces(pw, faces);

	}




}
