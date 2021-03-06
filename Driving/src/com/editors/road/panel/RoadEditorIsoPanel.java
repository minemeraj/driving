package com.editors.road.panel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;

import com.BarycentricCoordinates;
import com.CubicMesh;
import com.DrawObject;
import com.LineData;
import com.LineDataVertex;
import com.Point3D;
import com.Point4D;
import com.Polygon3D;
import com.PolygonMesh;
import com.SPLine;
import com.SPNode;
import com.Texture;
import com.ZBuffer;
import com.editors.EditorData;
import com.editors.road.RoadEditor;
import com.main.DrivingFrame;
import com.main.Renderer3D;
import com.main.Road;



public class RoadEditorIsoPanel extends RoadEditorPanel{

    private double deltay=1;
    private double deltax=1;

    private int y0=400;
    private int x0=100;



    private double alfa=Math.PI/3;
    private double cosAlfa=Math.cos(alfa);
    private double sinAlfa=Math.sin(alfa);

    private Point3D projectionNormal=new Point3D(-1/Math.sqrt(3),-1/Math.sqrt(3),1/Math.sqrt(3));

    private double viewDirectionCos=1.0;
    private double viewDirectionSin=0.0;

    private int greenRgb= Color.GREEN.getRGB();

    private Point3D lightDirection=new Point3D(-Math.sqrt(2)/2,-Math.sqrt(2)/2,0);

    protected int POSX=0;
    protected int POSY=0;

    private int minMovement=5;
    private Rectangle visibleArea;

    public RoadEditorIsoPanel(RoadEditor editor, int WIDTH,int HEIGHT) {

        super(editor, WIDTH,HEIGHT);
        initialize();
    }

    @Override
    public void drawRoad(PolygonMesh[] meshes, ArrayList<DrawObject> drawObjects,ArrayList<SPLine> splines,Point3D startPosition,ZBuffer landscapeZbuffer,Graphics2D graph) {


        displayTerrain(landscapeZbuffer,meshes);

        if(!isHide_objects()) {
            displayObjects(drawObjects,null,landscapeZbuffer);
        }
        if(!isHide_splines()) {
            displaySPLines(landscapeZbuffer,splines);
        }

        displayStartPosition(landscapeZbuffer, startPosition);
    }

    @Override
    public void initialize() {

        deltax=4;
        deltay=4;

        POSX=0;
        POSY=1000;

        selectionColor=new Color(255,0,0,127);

        xMovement=2*minMovement;
        yMovement=2*minMovement;

        visibleArea=new Rectangle(0,0,WIDTH,HEIGHT);

    }


    @Override
    public void displayTerrain(ZBuffer roadZbuffer,PolygonMesh[] meshes) {

        int index=0;

        //for(int index=0;index<2;index++){

        PolygonMesh mesh=meshes[index];

        int polySize=mesh.polygonData.size();

        for(int j=0;j<polySize;j++){


            LineData ld=mesh.polygonData.get(j);

            Polygon3D polProjected=buildLightPolygonProjection(ld,mesh.xpoints,mesh.ypoints,mesh.zpoints,index,mesh.getLevel());
            if(!Polygon3D.isIntersect(polProjected,visibleArea)){
                continue;
            }

            Polygon3D p3D=buildTranslatedPolygon3D(ld,mesh.xpoints,mesh.ypoints,mesh.zpoints,index,mesh.getLevel());


            Color selected=null;

            if(ld.isSelected()){

                if(index<0 || index==editor.getACTIVE_PANEL()){
                    selected=selectionColor;
                }
            }


            decomposeClippedPolygonIntoZBuffer(p3D,selected,EditorData.worldTextures[p3D.getIndex()],roadZbuffer,mesh.hashCode());

            if(index==1){

                //build road polgyons

                Polygon3D[] polygons=Road.buildAdditionalRoadPolygons(p3D);

                if(selected==null) {
                    selected=Color.DARK_GRAY;
                }

                for (int i = 0; i < polygons.length; i++) {
                    decomposeClippedPolygonIntoZBuffer(polygons[i],selected,null,roadZbuffer,mesh.hashCode());
                }

            }


        }

        //}


    }

