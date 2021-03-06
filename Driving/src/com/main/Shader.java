package com.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;

import com.CubicMesh;
import com.DrawObject;
import com.LineData;
import com.Point3D;
import com.Point4D;
import com.Polygon3D;
import com.PolygonMesh;
import com.ZBuffer;

class Shader extends Renderer3D{


    protected ZBuffer lightZbuffer;

    private int[] stencilZbuffer;

    private ShadowVolume[] shadowVolumes=null;



    @Override
    public void buildScreen(BufferedImage buf) {

        int length=roadZbuffer.getSize();


        for(int i=0;i<length;i++){


            //set
            if(roadZbuffer.getZ(i)>0) {
                roadZbuffer.setRgbColor( stencil(roadZbuffer.getRgbColor(i), stencilZbuffer[i]),i);
            }



        }
        buf.getRaster().setDataElements( 0,0,WIDTH,HEIGHT,roadZbuffer.getRgbColor());

        for(int i=0;i<length;i++){

            //clean
            roadZbuffer.set(0,0,0,0,greenRgb,Road.EMPTY_LEVEL,i,ZBuffer.EMPTY_HASH_CODE);
            stencilZbuffer[i]=0;


        }

        //buf.setRGB(0,0,WIDTH,HEIGHT,rgb,0,WIDTH);


    }

    private int stencil(int rgbColor, int stencil) {

        if(stencil==0) {
            return rgbColor;
        }


        int alphas=0xff & (rgbColor>>24);
        int rs = 0xff & (rgbColor>>16);
        int gs = 0xff & (rgbColor >>8);
        int bs = 0xff & rgbColor;

        rs=(int) (0.5*rs);
        gs=(int) (0.5*gs);
        bs=(int) (0.5*bs);

        return alphas <<24 | rs <<16 | gs <<8 | bs;
    }
    @Override
    public void buildNewZBuffers() {




        super.buildNewZBuffers();

        int lenght=roadZbuffer.getSize();

        for(int i=0;i<lenght;i++){

            roadZbuffer.setRgbColor(greenRgb, i);
            roadZbuffer.setZ(0, i);
            roadZbuffer.setLevel(Road.EMPTY_LEVEL, i);
        }

        stencilZbuffer=new int[lenght];

    }



    /*@Deprecated
     * public int calculateShadowColor(double xi, double yi, double zi, double cosin, int argbs) {


		double factor=(lightIntensity*(0.75+0.25*cosin));

		if(!isShadowMap){

			//here calculate shadow with map
			Point4D p_light=calculateLightTransformedPoint(new Point4D(xi,yi,zi),false);

			int p_screen_light_x=(int)calculPespX(p_light);
			int p_screen_light_y=(int)calculPerspY(p_light);

			if(p_screen_light_x>0 && p_screen_light_x<WIDTH && p_screen_light_y>0 && p_screen_light_y<HEIGHT){


				int tot=WIDTH*p_screen_light_y+p_screen_light_x;

				ZBuffer zb_light=lightZbuffer[tot];

				//the depth is yi,adding a bias of 1
				if(zb_light.getZ()<p_light.y-1 && zb_light.p_z>p_light.z+2)
					factor=factor*0.75;

			}

		}

		int alphas=0xff & (argbs>>24);
		int rs = 0xff & (argbs>>16);
		int gs = 0xff & (argbs >>8);
		int bs = 0xff & argbs;

		rs=(int) (factor*rs);
		gs=(int) (factor*gs);
		bs=(int) (factor*bs);

		return alphas <<24 | rs <<16 | gs <<8 | bs;

	}*/




    @Deprecated
    public void calculateShadowMap() {}


