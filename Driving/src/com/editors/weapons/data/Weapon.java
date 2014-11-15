package com.editors.weapons.data;

import java.util.Vector;

import com.BPoint;
import com.CustomData;
import com.LineData;
import com.Point3D;
import com.PolygonMesh;
import com.Prism;
import com.Segments;
import com.editors.forniture.data.Barrel;
import com.main.Renderer3D;

public class Weapon extends CustomData{

	double barrel_lenght=0; 
	double barrel_radius=0;		
	int barrel_meridians=0;
	
	public static int WEAPON_TYPE_GUN=0;	
	public static int WEAPON_TYPE_SHOTGUN=1;
	public static int WEAPON_TYPE_DOUBLE_BARREL_SHOTGUN=2;
	public static int WEAPON_TYPE_REVOLVER=3;
	public static int WEAPON_TYPE_SUBMACHINEGUN=4;
	public static int WEAPON_TYPE_BASEBALL_BAT=5;
	
	public int weapon_type=WEAPON_TYPE_GUN;
	
	
	
	double breech_length=0;
	double breech_width=0;
	double breech_height=0;
	
	double butt_length=0;
	double butt_width=0;
	double butt_height=0;

	double butt_end_length=0;
	double butt_end_width=0;
	double butt_end_height=0;
	
	double rear_overhang=0;
	
	double trigger_length=0;
	double trigger_width=0;
	double trigger_height=0;
	
	double forearm_length=0;
	double forearm_width=0;
	double forearm_height=0;
	
	double magazine_length=0;
	double magazine_width=0;
	double magazine_height=0;

	public Weapon(){}

	public Weapon(
			
			int weapon_type,
			
			double barrel_lenght, 
			double barrel_radius,
			int barrel_meridians,

			double breech_length,
			double breech_width,
			double breech_height,

			double butt_length,
			double butt_width,			
			double butt_height,
			
			double butt_end_length,
			double butt_end_width,
			double butt_end_height,
			
			double rear_overhang,
			
			double trigger_length,
			double trigger_width,			
			double trigger_height,
			
			double forearm_length,
			double forearm_width,
			double forearm_height,
			
			double magazine_length,
			double magazine_width,
			double magazine_height
			
		) 
	{

								
				this.weapon_type = weapon_type;
								
				this.barrel_lenght = barrel_lenght;
				this.barrel_radius = barrel_radius;
				this.barrel_meridians = barrel_meridians;
		
				this.breech_length = breech_length;
				this.breech_width = breech_width;
				this.breech_height = breech_height;
		
				this.butt_length = butt_length;
				this.butt_width = butt_width;				
				this.butt_height = butt_height;
				
				this.butt_end_length = butt_end_length;
				this.butt_end_width = butt_end_width;
				this.butt_end_height = butt_end_height;
				
				this.rear_overhang = rear_overhang;
				
				this.trigger_length = trigger_length;
				this.trigger_width = trigger_width;
				this.trigger_height = trigger_height;
				
				this.forearm_length = forearm_length;
				this.forearm_width = forearm_width;
				this.forearm_height = forearm_height;
				
				this.magazine_length = magazine_length;
				this.magazine_width = magazine_width;
				this.magazine_height = magazine_height;

	}


	public Object clone(){

		Weapon grid=new Weapon(
				weapon_type,
				barrel_lenght,barrel_radius,barrel_meridians,
				breech_length,breech_width,breech_height,
				butt_length,butt_width,butt_height,
				butt_end_length,butt_end_width,butt_end_height,
				rear_overhang,
				trigger_length,trigger_width,trigger_height,
				forearm_length,forearm_width,forearm_height,
				magazine_length,magazine_width,magazine_height
				);
		return grid;

	}


	public String toString() {

		return "F="+weapon_type+","+
				barrel_lenght+","+barrel_radius+","+barrel_meridians+","+
				breech_length+","+breech_width+","+breech_height+","+
				butt_length+","+butt_width+","+butt_height+","+
				butt_end_length+","+butt_width+","+butt_end_height+","+
				rear_overhang+","+
				trigger_length+","+trigger_width+","+trigger_height+","+
				forearm_length+","+forearm_width+","+forearm_height+","+
				magazine_length+","+magazine_width+","+magazine_height;
	}

	public static Weapon buildWeapon(String str) {

		String[] vals = str.split(",");

	
		int weaponType =Integer.parseInt(vals[0]);
		
		double barrel_lenght =Double.parseDouble(vals[1]);
		double barrel_radius = Double.parseDouble(vals[2]);
		int barrel_meridians=Integer.parseInt(vals[3]);
		
		double breech_length =Double.parseDouble(vals[4]);
		double breech_width =Double.parseDouble(vals[5]);
		double breech_height =Double.parseDouble(vals[6]);
		
		double butt_length =Double.parseDouble(vals[7]);
		double butt_width =Double.parseDouble(vals[8]); 		
		double butt_height =Double.parseDouble(vals[9]);
		
		double butt_end_length =Double.parseDouble(vals[10]);
		double butt_end_width =Double.parseDouble(vals[11]);
		double butt_end_height =Double.parseDouble(vals[12]);
		
		double rear_overhang =Double.parseDouble(vals[13]);
		
		double trigger_length =Double.parseDouble(vals[14]);
		double trigger_width =Double.parseDouble(vals[15]);
		double trigger_height =Double.parseDouble(vals[16]);
		
		double forearm_length =Double.parseDouble(vals[17]);
		double forearm_width =Double.parseDouble(vals[18]);
		double forearm_height =Double.parseDouble(vals[19]);
		
		double magazine_length =Double.parseDouble(vals[20]);
		double magazine_width =Double.parseDouble(vals[21]);
		double magazine_height =Double.parseDouble(vals[22]);


		Weapon grid=new Weapon(
				weaponType,
				barrel_lenght,barrel_radius,barrel_meridians,
				breech_length,breech_width,breech_height,
				butt_length,butt_width,butt_height,
				butt_end_length,butt_end_width,butt_end_height,
				rear_overhang,
				trigger_length,trigger_width,trigger_height,
				forearm_length,forearm_width,forearm_height,
				magazine_length,magazine_width,magazine_height
				);

		return grid;
	}

	public double getBarrel_lenght() {
		return barrel_lenght;
	}

	public void setBarrel_lenght(double trunk_lenght) {
		this.barrel_lenght = trunk_lenght; 
	}

	public double getBarrel_radius() {
		return barrel_radius;
	}

	public void setBarrel_radius(double trunk_radius) {
		this.barrel_radius = trunk_radius;
	}

	public double getBarrel_length() {
		return breech_length;
	}

	public void setBarrel_length(double foliage_length) {
		this.breech_length = foliage_length;
	}

	public int getBarrel_meridians() {
		return barrel_meridians;
	}

	public void setBarrel_meridians(int foliage_meridians) {
		this.barrel_meridians = foliage_meridians;
	}
	
	public double getBreech_length() {
		return breech_length;
	}

	public void setBreech_length(double breech_length) {
		this.breech_length = breech_length;
	}

	public double getBreech_width() {
		return breech_width;
	}

	public void setBreech_width(double breech_width) {
		this.breech_width = breech_width;
	}

	public double getBreech_height() {
		return breech_height;
	}

	public void setBreech_height(double breech_height) {
		this.breech_height = breech_height;
	}

	public double getButt_length() {
		return butt_length;
	}

	public void setButt_length(double butt_length) {
		this.butt_length = butt_length;
	}


	public double getButt_height() {
		return butt_height;
	}

	public void setButt_height(double butt_height) {
		this.butt_height = butt_height;
	}

	public int getWeapon_type() {
		return weapon_type;
	}

	public void setWeapon_type(int weapon_type) {
		this.weapon_type = weapon_type;
	}

	public double getButt_end_width() {
		return butt_end_width;
	}

	public void setButt_end_width(double butt_end_width) {
		this.butt_end_width = butt_end_width;
	}
	
	public double getButt_width() {
		return butt_width;
	}

	public void setButt_width(double butt_width) {
		this.butt_width = butt_width;
	}
	

	public double getButt_end_length() {
		return butt_end_length;
	}

	public void setButt_end_length(double butt_end_length) {
		this.butt_end_length = butt_end_length;
	}

	public double getButt_end_height() {
		return butt_end_height;
	}

	public void setButt_end_height(double butt_end_height) {
		this.butt_end_height = butt_end_height;
	}


	public double getRear_overhang() {
		return rear_overhang;
	}

	public void setRear_overhang(double rear_overhang) {
		this.rear_overhang = rear_overhang;
	}

	public double getTrigger_length() {
		return trigger_length;
	}

	public void setTrigger_length(double trigger_length) {
		this.trigger_length = trigger_length;
	}

	public double getTrigger_height() {
		return trigger_height;
	}

	public void setTrigger_height(double trigger_height) {
		this.trigger_height = trigger_height;
	}
	
	public double getTrigger_width() {
		return trigger_width;
	}

	public void setTrigger_width(double trigger_width) {
		this.trigger_width = trigger_width;
	}
	

	public double getForearm_length() {
		return forearm_length;
	}

	public void setForearm_length(double forearm_length) {
		this.forearm_length = forearm_length;
	}

	public double getForearm_width() {
		return forearm_width;
	}

	public void setForearm_width(double forearm_width) {
		this.forearm_width = forearm_width;
	}

	public double getForearm_height() {
		return forearm_height;
	}

	public void setForearm_height(double forearm_height) {
		this.forearm_height = forearm_height;
	}
	

	public double getMagazine_length() {
		return magazine_length;
	}

	public void setMagazine_length(double magazine_length) {
		this.magazine_length = magazine_length;
	}

	public double getMagazine_width() {
		return magazine_width;
	}

	public void setMagazine_width(double magazine_width) {
		this.magazine_width = magazine_width;
	}

	public double getMagazine_height() {
		return magazine_height;
	}

	public void setMagazine_height(double magazine_height) {
		this.magazine_height = magazine_height;
	}
	
	public PolygonMesh buildMesh(){

		if(weapon_type==WEAPON_TYPE_SHOTGUN)
			return buildShotGunMesh();
		else if(weapon_type==WEAPON_TYPE_GUN)
			return buildGunMesh();
		else if(weapon_type==WEAPON_TYPE_DOUBLE_BARREL_SHOTGUN)
			return buildDoubleBarrelGunMesh();
		else if(weapon_type==WEAPON_TYPE_REVOLVER)
			return buildRevolverMesh();
		else if(weapon_type==WEAPON_TYPE_BASEBALL_BAT)
			return buildBaseballBatMesh();
		else if(weapon_type==WEAPON_TYPE_SUBMACHINEGUN)
			return buildSubmachineGunMesh();
		else
			return buildGunMesh();
		
		

	}
	






