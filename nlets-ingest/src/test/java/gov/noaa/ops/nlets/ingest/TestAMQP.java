/*
* Classname				TestAMQP
* 
* 	This JUnit test employs a test camel context for testing the connection
* 	between nlets-ingest and AMQP.  It assumes that the AMQP queue called
* 	nww-ei-ingest-test exists.
*
* Version info
* 
* 	DJDobkin		11/06/2012		Initial version.
* 	DJDobkin		11/13/2012		Exception handling.
*
* Copyright notice
* 
* 	Copyright (C) National Oceanic and Atmospheric Administration 2012
*/
package gov.noaa.ops.nlets.ingest;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TestAMQP verifies that messages can be read from AMQP.
 */
public class TestAMQP extends CamelSpringTestSupport  {

	/** The Constant MSG_TEXT. */
	static final String MSG_TEXT = "==> message used to test AMQP <==";
	
	 /**
	 * @return The new ClassPathXmlApplicationContext.
	 * @see org.apache.camel.test.junit4.CamelSpringTestSupport#createApplicationContext()
	 */
	@Override 
	protected final AbstractXmlApplicationContext createApplicationContext() {
       return new ClassPathXmlApplicationContext("test-AMQP-camel-context.xml");
   }

    /**
     * Tests that a message can be written to the test AMQP queue and
     * then read back.
     *
     * @throws Exception the exception
     */
    @Test
	public final void exchangeMessageWithAMQP() throws Exception {
    	
        MockEndpoint mock = getMockEndpoint("mock:verificationEnd");
    	TestMessageExchanger tester = new TestMessageExchanger();
    	tester.exchangeTestMessage(MSG_TEXT, template, mock);
    }
}
