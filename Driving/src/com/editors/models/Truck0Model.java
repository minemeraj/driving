package com.editors.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.util.Vector;

import com.BPoint;
import com.Point3D;
import com.Segments;
import com.main.Renderer3D;

/**
 * One texture model Summing up the best creation logic so far
 *
 * @author Administrator
 *
 */
public class Truck0Model extends VehicleModel {

	protected double dxWagon = 0;
	protected double dyWagon = 0;
	protected double dzWagon = 0;

	protected double wheelRadius;
	protected double wheelWidth;
	protected int wheel_rays;

	// rear and cabin textures
	protected int[] tBackRear = null;
	protected int[][] tRe = null;
	// wagon textures
	protected int[] tBackWa = null;
	protected int[] tLeftWa = null;
	protected int[][] tWa = null;
	protected int[] tRightWa = null;
	// window texture
	protected int[][] tWi = null;
	// wheel texture
	protected int[][] tWh = null;

	protected double dyTexture = 100;
	protected double dxTexture = 100;
	protected BPoint[][] wheelLeftFront;
	protected BPoint[][] wheelRightFront;
	protected BPoint[][] wheelLeftRear;
	protected BPoint[][] wheelRightRear;

	protected int nzCab = 3;
	protected int nYcab = 6;
	protected int nzBody = 2;
	protected int nyBody = 2;

	protected BPoint[][][] cab;
	protected BPoint[][] rear;
	protected BPoint[][] wagon;
	protected BPoint[][][] roof;

	public static final String NAME = "Truck";

	public Truck0Model(double dxRoof, double dyRoof, double dzRoof, double dxFront, double dyfront, double dzFront,
			double dxRear, double dyRear, double dzRear, double dxWagon, double dyWagon, double dzWagon,
			double rearOverhang, double frontOverhang, double rearOverhang1, double frontOverhang1, double wheelRadius,
			double wheelWidth, int wheel_rays) {
		super();
		this.dxRear = dxRear;
		this.dyRear = dyRear;
		this.dzRear = dzRear;

		this.dxFront = dxFront;
		this.dyFront = dyfront;
		this.dzFront = dzFront;

		this.dxWagon = dxWagon;
		this.dyWagon = dyWagon;
		this.dzWagon = dzWagon;

		this.dxRoof = dxRoof;
		this.dyRoof = dyRoof;
		this.dzRoof = dzRoof;

		this.rearOverhang = rearOverhang;
		this.frontOverhang = frontOverhang;

		this.rearOverhang1 = rearOverhang1;
		this.frontOverhang1 = frontOverhang1;

		this.wheelRadius = wheelRadius;
		this.wheelWidth = wheelWidth;
		this.wheel_rays = wheel_rays;
	}

	@Override
	public void initMesh() {
		points = new Vector<Point3D>();
		texturePoints = new Vector<Point3D>();

		int c = 0;
		c = initDoubleArrayValues(tRe = new int[1][4], c);
		c = initSingleArrayValues(tBackRear = new int[4], c);
		c = initSingleArrayValues(tBackWa = new int[4], c);
		c = initSingleArrayValues(tLeftWa = new int[4], c);
		c = initDoubleArrayValues(tWa = new int[1][4], c);
		c = initSingleArrayValues(tRightWa = new int[4], c);
		c = initDoubleArrayValues(tWi = new int[1][4], c);
		c = initDoubleArrayValues(tWh = new int[1][4], c);

		x0 = dxWagon * 0.5;

		buildCabin();

		buildRear();

		int nzWagon = 2;
		buildWagon(nzWagon);

		buildTextures();

		buildWheels();

		int totWheelPolygon = wheel_rays + 2 * (wheel_rays - 2);
		int NUM_WHEEL_FACES = 4 * totWheelPolygon;

		// faces
		int NF = 2 + (2 + (nzCab - 1)) * (nYcab - 1) * 2;
		NF += 2 + (nzBody - 1) * 4;
		NF += 2 + (nzWagon - 1) * 4;

		faces = new int[NF + NUM_WHEEL_FACES][3][4];

		int counter = 0;
		counter = buildBodyFaces(counter, nzBody, nzWagon);
		counter = buildWheelFaces(counter, totWheelPolygon);

	}

