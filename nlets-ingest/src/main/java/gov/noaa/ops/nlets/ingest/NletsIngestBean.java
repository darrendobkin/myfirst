/* 
* Java filename				NletsIngestBean.java		
*
*	The NletsIngestBean class is instantiated as a bean
*	from the ingest Camel route.  It wraps the the incoming
*	message text into an NWWProduct. 
*
* Version info
* 
* 	DJDobkin		10/30/2012		Initial version.
*   DJDobkin		11/16/2012		Logging.
*
* Copyright notice
* 
* 	Copyright (C) National Oceanic and Atmospheric Administration 2012
*
*/
package gov.noaa.ops.nlets.ingest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * The NletsIngestBean class.  This class is instantiated as a bean
 * in the Camel route.  It creates an NWWProduct object for each
 * incoming message.
 */
public class NletsIngestBean {
	
	/** How much of the message to log. */
	static final int MSG_LOG_LEN = 20;
	
	/** A logger instance named NletsIngestBean. */
	private static Logger  logger = Logger.getLogger(NletsIngestBean.class);
	
	/**
	 * Generate product.
	 *
	 * @param body the body
	 * @return the object
	 */
	public final Object createProduct(final Object body) {

		logger.setLevel(Level.INFO);

		NWWProduct newProd = NWWProduct.createNWWProduct((String) body);
		
		logger.info("NWWProduct created for message: " + ((String) body).substring(0, MSG_LOG_LEN) + " ...");
		
//		int x, y;
//		
//		x = 3;
//		y = 0;
//		
//		System.out.print(x/y);
//		
//		
		return newProd;
	}
}
