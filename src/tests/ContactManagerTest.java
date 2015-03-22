package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import contactManager.*;

/**
 * The Class ContactManagerTest.
 */
public class ContactManagerTest {	
	
	/**
	 * Deletes previous file if present from previous test.
	 */
	@Before
	public void deleteFile(){
		File file = new File("ContactManager.xml");
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * Adds the future meeting test.
	 */
	@Test
	public void add1FutureMeetingTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact("Mr Man", "none");
		testCM.addNewContact("Miss Miss", "none");
		testCM.addNewContact("Mr Smith", "none");
		testCM.addNewContact("Mrs Ladyla", "none");
		Set<Contact> contactSet = testCM.getContacts(1, 2, 3, 4);
		Calendar date = sdf.getCalendar();
		date.set(Calendar.YEAR, 2015);
		date.set(Calendar.MONTH, 4);
		date.set(Calendar.DAY_OF_MONTH, 12);
		int output = testCM.addFutureMeeting(contactSet, date);
		int expected = 1;
		assertEquals(expected, output);
	}
	
	/**
	 * Date in the past exception test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void dateInThePastTest(){
		Calendar date = new GregorianCalendar(2014, 4, 12);
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man", 1);
		contactSet.add(contact1);
		ContactManager testCM = new ContactManagerImpl();
		testCM.addFutureMeeting(contactSet, date);
	}
	
	/**
	 * Add contact test.
	 */
	@Test
	public void addContactTest(){
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact("Mr Man", "He's the man");
		testCM.addNewContact("Miss SomeOne", "She's not a man");
		testCM.addNewContact("Mr Testy", "Testing");
		Set<Contact> outputSet = testCM.getContacts("Mr Testy");
		Iterator<Contact> it = outputSet.iterator();
		int output = 0;
		while(it.hasNext()){
		  Contact loopCon = it.next();
		  output = loopCon.getId();
		}
		int expected = 3;
		assertEquals(expected, output);
	}
	
	/**
	 * Add null contact name exception test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void addNullContactNameTest(){
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact(null, "notes");
	}
	
	/**
	 * Add null contact notes exception test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void addNullContactNotesTest(){
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact("Anon", null);
	}
	
	/**
	 * Add new past meeting test.
	 */
	@Test
	public void addNewPastMeetingTest(){ 
		ContactManager testCM = new ContactManagerImpl();
		testCM.addNewContact("Mr Man", "none");
		testCM.addNewContact("Miss Miss", "none");
		testCM.addNewContact("Mr Smith", "none");
		testCM.addNewContact("Mrs Ladyla", "none");
		Set<Contact> contactSet = testCM.getContacts(1, 2, 3, 4);
		Calendar date = new GregorianCalendar(2015, 1, 1);
		testCM.addNewPastMeeting(contactSet, date, "Notes from the meeting!!");
		PastMeeting outMeet = testCM.getPastMeeting(1);
		String output = outMeet.getNotes();
		String expected = "Notes from the meeting!!";
		assertEquals(expected, output);
		
	}
	
