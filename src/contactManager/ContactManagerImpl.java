package contactManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactManagerImpl.
 */
public class ContactManagerImpl implements ContactManager {
	
	/** The current time. */
	private Calendar currentTime = Calendar.getInstance();
	private Set<Contact> contactSet = new LinkedHashSet<Contact>();
	private List<Meeting> meetingList = new LinkedList<Meeting>();
	private int lastId = 1;
	private int meetingId = 1;
		
	/**
	 * Adds the future meeting.
	 *
	 * @param contacts the contacts
	 * @param date the date
	 * @return the int
	 */
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		if(currentTime.after(date)){
			throw new IllegalArgumentException("Date cannot be in the past");	
		}
		Meeting newFutureMeeting = new FutureMeetingImpl(date, contacts, meetingId);
		meetingList.add(newFutureMeeting);
		meetingId++;
		return newFutureMeeting.getId(); 
	}

	/**
	 * Gets the past meeting.
	 *
	 * @param id the id
	 * @return the past meeting
	 */
	@Override
	public PastMeeting getPastMeeting(int id) {
		PastMeeting returnPM = (PastMeeting) getMeeting(id);
		return returnPM;
	}

	
	/**
	 * Gets the future meeting.
	 *
	 * @param id the id
	 * @return the future meeting
	 */
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		FutureMeeting returnFM = (FutureMeeting) getMeeting(id);
		return returnFM;
	}

	/**
	 * Gets the meeting.
	 *
	 * @param id the id
	 * @return the meeting
	 */
	@Override
	public Meeting getMeeting(int id) {
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
				if(next.getId() == id){
					return next;
				}
			
		}
		return null;
	}

	/**
	 * Gets the future meeting list.
	 *
	 * @param contact the contact
	 * @return the future meeting list
	 */
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		List<Meeting> returnList = new LinkedList<Meeting>();
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
			if(next.getClass() == FutureMeeting.class && next.getContacts().contains(contact)){
				returnList.add(next);
			}
		}
		return returnList;
	}

	/**
	 * Gets the future meeting list.
	 *
	 * @param date the date
	 * @return the future meeting list
	 */
	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> returnList = new LinkedList<Meeting>();
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
			if(next.getClass() == FutureMeeting.class){
				returnList.add(next);
			}
		return returnList;	
		}
		return null;
	}

	/**
	 * Gets the past meeting list.
	 *
	 * @param contact the contact
	 * @return the past meeting list
	 */
	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Adds the new past meeting.
	 *
	 * @param contacts the contacts
	 * @param date the date
	 * @param text the text
	 */
	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		PastMeeting newMeet = new PastMeetingImpl(date, contacts, text, meetingId);
		meetingList.add(newMeet);
	}

	/**
	 * Adds the meeting notes.
	 *
	 * @param id the id
	 * @param text the text
	 */
	@Override
	public void addMeetingNotes(int id, String text) {
		updatePastMeeting(getMeeting(id).getContacts(), getMeeting(id).getId(), getMeeting(id).getDate(), text);
		
	}
	
	private void updatePastMeeting(Set<Contact> contacts, int id, Calendar date, String text){
		PastMeeting newMeet = new PastMeetingImpl(date, contacts, text, id);
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
			if(next.getId() == id){
				meetingList.remove(next);
			}
		}
		meetingList.add(newMeet);
	}
	
	/*public Meeting futureToPastMeeting(Meeting meeting){
		Meeting newPM = new PastMeetingImpl(meeting.getDate(), meeting.getContacts(), meeting.getId());
		return newPM;
	}*/

	/**
	 * Adds the new contact.
	 *
	 * @param name the name
	 * @param notes the notes
	 */
	@Override
	public void addNewContact(String name, String notes) {
		if(name == null || notes == null){
			throw (new IllegalArgumentException("test"));
		}
		Contact newContact = new ContactImpl(name, lastId);
		newContact.addNotes(notes);
		contactSet.add(newContact);
		lastId++;
	}

	/**
	 * Gets the contacts.
	 *
	 * @param ids the ids
	 * @return the contacts
	 */
	@Override
	public Set<Contact> getContacts(int... ids) {
		Set<Contact> returnSet = new LinkedHashSet<Contact>();
			for(int id: ids){
				returnSet.add(getContact(id));
			}
		return returnSet;
	}
	
	private Contact getContact(int id){
		Iterator<Contact> it = contactSet.iterator();
		while(it.hasNext()){
			Contact next = it.next();
				if(next.getId() == id){
					return next;
				}
		}return null;
	}

	/**
	 * Gets the contacts.
	 *
	 * @param name the name
	 * @return the contacts
	 */
	@Override
	public Set<Contact> getContacts(String name) {
		Iterator<Contact> it = contactSet.iterator();
		while(it.hasNext()){
			Contact next = it.next();
			if(next.getName() == name){
				Set<Contact> returnSet = new LinkedHashSet<Contact>();;
				returnSet.add(next);
				return returnSet;
				} 
			}
		return null;
	}

	/**
	 * Flush.
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

}
