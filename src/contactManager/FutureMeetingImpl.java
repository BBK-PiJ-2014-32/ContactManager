package contactManager;

import java.util.Set;
import java.util.Calendar;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

	public FutureMeetingImpl(Calendar date, Set<Contact> contactSet){
		super(date, contactSet);
	}
	
}
