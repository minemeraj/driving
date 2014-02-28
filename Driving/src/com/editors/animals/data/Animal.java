package com.editors.animals.data;

import java.util.Vector;

import com.BPoint;
import com.CustomData;
import com.LineData;
import com.PolygonMesh;
import com.main.Renderer3D;

public class Animal extends CustomData{







	double x_side=0;
	double y_side=0;
	double z_side=0;	
	
	double head_DZ=0;
	double head_DX=0;
	double head_DY=0;
	
	double neck_length=0;
	double neck_side=0;
	
	double humerus_length=0;
	double radius_length=0;
	
	double leg_side=0;
	
	double femur_length=0; 
	double shinbone_length=0;
	
	double foot_length=0;
	
	public static int ANIMAL_TYPE_QUADRUPED=0;
	public static int ANIMAL_TYPE_HUMAN=1;
	
	public int animal_type=ANIMAL_TYPE_HUMAN;


	public Animal(){}

	public Animal(double x_side, double y_side,double z_side,int animal_type,
			double femur_length,double shinbone_length,double leg_side,
			double head_DZ,double head_DX,double head_DY,double neck_length,double neck_side,
			double humerus_length,double radius_length,double foot_length
			) {
		super();

		this.x_side = x_side;
		this.y_side = y_side;
		this.z_side = z_side;
		this.animal_type = animal_type;

		this.head_DZ = head_DZ;
		this.head_DX = head_DX;
		this.head_DY = head_DY;
		this.neck_length = neck_length; 
		this.neck_side = neck_side;
		
		this.humerus_length = humerus_length;
		this.radius_length = radius_length;
		this.foot_length = foot_length;
		
		this.femur_length = femur_length;
		this.shinbone_length = shinbone_length;
		this.leg_side = leg_side;
	}

	public Object clone(){

		Animal animal=new Animal(
				x_side,y_side,z_side,animal_type,femur_length,shinbone_length,leg_side,
				head_DZ,head_DX,head_DY,neck_length,neck_side,humerus_length,radius_length,
				foot_length
				);
		return animal;

	}

	public int getAnimal_type() {
		return animal_type;
	}

	public void setAnimal_type(int animal_type) {
		this.animal_type = animal_type;
	}


	public double getX_side() {
		return x_side;
	}
	public void setX_side(double x_side) {
		this.x_side = x_side;
	}
	public double getY_side() {
		return y_side;
	}
	public void setY_side(double y_side) {
		this.y_side = y_side;
	}


	public String toString() {

		String ret= "A="+x_side+","+y_side+","+z_side+","+animal_type;
		ret+=","+femur_length+","+shinbone_length+","+leg_side+
				","+head_DZ+","+head_DX+","+head_DY+","+neck_length+","+neck_side+","
				+humerus_length+","+radius_length+","+foot_length;
				
		return ret;		
	}

	public static Animal buildAnimal(String str) {

		String[] vals = str.split(",");


		double x_side =Double.parseDouble(vals[0]);
		double y_side = Double.parseDouble(vals[1]);
		double z_side = Double.parseDouble(vals[2]); 
		int animal_type =Integer.parseInt(vals[3]);		
		
		double femurLength=Double.parseDouble(vals[4]);		
		double shinboneLength=Double.parseDouble(vals[5]);			
		double legSide = Double.parseDouble(vals[6]);	
		
		double headDZ = Double.parseDouble(vals[7]); 
		double headDX = Double.parseDouble(vals[8]); 
		double headDY = Double.parseDouble(vals[9]);
		double neckLength = Double.parseDouble(vals[10]);
		double neckSide = Double.parseDouble(vals[11]);
		
		double humerusLength =Double.parseDouble(vals[12]);
		double radiusLength = Double.parseDouble(vals[13]);
		
		double footLength=Double.parseDouble(vals[14]);

		Animal grid=new Animal(x_side,y_side,z_side,animal_type,
				femurLength,shinboneLength,legSide,
				headDZ,headDX,headDY,neckLength,neckSide,
				humerusLength,radiusLength,footLength);

		return grid;
	}



	public double getZ_side() {
		return z_side;
	}
	
	public void setZ_side(double z_side) {
		this.z_side = z_side;
	}

	public double getLeg_side() {
		return leg_side;
	}

	public void setLeg_side(double leg_side) {
		this.leg_side = leg_side;
	}

	
	public double getFemur_length() {
		return femur_length;
	}

	public void setFemur_length(double leg_length) {
		this.femur_length = leg_length;
	}
	

	public double getHead_DY() {
		return head_DY;
	}

	public void setHead_DY(double head_DY) {
		this.head_DY = head_DY;
	}

	public double getHead_DZ() {
		return head_DZ;
	}

	public void setHead_DZ(double head_DZ) {
		this.head_DZ = head_DZ;
	}

	public double getHead_DX() {
		return head_DX;
	}

	public void setHead_DX(double head_DX) {
		this.head_DX = head_DX;
	}

	public double getNeck_length() {
		return neck_length;
	}

	public void setNeck_length(double neck_length) {
		this.neck_length = neck_length;
	}

	public double getHumerus_length() {
		return humerus_length;
	}

	public void setHumerus_length(double arm_length) {
		this.humerus_length = arm_length;
	}

	public double getNeck_side() {
		return neck_side;
	}

	public void setNeck_side(double neck_side) {
		this.neck_side = neck_side;
	}

	public double getRadius_length() {
		return radius_length;
	}

	public void setRadius_length(double radius_length) {
		this.radius_length = radius_length;
	}

	public double getShinbone_length() {
		return shinbone_length;
	}

	public void setShinbone_length(double shinbone_length) {
		this.shinbone_length = shinbone_length;
	}
	
	public double getFoot_length() {
		return foot_length;
	}

	public void setFoot_length(double foot_length) {
		this.foot_length = foot_length;
	}

	public PolygonMesh buildMesh(){
		
		
		if(ANIMAL_TYPE_HUMAN==animal_type){	
			
			return buildHumanMesh();
			
			
			
		}
		else if(ANIMAL_TYPE_QUADRUPED==animal_type){			
			
			return buildQuadrupedMesh();
			
		}
		else
			return null;

		


	}

