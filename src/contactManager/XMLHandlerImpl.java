package contactManager;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedHashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class XMLHandlerImpl implements XMLHandler {
	
	
	@Override
	public boolean checkForFile(){
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

	@Override
	public void contactWrite(Set<Contact> contactSet) {
		try {
			File dataStore = new File ("ContactManager.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(AdaptedContact.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			//format pretty print
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			AdaptedContact adapCon = new AdaptedContact();
			Iterator<Contact> it = contactSet.iterator();
			while(it.hasNext()){
				adapCon.setCon(it.next());
				jaxbMarshaller.marshal(adapCon, dataStore);
			}
			
		} catch (JAXBException ex){
			ex.printStackTrace();
		}
			
		
	}

	@Override
	public int getLastContactId() {
		try{
			File file = new File("ContactManager.xml");
			JAXBContext context = JAXBContext.newInstance(AdaptedContact.class);
			Unmarshaller um = context.createUnmarshaller();
			AdaptedContact newCon = (AdaptedContact) um.unmarshal(file);
			Contact contact = newCon.getContact();
		    Set<Contact> conSet = new LinkedHashSet<Contact>();
			conSet.add(contact);
		    int lastId = 0;
		    Iterator<Contact> it = conSet.iterator();
		    
		    while(it.hasNext()){
		    	Contact next = it.next();
		    	lastId = next.getId();
		    	return lastId;
		    	}	
		    return lastId; 
		} catch (JAXBException ex){
			ex.printStackTrace();
		}
	return 0;
	}	
}
