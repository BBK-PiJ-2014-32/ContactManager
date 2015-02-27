package contactManager;

import java.util.Set;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class FutureMeetingImpl.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

	/**
	 * Instantiates a new future meeting impl.
	 *
	 * @param date the date
	 * @param contactSet the contact set
	 */
	public FutureMeetingImpl(Calendar date, Set<Contact> contactSet){
		super(date, contactSet);
	}
	
}