    @Override
    public void displaySPLines(ZBuffer landscapeZbuffer, ArrayList<SPLine> splines) {


        int spz=splines.size();
        for (int i = 0; i < spz; i++) {
            SPLine spline = splines.get(i);

            int hashCode=spline.hashCode();

            ArrayList<PolygonMesh> meshes = spline.getMeshes3D();

            int msz=meshes.size();

            for (int j = 0; j < msz; j++) {

                PolygonMesh mesh = meshes.get(j);

                ArrayList<LineData> polygonData=mesh.polygonData;

                int lsize=polygonData.size();


                for(int k=0;k<lsize;k++){

                    Color selected=null;

                    LineData ld=polygonData.get(k);

                    Polygon3D polProjected=buildLightPolygonProjection(ld,mesh.xpoints,mesh.ypoints,mesh.zpoints,DrivingFrame.ROAD_INDEX,mesh.getLevel());
                    if(!Polygon3D.isIntersect(polProjected,visibleArea)){
                        continue;
                    }

                    Polygon3D p3D=buildTranslatedPolygon3D(ld,mesh.xpoints,mesh.ypoints,mesh.zpoints,DrivingFrame.ROAD_INDEX,mesh.getLevel());

                    decomposeClippedPolygonIntoZBuffer(p3D,selected,EditorData.splinesTextures[ld.getTexture_index()],landscapeZbuffer,hashCode);


                }

            }

            ArrayList<SPNode> nodes = spline.getNodes();

            if(nodes==null) {
                continue;
            }

            int nodesSize=nodes.size();

            for (int k = 0; k < nodesSize; k++) {

                SPNode node = nodes.get(k);

                PolygonMesh pm=node.getRing();

                Texture texture=EditorData.whiteTexture;

                if(node.isSelected()) {
                    texture=EditorData.redTexture;
                }

                int pmSize=pm.polygonData.size();

                for(int l=0;l<pmSize;l++){


                    LineData ld=pm.polygonData.get(l);



                    Polygon3D p3D=buildTranslatedPolygon3D(ld,pm.xpoints,pm.ypoints,pm.zpoints,DrivingFrame.ROAD_INDEX,pm.getLevel());

                    decomposeClippedPolygonIntoZBuffer(p3D,null,texture,landscapeZbuffer,hashCode);

                }


            }


        }

    }



    @Override
    public void displayObjects(ArrayList<DrawObject> drawObjects,Area totalVisibleField,ZBuffer zbuffer){


        Rectangle rect = null;//totalVisibleField.getBounds();
        int objSize=drawObjects.size();
        for(int i=0;i<objSize;i++){

            DrawObject dro=drawObjects.get(i);
            drawPolygonMesh(dro, rect, zbuffer);
        }

    }


    private void drawPolygonMesh(DrawObject dro,Rectangle rect,ZBuffer zbuffer) {

        //if(!totalVisibleField.contains(dro.x-POSX,VIEW_DIRECTION*(dro.y-POSY)))

        //if(rect.y+rect.height<dro.y-POSY)
        //	return;

        PolygonMesh mesh = dro.getMesh();

        Color selected=null;

        if(dro.isSelected()){

            selected=selectionColor;

        }


        decomposeCubicMesh((CubicMesh) mesh,EditorData.objectTextures[dro.getIndex()],zbuffer,selected);

    }

    private void decomposeCubicMesh(CubicMesh cm, Texture texture,ZBuffer zBuffer,Color selected){



        Point3D point000=buildTransformedPoint(cm.point000);

        Point3D point011=buildTransformedPoint(cm.point011);

        Point3D point001=buildTransformedPoint(cm.point001);

        Point3D xVersor=buildTransformedVersor(cm.getXAxis());
        Point3D yVersor=buildTransformedVersor(cm.getYAxis());

        Point3D zVersor=buildTransformedVersor(cm.getZAxis());
        Point3D zMinusVersor=new Point3D(-zVersor.x,-zVersor.y,-zVersor.z);





        int polSize=cm.polygonData.size();
        for(int i=0;i<polSize;i++){

            //int due=(int)(255-i%15);
            //Color col=new Color(due,0,0);

            LineData ld=cm.polygonData.get(i);

            Polygon3D polProjected=buildLightPolygonProjection(ld,cm.xpoints,cm.ypoints,cm.zpoints,cm.getLevel(),cm.getLevel());
            if(!Polygon3D.isIntersect(polProjected,visibleArea)){
                continue;
            }

            Polygon3D polRotate=PolygonMesh.getBodyPolygon(cm.xpoints,cm.ypoints,cm.zpoints,ld,cm.getLevel());
            polRotate.setShadowCosin(ld.getShadowCosin());


            int face=cm.boxFaces[i];

            buildTransformedPolygon(polRotate);


            decomposeCubiMeshPolygon(polRotate,xVersor,yVersor,zVersor,zMinusVersor,cm,point000,point011,point001,face,selected,texture,zBuffer);



        }


    }


    private void decomposeCubiMeshPolygon(

            Polygon3D polRotate,
            Point3D xVersor,
            Point3D yVersor,
            Point3D zVersor,
            Point3D zMinusVersor,
            CubicMesh cm,
            Point3D point000,
            Point3D point011,
            Point3D point001,
            int face,
            Color selected,
            Texture texture,
            ZBuffer zBuffer
            ){

        Point3D xDirection=null;
        Point3D yDirection=null;

        Point3D rotateOrigin=point000;

        int deltaWidth=0;
        int deltaHeight=cm.getDeltaY();

        int deltaTexture=0;



        if(face==Renderer3D.CAR_BOTTOM){
            deltaWidth=cm.getDeltaX()+cm.getDeltaX2();
            xDirection=xVersor;
            yDirection=yVersor;
        }
        if(face==Renderer3D.CAR_FRONT  ){


            deltaWidth=cm.getDeltaX();
            deltaHeight=cm.getDeltaY2();
            xDirection=xVersor;
            yDirection=zMinusVersor;

            rotateOrigin=point011;


        }
        else if(face==Renderer3D.CAR_BACK){
            deltaWidth=cm.getDeltaX();
            deltaHeight=0;
            xDirection=xVersor;
            yDirection=zVersor;


        }
        else if(face==Renderer3D.CAR_TOP){
            deltaWidth=cm.getDeltaX();
            xDirection=xVersor;
            yDirection=yVersor;


        }
        else if(face==Renderer3D.CAR_LEFT) {

            xDirection=zVersor;
            yDirection=yVersor;

        }
        else if(face==Renderer3D.CAR_RIGHT) {

            xDirection=zMinusVersor;
            yDirection=yVersor;

            deltaWidth=cm.getDeltaX2();
            rotateOrigin=point001;
        }



        decomposeClippedPolygonIntoZBuffer(polRotate,selected,texture,zBuffer,xDirection,yDirection,rotateOrigin,deltaTexture+deltaWidth,deltaHeight,cm.hashCode());
    }

