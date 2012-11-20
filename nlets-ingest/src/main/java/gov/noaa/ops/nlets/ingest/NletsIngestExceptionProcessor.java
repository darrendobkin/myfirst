/*
* Classname				NletsIngestExceptionProcessor
* 
* 	This class processes exceptions thrown in the Camel context.  Its
* 	default mode is to log the exception message with all nested causes and
* 	halt the Camel route.   
*
* Version info
* 
* 	DJDobkin		11/14/2012		Initial version.
*
* Copyright notice
* 
* 	Copyright (C) National Oceanic and Atmospheric Administration 2012
*/
package gov.noaa.ops.nlets.ingest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * The Class NletsIngestExceptionProcessor.
 */
public class NletsIngestExceptionProcessor implements Processor {
	
	/** A logger instance named NletsIngestExceptionProcessor. */
	private static Logger  logger = Logger.getLogger(NletsIngestExceptionProcessor.class);

	/**
	 * If an exception is thrown, log the exception with all nested causes
	 * and stop the route.
	 *
	 * @param exchange the exchange
	 * @throws Exception the exception
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public final void process(final Exchange exchange) throws Exception {
		
		Exception ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		Throwable nextEx;
		
		logger.setLevel(Level.ERROR);
		StringBuilder sb = new StringBuilder();
		nextEx = ex;
		do {
			if (sb.length() > 0) {
				sb.append("\r\nCause: ");
			}
			sb.append(nextEx.getMessage());
			nextEx = nextEx.getCause();
		} while (nextEx != null);
		logger.error("Route stopped due to: " + sb.toString());
		
		exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
	}
}
