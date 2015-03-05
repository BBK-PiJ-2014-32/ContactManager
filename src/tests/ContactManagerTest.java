package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import contactManager.Meeting;
import contactManager.MeetingImpl;


// TODO: Auto-generated Javadoc
/**
 * The Class ContactManagerTest.
 */
public class ContactManagerTest {

	/**
	 * Adds the future meeting test.
	 */
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
	
	/**
	 * Date in the past test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void dateInThePastTest(){
		Calendar date = new GregorianCalendar(2014, 4, 12);
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		contactSet.add(contact1);
		ContactManager testCM = new ContactManagerImpl();
		testCM.addFutureMeeting(contactSet, date);
	}
	
	@Test
	public void addContactTest(){
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact("Mr Man", "He's the man");
		Set<Contact> outputSet = testCM.getContacts("Mr Man");
		Iterator<Contact> it = outputSet.iterator();
		String output = "";
		while(it.hasNext()){
		  Contact loopCon = it.next();
		  output = loopCon.getName();
		}
		String expected = "Mr Man";
		assertEquals(expected, output);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addNullContactNameTest(){
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact(null, "notes");
	}
}
