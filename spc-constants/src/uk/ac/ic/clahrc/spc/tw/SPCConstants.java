package uk.ac.ic.clahrc.spc.tw;

import org.apache.commons.math3.distribution.*;

import uk.ac.ic.clahrc.spc.tw.Simpsons1DIntegrator;

public class SPCConstants {


	public static double d2(int i) {
		
		double d2Result = 2 * Simpsons1DIntegrator.integrate(new d2TransformedIntegrand(), 0, 1, 500, i);
		
		return d2Result;
	}

	public static double d3(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static class d2TransformedIntegrand implements Simpsons1DIntegrator.Integrand {
		public double func(double xVar, double... params) {
			if(Math.abs(xVar) < 0.0000001) {
				return (double) 0;
			} else {
				NormalDistribution nDist = new NormalDistribution();
				double p1 = nDist.cumulativeProbability((1-xVar)/xVar);
				double funcValue = (1 - Math.pow(1 - p1, params[0]) - Math.pow(p1, params[0])) / Math.pow(xVar, 2);
				return funcValue;
			}
		}
	}
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
