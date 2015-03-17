package contactManager;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;


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
	private XPath path;
		
	public ContactManagerImpl(){
		if(checkForFile()==true){
			ParserSetup();
			ArrayList<FileObjects> fileObjects = Parse("ContactManager.xml");	
			Iterator<FileObjects> it = fileObjects.iterator();
			while(it.hasNext()){
				FileObjects anObject = it.next();
				Contact aContact = (Contact) anObject.getObject();
				contactSet.add(aContact);
			}
		}
		
	}
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
		if(!checkForFile()){
			createFile();
		}
		fileObjectSet = addObjects();
		build(fileObjectSet);
		DOMImplementation impl = doc.getImplementation();
		DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
		LSSerializer ser = implLS.createLSSerializer();
		ser.getDomConfig().setParameter("format-pretty-print", true);
		LSOutput lsOutput = implLS.createLSOutput();
		lsOutput.setEncoding("UTF-8");
		Writer stringWriter = new StringWriter();
		lsOutput.setCharacterStream(stringWriter);
		ser.write(doc, lsOutput);
		 
		String out = stringWriter.toString();
		fileWriter(out);
		
	}
	
	private void createFile(){
		try{
			File dataStore = new File("ContactManager.xml");
			dataStore.createNewFile();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private boolean checkForFile(){
		File dataStore = new File("ContactManager.xml");
			if(dataStore.exists() == true){
				return true;
			} else {
				return false;		
			}
	}
	
	private Set<FileObjects> addObjects(){
		Set<FileObjects> returnSet = new LinkedHashSet<FileObjects>();
		Iterator<Contact> it1 = contactSet.iterator();
		while(it1.hasNext()){
			FileObjects newObj = new FileObjectsImpl(it1.next());
			returnSet.add(newObj);
		}
		Iterator<Meeting> it2 = meetingList.iterator();
		while(it2.hasNext()){
			FileObjects newObj = new FileObjectsImpl(it2.next());
			returnSet.add(newObj);
			System.out.println("added");
		}
		return returnSet;
	}
	private Document build(Set<FileObjects> objects){
		doc = builder.newDocument();
		doc.appendChild(createObjects(objects));
		return doc;
	  }  
	
	private Element createObjects(Set<FileObjects> objects){
		Element e = doc.createElement("ContactManager");
		for (FileObjects anObject : objects)
			e.appendChild(createObject(anObject));
		return e;
	  }
	
	 private Element createObject(FileObjects anObject){
		 System.out.println(anObject.getObject().getClass() == ContactImpl.class);
		 if(anObject.getObject().getClass() == ContactImpl.class){
			 Element e = doc.createElement("Items");
			 Contact aContact = (Contact) anObject.getObject();
			 e.appendChild(createContact(aContact));
			 return e;
		 } else {
			 Element e = doc.createElement("Items");
			 Meeting aMeeting = (Meeting) anObject.getObject();
			 e.appendChild(createMeeting(aMeeting));
			 return e;
		 }
	  } 
	 
	 private Element createContact(Contact c){
		 Element e = doc.createElement("Contact");
		 e.appendChild(createTextElement("ID", String.valueOf(c.getId())));
		 e.appendChild(createTextElement("Name", c.getName()));
		 e.appendChild(createTextElement("Notes", c.getNotes()));
		 return e;
	 }
	 
	 private Element createMeeting(Meeting m){
		 if(m.getClass().equals(PastMeetingImpl.class)){
			 PastMeeting newM = (PastMeeting) m;
			 Element e = doc.createElement("PastMeeting");
			 e.appendChild(createTextElement("ID", String.valueOf(newM.getId())));
			 e.appendChild(createTextElement("Date", String.valueOf(newM.getDate())));
			 e.appendChild(createTextElement("Notes", newM.getNotes()));
			 Iterator<Contact> it = newM.getContacts().iterator();
			 String ids = "";
			 while(it.hasNext()){
				 	 ids += String.valueOf(it.next().getId()) + ", " ;
			 }
			 System.out.println(ids);
			 e.appendChild(createTextElement("ContactIDs", ids));
			 return e;
		 } else {
			 FutureMeeting newM = (FutureMeeting) m;
			 System.out.println(newM.getDate().get(Calendar.YEAR));
			 Element e = doc.createElement("FutureMeeting");
			 e.appendChild(createTextElement("ID", String.valueOf(newM.getId())));
			 e.appendChild(createTextElement("Year", String.valueOf(newM.getDate().get(Calendar.YEAR))));
			 e.appendChild(createTextElement("Month", String.valueOf(newM.getDate().get(Calendar.MONTH))));
			 e.appendChild(createTextElement("Day", String.valueOf(newM.getDate().get(Calendar.DAY_OF_MONTH))));
			 Iterator<Contact> it = newM.getContacts().iterator();
			 String ids = "";
			 while(it.hasNext()){
				 	 ids += String.valueOf(it.next().getId()) + ", " ;
			 }
			 System.out.println(ids);
			 e.appendChild(createTextElement("ContactIDs", ids));
		 return e;
		 }
	 }
	 
	 private Element createTextElement(String name, String text){
	   Text t = doc.createTextNode(text);
	   Element e = doc.createElement(name);
	   e.appendChild(t);
	   return e;
	   }
	 
	 private void fileWriter(String out){
		try{
			FileWriter fw = new FileWriter("ContactManager.xml");
			fw.write(out);
			fw.close();
			System.out.println(out);
		} catch (IOException ex){
			ex.printStackTrace();
		}
	 }
	 
	private void ParserSetup(){
		 try{				 
			 DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance(); 
			 builder = dbfactory.newDocumentBuilder(); 
			 XPathFactory xpfactory = XPathFactory.newInstance(); 
			 path = xpfactory.newXPath();
		} catch (ParserConfigurationException ex){
			ex.printStackTrace(); 
		}
	}
	public ArrayList<FileObjects> Parse(String fileName){
		try{
			File f = new File(fileName);
			Document doc = builder.parse(f);
			ArrayList<FileObjects> items = new ArrayList<FileObjects>(); 
			int itemCount = Integer.parseInt(path.evaluate("count(/ContactManager/Items)", doc)); 
			for (int i = 1; i <= itemCount; i++) {
				 String idStr = path.evaluate("/ContactManager/Items[" + i + "]/Contact/ID", doc);
				 int id = Integer.parseInt(idStr);
				 String name = path.evaluate( "/ContactManager/Items[" + i + "]/Contact/Name", doc);
				 String notes = path.evaluate("/ContactManager/Items[" + i + "]/Contact/Notes", doc);
				 Contact c = new ContactImpl(name, id);
				 c.addNotes(notes);
				 FileObjects it = new FileObjectsImpl(c); 
				 items.add(it);
			 	}
			 return items;
			
		} catch (SAXException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} catch (XPathExpressionException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
