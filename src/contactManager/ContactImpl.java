package contactManager;

public class ContactImpl implements Contact {

		private String name;
		private String notes;
		private int id = 0;
		private static int lastId = 0;
		
		public ContactImpl(String name){
			if (this.getClass() == ContactImpl.class){
				lastId++;
			}
			this.name = name;
			this.id = lastId;
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
			return lastId;
		}
}
