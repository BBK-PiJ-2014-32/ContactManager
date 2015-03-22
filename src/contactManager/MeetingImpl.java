package contactManager;

import java.util.Calendar;
import java.util.Set;

/**
 * The Class MeetingImpl which implements the Meeting interface.
 * @see contactManager.Meeting
 */
public class MeetingImpl implements Meeting {

	/** The id of the meeting. */
	private int id = 0;
	
	/** The date of the meeting. */
	private Calendar date;
	
	/** The contact set to be added to the meeting. */
	private Set<Contact> contactSet;
	
	
	/**
	 * Instantiates a new meeting.
	 *
	 * @param date the date of the meeting
	 * @param contactSet the set of contacts to be added to the meeting
	 * @param id the id of the meeting
	 */
	public MeetingImpl(Calendar date, Set<Contact> contactSet, int id){
		if(contactSet == null){
			throw new IllegalArgumentException("One or more contacts must be added");
		}
		this.id = id;
		this.date = date; 
		this.contactSet = contactSet;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Calendar getDate() {
		return date;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Contact> getContacts() {
		return contactSet;
	}

}
