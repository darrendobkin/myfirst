/*
* Classname				TestActiveMQ
* 
* 	This JUnit test employs a test camel context for testing the connection
* 	between nlets-ingest and ActiveMQ.
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

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TestActiveMQ verifies that messages can be read from ActiveMQ.
 */
public class TestActiveMQ extends CamelSpringTestSupport  {

	/** The Constant MSG_TEXT. */
	static final String MSG_TEXT = "==> message used to test ActiveMQ <==";
	
	 /**
	 * @return The new ClassPathXmlApplicationContext.
	 * @see org.apache.camel.test.junit4.CamelSpringTestSupport#createApplicationContext()
	 */
	@Override 
	protected final AbstractXmlApplicationContext createApplicationContext() {
       return new ClassPathXmlApplicationContext("test-ActiveMQ-camel-context.xml");
   }

    /**
     * Tests that a message can be written to the test ActiveMQ queue and
     * then read back.
     *
     * @throws Exception the exception
     */
    @Test
	public final void exchangeMessageWithActiveMQ() throws Exception {
    	
        MockEndpoint mock = getMockEndpoint("mock:verificationEnd");
        TestMessageExchanger tester = new TestMessageExchanger();
    	tester.exchangeTestMessage(MSG_TEXT, template, mock);
    }
}