    private void decomposeClippedPolygonIntoZBuffer(Polygon3D p3d,Color selected,Texture texture,ZBuffer zbuffer,int hashCode){

        Point3D origin=new Point3D(p3d.xpoints[0],p3d.ypoints[0],p3d.zpoints[0]);
        decomposeClippedPolygonIntoZBuffer(p3d, selected, texture,zbuffer,null,null,origin,0,0,hashCode);

    }



    private void decomposeClippedPolygonIntoZBuffer(Polygon3D p3d,Color selected,Texture texture,ZBuffer zbuffer,
            Point3D xDirection,Point3D yDirection,Point3D origin,int deltaX,int deltaY,int hashCode){

        Point3D normal=Polygon3D.findNormal(p3d);

        int rgb=-1;
        if(selected!=null) {
            rgb=selected.getRGB();
        }

        if(texture!=null && xDirection==null && yDirection==null){

            Point3D p0=new Point3D(p3d.xpoints[0],p3d.ypoints[0],p3d.zpoints[0]);
            Point3D p1=new Point3D(p3d.xpoints[1],p3d.ypoints[1],p3d.zpoints[1]);
            xDirection=(p1.substract(p0)).calculateVersor();

            yDirection=Point3D.calculateCrossProduct(normal,xDirection).calculateVersor();

            //yDirection=Point3D.calculateOrthogonal(xDirection);
        }

        Polygon3D[] triangles = Polygon3D.divideIntoTriangles(p3d);

        for(int i=0;i<triangles.length;i++){

            BarycentricCoordinates bc=new BarycentricCoordinates(triangles[i]);

            //Polygon3D clippedPolygon=Polygon3D.clipPolygon3DInY(triangles[i],(int) (Renderer3D.SCREEN_DISTANCE*2.0/3.0));

            //if(clippedPolygon.npoints==0)
            //	return ;

            Polygon3D[] clippedTriangles = Polygon3D.divideIntoTriangles(triangles[i]);

            for (int j = 0; j < clippedTriangles.length; j++) {


                decomposeTriangleIntoZBufferEdgeWalking( clippedTriangles[j],rgb, texture,zbuffer, xDirection,yDirection,origin, deltaX, deltaY,bc,hashCode);

            }



        }

    }

