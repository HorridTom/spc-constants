package uk.ac.ic.clahrc.spc.tw;

import org.apache.commons.math3.distribution.*;

import uk.ac.ic.clahrc.spc.tw.Simpsons1DIntegrator;

public class SPCConstants {


	/**Method returning the d2 constant
	@param i number of observations 
	@exception IllegalArgumentException if fewer than 2 observations
	*/
	public static double d2(int i) {
		
		if (i < 2) {
			throw new IllegalArgumentException(
					"Constant not defined.");
		}
		
		double d2Result = 2 * Simpsons1DIntegrator.integrate(new d2TransformedIntegrand(), 0, 1, 500, i);
		
		return d2Result;
	}

	/**Method returning the d3 constant
	@param i number of observations 
	@exception IllegalArgumentException if fewer than 2 observations
	*/
	public static double d3(int i) {
		
		if (i < 2) {
			throw new IllegalArgumentException(
					"Constant not defined.");
		}
		
		double d3Integral = Simpsons2DIntegrator.integrate(new d3TransformedIntegrand(),
				-10, 10, 0, 10, 50, 50, i);
		
		double term2 = Math.pow(d2(i),2);
		
		double d3Result = Math.sqrt(2 * d3Integral - term2);
		
		return d3Result;
	}
	
	public static class d2TransformedIntegrand implements Simpsons1DIntegrator.Integrand {
		
		// This integrand is a transformation of that given in
		// the Western Electric Handbook
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
	
	public static class d3TransformedIntegrand implements Simpsons2DIntegrator.Integrand {
		
		// This integrand is a transformation of that given in
		// the Western Electric Handbook. Specifically it is a
		// rotation of the half plane to the upper half plane,
		// facilitating application of Simpson's Rule.
		public double func(double xVar, double yVar, double... params) {
			
			NormalDistribution nDist = new NormalDistribution();
			
			// Coordinate transformation
			double x = (xVar + yVar) / 2;
			double y = (xVar - yVar) / 2;
			
			double PhiX = nDist.cumulativeProbability(x);
			double PhiY = nDist.cumulativeProbability(y);
			
			double d3integrand = 1 - Math.pow(PhiX, params[0]) - Math.pow(1 - PhiY, params[0]) +
					Math.pow(PhiX - PhiY, params[0]);
			
			// The Jacobian of this transformation is 0.5
			return (double)0.5 * d3integrand;
		}
	}
	
}
