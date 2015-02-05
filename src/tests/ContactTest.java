package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import contactManager.Contact;

public class ContactTest {

	@Test
	public void getNameTest() {
		Contact testCon = new Contact("Joe Bloggs");
		String output = testCon.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	}

}
