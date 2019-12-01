package cob.com.business;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class BusinessApplicationTests extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BusinessApplicationTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BusinessApplicationTests.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
