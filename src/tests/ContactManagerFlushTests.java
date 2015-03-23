package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import contactManager.*;

/**
 * The Class ContactManagerFlushTests.
 */
public class ContactManagerFlushTests {

	/**
	 * Delete file.
	 */
	@Before
	public void deleteFile(){
		File file = new File("ContactManager.xml");
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * First flush test.
	 */
	@Test
	public void firstFlushTest(){
		ContactManager testCM1 = new ContactManagerImpl();
		testCM1.addNewContact("Mr Man", "He's the man");
		testCM1.addNewContact("Miss SomeOne", "She's not a man");
		testCM1.addNewContact("Mr Testy", "Testing");
		testCM1.flush();
		ContactManager testCM2 = new ContactManagerImpl();
		Set<Contact> outSet = new LinkedHashSet<Contact>(); 
		outSet = testCM2.getContacts(1, 2, 3);
		int i = 0;
		String [] expected = {"Mr Man", "Miss SomeOne", "Mr Testy"};
		Iterator<Contact> it = outSet.iterator();
		while(it.hasNext()){
			Contact output = it.next();
			assertEquals(expected[i], output.getName());
			i++;
		}
	}
	
	/**
	 * Second flush test.
	 */
	@Test
	public void secondFlushTest(){
		ContactManager testCM1 = new ContactManagerImpl();
		testCM1.addNewContact("Mr Man", "He's the man");
		testCM1.addNewContact("Miss SomeOne", "She's not a man");
		testCM1.addNewContact("Mr Testy", "Testing");
		testCM1.addFutureMeeting(testCM1.getContacts(1,2,3), new GregorianCalendar(2015, 8, 15));
		testCM1.flush();
		ContactManager testCM2 = new ContactManagerImpl();
		Meeting testMeet = testCM2.getFutureMeeting(1);
		Set<Contact> outSet = new LinkedHashSet<Contact>();
		outSet = testMeet.getContacts();
		int i = 0;
		String [] expected = {"Mr Man", "Miss SomeOne", "Mr Testy"};
		Iterator<Contact> it = outSet.iterator();
		while(it.hasNext()){
			Contact output = it.next();
			assertEquals(expected[i], output.getName());
			i++;
		}
	}
	
	/**
	 * Third flush test.
	 */
	@Test
	public void thirdFlushTest(){
		ContactManager testCM1 = new ContactManagerImpl();
		testCM1.addNewContact("Mr Man", "He's the man");
		testCM1.addNewContact("Miss SomeOne", "She's not a man");
		testCM1.addNewContact("Mr Testy", "Testing");
		testCM1.addNewPastMeeting(testCM1.getContacts(1,2,3), new GregorianCalendar(2015, 2, 15), "some notes");
		testCM1.addFutureMeeting(testCM1.getContacts(1,2,3), new GregorianCalendar(2015, 8, 15));
		testCM1.flush();
		ContactManager testCM2 = new ContactManagerImpl();
		testCM2.addNewContact("Mr Man", "He's the man");
		testCM2.flush();
		ContactManager testCM3 = new ContactManagerImpl();
		PastMeeting testMeet = testCM3.getPastMeeting(1);
		String output = testMeet.getNotes();
		String expected = "some notes";
		assertEquals(expected, output);
	}

	/**
	 * Forth flush test.
	 */
	@Test
	public void forthFlushTest(){
		ContactManager testCM1 = new ContactManagerImpl();
		testCM1.addNewContact("Mr Man", "He's the man");
		testCM1.addNewContact("Miss SomeOne", "She's not a man");
		testCM1.addNewContact("Mr Testy", "Testing");
		testCM1.addNewPastMeeting(testCM1.getContacts(1,2,3), new GregorianCalendar(2015, 2, 15), "some notes");
		testCM1.addFutureMeeting(testCM1.getContacts(1,2,3), new GregorianCalendar(2015, 8, 15));
		Calendar currentTime = Calendar.getInstance();
		int year = currentTime.get(Calendar.YEAR);
		int month = currentTime.get(Calendar.MONTH);
	    int day = currentTime.get(Calendar.DAY_OF_MONTH);
		testCM1.addFutureMeeting(testCM1.getContacts(1,2,3), new GregorianCalendar(year, month, day));
		testCM1.addFutureMeeting(testCM1.getContacts(1,2,3), new GregorianCalendar(2015, 5, 15));
		testCM1.flush();
		ContactManager testCM2 = new ContactManagerImpl();
		testCM2.addNewContact("Mr Man", "He's the man");
		testCM2.addNewContact("A Man", "He's the man");
		testCM2.addNewContact("AN Man", "He's the man");
		testCM2.addMeetingNotes(3, "has it worked??");
		testCM2.flush();
		ContactManager testCM3 = new ContactManagerImpl();
		PastMeeting testMeet = testCM3.getPastMeeting(3);
		String output = testMeet.getNotes();
		String expected = "has it worked??";
		assertEquals(expected, output);
	}
}
