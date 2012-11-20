/*
* Classname				TestNletsIngestBean
* 
* 	This JUnit test employs a test camel context for testing the 
*	NletsIngestBean class.  
*
* Version info
* 
* 	DJDobkin		11/06/2012		Initial version.
*
* Copyright notice
* 
* 	Copyright (C) National Oceanic and Atmospheric Administration 2012
*/
package gov.noaa.ops.nlets.ingest;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TestAMQP verifies that messages can be read from AMQP.
 */
public class TestNletsIngestBean extends CamelSpringTestSupport  {

	/** The Constant MSG_TEXT. */
	static final String MSG_TEXT = "==> message used to test NletsIngestBean <==";
	
	 /**
	 * @return The new ClassPathXmlApplicationContext.
	 * @see org.apache.camel.test.junit4.CamelSpringTestSupport#createApplicationContext()
	 */
	@Override 
	protected final AbstractXmlApplicationContext createApplicationContext() {
       return new ClassPathXmlApplicationContext("test-NletsIngestBean-camel-context.xml");
   }

    /**
     * Test that a valid message gets converted to an NWWProduct
     * containing the original message text.
     *
     * @throws Exception the exception
     */
    @Test
	public final void testNWWProduct() throws Exception {
    	
        MockEndpoint mock = getMockEndpoint("mock:verificationEnd");
		List<String> expectedBodies = new ArrayList<String>();
    	
        mock.expectedMessageCount(1);
        
        // TBD: how to match MSG_TEXT against NWWProduct content???
      	//mock.expectedMessagesMatches(body().regex("test-[0-9]{4}-[0-9]{2}-[0-9]{2}\\.xml"));
      	//mock.expectedMessagesMatches(body().regex(".*"));
        //assertTrue("Should have contained the required result", 
        	//	body().getClass().getName()
        //expectedBodies.add(MSG_TEXT);
        //mock.expectedBodiesReceived(expectedBodies);
        
        template.sendBody("direct:inputPoint", MSG_TEXT);
        
      	mock.assertIsSatisfied();
      	
      	System.out.println(body());
      	System.out.println(body());
      	System.out.println(body());
    }
}
