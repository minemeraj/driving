package com.editors.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.util.Vector;

import com.Point3D;
import com.main.Renderer3D;

public class BellTowerModel extends MeshModel{

	private double dx=100;
	private double dy=200;
	private double dz=20;
	private double roof_height=50;

	private int bx=10;
	private int by=10;
	
	public static String NAME="BellTower";
	
	public BellTowerModel(double dx, double dy, double dz,double roof_height) {
		super();
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
		this.roof_height = roof_height;

	}


	public void printMeshData(PrintWriter pw) {

		super.printMeshData(pw);

		for (int i = 0; i < faces.length; i++) {

			int[][] face=faces[i];

			int[] fts=face[0];
			int[] pts=face[1];
			int[] tts=face[2];

			String line="f=["+fts[0]+"]";

			for (int j = 0; j < pts.length; j++) {

				if(j>0)
					line+=" ";
				line+=(pts[j]+"/"+tts[j]);
			}

			print(pw,line);

		}

	}


	@Override
	public void initMesh() {

		points=new Vector<Point3D>();

		//lower and upper base
		for(int k=0;k<2;k++){

			double z=dz*k;

			addPoint(0.0,0.0,z);
			addPoint(dx,0.0,z);
			addPoint(dx,dy,z);
			addPoint(0.0,dy,z);

		}
		
		//roof top
		addPoint(dx*0.5,dy*0.5,dz+roof_height);
		


		texturePoints=new Vector();

	
		///////main plane

		//lower base
		double y=by;
		double x=bx;

		addTPoint(x,y,0);
		addTPoint(x+dx,y,0);
		addTPoint(x+dx, y+dy,0);
		addTPoint(x,y+dy,0);

		//faces
		y=by+dy;

		addTPoint(x,y,0);
		addTPoint(x+dx,y,0);
		addTPoint(x+dy+dx,y,0);
		addTPoint(x+dy+2*dx,y,0);
		addTPoint(x+2*dy+2*dx,y,0);    	


		addTPoint(x,y+dz,0);
		addTPoint(x+dx,y+dz,0);
		addTPoint(x+dy+dx,y+dz,0);
		addTPoint(x+dy+2*dx,y+dz,0);
		addTPoint(x+2*dy+2*dx,y+dz,0);
	
		//roof tops
		y=by+dy+dz;
		addTPoint(x+dx*0.5,y+roof_height,0);	
		addTPoint(x+dx+dy*0.5,y+roof_height,0);
		addTPoint(x+dx+dy+dx*0.5,y+roof_height,0);		
		addTPoint(x+dx+dy+dx+dy*0.5,y+roof_height,0);	

		IMG_WIDTH=(int) (2*bx+2*dy+2*dx);
		IMG_HEIGHT=(int) (2*by+dy+dz+roof_height);
	}





	public void printTexture(Graphics2D bg) {


		//draw lines for reference

		bg.setColor(Color.BLACK);
		bg.setStroke(new BasicStroke(0.1f));

		//lower base
		printTextureLine(bg,0,1);
		printTextureLine(bg,1,2);
		printTextureLine(bg,2,3);
		printTextureLine(bg,3,0);

		//lateral faces
		bg.setColor(Color.BLACK);
		printTextureLine(bg,4,5);
		printTextureLine(bg,5,10);
		printTextureLine(bg,10,9);
		printTextureLine(bg,9,4);

		printTextureLine(bg,5,6);
		printTextureLine(bg,6,11);
		printTextureLine(bg,11,10);

		printTextureLine(bg,6,7);
		printTextureLine(bg,7,12);
		printTextureLine(bg,12,11);

		printTextureLine(bg,7,8);
		printTextureLine(bg,8,13);
		printTextureLine(bg,13,12);

        //gables
		bg.setColor(Color.BLACK);
		printTextureLine(bg,9,10);
		printTextureLine(bg,10,14);
		printTextureLine(bg,14,9);

		printTextureLine(bg,10,11);
		printTextureLine(bg,11,15);
		printTextureLine(bg,15,10);
		
		printTextureLine(bg,11,12);
		printTextureLine(bg,12,16);
		printTextureLine(bg,16,11);
		
		printTextureLine(bg,12,13);
		printTextureLine(bg,13,17);
		printTextureLine(bg,17,12);

	}

	private int[][][] faces={

			//base

			{{Renderer3D.CAR_BACK},{0,1,5,4},{4,5,10,9}},
			{{Renderer3D.CAR_RIGHT},{1,2,6,5},{5,6,11,10}},
			{{Renderer3D.CAR_FRONT},{2,3,7,6},{6,7,12,11}},
			{{Renderer3D.CAR_LEFT},{3,0,4,7},{7,8,13,12}},
			{{Renderer3D.CAR_BOTTOM},{0,3,2,1},{0,1,2,3}},
			

			//gables
			{{Renderer3D.CAR_BACK},{4,5,8},{9,10,14}},
			{{Renderer3D.CAR_RIGHT},{5,6,8},{10,11,15}},
			{{Renderer3D.CAR_FRONT},{6,7,8},{11,12,16}},
			{{Renderer3D.CAR_LEFT},{7,4,8},{12,13,17}},
	
	};
	

	String gables_top="14-15-16,17";
	String llf1="09-10-11-12-13";
	String llf0="04-05-06-07-08";
	String llb1="03-02";
	String llb0="00-01";
	
	String points_level2="8";
	String points_level1="4,5,6,7";
	String points_level0="0,1,2,3";
	

}
