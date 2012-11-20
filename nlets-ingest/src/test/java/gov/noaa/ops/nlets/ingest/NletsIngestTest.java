/*
* Classname				NletsIngestTest
* 
* 	This JUnit module verifies the NletsIngest implementation 
*	and utility classes.  
*
* Version info
* 
* 	Sung Vo		11/06/2012		Initial version.
*
* Copyright notice
* 
* 	Copyright (C) National Oceanic and Atmospheric Administration 2012
*/
package gov.noaa.ops.nlets.ingest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The NletsIngestTest will verify the NletsIngest implementation 
 * and utility classes.  JUnit tests will validate the following:
 * <ul>
 * <li> Instance of NWWProduct class
 * <li> isValid() method
 * <li> toString() method
 * <li> getTimeCreated() method
 * </ul>
 * @author sung.vo
 * Last modification 11-05-12 
 */


public class NletsIngestTest {
	
	/** The maximum message length. */
	static final int MAX_LEN = 500000; 
	
	/** The product. */
	private NWWProduct product;

	/**
	 * Sets up before the test.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tears down after the test.
	 *
	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Create a NWWProduct object during setup().
	 *
	 * @throws Exception the exception
	 */
	@Before
	public final void setUp() throws Exception {
		
		new NWWProduct();
		product = NWWProduct.createNWWProduct("THIS IS A TEST MESSAGE");
		// product = NWWProduct.createNWWProduct(null); - will fail
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public final void tearDown() throws Exception {
	}

	/**
	 * The testNWWProduct will verify the following.
	 * <ul>
	 * <li> isValid() method
	 * <li> toString() method
	 * <li> getTimeCreated() method
	 * </ul>
	 * @author sung.vo
	 * Last modification 11-05-12 
	 */
	@Test
	public final void testNWWProduct() {
		Assert.assertTrue(product.toString().length() < MAX_LEN);
		Assert.assertTrue(product.isValid());
		System.out.println("Time:" + product.getTimeCreated());
		System.out.println("Content: " + product.toString());
				
	}

}