    Point4D calculateLightTransformedPoint(Point3D p,boolean isReal){

        Point4D p_light=new Point4D();

        if(isReal){



            p_light.x=lightPoint.cos[0][0]*(p.x-lightPoint.position.x)+lightPoint.cos[0][1]*(p.y-lightPoint.position.y)+lightPoint.cos[0][2]*(p.z-lightPoint.position.z);
            p_light.y=lightPoint.cos[1][0]*(p.x-lightPoint.position.x)+lightPoint.cos[1][1]*(p.y-lightPoint.position.y)+lightPoint.cos[1][2]*(p.z-lightPoint.position.z);
            p_light.z=lightPoint.cos[2][0]*(p.x-lightPoint.position.x)+lightPoint.cos[2][1]*(p.y-lightPoint.position.y)+lightPoint.cos[2][2]*(p.z-lightPoint.position.z);

        }
        else{



            double lPoint_x=lightPoint.position.x-POSX;
            double lPoint_y=lightPoint.position.y-POSY;
            double lPoint_z=lightPoint.position.z+MOVZ;

            double p_x=p.x;
            double p_y=p.y;
            double p_z=p.z;

            if(VIEW_TYPE==FRONT_VIEW){



            }
            else{
                // reverse rear point
                p_x=-p_x;
                p_y=2*SCREEN_DISTANCE-p_y;

            }

            //System.out.println(lPoint);

            p_light.x=lightPoint.cos[0][0]*(p_x-lPoint_x)+lightPoint.cos[0][1]*(p_y-lPoint_y)+lightPoint.cos[0][2]*(p_z-lPoint_z);
            p_light.y=lightPoint.cos[1][0]*(p_x-lPoint_x)+lightPoint.cos[1][1]*(p_y-lPoint_y)+lightPoint.cos[1][2]*(p_z-lPoint_z);
            p_light.z=lightPoint.cos[2][0]*(p_x-lPoint_x)+lightPoint.cos[2][1]*(p_y-lPoint_y)+lightPoint.cos[2][2]*(p_z-lPoint_z);

        }


        return p_light;

    }



    void calculateLightTransformedPolygon(Polygon3D pol,boolean isReal){

        for (int i = 0; i < pol.npoints; i++) {

            Point3D p=new Point3D(pol.xpoints[i],pol.ypoints[i],pol.zpoints[i]);

            Point3D p_light=calculateLightTransformedPoint(p,isReal);

            pol.xpoints[i]=(int) p_light.x;
            pol.ypoints[i]=(int) p_light.y;
            pol.zpoints[i]=(int) p_light.z;

        }


    }
    @Deprecated
    public Point4D calculateLightTransformedVersor(Point3D p,boolean isReal){

        Point4D p_light=new Point4D();

        p_light.x=lightPoint.cos[0][0]*(p.x)+lightPoint.cos[0][1]*(p.y)+lightPoint.cos[0][2]*(p.z);
        p_light.y=lightPoint.cos[1][0]*(p.x)+lightPoint.cos[1][1]*(p.y)+lightPoint.cos[1][2]*(p.z);
        p_light.z=lightPoint.cos[2][0]*(p.x)+lightPoint.cos[2][1]*(p.y)+lightPoint.cos[2][2]*(p.z);

        return p_light;
    }
    @Deprecated
    public double calculatePercentageCloserFiltering(double[] pcf_values, double z) {

        double total=0;


        for (int i = 0; i < pcf_values.length; i++) {

            //bias=4
            if(z>4+pcf_values[i]) {
                total=total+1.0;
            }
        }

        return total/pcf_values.length;
    }

    @Deprecated
    public void processShadowMapForPCF() {



        for(int y=0;y<HEIGHT;y++){

            for (int x = 0; x < WIDTH ; x++) {



                ArrayList<Double> values=new ArrayList<Double>();

                int tot=y*WIDTH+x;


                int minX=x-1;
                int maxX=x+1;

                if(minX<0) {
                    minX=x;
                }

                if(maxX>=WIDTH) {
                    maxX=WIDTH-1;
                }

                int minY=y-1;
                int maxY=y+1;

                if(minY<0) {
                    minY=y;
                }

                if(maxY>=HEIGHT) {
                    maxY=HEIGHT-1;
                }

                for(int i=minX;i<maxX;i++){

                    for (int j = minY; j < maxY; j++) {

                        values.add(lightZbuffer.getZ(j*WIDTH+i));

                    }


                }


                /*int size=values.size();
				zb.pcf_values=new double[size];

				for (int c = 0; c < size; c++) {
					zb.pcf_values[c]=(Double)values.get(c);
				}*/

            }

        }



    }

