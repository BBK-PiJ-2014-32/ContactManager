package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import contactManager.Contact;
import contactManager.ContactImpl;

public class ContactTest {

	@Before
	public void resetLastIdTest(){
		Contact testCon = new ContactImpl();
		boolean output = testCon.resetLastId();
		boolean expected = true;
		assertEquals(expected, output);
	}
	
	@Test
	public void getNameTest() {
		ContactImpl testCon = new ContactImpl();
		testCon.setName("joe Bloggs");
		String output = testCon.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	} 

	@Test
	public void addGetNotesTest(){
		ContactImpl testCon = new ContactImpl();
		testCon.setName("joe Bloggs");
		testCon.addNotes("Test note input");
		String output = testCon.getNotes();
		String expected = "Test note input";
		assertEquals(expected, output);
	}
	
	@Test
	public void getIDTest(){
		ContactImpl testCon1 = new ContactImpl();
		testCon1.setName("joe Bloggs");
		ContactImpl testCon2 = new ContactImpl();
		testCon2.setName("John Smith");
		ContactImpl testCon3 = new ContactImpl();
		testCon3.setName("Mrs Something");
		ContactImpl testCon4 = new ContactImpl();
		testCon4.setName("Mr Nobody");
		int output = testCon3.getId();
		int expected = 4;
		assertEquals(expected, output);
	}
}
