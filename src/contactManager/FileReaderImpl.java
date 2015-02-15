package contactManager;

import java.io.*;

/**
 * The Class FileReaderImpl.
 */
public class FileReaderImpl implements FileReader{

	
	/* (non-Javadoc)
	 * @see contactManager.FileReader#checkForFile()
	 */
	public boolean checkForFile(){
		try{
			File dataStore = new File("contactManager.xml");
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
}
