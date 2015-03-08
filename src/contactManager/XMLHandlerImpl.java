package contactManager;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



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
			JAXBContext jaxbContext = JAXBContext.newInstance(ContactImpl.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			//format pretty print
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			Iterator<Contact> it = contactSet.iterator();
			while(it.hasNext()){
				jaxbMarshaller.marshal(it.next(), dataStore);
			}
		} catch (JAXBException ex){
			ex.printStackTrace();
		}
			
		
	}

	@Override
	public int getLastContactId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
