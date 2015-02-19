package contactManager;

import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {

	private int Id = 0;
	private Calendar date;
	private static int lastId = 0;
	
	public MeetingImpl(){
		if (this.getClass() == MeetingImpl.class){
			lastId++;
		}
		this.Id = lastId;
	}
	
	@Override
	public int getId() {
		return Id;
	}

	@Override
	public Calendar getDate() {
		return null;
	}

	@Override
	public Set<Contact> getContacts() {
		return null;
	}

}
