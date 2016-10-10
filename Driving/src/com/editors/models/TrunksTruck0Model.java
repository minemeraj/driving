package com.editors.models;

import java.util.Vector;

import com.BPoint;
import com.Point3D;
import com.main.Renderer3D;

/**
 * One texture model Summing up the best creation logic so far
 *
 * @author Administrator
 *
 */
public class TrunksTruck0Model extends Truck0Model {

	public static final String NAME = "Trunks truck";

	BPoint[][][] trunks = null;

	public TrunksTruck0Model(double dx, double dy, double dz, double dxFront, double dyfront, double dzFront,
			double dxRoof, double dyRoof, double dzRoof, double wheelRadius, double wheelWidth, int wheel_rays) {
		super(dx, dy, dz, dxFront, dyfront, dzFront, dxRoof, dyRoof, dzRoof, wheelRadius, wheelWidth, wheel_rays);
	}

	@Override
	public void initMesh() {
		points = new Vector<Point3D>();
		texturePoints = new Vector();

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
		NF += 2 + (nzBody - 1) * 4;
		NF += 2 + trunks.length * (nWagonMeridians + 2 * nWagonMeridians);

		faces = new int[NF + NUM_WHEEL_FACES][3][4];

		int counter = 0;
		counter = buildBodyFaces(counter, nzBody, nWagonMeridians);
		counter = buildWheelFaces(counter, totWheelPolygon);

		IMG_WIDTH = (int) (2 * bx + dx + wheelWidth);
		IMG_HEIGHT = (int) (2 * by + dy);

	}

	@Override
	protected void buildWagon(int nWagongMeridians) {

		trunks = new BPoint[3][nWagongMeridians][2];

		for (int i = 0; i < trunks.length; i++) {

			double radius = dxRoof / trunks.length * 0.5;
			double xx = dx / trunks.length * i + i * 2 * radius;
			trunks[i] = addYCylinder(xx, 0, dz + dxRoof * 0.5, radius, dyRoof, nWagongMeridians);
		}

	}

	@Override
	protected int buildWagonFaces(int counter, int nWagonMeridians) {

		for (int m = 0; m < trunks.length; m++) {

			for (int i = 0; i < nWagonMeridians; i++) {
				faces[counter++] = buildFace(Renderer3D.CAR_TOP, trunks[m][i][0],
						trunks[m][(i + 1) % trunks[m].length][0], trunks[m][(i + 1) % trunks[0].length][1],
						trunks[m][i][1], bo[0]);
			}

			for (int j = 1; j < nWagonMeridians - 1; j++) {

				BPoint p0 = trunks[m][0][1];
				BPoint p1 = trunks[m][(j + 1) % nWagonMeridians][1];
				BPoint p2 = trunks[m][j][1];

				faces[counter++] = buildFace(0, p0, p1, p2, bo[0]);
			}

			for (int j = 1; j < nWagonMeridians - 1; j++) {

				BPoint p0 = trunks[m][0][0];
				BPoint p1 = trunks[m][j][0];
				BPoint p2 = trunks[m][(j + 1) % nWagonMeridians][0];

				faces[counter++] = buildFace(0, p0, p1, p2, bo[0]);
			}
		}

		return counter;
	}

}