    /**
     *
     * DECOMPOSE PROJECTED TRIANGLE USING EDGE WALKING AND
     * PERSPECTIVE CORRECT MAPPING
     *
     * @param p3d
     * @param color
     * @param texture
     * @param useLowResolution
     * @param xDirection
     * @param yDirection
     * @param origin
     */
    @Override
    public void decomposeTriangleIntoZBufferEdgeWalking(Polygon3D p3d,int selected,Texture texture,ZBuffer zb,
            Point3D xDirection, Point3D yDirection, Point3D origin,int deltaX,int deltaY,
            BarycentricCoordinates bc,int hashCode) {

        int rgbColor=selected;

        rr=a2*(selected>>16 & mask);
        gg=a2*(selected>>8 & mask);
        bb=a2*(selected & mask);

        Point3D normal=Polygon3D.findNormal(p3d).calculateVersor();

        //boolean isFacing=isFacing(p3d,normal,observerPoint);

        int level=p3d.getLevel();

        double cosin=calculateCosin(p3d);


        Point3D po0=new Point3D(p3d.xpoints[0],p3d.ypoints[0],p3d.zpoints[0]);
        Point3D po1=new Point3D(p3d.xpoints[1],p3d.ypoints[1],p3d.zpoints[1]);
        Point3D po2=new Point3D(p3d.xpoints[2],p3d.ypoints[2],p3d.zpoints[2]);

        Point3D p0=new Point3D(p3d.xpoints[0],p3d.ypoints[0],p3d.zpoints[0]);
        Point3D p1=new Point3D(p3d.xpoints[1],p3d.ypoints[1],p3d.zpoints[1]);
        Point3D p2=new Point3D(p3d.xpoints[2],p3d.ypoints[2],p3d.zpoints[2]);

        p0.rotateZ(POSX,POSY,cosf,sinf);
        p1.rotateZ(POSX,POSY,cosf,sinf);
        p2.rotateZ(POSX,POSY,cosf,sinf);

        double x0=convertX(p0.x,p0.y,p0.z);
        double y0=convertY(p0.x,p0.y,p0.z);
        double z0=p0.y;

        double x1=convertX(p1.x,p1.y,p1.z);
        double y1=convertY(p1.x,p1.y,p1.z);
        double z1=p1.y;


        double x2=convertX(p2.x,p2.y,p2.z);
        double y2=convertY(p2.x,p2.y,p2.z);
        double z2=p2.y;


        //check if triangle is visible
        double maxX=Math.max(x0,x1);
        maxX=Math.max(x2,maxX);
        double maxY=Math.max(y0,y1);
        maxY=Math.max(y2,maxY);
        double minX=Math.min(x0,x1);
        minX=Math.min(x2,minX);
        double minY=Math.min(y0,y1);
        minY=Math.min(y2,minY);

        if(maxX<0 || minX>WIDTH || maxY<0 || minY>HEIGHT) {
            return;
        }

        Point3D[] points=new Point3D[3];

        points[0]=new Point3D(x0,y0,z0,p0.x,p0.y,p0.z);
        points[1]=new Point3D(x1,y1,z1,p1.x,p1.y,p1.z);
        points[2]=new Point3D(x2,y2,z2,p2.x,p2.y,p2.z);

        int mip_map_level=0;

        if(texture!=null){

            mip_map_level=(int) (0.5*Math.log(bc.getRealTriangleArea()/BarycentricCoordinates.getTriangleArea(x0, y0, x1, y1, x2, y2))/Math.log(2));

            int w=texture.getWidth();
            int h=texture.getHeight();

            Point3D pt0=bc.pt0;
            Point3D pt1=bc.pt1;
            Point3D pt2=bc.pt2;

            Point3D p=bc.getBarycentricCoordinates(new Point3D(po0.x,po0.y,po0.z));
            double x= (p.x*(pt0.x)+p.y*pt1.x+(1-p.x-p.y)*pt2.x);
            double y= (p.x*(pt0.y)+p.y*pt1.y+(1-p.x-p.y)*pt2.y);
            points[0].setTexurePositions(x,texture.getHeight()-y);


            p=bc.getBarycentricCoordinates(new Point3D(po1.x,po1.y,po1.z));
            x= (p.x*(pt0.x)+p.y*pt1.x+(1-p.x-p.y)*pt2.x);
            y= (p.x*(pt0.y)+p.y*pt1.y+(1-p.x-p.y)*pt2.y);
            points[1].setTexurePositions(x,texture.getHeight()-y);


            p=bc.getBarycentricCoordinates(new Point3D(po2.x,po2.y,po2.z));
            x= (p.x*(pt0.x)+p.y*pt1.x+(1-p.x-p.y)*pt2.x);
            y= (p.x*(pt0.y)+p.y*pt1.y+(1-p.x-p.y)*pt2.y);
            points[2].setTexurePositions(x,texture.getHeight()-y);

        }

        int upper=0;
        int middle=1;
        int lower=2;

        for(int i=0;i<3;i++){

            if(points[i].y>points[upper].y) {
                upper=i;
            }

            if(points[i].y<points[lower].y) {
                lower=i;
            }

        }
        for(int i=0;i<3;i++){

            if(i!=upper && i!=lower) {
                middle=i;
            }
        }


        //double i_depth=1.0/zs;
        //UPPER TRIANGLE

        Point3D lowP=points[lower];
        Point3D upP=points[upper];
        Point3D midP=points[middle];

        int j0=midP.y>0?(int)midP.y:0;
        int j1=upP.y<HEIGHT?(int)upP.y:HEIGHT;

        for(int j=j0;j<j1;j++){

            double middlex=Point3D.foundXIntersection(upP,lowP,j);
            Point3D intersects = foundPX_PY_PZ_TEXTURE_Intersection(upP,lowP,j);

            double middlex2=Point3D.foundXIntersection(upP,midP,j);
            Point3D intersecte = foundPX_PY_PZ_TEXTURE_Intersection(upP,midP,j);

            Point3D pstart=new Point3D(middlex,j,intersects.p_z,intersects.p_x,intersects.p_y,intersects.p_z,intersects.texture_x,intersects.texture_y);
            Point3D pend=new Point3D(middlex2,j,intersecte.p_z,intersecte.p_x,intersecte.p_y,intersecte.p_z,intersecte.texture_x,intersecte.texture_y);


            //pstart.p_y=pstart.p_x*projectionNormal.x+pstart.p_y*projectionNormal.y+pstart.p_z*projectionNormal.z;
            //pend.p_y=pend.p_x*projectionNormal.x+pend.p_y*projectionNormal.y+pend.p_z*projectionNormal.z;

            if(pstart.x>pend.x){

                Point3D swap= pend;
                pend= pstart;
                pstart=swap;

            }

            int start=(int)pstart.x;
            int end=(int)pend.x;



            double inverse=1.0/(end-start);


            int i0=start>0?start:0;

            for(int i=i0;i<end;i++){

                if(i>=WIDTH) {
                    break;
                }

                int tot=WIDTH*j+i;

                double l=(i-start)*inverse;

                double yi=((1-l)*pstart.p_y+l*pend.p_y);
                double zi=((1-l)*pstart.p_z+l*pend.p_z);

                if(!zb.isToUpdate(yi,zi,tot,level,hashCode)){

                    continue;
                }


                double xi=((1-l)*pstart.p_x+l*pend.p_x);

                double texture_x=(1-l)*pstart.texture_x+l*pend.texture_x;
                double texture_y=(1-l)*pstart.texture_y+l*pend.texture_y;



                if(texture!=null) {
                    rgbColor=texture.getRGBMip((int)texture_x,(int) texture_y,mip_map_level);
                }
                //rgbColor=ZBuffer.pickRGBColorFromTexture(texture,xi,yi,zi,xDirection,yDirection,origin,deltaX, deltaY);
                if(rgbColor==greenRgb) {
                    continue;
                }

                if(selected>-1){



                    int r=(int) (a1*(rgbColor>>16 & mask)+rr);
                    int g=(int) (a1*(rgbColor>>8 & mask)+gg);
                    int b=(int) (a1*(rgbColor & mask)+bb);

                    rgbColor= (255 << 32) + (r << 16) + (g << 8) + b;
                }

                //System.out.println(x+" "+y+" "+tot);

                zb.set(xi,yi,zi,yi,calculateShadowColor(xi,yi,zi,cosin,rgbColor,p3d.isFilledWithWater(),level),level,tot,hashCode);


            }


        }
        //LOWER TRIANGLE
        j0=lowP.y>0?(int)lowP.y:0;
        j1=midP.y<HEIGHT?(int)midP.y:HEIGHT;

        for(int j=j0;j<j1;j++){

            double middlex=Point3D.foundXIntersection(upP,lowP,j);

            Point3D intersects = foundPX_PY_PZ_TEXTURE_Intersection(upP,lowP,j);

            double middlex2=Point3D.foundXIntersection(lowP,midP,j);

            Point3D insersecte = foundPX_PY_PZ_TEXTURE_Intersection(lowP,midP,j);

            Point3D pstart=new Point3D(middlex,j,intersects.p_z,intersects.p_x,intersects.p_y,intersects.p_z,intersects.texture_x,intersects.texture_y);
            Point3D pend=new Point3D(middlex2,j,insersecte.p_z,insersecte.p_x,insersecte.p_y,insersecte.p_z,insersecte.texture_x,insersecte.texture_y);


            //pstart.p_y=pstart.p_x*projectionNormal.x+pstart.p_y*projectionNormal.y+pstart.p_z*projectionNormal.z;
            //pend.p_y=pend.p_x*projectionNormal.x+pend.p_y*projectionNormal.y+pend.p_z*projectionNormal.z;


            if(pstart.x>pend.x){


                Point3D swap= pend;
                pend= pstart;
                pstart=swap;

            }

            int start=(int)pstart.x;
            int end=(int)pend.x;

            double inverse=1.0/(end-start);

            int i0=start>0?start:0;

            for(int i=i0;i<end;i++){

                if(i>=WIDTH) {
                    break;
                }

                int tot=WIDTH*j+i;

                double l=(i-start)*inverse;

                double yi=((1-l)*pstart.p_y+l*pend.p_y);
                double zi=((1-l)*pstart.p_z+l*pend.p_z);

                if(!zb.isToUpdate(yi,zi,tot,level,hashCode) ){

                    continue;
                }



                double xi=((1-l)*pstart.p_x+l*pend.p_x);

                double texture_x=(1-l)*pstart.texture_x+l*pend.texture_x;
                double texture_y=(1-l)*pstart.texture_y+l*pend.texture_y;


                if(texture!=null) {
                    //rgbColor=ZBuffer.pickRGBColorFromTexture(texture,xi,yi,zi,xDirection,yDirection,origin, deltaX,deltaY);
                    rgbColor=texture.getRGBMip((int)texture_x,(int) texture_y,mip_map_level);
                }
                if(rgbColor==greenRgb) {
                    continue;
                }

                if(selected>-1){



                    int r=(int) (a1*(rgbColor>>16 & mask)+rr);
                    int g=(int) (a1*(rgbColor>>8 & mask)+gg);
                    int b=(int) (a1*(rgbColor & mask)+bb);

                    rgbColor= (255 << 32) + (r << 16) + (g << 8) + b;
                }

                //System.out.println(x+" "+y+" "+tot);



                zb.set(xi,yi,zi,yi,calculateShadowColor(xi,yi,zi,cosin,rgbColor,p3d.isFilledWithWater(),level),level,tot,p3d.hashCode());

            }


        }




    }



