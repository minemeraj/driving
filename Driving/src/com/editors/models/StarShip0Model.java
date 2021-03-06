package com.editors.models;

import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.util.Vector;

import com.BPoint;
import com.Point3D;
import com.Segments;
import com.main.Renderer3D;

public class StarShip0Model extends VehicleModel{

    private BPoint[][] head;
    private BPoint[][] body;
    private BPoint[][] leftWing;
    private BPoint[][] rightWing;

    public static final String NAME="Starship";

    //body textures
    protected int[][] tBo= null;

    public StarShip0Model(
            double dx, double dy, double dz,
            double dxf, double dyf, double dzf,
            double dxr, double dyr,	double dzr,
            double dxRoof,double dyRoof,double dzRoof,
            double rearOverhang, double frontOverhang,
            double rearOverhang1, double frontOverhang1
            ) {
        super();
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;

        this.dxFront = dxf;
        this.dyFront = dyf;
        this.dzFront = dzf;

        this.dxRear = dxr;
        this.dyRear = dyr;
        this.dzRear = dzr;

        this.dxRoof = dxRoof;
        this.dyRoof = dyRoof;
        this.dzRoof = dzRoof;

        this.rearOverhang = rearOverhang;
        this.frontOverhang = frontOverhang;

        this.rearOverhang1 = rearOverhang1;
        this.frontOverhang1 = frontOverhang1;
    }



    @Override
    public void initMesh() {

        int c=0;
        c = initDoubleArrayValues(tBo = new int[1][4], c);

        int nz=2;

        buildBody();

        buildTextures();

        //faces
        int NF=6*4;

        faces=new int[NF][3][4];

        int counter=0;
        counter=buildFaces(counter,nz);


    }

    private int buildFaces(int counter, int nz) {
        //head
        faces[counter++]=buildFace(Renderer3D.CAR_BOTTOM, head[0][0],head[0][3],head[0][2],head[0][1], tBo[0]);

        for (int k = 0; k < nz; k++) {

            faces[counter++]=buildFace(Renderer3D.CAR_LEFT, head[k][0],head[k+1][0],head[k+1][3],head[k][3], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_BACK, head[k][0],head[k][1],head[k+1][1],head[k+1][0], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_RIGHT, head[k][1],head[k][2],head[k+1][2],head[k+1][1], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_FRONT, head[k][2],head[k][3],head[k+1][3],head[k+1][2], tBo[0]);

        }

        faces[counter++]=buildFace(Renderer3D.CAR_TOP,head[nz-1][0],head[nz-1][1],head[nz-1][2],head[nz-1][3], tBo[0]);

        //body

        faces[counter++]=buildFace(Renderer3D.CAR_BOTTOM, body[0][0],body[0][3],body[0][2],body[0][1], tBo[0]);

        for (int k = 0; k < nz; k++) {

            faces[counter++]=buildFace(Renderer3D.CAR_LEFT, body[k][0],body[k+1][0],body[k+1][3],body[k][3], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_BACK, body[k][0],body[k][1],body[k+1][1],body[k+1][0], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_RIGHT, body[k][1],body[k][2],body[k+1][2],body[k+1][1], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_FRONT, body[k][2],body[k][3],body[k+1][3],body[k+1][2], tBo[0]);

        }

        faces[counter++]=buildFace(Renderer3D.CAR_TOP,body[nz-1][0],body[nz-1][1],body[nz-1][2],body[nz-1][3], tBo[0]);

        //left wing

        faces[counter++]=buildFace(Renderer3D.CAR_BOTTOM, leftWing[0][0],leftWing[0][3],leftWing[0][2],leftWing[0][1], tBo[0]);

        for (int k = 0; k < nz; k++) {

            faces[counter++]=buildFace(Renderer3D.CAR_LEFT, leftWing[k][0],leftWing[k+1][0],leftWing[k+1][3],leftWing[k][3], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_BACK, leftWing[k][0],leftWing[k][1],leftWing[k+1][1],leftWing[k+1][0], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_RIGHT, leftWing[k][1],leftWing[k][2],leftWing[k+1][2],leftWing[k+1][1], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_FRONT, leftWing[k][2],leftWing[k][3],leftWing[k+1][3],leftWing[k+1][2], tBo[0]);

        }

        faces[counter++]=buildFace(Renderer3D.CAR_TOP,leftWing[nz-1][0],leftWing[nz-1][1],leftWing[nz-1][2],leftWing[nz-1][3], tBo[0]);

        //right wing

        faces[counter++]=buildFace(Renderer3D.CAR_BOTTOM, rightWing[0][0],rightWing[0][3],rightWing[0][2],rightWing[0][1], tBo[0]);

        for (int k = 0; k < nz; k++) {

            faces[counter++]=buildFace(Renderer3D.CAR_LEFT, rightWing[k][0],rightWing[k+1][0],rightWing[k+1][3],rightWing[k][3], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_BACK, rightWing[k][0],rightWing[k][1],rightWing[k+1][1],rightWing[k+1][0], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_RIGHT, rightWing[k][1],rightWing[k][2],rightWing[k+1][2],rightWing[k+1][1], tBo[0]);
            faces[counter++]=buildFace(Renderer3D.CAR_FRONT, rightWing[k][2],rightWing[k][3],rightWing[k+1][3],rightWing[k+1][2], tBo[0]);

        }

        faces[counter++]=buildFace(Renderer3D.CAR_TOP,rightWing[nz-1][0],rightWing[nz-1][1],rightWing[nz-1][2],rightWing[nz-1][3], tBo[0]);


        return counter;
    }



