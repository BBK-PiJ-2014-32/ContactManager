package tests;

import static org.junit.Assert.*;

import contactManager.FileReader;
import contactManager.FileReaderImpl;
import contactManager.Contact;
import contactManager.ContactImpl;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void checkForFiletest() {
		FileReader testReader = new FileReaderImpl();
		boolean output = testReader.checkForFile();
		boolean expected = true;
		assertEquals(expected, output);

	}

	@Test
	public void contactRetreive(){
		FileReader testReader = new FileReaderImpl();
		Contact contact = testReader.getContact(1);
		String output = contact.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	}
}
