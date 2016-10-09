package com.editors.models;

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
public class PickupModel extends Truck0Model {

	public static final String NAME = "Pickup";

	public PickupModel(double dx, double dy, double dz, double dxFront, double dyfront, double dzFront, double dxRoof,
			double dyRoof, double dzRoof, double wheelRadius, double wheelWidth, int wheel_rays) {
		super(dx, dy, dz, dxFront, dyfront, dzFront, dxRoof, dyRoof, dzRoof, wheelRadius, wheelWidth, wheel_rays);
	}

	@Override
	public void initMesh() {
		points = new Vector<Point3D>();
		texturePoints = new Vector();

		nzCab = 2;
		nzBody = 2;
		nyBody = 6;

		x0 = dxRoof * 0.5;

		buildCabin();

		buildBody();

		int nWagonMeridians = 10;
		buildWagon(nWagonMeridians);

		buildWheels();
		buildTextures();

		int totWheelPolygon = wheel_rays + 2 * (wheel_rays - 2);
		int NUM_WHEEL_FACES = 4 * totWheelPolygon;

		// faces
		int NF = 2 + (2 + (nzCab - 1)) * (nYcab - 1) * 2;
		NF += 2 + (nyBody - 1) * 4;
		NF += 2 + (nWagonMeridians);
		// roof
		NF += 7;

		faces = new int[NF + NUM_WHEEL_FACES][3][4];

		int counter = 0;
		counter = buildBodyFaces(counter, nzBody, nWagonMeridians);
		counter = buildWheelFaces(counter, totWheelPolygon);

		IMG_WIDTH = (int) (2 * bx + dx + wheelWidth);
		IMG_HEIGHT = (int) (2 * by + dy);

	}

	@Override
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

		Segments s0 = new Segments(x0 - dxFront * 0.5, dxFront, y0 + dyRoof, dyFront, z0, dzFront);

		cab = new BPoint[2][nYcab][nzCab];

		cab[0][0][0] = addBPoint(0.0, fy0, 0.0, s0);
		cab[0][0][1] = addBPoint(0.0, fy0, fz2, s0);
		cab[1][0][0] = addBPoint(1.0, fy0, 0.0, s0);
		cab[1][0][1] = addBPoint(1.0, fy0, fz2, s0);

		cab[0][1][0] = addBPoint(0.0, fy1, 0.0, s0);
		cab[0][1][1] = addBPoint(0.0, fy1, fz2, s0);
		cab[1][1][0] = addBPoint(1.0, fy1, 0.0, s0);
		cab[1][1][1] = addBPoint(1.0, fy1, fz2, s0);

		cab[0][2][0] = addBPoint(0.0, fy2, wz2, s0);
		cab[0][2][1] = addBPoint(0.0, fy2, fz2, s0);
		cab[1][2][0] = addBPoint(1.0, fy2, wz2, s0);
		cab[1][2][1] = addBPoint(1.0, fy2, fz2, s0);

		cab[0][3][0] = addBPoint(0.0, fy3, wz3, s0);
		cab[0][3][1] = addBPoint(0.0, fy3, fz2, s0);
		cab[1][3][0] = addBPoint(1.0, fy3, wz3, s0);
		cab[1][3][1] = addBPoint(1.0, fy3, fz2, s0);

		cab[0][4][0] = addBPoint(0.0, fy4, 0.0, s0);
		cab[0][4][1] = addBPoint(0.0, fy4, fz2, s0);
		cab[1][4][0] = addBPoint(1.0, fy4, 0.0, s0);
		cab[1][4][1] = addBPoint(1.0, fy4, fz2, s0);

		cab[0][5][0] = addBPoint(0.0, fy5, 0.0, s0);
		cab[0][5][1] = addBPoint(0.0, fy5, fz2, s0);
		cab[1][5][0] = addBPoint(1.0, fy5, 0.0, s0);
		cab[1][5][1] = addBPoint(1.0, fy5, fz2, s0);