    @Override
    public double calculateCosin(Polygon3D polReal) {


        Point3D normal = Polygon3D.findNormal(polReal);

        double cosin=Point3D.calculateCosin(normal,lightDirection);

        return cosin;



    }
    @Override
    public int calculateShadowColor(double xi, double yi, double zi, double cosin, int argbs,boolean hasWater,int level) {

        boolean useSelectionColor=isUseSelectionColor(xi, yi, zi, cosin,  argbs, hasWater,level);
        if(useSelectionColor){
            return 0xffffffff;
        }


        double factor=(1*(0.75+0.25*cosin));

        int alphas=0xff & (argbs>>24);
        int rs = 0xff & (argbs>>16);
        int gs = 0xff & (argbs >>8);
        int bs = 0xff & argbs;

        //water effect
        if(hasWater && zi<Road.WATER_LEVEL+MOVZ){
            //factor=factor*0.7;
            rs=gs=0;
        }

        rs=(int) (factor*rs);
        gs=(int) (factor*gs);
        bs=(int) (factor*bs);

        return alphas <<24 | rs <<16 | gs <<8 | bs;

    }


    @Override
    protected boolean isUseSelectionColor(double xi, double yi, double zi,
            double cosin, int argbs, boolean hasWater, int level) {
        if(level==Road.OBJECT_LEVEL) {
            return false;
        }
        return super.isUseSelectionColor(xi, yi, zi, cosin, argbs, hasWater, level);
    }

