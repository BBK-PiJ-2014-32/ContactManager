package tests;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;

import contactManager.*;

import org.junit.Test;

public class XMLHandlerTest {

	@Test
	public void checkForFiletest() {
		XMLHandler testWTX = new XMLHandlerImpl();
		boolean output = testWTX.checkForFile();
		boolean expected = true;
		assertEquals(expected, output);
	}
	
	@Test
	public void contactWriteAndLastIdGetTest(){
		XMLHandler testWTX = new XMLHandlerImpl();
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man");
		Contact contact2 = new ContactImpl("Miss Miss");
		Contact contact3 = new ContactImpl("Mr Smith");
		Contact contact4 = new ContactImpl("Mrs Ladyla");
		contactSet.add(contact1);
		contactSet.add(contact2);
		contactSet.add(contact3);
		contactSet.add(contact4);
		testWTX.contactWrite(contactSet);
		int output = testWTX.getLastContactId();
		int expected = 4;
		assertEquals(expected, output);
	}
}