	protected void buildWheels() {

		double wz = 0;
		double wxLeft = dxRear * 0.5 + wheelWidth;
		double wxRight = dxRear * 0.5;

		double yRearAxle = 2.0 * wheelRadius;
		double yFrontAxle = dyRear + dyFront * 0.5;

		wheelLeftFront = buildWheel(x0 - wxLeft, yFrontAxle, wz, wheelRadius, wheelWidth, wheel_rays);
		wheelRightFront = buildWheel(x0 + wxRight, yFrontAxle, wz, wheelRadius, wheelWidth, wheel_rays);
		wheelLeftRear = buildWheel(x0 - wxLeft, yRearAxle, wz, wheelRadius, wheelWidth, wheel_rays);
		wheelRightRear = buildWheel(x0 + wxRight, yRearAxle, wz, wheelRadius, wheelWidth, wheel_rays);

	}

	protected int buildBodyFaces(int counter, int nzRear, int nzWagon) {

		counter = buildCabinFaces(counter, nYcab, nzCab);
		counter = buildRearFaces(counter, nzRear, nzWagon);
		counter = buildWagonFaces(counter, nzWagon);

		return counter;
	}

	protected int buildRearFaces(int counter, int nzRear, int nzWagon) {

		faces[counter++] = buildFace(Renderer3D.CAR_BOTTOM, rear[0][0], rear[0][3], rear[0][2], rear[0][1], tRe[0]);

		for (int k = 0; k < nzRear - 1; k++) {

			faces[counter++] = buildFace(Renderer3D.CAR_LEFT, rear[k][0], rear[k + 1][0], rear[k + 1][3], rear[k][3],
					tRe[0]);
			faces[counter++] = buildFace(Renderer3D.CAR_BACK, rear[k][0], rear[k][1], rear[k + 1][1], rear[k + 1][0],
					tBackRear);
			faces[counter++] = buildFace(Renderer3D.CAR_RIGHT, rear[k][1], rear[k][2], rear[k + 1][2], rear[k + 1][1],
					tRe[0]);
			faces[counter++] = buildFace(Renderer3D.CAR_FRONT, rear[k][2], rear[k][3], rear[k + 1][3], rear[k + 1][2],
					tRe[0]);

		}

		faces[counter++] = buildFace(Renderer3D.CAR_TOP, rear[nzRear - 1][0], rear[nzRear - 1][1], rear[nzRear - 1][2],
				rear[nzRear - 1][3], tRe[0]);

		return counter;
	}

	protected int buildRearYFaces(int counter, int nzRear, int nzWagon) {

		int numSections = rear.length;

		faces[counter++] = buildFace(Renderer3D.CAR_BACK, rear[0][0], rear[0][1], rear[0][2], rear[0][3], tRe[0]);

		for (int i = 0; i < numSections - 1; i++) {

			faces[counter++] = buildFace(Renderer3D.CAR_LEFT, rear[i + 1][0], rear[i][0], rear[i][3], rear[i + 1][3],
					tRe[0]);
			faces[counter++] = buildFace(Renderer3D.CAR_BOTTOM, rear[i][0], rear[i + 1][0], rear[i + 1][1], rear[i][1],
					tRe[0]);
			faces[counter++] = buildFace(Renderer3D.CAR_RIGHT, rear[i][1], rear[i + 1][1], rear[i + 1][2], rear[i][2],
					tRe[0]);
			faces[counter++] = buildFace(Renderer3D.CAR_TOP, rear[i][2], rear[i + 1][2], rear[i + 1][3], rear[i][3],
					tRe[0]);

		}
		faces[counter++] = buildFace(Renderer3D.CAR_FRONT, rear[numSections - 1][0], rear[numSections - 1][3],
				rear[numSections - 1][2], rear[numSections - 1][1], tRe[0]);
		return counter;

	}