    @Deprecated
    void calculateShadowVolumes(DrawObject[] drawObjects ){


        shadowVolumes=new ShadowVolume[drawObjects.length];

        for(int i=0;i<drawObjects.length;i++){

            DrawObject dro=drawObjects[i];
            shadowVolumes[i]=buildShadowVolumeBox((CubicMesh)dro.getMesh());
        }


    }

    private  ShadowVolume buildShadowVolumeBox(CubicMesh cm) {

        ShadowVolume shadowVolume=initShadowVolume(cm);
        buildShadowVolumeBox(shadowVolume,cm);

        return shadowVolume;

    }

    ShadowVolume initShadowVolume(CubicMesh cm) {

        ShadowVolume shadowVolume=new ShadowVolume();
        shadowVolume.allTriangles=new ArrayList<ShadowTriangle>();

        int polSize=cm.polygonData.size();



        for(int i=0;i<polSize;i++){

            LineData ld=cm.polygonData.get(i);

            LineData[] triangles = LineData.divideIntoTriangles(ld);

            for (int j = 0; j < triangles.length; j++) {


                ShadowTriangle st=new ShadowTriangle(triangles[j]);

                shadowVolume.allTriangles.add(st);
            }

        }

        int aSize= shadowVolume.allTriangles.size();

        for (int i = 0; i <aSize; i++) {



            ShadowTriangle triangle0 = shadowVolume.allTriangles.get(i);


            //ADJACENT TRIANGLES

            ArrayList<Integer> adjacentTriangles=new ArrayList<Integer>();

            for (int j = 0; j <aSize; j++) {

                if(j==i) {
                    continue;
                }

                int commonVertices=0;

                LineData triangle1 = shadowVolume.allTriangles.get(j);

                int iSize=triangle1.size();

                for (int k = 0; k < iSize; k++) {

                    if(
                            triangle0.getIndex(0)==triangle1.getIndex(k) ||
                            triangle0.getIndex(1)==triangle1.getIndex(k) ||
                            triangle0.getIndex(2)==triangle1.getIndex(k)

                            ) {
                        commonVertices++;
                    }

                    if(k==iSize-2 && commonVertices<1) {
                        break;
                    }

                    if(commonVertices==2){
                        adjacentTriangles.add(new Integer(j));
                        break;
                    }

                }



                //can't have more than 3 adjacent triangles?


            }

            if(adjacentTriangles.size()>0) {
                triangle0.setAdjacentTriangles(adjacentTriangles);
            }
        }


        return shadowVolume;
    }

