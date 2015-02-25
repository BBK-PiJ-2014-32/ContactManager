package contactManager;

import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {

	private int Id = 0;
	private Calendar date;
	private static int lastId = 0;
	private Set<Contact> contactSet;
	
	public MeetingImpl(int day, int month, int year, Set<Contact> contactSet){
		if (this.getClass() == MeetingImpl.class || this.getClass() == FutureMeetingImpl.class){
			lastId++;
		}
		this.Id = lastId;
		this.date = Calendar.getInstance();
		this.date.set(Calendar.YEAR, year);
		this.date.set(Calendar.MONTH, month);
		this.date.set(Calendar.DAY_OF_MONTH, day);
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
