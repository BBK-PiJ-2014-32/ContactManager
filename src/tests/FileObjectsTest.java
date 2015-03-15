package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import contactManager.*;

public class FileObjectsTest {

	@Test
	public void getObjectTest() {
		Contact testCon = new ContactImpl("Test Con", 1);
		FileObjects testObj = new FileObjectsImpl(testCon);
		Contact outCon = (Contact) testObj.getObject();
		String output = outCon.getName();
		String expected = "Test Con";
		assertEquals(expected, output);
	}

}