	protected int buildCabinFaces(int counter, int nYcab, int nzCab) {
		// cab
		for (int j = 0; j < nYcab - 1; j++) {

			faces[counter++] = buildFace(Renderer3D.CAR_BOTTOM, cab[0][j][0], cab[0][j + 1][0], cab[1][j + 1][0],
					cab[1][j][0], tRe[0]);

			for (int k = 0; k < nzCab - 1; k++) {

				// set windows in the upper part
				int[] ca = null;
				if (k > 0) {
					ca = tWi[0];
				} else {
					ca = tRe[0];
				}

				faces[counter++] = buildFace(Renderer3D.CAR_LEFT, cab[0][j][k], cab[0][j][k + 1], cab[0][j + 1][k + 1],
						cab[0][j + 1][k], ca);
				if (j == 0) {
					faces[counter++] = buildFace(Renderer3D.CAR_BACK, cab[0][j][k], cab[1][j][k], cab[1][j][k + 1],
							cab[0][j][k + 1], tRe[0]);
				}
				faces[counter++] = buildFace(Renderer3D.CAR_RIGHT, cab[1][j][k], cab[1][j + 1][k], cab[1][j + 1][k + 1],
						cab[1][j][k + 1], ca);
				if (j == nYcab - 2) {
					faces[counter++] = buildFace(Renderer3D.CAR_FRONT, cab[0][nYcab - 1][k], cab[0][nYcab - 1][k + 1],
							cab[1][nYcab - 1][k + 1], cab[1][nYcab - 1][k], ca);
				}

			}
			faces[counter++] = buildFace(Renderer3D.CAR_TOP, cab[0][j][nzCab - 1], cab[1][j][nzCab - 1],
					cab[1][j + 1][nzCab - 1], cab[0][j + 1][nzCab - 1], tRe[0]);
		}

		return counter;

	}

	/**
	 *
	 * BUILD WAGON BY Z SECTIONS
	 *
	 * @param nzBody
	 * @return
	 */
	protected int buildWagonFaces(int counter, int nzWagon) {

		faces[counter++] = buildFace(Renderer3D.CAR_BOTTOM, wagon[0][0], wagon[0][3], wagon[0][2], wagon[0][1], tWa[0]);

		for (int k = 0; k < nzWagon - 1; k++) {

			faces[counter++] = buildFace(Renderer3D.CAR_LEFT, wagon[k][0], wagon[k + 1][0], wagon[k + 1][3],
					wagon[k][3], tLeftWa);
			faces[counter++] = buildFace(Renderer3D.CAR_BACK, wagon[k][0], wagon[k][1], wagon[k + 1][1],
					wagon[k + 1][0], tBackWa);
			faces[counter++] = buildFace(Renderer3D.CAR_RIGHT, wagon[k + 1][1], wagon[k][1], wagon[k][2],
					wagon[k + 1][2], tRightWa);
			faces[counter++] = buildFace(Renderer3D.CAR_FRONT, wagon[k][2], wagon[k][3], wagon[k + 1][3],
					wagon[k + 1][2], tWa[0]);

		}

		faces[counter++] = buildFace(Renderer3D.CAR_TOP, wagon[nzWagon - 1][0], wagon[nzWagon - 1][1],
				wagon[nzWagon - 1][2], wagon[nzWagon - 1][3], tWa[0]);

		return counter;
	}

	protected int buildWheelFaces(int counter, int totWheelPolygon) {

		///// WHEELS

		int[][][] wFaces = buildWheelFaces(wheelLeftFront, tWh[0][0]);
		for (int i = 0; i < totWheelPolygon; i++) {
			faces[counter++] = wFaces[i];
		}

		wFaces = buildWheelFaces(wheelRightFront, tWh[0][0]);
		for (int i = 0; i < totWheelPolygon; i++) {
			faces[counter++] = wFaces[i];
		}

		wFaces = buildWheelFaces(wheelLeftRear, tWh[0][0]);
		for (int i = 0; i < totWheelPolygon; i++) {
			faces[counter++] = wFaces[i];
		}

		wFaces = buildWheelFaces(wheelRightRear, tWh[0][0]);
		for (int i = 0; i < totWheelPolygon; i++) {
			faces[counter++] = wFaces[i];
		}

		return counter;

	}

