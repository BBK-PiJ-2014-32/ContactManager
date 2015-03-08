package contactManager;

import java.io.File;
import java.io.IOException;

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

}