    ShadowVolume buildShadowVolumeBox(ShadowVolume shadowVolume,CubicMesh cm) {

        shadowVolume.initFaces();

        ArrayList<LineData> edges=new ArrayList<LineData>();

        int aSize= shadowVolume.allTriangles.size();

        Hashtable hEdges=new Hashtable();

        for (int i = 0; i <aSize; i++) {



            ShadowTriangle triangle0 = shadowVolume.allTriangles.get(i);

            Polygon3D polTriangle0=PolygonMesh.getBodyPolygon(cm.xpoints,cm.ypoints,cm.zpoints,triangle0,cm.getLevel());

            if(!isFacing(polTriangle0,Polygon3D.findNormal(polTriangle0),lightPoint.position)) {
                continue;
            }

            //ADJACENT TRIANGLES

            int[] adjacentTriangles = triangle0.getAdjacentTriangles();


            //FINDING EDGES



            for (int j = 0; j < adjacentTriangles.length; j++) {
                LineData triangle1 =  shadowVolume.allTriangles.get(adjacentTriangles[j]);
                Polygon3D adjPolTriangle=PolygonMesh.getBodyPolygon(cm.xpoints,cm.ypoints,cm.zpoints,triangle1,cm.getLevel());

                if(

                        ! isFacing(adjPolTriangle,Polygon3D.findNormal(adjPolTriangle),lightPoint.position)

                        ){




                    for (int k = 0; k < triangle0.size(); k++) {

                        if(
                                (
                                        triangle0.getIndex(k)==triangle1.getIndex(0)  ||
                                        triangle0.getIndex(k)==triangle1.getIndex(1)  ||
                                        triangle0.getIndex(k)==triangle1.getIndex(2)
                                        )
                                &&
                                (

                                        triangle0.getIndex((k+1)%3)==triangle1.getIndex(0)  ||
                                        triangle0.getIndex((k+1)%3)==triangle1.getIndex(1)  ||
                                        triangle0.getIndex((k+1)%3)==triangle1.getIndex(2)
                                        )

                                ){

                            LineData edge=new LineData();

                            edge.addIndex(triangle0.getIndex(k));
                            edge.addIndex(triangle0.getIndex((k+1)%3));

                            String key0=edge.getIndex(0)+"_"+edge.getIndex(1);
                            String key1=edge.getIndex(1)+"_"+edge.getIndex(0);
                            if(hEdges.get(key0)==null && hEdges.get(key1)==null ){

                                edges.add(edge);

                                hEdges.put(key0,"");
                                hEdges.put(key1,"");
                            }
                        }
                    }




                }

            }


        }

        //BUILD FRONT CAP

        double average_y=0;

        for (int i = 0; i < shadowVolume.allTriangles.size(); i++) {


            LineData triangle0 = shadowVolume.allTriangles.get(i);
            Polygon3D polTriangle0=PolygonMesh.getBodyPolygon(cm.xpoints,cm.ypoints,cm.zpoints,triangle0,cm.getLevel());

            if(isFacing(polTriangle0,Polygon3D.findNormal(polTriangle0),lightPoint.position)){
                shadowVolume.addToFrontCap(polTriangle0);
                average_y+=Polygon3D.findCentroid(polTriangle0) .y;
            }
        }

        //double  extrusion=2.0;

        average_y=average_y/shadowVolume.frontCap.size();
        double  extrusion=(1000+average_y-lightPoint.position.y)/(average_y-lightPoint.position.y);

        //System.out.println(average_y+" "+extrusion+" "+(average_y-lightPoint.position.y));

        //BUILD BACK CAP

        for (int i = 0; i < shadowVolume.frontCap.size(); i++) {

            Polygon3D triangle_front = shadowVolume.frontCap.get(i);

            Polygon3D triangle_back=new Polygon3D(3);

            //invert vertices order:j-> 2-j
            for (int j = 0; j < triangle_front.npoints; j++) {


                triangle_back.xpoints[j]=(int) (extrusion*(triangle_front.xpoints[2-j]-lightPoint.position.x)+lightPoint.position.x);
                triangle_back.ypoints[j]=(int) (extrusion*(triangle_front.ypoints[2-j]-lightPoint.position.y)+lightPoint.position.y);
                triangle_back.zpoints[j]=(int) (extrusion*(triangle_front.zpoints[2-j]-lightPoint.position.z)+lightPoint.position.z);

            }


            shadowVolume.addToBackCap(triangle_back);

        }



        //BUILD FACES

        int eSize=edges.size();

        for (int j = 0; j < eSize; j++) {

            LineData edge = edges.get(j);

            ArrayList<Point3D> facePoints=new ArrayList<Point3D>();

            Point3D p0=new Point3D(cm.xpoints[edge.getIndex(1)],cm.ypoints[edge.getIndex(1)],cm.zpoints[edge.getIndex(1)]);
            Point3D p1=new Point3D(cm.xpoints[edge.getIndex(0)],cm.ypoints[edge.getIndex(0)],cm.zpoints[edge.getIndex(0)]);

            //cast to int the points cause the front cap triangles are build with integer coordinates
            Point3D p2=new Point3D(
                    extrusion*((int)p1.x-lightPoint.position.x)+lightPoint.position.x,
                    extrusion*((int)p1.y-lightPoint.position.y)+lightPoint.position.y,
                    extrusion*((int)p1.z-lightPoint.position.z)+lightPoint.position.z
                    );

            Point3D p3=new Point3D(
                    extrusion*((int)p0.x-lightPoint.position.x)+lightPoint.position.x,
                    extrusion*((int)p0.y-lightPoint.position.y)+lightPoint.position.y,
                    extrusion*((int)p0.z-lightPoint.position.z)+lightPoint.position.z
                    );

            facePoints.add(p0);
            facePoints.add(p1);
            facePoints.add(p2);
            facePoints.add(p3);

            Polygon3D pol=new Polygon3D(facePoints);
            shadowVolume.addToFaces(pol);

        }


        shadowVolume.buildPolygonsArray();

        return shadowVolume;

    }