	private PolygonMesh buildHumanMesh() {
		
		double q_angle=12*2*Math.PI/360.0;

		Vector points=new Vector();
		points.setSize(200);

		Vector polyData=new Vector();

		int n=0;


		//body:
		
		double bz0=femur_length+shinbone_length;
		double bz1=femur_length+shinbone_length+z_side/4.0;
		double bz2=femur_length+shinbone_length+z_side/2.0;
		double bz3=femur_length+shinbone_length+z_side*3.0/4.0;
		double bz4=femur_length+shinbone_length+z_side;
		
		double SHOULDER_DX=10;		
		double WAIST_DX=x_side*0.1;
		
		int numx=2;
		int numy=2;
		int numz=5;
		
		BPoint[][][] body=new BPoint[numx][numy][numz]; 

		body[0][0][0]=addBPoint(0,0,bz0,n++,points);
		body[1][0][0]=addBPoint(x_side,0,bz0,n++,points);
		body[0][1][0]=addBPoint(0,y_side,bz0,n++,points);
		body[1][1][0]=addBPoint(x_side,y_side,bz0,n++,points);

		body[0][1][1]=addBPoint(0,y_side,bz1,n++,points);
		body[1][0][1]=addBPoint(x_side,0,bz1,n++,points);
		body[1][1][1]=addBPoint(x_side,y_side,bz1,n++,points);
		body[0][0][1]=addBPoint(0,0,bz1,n++,points);
		
		body[0][1][2]=addBPoint(WAIST_DX,y_side,bz2,n++,points);
		body[1][0][2]=addBPoint(x_side-WAIST_DX,0,bz2,n++,points);
		body[1][1][2]=addBPoint(x_side-WAIST_DX,y_side,bz2,n++,points);
		body[0][0][2]=addBPoint(WAIST_DX,0,bz2,n++,points);
		
		body[0][1][3]=addBPoint(-SHOULDER_DX,y_side,bz3,n++,points);
		body[1][0][3]=addBPoint(x_side+SHOULDER_DX,0,bz3,n++,points);
		body[1][1][3]=addBPoint(x_side+SHOULDER_DX,y_side,bz3,n++,points);
		body[0][0][3]=addBPoint(-SHOULDER_DX,0,bz3,n++,points);
		
		body[0][1][4]=addBPoint(-SHOULDER_DX,y_side,bz4,n++,points);
		body[1][0][4]=addBPoint(x_side+SHOULDER_DX,0,bz4,n++,points);
		body[1][1][4]=addBPoint(x_side+SHOULDER_DX,y_side,bz4,n++,points);
		body[0][0][4]=addBPoint(-SHOULDER_DX,0,bz4,n++,points);
		


		//addLine(polyData,body[0][0][1],body[1][0][1],body[1][1][1],body[0][1][1],Renderer3D.CAR_TOP);
		
		addLine(polyData,body[0][0][0],body[0][1][0],body[1][1][0],body[1][0][0],Renderer3D.CAR_BOTTOM);
		
		addLine(polyData,body[0][0][0],body[0][0][1],body[0][1][1],body[0][1][0],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][0][0],body[1][1][0],body[1][1][1],body[1][0][1],Renderer3D.CAR_RIGHT);

		addLine(polyData,body[0][0][0],body[1][0][0],body[1][0][1],body[0][0][1],Renderer3D.CAR_BACK);

		addLine(polyData,body[0][1][0],body[0][1][1],body[1][1][1],body[1][1][0],Renderer3D.CAR_FRONT);
		
	
		
		//addLine(polyData,body[0][0][2],body[1][0][2],body[1][1][2],body[0][1][2],Renderer3D.CAR_TOP);

		//addLine(polyData,body[0][0][0],body[0][1][0],body[1][1][0],body[1][0][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,body[0][0][1],body[0][0][2],body[0][1][2],body[0][1][1],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][0][1],body[1][1][1],body[1][1][2],body[1][0][2],Renderer3D.CAR_RIGHT);

		addLine(polyData,body[0][0][1],body[1][0][1],body[1][0][2],body[0][0][2],Renderer3D.CAR_BACK);

		addLine(polyData,body[0][1][1],body[0][1][2],body[1][1][2],body[1][1][1],Renderer3D.CAR_FRONT);
		
		
		//addLine(polyData,body[0][0][2],body[1][0][2],body[1][1][2],body[0][1][2],Renderer3D.CAR_TOP);

		//addLine(polyData,body[0][0][0],body[0][1][0],body[1][1][0],body[1][0][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,body[0][0][2],body[0][0][3],body[0][1][3],body[0][1][2],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][0][2],body[1][1][2],body[1][1][3],body[1][0][3],Renderer3D.CAR_RIGHT);

		addLine(polyData,body[0][0][2],body[1][0][2],body[1][0][3],body[0][0][3],Renderer3D.CAR_BACK);

		addLine(polyData,body[0][1][2],body[0][1][3],body[1][1][3],body[1][1][2],Renderer3D.CAR_FRONT);
		
		
		
		//addLine(polyData,body[0][0][4],body[1][0][4],body[1][1][4],body[0][1][4],Renderer3D.CAR_TOP);

		//addLine(polyData,body[0][0][0],body[0][1][0],body[1][1][0],body[1][0][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,body[0][0][3],body[0][0][4],body[0][1][4],body[0][1][3],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][0][3],body[1][1][3],body[1][1][4],body[1][0][4],Renderer3D.CAR_RIGHT);

		addLine(polyData,body[0][0][3],body[1][0][3],body[1][0][4],body[0][0][4],Renderer3D.CAR_BACK);

		addLine(polyData,body[0][1][3],body[0][1][4],body[1][1][4],body[1][1][3],Renderer3D.CAR_FRONT);


		///////////
		//head:
		
		
		double hx=(x_side-head_DX)/2.0;
		double hy=(y_side-head_DY)/2.0;
		double hz0=femur_length+shinbone_length+z_side+neck_length;
		double hz1=femur_length+shinbone_length+z_side+neck_length+head_DZ;
		
		BPoint[][][] head=new BPoint[2][2][2];

		head[0][0][0]=addBPoint(hx,hy,hz0,n++,points);
		head[1][0][0]=addBPoint(hx+head_DX,hy,hz0,n++,points);
		head[0][1][0]=addBPoint(hx,hy+head_DY,hz0,n++,points);
		head[1][1][0]=addBPoint(hx+head_DX,hy+head_DY,hz0,n++,points);

		head[0][1][1]=addBPoint(hx,hy+head_DY,hz1,n++,points);
		head[1][0][1]=addBPoint(hx+head_DX,hy,hz1,n++,points);
		head[1][1][1]=addBPoint(hx+head_DX,hy+head_DY,hz1,n++,points);
		head[0][0][1]=addBPoint(hx,hy,hz1,n++,points);


		addLine(polyData,head[0][0][1],head[1][0][1],head[1][1][1],head[0][1][1],Renderer3D.CAR_TOP);

