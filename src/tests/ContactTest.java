package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import contactManager.Contact;
import contactManager.ContactImpl;

/**
 * The Class ContactTest.
 */
public class ContactTest {

	/**
	 * Tests that the name of the contact is returned correctly.
	 */
	@Test
	public void getNameTest() {
		ContactImpl testCon = new ContactImpl("Joe Bloggs", 1);
		String output = testCon.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	} 

	/**
	 * Tests that the notes of the contact are added and returned correctly.
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
	 * Tests that the id of the contact is returned correctly.
	 */
	@Test
	public void getIDTest(){
		ContactImpl testCon = new ContactImpl("joe Bloggs", 1);
		int output = testCon.getId();
		int expected = 1;
		assertEquals(expected, output);
	}
	
	/**
	 * Tests that a null contact cannot be added.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void nullContactTest(){
		Contact testCon = new ContactImpl(null, 0);
	}
}
