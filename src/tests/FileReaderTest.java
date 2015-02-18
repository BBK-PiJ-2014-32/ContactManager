package tests;

import static org.junit.Assert.*;

import contactManager.FileReader;
import contactManager.FileReaderImpl;

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
	public void contactNameRetreive(){
		FileReader testReader = new FileReaderImpl();
		String output = testReader.getContactName(1);
		String expected = "Joe Bloggs";
		assertEquals(expected, output);
	}
}