    private void buildTextures() {

        texturePoints=new Vector<Point3D>();

        //Texture points

        double y=by;
        double x=bx;

        addTRect(x,y,dx,dy);


        IMG_WIDTH=(int) (2*bx+dx);
        IMG_HEIGHT=(int) (2*by+dy);

    }



    private void buildBody() {

        points=new Vector<Point3D>();

        Segments s0=new Segments(x0-dxFront*0.5,dxFront,y0+dy,dyFront,z0+dz*2,dzFront);

        head=new BPoint[2][4];
        head[0][0] = addBPoint(0.0,0.0,0.0,s0);
        head[0][1] = addBPoint(1.0,0.0,0.0,s0);
        head[0][2] = addBPoint(1.0,1.0,0.0,s0);
        head[0][3] = addBPoint(0.0,1.0,0.0,s0);

        head[1][0] = addBPoint(0.0,0.0,1.0,s0);
        head[1][1] = addBPoint(1.0,0.0,1.0,s0);
        head[1][2] = addBPoint(1.0,1.0,1.0,s0);
        head[1][3] = addBPoint(0.0,1.0,1.0,s0);

        s0=new Segments(x0-dx*0.5,dx,y0,dy,z0,dz);


        body=new BPoint[2][4];
        body[0][0] = addBPoint(0.0,0.0,0.0,s0);
        body[0][1] = addBPoint(1.0,0.0,0.0,s0);
        body[0][2] = addBPoint(1.0,1.0,0.0,s0);
        body[0][3] = addBPoint(0.0,1.0,0.0,s0);

        body[1][0] = addBPoint(0.0,0.0,1.0,s0);
        body[1][1] = addBPoint(1.0,0.0,1.0,s0);
        body[1][2] = addBPoint(1.0,1.0,1.0,s0);
        body[1][3] = addBPoint(0.0,1.0,1.0,s0);

        double y0Wing=dy-dyRear;

        s0=new Segments(x0-dxFront*2-dx*0.5,dx,y0Wing,dy,z0+dz*2,dz);


        leftWing=new BPoint[2][4];
        leftWing[0][0] = addBPoint(0.0,0.0,0.0,s0);
        leftWing[0][1] = addBPoint(1.0,0.0,0.0,s0);
        leftWing[0][2] = addBPoint(1.0,1.0,0.0,s0);
        leftWing[0][3] = addBPoint(0.0,1.0,0.0,s0);

        leftWing[1][0] = addBPoint(0.0,0.0,1.0,s0);
        leftWing[1][1] = addBPoint(1.0,0.0,1.0,s0);
        leftWing[1][2] = addBPoint(1.0,1.0,1.0,s0);
        leftWing[1][3] = addBPoint(0.0,1.0,1.0,s0);

        s0=new Segments(x0+dxFront*2+dx*0.5,dx,y0Wing,dy,z0+dz*2,dz);

        rightWing=new BPoint[2][4];
        rightWing[0][0] = addBPoint(0.0,0.0,0.0,s0);
        rightWing[0][1] = addBPoint(1.0,0.0,0.0,s0);
        rightWing[0][2] = addBPoint(1.0,1.0,0.0,s0);
        rightWing[0][3] = addBPoint(0.0,1.0,0.0,s0);

        rightWing[1][0] = addBPoint(0.0,0.0,1.0,s0);
        rightWing[1][1] = addBPoint(1.0,0.0,1.0,s0);
        rightWing[1][2] = addBPoint(1.0,1.0,1.0,s0);
        rightWing[1][3] = addBPoint(0.0,1.0,1.0,s0);

    }



    @Override
    public void printMeshData(PrintWriter pw) {

        super.printMeshData(pw);
        super.printFaces(pw, faces);

    }

    @Override
    public void printTexture(Graphics2D bufGraphics) {

        for (int i = 0; i < faces.length; i++) {

            int[][] face = faces[i];
            int[] tPoints = face[2];
            if(tPoints.length==4) {
                printTexturePolygon(bufGraphics, tPoints[0],tPoints[1],tPoints[2],tPoints[3]);
            } else if(tPoints.length==3) {
                printTexturePolygon(bufGraphics, tPoints[0],tPoints[1],tPoints[2]);
            }

        }


    }

}
