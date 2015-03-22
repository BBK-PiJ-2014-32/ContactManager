package contactManager;

import java.util.Set;
import java.util.Calendar;

/**
 * The Class FutureMeetingImpl extends the MeetingImpl class and implements the FutureMeeting interface.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

	/**
	 * Instantiates a new future meeting.
	 *
	 * @param date the date of the meeting
	 * @param contactSet the set of contacts to be added to the meeting
	 * @param id the id of the meeting
	 */
	public FutureMeetingImpl(Calendar date, Set<Contact> contactSet, int id){
		super(date, contactSet, id);
	}
	
}
