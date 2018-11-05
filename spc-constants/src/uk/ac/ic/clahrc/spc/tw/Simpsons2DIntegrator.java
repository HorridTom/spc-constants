package uk.ac.ic.clahrc.spc.tw;


public class Simpsons2DIntegrator {

	// Interface for the function to be integrated
	public interface Integrand {
		public double func(double xVar, double yVar, double... params);
	}
	
	/**Method returning Simpson's Rule approximation to the
	integral specified.
	@param integrand specifies the integrand
	@param xLowLimit the lower limit of the integral in the first variable (x)
	@param xHighLimit the upper limit of the integral in the first variable (x)
	@param yLowLimit the lower limit of the integral in the second variable (y)
	@param yHighLimit the upper limit of the integral in the second variable (y)
	@param xStrips specifies number of strips to use in the first coordinate (x)
	@param yStrips specifies number of strips to use in the second coordinate (y)
	@param params passes parameters to the integrand
	@exception IllegalArgumentException if a parameter is bad.
	*/
	public static double integrate(Integrand integrand, double xLowLimit, double xHighLimit,
			double yLowLimit, double yHighLimit, int xStrips, int yStrips, double... params) {
		
		if (xStrips < 2 || xStrips % 2 == 1 || yStrips < 2 || yStrips % 2 == 1) {
			throw new IllegalArgumentException(
					"Illegal number of strips, must be positive even integer.");
		}
		if(xLowLimit >= xHighLimit || yLowLimit >= yHighLimit) {
			throw new IllegalArgumentException(
					"Invalid limits.");
		}
		
		double xStripWidth = (xHighLimit - xLowLimit) / xStrips;
		double yStripWidth = (yHighLimit - yLowLimit) / yStrips;
		
		double factor = (double) 1 / (9 * xStrips * yStrips);
		
		double sumTotal = 0;
		
		for (int s = 0; s <= xStrips ; s++) {
			for (int t = 0; t <= yStrips ; t++) {
				double x = xLowLimit + s * xStripWidth;
				double y = yLowLimit + t * yStripWidth;
				sumTotal += factor * Simpsons2DIntegrator.weight(s, t, xStrips, yStrips) *
						integrand.func(x, y, params);
			}
		}
		
		return (xHighLimit - xLowLimit) * (yHighLimit - yLowLimit) * sumTotal;
	}

	/**Supplies the weights used in 2D version of Simpson's Rule
	 @param i between 0 and xStrips specifies which x strip
	 @param j between 0 and yStrips specifies which y strip
	 @param xStrips positive and even number of x strips
	 @param yStrips positive and even number of y strips
	 @exception IllegalArgumentException if a parameter does not comply.
	*/
	public static int weight(int i, int j, int xStrips, int yStrips) {
		
		if (xStrips < 2 || xStrips % 2 == 1 || yStrips < 2 || yStrips % 2 == 1) {
			throw new IllegalArgumentException(
					"Illegal number of strips, must be positive even integer.");
		}
		
		if(i < 0 || i > xStrips || j < 0 || j > yStrips) {
			throw new IllegalArgumentException("Weight not defined.");
		}
		
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
