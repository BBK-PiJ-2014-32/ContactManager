package tests;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import contactManager.Contact;
import contactManager.ContactImpl;
import contactManager.FutureMeeting;
import contactManager.FutureMeetingImpl;


public class FutureMeetingTest {

	@Test
	public void futureMeetingTest() {
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		Contact contact2 = new ContactImpl("Miss Miss");
		Contact contact3 = new ContactImpl("Mr Smith");
		Contact contact4 = new ContactImpl("Mrs Ladyla");
		contactSet.add(contact1);
		contactSet.add(contact2);
		contactSet.add(contact3);
		contactSet.add(contact4);
		FutureMeeting testMeet = new FutureMeetingImpl(12,4,2015,contactSet);
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

}
