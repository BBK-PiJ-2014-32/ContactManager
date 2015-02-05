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

}