    private Point3D buildTransformedPoint(Point3D point) {


        return buildTransformedPoint(point.x,point.y,point.z);
    }

    private Point3D buildTransformedPoint(double xp,double yp, double zp) {

        Point3D newPoint=new Point3D();

        double x=xp-POSX;
        double y=yp-POSY;

        newPoint.x=(int) (viewDirectionCos*x+viewDirectionSin*y);
        newPoint.y=(int) (viewDirectionCos*y-viewDirectionSin*x);
        newPoint.z=zp+MOVZ;

        return newPoint;
    }

    private Point3D buildTransformedVersor(Point3D point) {

        Point3D newPoint=new Point3D();



        double x=point.x;
        double y=point.y;
        double z=point.z;

        newPoint.x= (viewDirectionCos*x+viewDirectionSin*y);
        newPoint.y= (viewDirectionCos*y-viewDirectionSin*x);
        newPoint.z=z;

        return newPoint;
    }

    private void buildTransformedPolygon(Polygon3D base) {



        for(int i=0;i<base.npoints;i++){


            double x=base.xpoints[i]-POSX;
            double y=base.ypoints[i]-POSY;

            base.xpoints[i]=(int) (viewDirectionCos*x+viewDirectionSin*y);
            base.ypoints[i]=(int) (viewDirectionCos*y-viewDirectionSin*x);

            base.zpoints[i]=base.zpoints[i]+MOVZ;

        }


    }

    @Override
    public HashMap<Integer, Boolean> selectObjects(int x, int y, ArrayList<DrawObject> drawObjects,boolean toSelect) {

        HashMap<Integer, Boolean> ret=new HashMap<Integer, Boolean>();

        int droSize= drawObjects.size();
        for (int i = 0; i <droSize; i++) {

            DrawObject dro=drawObjects.get(i);

            boolean selected=selectObject(x,y,dro);

            if(selected){

                if(toSelect){
                    dro.setSelected(true);
                }
                editor.setObjectData(dro);
                ret.put(new Integer(i), new Boolean(true));

            }
            else if(!editor.isMultipleSelection()) {
                dro.setSelected(false);
            }

        }

        return ret;
    }


    private boolean selectObject(int x, int y, DrawObject dro) {


        CubicMesh cm = (CubicMesh) dro.getMesh();

        int polSize=cm.polygonData.size();
        for(int i=0;i<polSize;i++){


            LineData ld=cm.polygonData.get(i);
            Polygon3D polRotate=PolygonMesh.getBodyPolygon(cm.xpoints,cm.ypoints,cm.zpoints,ld,cm.getLevel());
            polRotate.setShadowCosin(ld.getShadowCosin());

            buildTransformedPolygon(polRotate);

            Polygon3D poly = builProjectedPolygon(polRotate);

            if(poly.contains(x,y)){

                return true;

            }

        }

        return false;
    }


    @Override
    public HashMap<String, Boolean>  selectSPNodes(int x, int y, ArrayList<SPLine> splines,boolean isToselect) {

        HashMap<String, Boolean> ret=new HashMap<String, Boolean>();

        int spSize=splines.size();
        for (int i = 0; i < spSize; i++) {

            SPLine spline = splines.get(i);

            ArrayList<SPNode> nodes = spline.getNodes();

            if(nodes==null) {
                continue;
            }

            int nodesSize=nodes.size();

            for(int j=0;j<nodesSize;j++){

                SPNode spnode = nodes.get(j);
                PolygonMesh circle = spnode.getCircle();

                int circleSize=circle.polygonData.size();

                for (int k = 0; k < circleSize; k++) {

                    LineData ld=circle.polygonData.get(k);
                    Polygon3D polReal= buildTranslatedPolygon3D(ld,circle.xpoints,circle.ypoints,circle.zpoints,0,0);
                    Polygon3D polProjectd=builProjectedPolygon(polReal);
                    // System.out.println(k+" "+pol);
                    if(polProjectd.contains(x,y)){

                        if(isToselect){

                            spnode.setSelected(true);

                        }
                        ret.put(i+"_"+j, new Boolean(true));
                        editor.setSPLineData(spline,spnode);


                        break;


                    }else{

                        if(!editor.isMultipleSelection()) {
                            spnode.setSelected(false);
                        }


                    }
                }



            }

        }

        return ret;
    }
    @Override
    public HashMap<Integer,Boolean> pickUpPointsWithFastCircle(PolygonMesh mesh) {

        HashMap<Integer, Boolean> map = new HashMap<Integer,Boolean>();


        if(mesh.xpoints==null || editor.fastSelectionCircle==null) {
            return map;
        }


        int xc=editor.fastSelectionCircle.x;
        int yc=editor.fastSelectionCircle.y;

        int rx=editor.fastSelectionCircle.width;

        editor.coordinatesx[editor.getACTIVE_PANEL()].setText("");
        editor.coordinatesy[editor.getACTIVE_PANEL()].setText("");
        editor.coordinatesz[editor.getACTIVE_PANEL()].setText("");


        for(int j=0;j<mesh.xpoints.length;j++){

            Point3D p=new Point3D(mesh.xpoints[j]-POSX,mesh.ypoints[j]-POSY,mesh.zpoints[j]+MOVZ);

            p.rotateZ(POSX,POSY,cosf,sinf);

            double distance=Point3D.distance(xc, yc, 0, p.x,p.y, 0);


            if(distance<rx){

                map.put(new Integer(j), new Boolean(true));


            }

        }


        return map;


    }


