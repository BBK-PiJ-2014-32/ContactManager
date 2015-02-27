package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import contactManager.Contact;
import contactManager.ContactImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactTest.
 */
public class ContactTest {

	/**
	 * Reset last id test.
	 */
	@Before
	public void resetLastIdTest(){
		Contact testCon = new ContactImpl("");
		boolean output = testCon.resetLastId();
		boolean expected = true;
		assertEquals(expected, output);
	}
	
	/**
	 * Gets the name test.
	 *
	 * @return the name test
	 */
	@Test
	public void getNameTest() {
		ContactImpl testCon = new ContactImpl("Joe Bloggs");
		String output = testCon.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	} 

	/**
	 * Adds the get notes test.
	 */
	@Test
	public void addGetNotesTest(){
		ContactImpl testCon = new ContactImpl("Joe Bloggs");
		testCon.addNotes("Test note input");
		String output = testCon.getNotes();
		String expected = "Test note input";
		assertEquals(expected, output);
	}
	
	/**
	 * Gets the ID test.
	 *
	 * @return the ID test
	 */
	@Test
	public void getIDTest(){
		ContactImpl testCon1 = new ContactImpl("joe Bloggs");
		ContactImpl testCon2 = new ContactImpl("John Smith");
		ContactImpl testCon3 = new ContactImpl("Mrs Something");
		ContactImpl testCon4 = new ContactImpl("Mr Nobody");
		int output = testCon4.getId();
		int expected = 4;
		assertEquals(expected, output);
	}
	
	/**
	 * Null contact test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void nullContactTest(){
		Contact testCon = new ContactImpl(null);
	}
}