		addLine(polyData,head[0][0][0],head[0][1][0],head[1][1][0],head[1][0][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,head[0][0][0],head[0][0][1],head[0][1][1],head[0][1][0],Renderer3D.CAR_LEFT);

		addLine(polyData,head[1][0][0],head[1][1][0],head[1][1][1],head[1][0][1],Renderer3D.CAR_RIGHT);

		addLine(polyData,head[0][0][0],head[1][0][0],head[1][0][1],head[0][0][1],Renderer3D.CAR_BACK);

		addLine(polyData,head[0][1][0],head[0][1][1],head[1][1][1],head[1][1][0],Renderer3D.CAR_FRONT);

		
		//neck:

		double nx1=(x_side-neck_side)/2.0;
		double nz0=femur_length+shinbone_length+z_side+neck_length/2.0;
		double nz1=femur_length+shinbone_length+z_side+neck_length;
		
		BPoint[][][] neck=new BPoint[2][2][2];

		neck[0][1][0]=addBPoint(nx1,y_side,nz0,n++,points);
		neck[1][0][0]=addBPoint(nx1+neck_side,0,nz0,n++,points);
		neck[1][1][0]=addBPoint(nx1+neck_side,y_side,nz0,n++,points);
		neck[0][0][0]=addBPoint(nx1,0,nz0,n++,points);
		

		neck[0][1][1]=addBPoint(nx1,y_side,nz1,n++,points);
		neck[1][0][1]=addBPoint(nx1+neck_side,0,nz1,n++,points);
		neck[1][1][1]=addBPoint(nx1+neck_side,y_side,nz1,n++,points);
		neck[0][0][1]=addBPoint(nx1,0,nz1,n++,points);


		//LineData topneckLD=buildLine(neck[0][0][1],neck[1][0][1],neck[1][1][1],neck[0][1][1],Renderer3D.CAR_TOP);
		//polyData.add(topneckLD);

	
		addLine(polyData,body[0][0][4],neck[0][0][0],neck[0][1][0],body[0][1][4],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][0][4],body[1][1][4],neck[1][1][0],neck[1][0][0],Renderer3D.CAR_RIGHT);

		addLine(polyData,body[0][0][4],body[1][0][4],neck[1][0][0],neck[0][0][0],Renderer3D.CAR_BACK);

		addLine(polyData,body[0][1][4],neck[0][1][0],neck[1][1][0],body[1][1][4],Renderer3D.CAR_FRONT);
		
		
		//addLine(polyData,neck001,neck101,neck111,neck011,Renderer3D.CAR_TOP);

		//LineData bottomneck1LD=buildLine(neck001,neck011,neck111,neck101,Renderer3D.CAR_BOTTOM);
		//polyData.add(bottomneck1LD);

		addLine(polyData,neck[0][0][0],neck[0][0][1],neck[0][1][1],neck[0][1][0],Renderer3D.CAR_LEFT);

		addLine(polyData,neck[1][0][0],neck[1][1][0],neck[1][1][1],neck[1][0][1],Renderer3D.CAR_RIGHT);

		addLine(polyData,neck[0][0][0],neck[1][0][0],neck[1][0][1],neck[0][0][1],Renderer3D.CAR_BACK);

		addLine(polyData,neck[0][1][0],neck[0][1][1],neck[1][1][1],neck[1][1][0],Renderer3D.CAR_FRONT);

		//legs:	
		
		double thigh_indentation=femur_length*Math.sin(q_angle);
		double thigh_side=leg_side+thigh_indentation;
		double LEG_DY=(y_side-leg_side)/2.0;

		//LeftLeg

		BPoint pBackLeftLeg000=addBPoint(thigh_indentation,LEG_DY,0,n++,points);
		BPoint pBackLeftLeg100=addBPoint(thigh_indentation+leg_side,LEG_DY,0,n++,points);
		BPoint pBackLeftLeg110=addBPoint(thigh_indentation+leg_side,leg_side+LEG_DY,0,n++,points);
		BPoint pBackLeftLeg010=addBPoint(thigh_indentation,leg_side+LEG_DY,0,n++,points);

		LineData backLeftLeg=buildLine(pBackLeftLeg000,pBackLeftLeg010,pBackLeftLeg110,pBackLeftLeg100,Renderer3D.CAR_FRONT);
		polyData.add(backLeftLeg);


		BPoint pBackLeftLeg001=addBPoint(thigh_indentation,LEG_DY,shinbone_length,n++,points);
		BPoint pBackLeftLeg101=addBPoint(thigh_indentation+leg_side,LEG_DY,shinbone_length,n++,points);
		BPoint pBackLeftLeg111=addBPoint(thigh_indentation+leg_side,LEG_DY+leg_side,shinbone_length,n++,points);
		BPoint pBackLeftLeg011=addBPoint(thigh_indentation,LEG_DY+leg_side,shinbone_length,n++,points);


		addLine(polyData,pBackLeftLeg000,pBackLeftLeg001,pBackLeftLeg011,pBackLeftLeg010,Renderer3D.CAR_LEFT);
;
		addLine(polyData,pBackLeftLeg010,pBackLeftLeg011,pBackLeftLeg111,pBackLeftLeg110,Renderer3D.CAR_FRONT);

		addLine(polyData,pBackLeftLeg110,pBackLeftLeg111,pBackLeftLeg101,pBackLeftLeg100,Renderer3D.CAR_RIGHT);

		addLine(polyData,pBackLeftLeg100,pBackLeftLeg101,pBackLeftLeg001,pBackLeftLeg000,Renderer3D.CAR_BACK);
		
		//left thigh

		BPoint pBackLeftThigh001=addBPoint(0,LEG_DY,femur_length+shinbone_length,n++,points);
		BPoint pBackLeftThigh101=addBPoint(thigh_side,LEG_DY,femur_length+shinbone_length,n++,points);
		BPoint pBackLeftThigh111=addBPoint(thigh_side,LEG_DY+leg_side,femur_length+shinbone_length,n++,points);
		BPoint pBackLeftThigh011=addBPoint(0,LEG_DY+leg_side,femur_length+shinbone_length,n++,points);

		addLine(polyData,pBackLeftLeg001,pBackLeftThigh001,pBackLeftThigh011,pBackLeftLeg011,Renderer3D.CAR_LEFT);

		addLine(polyData,pBackLeftLeg011,pBackLeftThigh011,pBackLeftThigh111,pBackLeftLeg111,Renderer3D.CAR_FRONT);

		addLine(polyData,pBackLeftLeg111,pBackLeftThigh111,pBackLeftThigh101,pBackLeftLeg101,Renderer3D.CAR_RIGHT);

		addLine(polyData,pBackLeftLeg101,pBackLeftThigh101,pBackLeftThigh001,pBackLeftLeg001,Renderer3D.CAR_BACK);


		///RightLeg

		BPoint pBackRightLeg000=addBPoint(x_side-leg_side-thigh_indentation,LEG_DY,0,n++,points);
		BPoint pBackRightLeg100=addBPoint(x_side-thigh_indentation,LEG_DY,0,n++,points);
		BPoint pBackRightLeg110=addBPoint(x_side-thigh_indentation,LEG_DY+leg_side,0,n++,points);
		BPoint pBackRightLeg010=addBPoint(x_side-leg_side-thigh_indentation,LEG_DY+leg_side,0,n++,points);

