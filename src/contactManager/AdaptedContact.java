package contactManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CONTACT")
public class AdaptedContact {

    private Contact con ;
    private String name;
    private int id;
    private String notes;

    public Contact getContact() {
        return con;
    }

    public void setContact(Contact con) {
        this.con = con;
    }
    
	@XmlElement(name = "NAME")
	public String getName(){
		name = con.getName();
		return name;
	}
	
	@XmlElement(name = "ID")
	public int getId() {
		id = con.getId();
		return id;
	}
	
	@XmlElement(name = "NOTES")
	public String getNotes() {
		notes = con.getNotes();
		return notes;
	}                      

}