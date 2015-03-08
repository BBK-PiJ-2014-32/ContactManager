package contactManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


// TODO: Auto-generated Javadoc
/**
 * The Class ContactImpl.
 */

@XmlJavaTypeAdapter(ContactAdapter.class)
public class ContactImpl implements Contact {

		/** The name. */
		private final String name;
		
		/** The notes. */
		private String notes;
		
		/** The id. */
		private int id = 0;
		
		/** The last id. */
		private static int lastId = 0; //Needs to not be static, should be updated from stored csv
		

		
		
		/**
		 * Instantiates a new contact impl.
		 *
		 * @param name the name
		 */
		public ContactImpl(String name){
			if(name == null){
				throw (new IllegalArgumentException("test"));
			}
				if (this.getClass() == ContactImpl.class){
					lastId++;
				}
				this.id = getLastId();
				this.name = name;
			
		}
		
		/* (non-Javadoc)
		 * @see contactManager.Contact#getName()
		 */
		@XmlElement(name = "NAME")
		public String getName(){
			return name;
		}
		
		/* (non-Javadoc)
		 * @see contactManager.Contact#getId()
		 */
		@XmlElement(name = "ID")
		public int getId() {
				return id;
		}
		
		/* (non-Javadoc)
		 * @see contactManager.Contact#getNotes()
		 */
		@XmlElement(name = "NOTES")
		public String getNotes() {
			return notes;
		}

		/* (non-Javadoc)
		 * @see contactManager.Contact#addNotes(java.lang.String)
		 */
		public void addNotes(String note) {
			this.notes = note;
		}
		
		/**
		 * Gets the last id.
		 *
		 * @return the last id
		 */
		private static int getLastId(){
			//Code to be added to retrieve lastId from stored csv			
			return lastId;
		}

		/* (non-Javadoc)
		 * @see contactManager.Contact#resetLastId()
		 */
		public boolean resetLastId() {
			lastId = 0;
			return true;
		}
		
}