	private PolygonMesh buildGunMesh() {
		points=new Vector();
		points.setSize(300);

		polyData=new Vector();
		
		
		n=0;
		
		double bax0=0;
		double bay0=breech_length+rear_overhang;
		double baz0=breech_height-2*barrel_radius;

		//barrel:
		
		int fnx=5;
		int fny=5;
		int fnz=5;
		
		BPoint[][][] p=new BPoint[fnx][fny][fnz];
		
		Segments f0=new Segments(bax0,breech_width,bay0,barrel_lenght,baz0,barrel_radius*2);

		p[0][0][0]=addBPoint(-0.5,0,0,f0);
		p[1][0][0]=addBPoint(-0.25,0.0,0,f0);
		p[2][0][0]=addBPoint(0.0,0.0,0,f0);
		p[3][0][0]=addBPoint(0.25,0.0,0,f0);
		p[4][0][0]=addBPoint(0.5,0.0,0,f0);
		p[0][1][0]=addBPoint(-0.5,0.25,0,f0);
		p[1][1][0]=addBPoint(-0.25,0.25,0,f0);
		p[2][1][0]=addBPoint(0.0,0.25,0,f0);
		p[3][1][0]=addBPoint(0.25,0.25,0,f0);
		p[4][1][0]=addBPoint(0.5,0.25,0,f0);
		p[0][2][0]=addBPoint(-0.5,0.5,0,f0);
		p[1][2][0]=addBPoint(-0.25,0.5,0,f0);
		p[2][2][0]=addBPoint(0.0,0.5,0,f0);
		p[3][2][0]=addBPoint(0.25,0.5,0,f0);
		p[4][2][0]=addBPoint(0.5,0.5,0,f0);
		p[0][3][0]=addBPoint(-0.5,0.75,0,f0);
		p[1][3][0]=addBPoint(-0.25,0.75,0,f0);
		p[2][3][0]=addBPoint(0.0,0.75,0,f0);
		p[3][3][0]=addBPoint(0.25,0.75,0,f0);
		p[4][3][0]=addBPoint(0.5,0.75,0,f0);
		p[0][4][0]=addBPoint(-0.5,1.0,0,f0);
		p[1][4][0]=addBPoint(-0.25,1.0,0,f0);
		p[2][4][0]=addBPoint(0.0,1.0,0,f0);
		p[3][4][0]=addBPoint(0.25,1.0,0,f0);
		p[4][4][0]=addBPoint(0.5,1.0,0,f0);
		
		
		p[0][0][1]=addBPoint(-0.5,0,0.25,f0);
		p[1][0][1]=addBPoint(-0.25,0.0,0.25,f0);
		p[2][0][1]=addBPoint(0.0,0.0,0.25,f0);
		p[3][0][1]=addBPoint(0.25,0.0,0.25,f0);
		p[4][0][1]=addBPoint(0.5,0.0,0.25,f0);
		p[0][1][1]=addBPoint(-0.5,0.25,0.25,f0);	
		p[4][1][1]=addBPoint(0.5,0.25,0.25,f0);
		p[0][2][1]=addBPoint(-0.5,0.5,0.25,f0);	
		p[4][2][1]=addBPoint(0.5,0.5,0.25,f0);
		p[0][3][1]=addBPoint(-0.5,0.75,0.25,f0);	
		p[4][3][1]=addBPoint(0.5,0.75,0.25,f0);
		p[0][4][1]=addBPoint(-0.5,1.0,0.25,f0);
		p[1][4][1]=addBPoint(-0.25,1.0,0.25,f0);
		p[2][4][1]=addBPoint(0.0,1.0,0.25,f0);
		p[3][4][1]=addBPoint(0.25,1.0,0.25,f0);
		p[4][4][1]=addBPoint(0.5,1.0,0.25,f0);
		
		p[0][0][2]=addBPoint(-0.5,0,0.5,f0);
		p[1][0][2]=addBPoint(-0.25,0.0,0.5,f0);
		p[2][0][2]=addBPoint(0.0,0.0,0.5,f0);
		p[3][0][2]=addBPoint(0.25,0.0,0.5,f0);
		p[4][0][2]=addBPoint(0.5,0.0,0.5,f0);
		p[0][1][2]=addBPoint(-0.5,0.25,0.5,f0);	
		p[4][1][2]=addBPoint(0.5,0.25,0.5,f0);
		p[0][2][2]=addBPoint(-0.5,0.5,0.5,f0);	
		p[4][2][2]=addBPoint(0.5,0.5,0.5,f0);
		p[0][3][2]=addBPoint(-0.5,0.75,0.5,f0);	
		p[4][3][2]=addBPoint(0.5,0.75,0.5,f0);
		p[0][4][2]=addBPoint(-0.5,1.0,0.5,f0);
		p[1][4][2]=addBPoint(-0.25,1.0,0.5,f0);
		p[2][4][2]=addBPoint(0.0,1.0,0.5,f0);
		p[3][4][2]=addBPoint(0.25,1.0,0.5,f0);
		p[4][4][2]=addBPoint(0.5,1.0,0.5,f0);
		
		p[0][0][3]=addBPoint(-0.5,0,0.75,f0);
		p[1][0][3]=addBPoint(-0.25,0.0,0.75,f0);
		p[2][0][3]=addBPoint(0.0,0.0,0.75,f0);
		p[3][0][3]=addBPoint(0.25,0.0,0.75,f0);
		p[4][0][3]=addBPoint(0.5,0.0,0.75,f0);
		p[0][1][3]=addBPoint(-0.5,0.25,0.75,f0);	
		p[4][1][3]=addBPoint(0.5,0.25,0.75,f0);
		p[0][2][3]=addBPoint(-0.5,0.5,0.75,f0);	
		p[4][2][3]=addBPoint(0.5,0.5,0.75,f0);
		p[0][3][3]=addBPoint(-0.5,0.75,0.75,f0);	
		p[4][3][3]=addBPoint(0.5,0.75,0.75,f0);
		p[0][4][3]=addBPoint(-0.5,1.0,0.75,f0);
		p[1][4][3]=addBPoint(-0.25,1.0,0.75,f0);
		p[2][4][3]=addBPoint(0.0,1.0,0.75,f0);
		p[3][4][3]=addBPoint(0.25,1.0,0.75,f0);
		p[4][4][3]=addBPoint(0.5,1.0,0.75,f0);
		
		p[0][0][4]=addBPoint(-0.5,0.0,1.0,f0);	
		p[1][0][4]=addBPoint(-0.25,0.0,1.0,f0);	
		p[2][0][4]=addBPoint(0.0,0.0,1.0,f0);
		p[3][0][4]=addBPoint(0.25,0.0,1.0,f0);	
		p[4][0][4]=addBPoint(0.5,0.0,1.0,f0);		
		p[0][1][4]=addBPoint(-0.5,0.25,1.0,f0);	
		p[1][1][4]=addBPoint(-0.25,0.25,1.0,f0);
		p[2][1][4]=addBPoint(0.0,0.25,1.0,f0);
		p[3][1][4]=addBPoint(0.25,0.25,1.0,f0);
		p[4][1][4]=addBPoint(0.5,0.25,1.0,f0);
		p[0][2][4]=addBPoint(-0.5,0.5,1.0,f0);
		p[1][2][4]=addBPoint(-0.25,0.5,1.0,f0);	
		p[2][2][4]=addBPoint(0.0,0.5,1.0,f0);
		p[3][2][4]=addBPoint(0.25,0.5,1.0,f0);
		p[4][2][4]=addBPoint(0.5,0.5,1.0,f0);
		p[0][3][4]=addBPoint(-0.5,0.75,1.0,f0);	
		p[1][3][4]=addBPoint(-0.25,0.75,1.0,f0);
		p[2][3][4]=addBPoint(0.0,0.75,1.0,f0);
		p[3][3][4]=addBPoint(0.25,0.75,1.0,f0);
		p[4][3][4]=addBPoint(0.5,0.75,1.0,f0);
		p[0][4][4]=addBPoint(-0.5,1.0,1.0,f0);
		p[1][4][4]=addBPoint(-0.25,1.0,1.0,f0);	
		p[2][4][4]=addBPoint(0.0,1.0,1.0,f0);
		p[3][4][4]=addBPoint(0.25,1.0,1.0,f0);
		p[4][4][4]=addBPoint(0.5,1.0,1.0,f0);
		
		for (int i = 0; i < fnx-1; i++) {


			for (int j = 0; j < fny-1; j++) {

				for (int k = 0; k < fnz-1; k++) {




					if(i==0){

						LineData leftLD=addLine(p[i][j][k],p[i][j][k+1],p[i][j+1][k+1],p[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						LineData bottomLD=addLine(p[i][j][k],p[i][j+1][k],p[i+1][j+1][k],p[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==fnz-1){
						LineData topLD=addLine(p[i][j][k+1],p[i+1][j][k+1],p[i+1][j+1][k+1],p[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						LineData backLD=addLine(p[i][j][k],p[i+1][j][k],p[i+1][j][k+1],p[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==fny-1){
						LineData frontLD=addLine(p[i][j+1][k],p[i][j+1][k+1],p[i+1][j+1][k+1],p[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==fnx-1){

						LineData rightLD=addLine(p[i+1][j][k],p[i+1][j+1][k],p[i+1][j+1][k+1],p[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		//breech
		
		//main body
		
		int bnx=5;
		int bny=5;
		int bnz=2;
		
		BPoint[][][] breech=new BPoint[bnx][bny][bnz];
		
		double xc=0.0;
		
		Segments b0=new Segments(xc,breech_width,rear_overhang,breech_length,0,breech_height);

		breech[0][0][0]=addBPoint(-0.5,0,0,b0);
		breech[1][0][0]=addBPoint(-0.25,0.0,0,b0);
		breech[2][0][0]=addBPoint(0.0,0.0,0,b0);
		breech[3][0][0]=addBPoint(0.25,0.0,0,b0);
		breech[4][0][0]=addBPoint(0.5,0.0,0,b0);
		breech[0][1][0]=addBPoint(-0.5,0.25,0,b0);
		breech[1][1][0]=addBPoint(-0.25,0.25,0,b0);
		breech[2][1][0]=addBPoint(0.0,0.25,0,b0);
		breech[3][1][0]=addBPoint(0.25,0.25,0,b0);
		breech[4][1][0]=addBPoint(0.5,0.25,0,b0);
		breech[0][2][0]=addBPoint(-0.5,0.5,0,b0);
		breech[1][2][0]=addBPoint(-0.25,0.5,0,b0);
		breech[2][2][0]=addBPoint(0.0,0.5,0,b0);
		breech[3][2][0]=addBPoint(0.25,0.5,0,b0);
		breech[4][2][0]=addBPoint(0.5,0.5,0,b0);
		breech[0][3][0]=addBPoint(-0.5,0.75,0,b0);
		breech[1][3][0]=addBPoint(-0.25,0.75,0,b0);
		breech[2][3][0]=addBPoint(0.0,0.75,0,b0);
		breech[3][3][0]=addBPoint(0.25,0.75,0,b0);
		breech[4][3][0]=addBPoint(0.5,0.75,0,b0);
		breech[0][4][0]=addBPoint(-0.5,1.0,0,b0);
		breech[1][4][0]=addBPoint(-0.25,1.0,0,b0);
		breech[2][4][0]=addBPoint(0.0,1.0,0,b0);
		breech[3][4][0]=addBPoint(0.25,1.0,0,b0);
		breech[4][4][0]=addBPoint(0.5,1.0,0,b0);
		
		breech[0][0][1]=addBPoint(-0.5,0.0,1.0,b0);	
		breech[1][0][1]=addBPoint(-0.25,0.0,1.0,b0);	
		breech[2][0][1]=addBPoint(0.0,0.0,1.0,b0);
		breech[3][0][1]=addBPoint(0.25,0.0,1.0,b0);	
		breech[4][0][1]=addBPoint(0.5,0.0,1.0,b0);		
		breech[0][1][1]=addBPoint(-0.5,0.25,1.0,b0);	
		breech[1][1][1]=addBPoint(-0.25,0.25,1.0,b0);
		breech[2][1][1]=addBPoint(0.0,0.25,1.0,b0);
		breech[3][1][1]=addBPoint(0.25,0.25,1.0,b0);
		breech[4][1][1]=addBPoint(0.5,0.25,1.0,b0);
		breech[0][2][1]=addBPoint(-0.5,0.5,1.0,b0);
		breech[1][2][1]=addBPoint(-0.25,0.5,1.0,b0);	
		breech[2][2][1]=addBPoint(0.0,0.5,1.0,b0);
		breech[3][2][1]=addBPoint(0.25,0.5,1.0,b0);
		breech[4][2][1]=addBPoint(0.5,0.5,1.0,b0);
		breech[0][3][1]=addBPoint(-0.5,0.75,1.0,b0);	
		breech[1][3][1]=addBPoint(-0.25,0.75,1.0,b0);
		breech[2][3][1]=addBPoint(0.0,0.75,1.0,b0);
		breech[3][3][1]=addBPoint(0.25,0.75,1.0,b0);
		breech[4][3][1]=addBPoint(0.5,0.75,1.0,b0);
		breech[0][4][1]=addBPoint(-0.5,1.0,1.0,b0);
		breech[1][4][1]=addBPoint(-0.25,1.0,1.0,b0);	
		breech[2][4][1]=addBPoint(0.0,1.0,1.0,b0);
		breech[3][4][1]=addBPoint(0.25,1.0,1.0,b0);
		breech[4][4][1]=addBPoint(0.5,1.0,1.0,b0);
		
		for (int i = 0; i < bnx-1; i++) {


			for (int j = 0; j < bny-1; j++) {

				for (int k = 0; k < bnz-1; k++) {




					if(i==0){

						addLine(breech[i][j][k],breech[i][j][k+1],breech[i][j+1][k+1],breech[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(breech[i][j][k],breech[i][j+1][k],breech[i+1][j+1][k],breech[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==bnz-1){
						addLine(breech[i][j][k+1],breech[i+1][j][k+1],breech[i+1][j+1][k+1],breech[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(breech[i][j][k],breech[i+1][j][k],breech[i+1][j][k+1],breech[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==bny-1){
						addLine(breech[i][j+1][k],breech[i][j+1][k+1],breech[i+1][j+1][k+1],breech[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==bnx-1){

						addLine(breech[i+1][j][k],breech[i+1][j+1][k],breech[i+1][j+1][k+1],breech[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		//butt
		
		int pnx=5;
		int pny=5;
		int pnz=2;
		
		BPoint[][][] butt=new BPoint[pnx][pny][pnz];
		
		
		
		Segments p0=new Segments(xc,butt_width,0,butt_end_length,-butt_height,butt_height);

		butt[0][0][0]=addBPoint(-0.5,0,0,p0);
		butt[1][0][0]=addBPoint(-0.25,0.0,0,p0);
		butt[2][0][0]=addBPoint(0.0,0.0,0,p0);
		butt[3][0][0]=addBPoint(0.25,0.0,0,p0);
		butt[4][0][0]=addBPoint(0.5,0.0,0,p0);
		butt[0][1][0]=addBPoint(-0.5,0.25,0,p0);
		butt[1][1][0]=addBPoint(-0.25,0.25,0,p0);
		butt[2][1][0]=addBPoint(0.0,0.25,0,p0);
		butt[3][1][0]=addBPoint(0.25,0.25,0,p0);
		butt[4][1][0]=addBPoint(0.5,0.25,0,p0);
		butt[0][2][0]=addBPoint(-0.5,0.5,0,p0);
		butt[1][2][0]=addBPoint(-0.25,0.5,0,p0);
		butt[2][2][0]=addBPoint(0.0,0.5,0,p0);
		butt[3][2][0]=addBPoint(0.25,0.5,0,p0);
		butt[4][2][0]=addBPoint(0.5,0.5,0,p0);
		butt[0][3][0]=addBPoint(-0.5,0.75,0,p0);
		butt[1][3][0]=addBPoint(-0.25,0.75,0,p0);
		butt[2][3][0]=addBPoint(0.0,0.75,0,p0);
		butt[3][3][0]=addBPoint(0.25,0.75,0,p0);
		butt[4][3][0]=addBPoint(0.5,0.75,0,p0);
		butt[0][4][0]=addBPoint(-0.5,1.0,0,p0);
		butt[1][4][0]=addBPoint(-0.25,1.0,0,p0);
		butt[2][4][0]=addBPoint(0.0,1.0,0,p0);
		butt[3][4][0]=addBPoint(0.25,1.0,0,p0);
		butt[4][4][0]=addBPoint(0.5,1.0,0,p0);
		
			
		Segments p1=new Segments(xc,butt_width,rear_overhang,butt_length,-butt_height,butt_height);
		
		butt[0][0][1]=addBPoint(-0.5,0.0,1.0,p1);	
		butt[1][0][1]=addBPoint(-0.25,0.0,1.0,p1);	
		butt[2][0][1]=addBPoint(0.0,0.0,1.0,p1);
		butt[3][0][1]=addBPoint(0.25,0.0,1.0,p1);	
		butt[4][0][1]=addBPoint(0.5,0.0,1.0,p1);		
		butt[0][1][1]=addBPoint(-0.5,0.25,1.0,p1);	
		butt[1][1][1]=addBPoint(-0.25,0.25,1.0,p1);
		butt[2][1][1]=addBPoint(0.0,0.25,1.0,p1);
		butt[3][1][1]=addBPoint(0.25,0.25,1.0,p1);
		butt[4][1][1]=addBPoint(0.5,0.25,1.0,p1);
		butt[0][2][1]=addBPoint(-0.5,0.5,1.0,p1);
		butt[1][2][1]=addBPoint(-0.25,0.5,1.0,p1);	
		butt[2][2][1]=addBPoint(0.0,0.5,1.0,p1);
		butt[3][2][1]=addBPoint(0.25,0.5,1.0,p1);
		butt[4][2][1]=addBPoint(0.5,0.5,1.0,p1);
		butt[0][3][1]=addBPoint(-0.5,0.75,1.0,p1);	
		butt[1][3][1]=addBPoint(-0.25,0.75,1.0,p1);
		butt[2][3][1]=addBPoint(0.0,0.75,1.0,p1);
		butt[3][3][1]=addBPoint(0.25,0.75,1.0,p1);
		butt[4][3][1]=addBPoint(0.5,0.75,1.0,p1);
		butt[0][4][1]=addBPoint(-0.5,1.0,1.0,p1);
		butt[1][4][1]=addBPoint(-0.25,1.0,1.0,p1);	
		butt[2][4][1]=addBPoint(0.0,1.0,1.0,p1);
		butt[3][4][1]=addBPoint(0.25,1.0,1.0,p1);
		butt[4][4][1]=addBPoint(0.5,1.0,1.0,p1);
		
		for (int i = 0; i < pnx-1; i++) {


			for (int j = 0; j < pny-1; j++) {

				for (int k = 0; k < pnz-1; k++) {




					if(i==0){

						addLine(butt[i][j][k],butt[i][j][k+1],butt[i][j+1][k+1],butt[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(butt[i][j][k],butt[i][j+1][k],butt[i+1][j+1][k],butt[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==pnz-1){
						addLine(butt[i][j][k+1],butt[i+1][j][k+1],butt[i+1][j+1][k+1],butt[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(butt[i][j][k],butt[i+1][j][k],butt[i+1][j][k+1],butt[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==pny-1){
						addLine(butt[i][j+1][k],butt[i][j+1][k+1],butt[i+1][j+1][k+1],butt[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==pnx-1){

						addLine(butt[i+1][j][k],butt[i+1][j+1][k],butt[i+1][j+1][k+1],butt[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		////trigger
		
		Segments tr0=new Segments(xc,trigger_width,rear_overhang+breech_length,trigger_length,0,trigger_height);
				
		
		Prism triggerFront=new Prism(4);	
		
		double dy1=10/trigger_length;
				
		triggerFront.lowerBase[0]=addBPoint(-0.5,1.0-dy1,-1,tr0);
		triggerFront.lowerBase[1]=addBPoint(0.5,1.0-dy1,-1,tr0);
		triggerFront.lowerBase[2]=addBPoint(0.5,1.0,-1,tr0);
		triggerFront.lowerBase[3]=addBPoint(-0.5,1.0,-1,tr0);
		
		triggerFront.upperBase[0]=addBPoint(-0.5,1.0-dy1,0.0,tr0);
		triggerFront.upperBase[1]=addBPoint(0.5,1.0-dy1,0.0,tr0);
		triggerFront.upperBase[2]=addBPoint(0.5,1.0,0.0,tr0);
		triggerFront.upperBase[3]=addBPoint(-0.5,1.0,0.0,tr0);
		
		addPrism(triggerFront);
		
		Prism triggerBottom=new Prism(4);			
		
		double dz=10/trigger_height;
		double dy0=(trigger_height*rear_overhang/butt_height)/trigger_length;
		
		triggerBottom.lowerBase[0]=addBPoint(-0.5,-dy0,-1,tr0);
		triggerBottom.lowerBase[1]=addBPoint(0.5,-dy0,-1,tr0);
		triggerBottom.lowerBase[2]=addBPoint(0.5,1.0-dy1,-1,tr0);
		triggerBottom.lowerBase[3]=addBPoint(-0.5,1.0-dy1,-1,tr0);
		
		triggerBottom.upperBase[0]=addBPoint(-0.5,-dy0,-1.0+dz,tr0);
		triggerBottom.upperBase[1]=addBPoint(0.5,-dy0,-1.0+dz,tr0);
		triggerBottom.upperBase[2]=addBPoint(0.5,1.0-dy1,-1.0+dz,tr0);
		triggerBottom.upperBase[3]=addBPoint(-0.5,1.0-dy1,-1.0+dz,tr0);
	
		addPrism(triggerBottom);
		
		/////////

		//translatePoints(points,nw_x,nw_y);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;

	}

	private PolygonMesh buildShotGunMesh() {
		points=new Vector();
		points.setSize(200);

		polyData=new Vector();
		
		
		n=0;
		
		double bax0=0;
		double bay0=breech_length;
		double baz0=breech_height-barrel_radius;

		//barrel:
		
		addCylinder(bax0,bay0,baz0,barrel_radius,barrel_lenght,barrel_meridians);
		
		//breech
		
		int bnx=5;
		int bny=5;
		int bnz=2;
		
		BPoint[][][] breech=new BPoint[bnx][bny][bnz];
		
		double xc=0.0;
		
		Segments b0=new Segments(xc,breech_width,0,breech_length,0,breech_height);
		
		//double wr=(breech_height-2*barrel_radius)/breech_height;

		breech[0][0][0]=addBPoint(-0.5,0,0,b0);
		breech[1][0][0]=addBPoint(-0.25,0.0,0,b0);
		breech[2][0][0]=addBPoint(0.0,0.0,0,b0);
		breech[3][0][0]=addBPoint(0.25,0.0,0,b0);
		breech[4][0][0]=addBPoint(0.5,0.0,0,b0);
		breech[0][1][0]=addBPoint(-0.5,0.25,0,b0);
		breech[1][1][0]=addBPoint(-0.25,0.25,0,b0);
		breech[2][1][0]=addBPoint(0.0,0.25,0,b0);
		breech[3][1][0]=addBPoint(0.25,0.25,0,b0);
		breech[4][1][0]=addBPoint(0.5,0.25,0,b0);
		breech[0][2][0]=addBPoint(-0.5,0.5,0,b0);
		breech[1][2][0]=addBPoint(-0.25,0.5,0,b0);
		breech[2][2][0]=addBPoint(0.0,0.5,0,b0);
		breech[3][2][0]=addBPoint(0.25,0.5,0,b0);
		breech[4][2][0]=addBPoint(0.5,0.5,0,b0);
		breech[0][3][0]=addBPoint(-0.5,0.75,0,b0);
		breech[1][3][0]=addBPoint(-0.25,0.75,0,b0);
		breech[2][3][0]=addBPoint(0.0,0.75,0,b0);
		breech[3][3][0]=addBPoint(0.25,0.75,0,b0);
		breech[4][3][0]=addBPoint(0.5,0.75,0,b0);
		breech[0][4][0]=addBPoint(-0.5,1.0,0,b0);
		breech[1][4][0]=addBPoint(-0.25,1.0,0,b0);
		breech[2][4][0]=addBPoint(0.0,1.0,0,b0);
		breech[3][4][0]=addBPoint(0.25,1.0,0,b0);
		breech[4][4][0]=addBPoint(0.5,1.0,0,b0);

		breech[0][0][1]=addBPoint(-0.5,0.0,1.0,b0);	
		breech[1][0][1]=addBPoint(-0.25,0.0,1.0,b0);	
		breech[2][0][1]=addBPoint(0.0,0.0,1.0,b0);
		breech[3][0][1]=addBPoint(0.25,0.0,1.0,b0);	
		breech[4][0][1]=addBPoint(0.5,0.0,1.0,b0);		
		breech[0][1][1]=addBPoint(-0.5,0.25,1.0,b0);	
		breech[1][1][1]=addBPoint(-0.25,0.25,1.0,b0);
		breech[2][1][1]=addBPoint(0.0,0.25,1.0,b0);
		breech[3][1][1]=addBPoint(0.25,0.25,1.0,b0);
		breech[4][1][1]=addBPoint(0.5,0.25,1.0,b0);
		breech[0][2][1]=addBPoint(-0.5,0.5,1.0,b0);
		breech[1][2][1]=addBPoint(-0.25,0.5,1.0,b0);	
		breech[2][2][1]=addBPoint(0.0,0.5,1.0,b0);
		breech[3][2][1]=addBPoint(0.25,0.5,1.0,b0);
		breech[4][2][1]=addBPoint(0.5,0.5,1.0,b0);
		breech[0][3][1]=addBPoint(-0.5,0.75,1.0,b0);	
		breech[1][3][1]=addBPoint(-0.25,0.75,1.0,b0);
		breech[2][3][1]=addBPoint(0.0,0.75,1.0,b0);
		breech[3][3][1]=addBPoint(0.25,0.75,1.0,b0);
		breech[4][3][1]=addBPoint(0.5,0.75,1.0,b0);
		breech[0][4][1]=addBPoint(-0.5,1.0,1.0,b0);
		breech[1][4][1]=addBPoint(-0.25,1.0,1.0,b0);	
		breech[2][4][1]=addBPoint(0.0,1.0,1.0,b0);
		breech[3][4][1]=addBPoint(0.25,1.0,1.0,b0);
		breech[4][4][1]=addBPoint(0.5,1.0,1.0,b0);
		
		for (int i = 0; i < bnx-1; i++) {


			for (int j = 0; j < bny-1; j++) {

				for (int k = 0; k < bnz-1; k++) {




					if(i==0){

						addLine(breech[i][j][k],breech[i][j][k+1],breech[i][j+1][k+1],breech[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(breech[i][j][k],breech[i][j+1][k],breech[i+1][j+1][k],breech[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==bnz-1){
						addLine(breech[i][j][k+1],breech[i+1][j][k+1],breech[i+1][j+1][k+1],breech[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(breech[i][j][k],breech[i+1][j][k],breech[i+1][j][k+1],breech[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==bny-1){
						addLine(breech[i][j+1][k],breech[i][j+1][k+1],breech[i+1][j+1][k+1],breech[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==bnx-1){

						addLine(breech[i+1][j][k],breech[i+1][j+1][k],breech[i+1][j+1][k+1],breech[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		
		//forearm
		
		int fanx=5;
		int fany=5;
		int fanz=2;
		
		BPoint[][][] forearm=new BPoint[fanx][fany][fanz];

		
		Segments fa0=new Segments(xc,forearm_width,breech_length,forearm_length,0,forearm_height);
		
		//double wr=(breech_height-2*barrel_radius)/breech_height;

		forearm[0][0][0]=addBPoint(-0.5,0,0,fa0);
		forearm[1][0][0]=addBPoint(-0.25,0.0,0,fa0);
		forearm[2][0][0]=addBPoint(0.0,0.0,0,fa0);
		forearm[3][0][0]=addBPoint(0.25,0.0,0,fa0);
		forearm[4][0][0]=addBPoint(0.5,0.0,0,fa0);
		forearm[0][1][0]=addBPoint(-0.5,0.25,0,fa0);
		forearm[1][1][0]=addBPoint(-0.25,0.25,0,fa0);
		forearm[2][1][0]=addBPoint(0.0,0.25,0,fa0);
		forearm[3][1][0]=addBPoint(0.25,0.25,0,fa0);
		forearm[4][1][0]=addBPoint(0.5,0.25,0,fa0);
		forearm[0][2][0]=addBPoint(-0.5,0.5,0,fa0);
		forearm[1][2][0]=addBPoint(-0.25,0.5,0,fa0);
		forearm[2][2][0]=addBPoint(0.0,0.5,0,fa0);
		forearm[3][2][0]=addBPoint(0.25,0.5,0,fa0);
		forearm[4][2][0]=addBPoint(0.5,0.5,0,fa0);
		forearm[0][3][0]=addBPoint(-0.5,0.75,0,fa0);
		forearm[1][3][0]=addBPoint(-0.25,0.75,0,fa0);
		forearm[2][3][0]=addBPoint(0.0,0.75,0,fa0);
		forearm[3][3][0]=addBPoint(0.25,0.75,0,fa0);
		forearm[4][3][0]=addBPoint(0.5,0.75,0,fa0);
		forearm[0][4][0]=addBPoint(-0.5,1.0,0,fa0);
		forearm[1][4][0]=addBPoint(-0.25,1.0,0,fa0);
		forearm[2][4][0]=addBPoint(0.0,1.0,0,fa0);
		forearm[3][4][0]=addBPoint(0.25,1.0,0,fa0);
		forearm[4][4][0]=addBPoint(0.5,1.0,0,fa0);

		forearm[0][0][1]=addBPoint(-0.5,0.0,1.0,fa0);	
		forearm[1][0][1]=addBPoint(-0.25,0.0,1.0,fa0);	
		forearm[2][0][1]=addBPoint(0.0,0.0,1.0,fa0);
		forearm[3][0][1]=addBPoint(0.25,0.0,1.0,fa0);	
		forearm[4][0][1]=addBPoint(0.5,0.0,1.0,fa0);		
		forearm[0][1][1]=addBPoint(-0.5,0.25,1.0,fa0);	
		forearm[1][1][1]=addBPoint(-0.25,0.25,1.0,fa0);
		forearm[2][1][1]=addBPoint(0.0,0.25,1.0,fa0);
		forearm[3][1][1]=addBPoint(0.25,0.25,1.0,fa0);
		forearm[4][1][1]=addBPoint(0.5,0.25,1.0,fa0);
		forearm[0][2][1]=addBPoint(-0.5,0.5,1.0,fa0);
		forearm[1][2][1]=addBPoint(-0.25,0.5,1.0,fa0);	
		forearm[2][2][1]=addBPoint(0.0,0.5,1.0,fa0);
		forearm[3][2][1]=addBPoint(0.25,0.5,1.0,fa0);
		forearm[4][2][1]=addBPoint(0.5,0.5,1.0,fa0);
		forearm[0][3][1]=addBPoint(-0.5,0.75,1.0,fa0);	
		forearm[1][3][1]=addBPoint(-0.25,0.75,1.0,fa0);
		forearm[2][3][1]=addBPoint(0.0,0.75,1.0,fa0);
		forearm[3][3][1]=addBPoint(0.25,0.75,1.0,fa0);
		forearm[4][3][1]=addBPoint(0.5,0.75,1.0,fa0);
		forearm[0][4][1]=addBPoint(-0.5,1.0,1.0,fa0);
		forearm[1][4][1]=addBPoint(-0.25,1.0,1.0,fa0);	
		forearm[2][4][1]=addBPoint(0.0,1.0,1.0,fa0);
		forearm[3][4][1]=addBPoint(0.25,1.0,1.0,fa0);
		forearm[4][4][1]=addBPoint(0.5,1.0,1.0,fa0);
		
		for (int i = 0; i < fanx-1; i++) {


			for (int j = 0; j < fany-1; j++) {

				for (int k = 0; k < fanz-1; k++) {




					if(i==0){

						addLine(forearm[i][j][k],forearm[i][j][k+1],forearm[i][j+1][k+1],forearm[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(forearm[i][j][k],forearm[i][j+1][k],forearm[i+1][j+1][k],forearm[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==fanz-1){
						addLine(forearm[i][j][k+1],forearm[i+1][j][k+1],forearm[i+1][j+1][k+1],forearm[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(forearm[i][j][k],forearm[i+1][j][k],forearm[i+1][j][k+1],forearm[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==fany-1){
						addLine(forearm[i][j+1][k],forearm[i][j+1][k+1],forearm[i+1][j+1][k+1],forearm[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==fanx-1){

						addLine(forearm[i+1][j][k],forearm[i+1][j+1][k],forearm[i+1][j+1][k+1],forearm[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		//butt
		
		int pnx=5;
		int pny=2;
		int pnz=2;
		
		BPoint[][][] butt=new BPoint[pnx][pny][pnz];
		
		double zEnd=breech_height-butt_end_height-rear_overhang;
	
		Segments p0=new Segments(xc,butt_width,-butt_length,butt_length,zEnd,butt_end_height);	
		
		butt[0][0][0]=addBPoint(-0.5,0,0,p0);
		butt[1][0][0]=addBPoint(-0.25,0.0,0,p0);
		butt[2][0][0]=addBPoint(0.0,0.0,0,p0);
		butt[3][0][0]=addBPoint(0.25,0.0,0,p0);
		butt[4][0][0]=addBPoint(0.5,0.0,0,p0);
		butt[0][0][1]=addBPoint(-0.5,0.0,1.0,p0);	
		butt[1][0][1]=addBPoint(-0.25,0.0,1.0,p0);	
		butt[2][0][1]=addBPoint(0.0,0.0,1.0,p0);
		butt[3][0][1]=addBPoint(0.25,0.0,1.0,p0);	
		butt[4][0][1]=addBPoint(0.5,0.0,1.0,p0);	
		
		Segments p1=new Segments(xc,butt_width,-butt_length,butt_length,0,butt_height);


		butt[0][1][0]=addBPoint(-0.5,1.0,0,p1);
		butt[1][1][0]=addBPoint(-0.25,1.0,0,p1);
		butt[2][1][0]=addBPoint(0.0,1.0,0,p1);
		butt[3][1][0]=addBPoint(0.25,1.0,0,p1);
		butt[4][1][0]=addBPoint(0.5,1.0,0,p1);
		butt[0][1][1]=addBPoint(-0.5,1.0,1.0,p1);
		butt[1][1][1]=addBPoint(-0.25,1.0,1.0,p1);	
		butt[2][1][1]=addBPoint(0.0,1.0,1.0,p1);
		butt[3][1][1]=addBPoint(0.25,1.0,1.0,p1);
		butt[4][1][1]=addBPoint(0.5,1.0,1.0,p1);
		
		for (int i = 0; i < pnx-1; i++) {


			for (int j = 0; j < pny-1; j++) {

				for (int k = 0; k < pnz-1; k++) {




					if(i==0){

						addLine(butt[i][j][k],butt[i][j][k+1],butt[i][j+1][k+1],butt[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(butt[i][j][k],butt[i][j+1][k],butt[i+1][j+1][k],butt[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==pnz-1){
						addLine(butt[i][j][k+1],butt[i+1][j][k+1],butt[i+1][j+1][k+1],butt[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(butt[i][j][k],butt[i+1][j][k],butt[i+1][j][k+1],butt[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==pny-1){
						addLine(butt[i][j+1][k],butt[i][j+1][k+1],butt[i+1][j+1][k+1],butt[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==pnx-1){

						addLine(butt[i+1][j][k],butt[i+1][j+1][k],butt[i+1][j+1][k+1],butt[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		////trigger
		
			Segments tr0=new Segments(xc,trigger_width,0,trigger_length,0,trigger_height);
					
			
			Prism triggerFront=new Prism(4);	
			
			double dy1=2/trigger_length;
					
			triggerFront.lowerBase[0]=addBPoint(-0.5,1.0-dy1,-1,tr0);
			triggerFront.lowerBase[1]=addBPoint(0.5,1.0-dy1,-1,tr0);
			triggerFront.lowerBase[2]=addBPoint(0.5,1.0,-1,tr0);
			triggerFront.lowerBase[3]=addBPoint(-0.5,1.0,-1,tr0);
			
			triggerFront.upperBase[0]=addBPoint(-0.5,1.0-dy1,0.0,tr0);
			triggerFront.upperBase[1]=addBPoint(0.5,1.0-dy1,0.0,tr0);
			triggerFront.upperBase[2]=addBPoint(0.5,1.0,0.0,tr0);
			triggerFront.upperBase[3]=addBPoint(-0.5,1.0,0.0,tr0);
			
			addPrism(triggerFront);
			
			Prism triggerBottom=new Prism(4);			
			
			double dz=2/trigger_height;
			double dy0=0;//double dy0=(-trigger_height*butt_length/zEnd)/trigger_length;
			
			triggerBottom.lowerBase[0]=addBPoint(-0.5,-dy0,-1,tr0);
			triggerBottom.lowerBase[1]=addBPoint(0.5,-dy0,-1,tr0);
			triggerBottom.lowerBase[2]=addBPoint(0.5,1.0-dy1,-1,tr0);
			triggerBottom.lowerBase[3]=addBPoint(-0.5,1.0-dy1,-1,tr0);
			
			triggerBottom.upperBase[0]=addBPoint(-0.5,-dy0,-1.0+dz,tr0);
			triggerBottom.upperBase[1]=addBPoint(0.5,-dy0,-1.0+dz,tr0);
			triggerBottom.upperBase[2]=addBPoint(0.5,1.0-dy1,-1.0+dz,tr0);
			triggerBottom.upperBase[3]=addBPoint(-0.5,1.0-dy1,-1.0+dz,tr0);
		
			addPrism(triggerBottom);
			
			/////////
		
		/////////

		//translatePoints(points,nw_x,nw_y);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;

	}


	private PolygonMesh buildDoubleBarrelGunMesh() {
		points=new Vector();
		points.setSize(200);

		polyData=new Vector();
		
		
		n=0;
		
		double barx=-barrel_radius;
		double balx=+barrel_radius;
		double bay0=breech_length;
		double baz0=breech_height-barrel_radius;

		//barrels:
		
		addCylinder(barx,bay0,baz0,barrel_radius,barrel_lenght,barrel_meridians);
		addCylinder(balx,bay0,baz0,barrel_radius,barrel_lenght,barrel_meridians);
		
		//breech
		
		int bnx=5;
		int bny=5;
		int bnz=2;
		
		BPoint[][][] breech=new BPoint[bnx][bny][bnz];
		
		double xc=0.0;
		
		Segments b0=new Segments(xc,breech_width,0,breech_length,0,breech_height);
		
		//double wr=(breech_height-2*barrel_radius)/breech_height;

		breech[0][0][0]=addBPoint(-0.5,0,0,b0);
		breech[1][0][0]=addBPoint(-0.25,0.0,0,b0);
		breech[2][0][0]=addBPoint(0.0,0.0,0,b0);
		breech[3][0][0]=addBPoint(0.25,0.0,0,b0);
		breech[4][0][0]=addBPoint(0.5,0.0,0,b0);
		breech[0][1][0]=addBPoint(-0.5,0.25,0,b0);
		breech[1][1][0]=addBPoint(-0.25,0.25,0,b0);
		breech[2][1][0]=addBPoint(0.0,0.25,0,b0);
		breech[3][1][0]=addBPoint(0.25,0.25,0,b0);
		breech[4][1][0]=addBPoint(0.5,0.25,0,b0);
		breech[0][2][0]=addBPoint(-0.5,0.5,0,b0);
		breech[1][2][0]=addBPoint(-0.25,0.5,0,b0);
		breech[2][2][0]=addBPoint(0.0,0.5,0,b0);
		breech[3][2][0]=addBPoint(0.25,0.5,0,b0);
		breech[4][2][0]=addBPoint(0.5,0.5,0,b0);
		breech[0][3][0]=addBPoint(-0.5,0.75,0,b0);
		breech[1][3][0]=addBPoint(-0.25,0.75,0,b0);
		breech[2][3][0]=addBPoint(0.0,0.75,0,b0);
		breech[3][3][0]=addBPoint(0.25,0.75,0,b0);
		breech[4][3][0]=addBPoint(0.5,0.75,0,b0);
		breech[0][4][0]=addBPoint(-0.5,1.0,0,b0);
		breech[1][4][0]=addBPoint(-0.25,1.0,0,b0);
		breech[2][4][0]=addBPoint(0.0,1.0,0,b0);
		breech[3][4][0]=addBPoint(0.25,1.0,0,b0);
		breech[4][4][0]=addBPoint(0.5,1.0,0,b0);

		breech[0][0][1]=addBPoint(-0.5,0.0,1.0,b0);	
		breech[1][0][1]=addBPoint(-0.25,0.0,1.0,b0);	
		breech[2][0][1]=addBPoint(0.0,0.0,1.0,b0);
		breech[3][0][1]=addBPoint(0.25,0.0,1.0,b0);	
		breech[4][0][1]=addBPoint(0.5,0.0,1.0,b0);		
		breech[0][1][1]=addBPoint(-0.5,0.25,1.0,b0);	
		breech[1][1][1]=addBPoint(-0.25,0.25,1.0,b0);
		breech[2][1][1]=addBPoint(0.0,0.25,1.0,b0);
		breech[3][1][1]=addBPoint(0.25,0.25,1.0,b0);
		breech[4][1][1]=addBPoint(0.5,0.25,1.0,b0);
		breech[0][2][1]=addBPoint(-0.5,0.5,1.0,b0);
		breech[1][2][1]=addBPoint(-0.25,0.5,1.0,b0);	
		breech[2][2][1]=addBPoint(0.0,0.5,1.0,b0);
		breech[3][2][1]=addBPoint(0.25,0.5,1.0,b0);
		breech[4][2][1]=addBPoint(0.5,0.5,1.0,b0);
		breech[0][3][1]=addBPoint(-0.5,0.75,1.0,b0);	
		breech[1][3][1]=addBPoint(-0.25,0.75,1.0,b0);
		breech[2][3][1]=addBPoint(0.0,0.75,1.0,b0);
		breech[3][3][1]=addBPoint(0.25,0.75,1.0,b0);
		breech[4][3][1]=addBPoint(0.5,0.75,1.0,b0);
		breech[0][4][1]=addBPoint(-0.5,1.0,1.0,b0);
		breech[1][4][1]=addBPoint(-0.25,1.0,1.0,b0);	
		breech[2][4][1]=addBPoint(0.0,1.0,1.0,b0);
		breech[3][4][1]=addBPoint(0.25,1.0,1.0,b0);
		breech[4][4][1]=addBPoint(0.5,1.0,1.0,b0);
		
		for (int i = 0; i < bnx-1; i++) {


			for (int j = 0; j < bny-1; j++) {

				for (int k = 0; k < bnz-1; k++) {




					if(i==0){

						addLine(breech[i][j][k],breech[i][j][k+1],breech[i][j+1][k+1],breech[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(breech[i][j][k],breech[i][j+1][k],breech[i+1][j+1][k],breech[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==bnz-1){
						addLine(breech[i][j][k+1],breech[i+1][j][k+1],breech[i+1][j+1][k+1],breech[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(breech[i][j][k],breech[i+1][j][k],breech[i+1][j][k+1],breech[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==bny-1){
						addLine(breech[i][j+1][k],breech[i][j+1][k+1],breech[i+1][j+1][k+1],breech[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==bnx-1){

						addLine(breech[i+1][j][k],breech[i+1][j+1][k],breech[i+1][j+1][k+1],breech[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		
		//forearm
		
		int fanx=5;
		int fany=5;
		int fanz=2;
		
		BPoint[][][] forearm=new BPoint[fanx][fany][fanz];

		
		Segments fa0=new Segments(xc,forearm_width,breech_length,forearm_length,0,forearm_height);
		
		//double wr=(breech_height-2*barrel_radius)/breech_height;

		forearm[0][0][0]=addBPoint(-0.5,0,0,fa0);
		forearm[1][0][0]=addBPoint(-0.25,0.0,0,fa0);
		forearm[2][0][0]=addBPoint(0.0,0.0,0,fa0);
		forearm[3][0][0]=addBPoint(0.25,0.0,0,fa0);
		forearm[4][0][0]=addBPoint(0.5,0.0,0,fa0);
		forearm[0][1][0]=addBPoint(-0.5,0.25,0,fa0);
		forearm[1][1][0]=addBPoint(-0.25,0.25,0,fa0);
		forearm[2][1][0]=addBPoint(0.0,0.25,0,fa0);
		forearm[3][1][0]=addBPoint(0.25,0.25,0,fa0);
		forearm[4][1][0]=addBPoint(0.5,0.25,0,fa0);
		forearm[0][2][0]=addBPoint(-0.5,0.5,0,fa0);
		forearm[1][2][0]=addBPoint(-0.25,0.5,0,fa0);
		forearm[2][2][0]=addBPoint(0.0,0.5,0,fa0);
		forearm[3][2][0]=addBPoint(0.25,0.5,0,fa0);
		forearm[4][2][0]=addBPoint(0.5,0.5,0,fa0);
		forearm[0][3][0]=addBPoint(-0.5,0.75,0,fa0);
		forearm[1][3][0]=addBPoint(-0.25,0.75,0,fa0);
		forearm[2][3][0]=addBPoint(0.0,0.75,0,fa0);
		forearm[3][3][0]=addBPoint(0.25,0.75,0,fa0);
		forearm[4][3][0]=addBPoint(0.5,0.75,0,fa0);
		forearm[0][4][0]=addBPoint(-0.5,1.0,0,fa0);
		forearm[1][4][0]=addBPoint(-0.25,1.0,0,fa0);
		forearm[2][4][0]=addBPoint(0.0,1.0,0,fa0);
		forearm[3][4][0]=addBPoint(0.25,1.0,0,fa0);
		forearm[4][4][0]=addBPoint(0.5,1.0,0,fa0);

		forearm[0][0][1]=addBPoint(-0.5,0.0,1.0,fa0);	
		forearm[1][0][1]=addBPoint(-0.25,0.0,1.0,fa0);	
		forearm[2][0][1]=addBPoint(0.0,0.0,1.0,fa0);
		forearm[3][0][1]=addBPoint(0.25,0.0,1.0,fa0);	
		forearm[4][0][1]=addBPoint(0.5,0.0,1.0,fa0);		
		forearm[0][1][1]=addBPoint(-0.5,0.25,1.0,fa0);	
		forearm[1][1][1]=addBPoint(-0.25,0.25,1.0,fa0);
		forearm[2][1][1]=addBPoint(0.0,0.25,1.0,fa0);
		forearm[3][1][1]=addBPoint(0.25,0.25,1.0,fa0);
		forearm[4][1][1]=addBPoint(0.5,0.25,1.0,fa0);
		forearm[0][2][1]=addBPoint(-0.5,0.5,1.0,fa0);
		forearm[1][2][1]=addBPoint(-0.25,0.5,1.0,fa0);	
		forearm[2][2][1]=addBPoint(0.0,0.5,1.0,fa0);
		forearm[3][2][1]=addBPoint(0.25,0.5,1.0,fa0);
		forearm[4][2][1]=addBPoint(0.5,0.5,1.0,fa0);
		forearm[0][3][1]=addBPoint(-0.5,0.75,1.0,fa0);	
		forearm[1][3][1]=addBPoint(-0.25,0.75,1.0,fa0);
		forearm[2][3][1]=addBPoint(0.0,0.75,1.0,fa0);
		forearm[3][3][1]=addBPoint(0.25,0.75,1.0,fa0);
		forearm[4][3][1]=addBPoint(0.5,0.75,1.0,fa0);
		forearm[0][4][1]=addBPoint(-0.5,1.0,1.0,fa0);
		forearm[1][4][1]=addBPoint(-0.25,1.0,1.0,fa0);	
		forearm[2][4][1]=addBPoint(0.0,1.0,1.0,fa0);
		forearm[3][4][1]=addBPoint(0.25,1.0,1.0,fa0);
		forearm[4][4][1]=addBPoint(0.5,1.0,1.0,fa0);
		
		for (int i = 0; i < fanx-1; i++) {


			for (int j = 0; j < fany-1; j++) {

				for (int k = 0; k < fanz-1; k++) {




					if(i==0){

						addLine(forearm[i][j][k],forearm[i][j][k+1],forearm[i][j+1][k+1],forearm[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(forearm[i][j][k],forearm[i][j+1][k],forearm[i+1][j+1][k],forearm[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==fanz-1){
						addLine(forearm[i][j][k+1],forearm[i+1][j][k+1],forearm[i+1][j+1][k+1],forearm[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(forearm[i][j][k],forearm[i+1][j][k],forearm[i+1][j][k+1],forearm[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==fany-1){
						addLine(forearm[i][j+1][k],forearm[i][j+1][k+1],forearm[i+1][j+1][k+1],forearm[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==fanx-1){

						addLine(forearm[i+1][j][k],forearm[i+1][j+1][k],forearm[i+1][j+1][k+1],forearm[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		//butt
		
		int pnx=5;
		int pny=2;
		int pnz=2;
		
		BPoint[][][] butt=new BPoint[pnx][pny][pnz];
		
		double zEnd=breech_height-butt_end_height-rear_overhang;
	
		Segments p0=new Segments(xc,butt_width,-butt_length,butt_length,zEnd,butt_end_height);	
		
		butt[0][0][0]=addBPoint(-0.5,0,0,p0);
		butt[1][0][0]=addBPoint(-0.25,0.0,0,p0);
		butt[2][0][0]=addBPoint(0.0,0.0,0,p0);
		butt[3][0][0]=addBPoint(0.25,0.0,0,p0);
		butt[4][0][0]=addBPoint(0.5,0.0,0,p0);
		butt[0][0][1]=addBPoint(-0.5,0.0,1.0,p0);	
		butt[1][0][1]=addBPoint(-0.25,0.0,1.0,p0);	
		butt[2][0][1]=addBPoint(0.0,0.0,1.0,p0);
		butt[3][0][1]=addBPoint(0.25,0.0,1.0,p0);	
		butt[4][0][1]=addBPoint(0.5,0.0,1.0,p0);	
		
		Segments p1=new Segments(xc,butt_width,-butt_length,butt_length,0,butt_height);


		butt[0][1][0]=addBPoint(-0.5,1.0,0,p1);
		butt[1][1][0]=addBPoint(-0.25,1.0,0,p1);
		butt[2][1][0]=addBPoint(0.0,1.0,0,p1);
		butt[3][1][0]=addBPoint(0.25,1.0,0,p1);
		butt[4][1][0]=addBPoint(0.5,1.0,0,p1);
		butt[0][1][1]=addBPoint(-0.5,1.0,1.0,p1);
		butt[1][1][1]=addBPoint(-0.25,1.0,1.0,p1);	
		butt[2][1][1]=addBPoint(0.0,1.0,1.0,p1);
		butt[3][1][1]=addBPoint(0.25,1.0,1.0,p1);
		butt[4][1][1]=addBPoint(0.5,1.0,1.0,p1);
		
		for (int i = 0; i < pnx-1; i++) {


			for (int j = 0; j < pny-1; j++) {

				for (int k = 0; k < pnz-1; k++) {




					if(i==0){

						addLine(butt[i][j][k],butt[i][j][k+1],butt[i][j+1][k+1],butt[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(butt[i][j][k],butt[i][j+1][k],butt[i+1][j+1][k],butt[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==pnz-1){
						addLine(butt[i][j][k+1],butt[i+1][j][k+1],butt[i+1][j+1][k+1],butt[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(butt[i][j][k],butt[i+1][j][k],butt[i+1][j][k+1],butt[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==pny-1){
						addLine(butt[i][j+1][k],butt[i][j+1][k+1],butt[i+1][j+1][k+1],butt[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==pnx-1){

						addLine(butt[i+1][j][k],butt[i+1][j+1][k],butt[i+1][j+1][k+1],butt[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		////trigger
		
			Segments tr0=new Segments(xc,trigger_width,0,trigger_length,0,trigger_height);
					
			
			Prism triggerFront=new Prism(4);	
			
			double dy1=2/trigger_length;
					
			triggerFront.lowerBase[0]=addBPoint(-0.5,1.0-dy1,-1,tr0);
			triggerFront.lowerBase[1]=addBPoint(0.5,1.0-dy1,-1,tr0);
			triggerFront.lowerBase[2]=addBPoint(0.5,1.0,-1,tr0);
			triggerFront.lowerBase[3]=addBPoint(-0.5,1.0,-1,tr0);
			
			triggerFront.upperBase[0]=addBPoint(-0.5,1.0-dy1,0.0,tr0);
			triggerFront.upperBase[1]=addBPoint(0.5,1.0-dy1,0.0,tr0);
			triggerFront.upperBase[2]=addBPoint(0.5,1.0,0.0,tr0);
			triggerFront.upperBase[3]=addBPoint(-0.5,1.0,0.0,tr0);
			
			addPrism(triggerFront);
			
			Prism triggerBottom=new Prism(4);			
			
			double dz=2/trigger_height;
			double dy0=0;//double dy0=(-trigger_height*butt_length/zEnd)/trigger_length;
			
			triggerBottom.lowerBase[0]=addBPoint(-0.5,-dy0,-1,tr0);
			triggerBottom.lowerBase[1]=addBPoint(0.5,-dy0,-1,tr0);
			triggerBottom.lowerBase[2]=addBPoint(0.5,1.0-dy1,-1,tr0);
			triggerBottom.lowerBase[3]=addBPoint(-0.5,1.0-dy1,-1,tr0);
			
			triggerBottom.upperBase[0]=addBPoint(-0.5,-dy0,-1.0+dz,tr0);
			triggerBottom.upperBase[1]=addBPoint(0.5,-dy0,-1.0+dz,tr0);
			triggerBottom.upperBase[2]=addBPoint(0.5,1.0-dy1,-1.0+dz,tr0);
			triggerBottom.upperBase[3]=addBPoint(-0.5,1.0-dy1,-1.0+dz,tr0);
		
			addPrism(triggerBottom);
			
			/////////
		
		/////////

		//translatePoints(points,nw_x,nw_y);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;

	}

	private PolygonMesh buildSubmachineGunMesh() {
		points=new Vector();
		points.setSize(200);

		polyData=new Vector();
		
		
		n=0;
		
		double bax0=0;
		double bay0=breech_length;
		double baz0=breech_height-barrel_radius;

		//barrel:
		
		BPoint[] uTrunkpoints=new BPoint[barrel_meridians];
		BPoint[] bTrunkpoints=new BPoint[barrel_meridians];
		
		for (int i = 0; i < barrel_meridians; i++) {
		
			
			double x=bax0+barrel_radius*Math.cos(2*Math.PI/barrel_meridians*i);
			double z=baz0+barrel_radius*Math.sin(2*Math.PI/barrel_meridians*i);
			
			uTrunkpoints[i]=addBPoint(x,bay0+barrel_lenght,z);
			
			
		}


		LineData topLD=new LineData();
		
		
			
			for (int i = barrel_meridians-1; i >=0; i--) {
			
			topLD.addIndex(uTrunkpoints[i].getIndex());
			
		}
		topLD.setData(""+Renderer3D.CAR_TOP);
		polyData.add(topLD);
		
		for (int i = 0; i < barrel_meridians; i++) {
			
			double x=bax0+barrel_radius*Math.cos(2*Math.PI/barrel_meridians*i);
			double z=baz0+barrel_radius*Math.sin(2*Math.PI/barrel_meridians*i);
			
			bTrunkpoints[i]=addBPoint(x,bay0,z);
			
		}


		LineData bottomLD=new LineData();
		
		for (int i = 0; i < barrel_meridians; i++) {
			
			bottomLD.addIndex(bTrunkpoints[i].getIndex());
			
		}
		bottomLD.setData(""+Renderer3D.CAR_BOTTOM);
		polyData.add(bottomLD);
		
		
		
		for (int i = 0; i < barrel_meridians; i++) {
			
			LineData sideLD=new LineData();
			
			
			sideLD.addIndex(bTrunkpoints[(i+1)%barrel_meridians].getIndex());
			sideLD.addIndex(bTrunkpoints[i].getIndex());
			sideLD.addIndex(uTrunkpoints[i].getIndex());
			sideLD.addIndex(uTrunkpoints[(i+1)%barrel_meridians].getIndex());
			sideLD.setData(""+Renderer3D.getFace(sideLD,points));
			polyData.add(sideLD);
			
		}
		
		//breech
		
		int bnx=5;
		int bny=5;
		int bnz=2;
		
		BPoint[][][] breech=new BPoint[bnx][bny][bnz];
		
		double xc=0.0;
		
		Segments b0=new Segments(xc,breech_width,0,breech_length,0,breech_height);
		
		//double wr=(breech_height-2*barrel_radius)/breech_height;

		breech[0][0][0]=addBPoint(-0.5,0,0,b0);
		breech[1][0][0]=addBPoint(-0.25,0.0,0,b0);
		breech[2][0][0]=addBPoint(0.0,0.0,0,b0);
		breech[3][0][0]=addBPoint(0.25,0.0,0,b0);
		breech[4][0][0]=addBPoint(0.5,0.0,0,b0);
		breech[0][1][0]=addBPoint(-0.5,0.25,0,b0);
		breech[1][1][0]=addBPoint(-0.25,0.25,0,b0);
		breech[2][1][0]=addBPoint(0.0,0.25,0,b0);
		breech[3][1][0]=addBPoint(0.25,0.25,0,b0);
		breech[4][1][0]=addBPoint(0.5,0.25,0,b0);
		breech[0][2][0]=addBPoint(-0.5,0.5,0,b0);
		breech[1][2][0]=addBPoint(-0.25,0.5,0,b0);
		breech[2][2][0]=addBPoint(0.0,0.5,0,b0);
		breech[3][2][0]=addBPoint(0.25,0.5,0,b0);
		breech[4][2][0]=addBPoint(0.5,0.5,0,b0);
		breech[0][3][0]=addBPoint(-0.5,0.75,0,b0);
		breech[1][3][0]=addBPoint(-0.25,0.75,0,b0);
		breech[2][3][0]=addBPoint(0.0,0.75,0,b0);
		breech[3][3][0]=addBPoint(0.25,0.75,0,b0);
		breech[4][3][0]=addBPoint(0.5,0.75,0,b0);
		breech[0][4][0]=addBPoint(-0.5,1.0,0,b0);
		breech[1][4][0]=addBPoint(-0.25,1.0,0,b0);
		breech[2][4][0]=addBPoint(0.0,1.0,0,b0);
		breech[3][4][0]=addBPoint(0.25,1.0,0,b0);
		breech[4][4][0]=addBPoint(0.5,1.0,0,b0);

		breech[0][0][1]=addBPoint(-0.5,0.0,1.0,b0);	
		breech[1][0][1]=addBPoint(-0.25,0.0,1.0,b0);	
		breech[2][0][1]=addBPoint(0.0,0.0,1.0,b0);
		breech[3][0][1]=addBPoint(0.25,0.0,1.0,b0);	
		breech[4][0][1]=addBPoint(0.5,0.0,1.0,b0);		
		breech[0][1][1]=addBPoint(-0.5,0.25,1.0,b0);	
		breech[1][1][1]=addBPoint(-0.25,0.25,1.0,b0);
		breech[2][1][1]=addBPoint(0.0,0.25,1.0,b0);
		breech[3][1][1]=addBPoint(0.25,0.25,1.0,b0);
		breech[4][1][1]=addBPoint(0.5,0.25,1.0,b0);
		breech[0][2][1]=addBPoint(-0.5,0.5,1.0,b0);
		breech[1][2][1]=addBPoint(-0.25,0.5,1.0,b0);	
		breech[2][2][1]=addBPoint(0.0,0.5,1.0,b0);
		breech[3][2][1]=addBPoint(0.25,0.5,1.0,b0);
		breech[4][2][1]=addBPoint(0.5,0.5,1.0,b0);
		breech[0][3][1]=addBPoint(-0.5,0.75,1.0,b0);	
		breech[1][3][1]=addBPoint(-0.25,0.75,1.0,b0);
		breech[2][3][1]=addBPoint(0.0,0.75,1.0,b0);
		breech[3][3][1]=addBPoint(0.25,0.75,1.0,b0);
		breech[4][3][1]=addBPoint(0.5,0.75,1.0,b0);
		breech[0][4][1]=addBPoint(-0.5,1.0,1.0,b0);
		breech[1][4][1]=addBPoint(-0.25,1.0,1.0,b0);	
		breech[2][4][1]=addBPoint(0.0,1.0,1.0,b0);
		breech[3][4][1]=addBPoint(0.25,1.0,1.0,b0);
		breech[4][4][1]=addBPoint(0.5,1.0,1.0,b0);
		
		for (int i = 0; i < bnx-1; i++) {


			for (int j = 0; j < bny-1; j++) {

				for (int k = 0; k < bnz-1; k++) {




					if(i==0){

						addLine(breech[i][j][k],breech[i][j][k+1],breech[i][j+1][k+1],breech[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(breech[i][j][k],breech[i][j+1][k],breech[i+1][j+1][k],breech[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==bnz-1){
						addLine(breech[i][j][k+1],breech[i+1][j][k+1],breech[i+1][j+1][k+1],breech[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(breech[i][j][k],breech[i+1][j][k],breech[i+1][j][k+1],breech[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==bny-1){
						addLine(breech[i][j+1][k],breech[i][j+1][k+1],breech[i+1][j+1][k+1],breech[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==bnx-1){

						addLine(breech[i+1][j][k],breech[i+1][j+1][k],breech[i+1][j+1][k+1],breech[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		
		//magazine
		
		int manx=5;
		int many=5; 
		int manz=2;
		
		BPoint[][][] magazine=new BPoint[manx][many][manz];

		
		Segments ma0=new Segments(xc,magazine_width,breech_length-magazine_length,magazine_length,-magazine_height,magazine_height);
		
		//double wr=(breech_height-2*barrel_radius)/breech_height;

		magazine[0][0][0]=addBPoint(-0.5,0,0,ma0);
		magazine[1][0][0]=addBPoint(-0.25,0.0,0,ma0);
		magazine[2][0][0]=addBPoint(0.0,0.0,0,ma0);
		magazine[3][0][0]=addBPoint(0.25,0.0,0,ma0);
		magazine[4][0][0]=addBPoint(0.5,0.0,0,ma0);
		magazine[0][1][0]=addBPoint(-0.5,0.25,0,ma0);
		magazine[1][1][0]=addBPoint(-0.25,0.25,0,ma0);
		magazine[2][1][0]=addBPoint(0.0,0.25,0,ma0);
		magazine[3][1][0]=addBPoint(0.25,0.25,0,ma0);
		magazine[4][1][0]=addBPoint(0.5,0.25,0,ma0);
		magazine[0][2][0]=addBPoint(-0.5,0.5,0,ma0);
		magazine[1][2][0]=addBPoint(-0.25,0.5,0,ma0);
		magazine[2][2][0]=addBPoint(0.0,0.5,0,ma0);
		magazine[3][2][0]=addBPoint(0.25,0.5,0,ma0);
		magazine[4][2][0]=addBPoint(0.5,0.5,0,ma0);
		magazine[0][3][0]=addBPoint(-0.5,0.75,0,ma0);
		magazine[1][3][0]=addBPoint(-0.25,0.75,0,ma0);
		magazine[2][3][0]=addBPoint(0.0,0.75,0,ma0);
		magazine[3][3][0]=addBPoint(0.25,0.75,0,ma0);
		magazine[4][3][0]=addBPoint(0.5,0.75,0,ma0);
		magazine[0][4][0]=addBPoint(-0.5,1.0,0,ma0);
		magazine[1][4][0]=addBPoint(-0.25,1.0,0,ma0);
		magazine[2][4][0]=addBPoint(0.0,1.0,0,ma0);
		magazine[3][4][0]=addBPoint(0.25,1.0,0,ma0);
		magazine[4][4][0]=addBPoint(0.5,1.0,0,ma0);

		magazine[0][0][1]=addBPoint(-0.5,0.0,1.0,ma0);	
		magazine[1][0][1]=addBPoint(-0.25,0.0,1.0,ma0);	
		magazine[2][0][1]=addBPoint(0.0,0.0,1.0,ma0);
		magazine[3][0][1]=addBPoint(0.25,0.0,1.0,ma0);	
		magazine[4][0][1]=addBPoint(0.5,0.0,1.0,ma0);		
		magazine[0][1][1]=addBPoint(-0.5,0.25,1.0,ma0);	
		magazine[1][1][1]=addBPoint(-0.25,0.25,1.0,ma0);
		magazine[2][1][1]=addBPoint(0.0,0.25,1.0,ma0);
		magazine[3][1][1]=addBPoint(0.25,0.25,1.0,ma0);
		magazine[4][1][1]=addBPoint(0.5,0.25,1.0,ma0);
		magazine[0][2][1]=addBPoint(-0.5,0.5,1.0,ma0);
		magazine[1][2][1]=addBPoint(-0.25,0.5,1.0,ma0);	
		magazine[2][2][1]=addBPoint(0.0,0.5,1.0,ma0);
		magazine[3][2][1]=addBPoint(0.25,0.5,1.0,ma0);
		magazine[4][2][1]=addBPoint(0.5,0.5,1.0,ma0);
		magazine[0][3][1]=addBPoint(-0.5,0.75,1.0,ma0);	
		magazine[1][3][1]=addBPoint(-0.25,0.75,1.0,ma0);
		magazine[2][3][1]=addBPoint(0.0,0.75,1.0,ma0);
		magazine[3][3][1]=addBPoint(0.25,0.75,1.0,ma0);
		magazine[4][3][1]=addBPoint(0.5,0.75,1.0,ma0);
		magazine[0][4][1]=addBPoint(-0.5,1.0,1.0,ma0);
		magazine[1][4][1]=addBPoint(-0.25,1.0,1.0,ma0);	
		magazine[2][4][1]=addBPoint(0.0,1.0,1.0,ma0);
		magazine[3][4][1]=addBPoint(0.25,1.0,1.0,ma0);
		magazine[4][4][1]=addBPoint(0.5,1.0,1.0,ma0);
		
		for (int i = 0; i < manx-1; i++) {


			for (int j = 0; j < many-1; j++) {

				for (int k = 0; k < manz-1; k++) {




					if(i==0){

						addLine(magazine[i][j][k],magazine[i][j][k+1],magazine[i][j+1][k+1],magazine[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(magazine[i][j][k],magazine[i][j+1][k],magazine[i+1][j+1][k],magazine[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==manz-1){
						addLine(magazine[i][j][k+1],magazine[i+1][j][k+1],magazine[i+1][j+1][k+1],magazine[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(magazine[i][j][k],magazine[i+1][j][k],magazine[i+1][j][k+1],magazine[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==many-1){
						addLine(magazine[i][j+1][k],magazine[i][j+1][k+1],magazine[i+1][j+1][k+1],magazine[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==manx-1){

						addLine(magazine[i+1][j][k],magazine[i+1][j+1][k],magazine[i+1][j+1][k+1],magazine[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		//forearm
		
				int fanx=5;
				int fany=5;
				int fanz=2;
				
				BPoint[][][] forearm=new BPoint[fanx][fany][fanz];

				
				Segments fa0=new Segments(xc,forearm_width,0,forearm_length,-forearm_height,forearm_height);
				
				//double wr=(breech_height-2*barrel_radius)/breech_height;

				forearm[0][0][0]=addBPoint(-0.5,0,0,fa0);
				forearm[1][0][0]=addBPoint(-0.25,0.0,0,fa0);
				forearm[2][0][0]=addBPoint(0.0,0.0,0,fa0);
				forearm[3][0][0]=addBPoint(0.25,0.0,0,fa0);
				forearm[4][0][0]=addBPoint(0.5,0.0,0,fa0);
				forearm[0][1][0]=addBPoint(-0.5,0.25,0,fa0);
				forearm[1][1][0]=addBPoint(-0.25,0.25,0,fa0);
				forearm[2][1][0]=addBPoint(0.0,0.25,0,fa0);
				forearm[3][1][0]=addBPoint(0.25,0.25,0,fa0);
				forearm[4][1][0]=addBPoint(0.5,0.25,0,fa0);
				forearm[0][2][0]=addBPoint(-0.5,0.5,0,fa0);
				forearm[1][2][0]=addBPoint(-0.25,0.5,0,fa0);
				forearm[2][2][0]=addBPoint(0.0,0.5,0,fa0);
				forearm[3][2][0]=addBPoint(0.25,0.5,0,fa0);
				forearm[4][2][0]=addBPoint(0.5,0.5,0,fa0);
				forearm[0][3][0]=addBPoint(-0.5,0.75,0,fa0);
				forearm[1][3][0]=addBPoint(-0.25,0.75,0,fa0);
				forearm[2][3][0]=addBPoint(0.0,0.75,0,fa0);
				forearm[3][3][0]=addBPoint(0.25,0.75,0,fa0);
				forearm[4][3][0]=addBPoint(0.5,0.75,0,fa0);
				forearm[0][4][0]=addBPoint(-0.5,1.0,0,fa0);
				forearm[1][4][0]=addBPoint(-0.25,1.0,0,fa0);
				forearm[2][4][0]=addBPoint(0.0,1.0,0,fa0);
				forearm[3][4][0]=addBPoint(0.25,1.0,0,fa0);
				forearm[4][4][0]=addBPoint(0.5,1.0,0,fa0);

				forearm[0][0][1]=addBPoint(-0.5,0.0,1.0,fa0);	
				forearm[1][0][1]=addBPoint(-0.25,0.0,1.0,fa0);	
				forearm[2][0][1]=addBPoint(0.0,0.0,1.0,fa0);
				forearm[3][0][1]=addBPoint(0.25,0.0,1.0,fa0);	
				forearm[4][0][1]=addBPoint(0.5,0.0,1.0,fa0);		
				forearm[0][1][1]=addBPoint(-0.5,0.25,1.0,fa0);	
				forearm[1][1][1]=addBPoint(-0.25,0.25,1.0,fa0);
				forearm[2][1][1]=addBPoint(0.0,0.25,1.0,fa0);
				forearm[3][1][1]=addBPoint(0.25,0.25,1.0,fa0);
				forearm[4][1][1]=addBPoint(0.5,0.25,1.0,fa0);
				forearm[0][2][1]=addBPoint(-0.5,0.5,1.0,fa0);
				forearm[1][2][1]=addBPoint(-0.25,0.5,1.0,fa0);	
				forearm[2][2][1]=addBPoint(0.0,0.5,1.0,fa0);
				forearm[3][2][1]=addBPoint(0.25,0.5,1.0,fa0);
				forearm[4][2][1]=addBPoint(0.5,0.5,1.0,fa0);
				forearm[0][3][1]=addBPoint(-0.5,0.75,1.0,fa0);	
				forearm[1][3][1]=addBPoint(-0.25,0.75,1.0,fa0);
				forearm[2][3][1]=addBPoint(0.0,0.75,1.0,fa0);
				forearm[3][3][1]=addBPoint(0.25,0.75,1.0,fa0);
				forearm[4][3][1]=addBPoint(0.5,0.75,1.0,fa0);
				forearm[0][4][1]=addBPoint(-0.5,1.0,1.0,fa0);
				forearm[1][4][1]=addBPoint(-0.25,1.0,1.0,fa0);	
				forearm[2][4][1]=addBPoint(0.0,1.0,1.0,fa0);
				forearm[3][4][1]=addBPoint(0.25,1.0,1.0,fa0);
				forearm[4][4][1]=addBPoint(0.5,1.0,1.0,fa0);
				
				for (int i = 0; i < fanx-1; i++) {


					for (int j = 0; j < fany-1; j++) {

						for (int k = 0; k < fanz-1; k++) {




							if(i==0){

								addLine(forearm[i][j][k],forearm[i][j][k+1],forearm[i][j+1][k+1],forearm[i][j+1][k],Renderer3D.CAR_LEFT);
							}

						
								
							if(k==0){

								addLine(forearm[i][j][k],forearm[i][j+1][k],forearm[i+1][j+1][k],forearm[i+1][j][k],Renderer3D.CAR_BOTTOM);
							
							}
							
							if(k+1==fanz-1){
								addLine(forearm[i][j][k+1],forearm[i+1][j][k+1],forearm[i+1][j+1][k+1],forearm[i][j+1][k+1],Renderer3D.CAR_TOP);
							}
							
							if(j==0){
								addLine(forearm[i][j][k],forearm[i+1][j][k],forearm[i+1][j][k+1],forearm[i][j][k+1],Renderer3D.CAR_BACK);
							}
							if(j+1==fany-1){
								addLine(forearm[i][j+1][k],forearm[i][j+1][k+1],forearm[i+1][j+1][k+1],forearm[i+1][j+1][k],Renderer3D.CAR_FRONT);	
							}
						

							if(i+1==fanx-1){

								addLine(forearm[i+1][j][k],forearm[i+1][j+1][k],forearm[i+1][j+1][k+1],forearm[i+1][j][k+1],Renderer3D.CAR_RIGHT);

							}
						}
					}

				}
		
		//butt
		
		int pnx=5;
		int pny=2;
		int pnz=2;
		
		BPoint[][][] butt=new BPoint[pnx][pny][pnz];
		
		double zEnd=breech_height-butt_end_height-rear_overhang;
	
		Segments p0=new Segments(xc,butt_width,-butt_length,butt_length,zEnd,butt_end_height);	
		
		butt[0][0][0]=addBPoint(-0.5,0,0,p0);
		butt[1][0][0]=addBPoint(-0.25,0.0,0,p0);
		butt[2][0][0]=addBPoint(0.0,0.0,0,p0);
		butt[3][0][0]=addBPoint(0.25,0.0,0,p0);
		butt[4][0][0]=addBPoint(0.5,0.0,0,p0);
		butt[0][0][1]=addBPoint(-0.5,0.0,1.0,p0);	
		butt[1][0][1]=addBPoint(-0.25,0.0,1.0,p0);	
		butt[2][0][1]=addBPoint(0.0,0.0,1.0,p0);
		butt[3][0][1]=addBPoint(0.25,0.0,1.0,p0);	
		butt[4][0][1]=addBPoint(0.5,0.0,1.0,p0);	
		
		Segments p1=new Segments(xc,butt_width,-butt_length,butt_length,0,butt_height);


		butt[0][1][0]=addBPoint(-0.5,1.0,0,p1);
		butt[1][1][0]=addBPoint(-0.25,1.0,0,p1);
		butt[2][1][0]=addBPoint(0.0,1.0,0,p1);
		butt[3][1][0]=addBPoint(0.25,1.0,0,p1);
		butt[4][1][0]=addBPoint(0.5,1.0,0,p1);
		butt[0][1][1]=addBPoint(-0.5,1.0,1.0,p1);
		butt[1][1][1]=addBPoint(-0.25,1.0,1.0,p1);	
		butt[2][1][1]=addBPoint(0.0,1.0,1.0,p1);
		butt[3][1][1]=addBPoint(0.25,1.0,1.0,p1);
		butt[4][1][1]=addBPoint(0.5,1.0,1.0,p1);
		
		for (int i = 0; i < pnx-1; i++) {


			for (int j = 0; j < pny-1; j++) {

				for (int k = 0; k < pnz-1; k++) {




					if(i==0){

						addLine(butt[i][j][k],butt[i][j][k+1],butt[i][j+1][k+1],butt[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(butt[i][j][k],butt[i][j+1][k],butt[i+1][j+1][k],butt[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==pnz-1){
						addLine(butt[i][j][k+1],butt[i+1][j][k+1],butt[i+1][j+1][k+1],butt[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(butt[i][j][k],butt[i+1][j][k],butt[i+1][j][k+1],butt[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==pny-1){
						addLine(butt[i][j+1][k],butt[i][j+1][k+1],butt[i+1][j+1][k+1],butt[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==pnx-1){

						addLine(butt[i+1][j][k],butt[i+1][j+1][k],butt[i+1][j+1][k+1],butt[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}

		
		/////////

		//translatePoints(points,nw_x,nw_y);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;

	}
	
	private PolygonMesh buildBaseballBatMesh() {
		

		
		int num=30;
		
		Point3D[] profile=new Point3D[num];
		
		
		profile[0]=new Point3D(0.0,7.0,0.0);
		profile[1]=new Point3D(33.758620689655174, 38.0,0.0);
		profile[2]=new Point3D(67.51724137931035, 39.0,0.0);
		profile[3]=new Point3D(101.27586206896552, 40.0,0.0);
		profile[4]=new Point3D(135.0344827586207, 39.0,0.0);
		profile[5]=new Point3D(168.79310344827587, 39.0,0.0);
		profile[6]=new Point3D(202.55172413793105, 38.0,0.0);
		profile[7]=new Point3D(236.31034482758622, 37.0,0.0);
		profile[8]=new Point3D(270.0689655172414, 35.0,0.0);
		profile[9]=new Point3D(303.82758620689657, 34.0,0.0);
		profile[10]=new Point3D(337.58620689655174, 32.0,0.0);
		profile[11]=new Point3D(371.3448275862069, 30.0,0.0);
		profile[12]=new Point3D(405.1034482758621, 28.0,0.0);
		profile[13]=new Point3D(438.86206896551727, 27.0,0.0);
		profile[14]=new Point3D(472.62068965517244, 25.0,0.0);
		profile[15]=new Point3D(506.3793103448276, 23.0,0.0);
		profile[16]=new Point3D(540.1379310344828, 21.0,0.0);
		profile[17]=new Point3D(573.8965517241379, 20.0,0.0);
		profile[18]=new Point3D(607.6551724137931, 18.0,0.0);
		profile[19]=new Point3D(641.4137931034484, 17.0,0.0);
		profile[20]=new Point3D(675.1724137931035, 16.0,0.0);
		profile[21]=new Point3D(708.9310344827586, 15.0,0.0);
		profile[22]=new Point3D(742.6896551724138, 15.0,0.0);
		profile[23]=new Point3D(776.4482758620691, 14.0,0.0);
		profile[24]=new Point3D(810.2068965517242, 14.0,0.0);
		profile[25]=new Point3D(843.9655172413793, 15.0,0.0);
		profile[26]=new Point3D(877.7241379310345, 15.0,0.0);
		profile[27]=new Point3D(911.4827586206898, 18.0,0.0);
		profile[28]=new Point3D(945.2413793103449, 24.0,0.0);
		profile[29]=new Point3D(979.0, 10.0,0.0);

		specificData=new Barrel(10,profile);
		
		PolygonMesh pm=specificData.getMesh();
	
		return pm;
	}

	private PolygonMesh buildRevolverMesh() {
		points=new Vector();
		points.setSize(200);

		polyData=new Vector();
		
		
		n=0;
		
		double bax0=0;
		double bay0=breech_length;
		double baz0=breech_height-barrel_radius;

		//barrel:
		
		addCylinder(bax0,bay0,baz0,barrel_radius,barrel_lenght,barrel_meridians);
		
				
		//breech
		
		//main body
		
		int bnx=5;
		int bny=5;
		int bnz=2;
		
		BPoint[][][] breech=new BPoint[bnx][bny][bnz];
		
		double xc=0.0;

		
		Segments b0=new Segments(xc,breech_width,0,breech_length,0,breech_height);
		
		double wr=(breech_height-2*barrel_radius)/breech_height;

		breech[0][0][0]=addBPoint(-0.5,0,0,b0);
		breech[1][0][0]=addBPoint(-0.25,0.0,0,b0);
		breech[2][0][0]=addBPoint(0.0,0.0,0,b0);
		breech[3][0][0]=addBPoint(0.25,0.0,0,b0);
		breech[4][0][0]=addBPoint(0.5,0.0,0,b0);
		breech[0][1][0]=addBPoint(-0.5,0.25,0,b0);
		breech[1][1][0]=addBPoint(-0.25,0.25,0,b0);
		breech[2][1][0]=addBPoint(0.0,0.25,0,b0);
		breech[3][1][0]=addBPoint(0.25,0.25,0,b0);
		breech[4][1][0]=addBPoint(0.5,0.25,0,b0);
		breech[0][2][0]=addBPoint(-0.5,0.5,0,b0);
		breech[1][2][0]=addBPoint(-0.25,0.5,0,b0);
		breech[2][2][0]=addBPoint(0.0,0.5,0,b0);
		breech[3][2][0]=addBPoint(0.25,0.5,0,b0);
		breech[4][2][0]=addBPoint(0.5,0.5,0,b0);
		breech[0][3][0]=addBPoint(-0.5,0.75,0,b0);
		breech[1][3][0]=addBPoint(-0.25,0.75,0,b0);
		breech[2][3][0]=addBPoint(0.0,0.75,0,b0);
		breech[3][3][0]=addBPoint(0.25,0.75,0,b0);
		breech[4][3][0]=addBPoint(0.5,0.75,0,b0);
		breech[0][4][0]=addBPoint(-0.5,1.0,wr,b0);
		breech[1][4][0]=addBPoint(-0.25,1.0,wr,b0);
		breech[2][4][0]=addBPoint(0.0,1.0,wr,b0);
		breech[3][4][0]=addBPoint(0.25,1.0,wr,b0);
		breech[4][4][0]=addBPoint(0.5,1.0,wr,b0);
		
		breech[0][0][1]=addBPoint(-0.5,0.0,1.0,b0);	
		breech[1][0][1]=addBPoint(-0.25,0.0,1.0,b0);	
		breech[2][0][1]=addBPoint(0.0,0.0,1.0,b0);
		breech[3][0][1]=addBPoint(0.25,0.0,1.0,b0);	
		breech[4][0][1]=addBPoint(0.5,0.0,1.0,b0);		
		breech[0][1][1]=addBPoint(-0.5,0.25,1.0,b0);	
		breech[1][1][1]=addBPoint(-0.25,0.25,1.0,b0);
		breech[2][1][1]=addBPoint(0.0,0.25,1.0,b0);
		breech[3][1][1]=addBPoint(0.25,0.25,1.0,b0);
		breech[4][1][1]=addBPoint(0.5,0.25,1.0,b0);
		breech[0][2][1]=addBPoint(-0.5,0.5,1.0,b0);
		breech[1][2][1]=addBPoint(-0.25,0.5,1.0,b0);	
		breech[2][2][1]=addBPoint(0.0,0.5,1.0,b0);
		breech[3][2][1]=addBPoint(0.25,0.5,1.0,b0);
		breech[4][2][1]=addBPoint(0.5,0.5,1.0,b0);
		breech[0][3][1]=addBPoint(-0.5,0.75,1.0,b0);	
		breech[1][3][1]=addBPoint(-0.25,0.75,1.0,b0);
		breech[2][3][1]=addBPoint(0.0,0.75,1.0,b0);
		breech[3][3][1]=addBPoint(0.25,0.75,1.0,b0);
		breech[4][3][1]=addBPoint(0.5,0.75,1.0,b0);
		breech[0][4][1]=addBPoint(-0.5,1.0,1.0,b0);
		breech[1][4][1]=addBPoint(-0.25,1.0,1.0,b0);	
		breech[2][4][1]=addBPoint(0.0,1.0,1.0,b0);
		breech[3][4][1]=addBPoint(0.25,1.0,1.0,b0);
		breech[4][4][1]=addBPoint(0.5,1.0,1.0,b0);
		
		for (int i = 0; i < bnx-1; i++) {


			for (int j = 0; j < bny-1; j++) {

				for (int k = 0; k < bnz-1; k++) {




					if(i==0){

						addLine(breech[i][j][k],breech[i][j][k+1],breech[i][j+1][k+1],breech[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(breech[i][j][k],breech[i][j+1][k],breech[i+1][j+1][k],breech[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==bnz-1){
						addLine(breech[i][j][k+1],breech[i+1][j][k+1],breech[i+1][j+1][k+1],breech[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(breech[i][j][k],breech[i+1][j][k],breech[i+1][j][k+1],breech[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==bny-1){
						addLine(breech[i][j+1][k],breech[i][j+1][k+1],breech[i+1][j+1][k+1],breech[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==bnx-1){

						addLine(breech[i+1][j][k],breech[i+1][j+1][k],breech[i+1][j+1][k+1],breech[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		//forearm
		
				int fanx=5;
				int fany=5;
				int fanz=2;
				
				BPoint[][][] forearm=new BPoint[fanx][fany][fanz];
				
				double faZed=breech_height-(forearm_height+2*barrel_radius);

				
				Segments fa0=new Segments(xc,forearm_width,breech_length,forearm_length,faZed,forearm_height);
				
				//double wr=(breech_height-2*barrel_radius)/breech_height;

				forearm[0][0][0]=addBPoint(-0.5,0,0,fa0);
				forearm[1][0][0]=addBPoint(-0.25,0.0,0,fa0);
				forearm[2][0][0]=addBPoint(0.0,0.0,0,fa0);
				forearm[3][0][0]=addBPoint(0.25,0.0,0,fa0);
				forearm[4][0][0]=addBPoint(0.5,0.0,0,fa0);
				forearm[0][1][0]=addBPoint(-0.5,0.25,0,fa0);
				forearm[1][1][0]=addBPoint(-0.25,0.25,0,fa0);
				forearm[2][1][0]=addBPoint(0.0,0.25,0,fa0);
				forearm[3][1][0]=addBPoint(0.25,0.25,0,fa0);
				forearm[4][1][0]=addBPoint(0.5,0.25,0,fa0);
				forearm[0][2][0]=addBPoint(-0.5,0.5,0,fa0);
				forearm[1][2][0]=addBPoint(-0.25,0.5,0,fa0);
				forearm[2][2][0]=addBPoint(0.0,0.5,0,fa0);
				forearm[3][2][0]=addBPoint(0.25,0.5,0,fa0);
				forearm[4][2][0]=addBPoint(0.5,0.5,0,fa0);
				forearm[0][3][0]=addBPoint(-0.5,0.75,0,fa0);
				forearm[1][3][0]=addBPoint(-0.25,0.75,0,fa0);
				forearm[2][3][0]=addBPoint(0.0,0.75,0,fa0);
				forearm[3][3][0]=addBPoint(0.25,0.75,0,fa0);
				forearm[4][3][0]=addBPoint(0.5,0.75,0,fa0);
				forearm[0][4][0]=addBPoint(-0.5,1.0,0,fa0);
				forearm[1][4][0]=addBPoint(-0.25,1.0,0,fa0);
				forearm[2][4][0]=addBPoint(0.0,1.0,0,fa0);
				forearm[3][4][0]=addBPoint(0.25,1.0,0,fa0);
				forearm[4][4][0]=addBPoint(0.5,1.0,0,fa0);

				forearm[0][0][1]=addBPoint(-0.5,0.0,1.0,fa0);	
				forearm[1][0][1]=addBPoint(-0.25,0.0,1.0,fa0);	
				forearm[2][0][1]=addBPoint(0.0,0.0,1.0,fa0);
				forearm[3][0][1]=addBPoint(0.25,0.0,1.0,fa0);	
				forearm[4][0][1]=addBPoint(0.5,0.0,1.0,fa0);		
				forearm[0][1][1]=addBPoint(-0.5,0.25,1.0,fa0);	
				forearm[1][1][1]=addBPoint(-0.25,0.25,1.0,fa0);
				forearm[2][1][1]=addBPoint(0.0,0.25,1.0,fa0);
				forearm[3][1][1]=addBPoint(0.25,0.25,1.0,fa0);
				forearm[4][1][1]=addBPoint(0.5,0.25,1.0,fa0);
				forearm[0][2][1]=addBPoint(-0.5,0.5,1.0,fa0);
				forearm[1][2][1]=addBPoint(-0.25,0.5,1.0,fa0);	
				forearm[2][2][1]=addBPoint(0.0,0.5,1.0,fa0);
				forearm[3][2][1]=addBPoint(0.25,0.5,1.0,fa0);
				forearm[4][2][1]=addBPoint(0.5,0.5,1.0,fa0);
				forearm[0][3][1]=addBPoint(-0.5,0.75,1.0,fa0);	
				forearm[1][3][1]=addBPoint(-0.25,0.75,1.0,fa0);
				forearm[2][3][1]=addBPoint(0.0,0.75,1.0,fa0);
				forearm[3][3][1]=addBPoint(0.25,0.75,1.0,fa0);
				forearm[4][3][1]=addBPoint(0.5,0.75,1.0,fa0);
				forearm[0][4][1]=addBPoint(-0.5,1.0,1.0,fa0);
				forearm[1][4][1]=addBPoint(-0.25,1.0,1.0,fa0);	
				forearm[2][4][1]=addBPoint(0.0,1.0,1.0,fa0);
				forearm[3][4][1]=addBPoint(0.25,1.0,1.0,fa0);
				forearm[4][4][1]=addBPoint(0.5,1.0,1.0,fa0);
				
				for (int i = 0; i < fanx-1; i++) {


					for (int j = 0; j < fany-1; j++) {

						for (int k = 0; k < fanz-1; k++) {




							if(i==0){

								addLine(forearm[i][j][k],forearm[i][j][k+1],forearm[i][j+1][k+1],forearm[i][j+1][k],Renderer3D.CAR_LEFT);
							}

						
								
							if(k==0){

								addLine(forearm[i][j][k],forearm[i][j+1][k],forearm[i+1][j+1][k],forearm[i+1][j][k],Renderer3D.CAR_BOTTOM);
							
							}
							
							if(k+1==fanz-1){
								addLine(forearm[i][j][k+1],forearm[i+1][j][k+1],forearm[i+1][j+1][k+1],forearm[i][j+1][k+1],Renderer3D.CAR_TOP);
							}
							
							if(j==0){
								addLine(forearm[i][j][k],forearm[i+1][j][k],forearm[i+1][j][k+1],forearm[i][j][k+1],Renderer3D.CAR_BACK);
							}
							if(j+1==fany-1){
								addLine(forearm[i][j+1][k],forearm[i][j+1][k+1],forearm[i+1][j+1][k+1],forearm[i+1][j+1][k],Renderer3D.CAR_FRONT);	
							}
						

							if(i+1==fanx-1){

								addLine(forearm[i+1][j][k],forearm[i+1][j+1][k],forearm[i+1][j+1][k+1],forearm[i+1][j][k+1],Renderer3D.CAR_RIGHT);

							}
						}
					}

				}
				
		//cylinder
		
		
		double cyx0=0;
		double cyy0=breech_length-magazine_length;
		double cyz0=breech_height-magazine_width*0.5;
		
		addCylinder(cyx0,cyy0,cyz0,magazine_width*0.5,magazine_length,barrel_meridians);
		
		//butt
		
		int pnx=5;
		int pny=2;
		int pnz=2;
		
		BPoint[][][] butt=new BPoint[pnx][pny][pnz];
		
		double zButt=-butt_height+breech_height*0.5;
		
		
		
		Segments p0=new Segments(xc,butt_width,-butt_end_length,butt_end_length,zButt,butt_height);

		butt[0][0][0]=addBPoint(-0.5,0,0,p0);
		butt[1][0][0]=addBPoint(-0.25,0.0,0,p0);
		butt[2][0][0]=addBPoint(0.0,0.0,0,p0);
		butt[3][0][0]=addBPoint(0.25,0.0,0,p0);
		butt[4][0][0]=addBPoint(0.5,0.0,0,p0);
		butt[0][1][0]=addBPoint(-0.5,1.0,0,p0);
		butt[1][1][0]=addBPoint(-0.25,1.0,0,p0);
		butt[2][1][0]=addBPoint(0.0,1.0,0,p0);
		butt[3][1][0]=addBPoint(0.25,1.0,0,p0);
		butt[4][1][0]=addBPoint(0.5,1.0,0,p0);
		
		Segments p1=new Segments(xc,butt_width,-butt_length,butt_length,zButt,butt_height);
		
		butt[0][0][1]=addBPoint(-0.5,0.0,1.0,p1);	
		butt[1][0][1]=addBPoint(-0.25,0.0,1.0,p1);	
		butt[2][0][1]=addBPoint(0.0,0.0,1.0,p1);
		butt[3][0][1]=addBPoint(0.25,0.0,1.0,p1);	
		butt[4][0][1]=addBPoint(0.5,0.0,1.0,p1);		
		butt[0][1][1]=addBPoint(-0.5,1.0,1.0,p1);
		butt[1][1][1]=addBPoint(-0.25,1.0,1.0,p1);	
		butt[2][1][1]=addBPoint(0.0,1.0,1.0,p1);
		butt[3][1][1]=addBPoint(0.25,1.0,1.0,p1);
		butt[4][1][1]=addBPoint(0.5,1.0,1.0,p1);
		
		for (int i = 0; i < pnx-1; i++) {


			for (int j = 0; j < pny-1; j++) {

				for (int k = 0; k < pnz-1; k++) {




					if(i==0){

						addLine(butt[i][j][k],butt[i][j][k+1],butt[i][j+1][k+1],butt[i][j+1][k],Renderer3D.CAR_LEFT);
					}

				
						
					if(k==0){

						addLine(butt[i][j][k],butt[i][j+1][k],butt[i+1][j+1][k],butt[i+1][j][k],Renderer3D.CAR_BOTTOM);
					
					}
					
					if(k+1==pnz-1){
						addLine(butt[i][j][k+1],butt[i+1][j][k+1],butt[i+1][j+1][k+1],butt[i][j+1][k+1],Renderer3D.CAR_TOP);
					}
					
					if(j==0){
						addLine(butt[i][j][k],butt[i+1][j][k],butt[i+1][j][k+1],butt[i][j][k+1],Renderer3D.CAR_BACK);
					}
					if(j+1==pny-1){
						addLine(butt[i][j+1][k],butt[i][j+1][k+1],butt[i+1][j+1][k+1],butt[i+1][j+1][k],Renderer3D.CAR_FRONT);	
					}
				

					if(i+1==pnx-1){

						addLine(butt[i+1][j][k],butt[i+1][j+1][k],butt[i+1][j+1][k+1],butt[i+1][j][k+1],Renderer3D.CAR_RIGHT);

					}
				}
			}

		}
		
		/////////

		//translatePoints(points,nw_x,nw_y);

		PolygonMesh pm=new PolygonMesh(points,polyData);

		PolygonMesh spm=PolygonMesh.simplifyMesh(pm);
		return spm;

	}




}	