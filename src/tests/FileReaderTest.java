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

}
