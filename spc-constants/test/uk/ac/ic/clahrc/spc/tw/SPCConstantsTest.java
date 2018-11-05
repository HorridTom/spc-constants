package uk.ac.ic.clahrc.spc.tw;

import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.Test;


public class SPCConstantsTest {
	
	/*SPCConstants spcConst;
	
	@Before
	public void setUp() {
		this.spcConst = new SPCConstants();
	}*/
	
	@Test
	public void testConstantD2() {
		double d2_n_2 = SPCConstants.d2(2);
		double d2_n_3 = SPCConstants.d2(3);
		double d2_n_10 = SPCConstants.d2(10);
		double d2_n_100 = SPCConstants.d2(100);
		
		assertEquals((double)1.128, d2_n_2, 0.001);
		assertEquals((double)1.693, d2_n_3, 0.001);
		assertEquals((double)3.078, d2_n_10, 0.001);
		assertEquals((double)5.015, d2_n_100, 0.001);
	}
	
	@Test
	public void testConstantD3() {
		double d3_n_2 = SPCConstants.d3(2);
		double d3_n_3 = SPCConstants.d3(3);
		double d3_n_10 = SPCConstants.d3(10);
		double d3_n_100 = SPCConstants.d3(100);
		
		assertEquals(0.8525, d3_n_2, 0.0001);
		assertEquals(0.8884, d3_n_3, 0.0001);
		assertEquals(0.7971, d3_n_10, 0.0001);
		assertEquals(0.6052, d3_n_100, 0.0001);
	}
	
}
