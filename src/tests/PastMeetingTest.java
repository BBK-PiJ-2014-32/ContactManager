package tests;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.time.*;
import java.text.*;

import org.junit.Test;

import contactManager.*;

// TODO: Auto-generated Javadoc
/**
 * The Class PastMeetingTest.
 */
public class PastMeetingTest {

	/**
	 * Adds the past meeting test.
	 */
	@Test
	public void addPastMeetingTest(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man", 1);
		Contact contact2 = new ContactImpl("Miss Miss", 2);
		Contact contact3 = new ContactImpl("Mr Smith", 3);
		Contact contact4 = new ContactImpl("Mrs Ladyla", 4);
		Calendar date = new GregorianCalendar(2015, 4, 12);
		PastMeeting testPM = new PastMeetingImpl(date, contactSet, "Some Notes", 1);
		Calendar outputDate = testPM.getDate();
		String output = sdf.format(outputDate.getTime());
		Calendar expectedCal = sdf.getCalendar();
		expectedCal.set(2015, 4, 12);
		String expected = sdf.format(expectedCal.getTime());
		assertEquals(expected, output);
	}

	/**
	 * Gets the past meeting notes test.
	 *
	 * @return the past meeting notes test
	 */
	@Test
	public void getPastMeetingNotesTest(){
		Calendar date = new GregorianCalendar(2015, 4, 12);
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man", 1);
		PastMeeting testPM = new PastMeetingImpl(date, contactSet, "Some Notes", 1);
		String output = testPM.getNotes();
		String expected = "Some Notes";
		assertEquals(expected, output);
	}
}