    /**
     *  CALCULATE SHADOW VOLUMES
     *
     *	RENDER NORMAL Z BUFFER SCREEN
     *
     *	RENDER WITHOUT DRAWING AND Z-BUFFERING THE SHADOW VOLUMES
     *
     *	RENDER FRONT AND BACK FACING FACES, DECREMENT AND INCREMENT STENCIL BUFFER
     *
     *	FINALLY APPLY STENCIL BUFFER
     *
     *
     * @param p
     * @param observer
     * @param shadowVolumes
     * @return
     */

    public void calculateStencilBuffer( ){

        isStencilBuffer=true;

        for (int i = 0; i < shadowVolumes.length; i++) {

            ShadowVolume sv= shadowVolumes[i];

            for (int j = 0; j < sv.allPolygons.length; j++) {

                Polygon3D pol = sv.allPolygons[j];

                Polygon3D polTrans=pol.clone();
                buildTransformedPolygon(polTrans);

                decomposeClippedPolygonIntoZBuffer(polTrans,Color.red,null,roadZbuffer,-1);
            }

        }

        isStencilBuffer=false;

    }

    /**
     * Z-FAIL STENCIL BUFFER
     *
     */
    @Override
    public void stencilBuffer(int tot, boolean isFacing) {

        if(isFacing) {
            stencilZbuffer[tot]-=1;
        } else {
            stencilZbuffer[tot]+=1;
        }

    }

    class ShadowVolume{

        private ArrayList<Polygon3D> frontCap=null;
        private ArrayList<Polygon3D> backCap=null;
        private ArrayList<Polygon3D> faces=null;

        private ArrayList<ShadowTriangle> allTriangles=null;

        Polygon3D[] allPolygons=null;

        private void addToFrontCap(Polygon3D pol){

            frontCap.add(pol);

        }

        private void addToBackCap(Polygon3D pol){

            backCap.add(pol);

        }

        private void addToFaces(Polygon3D pol){

            faces.add(pol);

        }

        private void initFaces(){

            frontCap=new ArrayList<Polygon3D>();
            backCap=new ArrayList<Polygon3D>();
            faces=new ArrayList<Polygon3D>();


        }

        private void buildPolygonsArray(){

            int size=frontCap.size()+backCap.size()+faces.size();

            ArrayList<Polygon3D> vAllpolygons=new ArrayList<Polygon3D>(size);

            int counter=0;
            int sz0=frontCap.size();
            for (int i = 0; i < sz0; i++) {
                Polygon3D pol = frontCap.get(i);
                //allPolygons[counter]=pol;
                vAllpolygons.add(pol);
                counter++;
            }
            int sz1=faces.size();
            for (int i = 0; i < sz1; i++) {
                Polygon3D pol = faces.get(i);
                vAllpolygons.add(pol);
                counter++;
            }
            int sz2=backCap.size();
            for (int i = 0; i < sz2; i++) {
                Polygon3D pol = backCap.get(i);
                vAllpolygons.add(pol);
                counter++;
            }


            allPolygons=new Polygon3D[vAllpolygons.size()];
            int sza=vAllpolygons.size();
            for (int i = 0; i < sza; i++) {
                allPolygons[i]= vAllpolygons.get(i);
            }

        }

        public Polygon3D[] getAllPolygons() {
            return allPolygons;
        }

        public void setAllPolygons(Polygon3D[] allPolygons) {
            this.allPolygons = allPolygons;
        }

    }

    private class ShadowTriangle extends LineData {

        private ShadowTriangle(LineData ld) {

            int sz=ld.size();
            for(int i=0;i<sz;i++){



                addIndex(ld.getIndex(i));


            }
        }

        public void setAdjacentTriangles(ArrayList<Integer> vAdjacentTriangles) {

            adjacentTriangles=new int[vAdjacentTriangles.size()];

            for (int i = 0; i < vAdjacentTriangles.size(); i++) {
                adjacentTriangles[i]= vAdjacentTriangles.get(i).intValue();
            }

        }

        public int[] getAdjacentTriangles() {
            return adjacentTriangles;
        }



        private int[] adjacentTriangles=null;

    }

}
