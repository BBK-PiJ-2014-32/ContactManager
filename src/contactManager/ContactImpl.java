package contactManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


// TODO: Auto-generated Javadoc
/**
 * The Class ContactImpl.
 */

public class ContactImpl implements Contact {

		/** The name. */
		private final String name;
		
		/** The notes. */
		private String notes;
		
		/** The id. */
		private int id = 0;
		
		/**
		 * Instantiates a new contact impl.
		 *
		 * @param name the name
		 */
		public ContactImpl(String name, int id){
			if(name == null){
				throw (new IllegalArgumentException("test"));
			}
				this.id = id;
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
		
		
}
