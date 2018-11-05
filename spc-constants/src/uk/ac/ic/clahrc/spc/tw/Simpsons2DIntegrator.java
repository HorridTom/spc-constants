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

}
