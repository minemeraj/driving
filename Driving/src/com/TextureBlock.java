package com;

public class TextureBlock {
	
	public Point3D[][] upperBase=null;
	public Point3D[][] lowerBase=null;
	public Point3D[][] lateralFaces=null; 
	
	public int N_FACES=0;
	public double texture_side_dy;
	public double texture_side_dx;
	public int numx;
	public int numy;
	public int numz;
	
	boolean isDrawUpperBase=true;
	boolean isDrawLowerBase=true;
	public double len;
	public int vlen;
	
	public TextureBlock(int numx,int numy,int numz,
			double DX,double DY,double DZ,double x0,double y0){
		
		this.numx=numx;
		this.numy=numy;
		this.numz=numz;
		
		N_FACES=numx*2+(numy-2)*2;
		
		len=2*(DX+DY);
		vlen=(int) (DZ+DY*2);
		
		texture_side_dy=(DY/(numy-1));
		texture_side_dx=(DX/(numx-1));
		
		upperBase=new Point3D[numx][numy];
		
		double baseY=DZ+DY;
		
		for (int i = 0; i <numx; i++) {
			
			for (int j = 0; j < numy; j++) {
				
				double x=x0+texture_side_dx*i;
				double y=y0+texture_side_dy*j+baseY;
				
				upperBase[i][j]=new Point3D(x,y,0);
			}
			
		}
	
		
		lowerBase=new Point3D[numx][numy];
		
		for (int i = 0; i <numx; i++) {
			
			for (int j = 0; j < numy; j++) {
				
				double x=x0+texture_side_dx*i;
				double y=y0+texture_side_dy*j;
				
				lowerBase[i][j]=new Point3D(x,y,0);
			}
			
		}
		
		double texture_side_dz= (DZ/(numz-1));
			
		lateralFaces=new Point3D[N_FACES+1][numz];

		for(int j=0;j<numz;j++){
			
			double y=y0+DY+texture_side_dz*j;

			//texture is open and periodical:
			
			double x=x0; 

			for (int i = 0; i <=N_FACES; i++) {

			
				

				lateralFaces[i][j]=new Point3D(x,y,0);
				
				double dx=texture_side_dx;
				
				if(i%(numx+numy-2)>=numx-1)
					dx=texture_side_dy;
				
				x+=dx;

			}
			
		}	
	}

	public boolean isDrawUpperBase() {
		return isDrawUpperBase;
	}

	public void setDrawUpperBase(boolean isDrawUpperBase) {
		this.isDrawUpperBase = isDrawUpperBase;
	}

	public boolean isDrawLowerBase() {
		return isDrawLowerBase;
	}

	public void setDrawLowerBase(boolean isDrawLowerBase) {
		this.isDrawLowerBase = isDrawLowerBase;
	}

	public double getLen() {
		return len;
	}

	public void setLen(double len) {
		this.len = len;
	}

	public int getVlen() {
		return vlen;
	}

	public void setVlen(int vlen) {
		this.vlen = vlen;
	}

}
