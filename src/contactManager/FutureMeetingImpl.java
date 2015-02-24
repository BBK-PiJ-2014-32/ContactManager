package contactManager;

import java.util.Set;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

	public FutureMeetingImpl(int day, int month, int year, Set<Contact> contactSet){
		super(day, month, year, contactSet);
	}
	
}
