package contactManager;

import contactManager.ContactImpl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class AdaptedContact {
	
	private String name;
	private String notes;
	private int id;
	
	@XmlAttribute
	public int getId() {
			return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getNotes() {
		return notes;
	}

	public void addNotes(String note) {
		this.notes = note;
	}
}
