package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import contactManager.Contact;
import contactManager.ContactManager;
import contactManager.ContactManagerImpl;
import contactManager.Meeting;

public class ContactManagerFlushTests {

	@Test
	public void a1firstFlushTest(){
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
	
	@Test
	public void a2secondFlushTest(){
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
}
