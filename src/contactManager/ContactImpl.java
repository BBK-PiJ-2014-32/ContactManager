package contactManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class ContactImpl implements Contact {

		private String name;
		private String notes;
		private int id = 0;
		private static int lastId = 0; //Needs to not be static, should be updated from stored csv
		
		public ContactImpl(String name){
			if (this.getClass() == ContactImpl.class){
				lastId++;
			}
			this.name = name;
			this.id = getLastId();
		}
		@XmlElement(name = "name")
		public String getName(){
			return name;
		}
		@XmlElement(name = "Id")
		public int getId() {
				return id;
		}
		
		@XmlElement(name = "notes")
		public String getNotes() {
			return notes;
		}

		public void addNotes(String note) {
			this.notes = note;
		}
		
		private static int getLastId(){
			//Code to be added to retrieve lastId from stored csv			
			return lastId;
		}

		public boolean resetLastId() {
			lastId = 0;
			return true;
		}
}