	/**
	 * Get existing meeting test.
	 *
	 */
	@Test
	public void getMeetingTest(){
		ContactManager testCM = new ContactManagerImpl();
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man", 1);
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
	
	/**
	 * Get future meeting list by date test.
	 */
	@Test
	public void getFutureMeetingListTest(){
		ContactManager testCM = new ContactManagerImpl();
		Set<Contact> contactSet = new LinkedHashSet<Contact>();
		Contact contact1 = new ContactImpl("Mr Man", 1);
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
		
		/**
		 * Add meeting notes test.
		 */
		@Test
		public void addMeetingNotesTest(){ //Also uses getPastMeeting for the first time.
			ContactManager testCM = new ContactManagerImpl();
			Set<Contact> contactSet = new LinkedHashSet<Contact>();
			Contact contact1 = new ContactImpl("Mr Man", 1);
			contactSet.add(contact1);
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 3, 8));
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2019, 5, 01));
			Calendar currentTime = Calendar.getInstance();
			int year = currentTime.get(Calendar.YEAR);
			int month = currentTime.get(Calendar.MONTH);
		    int day = currentTime.get(Calendar.DAY_OF_MONTH);
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(year, month, day));
			testCM.addMeetingNotes(3, "Some text about the meeting");
			String output = testCM.getPastMeeting(3).getNotes();
			String expected = "Some text about the meeting";
			assertEquals(expected, output);
		}

		/**
		 * Get future meeting by id test.
		 */
		@Test
		public void getFutureMeetingTest(){
			ContactManager testCM = new ContactManagerImpl();
			Set<Contact> contactSet = new LinkedHashSet<Contact>();
			Contact contact1 = new ContactImpl("Mr Man", 1);
			contactSet.add(contact1);
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 3, 8));
			Calendar output = testCM.getFutureMeeting(1).getDate();
			Calendar expected = new GregorianCalendar(2015,3,8);
			assertEquals(expected, output);
		}
		
		/**
		 * Get future meeting list by contact test.
		 */
		@Test
		public void getFutureMeetingListByContactTest(){
			ContactManager testCM = new ContactManagerImpl();
			List<Meeting> outList = new LinkedList<Meeting>();
			testCM.addNewContact("Mr Man", "");
			testCM.addNewContact("Some Man", "");
			testCM.addNewContact("A Woman", "");
			testCM.addNewContact("Anon", "");
			testCM.addNewContact("Andrew", "");
			Set<Contact> contactSet = testCM.getContacts(1,2,3,4);
			Set<Contact> contactSet2 = testCM.getContacts(5);		
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 3, 15));
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 4, 12));
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 07, 30));
			testCM.addFutureMeeting(contactSet2, new GregorianCalendar(2016, 1, 1));
			testCM.addFutureMeeting(contactSet, new GregorianCalendar(2015, 06, 30));
			Set<Contact> conSet = testCM.getContacts("Anon");
			Iterator<Contact> it1 = conSet.iterator();
			if(conSet.size()==1){
				Contact contact = it1.next();
				outList = testCM.getFutureMeetingList(contact);
			}
			int [] expectId = {1, 2, 3, 5};
			Iterator<Meeting> it2 = outList.iterator();
			int i = 0;
			while(it2.hasNext()){
				int outId = it2.next().getId();
				assertEquals(expectId[i], outId);
				i++;
				}
			
		}
		
		/**
		 * Get past meeting list by contact test.
		 */
		@Test
		public void getPastMeetingListByContactTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "");
			testCM.addNewContact("Some Man", "");
			testCM.addNewContact("A Woman", "");
			testCM.addNewContact("Anon", "");
			testCM.addNewContact("Andrew", "");
			Set<Contact> contactSet = testCM.getContacts(1,2,3,4);
			Set<Contact> contactSet2 = testCM.getContacts(5);
			testCM.addNewPastMeeting(contactSet, new GregorianCalendar(2015, 3, 15), "notes");
			testCM.addNewPastMeeting(contactSet, new GregorianCalendar(2015, 4, 12), "the meeting was great");
			testCM.addNewPastMeeting(contactSet, new GregorianCalendar(2015, 07, 30), "N/A");
			testCM.addNewPastMeeting(contactSet2, new GregorianCalendar(2016, 1, 1), "No Actions");
			testCM.addNewPastMeeting(contactSet, new GregorianCalendar(2015, 06, 30), "something");
			Contact contactIn = null;
			Set<Contact> conSet = testCM.getContacts("Anon");
			Iterator<Contact> itc = conSet.iterator();
			while(itc.hasNext()){
				Contact c = itc.next();
				if(c.getName().equals("Anon"));
					contactIn = c;
			}
			List<PastMeeting> outList = testCM.getPastMeetingList(contactIn);
			int [] expectId = {1, 2, 3, 5};
			Iterator<PastMeeting> it = outList.iterator();
			int i = 0;
			while(it.hasNext()){
				int outId = it.next().getId();
				assertEquals(expectId[i], outId);
				i++;
			}
		}
		
		/**
		 * Null get contact exception test.
		 */
		@Test(expected = NullPointerException.class)
		public void getContactsExceptionTest(){
			ContactManager testCM = new ContactManagerImpl();
			String str = null;
			testCM.getContacts(str);
		}
		
		/**
		 * Get future meeting by contact that does not exist test.
		 */
		@Test(expected = IllegalArgumentException.class)
		public void getFutureMeetingContactDoesNotExistTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			testCM.addNewContact("Miss SomeOne", "She's not a man");
			testCM.addNewContact("Mr Testy", "Testing");
			Contact contact = new ContactImpl("Some Contact", 4);
			testCM.getFutureMeetingList(contact);
		}
		
		/**
		 * Get past meeting in the future test exception test.
		 */
		@Test(expected = IllegalArgumentException.class)
		public void getPastMeetingInTheFutureTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			testCM.addNewContact("Miss SomeOne", "She's not a man");
			testCM.addNewContact("Mr Testy", "Testing");
			testCM.addFutureMeeting(testCM.getContacts(1,2,3), new GregorianCalendar(2015, 8, 15));
			testCM.getPastMeeting(1);
		}
		
		/**
		 * Get future meeting that is from the past exception test.
		 */
		@Test(expected = IllegalArgumentException.class)
		public void getPastMeetingFromTheFutureExceptionTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			testCM.addNewContact("Miss SomeOne", "She's not a man");
			testCM.addNewContact("Mr Testy", "Testing");
			testCM.addNewPastMeeting(testCM.getContacts(1,2,3), new GregorianCalendar(2015, 2, 1), "test");
			testCM.getFutureMeeting(1);
		}
		
		/**
		 * Get a meeting that doesn't exist null test.
		 */
		@Test
		public void getMeetingNullTest(){
			ContactManager testCM = new ContactManagerImpl();
			Meeting testMeet = testCM.getMeeting(1);
			Meeting expected = null;
			assertEquals(expected, testMeet);
		}
		
		/**
		 * Gets the past meeting by a contact that does not exist test.
		 */
		@Test(expected = IllegalArgumentException.class)
		public void getPastMeetingContactDoesNotExistTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			testCM.addNewContact("Miss SomeOne", "She's not a man");
			testCM.addNewContact("Mr Testy", "Testing");
			Contact contact = new ContactImpl("Some Contact", 4);
			testCM.getPastMeetingList(contact);
		}
		
		/**
		 * Add a new past meeting with null contact set exception test.
		 */
		@Test(expected = NullPointerException.class)
		public void addNewPastMeetingNullTest1(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewPastMeeting(null, new GregorianCalendar(2015, 2, 1), "text");
		}
		
		/**
		 * Add a new past meeting with a null date exception test.
		 */
		@Test(expected = NullPointerException.class)
		public void addNewPastMeetingNullTest2(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			Set<Contact> conSet = testCM.getContacts("Mr Man");
			testCM.addNewPastMeeting(conSet, null, "text");
			
		}
		
		/**
		 * Add a new past meeting with null notes exception test.
		 */
		@Test(expected = NullPointerException.class)
		public void addNewPastMeetingNullTest3(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			Set<Contact> conSet = testCM.getContacts("Mr Man");
			testCM.addNewPastMeeting(conSet, new GregorianCalendar(2015, 2, 1), null);
		}
		
		/**
		 * Add meeting notes to a meeting that doesn't exist exception test.
		 */
		@Test(expected = IllegalArgumentException.class)
		public void addMeetingNotesIllegalArgumentExceptionTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addMeetingNotes(1, "text");
		}
		
		/**
		 * Add the meeting notes to a future meeting illegal state exception test.
		 */
		@Test(expected = IllegalStateException.class)
		public void addMeetingNotesIllegalStateExceptionTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			testCM.addFutureMeeting(testCM.getContacts(1), new GregorianCalendar(2015, 8, 15));
			testCM.addMeetingNotes(1, "text");
		}
		
		/**
		 * Add meeting notes pointer exception test.
		 */
		@Test(expected = NullPointerException.class)
		public void addMeetingNotesNullPointerExceptionTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			Calendar currentTime = Calendar.getInstance();
			int year = currentTime.get(Calendar.YEAR);
			int month = currentTime.get(Calendar.MONTH);
		    int day = currentTime.get(Calendar.DAY_OF_MONTH);
			testCM.addFutureMeeting(testCM.getContacts(1), new GregorianCalendar(year, month, day));
			testCM.addMeetingNotes(1, null);
		}
		
		/**
		 * Get contact set by contact that doesn't exist illegal argument exception test.
		 */
		@Test(expected = IllegalArgumentException.class)
		public void getContactsSetIllegalArgumentExceptionTest(){
			ContactManager testCM = new ContactManagerImpl();
			testCM.addNewContact("Mr Man", "He's the man");
			testCM.addNewContact("Miss SomeOne", "She's not a man");
			testCM.addNewContact("Mr Testy", "Testing");
			testCM.getContacts(1,2,3,4);
		}
		
}		

