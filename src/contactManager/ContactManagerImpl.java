package contactManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
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
		FutureMeeting newFutureMeeting = new FutureMeetingImpl(date, contacts);
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the future meeting.
	 *
	 * @param id the id
	 * @return the future meeting
	 */
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the meeting.
	 *
	 * @param id the id
	 * @return the meeting
	 */
	@Override
	public Meeting getMeeting(int id) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the future meeting list.
	 *
	 * @param date the date
	 * @return the future meeting list
	 */
	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		// TODO Auto-generated method stub
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
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,
			String text) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Adds the meeting notes.
	 *
	 * @param id the id
	 * @param text the text
	 */
	@Override
	public void addMeetingNotes(int id, String text) {
		// TODO Auto-generated method stub
		
	}

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
		Contact newContact = new ContactImpl(name);
		newContact.addNotes(notes);
		contactSet.add(newContact);
	}

	/**
	 * Gets the contacts.
	 *
	 * @param ids the ids
	 * @return the contacts
	 */
	@Override
	public Set<Contact> getContacts(int... ids) {
		// TODO Auto-generated method stub
		return null;
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
			} else {
				return null;
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