		addLine(polyData,pBackRightLeg000,pBackRightLeg010,pBackRightLeg110,pBackRightLeg100,Renderer3D.CAR_FRONT);

		BPoint pBackRightLeg001=addBPoint(x_side-leg_side-thigh_indentation,LEG_DY,shinbone_length,n++,points);
		BPoint pBackRightLeg101=addBPoint(x_side-thigh_indentation,LEG_DY,shinbone_length,n++,points);
		BPoint pBackRightLeg111=addBPoint(x_side-thigh_indentation,LEG_DY+leg_side,shinbone_length,n++,points);
		BPoint pBackRightLeg011=addBPoint(x_side-leg_side-thigh_indentation,LEG_DY+leg_side,shinbone_length,n++,points);

		addLine(polyData,pBackRightLeg000,pBackRightLeg001,pBackRightLeg011,pBackRightLeg010,Renderer3D.CAR_LEFT);
	
		addLine(polyData,pBackRightLeg010,pBackRightLeg011,pBackRightLeg111,pBackRightLeg110,Renderer3D.CAR_FRONT);
	
		addLine(polyData,pBackRightLeg110,pBackRightLeg111,pBackRightLeg101,pBackRightLeg100,Renderer3D.CAR_RIGHT);
		
		addLine(polyData,pBackRightLeg100,pBackRightLeg101,pBackRightLeg001,pBackRightLeg000,Renderer3D.CAR_BACK);
	
		
		//right thigh
		
		BPoint pBackRightThigh001=addBPoint(x_side-thigh_side,LEG_DY,femur_length+shinbone_length,n++,points);
		BPoint pBackRightThigh101=addBPoint(x_side,LEG_DY,femur_length+shinbone_length,n++,points);
		BPoint pBackRightThigh111=addBPoint(x_side,LEG_DY+leg_side,femur_length+shinbone_length,n++,points);
		BPoint pBackRightThigh011=addBPoint(x_side-thigh_side,LEG_DY+leg_side,femur_length+shinbone_length,n++,points);

		addLine(polyData,pBackRightLeg001,pBackRightThigh001,pBackRightThigh011,pBackRightLeg011,Renderer3D.CAR_LEFT);
	
		addLine(polyData,pBackRightLeg011,pBackRightThigh011,pBackRightThigh111,pBackRightLeg111,Renderer3D.CAR_FRONT);
	
		addLine(polyData,pBackRightLeg111,pBackRightThigh111,pBackRightThigh101,pBackRightLeg101,Renderer3D.CAR_RIGHT);
	
		addLine(polyData,pBackRightLeg101,pBackRightThigh101,pBackRightThigh001,pBackRightLeg001,Renderer3D.CAR_BACK);
	
		
		//Arms:
		
		//Left fore arm
		
		double ax=SHOULDER_DX+leg_side;
		double az=femur_length+shinbone_length+z_side-humerus_length-radius_length;
		double ay=(y_side-leg_side)/2.0;
		
		BPoint pFrontLeftForearm000=addBPoint(-ax,ay,az,n++,points);
		BPoint pFrontLeftForearm100=addBPoint(-ax+leg_side,ay,az,n++,points);
		BPoint pFrontLeftForearm110=addBPoint(-ax+leg_side,ay+leg_side,az,n++,points);
		BPoint pFrontLeftForearm010=addBPoint(-ax,ay+leg_side,az,n++,points);

		
		addLine(polyData,pFrontLeftForearm000,pFrontLeftForearm010,pFrontLeftForearm110,pFrontLeftForearm100,Renderer3D.CAR_BOTTOM);
;
		
		BPoint pFrontLeftForearm001=addBPoint(-ax,ay,az+radius_length,n++,points);
		BPoint pFrontLeftForearm101=addBPoint(-ax+leg_side,ay,az+radius_length,n++,points);
		BPoint pFrontLeftForearm111=addBPoint(-ax+leg_side,ay+leg_side,az+radius_length,n++,points);
		BPoint pFrontLeftForearm011=addBPoint(-ax,ay+leg_side,az+radius_length,n++,points);
		
		//LineData topLeftForearm=addLine(polyData,pFrontLeftForearm001,pFrontLeftForearm101,pFrontLeftForearm111,pFrontLeftForearm011,Renderer3D.CAR_TOP);
		//polyData.add(topLeftForearm);
		
		addLine(polyData,pFrontLeftForearm000,pFrontLeftForearm001,pFrontLeftForearm011,pFrontLeftForearm010,Renderer3D.CAR_LEFT);

		addLine(polyData,pFrontLeftForearm010,pFrontLeftForearm011,pFrontLeftForearm111,pFrontLeftForearm110,Renderer3D.CAR_FRONT);

		addLine(polyData,pFrontLeftForearm110,pFrontLeftForearm111,pFrontLeftForearm101,pFrontLeftForearm100,Renderer3D.CAR_RIGHT);

		addLine(polyData,pFrontLeftForearm100,pFrontLeftForearm101,pFrontLeftForearm001,pFrontLeftForearm000,Renderer3D.CAR_BACK);
		
		
		//left arm
		
		BPoint pFrontLeftArm001=addBPoint(-ax,ay,az+humerus_length+radius_length,n++,points);
		BPoint pFrontLeftArm101=addBPoint(-ax+leg_side,ay,az+humerus_length+radius_length,n++,points);
		BPoint pFrontLeftArm111=addBPoint(-ax+leg_side,ay+leg_side,az+humerus_length+radius_length,n++,points);
		BPoint pFrontLeftArm011=addBPoint(-ax,ay+leg_side,az+humerus_length+radius_length,n++,points);
		
		addLine(polyData,pFrontLeftArm001,pFrontLeftArm101,pFrontLeftArm111,pFrontLeftArm011,Renderer3D.CAR_TOP);
		
		addLine(polyData,pFrontLeftForearm001,pFrontLeftArm001,pFrontLeftArm011,pFrontLeftForearm011,Renderer3D.CAR_LEFT);

		addLine(polyData,pFrontLeftForearm011,pFrontLeftArm011,pFrontLeftArm111,pFrontLeftForearm111,Renderer3D.CAR_FRONT);

		addLine(polyData,pFrontLeftForearm111,pFrontLeftArm111,pFrontLeftArm101,pFrontLeftForearm101,Renderer3D.CAR_RIGHT);

		addLine(polyData,pFrontLeftForearm101,pFrontLeftArm101,pFrontLeftArm001,pFrontLeftForearm001,Renderer3D.CAR_BACK);
		
		
		//Right forearm
		
