package contactManager;


/**
 * The Class ContactImpl that implements Contact.
 *
 * @see contactManager.Contact
 */
public class ContactImpl implements Contact {

		/** The contacts name. */
		private final String name;
		
		/** The notes related to the contact. */
		private String notes;
		
		/** The contact's id. */
		private int id = 0;
		
		
		/**
		 * Instantiates a new contact.
		 *
		 * @param the contact's name
		 * @param the contact's id
		 */
		public ContactImpl(String name, int id){
			if(name == null){
				throw (new IllegalArgumentException("test"));
			}
				this.id = id;
				this.name = name;
			
		}
		
		/** 
		 * {@inheritDoc}
		 */
		public String getName(){
			return name;
		}
		
		/** 
		 * {@inheritDoc}
		 */
		public int getId() {
				return id;
		}
		
		/** 
		 * {@inheritDoc}
		 */
		public String getNotes() {
			return notes;
		}

		/** 
		 * {@inheritDoc}
		 */
		public void addNotes(String note) {
			this.notes = note;
		}
		
		
}