		roof = new BPoint[2][3][2];
		roof[0][0][0] = addBPoint(0.0, 0, fz2, s0);
		roof[1][0][0] = addBPoint(1.0, 0, fz2, s0);
		roof[0][1][0] = addBPoint(0.0, 0.5, fz2, s0);
		roof[1][1][0] = addBPoint(1.0, 0.5, fz2, s0);
		roof[0][2][0] = addBPoint(0.0, 0.75, fz2, s0);
		roof[1][2][0] = addBPoint(1.0, 0.75, fz2, s0);

		roof[0][0][1] = addBPoint(0.0, 0, 1.0, s0);
		roof[1][0][1] = addBPoint(1.0, 0, 1.0, s0);
		roof[0][1][1] = addBPoint(0.0, 0.5, 1.0, s0);
		roof[1][1][1] = addBPoint(1.0, 0.5, 1.0, s0);
	}

	@Override
	protected void buildBody() {

		double yRearAxle = 2.0 * wheelRadius / dy;

		double wy = wheelRadius * 1.0 / dy;

		double fy0 = 0;
		double fy2 = yRearAxle - wy;
		double fy1 = (0 * 0.25 + fy2 * 0.75);
		double fy3 = yRearAxle + wy;
		double fy5 = 1.0;
		double fy4 = yRearAxle + (yRearAxle - fy1);

		double wz2 = wheelRadius * 1.0 / dz;
		double wz3 = wz2;

		double fz1 = 1.0;

		body = new BPoint[nyBody][4];

		Segments s0 = new Segments(x0 - dx * 0.5, dx, y0, dy, z0, dz);

		body[0][0] = addBPoint(0.0, fy0, 0.0, s0);
		body[0][1] = addBPoint(1.0, fy0, 0.0, s0);
		body[0][2] = addBPoint(1.0, fy0, fz1, s0);
		body[0][3] = addBPoint(0.0, fy0, fz1, s0);

		body[1][0] = addBPoint(0.0, fy1, 0.0, s0);
		body[1][1] = addBPoint(1.0, fy1, 0.0, s0);
		body[1][2] = addBPoint(1.0, fy1, fz1, s0);
		body[1][3] = addBPoint(0.0, fy1, fz1, s0);

		body[2][0] = addBPoint(0.0, fy2, wz2, s0);
		body[2][1] = addBPoint(1.0, fy2, wz2, s0);
		body[2][2] = addBPoint(1.0, fy2, fz1, s0);
		body[2][3] = addBPoint(0.0, fy2, fz1, s0);

		body[3][0] = addBPoint(0.0, fy3, wz3, s0);
		body[3][1] = addBPoint(1.0, fy3, wz3, s0);
		body[3][2] = addBPoint(1.0, fy3, fz1, s0);
		body[3][3] = addBPoint(0.0, fy3, fz1, s0);

		body[4][0] = addBPoint(0.0, fy4, 0.0, s0);
		body[4][1] = addBPoint(1.0, fy4, 0.0, s0);
		body[4][2] = addBPoint(1.0, fy4, fz1, s0);
		body[4][3] = addBPoint(0.0, fy4, fz1, s0);

		body[5][0] = addBPoint(0.0, fy5, 0.0, s0);
		body[5][1] = addBPoint(1.0, fy5, 0.0, s0);
		body[5][2] = addBPoint(1.0, fy5, fz1, s0);
		body[5][3] = addBPoint(0.0, fy5, fz1, s0);

	}

	@Override
	protected int buildBodyFaces(int counter, int nzRear, int nzWagon) {

		counter = buildCabinFaces(counter, nYcab, nzCab);
		counter = buildRearYFaces(counter, nzRear, nzWagon);
		counter = buildWagonFaces(counter, nzWagon, wagon);

		return counter;
	}

	@Override
	protected int buildCabinFaces(int counter, int nYcab, int nzCab) {

		// cab
		for (int j = 0; j < nYcab - 1; j++) {

			faces[counter++] = buildFace(Renderer3D.CAR_BOTTOM, cab[0][j][0], cab[0][j + 1][0], cab[1][j + 1][0],
					cab[1][j][0], bo[0]);

			for (int k = 0; k < nzCab - 1; k++) {

				// set windows in the upper part
				int[] ca = null;
				if (k > 0) {
					ca = wi[0];
				} else {
					ca = bo[0];
				}

				faces[counter++] = buildFace(Renderer3D.CAR_LEFT, cab[0][j][k], cab[0][j][k + 1], cab[0][j + 1][k + 1],
						cab[0][j + 1][k], ca);
				if (j == 0) {
					faces[counter++] = buildFace(Renderer3D.CAR_BACK, cab[0][j][k], cab[1][j][k], cab[1][j][k + 1],
							cab[0][j][k + 1], bo[0]);
				}
				faces[counter++] = buildFace(Renderer3D.CAR_RIGHT, cab[1][j][k], cab[1][j + 1][k], cab[1][j + 1][k + 1],
						cab[1][j][k + 1], ca);
				if (j == nYcab - 2) {
					faces[counter++] = buildFace(Renderer3D.CAR_FRONT, cab[0][nYcab - 1][k], cab[0][nYcab - 1][k + 1],
							cab[1][nYcab - 1][k + 1], cab[1][nYcab - 1][k], ca);
				}

			}

			faces[counter++] = buildFace(Renderer3D.CAR_TOP, cab[0][j][nzCab - 1], cab[1][j][nzCab - 1],
					cab[1][j + 1][nzCab - 1], cab[0][j + 1][nzCab - 1], bo[0]);

		}

		faces[counter++] = buildFace(Renderer3D.CAR_LEFT, roof[0][0][0], roof[0][0][1], roof[0][1][1], roof[0][1][0],
				bo[0]);
		faces[counter++] = buildFace(Renderer3D.CAR_LEFT, roof[0][1][0], roof[0][1][1], roof[0][2][0], bo[0]);
		faces[counter++] = buildFace(Renderer3D.CAR_TOP, roof[0][0][1], roof[1][0][1], roof[1][1][1], roof[0][1][1],
				bo[0]);
		faces[counter++] = buildFace(Renderer3D.CAR_TOP, roof[0][1][1], roof[1][1][1], roof[1][2][0], roof[0][2][0],
				bo[0]);
		faces[counter++] = buildFace(Renderer3D.CAR_BACK, roof[0][0][0], roof[1][0][0], roof[1][0][1], roof[0][0][1],
				bo[0]);
		faces[counter++] = buildFace(Renderer3D.CAR_RIGHT, roof[1][0][0], roof[1][1][0], roof[1][1][1], roof[1][0][1],
				bo[0]);
		faces[counter++] = buildFace(Renderer3D.CAR_RIGHT, roof[1][1][0], roof[1][2][0], roof[1][1][1], bo[0]);

		return counter;
	}

	@Override
	protected void buildWheels() {

		////
		double wz = 0;
		double wxLeft = dx * 0.5;
		double wxRight = dx * 0.5 - wheelWidth;

		double yRearAxle = 2.0 * wheelRadius;
		double yFrontAxle = dy + dyFront * 0.5;

		wheelLeftFront = buildWheel(x0 - wxLeft, yFrontAxle, wz, wheelRadius, wheelWidth, wheel_rays);
		wheelRightFront = buildWheel(x0 + wxRight, yFrontAxle, wz, wheelRadius, wheelWidth, wheel_rays);
		wheelLeftRear = buildWheel(x0 - wxLeft, yRearAxle, wz, wheelRadius, wheelWidth, wheel_rays);
		wheelRightRear = buildWheel(x0 + wxRight, yRearAxle, wz, wheelRadius, wheelWidth, wheel_rays);

	}

	@Override
	protected void buildWagon(int nWagongMeridians) {

		wagon = addYCylinder(x0, 0, dz + dxRoof * 0.5, dxRoof * 0.5, dyRoof, nWagongMeridians);

	}

	@Override
	protected int buildWagonFaces(int counter, int nWagonMeridians, BPoint[][] wagon) {

		for (int i = 0; i < wagon.length; i++) {

			faces[counter++] = buildFace(Renderer3D.CAR_TOP, wagon[i][0], wagon[(i + 1) % wagon.length][0],
					wagon[(i + 1) % wagon.length][1], wagon[i][1], bo[0]);
		}

		return counter;
	}

}
