package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import contactManager.Contact;
import contactManager.ContactImpl;
import contactManager.ContactManager;
import contactManager.ContactManagerImpl;
import contactManager.FutureMeeting;
import contactManager.FutureMeetingImpl;


public class ContactManagerTest {

	@Test
	public void addFutureMeetingTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		Contact contact2 = new ContactImpl("Miss Miss");
		Contact contact3 = new ContactImpl("Mr Smith");
		Contact contact4 = new ContactImpl("Mrs Ladyla");
		contactSet.add(contact1);
		contactSet.add(contact2);
		contactSet.add(contact3);
		contactSet.add(contact4);
		ContactManager testCM = new ContactManagerImpl();
		Calendar date = sdf.getCalendar();
		date.set(Calendar.YEAR, 2015);
		date.set(Calendar.MONTH, 4);
		date.set(Calendar.DAY_OF_MONTH, 12);
		int output = testCM.addFutureMeeting(contactSet, date);
		int expected = 1;
		assertEquals(expected, output);
	}

}