	protected void buildTextures() {

		int shift = 1;
		double deltaXR = (dxWagon - dxRear) * 0.5;

		// body points
		double y = by;
		double x = bx;
		addTRect(x, y, dxTexture, dyTexture);

		// wagon points
		x += dxTexture + shift;
		y = by;
		addTRect(x + dzWagon + deltaXR, y, dxRear, dzRear);
		y += dzRear + shift;
		addTRect(x + dzWagon, y, dxWagon, dzWagon);
		y += dzWagon;
		addTRect(x, y, dzWagon, dyWagon);
		addTRect(x + dzWagon, y, dxWagon, dyWagon);
		addTRect(x + dzWagon + dxWagon, y, dzWagon, dyWagon);

		// window points
		double maxDX = Math.max(dxRear, dxWagon);
		x += maxDX + 2 * dzWagon + shift;
		y = by;
		addTRect(x, y, dxTexture, dyTexture);

		// wheel texture, a black square for simplicity:
		x += dxTexture + shift;
		y = by;
		addTRect(x, y, wheelWidth, wheelWidth);

		IMG_WIDTH = (int) (2 * bx + maxDX + 2 * dzWagon + 2 * dxTexture + wheelWidth + 3 * shift);
		IMG_HEIGHT = (int) (2 * by + Math.max(dyTexture, dyWagon + dzWagon + dzRear + 2 * shift));
	}

	protected void buildRear() {

		Segments s0 = new Segments(x0 - dxRear * 0.5, dxRear, y0, dyRear, z0, dzRear);

		rear = new BPoint[nyBody][4];

		rear[0][0] = addBPoint(0.0, 0.0, 0.0, s0);
		rear[0][1] = addBPoint(1.0, 0.0, 0.0, s0);
		rear[0][2] = addBPoint(1.0, 1.0, 0.0, s0);
		rear[0][3] = addBPoint(0.0, 1.0, 0.0, s0);

		rear[1][0] = addBPoint(0.0, 0.0, 1.0, s0);
		rear[1][1] = addBPoint(1.0, 0.0, 1.0, s0);
		rear[1][2] = addBPoint(1.0, 1.0, 1.0, s0);
		rear[1][3] = addBPoint(0.0, 1.0, 1.0, s0);

	}

