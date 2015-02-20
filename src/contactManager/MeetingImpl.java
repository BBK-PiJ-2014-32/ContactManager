package contactManager;

import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {

	private int Id = 0;
	private Calendar date;
	private static int lastId = 0;
	
	public MeetingImpl(Calendar date){
		if (this.getClass() == MeetingImpl.class){
			lastId++;
		}
		this.Id = lastId;
		this.date = date;
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
		return null;
	}

}
