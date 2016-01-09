package com.editors.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class HeadModel extends MeshModel {
	
	private int[][][] splacFaces; 
	private int[][][] neuroFaces;
	
	double dx = 0;
	double dy = 0;
	double dz = 0;
	
	int bx=10;
	int by=10;

	

	public HeadModel(double dx, double dy, double dz) {
	
		super();
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}

	@Override
	public void initMesh() {
		
		points=new Vector();
		texturePoints=new Vector();
		
		int xNumSections=splancnoSections[0].length;

		int zNumSections=skullSections.length;
		
		int tetaNumSections=10;
		
		double deltax=dx/(xNumSections-1);
		double deltaz=dz/(zNumSections-1);
		
	
		int nSplacnoPoints=0;
		
		//splanchnocranium
		for (int k = 0; k < zNumSections; k++) { 
			
			double[] d=skullSections[k];
			double[] s=splancnoSections[k];
			
			double z=dz*d[0];  
			
			for (int i = 0; i < xNumSections; i++) {
				
				//center points in x=0 with -dx/2;
				double x=deltax*i-dx*0.5;
				//double y=-s[i]*dy*d[2];
				
				double y=-dy*faceData[k][i];
				
				addPoint(x, y, z);
				
				nSplacnoPoints++;
			}
			
		}
		
		//neurocranium
		
		double dTeta=Math.PI/(tetaNumSections-1);
		for (int k = 0; k < zNumSections; k++) { 
			
			
			double rx=dx*0.5;
			double ry=dy;
			
			double[] d=skullSections[k];
			
			double z=dz*d[0]; 
			ry=ry*d[1];
			
			for (int i = 0; i < tetaNumSections; i++) {
				
				double teta=dTeta*i;
				
				
				double x=rx*Math.cos(teta);
				double y=ry*Math.sin(teta);
				
				addPoint(x, y, z);
				
			}
			
		}
		
		//splanchnocranium
		
		int nSplacnoTPoints=0;
		
		for (int k = 0; k < zNumSections; k++) { 
			
			double[] d=skullSections[k];
			
			double z=by+dz*d[0]; 
			
			for (int i = 0; i < xNumSections; i++) {
				
				double x=bx+deltax*i;
			
				
				addTPoint(x, z, 0);
				
				nSplacnoTPoints++;
			}
			
		}
		
		//neurocranium
		double deltaTeta=(dy*Math.PI)/(tetaNumSections-1);
		
		for (int k = 0; k < zNumSections; k++) { 
			
			double[] d=skullSections[k];
			
			double z=by+dz*d[0]; 
			
			for (int i = 0; i < tetaNumSections; i++) {
				
				double x=bx+dx+deltaTeta*i;
				
				addTPoint(x, z, 0);
				
			}
			
		}
		
		splacFaces=buildSinglePlaneFaces(xNumSections, zNumSections,
				0, 0);
		neuroFaces=buildSinglePlaneFaces(tetaNumSections, zNumSections, 
				nSplacnoPoints, nSplacnoTPoints);
		
		IMG_WIDTH=(int) (2*bx+dx+dy*Math.PI);
		IMG_HEIGHT=(int) (2*by+dz);

	}


	@Override
	public void printTexture(Graphics2D bg) {
	
		bg.setColor(Color.BLACK);
		bg.setStroke(new BasicStroke(0.1f));
		printTextureFaces(bg,splacFaces);

		bg.setColor(Color.BLUE);
		bg.setStroke(new BasicStroke(0.1f));
		printTextureFaces(bg,neuroFaces);
	}
	
	@Override
	public void printMeshData(PrintWriter pw) {
		super.printMeshData(pw);
		super.printFaces(pw, splacFaces);
		super.printFaces(pw, neuroFaces);
	}
	
	double[][] skullSections={
			{0,0.8837,0.2093},
			{0.1148,0.814,0.2326},
			{0.1803,0.814,0.2326},
			{0.2787,0.8604,0.2791},
			{0.3279,0.8837,0.3721},
			{0.4754,0.9651,0.2791},
			{0.6066,0.9767,0.2558},
			{0.6885,1.0,0.2093},
			{0.8525,0.8637,0.0698},
			{0.9344,0.7558,-0.0349},
			{1.0,0.3953,-0.4070}
			};

	
	double[][] splancnoSections={
			
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},
			{0.0,0.6614,0.8660,0.9682,1.0,0.9682,0.8660,0.6614,0.0},

			
	};
	
	double[][] faceData={
			{0,0.1384,0.1813,0.2026,0.2093,0.2026,0.1813,0.1384,0},
			{0,0.1538,0.2014,0.2252,0.2326,0.2252,0.2014,0.1538,0},
			{0,0.1538,0.2014,0.2252,0.2326,0.2252,0.2014,0.1538,0},
			{0,0.1846,0.2417,0.2702,0.2791,0.2702,0.2417,0.1846,0}, 
			{0,0.1692,0.2215,0.2477,0.3721,0.2477,0.2215,0.1692,0},//4 noze tip
			{0,0.1846,0.1417,0.2002,0.2791,0.2002,0.1417,0.1846,0},//5 orbits centers
			{0,0.1692,0.2215,0.2477,0.2558,0.2477,0.2215,0.1692,0},
			{0,0.1384,0.1813,0.2026,0.2093,0.2026,0.1813,0.1384,0},
			{0,0.0462,0.0604,0.0676,0.0698,0.0676,0.0604,0.0462,0},
			{-0,-0.0231,-0.0302,-0.0338,-0.0349,-0.0338,-0.0302,-0.0231,-0},
			{-0,-0.2692,-0.3525,-0.3941,-0.407,-0.3941,-0.3525,-0.2692,-0}
		};

	
	public static void main(String[] args) {
		//new HeadModel(0, 0, 0).generateBaseFaceData();
	}

	private void generateBaseFaceData() {
		
	    DecimalFormat sdf=new DecimalFormat("0.####");
	    DecimalFormatSymbols symbols=sdf.getDecimalFormatSymbols();
	    symbols.setDecimalSeparator('.');
	    symbols.setGroupingSeparator(',');
	    sdf.setDecimalFormatSymbols(symbols);
		
	    System.out.print("{");
	    
		for (int k = 0; k < skullSections.length; k++) {
			

			
			System.out.print("{");
			
			for (int i = 0; i < splancnoSections[k].length; i++) {
				
				if(i>0)
					System.out.print(",");
				
				double d=skullSections[k][2]*splancnoSections[k][i];
				//double d=0.2558*splancnoSections[k][i];
				
				System.out.print(sdf.format(d));
			}
			
			System.out.println("},");

			
		}
		System.out.println("}");
	}
	
	
}