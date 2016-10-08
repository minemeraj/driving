package com.editors.models;

import java.util.Vector;

import com.Point3D;

public class BeetleModel extends Car0Model{

    public static String NAME="Beetle";

    public BeetleModel(double dx, double dy, double dz){

        super(dx, dy, dz);
    }

    @Override
    public void initMesh() {

        points=new Vector<Point3D>();
        texturePoints=new Vector();

        double[][][] mainBody={

                {{0.0000},{1.0,1.0,0.75},{0.1044,0.3655,0.3655}},
                {{0.0329},{1.0,1.0,0.75},{0.0763,0.7068,0.7068}},
                {{0.1186},{1.0,1.0,0.6279},{0.0562,0.7108,0.7108}},
                {{0.1405},{1.0,1.0,0.6279},{0.0482,0.7068,0.7590}},
                {{0.1471},{1.0,1.0,0.6279},{0.2490,0.7028,0.7711}},
                {{0.1778},{1.0,1.0,0.6279},{0.3775,0.6867,0.8273}},
                {{0.2217},{1.0,1.0,0.6279},{0.4217,0.7269,0.8916}},
                {{0.2667},{1.0,1.0,0.6279},{0.3775,0.6707,0.9438}},
                {{0.2964},{1.0,1.0,0.6279},{0.2490,0.6667,0.9639}},
                {{0.3063},{1.0,1.0,0.6279},{0.0000,0.6627,0.9679}},
                {{0.4281},{1.0,1.0,0.6279},{0.0000,0.6426,0.9839}},
                {{0.5653},{1.0,1.0,0.8343},{0.0000,0.6145,0.9157}},
                {{0.6894},{1.0,1.0,0.8343},{0.0000,0.6104,0.7108}},
                {{0.7102},{1.0,1.0,0.75},{0.0000,0.5984,0.6747}},
                {{0.7201},{1.0,1.0,0.75},{0.2490,0.5984,0.6586}},
                {{0.7519},{1.0,1.0,0.75},{0.3775,0.5944,0.6305}},
                {{0.7980},{1.0,1.0,0.75},{0.4257,0.6225,0.6225}},
                {{0.8419},{1.0,1.0,0.75},{0.2490,0.5984,0.5984}},
                {{0.8782},{1.0,1.0,0.75},{0.0000,0.5703,0.5703}},
                {{0.9759},{1.0,1.0,0.75},{0.0000,0.4378,0.4378}},
                {{1.0000},{1.0,1.0,0.75},{0.1727,0.2811,0.2811}},

        };

        double[] mainAxles={0.2217,0.7980};

        body=mainBody;
        axles=mainAxles;

        buildCar();

    }

}