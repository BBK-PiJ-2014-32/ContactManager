package tests;

import static org.junit.Assert.*;

import contactManager.ReaderFile;
import contactManager.ReaderFileImpl;
import contactManager.Contact;
import contactManager.ContactImpl;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class ReaderFileTest.
 */
public class ReaderFileTest {

	/**
	 * Check for filetest.
	 */
	@Test
	public void checkForFiletest() {
		ReaderFile testReader = new ReaderFileImpl();
		boolean output = testReader.checkForFile();
		boolean expected = true;
		assertEquals(expected, output);

	}

	/**
	 * Contact retreive.
	 */
	@Test
	public void contactRetreive(){
		ReaderFile testReader = new ReaderFileImpl();
		Contact contact = testReader.getContact(1);
		String output = contact.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	}
}
