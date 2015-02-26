package contactManager;

import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {

	private int Id = 0;
	private Calendar date;
	private static int lastId = 0;
	private Set<Contact> contactSet;
	
	public MeetingImpl(Calendar date, Set<Contact> contactSet){
		if (this.getClass() == MeetingImpl.class || this.getClass() == FutureMeetingImpl.class){
			lastId++;
		}
		this.Id = lastId;
		this.date = date; 
		this.contactSet = contactSet;
	}
	
	@Override
	public int getId() {
		return Id;
	}

	@Override
	public Calendar getDate() {
		return date;
	}

	@Override
	public Set<Contact> getContacts() {
		return contactSet;
	}

}
