package uk.ac.ic.clahrc.spc.tw;

import static org.junit.Assert.*;

import org.junit.Test;

public class Simpsons2DIntegratorTest {

	@Test
	public void testSimpsons2DIntegrator() {
		double exact_integral_1 = 1/9;
		double correct_answer_integral_2 = 1.1351*2;
		
		double simpsons_integral_1 = Simpsons2DIntegrator.integrate(new sqrIntegrand(), 0, 1, 0, 1, 1000);
		double simpsons_integral_2 = Simpsons2DIntegrator.integrate(
				new expSqrIntegrand(), 0, 1, 0, 1, 1000, 2);
		
		assertEquals(exact_integral_1, simpsons_integral_1, 0.0001);
		assertEquals(correct_answer_integral_2, simpsons_integral_2, 0.0001);
	}
	
	public class sqrIntegrand implements Simpsons2DIntegrator.Integrand {
		public double func(double xVar, double yVar, double... params) {
			return Math.pow(xVar, 2)*Math.pow(yVar, 2);
		}
	}
	
	public class expSqrIntegrand implements Simpsons2DIntegrator.Integrand {
		public double func(double xVar, double yVar, double... params) {
			return params[0]*Math.exp(Math.pow(xVar, 2)*Math.pow(yVar, 2));
		}
	}

}