		BPoint pFrontRightForearm000=addBPoint(ax+x_side-leg_side,ay,az,n++,points);
		BPoint pFrontRightForearm100=addBPoint(ax+x_side,ay,az,n++,points);
		BPoint pFrontRightForearm110=addBPoint(ax+x_side,ay+leg_side,az,n++,points);
		BPoint pFrontRightForearm010=addBPoint(ax+x_side-leg_side,ay+leg_side,az,n++,points);

		addLine(polyData,pFrontRightForearm000,pFrontRightForearm010,pFrontRightForearm110,pFrontRightForearm100,Renderer3D.CAR_BOTTOM);

		
		BPoint pFrontRightForearm001=addBPoint(ax+x_side-leg_side,ay,az+radius_length,n++,points);
		BPoint pFrontRightForearm101=addBPoint(ax+x_side,ay,az+radius_length,n++,points);
		BPoint pFrontRightForearm111=addBPoint(ax+x_side,ay+leg_side,az+radius_length,n++,points);
		BPoint pFrontRightForearm011=addBPoint(ax+x_side-leg_side,ay+leg_side,az+radius_length,n++,points);
		
		//LineData topRightForearm=addLine(polyData,pFrontRightForearm001,pFrontRightForearm101,pFrontRightForearm111,pFrontRightForearm011,Renderer3D.CAR_TOP);
		//polyData.add(topRightForearm);
		
		addLine(polyData,pFrontRightForearm000,pFrontRightForearm001,pFrontRightForearm011,pFrontRightForearm010,Renderer3D.CAR_LEFT);

		addLine(polyData,pFrontRightForearm010,pFrontRightForearm011,pFrontRightForearm111,pFrontRightForearm110,Renderer3D.CAR_FRONT);
		
		addLine(polyData,pFrontRightForearm110,pFrontRightForearm111,pFrontRightForearm101,pFrontRightForearm100,Renderer3D.CAR_RIGHT);
	
		addLine(polyData,pFrontRightForearm100,pFrontRightForearm101,pFrontRightForearm001,pFrontRightForearm000,Renderer3D.CAR_BACK);

		
		//right arm
		
		BPoint pFrontRightArm001=addBPoint(ax+x_side-leg_side,ay,az+radius_length+humerus_length,n++,points);
		BPoint pFrontRightArm101=addBPoint(ax+x_side,ay,az+radius_length+humerus_length,n++,points);
		BPoint pFrontRightArm111=addBPoint(ax+x_side,ay+leg_side,az+radius_length+humerus_length,n++,points);
		BPoint pFrontRightArm011=addBPoint(ax+x_side-leg_side,ay+leg_side,az+radius_length+humerus_length,n++,points);
		
		addLine(polyData,pFrontRightArm001,pFrontRightArm101,pFrontRightArm111,pFrontRightArm011,Renderer3D.CAR_TOP);
			
		addLine(polyData,pFrontRightForearm001,pFrontRightArm001,pFrontRightArm011,pFrontRightForearm011,Renderer3D.CAR_LEFT);

		addLine(polyData,pFrontRightForearm011,pFrontRightArm011,pFrontRightArm111,pFrontRightForearm111,Renderer3D.CAR_FRONT);
	
		addLine(polyData,pFrontRightForearm111,pFrontRightArm111,pFrontRightArm101,pFrontRightForearm101,Renderer3D.CAR_RIGHT);
		
