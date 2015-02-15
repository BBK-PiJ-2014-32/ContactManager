package contactManager;

import java.io.*;

public class FileReaderImpl implements FileReader{

	
	public boolean checkForFile(){
		try{
			File dataStore = new File("contactManager.csv");
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