    @Override
    public Polygon3D builProjectedPolygon(Polygon3D p3d) {


        Polygon3D pol=new Polygon3D();

        int size=p3d.npoints;


        for(int i=0;i<size;i++){



            double x=p3d.xpoints[i];
            double y=p3d.ypoints[i];
            double z=p3d.zpoints[i];

            Point3D p=new Point3D(x,y,z);

            p.rotateZ(POSX,POSY,cosf,sinf);

            int xx=convertX(p.x,p.y,p.z);
            int yy=convertY(p.x,p.y,p.z);


            pol.addPoint(xx,yy);


        }

        return pol;
    }

    private Polygon3D buildTranslatedPolygon3D(LineData ld,double[] xpoints,double[] ypoints,double[] zpoints,int index,int level) {



        int size=ld.size();

        int[] cxr=new int[size];
        int[] cyr=new int[size];
        int[] czr=new int[size];
        int[] cxtr=new int[size];
        int[] cytr=new int[size];

        for(int i=0;i<size;i++){


            LineDataVertex ldv=ld.getItem(i);
            int num=ldv.getVertex_index();

            Point4D p= new Point4D(xpoints[num],ypoints[num],zpoints[num]);

            //real coordinates

            cxr[i]=(int)(p.x)-POSX;
            cyr[i]=(int)(p.y)-POSY;
            czr[i]=(int)(p.z)+MOVZ;
            cxtr[i]=(int) ldv.getVertex_texture_x();
            cytr[i]=(int) ldv.getVertex_texture_y();

            //if(index==1)
            //	czr[i]+=Road.ROAD_THICKNESS;

        }



        Polygon3D p3dr=new Polygon3D(size,cxr,cyr,czr,cxtr,cytr);
        p3dr.setHexColor(ld.getHexColor());
        p3dr.setIndex(ld.getTexture_index());
        p3dr.setLevel(level);
        p3dr.setIsFilledWithWater(ld.isFilledWithWater());
        p3dr.setWaterPolygon(ld.isWaterPolygon());
        return p3dr;

    }

