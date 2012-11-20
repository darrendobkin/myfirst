/*
* Classname				TestMessageExchanger
* 
* 	This class provides common methods for testing both AMQP and ActiveMQ.
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
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;

/**
 * The Class TestMessageExchanger for common support of testing AMQP and ActiveMQ.
 */
public final class TestMessageExchanger {
	
	/**
	 * Exchange test message with a queueing server.
	 *
	 * @param strMsg the text of the test message
	 * @param template the ProducerTemplate to send the message to
	 * @param mock the endpoint where output is expected
	 * @throws Exception the exception
	 */
	public void exchangeTestMessage(
			final String strMsg,
			final ProducerTemplate template, 
			final MockEndpoint mock) throws Exception {

		List<String> expectedBodies = new ArrayList<String>();
    	
        mock.expectedMessageCount(1);
        
        expectedBodies.add(strMsg);
        mock.expectedBodiesReceived(expectedBodies);
        
        template.sendBody("direct:inputPoint", strMsg);
        
      	mock.assertIsSatisfied();

	}
}
