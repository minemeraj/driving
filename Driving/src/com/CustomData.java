package com;

import java.util.Vector;

import com.main.Renderer3D;

public class CustomData {
	public int buildBox(double x , double y, double z, Vector points, Vector polyData,
			int n, double x_side, double z_side) {
		
		BPoint pLeg000=new BPoint(x,y,z,n++);
		BPoint pLeg100=new BPoint(x+x_side,y,z,n++);
		BPoint pLeg110=new BPoint(x+x_side,y+x_side,z,n++);
		BPoint pLeg010=new BPoint(x,y+x_side,z,n++);
		
		points.setElementAt(pLeg000,pLeg000.getIndex());
		points.setElementAt(pLeg100,pLeg100.getIndex());
		points.setElementAt(pLeg110,pLeg110.getIndex());
		points.setElementAt(pLeg010,pLeg010.getIndex());
		
		LineData Leg=buildLine(pLeg000,pLeg010,pLeg110,pLeg100,Renderer3D.CAR_BOTTOM);
		polyData.add(Leg);
		
		
		BPoint pLeg001=new BPoint(x,y,z+z_side,n++);
		BPoint pLeg101=new BPoint(x+x_side,y,z_side,n++);
		BPoint pLeg111=new BPoint(x+x_side,y+x_side,z+z_side,n++);
		BPoint pLeg011=new BPoint(x,y+x_side,z+z_side,n++);
		
		points.setElementAt(pLeg001,pLeg001.getIndex());
		points.setElementAt(pLeg101,pLeg101.getIndex());
		points.setElementAt(pLeg111,pLeg111.getIndex());
		points.setElementAt(pLeg011,pLeg011.getIndex());
		
		LineData LegS0=buildLine(pLeg000,pLeg001,pLeg011,pLeg010,Renderer3D.CAR_LEFT);
		polyData.add(LegS0);
		LineData LegS1=buildLine(pLeg010,pLeg011,pLeg111,pLeg110,Renderer3D.CAR_FRONT);
		polyData.add(LegS1);
		LineData LegS2=buildLine(pLeg110,pLeg111,pLeg101,pLeg100,Renderer3D.CAR_RIGHT);
		polyData.add(LegS2);
		LineData LegS3=buildLine(pLeg100,pLeg101,pLeg001,pLeg000,Renderer3D.CAR_BACK);
		polyData.add(LegS3);
		
		return n;
	}
	

	public void translatePoints(Vector points, double dx, double dy) { 

		for (int i = 0; i < points.size(); i++) {
			BPoint point = (BPoint) points.elementAt(i);
			if(point==null)
				continue;
			point.translate(dx,dy,0);
		}

	}

	public LineData buildLine(BPoint p0, BPoint p1, BPoint p2,
			BPoint p3, int face) {

		LineData ld=new LineData();

		ld.addIndex(p0.getIndex());
		ld.addIndex(p1.getIndex());					
		ld.addIndex(p2.getIndex());
		if(p3!=null)
			ld.addIndex(p3.getIndex());	
		ld.setData(""+face);

		return ld;
	}
	
}
