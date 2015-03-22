package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import contactManager.*;

/**
 * The Class FileObjectsTest.
 */
public class FileObjectsTest {

	/**
	 * Tests that the FileObjects class is holding a contact correctly.
	 */
	@Test
	public void getObjectTest() {
		Contact testCon = new ContactImpl("Test Con", 1);
		FileObjects testObj = new FileObjectsImpl(testCon);
		Contact outCon = (Contact) testObj.getObject();
		String output = outCon.getName();
		String expected = "Test Con";
		assertEquals(expected, output);
	}
	
	/**
	 * Tests that the FileObjects class is holding a meeting correctly.
	 */
	@Test
	public void getObjectMeetingTest(){
		Calendar date = new GregorianCalendar(2015, 4, 12);
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man", 1);
		contactSet.add(contact1);
		Meeting testMeet = new MeetingImpl(date, contactSet, 1);
		FileObjects testObj = new FileObjectsImpl(testMeet);
		Meeting outMeet = (Meeting) testObj.getObject();
		int output = outMeet.getId();
		int expected = 1;
		assertEquals(expected, output);
	}
}
