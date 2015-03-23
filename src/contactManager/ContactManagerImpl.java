package contactManager;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

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

/**
 * The Class ContactManagerImpl that implements the Contact Manager interface.
 * 
 * @see contactManager.ContactManager
 */
public class ContactManagerImpl implements ContactManager {
	
	/** The current time. */
	private Calendar currentTime = Calendar.getInstance();
	
	/** The contact set. */
	private Set<Contact> contactSet = new LinkedHashSet<Contact>();
	
	/** The file object set. */
	private Set<FileObjects> fileObjectSet = new LinkedHashSet<FileObjects>();
	
	/** The meeting list. */
	private List<Meeting> meetingList = new LinkedList<Meeting>();
	
	/** The meeting iterator. */
	private Iterator<Meeting> itm;
	
	/** The contact iterator. */
	private Iterator<Contact> itc;
	
	/** The last contact id. */
	private int lastId = 1;
	
	/** The meeting id. */
	private int meetingId = 1;
	
	/** The builder. */
	private DocumentBuilder builder;
	
	/** The doc. */
	private Document doc;
	
	/** The path. */
	private XPath path;
	
	/** The Future Meeting starting point when parsing. */
	private int fmMeetingStart = 0;
	
	/** The Past Meeting starting point when parsing. */
	private int pmMeetingStart = 0;
		
