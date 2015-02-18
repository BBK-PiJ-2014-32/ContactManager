package contactManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ContactManager")
public class ContactImpl implements Contact {

		private String name;
		private String notes;
		private int id = 0;
		private static int lastId = 0; //Needs to not be static, should be updated from stored csv
		
		public ContactImpl(){
			if (this.getClass() == ContactImpl.class){
				lastId++;
			}
			this.id = getLastId();
		}
		@XmlElement
		public String getName(){
			return name;
		}
		@XmlElement
		public int getId() {
				return id;
		}
		
		@XmlElement
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
		
		public void setName(String name){
			this.name = name;
		}
}
