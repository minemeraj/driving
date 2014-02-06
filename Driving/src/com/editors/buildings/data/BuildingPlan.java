package com.editors.buildings.data;

import java.util.Vector;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import com.LineData;
import com.Point3D;
import com.PolygonMesh;
import com.main.Renderer3D;

public class BuildingPlan {
	


	double x_side=0;
	double y_side=0;
	double z_side=0;
	
	double nw_x=0;
	double nw_y=0;

	
	public BuildingPlan(){}
	
	public BuildingPlan( double nw_x, double nw_y,double x_side, double y_side,double z_side
			) {
		super();

		this.x_side = x_side;
		this.y_side = y_side;
		this.z_side = z_side;
		this.nw_x = nw_x;
		this.nw_y = nw_y;
		

	}
	
	public Object clone(){
		
		BuildingPlan grid=new BuildingPlan(nw_x,nw_y,x_side,y_side,z_side);

		return grid;
		
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
		
		return nw_x+","+nw_y+","+x_side+","+y_side+","+z_side;
	}
	
	public static BuildingPlan buildPlan(String str) {
		
		String[] vals = str.split(",");
		
		double nw_x =Double.parseDouble(vals[0]);
		double nw_y = Double.parseDouble(vals[1]);
		double x_side =Double.parseDouble(vals[2]);
		double y_side = Double.parseDouble(vals[3]);
		double z_side = Double.parseDouble(vals[4]);

		
		BuildingPlan grid=new BuildingPlan(nw_x,nw_y,x_side,y_side,z_side);
	
		return grid;
	}


	
	public PolygonMesh buildMesh(){

		int xnum=1;
		int ynum=1;


		Vector points=new Vector();
		points.setSize((xnum+1)*(ynum+1)*2);

		Vector polyData=new Vector();

		for (int k = 0; k < 2; k++) {

			for (int i = 0; i < xnum+1; i++) {

				for (int j = 0; j <ynum+1; j++) {

					Point3D p=new Point3D(i*x_side,j*y_side,k*z_side);
					points.setElementAt(p,pos(i,j,k));
				}

			}

		}


		for (int i = 0; i < xnum; i++) {

			for (int j = 0; j <ynum; j++) {


				LineData topLD=new LineData();
				topLD.addIndex(pos(i,j,1));
				topLD.addIndex(pos(i+1,j,1));					
				topLD.addIndex(pos(i+1,j+1,1));
				topLD.addIndex(pos(i,j+1,1));	
				topLD.setData(""+Renderer3D.CAR_TOP);
				polyData.add(topLD);


				LineData bottomLD=new LineData();
				bottomLD.addIndex(pos(i,j,0));
				bottomLD.addIndex(pos(i,j+1,0));					
				bottomLD.addIndex(pos(i+1,j+1,0));
				bottomLD.addIndex(pos(i+1,j,0));
				bottomLD.setData(""+Renderer3D.CAR_BOTTOM);
				polyData.add(bottomLD);
				
				LineData leftLD=new LineData();
				leftLD.addIndex(pos(i,j,0));
				leftLD.addIndex(pos(i,j,1));								
				leftLD.addIndex(pos(i,j+1,1));
				leftLD.addIndex(pos(i,j+1,0));		
				leftLD.setData(""+Renderer3D.CAR_LEFT);
				polyData.add(leftLD);
				
				
				LineData rightLD=new LineData();
				rightLD.addIndex(pos(i+1,j,0));
				rightLD.addIndex(pos(i+1,j+1,0));					
				rightLD.addIndex(pos(i+1,j+1,1));
				rightLD.addIndex(pos(i+1,j,1));	
				rightLD.setData(""+Renderer3D.CAR_RIGHT);				
				polyData.add(rightLD);
				
				
				LineData backLD=new LineData();
				backLD.addIndex(pos(i,j,0));
				backLD.addIndex(pos(i+1,j,0));					
				backLD.addIndex(pos(i+1,j,1));
				backLD.addIndex(pos(i,j,1));
				backLD.setData(""+Renderer3D.CAR_BACK);	
				polyData.add(backLD);
				
				LineData frontLD=new LineData();
				frontLD.addIndex(pos(i,j+1,0));
				frontLD.addIndex(pos(i,j+1,1));					
				frontLD.addIndex(pos(i+1,j+1,1));
				frontLD.addIndex(pos(i+1,j+1,0));	
				frontLD.setData(""+Renderer3D.CAR_FRONT);
				polyData.add(frontLD);

			}
		}	



		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;


	}

	public double getZ_side() {
		return z_side;
	}

	public void setZ_side(double z_side) {
		this.z_side = z_side;
	}

	public double getNw_x() {
		return nw_x;
	}

	public void setNw_x(double nw_x) {
		this.nw_x = nw_x;
	}

	public double getNw_y() {
		return nw_y;
	}

	public void setNw_y(double nw_y) {
		this.nw_y = nw_y;
	}
	
	public int pos(int i, int j, int k){
		
		return (i+(1+1)*j)*2+k;
	}

}