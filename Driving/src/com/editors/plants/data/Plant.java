package com.editors.plants.data;

import java.util.Vector;

import com.BPoint;
import com.CustomData;
import com.LineData;
import com.Point3D;
import com.PolygonMesh;
import com.main.Renderer3D;

public class Plant extends CustomData{

	double trunk_lenght=0; 
	double trunk_upper_radius=0;
	double trunk_lower_radius=0;
	
	int trunk_parallels=0;
	int trunk_meridians=0;	
	
	double foliage_length=0;
	double foliage_radius=0;
		
	int foliage_meridians=0;
	int foliage_parallels=0;
	int foliage_lobes=0;
	
	double lobe_percentage_depth=1.0;
	
	public static int PLANT_TYPE_0=0;
	
	public int plant_type=PLANT_TYPE_0;

	public Plant(){}

	public Plant(
			int plant_type,
			double trunk_lenght, double trunk_upper_radius,double trunk_lower_radius,
			int trunk_meridians,int trunk_parallels,
			double foliage_length, double foliage_radius,			
			int foliage_meridians,int foliage_parallels,
			int foliage_lobes,
			double lobe_percentage_depth
			
			) {
		
		super();
		
		this.plant_type = plant_type; 
		this.trunk_lenght = trunk_lenght; 
		this.trunk_upper_radius = trunk_upper_radius;
		this.trunk_lower_radius = trunk_lower_radius;
		this.trunk_meridians = trunk_meridians;
		this.trunk_parallels = trunk_parallels;
		
		this.foliage_length = foliage_length;
		this.foliage_radius = foliage_radius;
		
		this.foliage_meridians = foliage_meridians;
		this.foliage_parallels = foliage_parallels;
		this.foliage_lobes = foliage_lobes;
		this.lobe_percentage_depth = lobe_percentage_depth;
	}


	public Object clone(){

		Plant grid=new Plant(
				plant_type,
				trunk_lenght,trunk_upper_radius,trunk_lower_radius,
				trunk_meridians,trunk_parallels,
				foliage_length,foliage_radius,
				foliage_meridians,foliage_parallels,
				foliage_lobes,lobe_percentage_depth);
		return grid;

	}




	public String toString() {

		return "F="+plant_type+","+trunk_lenght+","+trunk_upper_radius+","+trunk_lower_radius+","
				+trunk_meridians+","+trunk_parallels+","
				+foliage_length+","+foliage_radius+","+
				foliage_meridians+","+foliage_parallels+","+foliage_lobes
				+","+lobe_percentage_depth;
	}

	public static Plant buildPlant(String str) {

		String[] vals = str.split(",");

		int plant_type=Integer.parseInt(vals[0]);
		double trunk_lenght =Double.parseDouble(vals[1]);
		double trunk_upper_radius = Double.parseDouble(vals[2]);
		double trunk_lower_radius = Double.parseDouble(vals[3]);
		int trunk_meridians=Integer.parseInt(vals[4]);
		int trunk_parallels=Integer.parseInt(vals[5]);
		
		double foliage_length = Double.parseDouble(vals[6]);  
		double foliage_radius = Double.parseDouble(vals[7]); 
		int foliageMeridians=Integer.parseInt(vals[8]);
		int foliageParallels=Integer.parseInt(vals[9]);
		int foliageLobes=Integer.parseInt(vals[10]);
		double lobe_percentage_depth = Double.parseDouble(vals[11]);

		Plant grid=new Plant(
				plant_type,
				trunk_lenght,trunk_upper_radius,trunk_lower_radius,	
				trunk_meridians,trunk_parallels,
				foliage_length,foliage_radius,
				foliageMeridians,foliageParallels,foliageLobes,
				lobe_percentage_depth
				);

		return grid;
	}

