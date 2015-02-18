package contactManager;

import java.io.*;
import javax.xml.*;

/**
 * The Class FileReaderImpl.
 */
public class FileReaderImpl implements FileReader{

	
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
		Contact newCon = new ContactImpl(null);
		return newCon;
	}
	
}
