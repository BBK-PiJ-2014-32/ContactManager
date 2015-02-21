package contactManager;

import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {

	private int Id = 0;
	private Calendar date;
	private static int lastId = 0;
	
	public MeetingImpl(int day, int month, int year){
		if (this.getClass() == MeetingImpl.class){
			lastId++;
		}
		this.Id = lastId;
		this.date = Calendar.getInstance();
		this.date.set(Calendar.YEAR, year);
		this.date.set(Calendar.MONTH, month);
		this.date.set(Calendar.DAY_OF_MONTH, day);
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