    private Polygon3D buildLightPolygonProjection(LineData ld,double[] xpoints,double[] ypoints,double[] zpoints,int index,int level) {

        Polygon3D pol=new Polygon3D();

        int size=ld.size();

        for(int i=0;i<size;i++){


            LineDataVertex ldv=ld.getItem(i);
            int num=ldv.getVertex_index();

            Point4D p= new Point4D(xpoints[num],ypoints[num],zpoints[num]);

            //real coordinates

            p.x=(p.x)-POSX;
            p.y=(p.y)-POSY;
            p.z=(p.z)+MOVZ;

            p.rotateZ(POSX,POSY,cosf,sinf);

            int xx=convertX(p.x,p.y,p.z);
            int yy=convertY(p.x,p.y,p.z);


            pol.addPoint(xx,yy);

        }
        return pol;

    }


    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println(evt.getPropertyName());
        if("RoadEditorUndo".equals(evt.getPropertyName())
                || "RoadAltimetryUndo".equals(evt.getPropertyName())
                || "RoadEditorUpdate".equals(evt.getPropertyName())
                )
        {
            /*this.meshes=roadEditor.meshes;

			draw();*/
        }




    }


    @Override
    public int convertX(double sx,double sy,double sz){


        //return x0+(int) (deltax*(sy-sx*sinAlfa));//axonometric formula
        return x0+(int) ((sx*sinAlfa-sy*sinAlfa)/deltay);
    }
    @Override
    public int convertY(double sx,double sy,double sz){


        //return y0+(int) (deltay*(sz-sx*cosAlfa));
        return y0-(int) ((sz+sy*cosAlfa+sx*cosAlfa)/deltay);
    }


    @Override
    /**
     * X points on the z=0 plane
     */
    public int invertX(int xp,int yp) {

        return  (int) (((xp-x0)*cosAlfa-(yp-y0)*sinAlfa)*deltax/(2*sinAlfa*cosAlfa));
    }

    @Override
    /**
     * Y points on the z=0 plane
     */
    public int invertY(int xp,int yp) {

        return  (int) (((xp-x0)*cosAlfa+(yp-y0)*sinAlfa)*deltay/(-2*sinAlfa*cosAlfa));

    }

    @Override

    public void translate(int i, int j) {

        POSX+=i*xMovement*deltax;
        POSY-=j*yMovement*deltay;
    }


    @Override
    public void gotoPosition(int goPOSX, int goPOSY) {
        POSX=goPOSX;
        POSY=goPOSY;
        editor.draw();

    }

    @Override
    public void zoom(int i) {

        double alfa=1.0;

        if(i<0){

            alfa=2.0;

            deltax=deltax*alfa;
            deltay=deltay*alfa;

        }
        else if(i>0){

            alfa=0.5;

            deltax=deltax*alfa;
            deltay=deltay*alfa;

        }

        x0+=(WIDTH*0.5-x0)*(1.0-1.0/alfa);
        y0+=(HEIGHT*0.5-y0)*(1.0-1.0/alfa);
    }

    @Override
    public void mouseDown() {
        y0+=xMovement;

    }


    @Override
    public void mouseUp() {
        y0-=yMovement;

    }

    private class PolygonToOrder{

        private LineData polygon=null;
        private Point3D centroid=null;
        private int index=-1;
        private int indexZ=-1;

        private PolygonToOrder(LineData polygon, Point3D centroid, int index) {

            this.polygon = polygon;
            this.centroid = centroid;
            this.index = index;

        }

        public Point3D getCentroid() {
            return centroid;
        }

        public void setCentroid(Point3D centroid) {
            this.centroid = centroid;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIndexZ() {
            return indexZ;
        }

        public void setIndexZ(int indexZ) {
            this.indexZ = indexZ;
        }



        public LineData getPolygon() {
            return polygon;
        }



        public void setPolygon(LineData polygon) {
            this.polygon = polygon;
        }



    }

    @Override
    public void rotate(int signum) {

        double df=0.1;
        fi+=df*signum;
        sinf=Math.sin(fi);
        cosf=Math.cos(fi);

    }


    @Override
    public void displayStartPosition(ZBuffer landscapeZbuffer, Point3D startPosition) {

        if(startPosition==null) {
            return;
        }

        PolygonMesh ring = EditorData.getRing(startPosition.getX(),startPosition.getY(),25,40,50);


        int lsize=ring.polygonData.size();


        for(int j=0;j<lsize;j++){


            LineData ld=ring.polygonData.get(j);


            Polygon3D p3D=buildTranslatedPolygon3D(ld,ring.xpoints,ring.ypoints,ring.zpoints,DrivingFrame.ROAD_INDEX,ring.getLevel());

            decomposeClippedPolygonIntoZBuffer(p3D,Color.CYAN,EditorData.cyanTexture,landscapeZbuffer,ZBuffer.EMPTY_HASH_CODE);


        }


    }

    @Override
    public int getPOSX() {
        return POSX;
    }

    @Override
    public int getPOSY() {
        return POSY;
    }

    @Override
    double getDeltaY() {
        return deltay;
    }

    @Override
    double getDeltaX() {
        return deltax;
    }

    @Override
    public void changeMotionIncrement(int i) {
        if(i>0){

            xMovement=2*xMovement;
            yMovement=2*yMovement;

        }else{

            if(xMovement==minMovement) {
                return;
            }

            xMovement=xMovement/2;
            yMovement=yMovement/2;

        }

    }

    @Override
    public Rectangle buildSelecctionCircle(MouseEvent e, int rad) {

        int x=invertX(e.getX(), e.getY());
        int y=invertY(e.getX(), e.getY());

        return new Rectangle(x, y, rad, rad);
    }

    @Override
    public HashMap<Integer, Boolean> pickUpPoygonsWithFastCircle(PolygonMesh mesh) {

        HashMap<Integer, Boolean> map = new HashMap<Integer,Boolean>();


        if(mesh.xpoints==null || editor.fastSelectionCircle==null) {
            return map;
        }


        int xc=editor.fastSelectionCircle.x;
        int yc=editor.fastSelectionCircle.y;

        int rx=editor.fastSelectionCircle.width;

        int sizel=mesh.polygonData.size();


        for(int j=0;j<sizel;j++){

            LineData ld=mesh.polygonData.get(j);

            Polygon3D pol= PolygonMesh.getBodyPolygon( mesh.xpoints,mesh.ypoints,mesh.zpoints, ld,mesh.getLevel());
            pol.translate(-POSX, -POSY);
            Polygon3D.rotate(pol, new Point3D(POSX,POSY,0), fi);

            boolean selected=false;


            Point3D centroid = Polygon3D.findCentroid(pol);

            double distance=Point3D.distance(xc, yc, 0, centroid.x,centroid.y, 0);

            if(distance<rx){

                map.put(new Integer(j), new Boolean(true));
                selected=true;

            }


            if(!selected){

                if(Polygon3D.isIntersect(new Point3D(xc,yc,0),pol.getBounds())){

                    map.put(new Integer(j), new Boolean(true));
                    selected=true;
                }

            }

        }


        return map;


    }

    @Override
    public Polygon3D getInterpolatingPoygonFromPoint(PolygonMesh mesh,double x, double y) {

        if(mesh.xpoints==null || editor.fastSelectionCircle==null) {
            return null;
        }

        int sizel=mesh.polygonData.size();

        for(int j=0;j<sizel;j++){

            LineData ld=mesh.polygonData.get(j);

            Polygon3D pol= PolygonMesh.getBodyPolygon( mesh.xpoints,mesh.ypoints,mesh.zpoints, ld,mesh.getLevel());
            pol.translate(-POSX, -POSY);
            Polygon3D.rotate(pol, new Point3D(POSX,POSY,0), fi);

            if(Polygon3D.isIntersect(new Point3D(x,y,0),pol.getBounds())){
                return pol;
            }

        }
        return null;
    }


}
