package uk.ac.ic.clahrc.spc.tw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Simpsons1DIntegratorTest.class, Simpsons2DIntegratorTest.class,
	SPCConstantsTest.class })
public class AllTests {

}