	public PolygonMesh buildMesh(){
		
		if(plant_type==PLANT_TYPE_0)
			return buildMeshPlant0();
		else
			return buildMeshPlant0();
	}

	
	public PolygonMesh buildMeshPlant0(){



		points=new Vector();
		points.setSize(800);

		polyData=new Vector();
		
		
		n=0;


		//trunk:

	    BPoint[][] trunkpoints=new BPoint[trunk_parallels][trunk_meridians];
		
		for (int k = 0; k < trunk_parallels; k++) {
			
			
			for (int i = 0; i < trunk_meridians; i++) {
				
				double z=trunk_lenght/(trunk_parallels-1.0)*k;
				
				//linear
				//double r=z/trunk_lenght*(trunk_upper_radius-trunk_lower_radius)+trunk_lower_radius;
				//parabolic
				double r=(z-trunk_lenght)*(z-trunk_lenght)
						*(trunk_lower_radius-trunk_upper_radius)/(trunk_lenght*trunk_lenght)
						+trunk_upper_radius;
								
				double x=r*Math.cos(2*Math.PI/trunk_meridians*i);
				double y=r*Math.sin(2*Math.PI/trunk_meridians*i);
				
				
				trunkpoints[k][i]=addBPoint(x,y,z);
				
			}
			
		}
		


		LineData topLD=new LineData();
		
		for (int i = 0; i < trunk_meridians; i++) {
			
			topLD.addIndex(trunkpoints[trunk_parallels-1][i].getIndex());
			
		}
		topLD.setData(""+Renderer3D.CAR_TOP);
		polyData.add(topLD);
		



		LineData bottomLD=new LineData();
		
		for (int i = trunk_meridians-1; i >=0; i--) {
			
			bottomLD.addIndex(trunkpoints[0][i].getIndex());
			
		}
		bottomLD.setData(""+Renderer3D.CAR_BOTTOM);
		polyData.add(bottomLD);
		
		for (int k = 0; k < trunk_parallels-1; k++) {
		
			for (int i = 0; i < trunk_meridians; i++) {
				
				LineData sideLD=new LineData();
				
				sideLD.addIndex(trunkpoints[k][i].getIndex());
				sideLD.addIndex(trunkpoints[k][(i+1)%trunk_meridians].getIndex());
				sideLD.addIndex(trunkpoints[k+1][(i+1)%trunk_meridians].getIndex());
				sideLD.addIndex(trunkpoints[k+1][i].getIndex());	
				sideLD.setData(""+Renderer3D.getFace(sideLD,points));
				polyData.add(sideLD);
				
				
			}
		}
		//foliage:
		
	
		
		BPoint[][] foliagePoints=new BPoint[foliage_parallels][foliage_meridians];
		
		double dzf=foliage_length/(foliage_parallels-1);
		
		for (int k = 0; k < foliage_parallels; k++) {
			
			double zf=dzf*k;
			
			foliagePoints[k]=new BPoint[foliage_meridians];
			
			for (int i = 0; i < foliage_meridians; i++) {
				
				double teta=2*Math.PI/foliage_meridians*i;
				
				double r=ff(zf)*rr(teta);			
				
				double x=r*Math.cos(teta);
				double y=r*Math.sin(teta);
				
				foliagePoints[k][i]=addBPoint(x,y,trunk_lenght+zf);
				
			}
			
		}
			

		LineData topFoliage=new LineData();
		
		for (int i = 0; i < foliage_meridians; i++) {
			
			topFoliage.addIndex(foliagePoints[foliage_parallels-1][i].getIndex());
			
		}
		topFoliage.setData(""+Renderer3D.CAR_TOP);
		polyData.add(topFoliage);
		
		LineData bottomFoliage=new LineData();
		
		for (int i = foliage_meridians-1; i>=0; i--) {
			
			bottomFoliage.addIndex(foliagePoints[0][i].getIndex());
			
			
		}
		bottomFoliage.setData(""+Renderer3D.getFace(bottomFoliage,points));
		polyData.add(bottomFoliage);
		
		for (int k = 0; k < foliage_parallels-1; k++) {
		
		
			for (int i = 0; i < foliage_meridians; i++) {
				
				LineData sideLD=new LineData();
				
				sideLD.addIndex(foliagePoints[k][i].getIndex());
				sideLD.addIndex(foliagePoints[k][(i+1)%foliage_meridians].getIndex());
				sideLD.addIndex(foliagePoints[k+1][(i+1)%foliage_meridians].getIndex());
				sideLD.addIndex(foliagePoints[k+1][i].getIndex());	
				sideLD.setData(""+Renderer3D.getFace(sideLD,points));
				polyData.add(sideLD);
				
			}
	
		}
		/////////

		//translatePoints(points,nw_x,nw_y);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;


	}
	
	
	


	public double getTrunk_lenght() {
		return trunk_lenght;
	}

	public void setTrunk_lenght(double trunk_lenght) {
		this.trunk_lenght = trunk_lenght; 
	}


	public double getFoliage_length() {
		return foliage_length;
	}

	public void setFoliage_length(double foliage_length) {
		this.foliage_length = foliage_length;
	}

	public double getFoliage_radius() {
		return foliage_radius;
	}

	public void setFoliage_radius(double foliage_radius) {
		this.foliage_radius = foliage_radius;
	}


	public int getFoliage_meridians() {
		return foliage_meridians;
	}

	public void setFoliage_meridians(int foliage_meridians) {
		this.foliage_meridians = foliage_meridians;
	}

	public int getFoliage_parallels() {
		return foliage_parallels;
	}

	public void setFoliage_parallels(int foliage_parallels) {
		this.foliage_parallels = foliage_parallels;
	}

	public int getFoliage_lobes() {
		return foliage_lobes;
	}

	public void setFoliage_lobes(int foliage_lobes) {
		this.foliage_lobes = foliage_lobes;
	}


	public double getTrunk_upper_radius() {
		return trunk_upper_radius;
	}

	public void setTrunk_upper_radius(double trunk_upper_radius) {
		this.trunk_upper_radius = trunk_upper_radius;
	}

	public double getTrunk_lower_radius() {
		return trunk_lower_radius;
	}

	public void setTrunk_lower_radius(double trunk_lower_radius) {
		this.trunk_lower_radius = trunk_lower_radius;
	}

	public int getTrunk_parallels() {
		return trunk_parallels;
	}

	public void setTrunk_parallels(int trunk_parallels) {
		this.trunk_parallels = trunk_parallels;
	}

	public int getTrunk_meridians() {
		return trunk_meridians;
	}

	public void setTrunk_meridians(int trunk_meridians) {
		this.trunk_meridians = trunk_meridians;
	}

	public double getLobe_percentage_depth() {
		return lobe_percentage_depth;
	}

	public void setLobe_percentage_depth(double lobe_percentage_depth) {
		this.lobe_percentage_depth = lobe_percentage_depth;
	}


	public double ff(double x){
		
		if(foliage_length==0)
			return 0;
		
		double a=-4*(foliage_radius-trunk_upper_radius);
		double b=4*(foliage_radius-trunk_upper_radius);
		double c=trunk_upper_radius;
		
		double xr=x/foliage_length;

		
		return a*xr*xr+b*xr+c;
		
	}
	
	public double rr(double teta){
		
		double alfa=lobe_percentage_depth/100.0;
		
		double rad=(alfa+(1.0-alfa)*Math.abs(Math.cos(foliage_lobes*teta/2.0)));
		
		return rad;
		
	}

	public int getPlant_type() {
		return plant_type;
	}

	public void setPlant_type(int plant_type) {
		this.plant_type = plant_type;
	}


}	