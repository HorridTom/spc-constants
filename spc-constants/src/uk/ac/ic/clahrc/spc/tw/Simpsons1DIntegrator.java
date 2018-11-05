package uk.ac.ic.clahrc.spc.tw;


public class Simpsons1DIntegrator {
	
	// Interface for the function to be integrated
	public interface Integrand {
		public double func(double xVar, double... params);
	}
	
	/**Method returning Simpson's Rule approximation to the
	integral specified.
	@param integrand specifies the integrand
	@param xLowLimit the lower limit of the integral
	@param xHighLimit the upper limit of the integral
	@param nStrips specifies the number of strips to be used
	@param params passes parameters to the integrand
	@exception IllegalArgumentException if a parameter is bad.
	*/
	public static double integrate(Integrand integrand, double xLowLimit, double xHighLimit,
			int nStrips, double... params) {
		
		if (nStrips < 2 || nStrips % 2 == 1) {
			throw new IllegalArgumentException(
					"Illegal number of strips, must be positive even integer.");
		}
		if(xLowLimit >= xHighLimit) {
			throw new IllegalArgumentException(
					"Invalid limits.");
		}
		
		double stripWidth = (xHighLimit - xLowLimit) / nStrips;
		
		double sumTotal = 0;
		
		for (int t = 0; t <= nStrips ; t++) {
			double x = xLowLimit + t * stripWidth;
			sumTotal += Simpsons1DIntegrator.weight(t, nStrips) * integrand.func(x, params);
		}
		
		double approximation = (stripWidth/3)*sumTotal;
		
		return approximation;
	}
	
	/**Supplies the weights used in Simpson's Rule
	 @param i between 0 and nStrips
	 @param nStrips positive and even
	 @exception IllegalArgumentException if a parameter does not comply.
	*/
	public static int weight(int i, int nStrips) {
		
		if (nStrips < 2 || nStrips % 2 == 1) {
			throw new IllegalArgumentException(
					"Illegal number of strips, must be positive even integer.");
		}
		if(i < 0 || i > nStrips) {
			throw new IllegalArgumentException("Weight not defined.");
		}
		
		if (i == 0 || i == nStrips) {
			return 1;
		} else if (i%2 == 0) {
			return 2;
		} else return 4;
	}
}
