package contactManager;

import Contact;
import Contacts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
// TODO: Auto-generated Javadoc
/**
 * The Class ContactManagerImpl.
 */
public class ContactManagerImpl implements ContactManager {
	
	/** The current time. */
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Calendar currentTime = Calendar.getInstance();
	private Set<Contact> contactSet = new LinkedHashSet<Contact>();
	private Set<FileObjects> fileObjectSet = new LinkedHashSet<FileObjects>();
	private List<Meeting> meetingList = new LinkedList<Meeting>();
	private int lastId = 1;
	private int meetingId = 1;
	private DocumentBuilder builder;
	private Document doc;
		
	
	/**
	 * Adds the future meeting.
	 *
	 * @param contacts the contacts
	 * @param date the date
	 * @return the int
	 */
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		if(currentTime.after(date) && !isItToday(date)){
			System.out.println(isItToday(date));
			throw new IllegalArgumentException("Date cannot be in the past");	
		}
		Meeting newFutureMeeting = new FutureMeetingImpl(date, contacts, meetingId);
		meetingList.add(newFutureMeeting);
		meetingId++;
		return newFutureMeeting.getId(); 
	}

	/**
	 * Gets the past meeting.
	 *
	 * @param id the id
	 * @return the past meeting
	 */
	@Override
	public PastMeeting getPastMeeting(int id) {
		if(currentTime.before(getMeeting(id).getDate())){ 
			throw (new IllegalArgumentException("Date cannot be in the future"));
		}
		PastMeeting returnPM = (PastMeeting) getMeeting(id);
		return returnPM;
	}
	
	private boolean isItToday(Calendar date){
		if(currentTime.get(Calendar.YEAR) == date.get(Calendar.YEAR) && 
			currentTime.get(Calendar.MONTH) == currentTime.get(Calendar.MONTH) &&
            currentTime.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Gets the future meeting.
	 *
	 * @param id the id
	 * @return the future meeting
	 */
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		FutureMeeting returnFM = (FutureMeeting) getMeeting(id);
		return returnFM;
	}

	/**
	 * Gets the meeting.
	 *
	 * @param id the id
	 * @return the meeting
	 */
	@Override
	public Meeting getMeeting(int id) {
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
				if(next.getId() == id){
					return next;
				}
			
		}
		return null;
	}

	/**
	 * Gets the future meeting list.
	 *
	 * @param contact the contact
	 * @return the future meeting list
	 */
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		if(!contactSet.contains(contact)){
			throw (new IllegalArgumentException("Contact does not exist"));
		}
		List<Meeting> returnList = new LinkedList<Meeting>();
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
			if(next.getClass() == FutureMeeting.class && next.getContacts().contains(contact)){
				returnList.add(next);
			} 
		}
		return returnList;
	}

	/**
	 * Gets the future meeting list.
	 *
	 * @param date the date
	 * @return the future meeting list
	 */
	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> returnList = new LinkedList<Meeting>();
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
			if(next.getClass() == FutureMeeting.class){
				returnList.add(next);
			}
		return returnList;	
		}
		return null;
	}

	/**
	 * Gets the past meeting list.
	 *
	 * @param contact the contact
	 * @return the past meeting list
	 */
	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		List<PastMeeting> returnList = new LinkedList<PastMeeting>();
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
			if(next.getClass() == PastMeeting.class && next.getContacts().contains(contact)){
				returnList.add((PastMeeting) next);
			}
		}
		return returnList;
	}

	/**
	 * Adds the new past meeting.
	 *
	 * @param contacts the contacts
	 * @param date the date
	 * @param text the text
	 */
	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		PastMeeting newMeet = new PastMeetingImpl(date, contacts, text, meetingId);
		meetingList.add(newMeet);
	}

	/**
	 * Adds the meeting notes.
	 *
	 * @param id the id
	 * @param text the text
	 */
	@Override
	public void addMeetingNotes(int id, String text) {
		updatePastMeeting(getMeeting(id).getContacts(), getMeeting(id).getId(), getMeeting(id).getDate(), text);
		
	}
	
	private void updatePastMeeting(Set<Contact> contacts, int id, Calendar date, String text){
		PastMeeting newMeet = new PastMeetingImpl(date, contacts, text, id);
		Iterator<Meeting> it = meetingList.iterator();
		while(it.hasNext()){
			Meeting next = it.next();
			if(next.getId() == id){
				meetingList.remove(next);
			}
		}
		meetingList.add(newMeet);
	}
	
	/*public Meeting futureToPastMeeting(Meeting meeting){
		Meeting newPM = new PastMeetingImpl(meeting.getDate(), meeting.getContacts(), meeting.getId());
		return newPM;
	}*/

	/**
	 * Adds the new contact.
	 *
	 * @param name the name
	 * @param notes the notes
	 */
	@Override
	public void addNewContact(String name, String notes) {
		if(name == null || notes == null){
			throw (new IllegalArgumentException("Name or notes cannot be null"));
		}
		Contact newContact = new ContactImpl(name, lastId);
		newContact.addNotes(notes);
		contactSet.add(newContact);
		lastId++;
	}

	/**
	 * Gets the contacts.
	 *
	 * @param ids the ids
	 * @return the contacts
	 */
	@Override
	public Set<Contact> getContacts(int... ids) {
		Set<Contact> returnSet = new LinkedHashSet<Contact>();
			for(int id: ids){
				returnSet.add(getContact(id));
			}
		return returnSet;
	}
	
	private Contact getContact(int id){
		Iterator<Contact> it = contactSet.iterator();
		while(it.hasNext()){
			Contact next = it.next();
				if(next.getId() == id){
					return next;
				}
		}return null;
	}

	/**
	 * Gets the contacts.
	 *
	 * @param name the name
	 * @return the contacts
	 */
	@Override
	public Set<Contact> getContacts(String name) {
		if(name == null){
			throw(new NullPointerException("Name cannot be null"));
		}
		Iterator<Contact> it = contactSet.iterator();
		while(it.hasNext()){
			Contact next = it.next();
			if(next.getName() == name){
				Set<Contact> returnSet = new LinkedHashSet<Contact>();;
				returnSet.add(next);
				return returnSet;
				} 
			}
		return null;
	}

	/**
	 * Flush.
	 */
	@Override
	public void flush() {
			 try{ 
				 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				 builder = factory.newDocumentBuilder();
			 } catch (ParserConfigurationException ex){
				 ex.printStackTrace();
			 }	  
		checkForFile();
		fileObjectSet = addObjects();
		
		
		
	}

	private boolean checkForFile(){
		try{
			File dataStore = new File("ContactManager.xml");
				if(dataStore.exists() == true){
					return true;
				} else {
					return dataStore.createNewFile();
				}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	private Set<FileObjects> addObjects(){
		Set<FileObjects> returnSet = new LinkedHashSet<FileObjects>();
		Iterator<Contact> it = contactSet.iterator();
		while(it.hasNext()){
			FileObjects newObj = new FileObjectsImpl(it.next());
			returnSet.add(newObj);
		}
		return returnSet;
	}
	private Document build(Set<FileObjects> objects){
		doc = builder.newDocument();
		doc.appendChild(createObjects(objects));
		return doc;
	  }  
	
	private Element createObjects(Set<FileObjects> objects){
		Element e = doc.createElement("Items");
		for (FileObjects anObject : objects)
			e.appendChild(createObject(anObject));
		return e;
	  }
	
	 private Element createObject(FileObjects anObject){
		 Element e = doc.createElement("Contact");
		 Contact aContact = (Contact) anObject.getObject();
		 e.appendChild(createContact(aContact));
		 return e;
	  } 
	 
	 private Element createContact(Contact c){
		 Element e = doc.createElement("Contact");
		 e.appendChild(createTextElement("ID", String.valueOf(c.getId())));
		 e.appendChild(createTextElement("Name", c.getName()));
		 e.appendChild(createTextElement("Notes", c.getNotes()));
		 return e;
	 }
}
