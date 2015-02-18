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
			File file = new File("ContactManager.xml");
			JAXBContext context = JAXBContext.newInstance(ContactImpl.class);
			Unmarshaller um = context.createUnmarshaller();
			ContactImpl newCon = (ContactImpl) um.unmarshal(file);
				
			return newCon;
		//} catch (IOException ex) {
	  //      ex.printStackTrace();
		} catch (JAXBException ex){
			ex.printStackTrace();
		}
		
		return null;
	}
	
}