		addLine(polyData,pFrontRightForearm101,pFrontRightArm101,pFrontRightArm001,pFrontRightForearm001,Renderer3D.CAR_BACK);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;
	}

	private PolygonMesh buildQuadrupedMesh() {


		Vector points=new Vector();
		points.setSize(200);

		Vector polyData=new Vector();
		
		int n=0;
		


		//main body:
		
		double bz0=femur_length+shinbone_length;
		double bz1=femur_length+shinbone_length+z_side;
		
		double by0=0;
		double by1=y_side/4.0;
		double by2=y_side/2.0;
		double by3=y_side*3.0/4.0;
		double by4=y_side;
		
		int numx=2;
		int numy=5;
		int numz=2;
		
		BPoint[][][] body=new BPoint[numx][numy][numz]; 

		body[0][0][0]=addBPoint(0,by0,bz0,n++,points);
		body[1][0][0]=addBPoint(x_side,by0,bz0,n++,points);
		body[0][0][1]=addBPoint(0,by0,bz1,n++,points);	
		body[1][0][1]=addBPoint(x_side,by0,bz1,n++,points);
		
		body[0][1][0]=addBPoint(0,by1,bz0,n++,points);
		body[1][1][0]=addBPoint(x_side,by1,bz0,n++,points);			
		body[0][1][1]=addBPoint(0,by1,bz1,n++,points);		
		body[1][1][1]=addBPoint(x_side,by1,bz1,n++,points);

		body[0][2][0]=addBPoint(0,by2,bz0,n++,points);
		body[1][2][0]=addBPoint(x_side,by2,bz0,n++,points);			
		body[0][2][1]=addBPoint(0,by2,bz1,n++,points);		
		body[1][2][1]=addBPoint(x_side,by2,bz1,n++,points);		
		
		body[0][3][0]=addBPoint(0,by3,bz0,n++,points);
		body[1][3][0]=addBPoint(x_side,by3,bz0,n++,points);			
		body[0][3][1]=addBPoint(0,by3,bz1,n++,points);		
		body[1][3][1]=addBPoint(x_side,by3,bz1,n++,points);
		
		body[0][4][0]=addBPoint(0,by4,bz0,n++,points);
		body[1][4][0]=addBPoint(x_side,by4,bz0,n++,points);			
		body[0][4][1]=addBPoint(0,by4,bz1,n++,points);		
		body[1][4][1]=addBPoint(x_side,by4,bz1,n++,points);


		addLine(polyData,body[0][0][1],body[1][0][1],body[1][1][1],body[0][1][1],Renderer3D.CAR_TOP);
		
		addLine(polyData,body[0][0][0],body[0][1][0],body[1][1][0],body[1][0][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,body[0][0][0],body[0][0][1],body[0][1][1],body[0][1][0],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][0][0],body[1][1][0],body[1][1][1],body[1][0][1],Renderer3D.CAR_RIGHT);

		addLine(polyData,body[0][0][0],body[1][0][0],body[1][0][1],body[0][0][1],Renderer3D.CAR_BACK);

		//addLine(polyData,body[0][1][0],body[0][1][1],body[1][1][1],body[1][1][0],Renderer3D.CAR_FRONT);		
		
		
		
		addLine(polyData,body[0][1][1],body[1][1][1],body[1][2][1],body[0][2][1],Renderer3D.CAR_TOP);
		
		addLine(polyData,body[0][1][0],body[0][2][0],body[1][2][0],body[1][1][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,body[0][1][0],body[0][1][1],body[0][2][1],body[0][2][0],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][1][0],body[1][2][0],body[1][2][1],body[1][1][1],Renderer3D.CAR_RIGHT);

		//addLine(polyData,body[0][0][0],body[1][0][0],body[1][0][1],body[0][0][1],Renderer3D.CAR_BACK);

		//addLine(polyData,body[0][1][0],body[0][1][1],body[1][1][1],body[1][1][0],Renderer3D.CAR_FRONT);	
		
		
		
		addLine(polyData,body[0][2][1],body[1][2][1],body[1][3][1],body[0][3][1],Renderer3D.CAR_TOP);
		
		addLine(polyData,body[0][2][0],body[0][3][0],body[1][3][0],body[1][2][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,body[0][2][0],body[0][2][1],body[0][3][1],body[0][3][0],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][2][0],body[1][3][0],body[1][3][1],body[1][2][1],Renderer3D.CAR_RIGHT);

		//addLine(polyData,body[0][0][0],body[1][0][0],body[1][0][1],body[0][0][1],Renderer3D.CAR_BACK);

		//addLine(polyData,body[0][1][0],body[0][1][1],body[1][1][1],body[1][1][0],Renderer3D.CAR_FRONT);	
		
		
		
		addLine(polyData,body[0][3][1],body[1][3][1],body[1][4][1],body[0][4][1],Renderer3D.CAR_TOP);
		
		addLine(polyData,body[0][3][0],body[0][4][0],body[1][4][0],body[1][3][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,body[0][3][0],body[0][3][1],body[0][4][1],body[0][4][0],Renderer3D.CAR_LEFT);

		addLine(polyData,body[1][3][0],body[1][4][0],body[1][4][1],body[1][3][1],Renderer3D.CAR_RIGHT);

		//addLine(polyData,body[0][0][0],body[1][0][0],body[1][0][1],body[0][0][1],Renderer3D.CAR_BACK);

		addLine(polyData,body[0][4][0],body[0][4][1],body[1][4][1],body[1][4][0],Renderer3D.CAR_FRONT);	
		
		
		
		/////////head:		
		
		
		double hx=(x_side-head_DX)/2.0;
		double hy=(y_side-neck_side);			
		double hz=femur_length+shinbone_length+z_side+neck_length;
		
		BPoint[][][] head=new BPoint[2][2][2];

		head[0][0][0]=addBPoint(hx,hy,hz,n++,points);
		head[1][0][0]=addBPoint(hx+head_DX,hy,hz,n++,points);
		head[0][1][0]=addBPoint(hx,hy+head_DY,hz,n++,points);
		head[1][1][0]=addBPoint(hx+head_DX,hy+head_DY,hz,n++,points);

		head[0][1][1]=addBPoint(hx,hy+head_DY,hz+head_DZ,n++,points);
		head[1][0][1]=addBPoint(hx+head_DX,hy,hz+head_DZ,n++,points);
		head[1][1][1]=addBPoint(hx+head_DX,hy+head_DY,hz+head_DZ,n++,points);
		head[0][0][1]=addBPoint(hx,hy,hz+head_DZ,n++,points);

		addLine(polyData,head[0][0][1],head[1][0][1],head[1][1][1],head[0][1][1],Renderer3D.CAR_TOP);

		addLine(polyData,head[0][0][0],head[0][1][0],head[1][1][0],head[1][0][0],Renderer3D.CAR_BOTTOM);

		addLine(polyData,head[0][0][0],head[0][0][1],head[0][1][1],head[0][1][0],Renderer3D.CAR_LEFT);

		addLine(polyData,head[1][0][0],head[1][1][0],head[1][1][1],head[1][0][1],Renderer3D.CAR_RIGHT);

		addLine(polyData,head[0][0][0],head[1][0][0],head[1][0][1],head[0][0][1],Renderer3D.CAR_BACK);

		addLine(polyData,head[0][1][0],head[0][1][1],head[1][1][1],head[1][1][0],Renderer3D.CAR_FRONT);
		
		//neck:
		
		
		double nz0=femur_length+shinbone_length+z_side;
		double nz1=femur_length+shinbone_length+z_side+neck_length;
		double nx=(x_side-neck_side)/2.0;
		double ny=y_side-neck_side;
	
		BPoint neck000=addBPoint(nx,ny,nz0,n++,points);
		BPoint neck100=addBPoint(nx+neck_side,ny,nz0,n++,points);
		BPoint neck010=addBPoint(nx,ny+neck_side,nz0,n++,points);
		BPoint neck110=addBPoint(nx+neck_side,ny+neck_side,nz0,n++,points);

		BPoint neck011=addBPoint(nx,ny+neck_side,nz1,n++,points);
		BPoint neck101=addBPoint(nx+neck_side,ny,nz1,n++,points);
		BPoint neck111=addBPoint(nx+neck_side,ny+neck_side,nz1,n++,points);
		BPoint neck001=addBPoint(nx,ny,nz1,n++,points);

		addLine(polyData,neck001,neck101,neck111,neck011,Renderer3D.CAR_TOP);

		addLine(polyData,neck000,neck010,neck110,neck100,Renderer3D.CAR_BOTTOM);

		addLine(polyData,neck000,neck001,neck011,neck010,Renderer3D.CAR_LEFT);

		addLine(polyData,neck100,neck110,neck111,neck101,Renderer3D.CAR_RIGHT);

		addLine(polyData,neck000,neck100,neck101,neck001,Renderer3D.CAR_BACK);

		addLine(polyData,neck010,neck011,neck111,neck110,Renderer3D.CAR_FRONT);

		
		//neck:
		
		
		//legs:	
		
		//backLeftLeg
		
		BPoint pBackLeftLeg000=addBPoint(0,0,0,n++,points);
		BPoint pBackLeftLeg100=addBPoint(leg_side,0,0,n++,points);
		BPoint pBackLeftLeg110=addBPoint(leg_side,leg_side,0,n++,points);
		BPoint pBackLeftLeg010=addBPoint(0,leg_side,0,n++,points);

		
		LineData backLeftLeg=buildLine(pBackLeftLeg000,pBackLeftLeg010,pBackLeftLeg110,pBackLeftLeg100,Renderer3D.CAR_FRONT);
		polyData.add(backLeftLeg);

		
		BPoint pBackLeftLeg001=addBPoint(0,0,shinbone_length,n++,points);
		BPoint pBackLeftLeg101=addBPoint(leg_side,0,shinbone_length,n++,points);
		BPoint pBackLeftLeg111=addBPoint(leg_side,leg_side,shinbone_length,n++,points);
		BPoint pBackLeftLeg011=addBPoint(0,leg_side,shinbone_length,n++,points);
		
		LineData backLeftLegS0=buildLine(pBackLeftLeg000,pBackLeftLeg001,pBackLeftLeg011,pBackLeftLeg010,Renderer3D.CAR_LEFT);
		polyData.add(backLeftLegS0);
		LineData backLeftLegS1=buildLine(pBackLeftLeg010,pBackLeftLeg011,pBackLeftLeg111,pBackLeftLeg110,Renderer3D.CAR_FRONT);
		polyData.add(backLeftLegS1);
		LineData backLeftLegS2=buildLine(pBackLeftLeg110,pBackLeftLeg111,pBackLeftLeg101,pBackLeftLeg100,Renderer3D.CAR_RIGHT);
		polyData.add(backLeftLegS2);
		LineData backLeftLegS3=buildLine(pBackLeftLeg100,pBackLeftLeg101,pBackLeftLeg001,pBackLeftLeg000,Renderer3D.CAR_BACK);
		polyData.add(backLeftLegS3);
		
		//back left thigh
		
		BPoint pBackLeftThigh001=addBPoint(0,0,femur_length+shinbone_length,n++,points);
		BPoint pBackLeftThigh101=addBPoint(leg_side,0,femur_length+shinbone_length,n++,points);
		BPoint pBackLeftThigh111=addBPoint(leg_side,leg_side,femur_length+shinbone_length,n++,points);
		BPoint pBackLeftThigh011=addBPoint(0,leg_side,femur_length+shinbone_length,n++,points);

		LineData backLeftThighS0=buildLine(pBackLeftLeg001,pBackLeftThigh001,pBackLeftThigh011,pBackLeftLeg011,Renderer3D.CAR_LEFT);
		polyData.add(backLeftThighS0);
		LineData backLeftThighS1=buildLine(pBackLeftLeg011,pBackLeftThigh011,pBackLeftThigh111,pBackLeftLeg111,Renderer3D.CAR_FRONT);
		polyData.add(backLeftThighS1);
		LineData backLeftThighS2=buildLine(pBackLeftLeg111,pBackLeftThigh111,pBackLeftThigh101,pBackLeftLeg101,Renderer3D.CAR_RIGHT);
		polyData.add(backLeftThighS2);
		LineData backLeftThighS3=buildLine(pBackLeftLeg101,pBackLeftThigh101,pBackLeftThigh001,pBackLeftLeg001,Renderer3D.CAR_BACK);
		polyData.add(backLeftThighS3);
		
		//backRightLeg
		
		BPoint pBackRightLeg000=addBPoint(x_side-leg_side,0,0,n++,points);
		BPoint pBackRightLeg100=addBPoint(x_side,0,0,n++,points);
		BPoint pBackRightLeg110=addBPoint(x_side,leg_side,0,n++,points);
		BPoint pBackRightLeg010=addBPoint(x_side-leg_side,leg_side,0,n++,points);
		
		LineData backRightLeg=buildLine(pBackRightLeg000,pBackRightLeg010,pBackRightLeg110,pBackRightLeg100,Renderer3D.CAR_FRONT);
		polyData.add(backRightLeg);

		
		BPoint pBackRightLeg001=addBPoint(x_side-leg_side,0,shinbone_length,n++,points);
		BPoint pBackRightLeg101=addBPoint(x_side,0,shinbone_length,n++,points);
		BPoint pBackRightLeg111=addBPoint(x_side,leg_side,shinbone_length,n++,points);
		BPoint pBackRightLeg011=addBPoint(x_side-leg_side,leg_side,shinbone_length,n++,points);

		
		LineData backRightLegS0=buildLine(pBackRightLeg000,pBackRightLeg001,pBackRightLeg011,pBackRightLeg010,Renderer3D.CAR_LEFT);
		polyData.add(backRightLegS0);
		LineData backRightLegS1=buildLine(pBackRightLeg010,pBackRightLeg011,pBackRightLeg111,pBackRightLeg110,Renderer3D.CAR_FRONT);
		polyData.add(backRightLegS1);
		LineData backRightLegS2=buildLine(pBackRightLeg110,pBackRightLeg111,pBackRightLeg101,pBackRightLeg100,Renderer3D.CAR_RIGHT);
		polyData.add(backRightLegS2);
		LineData backRightLegS3=buildLine(pBackRightLeg100,pBackRightLeg101,pBackRightLeg001,pBackRightLeg000,Renderer3D.CAR_BACK);
		polyData.add(backRightLegS3);
		
		//back right thigh
		
		BPoint pBackRightThigh001=addBPoint(x_side-leg_side,0,femur_length+shinbone_length,n++,points);
		BPoint pBackRightThigh101=addBPoint(x_side,0,femur_length+shinbone_length,n++,points);
		BPoint pBackRightThigh111=addBPoint(x_side,leg_side,femur_length+shinbone_length,n++,points);
		BPoint pBackRightThigh011=addBPoint(x_side-leg_side,leg_side,femur_length+shinbone_length,n++,points);

		LineData backRightThighS0=buildLine(pBackRightLeg001,pBackRightThigh001,pBackRightThigh011,pBackRightLeg011,Renderer3D.CAR_LEFT);
		polyData.add(backRightThighS0);
		LineData backRightThighS1=buildLine(pBackRightLeg011,pBackRightThigh011,pBackRightThigh111,pBackRightLeg111,Renderer3D.CAR_FRONT);
		polyData.add(backRightThighS1);
		LineData backRightThighS2=buildLine(pBackRightLeg111,pBackRightThigh111,pBackRightThigh101,pBackRightLeg101,Renderer3D.CAR_RIGHT);
		polyData.add(backRightThighS2);
		LineData backRightThighS3=buildLine(pBackRightLeg101,pBackRightThigh101,pBackRightThigh001,pBackRightLeg001,Renderer3D.CAR_BACK);
		polyData.add(backRightThighS3);
		
		//frontLeftLeg
		
		BPoint pFrontLeftForearm000=addBPoint(0,y_side-leg_side,0,n++,points);
		BPoint pFrontLeftForearm100=addBPoint(leg_side,y_side-leg_side,0,n++,points);
		BPoint pFrontLeftForearm110=addBPoint(leg_side,y_side,0,n++,points);
		BPoint pFrontLeftForearm010=addBPoint(0,y_side,0,n++,points);
		
		LineData FrontLeftForearm=buildLine(pFrontLeftForearm000,pFrontLeftForearm010,pFrontLeftForearm110,pFrontLeftForearm100,Renderer3D.CAR_FRONT);
		polyData.add(FrontLeftForearm);
		
		BPoint pFrontLeftForearm001=addBPoint(0,y_side-leg_side,radius_length,n++,points);
		BPoint pFrontLeftForearm101=addBPoint(leg_side,y_side-leg_side,radius_length,n++,points);
		BPoint pFrontLeftForearm111=addBPoint(leg_side,y_side,radius_length,n++,points);
		BPoint pFrontLeftForearm011=addBPoint(0,y_side,radius_length,n++,points);
		
		LineData FrontLeftForearmS0=buildLine(pFrontLeftForearm000,pFrontLeftForearm001,pFrontLeftForearm011,pFrontLeftForearm010,Renderer3D.CAR_LEFT);
		polyData.add(FrontLeftForearmS0);
		LineData FrontLeftForearmS1=buildLine(pFrontLeftForearm010,pFrontLeftForearm011,pFrontLeftForearm111,pFrontLeftForearm110,Renderer3D.CAR_FRONT);
		polyData.add(FrontLeftForearmS1);
		LineData FrontLeftForearmS2=buildLine(pFrontLeftForearm110,pFrontLeftForearm111,pFrontLeftForearm101,pFrontLeftForearm100,Renderer3D.CAR_RIGHT);
		polyData.add(FrontLeftForearmS2);
		LineData FrontLeftForearmS3=buildLine(pFrontLeftForearm100,pFrontLeftForearm101,pFrontLeftForearm001,pFrontLeftForearm000,Renderer3D.CAR_BACK);
		polyData.add(FrontLeftForearmS3);
		
		//left arm
		
		BPoint pFrontLeftArm001=addBPoint(0,y_side-leg_side,radius_length+humerus_length,n++,points);
		BPoint pFrontLeftArm101=addBPoint(leg_side,y_side-leg_side,radius_length+humerus_length,n++,points);
		BPoint pFrontLeftArm111=addBPoint(leg_side,y_side,radius_length+humerus_length,n++,points);
		BPoint pFrontLeftArm011=addBPoint(0,y_side,radius_length+humerus_length,n++,points);
		
		LineData topLeftArm=buildLine(pFrontLeftArm001,pFrontLeftArm101,pFrontLeftArm111,pFrontLeftArm011,Renderer3D.CAR_TOP);
		polyData.add(topLeftArm);
		
		LineData FrontLeftArmS0=buildLine(pFrontLeftForearm000,pFrontLeftArm001,pFrontLeftArm011,pFrontLeftForearm010,Renderer3D.CAR_LEFT);
		polyData.add(FrontLeftArmS0);
		LineData FrontLeftArmS1=buildLine(pFrontLeftForearm010,pFrontLeftArm011,pFrontLeftArm111,pFrontLeftForearm110,Renderer3D.CAR_FRONT);
		polyData.add(FrontLeftArmS1);
		LineData FrontLeftArmS2=buildLine(pFrontLeftForearm110,pFrontLeftArm111,pFrontLeftArm101,pFrontLeftForearm100,Renderer3D.CAR_RIGHT);
		polyData.add(FrontLeftArmS2);
		LineData FrontLeftArmS3=buildLine(pFrontLeftForearm100,pFrontLeftArm101,pFrontLeftArm001,pFrontLeftForearm000,Renderer3D.CAR_BACK);
		polyData.add(FrontLeftArmS3);
		
		//frontRightLeg
		
		BPoint pFrontRightForearm000=addBPoint(x_side-leg_side,y_side-leg_side,0,n++,points);
		BPoint pFrontRightForearm100=addBPoint(x_side,y_side-leg_side,0,n++,points);
		BPoint pFrontRightForearm110=addBPoint(x_side,y_side,0,n++,points);
		BPoint pFrontRightForearm010=addBPoint(x_side-leg_side,y_side,0,n++,points);
		
		LineData FrontRightForearm=buildLine(pFrontRightForearm000,pFrontRightForearm010,pFrontRightForearm110,pFrontRightForearm100,Renderer3D.CAR_FRONT);
		polyData.add(FrontRightForearm);

		
		BPoint pFrontRightForearm001=addBPoint(x_side-leg_side,y_side-leg_side,radius_length,n++,points);
		BPoint pFrontRightForearm101=addBPoint(x_side,y_side-leg_side,radius_length,n++,points);
		BPoint pFrontRightForearm111=addBPoint(x_side,y_side,radius_length,n++,points);
		BPoint pFrontRightForearm011=addBPoint(x_side-leg_side,y_side,radius_length,n++,points);

		
		LineData frontRightForearmS0=buildLine(pFrontRightForearm000,pFrontRightForearm001,pFrontRightForearm011,pFrontRightForearm010,Renderer3D.CAR_LEFT);
		polyData.add(frontRightForearmS0);
		LineData frontRightForearmS1=buildLine(pFrontRightForearm010,pFrontRightForearm011,pFrontRightForearm111,pFrontRightForearm110,Renderer3D.CAR_FRONT);
		polyData.add(frontRightForearmS1);
		LineData frontRightForearmS2=buildLine(pFrontRightForearm110,pFrontRightForearm111,pFrontRightForearm101,pFrontRightForearm100,Renderer3D.CAR_RIGHT);
		polyData.add(frontRightForearmS2);
		LineData frontRightForearmS3=buildLine(pFrontRightForearm100,pFrontRightForearm101,pFrontRightForearm001,pFrontRightForearm000,Renderer3D.CAR_BACK);
		polyData.add(frontRightForearmS3);
		

		//right arm
		
		BPoint pFrontRightArm001=addBPoint(x_side-leg_side,y_side-leg_side,radius_length+humerus_length,n++,points);
		BPoint pFrontRightArm101=addBPoint(x_side,y_side-leg_side,radius_length+humerus_length,n++,points);
		BPoint pFrontRightArm111=addBPoint(x_side,y_side,radius_length+humerus_length,n++,points);
		BPoint pFrontRightArm011=addBPoint(x_side-leg_side,y_side,radius_length+humerus_length,n++,points);
		
		LineData topRightArm=buildLine(pFrontRightArm001,pFrontRightArm101,pFrontRightArm111,pFrontRightArm011,Renderer3D.CAR_TOP);
		polyData.add(topRightArm);
		
		LineData frontRightArmS0=buildLine(pFrontRightForearm000,pFrontRightArm001,pFrontRightArm011,pFrontRightForearm010,Renderer3D.CAR_LEFT);
		polyData.add(frontRightArmS0);
		LineData frontRightArmS1=buildLine(pFrontRightForearm010,pFrontRightArm011,pFrontRightArm111,pFrontRightForearm110,Renderer3D.CAR_FRONT);
		polyData.add(frontRightArmS1);
		LineData frontRightArmS2=buildLine(pFrontRightForearm110,pFrontRightArm111,pFrontRightArm101,pFrontRightForearm100,Renderer3D.CAR_RIGHT);
		polyData.add(frontRightArmS2);
		LineData frontRightArmS3=buildLine(pFrontRightForearm100,pFrontRightArm101,pFrontRightArm001,pFrontRightForearm000,Renderer3D.CAR_BACK);
		polyData.add(frontRightArmS3);
		
		/////////

		//translatePoints(points,nw_x,nw_y);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;
	}




	

	



}	