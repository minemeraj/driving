package com.editors.weapons.data;

import java.util.ArrayList;

import com.BPoint;
import com.CustomData;
import com.LineData;
import com.Point3D;
import com.Segments;
import com.main.Renderer3D;

class Sword extends CustomData {
	
	private double forearm_length=0;
	private double forearm_width=0;
	private double forearm_height=0;
	
	private double breech_length=0;
	private double breech_width=0;
	private double breech_height=0;
	
	private double butt_length=0;
	private double butt_width=0;
	private double butt_height=0;
	
	private int barrel_meridians=0;

	Sword(double forearm_length, double forearm_width,
			double forearm_height, double breech_length, double breech_width,
			double breech_height, double butt_length, double butt_width,
			double butt_height,int barrel_meridians) {
		super();
		this.forearm_length = forearm_length;
		this.forearm_width = forearm_width;
		this.forearm_height = forearm_height;
		this.breech_length = breech_length;
		this.breech_width = breech_width;
		this.breech_height = breech_height;
		this.butt_length = butt_length;
		this.butt_width = butt_width;
		this.butt_height = butt_height;
		this.barrel_meridians = barrel_meridians;
		
		initMesh();
	}



	private void initMesh() {

		points=new ArrayList<Point3D>();
		polyData=new ArrayList<LineData>();

		n=0;
		
		//the grip		
		addZCylinder(0,0,0,butt_width,butt_height,barrel_meridians);
		
		Segments s1=new Segments(0,breech_width,0,breech_length,butt_height,breech_height);
		
		BPoint[][][] guard=new BPoint[2][2][2];

		guard[0][0][0]=addBPoint(-0.5,-0.5,0,s1);
		guard[1][0][0]=addBPoint(0.5,-0.5,0,s1);
		guard[0][1][0]=addBPoint(-0.5,0.5,0,s1);
		guard[1][1][0]=addBPoint(0.5,0.5,0,s1);		

		guard[0][0][1]=addBPoint(-0.5,-0.5,1.0,s1);
		guard[1][0][1]=addBPoint(0.5,-0.5,1.0,s1);
		guard[0][1][1]=addBPoint(-0.5,0.5,1.0,s1);
		guard[1][1][1]=addBPoint(0.5,0.5,1.0,s1);

		addLine(guard[0][0][1],guard[1][0][1],guard[1][1][1],guard[0][1][1],Renderer3D.CAR_TOP);		

		addLine(guard[0][0][0],guard[0][0][1],guard[0][1][1],guard[0][1][0],Renderer3D.CAR_LEFT);				

		addLine(guard[1][0][0],guard[1][1][0],guard[1][1][1],guard[1][0][1],Renderer3D.CAR_RIGHT);

		addLine(guard[0][1][0],guard[0][1][1],guard[1][1][1],guard[1][1][0],Renderer3D.CAR_FRONT);

		addLine(guard[0][0][0],guard[1][0][0],guard[1][0][1],guard[0][0][1],Renderer3D.CAR_BACK);

		addLine(guard[0][0][0],guard[0][1][0],guard[1][1][0],guard[1][0][0],Renderer3D.CAR_BOTTOM);
		

		Segments s2=new Segments(0,forearm_width,0,forearm_length,butt_height+breech_height,forearm_height);

		BPoint[][][] blade=new BPoint[2][2][2];

		blade[0][0][0]=addBPoint(0,-0.5,0,s2); 
		blade[1][0][0]=addBPoint(0.5,0.0,0,s2);
		blade[0][1][0]=addBPoint(-0.5,0.0,0,s2);
		blade[1][1][0]=addBPoint(0,0.5,0,s2);

		blade[0][0][1]=addBPoint(0,-0.125,1.0,s2); 
		blade[1][0][1]=addBPoint(0.125,0.0,1.0,s2);
		blade[0][1][1]=addBPoint(-0.125,0.0,1.0,s2);
		blade[1][1][1]=addBPoint(0,0.125,1.0,s2);	


		addLine(blade[0][0][1],blade[1][0][1],blade[1][1][1],blade[0][1][1],Renderer3D.CAR_TOP);		

		addLine(blade[0][0][0],blade[0][0][1],blade[0][1][1],blade[0][1][0],Renderer3D.CAR_LEFT);				

		addLine(blade[1][0][0],blade[1][1][0],blade[1][1][1],blade[1][0][1],Renderer3D.CAR_RIGHT);

		addLine(blade[0][1][0],blade[0][1][1],blade[1][1][1],blade[1][1][0],Renderer3D.CAR_FRONT);

		addLine(blade[0][0][0],blade[1][0][0],blade[1][0][1],blade[0][0][1],Renderer3D.CAR_BACK);

		addLine(blade[0][0][0],blade[0][1][0],blade[1][1][0],blade[1][0][0],Renderer3D.CAR_BOTTOM);
		

		
	
	}

}
