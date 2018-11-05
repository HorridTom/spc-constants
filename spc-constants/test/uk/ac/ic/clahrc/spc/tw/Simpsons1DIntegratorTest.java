package uk.ac.ic.clahrc.spc.tw;

import static org.junit.Assert.*;

import org.junit.Test;

public class Simpsons1DIntegratorTest {

	@Test
	public void testSimpsons1DIntegrator() {
		double exact_integral_1 = 1/3;
		double correct_answer_integral_2 = 1.46265*2;
		
		double simpsons_integral_1 = Simpsons1DIntegrator.integrate(new sqrIntegrand(), 0, 1, 2);
		double simpsons_integral_2 = Simpsons1DIntegrator.integrate(new expSqrIntegrand(), 0, 1, 1000, 2);
		
		assertEquals(exact_integral_1, simpsons_integral_1, 0.0000001);
		assertEquals(correct_answer_integral_2, simpsons_integral_2, 0.0001);
	}
	
	@Test
	public void testWeights() {
		// Check weights are correct for two strips
		assertEquals(1, Simpsons1DIntegrator.weight(0, 2));
		assertEquals(4, Simpsons1DIntegrator.weight(1, 2));
		assertEquals(1, Simpsons1DIntegrator.weight(2, 2));
		
		// Check weights are correct for four strips
		assertEquals(1, Simpsons1DIntegrator.weight(0, 4));
		assertEquals(4, Simpsons1DIntegrator.weight(1, 4));
		assertEquals(2, Simpsons1DIntegrator.weight(2, 4));
		assertEquals(4, Simpsons1DIntegrator.weight(3, 4));
		assertEquals(1, Simpsons1DIntegrator.weight(4, 4));
		
		// Check weights are correct for four strips
		assertEquals(1, Simpsons1DIntegrator.weight(0, 6));
		assertEquals(4, Simpsons1DIntegrator.weight(1, 6));
		assertEquals(2, Simpsons1DIntegrator.weight(2, 6));
		assertEquals(4, Simpsons1DIntegrator.weight(3, 6));
		assertEquals(2, Simpsons1DIntegrator.weight(4, 6));
		assertEquals(4, Simpsons1DIntegrator.weight(5, 6));
		assertEquals(1, Simpsons1DIntegrator.weight(6, 6));
		
		// Check four weights are correct for 1000 strips
		assertEquals(1, Simpsons1DIntegrator.weight(0, 1000));
		assertEquals(4, Simpsons1DIntegrator.weight(365, 1000));
		assertEquals(2, Simpsons1DIntegrator.weight(896, 1000));
		assertEquals(1, Simpsons1DIntegrator.weight(1000, 1000));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalid0Strips() {
		Simpsons1DIntegrator.weight(0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidOddStrips() {
		Simpsons1DIntegrator.weight(5, 11);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidI() {
		Simpsons1DIntegrator.weight(13, 12);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWeightsInvalidNegativeI() {
		Simpsons1DIntegrator.weight(-1, 2);
	}
	
	public class sqrIntegrand implements Simpsons1DIntegrator.Integrand {
		public double func(double xVar, double... params) {
			return Math.pow(xVar, 2);
		}
	}
	
	public class expSqrIntegrand implements Simpsons1DIntegrator.Integrand {
		public double func(double xVar, double... params) {
			return params[0]*Math.exp(Math.pow(xVar, 2));
		}
	}

}
