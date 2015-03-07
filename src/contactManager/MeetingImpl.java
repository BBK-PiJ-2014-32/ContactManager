package contactManager;

import java.util.Calendar;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class MeetingImpl.
 */
public class MeetingImpl implements Meeting {

	/** The Id. */
	private int Id = 0;
	
	/** The date. */
	private Calendar date;
	
	/** The last id. */
	private static int lastId = 0;
	
	/** The contact set. */
	private Set<Contact> contactSet;
	
	/**
	 * Instantiates a new meeting impl.
	 *
	 * @param date the date
	 * @param contactSet the contact set
	 */
	public MeetingImpl(Calendar date, Set<Contact> contactSet){
		if(contactSet == null){
			throw new IllegalArgumentException("One or more contacts must be added");
		}
		if (this.getClass() == MeetingImpl.class || this.getClass() == FutureMeetingImpl.class || this.getClass() == PastMeetingImpl.class){
			lastId++;
		}
		this.Id = lastId;
		this.date = date; 
		this.contactSet = contactSet;
	}
	
	/* (non-Javadoc)
	 * @see contactManager.Meeting#getId()
	 */
	@Override
	public int getId() {
		return Id;
	}

	/* (non-Javadoc)
	 * @see contactManager.Meeting#getDate()
	 */
	@Override
	public Calendar getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see contactManager.Meeting#getContacts()
	 */
	@Override
	public Set<Contact> getContacts() {
		return contactSet;
	}

}
