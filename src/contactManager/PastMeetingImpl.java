package contactManager;

import java.util.Set;
import java.util.Calendar;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

	private String notes;
	
	public PastMeetingImpl(Calendar date, Set<Contact> contactSet, String notes){
		super(date, contactSet);
		this.notes = notes;
}

	@Override
	public String getNotes() {
		// TODO Auto-generated method stub
		return null;
	}
}
