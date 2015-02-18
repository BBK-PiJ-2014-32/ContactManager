package contactManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * The Class FileReaderImpl.
 */
public class ReaderFileImpl implements ReaderFile{

	
	/* (non-Javadoc)
	 * @see contactManager.FileReader#checkForFile()
	 */
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
	
	public Contact getContact(int id){
		try{
			JAXBContext context = JAXBContext.newInstance(Contact.class);
			Unmarshaller um = context.createUnmarshaller();
			Contact newCon = (Contact) um.unmarshal(new FileReader("ContactManager.xml"));
			return newCon;
		} catch (IOException ex) {
	        ex.printStackTrace();
		} catch (JAXBException ex){
			ex.printStackTrace();
		}
		
		return null;
	}
	
}