	/**
	 * Instantiates a new contact manager.
	 */
	public ContactManagerImpl(){
		if(checkForFile()){
			contactSet = parseContacts("ContactManager.xml");	
			if(doesItContainMeetings("ContactManager.xml")){
				meetingList = parseMeetings("ContactManager.xml");	
			}
		}
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		if(currentTime.after(date) && !isItToday(date)){
			throw new IllegalArgumentException("Date cannot be in the past");	
		}
		Meeting newFutureMeeting = new FutureMeetingImpl(date, contacts, meetingId);
		meetingList.add(newFutureMeeting);
		meetingId++;
		return newFutureMeeting.getId(); 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PastMeeting getPastMeeting(int id) {
		if(currentTime.before(getMeeting(id).getDate())){ 
			throw (new IllegalArgumentException("Date cannot be in the future"));
		}
		PastMeeting returnPM = (PastMeeting) getMeeting(id);
		return returnPM;
	}
	
	/**
	 * Private helper method to check if the date entered is today's date.
	 *
	 * @param date is the date to be checked.
	 * @return true, if is it today.
	 */
	private boolean isItToday(Calendar date){
		if(currentTime.get(Calendar.YEAR) == date.get(Calendar.YEAR) && 
			currentTime.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
            currentTime.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FutureMeeting getFutureMeeting(int id) {
		if(getMeeting(id).getClass().equals(PastMeetingImpl.class)){
			throw (new IllegalArgumentException("The meeting " + id + " is in the past"));
		}
		FutureMeeting returnFM = (FutureMeeting) getMeeting(id);
		return returnFM;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Meeting getMeeting(int id) {
		itm = meetingList.iterator();
		while(itm.hasNext()){
			Meeting next = itm.next();
				if(next.getId() == id){
					return next;
				}
			
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		if(!contactSet.contains(contact)){
			throw (new IllegalArgumentException("Contact does not exist"));
		}
		List<Meeting> returnList = new LinkedList<Meeting>();
		itm = meetingList.iterator();
		while(itm.hasNext()){
			Meeting next = itm.next();
			if(next.getClass() == FutureMeeting.class && next.getContacts().contains(contact)){
				returnList.add(next);
			} 
		}
		return returnList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> returnList = new LinkedList<Meeting>();
		itm = meetingList.iterator();
		while(itm.hasNext()){
			Meeting next = itm.next();
			if(next.getClass() == FutureMeeting.class){
				returnList.add(next);
			}
		return returnList;	
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		if(!contactSet.contains(contact)){
			throw (new IllegalArgumentException("Contact does not exist"));
		}
		List<PastMeeting> returnList = new LinkedList<PastMeeting>();
		itm = meetingList.iterator();
		while(itm.hasNext()){
			Meeting next = itm.next();
			if(next.getClass() == PastMeeting.class && next.getContacts().contains(contact)){
				returnList.add((PastMeeting) next);
			}
		}
		return returnList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
		if(contacts.equals(null) || date.equals(null) || text.equals(null)){
			throw (new NullPointerException("ContactSet, date or text cannot be null"));
		}
		PastMeeting newMeet = new PastMeetingImpl(date, contacts, text, meetingId);
		meetingList.add(newMeet);
		meetingId++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addMeetingNotes(int id, String text) {
		if(getMeeting(id) == null){
			throw (new IllegalArgumentException("Meeting " + id + " does not exist"));
		}
		if(currentTime.before(getMeeting(id).getDate()) && !isItToday(getMeeting(id).getDate()) || currentTime.before(getMeeting(id).getDate())){ 
			throw (new IllegalStateException("Notes cannont be added to a future meeting"));
		}
		if(text.equals(null)){
			throw (new NullPointerException("Notes cannot be null"));
		}
		updatePastMeeting(getMeeting(id).getContacts(), getMeeting(id).getId(), getMeeting(id).getDate(), text);
		
	}
	
	/**
	 * Private helper method to update a Future Meeting to a Past Meeting so notes can added.
	 *
	 * @param contacts are the contact set from the original meeting.
	 * @param id the id of the meeting.
	 * @param date the date of the meeting.
	 * @param text the notes to be added for the meeting.
	 */
	private void updatePastMeeting(Set<Contact> contacts, int id, Calendar date, String text){
		PastMeeting newMeet = new PastMeetingImpl(date, contacts, text, id);
		itm = meetingList.iterator();
		while(itm.hasNext()){
			Meeting next = itm.next();
			if(next.getId() == id){
				itm.remove();
			}
		}
		meetingList.add(newMeet);
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public Set<Contact> getContacts(int... ids) {
		for(int id: ids){
			if(getContact(id) == null){
				throw (new IllegalArgumentException("Contact " + id + " does not exist"));
			}
		}
		Set<Contact> returnSet = new LinkedHashSet<Contact>();
			for(int id: ids){
				returnSet.add(getContact(id));
			}
		return returnSet;
	}
	
	/**
	 * Private helper method to get and return single contact from stored contact set.
	 *
	 * @param id the id of the contact.
	 * @return the contact.
	 */
	private Contact getContact(int id){
		itc = contactSet.iterator();
		while(itc.hasNext()){
			Contact next = itc.next();
				if(next.getId() == id){
					return next;
				}
		}return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Contact> getContacts(String name) {
		if(name == null){
			throw(new NullPointerException("Name cannot be null"));
		}
		itc = contactSet.iterator();
		while(itc.hasNext()){
			Contact next = itc.next();
			if(next.getName() == name){
				Set<Contact> returnSet = new LinkedHashSet<Contact>();;
				returnSet.add(next);
				return returnSet;
				} 
			}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() {
			 try{ 
				 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				 builder = factory.newDocumentBuilder();
			 	  
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
			 } catch (ParserConfigurationException ex){
				 ex.printStackTrace();
			 }
	}
	
	/**
	 * Creates new instance of the ContactManager xml file.
	 */
	private void createFile(){
		try{
			File dataStore = new File("ContactManager.xml");
			dataStore.createNewFile();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Checks if an instance of the ContactManager xml file already exists.
	 *
	 * @return true, if successful
	 */
	private boolean checkForFile(){
		File dataStore = new File("ContactManager.xml");
			if(dataStore.exists()){
				return true;
			} else {
				return false;		
			}
	}
	
	/**
	 * Adds the contents of the contact set and meeting list to a File Objects list the
	 * in preparation for being written to file.
	 * @return the set of File Objects to be written.
	 */
	private Set<FileObjects> addObjects(){
		Set<FileObjects> returnSet = new LinkedHashSet<FileObjects>();
		if(!contactSet.isEmpty()){
			itc = contactSet.iterator();
			while(itc.hasNext()){
				FileObjects newObj = new FileObjectsImpl(itc.next());
				returnSet.add(newObj);
			}
		}
		if(meetingList != null){
			itm = meetingList.iterator();
			while(itm.hasNext()){
				Meeting m = itm.next();
				if(m.getClass().equals(FutureMeetingImpl.class)){
					FileObjects newObj = new FileObjectsImpl(m);
					returnSet.add(newObj);
				}
			}
			itm = meetingList.iterator();
			while(itm.hasNext()){
				Meeting m = itm.next();
					if(m.getClass().equals(PastMeetingImpl.class)){
						FileObjects newObj = new FileObjectsImpl(m);
						returnSet.add(newObj);
					}
				}
			}
		return returnSet;
	}
	
	/**
	 * Builds the DOM document for the list of File Objects.
	 *
	 * @param objects the set File Objects to be written.
	 * @return the DOM document
	 */
	private Document build(Set<FileObjects> objects){
		doc = builder.newDocument();
		doc.appendChild(createObjects(objects));
		return doc;
	  }  
	
	/**
	 * Creates the objects to be written.
	 *
	 * @param objects the set File Objects to be written.
	 * @return the element
	 */
	private Element createObjects(Set<FileObjects> objects){
		Element e = doc.createElement("ContactManager");
		for (FileObjects anObject : objects)
			e.appendChild(createObject(anObject));
		return e;
	  }
	
	 /**
 	 * Creates the DOM elements for the items.
 	 *
 	 * @param anObject the contact or meeting held in the File Objects Set.
 	 * @return the element
 	 */
 	private Element createObject(FileObjects anObject){
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
	 
	 /**
 	 * Creates the contact DOM element to be written.
 	 *
 	 * @param c the Contact
 	 * @return the element
 	 */
 	private Element createContact(Contact c){
		 Element e = doc.createElement("Contact");
		 e.appendChild(createTextElement("ID", String.valueOf(c.getId())));
		 e.appendChild(createTextElement("Name", c.getName()));
		 e.appendChild(createTextElement("Notes", c.getNotes()));
		 return e;
	 }
	 
	 /**
 	 * Creates the meeting DOM element to be written.
 	 *
 	 * @param m the Meeting
 	 * @return the element
 	 */
 	private Element createMeeting(Meeting m){
		 if(m.getClass().equals(PastMeetingImpl.class)){
			 PastMeeting newM = (PastMeeting) m;
			 Element e = doc.createElement("PastMeeting");
			 e.appendChild(createTextElement("ID", String.valueOf(newM.getId())));
			 e.appendChild(createTextElement("Year", String.valueOf(newM.getDate().get(Calendar.YEAR))));
			 e.appendChild(createTextElement("Month", String.valueOf(newM.getDate().get(Calendar.MONTH))));
			 e.appendChild(createTextElement("Day", String.valueOf(newM.getDate().get(Calendar.DAY_OF_MONTH))));
			 e.appendChild(createTextElement("Notes", newM.getNotes()));
			 itc = newM.getContacts().iterator();
			 String ids = "";
			 while(itc.hasNext()){
				 	 ids += String.valueOf(itc.next().getId()) + ", " ;
			 }
			 e.appendChild(createTextElement("ContactIDs", ids));
			 return e;
		 } else {
			 FutureMeeting newM = (FutureMeeting) m;
			 Element e = doc.createElement("FutureMeeting");
			 e.appendChild(createTextElement("ID", String.valueOf(newM.getId())));
			 e.appendChild(createTextElement("Year", String.valueOf(newM.getDate().get(Calendar.YEAR))));
			 e.appendChild(createTextElement("Month", String.valueOf(newM.getDate().get(Calendar.MONTH))));
			 e.appendChild(createTextElement("Day", String.valueOf(newM.getDate().get(Calendar.DAY_OF_MONTH))));
			 itc = newM.getContacts().iterator();
			 String ids = "";
			 while(itc.hasNext()){
				 	 ids += String.valueOf(itc.next().getId()) + ", " ;
			 }
			 e.appendChild(createTextElement("ContactIDs", ids));
		 return e;
		 }
	 }
	 
	 /**
 	 * Creates the text element to be written.
 	 *
 	 * @param name the name(tag) to be written
 	 * @param text the text to be written
 	 * @return the element
 	 */
 	private Element createTextElement(String name, String text){
	   Text t = doc.createTextNode(text);
	   Element e = doc.createElement(name);
	   e.appendChild(t);
	   return e;
	   }
	 
	 /**
 	 * Writes the file to the ContactManager xml file.
 	 *
 	 * @param out the String to be written to file.
 	 */
 	private void fileWriter(String out){
		try{
			FileWriter fw = new FileWriter("ContactManager.xml");
			fw.write(out);
			fw.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	 }
	 
	/**
	 * Parses the contacts from the ContactManager xml and adds them to a set, also sets the last contact id
	 * for the next contact to be added.
	 *
	 * @param fileName the name of the file to be parsed.
	 * @return the Contact Set.
	 */
	private Set<Contact> parseContacts(String fileName){
		try{
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance(); 
			builder = dbfactory.newDocumentBuilder(); 
			XPathFactory xpfactory = XPathFactory.newInstance(); 
			path = xpfactory.newXPath();
			File f = new File(fileName);
			Document doc = builder.parse(f);
			Set<Contact> items = new LinkedHashSet<Contact>(); 
			int itemCount = Integer.parseInt(path.evaluate("count(/ContactManager/Items/Contact)", doc)); 
			fmMeetingStart = itemCount + 1;
			lastId = itemCount + 1;
			if(itemCount > 0){
				for (int i = 1; i <= itemCount; i++) {
					String idStr = path.evaluate("/ContactManager/Items[" + i + "]/Contact/ID", doc);
					int contactId = Integer.parseInt(idStr);
					String name = path.evaluate( "/ContactManager/Items[" + i + "]/Contact/Name", doc);
					String notes = path.evaluate("/ContactManager/Items[" + i + "]/Contact/Notes", doc);
					Contact c = new ContactImpl(name, contactId);
					c.addNotes(notes); 
					items.add(c);
			 		}
				}
			 return items;
			
		} catch (SAXException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} catch (XPathExpressionException ex){
			ex.printStackTrace();
		} catch (ParserConfigurationException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Parses the Meetings from the ContactManager xml by calling the future and past meeting parsers,
	 * combines and returns the two lists returned from these meethods.
	 *
	 * @param fileName the name of the file to be parsed.
	 * @return a list of the Meetings parsed.
	 */
	private List<Meeting> parseMeetings(String fileName){
		List<Meeting> fm = parseFutureMeetings(fileName);
		List<Meeting> pm = parsePastMeetings(fileName);
		List<Meeting> returnList = new LinkedList<Meeting>();
		if(fm!=null){
			itm = fm.iterator();
			while(itm.hasNext()){
				returnList.add(itm.next());
			}
		}
		if(pm!=null){
			itm = pm.iterator();			
			while(itm.hasNext()){
				returnList.add(itm.next());
			}
		}	
		return returnList;
	}
	
	
	/**
	 * Parses the Future Meetings from the ContactManager xml and adds them to a list to be combined 
	 * with the Past Meetings.
	 *
	 * @param fileName the name of the file to be parsed.
	 * @return a list of the Past Meetings parsed.
	 */
	private List<Meeting> parseFutureMeetings(String fileName){
		try{
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance(); 
			builder = dbfactory.newDocumentBuilder(); 
			XPathFactory xpfactory = XPathFactory.newInstance(); 
			path = xpfactory.newXPath();
			File f = new File(fileName);
			Document doc = builder.parse(f);
			List<Meeting> items = new LinkedList<Meeting>(); 
			int itemCount = Integer.parseInt(path.evaluate("count(/ContactManager/Items/FutureMeeting)", doc)); 
			pmMeetingStart = fmMeetingStart + itemCount;
			if(itemCount > 0){
				meetingId = itemCount + 1;
				itemCount += fmMeetingStart;
				for (int i = fmMeetingStart; i < itemCount; i++) {
					String idStr = path.evaluate("/ContactManager/Items[" + i + "]/FutureMeeting/ID", doc);
					int themeetingId = Integer.parseInt(idStr);
					int year = Integer.parseInt(path.evaluate( "/ContactManager/Items[" + i + "]/FutureMeeting/Year", doc));
					int month = Integer.parseInt(path.evaluate( "/ContactManager/Items[" + i + "]/FutureMeeting/Month", doc));
					int day = Integer.parseInt(path.evaluate( "/ContactManager/Items[" + i + "]/FutureMeeting/Day", doc));
					String contactIds = path.evaluate("/ContactManager/Items[" + i + "]/FutureMeeting/ContactIDs", doc);
					Scanner sc = new Scanner(contactIds);
					sc.useDelimiter(Pattern.compile(", "));
					Set<Contact> contactSet = new LinkedHashSet<Contact>();
					while (sc.hasNext()){ 
						int conIn = Integer.parseInt(sc.next());
						Contact c = getContact(conIn);
						contactSet.add(c);
						}
					Meeting m = new FutureMeetingImpl(new GregorianCalendar(year, month, day), contactSet, themeetingId);
					items.add(m);
					sc.close();
			}
				return items;
			}
			
		} catch (SAXException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} catch (XPathExpressionException ex){
			ex.printStackTrace();
		} catch (ParserConfigurationException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Parses the Past Meetings from the ContactManager xml and adds them to a list to be combined 
	 * with the Future Meetings.
	 *
	 * @param fileName the name of the file to be parsed.
	 * @return a list of the Past Meetings parsed.
	 */
	private List<Meeting> parsePastMeetings(String fileName){
		try{
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance(); 
			builder = dbfactory.newDocumentBuilder(); 
			XPathFactory xpfactory = XPathFactory.newInstance(); 
			path = xpfactory.newXPath();
			File f = new File(fileName);
			Document doc = builder.parse(f);
			List<Meeting> items = new LinkedList<Meeting>(); 
			int itemCount = Integer.parseInt(path.evaluate("count(/ContactManager/Items/PastMeeting)", doc)); 
			if(itemCount > 0){
				meetingId += itemCount;
				itemCount += pmMeetingStart;
				for (int i = pmMeetingStart; i < itemCount; i++) {
					String idStr = path.evaluate("/ContactManager/Items[" + i + "]/PastMeeting/ID", doc);
					int themeetingId = Integer.parseInt(idStr);
					int year = Integer.parseInt(path.evaluate( "/ContactManager/Items[" + i + "]/PastMeeting/Year", doc));
					int month = Integer.parseInt(path.evaluate( "/ContactManager/Items[" + i + "]/PastMeeting/Month", doc));
					int day = Integer.parseInt(path.evaluate( "/ContactManager/Items[" + i + "]/PastMeeting/Day", doc));
					String notes = path.evaluate("/ContactManager/Items[" + i + "]/PastMeeting/Notes", doc);
					String contactIds = path.evaluate("/ContactManager/Items[" + i + "]/PastMeeting/ContactIDs", doc);
					Scanner sc = new Scanner(contactIds);
					sc.useDelimiter(Pattern.compile(", "));
					Set<Contact> contactSet = new LinkedHashSet<Contact>();
					while (sc.hasNext()){ 
						int conIn = Integer.parseInt(sc.next());
						Contact c = getContact(conIn);
						contactSet.add(c);
					}
					Meeting m = new PastMeetingImpl(new GregorianCalendar(year, month, day), contactSet, notes, themeetingId);
					items.add(m);
					sc.close();
				}
				return items;
			}
			
		} catch (SAXException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} catch (XPathExpressionException ex){
			ex.printStackTrace();
		} catch (ParserConfigurationException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Checks if the file contains any meetings.
	 *
	 * @param fileName the name of the file to be parsed.
	 * @return true, if successful.
	 */
	private boolean doesItContainMeetings(String fileName){
		try{
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance(); 
			builder = dbfactory.newDocumentBuilder(); 
			XPathFactory xpfactory = XPathFactory.newInstance(); 
			path = xpfactory.newXPath();
			File f = new File(fileName);
			Document doc = builder.parse(f);
			int fmCount = Integer.parseInt(path.evaluate("count(/ContactManager/Items/FutureMeeting)", doc)); 
			int pmCount = Integer.parseInt(path.evaluate("count(/ContactManager/Items/FutureMeeting)", doc));
			if(fmCount > 0 || pmCount > 0){
				return true;
			} else {
				return false;
			}
		} catch (SAXException ex){
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} catch (XPathExpressionException ex){
			ex.printStackTrace();
		} catch (ParserConfigurationException ex){
			ex.printStackTrace();
		}
		return false;
	}
}
