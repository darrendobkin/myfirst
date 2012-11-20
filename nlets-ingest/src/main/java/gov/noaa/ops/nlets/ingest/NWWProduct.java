/* 
* Java filename				NWWProduct.java		
*
*	The NWWProduct class wraps the text of the incoming message and
*	is then enqueued on the ActiveMQ ingest queue.
*
* Version info
* 
* 	DJDobkin		10/29/2012		Initial version.
* 	DJDobkin		11/16/2012		Logging.
*
* Copyright notice
* 
* 	Copyright (C) National Oceanic and Atmospheric Administration 2012
*
*/
package gov.noaa.ops.nlets.ingest;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * The NWWProduct class.  These objects are created by 
 * wrapping the body of the incoming message.  They are then
 * queued to ActiveMQ by the Camel context.
 */
public class NWWProduct implements Serializable {

	/** A logger instance named NWWProduct. */
	private static Logger  logger = Logger.getLogger(NWWProduct.class);

    /** The maximum valid message body size. */
	public static final int MAX_MESSAGE_LEN = 500000;
	
    /** The Constant serialVersionUID, required for Serializable. */
    private static final long serialVersionUID = 1L;
	
	/** A timestamp for when the object was created. */
	private Date mDateTimeCreated;
	
	/** The body of the message. */
	private String mMessageText;
	
	/** Whether the object is valid. */
	private Boolean mValid;
	
	/**
	 * Creates the NWWProduct object.
	 *
	 * @param strMessage the message string to wrap in the object.
	 * @return the NWWProduct
	 */
	public static final NWWProduct createNWWProduct(final String strMessage) {
		logger.setLevel(Level.INFO);
		NWWProduct newProd = new NWWProduct();
		newProd.mDateTimeCreated = new Date();
		newProd.mMessageText = strMessage;
		newProd.setValid();
		return newProd;
	}
	
	/**
	 * Validates the message.
	 */
	private void setValid() {
		if ((mMessageText == null) || (mMessageText.length() > MAX_MESSAGE_LEN)) {
			mValid = false;
		} else {
			mValid = true;
		}
		logger.info("Valid  = " + mValid.toString());
	}
	
	/**
	 * Returns whether the object is valid.
	 *
	 * @return the boolean
	 */
	public final Boolean isValid() {
		return mValid;
	}

	/** Returns a text version of the object for logging/debugging.
	 * @return the string version
	 */
	public final String toString() {
		String strDate;
		String strRet;
		
        SimpleDateFormat fmtDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSS");
        strDate = fmtDate.format(mDateTimeCreated);
    	strRet = "NWWProduct created " + strDate + "\r\n";
        if (mMessageText != null) {
        	strRet += mMessageText + "\r\n";
        }
        return strRet; 
	}
	
	/**
	 * Gets the time created.
	 *
	 * @return the time created
	 */
	public final Date getTimeCreated() {
		return mDateTimeCreated;
	}
}
