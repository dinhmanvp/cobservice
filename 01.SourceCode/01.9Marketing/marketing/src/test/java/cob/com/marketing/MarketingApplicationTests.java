package cob.com.marketing;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//@SpringBootTest
public class MarketingApplicationTests extends TestCase {
	 /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MarketingApplicationTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MarketingApplicationTests.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
