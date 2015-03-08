package tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import contactManager.Contact;
import contactManager.ContactImpl;
import contactManager.ContactManager;
import contactManager.ContactManagerImpl;
import contactManager.FutureMeeting;
import contactManager.FutureMeetingImpl;
import contactManager.Meeting;
import contactManager.MeetingImpl;
import contactManager.PastMeeting;
import contactManager.PastMeetingImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

// TODO: Auto-generated Javadoc
/**
 * The Class ContactManagerTest.
 */
public class ContactManagerTest {	
	
	/**
	 * Adds the future meeting test.
	 */
	@Test
	public void add1FutureMeetingTest() {
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
	
	@Test(expected = IllegalArgumentException.class)
	public void addNullContactNotesTest(){
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact("Anon", null);
	}
	
	@Test
	public void add2NewPastMeetingTest(){ 
		ContactManager testCM = new ContactManagerImpl();
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
		testCM.addNewPastMeeting(contactSet, date, "Notes from the meeting!!");
		Meeting outMeetCheck = new MeetingImpl(date, contactSet);
		int output = outMeetCheck.getId();
		int expected = 3;
		assertEquals(expected, output);
		
	}
	@Test
	public void getMeetingTest(){
		ContactManager testCM = new ContactManagerImpl();
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		contactSet.add(contact1);
		testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 4, 12));
		testCM.addFutureMeeting(contactSet, new GregorianCalendar(2019, 5, 01));
		testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 9, 17));
		int testId = testCM.addFutureMeeting(contactSet, new GregorianCalendar(2016, 11, 19));
		Meeting outMeet = testCM.getMeeting(testId);
		Calendar output = outMeet.getDate();
		Calendar expected = new GregorianCalendar(2016, 11, 19);
		assertEquals(expected, output);
	}
	
	@Test
	public void getFutureMeetingListTest(){
		ContactManager testCM = new ContactManagerImpl();
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		contactSet.add(contact1);
		testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 4, 12));
		testCM.addFutureMeeting(contactSet, new GregorianCalendar(2019, 5, 01));
		testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 9, 17));
		testCM.addNewPastMeeting(contactSet, new GregorianCalendar(2015, 1, 1), "Notes");
		testCM.addNewPastMeeting(contactSet, new GregorianCalendar(2014, 1, 1), "Notes");
		testCM.addNewPastMeeting(contactSet, new GregorianCalendar(2012, 3, 23), "Notes");
		List<Meeting> outList = new LinkedList<Meeting>();
		int i = 0;
		Calendar [] expectDate = {new GregorianCalendar(2015, 4, 12), new GregorianCalendar(2019, 5, 01),new GregorianCalendar(2015, 9, 17)};  
		outList = testCM.getFutureMeetingList(new GregorianCalendar(2015, 3, 17));
		Iterator<Meeting> it = outList.iterator();
		while(it.hasNext()){
			Calendar outDate = it.next().getDate();
			assertEquals(expectDate[i], outDate);
			i++;
			}
		}
		
		@Test
		public void addMeetingNotesTest(){ //Also uses getPastMeeting for the first time.
			ContactManager testCM = new ContactManagerImpl();
			Set<Contact> contactSet = new LinkedHashSet<Contact>();
			Contact contact1 = new ContactImpl("Mr Man");
			contactSet.add(contact1);
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 3, 8));
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2019, 5, 01));
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 9, 17));
			testCM.addMeetingNotes(6, "Some text about the meeting");
			String output = testCM.getPastMeeting(6).getNotes();
			String expected = "Some text about the meeting";
			assertEquals(expected, output);
		}
}
