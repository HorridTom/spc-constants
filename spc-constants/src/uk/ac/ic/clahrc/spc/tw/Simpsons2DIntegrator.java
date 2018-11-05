package uk.ac.ic.clahrc.spc.tw;


public class Simpsons2DIntegrator {

	public interface Integrand {
		public double func(double xVar, double yVar, double... params);
	}

	public static double integrate(Integrand integrand, double xLowLimit, double xHighLimit,
			double yLowLimit, double yHighLimit, int nStrips, double... params) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Object weight(int i, int j, int xStrips, int yStrips) {
		if ((i == 0 || i == xStrips) && (j == 0 || j == yStrips)) {
			return 1;
		} else if ((i == 0 || i == xStrips) ^ (j == 0 || j == yStrips)) {
			if ((i + j) % 2 == 0) {
				return 2;
			} else {
				return 4;
			}
		} else if ((i + j) % 2 == 1) {
			return 8;
		} if ((i % 2 == 1) && (j % 2 == 1) ) {
			return 16;
		} else {
			return 4;
		}
	}

}
