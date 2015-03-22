package contactManager;

import java.util.Set;
import java.util.Calendar;


/**
 * The Class PastMeetingImpl which extends the MeetingImpl class and implements the PastMeeting interface.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

	/** The notes to be added to the meeting. */
	private String notes;
	
	
	/**
	 * Instantiates a new past meeting.
	 *
	 * @param date the date
	 * @param contactSet the contact set
	 * @param notes the notes
	 * @param id the id
	 */
	public PastMeetingImpl(Calendar date, Set<Contact> contactSet, String notes, int id){
		super(date, contactSet, id);
		this.notes = notes;
}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNotes() {
		return notes;
	}
	
	/**
	 * Sets the notes if needed.
	 *
	 * @param text the new notes
	 */
	private void setNotes(String text){
		notes = text;
	}
}
