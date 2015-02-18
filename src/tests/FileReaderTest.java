package tests;

import static org.junit.Assert.*;

import contactManager.ReaderFile;
import contactManager.ReaderFileImpl;
import contactManager.Contact;
import contactManager.ContactImpl;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void checkForFiletest() {
		ReaderFile testReader = new ReaderFileImpl();
		boolean output = testReader.checkForFile();
		boolean expected = true;
		assertEquals(expected, output);

	}

	@Test
	public void contactRetreive(){
		ReaderFile testReader = new ReaderFileImpl();
		Contact contact = testReader.getContact(1);
		String output = contact.getName();
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	}
}
