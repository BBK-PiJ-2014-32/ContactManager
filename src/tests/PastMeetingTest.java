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

public class PastMeetingTest {

	@Test
	public void addPastMeetingTest(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		Contact contact2 = new ContactImpl("Miss Miss");
		Contact contact3 = new ContactImpl("Mr Smith");
		Contact contact4 = new ContactImpl("Mrs Ladyla");
		Calendar date = new GregorianCalendar(2015, 4, 12);
		PastMeeting testPM = new PastMeetingImpl(date, contactSet, "Some Notes");
		Calendar outputDate = testPM.getDate();
		String output = sdf.format(outputDate.getTime());
		Calendar expectedCal = sdf.getCalendar();
		expectedCal.set(2015, 4, 12);
		String expected = sdf.format(expectedCal.getTime());
		assertEquals(expected, output);
	}

	@Test
	public void getPastMeetingNotesTest(){
		Calendar date = new GregorianCalendar(2015, 4, 12);
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		PastMeeting testPM = new PastMeetingImpl(date, contactSet, "Some Notes");
		String output = testPM.getNotes();
		String expected = "Some Notes";
		assertEquals(expected, output);
	}
}
