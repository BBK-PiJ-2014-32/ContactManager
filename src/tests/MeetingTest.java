package tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.text.*;
import java.time.*;
import java.util.Set;

import org.junit.Test;

import contactManager.Contact;
import contactManager.ContactImpl;
import contactManager.Meeting;
import contactManager.MeetingImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class MeetingTest.
 */
public class MeetingTest {

	/**
	 * Gets the id test.
	 *
	 * @return the id test
	 */
	@Test
	public void getIdTest() {
		Calendar date = new GregorianCalendar(2015, 4, 12);
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		contactSet.add(contact1);
		Meeting testMeet = new MeetingImpl(date, contactSet);
		int output = testMeet.getId();
		int expected = 1;
		assertEquals(expected, output);
	}
	
	/**
	 * Gets the date test.
	 *
	 * @return the date test
	 */
	@Test
	public void getDateTest(){
		Calendar date = new GregorianCalendar(2015, 4, 12);
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		contactSet.add(contact1);
		Meeting testMeet = new MeetingImpl(date, contactSet);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar outputCal = testMeet.getDate();
		String output = sdf.format(outputCal.getTime());
		Calendar expectedCal = sdf.getCalendar();
		expectedCal.set(Calendar.YEAR, 2015);
		expectedCal.set(Calendar.MONTH, 4);
		expectedCal.set(Calendar.DAY_OF_MONTH, 12);
		String expected = sdf.format(expectedCal.getTime());
		assertEquals(expected, output);	
	}
	
	/**
	 * Gets the contacts test.
	 *
	 * @return the contacts test
	 */
	@Test
	public void getContactsTest(){
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		Contact contact2 = new ContactImpl("Miss Miss");
		Contact contact3 = new ContactImpl("Mr Smith");
		Contact contact4 = new ContactImpl("Mrs Ladyla");
		contactSet.add(contact1);
		contactSet.add(contact2);
		contactSet.add(contact3);
		contactSet.add(contact4);
		Calendar date = new GregorianCalendar(2015, 4, 12);
		Meeting testMeet = new MeetingImpl(date,contactSet);
		Set<Contact> outputSet = testMeet.getContacts();
		Iterator<Contact> it = outputSet.iterator();
		String output = "";
		while(it.hasNext()){
		  Contact loopCon = it.next();
		  output += loopCon.getName() + ", ";
		}
		String expected = "Mr Man, Miss Miss, Mr Smith, Mrs Ladyla, ";
		assertEquals(expected, output);
	}
	
	/**
	 * Null contact set test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void nullContactSetTest(){
		Calendar date = new GregorianCalendar(2015, 4, 12);
		Meeting testMeet = new MeetingImpl(date, null);
	}
}
