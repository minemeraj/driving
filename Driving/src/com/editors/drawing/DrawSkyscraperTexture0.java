package com.editors.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class DrawSkyscraperTexture0 {


	int bx=10;
	int by=10;





	private static final Color BOTTOM_COLOR=Color.YELLOW;



	public static void main(String[] args) {


		DrawSkyscraperTexture0 sky=new DrawSkyscraperTexture0();
		sky.draw25_36_50();
		//sky.draw1(building_w,building_l,building_h);
	}



	private void draw25_36_50() {
		
		Color BACKGROUND_COLOR0=new Color(153, 153, 153);
		
		Color WALL_COLOR0 = Color.GRAY;
		Color WINDOW_COLOR0 = Color.BLACK;
		

		int building_w=650;
		int building_l=900;
		int building_h=1300;


		int window_width=26;
		int window_height=52;

		double intervalXW=10;
		double intervalXL=10;
		double intervalY=3*26-window_height;

		int w=2*building_l+2*building_w+2*bx;
		int h=building_h+building_l+2*by;

		BufferedImage buf=new BufferedImage(w,h,BufferedImage.TYPE_BYTE_INDEXED);

		File file=new File("texture.jpg");


		int nbxw=(int) (building_w/(window_width+intervalXW));
		int nbxl=(int) (building_l/(window_width+intervalXW));
		int nby=(int) (building_h/(window_height+intervalY));

		//recalculate intervals
		intervalXW=(building_w-window_width*nbxw)/(nbxw+1);
		intervalXL=(building_l-window_width*nbxl)/(nbxl+1);
		intervalY=(building_h-window_height*nby)/(nby+1);


		try {

			Graphics2D bufGraphics=(Graphics2D)buf.getGraphics();

			bufGraphics.setColor(BACKGROUND_COLOR0);
			bufGraphics.fillRect(0,0,w,h);

			//lower base
			int y=by+building_h;
			int x=bx+building_l+building_w;

			bufGraphics.setColor(WALL_COLOR0);
			bufGraphics.drawRect(x, h-(y+building_l), building_w, building_l);


			//faces	    	

			//BACK
			y=by;
			x=bx;
			bufGraphics.setColor(WALL_COLOR0);
			bufGraphics.fillRect(x, h-(y+building_h), building_w, building_h);	    	
			bufGraphics.setColor(WINDOW_COLOR0);
			for(int ii=0;ii<nbxw;ii++){



				for(int jj=0;jj<nby;jj++){

					double dpx=intervalXW+ii*(intervalXW+window_width);
					double dpy=intervalY+jj*(intervalY+window_height);

					bufGraphics.fillRect(
							(int)(x+dpx), 
							(int)(h-(y+building_h-dpy)), 
							window_width, 
							window_height);

				}
			}	



			//RIGHT
			x+=building_w;

			bufGraphics.setColor(WALL_COLOR0);
			bufGraphics.fillRect(x, h-(y+building_h), building_l, building_h);
			bufGraphics.setColor(WINDOW_COLOR0);
			for(int ii=0;ii<nbxl;ii++){



				for(int jj=0;jj<nby;jj++){

					double dpx=intervalXL+ii*(intervalXL+window_width);
					double dpy=intervalY+jj*(intervalY+window_height);

					bufGraphics.fillRect(
							(int)(x+dpx), 
							(int)(h-(y+building_h-dpy)), 
							window_width, 
							window_height);

				}
			}	

			//FRONT
			x+=building_l;	    	
			bufGraphics.setColor(WALL_COLOR0);
			bufGraphics.fillRect(x, h-(y+building_h), building_w, building_h);	    	
			bufGraphics.setColor(WINDOW_COLOR0);
			for(int ii=0;ii<nbxw;ii++){



				for(int jj=0;jj<nby;jj++){

					double dpx=intervalXW+ii*(intervalXW+window_width);
					double dpy=intervalY+jj*(intervalY+window_height);

					bufGraphics.fillRect(
							(int)(x+dpx), 
							(int)(h-(y+building_h-dpy)), 
							window_width, 
							window_height);

				}
			}	


			//LEFT

			x+=building_w;

			bufGraphics.setColor(WALL_COLOR0);
			bufGraphics.fillRect(x, h-(y+building_h), building_l, building_h);
			bufGraphics.setColor(WINDOW_COLOR0);
			for(int ii=0;ii<nbxl;ii++){



				for(int jj=0;jj<nby;jj++){

					double dpx=intervalXL+ii*(intervalXL+window_width);
					double dpy=intervalY+jj*(intervalY+window_height);

					bufGraphics.fillRect(
							(int)(x+dpx), 
							(int)(h-(y+building_h-dpy)), 
							window_width, 
							window_height);

				}
			}	

			x=bx;
			y+=building_h;

			//upper base
			bufGraphics.setColor(WALL_COLOR0);
			bufGraphics.fillRect(x, h-(y+building_l), building_w, building_l);

			ImageIO.write(buf,"gif",file);

			System.out.println("DONE");

		} catch (Exception e) {

			e.printStackTrace();
		}


	}

	private void draw20_40_100() {

		Color BACKGROUND_COLOR0=new Color(156, 156, 156);
		
		Color WALL_COLOR1 =  new Color(210,206,197);
		Color WINDOW_COLOR1 =  new Color(114,149,189);

		int building_w=520;
		int building_l=1040;
		int building_h=2600;

		double story_height=4*26;
		double window_width=2*26;
		double window_height=3.5*26;

		double intervalXW=10;
		double intervalXL=10;
		double intervalY=(story_height-window_height)*0.5;

		int w=2*building_l+2*building_w+2*bx;
		int h=building_h+building_l+2*by;

		BufferedImage buf=new BufferedImage(w,h,BufferedImage.TYPE_BYTE_INDEXED);

		File file=new File("texture.jpg");


		int nbxw=(int) (building_w/(window_width+intervalXW));
		int nbxl=(int) (building_l/(window_width+intervalXW));
		int nby=(int) (building_h/(window_height+intervalY));

		//recalculate intervals
		intervalXW=(building_w-window_width*nbxw)/(nbxw+1);
		intervalXL=(building_l-window_width*nbxl)/(nbxl+1);
		intervalY=(building_h-window_height*nby)/(nby+1);


		try {

			Graphics2D bufGraphics=(Graphics2D)buf.getGraphics();

			bufGraphics.setColor(BACKGROUND_COLOR0);
			bufGraphics.fillRect(0,0,w,h);

			//lower base
			int y=by+building_h;
			int x=bx+building_l+building_w;

			bufGraphics.setColor(WALL_COLOR1);
			bufGraphics.drawRect(x, h-(y+building_l), building_w, building_l);


			//faces	    	

			//BACK
			
			y=by;
			x=bx;
			bufGraphics.setColor(WALL_COLOR1);
			bufGraphics.fillRect(x, h-(y+building_h), building_w, building_h);	    	
			bufGraphics.setColor(WINDOW_COLOR1);
			for(int ii=0;ii<nbxw;ii++){



				for(int jj=0;jj<nby;jj++){

					double dpx=intervalXW+ii*(intervalXW+window_width);
					double dpy=intervalY+jj*(intervalY+window_height);

					bufGraphics.fillRect(
							(int)(x+dpx), 
							(int)(h-(y+building_h-dpy)), 
							(int)window_width, 
							(int)window_height);

				}
			}	



			//RIGHT
			x+=building_w;

			bufGraphics.setColor(WALL_COLOR1);
			bufGraphics.fillRect(x, h-(y+building_h), building_l, building_h);
			bufGraphics.setColor(WINDOW_COLOR1);
			for(int ii=0;ii<nbxl;ii++){



				for(int jj=0;jj<nby;jj++){

					double dpx=intervalXL+ii*(intervalXL+window_width);
					double dpy=intervalY+jj*(intervalY+window_height);

					bufGraphics.fillRect(
							(int)(x+dpx), 
							(int)(h-(y+building_h-dpy)), 
							(int)window_width, 
							(int)window_height);

				}
			}	

			//FRONT
			x+=building_l;	    	
			bufGraphics.setColor(WALL_COLOR1);
			bufGraphics.fillRect(x, h-(y+building_h), building_w, building_h);	    	
			bufGraphics.setColor(WINDOW_COLOR1);
			for(int ii=0;ii<nbxw;ii++){



				for(int jj=0;jj<nby;jj++){

					double dpx=intervalXW+ii*(intervalXW+window_width);
					double dpy=intervalY+jj*(intervalY+window_height);

					bufGraphics.fillRect(
							(int)(x+dpx), 
							(int)(h-(y+building_h-dpy)), 
							(int)window_width, 
							(int)window_height);

				}
			}	


			//LEFT

			x+=building_w;

			bufGraphics.setColor(WALL_COLOR1);
			bufGraphics.fillRect(x, h-(y+building_h), building_l, building_h);
			bufGraphics.setColor(WINDOW_COLOR1);
			for(int ii=0;ii<nbxl;ii++){



				for(int jj=0;jj<nby;jj++){

					double dpx=intervalXL+ii*(intervalXL+window_width);
					double dpy=intervalY+jj*(intervalY+window_height);

					bufGraphics.fillRect(
							(int)(x+dpx), 
							(int)(h-(y+building_h-dpy)), 
							(int)window_width, 
							(int)window_height);

				}
			}	

			x=bx;
			y+=building_h;

			//upper base
			bufGraphics.setColor(WALL_COLOR1);
			bufGraphics.fillRect(x, h-(y+building_l), building_w, building_l);

			ImageIO.write(buf,"gif",file);

			System.out.println("DONE");

		} catch (Exception e) {

			e.printStackTrace();
		}


	}

}