	protected void buildCabin() {

		double wy = wheelRadius * 1.0 / dyFront;

		double fy0 = 0;
		double fy2 = 0.5 - wy;
		double fy1 = (0 * 0.25 + fy2 * 0.75);
		double fy3 = 0.5 + wy;
		double fy5 = 1.0;
		double fy4 = (fy3 * 0.75 + fy5 * 0.25);

		double wz2 = wheelRadius * 1.0 / dzFront;
		double wz3 = wz2;

		double fz2 = 0.5;

		Segments s0 = new Segments(x0 - dxFront * 0.5, dxFront, y0 + dyWagon, dyFront, z0, dzFront);

		cab = new BPoint[2][nYcab][nzCab];
		cab[0][0][0] = addBPoint(0.0, fy0, 0.0, s0);
		cab[0][0][1] = addBPoint(0.0, fy0, fz2, s0);
		cab[0][0][2] = addBPoint(0.0, fy0, 1.0, s0);
		cab[1][0][0] = addBPoint(1.0, fy0, 0.0, s0);
		cab[1][0][1] = addBPoint(1.0, fy0, fz2, s0);
		cab[1][0][2] = addBPoint(1.0, fy0, 1.0, s0);

		cab[0][1][0] = addBPoint(0.0, fy1, 0.0, s0);
		cab[0][1][1] = addBPoint(0.0, fy1, fz2, s0);
		cab[0][1][2] = addBPoint(0.0, fy1, 1.0, s0);
		cab[1][1][0] = addBPoint(1.0, fy1, 0.0, s0);
		cab[1][1][1] = addBPoint(1.0, fy1, fz2, s0);
		cab[1][1][2] = addBPoint(1.0, fy1, 1.0, s0);

		cab[0][2][0] = addBPoint(0.0, fy2, wz2, s0);
		cab[0][2][1] = addBPoint(0.0, fy2, fz2, s0);
		cab[0][2][2] = addBPoint(0.0, fy2, 1.0, s0);
		cab[1][2][0] = addBPoint(1.0, fy2, wz2, s0);
		cab[1][2][1] = addBPoint(1.0, fy2, fz2, s0);
		cab[1][2][2] = addBPoint(1.0, fy2, 1.0, s0);

		cab[0][3][0] = addBPoint(0.0, fy3, wz3, s0);
		cab[0][3][1] = addBPoint(0.0, fy3, fz2, s0);
		cab[0][3][2] = addBPoint(0.0, fy3, 1.0, s0);
		cab[1][3][0] = addBPoint(1.0, fy3, wz3, s0);
		cab[1][3][1] = addBPoint(1.0, fy3, fz2, s0);
		cab[1][3][2] = addBPoint(1.0, fy3, 1.0, s0);

		cab[0][4][0] = addBPoint(0.0, fy4, 0.0, s0);
		cab[0][4][1] = addBPoint(0.0, fy4, fz2, s0);
		cab[0][4][2] = addBPoint(0.0, fy4, 1.0, s0);
		cab[1][4][0] = addBPoint(1.0, fy4, 0.0, s0);
		cab[1][4][1] = addBPoint(1.0, fy4, fz2, s0);
		cab[1][4][2] = addBPoint(1.0, fy4, 1.0, s0);

		cab[0][5][0] = addBPoint(0.0, fy5, 0.0, s0);
		cab[0][5][1] = addBPoint(0.0, fy5, fz2, s0);
		cab[0][5][2] = addBPoint(0.0, fy5, 1.0, s0);
		cab[1][5][0] = addBPoint(1.0, fy5, 0.0, s0);
		cab[1][5][1] = addBPoint(1.0, fy5, fz2, s0);
		cab[1][5][2] = addBPoint(1.0, fy5, 1.0, s0);

	}

	protected void buildWagon(int nzWagon) {

		Segments s0 = new Segments(x0 - dxWagon * 0.5, dxWagon, y0, dyWagon, z0 + dzRear, dzWagon);

		wagon = new BPoint[nzWagon][4];
		wagon[0][0] = addBPoint(0.0, 0.0, 0.0, s0);
		wagon[0][1] = addBPoint(1.0, 0.0, 0.0, s0);
		wagon[0][2] = addBPoint(1.0, 1.0, 0.0, s0);
		wagon[0][3] = addBPoint(0.0, 1.0, 0.0, s0);

		wagon[1][0] = addBPoint(0.0, 0.0, 1.0, s0);
		wagon[1][1] = addBPoint(1.0, 0.0, 1.0, s0);
		wagon[1][2] = addBPoint(1.0, 1.0, 1.0, s0);
		wagon[1][3] = addBPoint(0.0, 1.0, 1.0, s0);
	}

	@Override
	public void printMeshData(PrintWriter pw) {

		super.printMeshData(pw);
		super.printFaces(pw, faces);

	}

	@Override
	public void printTexture(Graphics2D bufGraphics) {
		bufGraphics.setStroke(new BasicStroke(0.1f));

		bufGraphics.setColor(new Color(217, 15, 27));
		printTexturePolygon(bufGraphics, tRe[0]);
		printTexturePolygon(bufGraphics, tBackRear);
		bufGraphics.setColor(new Color(255, 255, 255));
		printTexturePolygon(bufGraphics, tBackWa);
		printTexturePolygon(bufGraphics, tLeftWa);
		printTexturePolygon(bufGraphics, tWa[0]);
		printTexturePolygon(bufGraphics, tRightWa);

		bufGraphics.setColor(Color.BLUE);
		printTexturePolygon(bufGraphics, tWi[0]);

		bufGraphics.setColor(Color.BLACK);
		printTexturePolygon(bufGraphics, tWh[0]);

	}

}
