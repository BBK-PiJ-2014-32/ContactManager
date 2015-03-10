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
	 * Gets the name test.
	 *
	 * @return the name test
	 */
	@Test
	public void getNameTest() {
		ContactImpl testCon = new ContactImpl("Joe Bloggs", 1);
		String output = testCon.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	} 

	/**
	 * Adds the get notes test.
	 */
	@Test
	public void addGetNotesTest(){
		ContactImpl testCon = new ContactImpl("Joe Bloggs", 1);
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
		ContactImpl testCon = new ContactImpl("joe Bloggs", 1);
		int output = testCon.getId();
		int expected = 1;
		assertEquals(expected, output);
	}
	
	/**
	 * Null contact test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void nullContactTest(){
		Contact testCon = new ContactImpl(null, 0);
	}
}
