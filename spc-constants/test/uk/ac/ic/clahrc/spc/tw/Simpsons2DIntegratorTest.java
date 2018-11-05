package uk.ac.ic.clahrc.spc.tw;

import static org.junit.Assert.*;

import org.junit.Test;


public class Simpsons2DIntegratorTest {

	// Test the 1D Simpson's Rule integrator on some well known
	// example integrals.
	// Test that the weights used in the sum within Simpson's Rule
	// are correct for some examples.
	// Test that the integrator, and the weight function, throw
	// exceptions for bad arguments.
	
	@Test
	public void testSimpsons2DIntegrator() {
		double exact_integral_1 = (double) 1/9;
		double correct_answer_integral_2 = (double) 1.1351*2;
		double exact_integral_3 = (double) 8/9;
		
		double simpsons_integral_1 = Simpsons2DIntegrator.integrate(new sqrIntegrand(), 0, 1, 0, 1, 10, 10);
		double simpsons_integral_2 = Simpsons2DIntegrator.integrate(
				new expSqrIntegrand(), 0, 1, 0, 1, 1000, 1000, 2);
		double simpsons_integral_3 = Simpsons2DIntegrator.integrate(new sqrIntegrand(), 0, 2, 0, 1, 10, 10);
		
		assertEquals(exact_integral_1, simpsons_integral_1, 0.0001);
		assertEquals(correct_answer_integral_2, simpsons_integral_2, 0.0001);
		assertEquals(exact_integral_3, simpsons_integral_3, 0.0001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIntegrateInvalidStripsOdd() {
		Simpsons2DIntegrator.integrate(new sqrIntegrand(), 0, 1, 0, 1, 3, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIntegrateInvalidStripsZero() {
		Simpsons2DIntegrator.integrate(new sqrIntegrand(), 0, 1, 0, 1, 0, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIntegrateInvalidLimits() {
		Simpsons2DIntegrator.integrate(new sqrIntegrand(), 1, 0, 0, 1, 4, 4);
	}
	
	@Test
	public void testWeights() {
		// Check weights are correct for two by two strips
		assertEquals(1, Simpsons2DIntegrator.weight(0, 0, 2, 2));
		assertEquals(4, Simpsons2DIntegrator.weight(1, 0, 2, 2));
		assertEquals(1, Simpsons2DIntegrator.weight(2, 0, 2, 2));
		assertEquals(4, Simpsons2DIntegrator.weight(0, 1, 2, 2));
		assertEquals(16, Simpsons2DIntegrator.weight(1, 1, 2, 2));
		assertEquals(4, Simpsons2DIntegrator.weight(2, 1, 2, 2));
		assertEquals(1, Simpsons2DIntegrator.weight(0, 2, 2, 2));
		assertEquals(4, Simpsons2DIntegrator.weight(1, 2, 2, 2));
		assertEquals(1, Simpsons2DIntegrator.weight(2, 2, 2, 2));
		
		// Check weights are correct for four by four strips
		assertEquals(1, Simpsons2DIntegrator.weight(0, 0, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(1, 0, 4, 4));
		assertEquals(2, Simpsons2DIntegrator.weight(2, 0, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(3, 0, 4, 4));
		assertEquals(1, Simpsons2DIntegrator.weight(4, 0, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(0, 1, 4, 4));
		assertEquals(16, Simpsons2DIntegrator.weight(1, 1, 4, 4));
		assertEquals(8, Simpsons2DIntegrator.weight(2, 1, 4, 4));
		assertEquals(16, Simpsons2DIntegrator.weight(3, 1, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(4, 1, 4, 4));
		assertEquals(2, Simpsons2DIntegrator.weight(0, 2, 4, 4));
		assertEquals(8, Simpsons2DIntegrator.weight(1, 2, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(2, 2, 4, 4));
		assertEquals(8, Simpsons2DIntegrator.weight(3, 2, 4, 4));
		assertEquals(2, Simpsons2DIntegrator.weight(4, 2, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(0, 3, 4, 4));
		assertEquals(16, Simpsons2DIntegrator.weight(1, 3, 4, 4));
		assertEquals(8, Simpsons2DIntegrator.weight(2, 3, 4, 4));
		assertEquals(16, Simpsons2DIntegrator.weight(3, 3, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(4, 3, 4, 4));
		assertEquals(1, Simpsons2DIntegrator.weight(0, 4, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(1, 4, 4, 4));
		assertEquals(2, Simpsons2DIntegrator.weight(2, 4, 4, 4));
		assertEquals(4, Simpsons2DIntegrator.weight(3, 4, 4, 4));
		assertEquals(1, Simpsons2DIntegrator.weight(4, 4, 4, 4));
	
		
		// Check some weights correct for 1000 by 1000 strips
		assertEquals(16, Simpsons2DIntegrator.weight(203, 105, 1000, 1000));
		assertEquals(4, Simpsons2DIntegrator.weight(554, 896, 1000, 1000));
		assertEquals(4, Simpsons2DIntegrator.weight(0, 105, 1000, 1000));
		assertEquals(2, Simpsons2DIntegrator.weight(554, 0, 1000, 1000));
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalid0xStrips() {
		Simpsons2DIntegrator.weight(0, 0, 0, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalid0yStrips() {
		Simpsons2DIntegrator.weight(0, 0, 1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidOddxStrips() {
		Simpsons2DIntegrator.weight(5, 5, 7, 8);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidOddyStrips() {
		Simpsons2DIntegrator.weight(5, 5, 8, 7);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidI() {
		Simpsons2DIntegrator.weight(13, 12, 12, 12);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidJ() {
		Simpsons2DIntegrator.weight(12, 13, 12, 12);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidNegativeI() {
		Simpsons2DIntegrator.weight(-1, 2, 4, 4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidNegativeJ() {
		Simpsons2DIntegrator.weight(2, -2, 4, 4);
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
