package contactManager;

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
		
		public String getName(){
			return name;
		}

		public int getId() {
				return id;
		}

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
}
