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
	
	public static int weight(int i, int nStrips) {
		return 0;
	}
}
