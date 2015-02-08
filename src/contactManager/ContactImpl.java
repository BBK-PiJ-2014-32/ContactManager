package contactManager;

public class ContactImpl implements Contact {

		private String name;
		private String notes;
		
		public ContactImpl(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}

		public int getId() {
				return 0;
		}

		public String getNotes() {
			return notes;
		}

		public void addNotes(String note) {
			this.notes = note;
		}
		
		
}
