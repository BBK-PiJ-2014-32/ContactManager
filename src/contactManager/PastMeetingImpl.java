package contactManager;

import java.util.Set;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class PastMeetingImpl.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

	/** The notes. */
	private String notes;
	
	/**
	 * Instantiates a new past meeting impl.
	 *
	 * @param date the date
	 * @param contactSet the contact set
	 * @param notes the notes
	 */
	public PastMeetingImpl(Calendar date, Set<Contact> contactSet, String notes){
		super(date, contactSet);
		this.notes = notes;
}

	/* (non-Javadoc)
	 * @see contactManager.PastMeeting#getNotes()
	 */
	@Override
	public String getNotes() {
		return notes;
	}
}
