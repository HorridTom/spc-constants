package uk.ac.ic.clahrc.spc.tw;


public class Simpsons1DIntegrator {
	
	public interface Integrand {
		public double func(double xVar, double... params);
	}

	public static double integrate(Integrand integrand, double xLowLimit, double xHighLimit,
			int nStrips, double... params) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
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
