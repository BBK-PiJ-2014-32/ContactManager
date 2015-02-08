package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import contactManager.Contact;
import contactManager.ContactImpl;

public class ContactTest {

	@Test
	public void getNameTest() {
		Contact testCon = new ContactImpl("Joe Bloggs");
		String output = testCon.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	}
	
	@Test
	public void addGetNotesTest(){
		Contact testCon = new ContactImpl("Joe Bloggs");
		testCon.addNotes("Test note input");
		String output = testCon.getNotes();
		String expected = "Test note input";
		assertEquals(expected, output);
	}
	
	@Test
	public void getIDTest(){
		Contact testCon = new ContactImpl("Joe Bloggs");
		Contact testCon1 = new ContactImpl("John Smith");
		Contact testCon2 = new ContactImpl("Mrs Something");
		Contact testCon3 = new ContactImpl("Mr Nobody");
		int output = testCon.getId();
		int expected = 4;
		assertEquals(expected, output);
	}
}
