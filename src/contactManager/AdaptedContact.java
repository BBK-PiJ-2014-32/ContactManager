package contactManager;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CONTACT")
public class AdaptedContact {
	
	private String name;

	@XmlAttribute
	public String getName(){
		System.out.println("aaaaaaaaaaaaaaaaa");
		return name;
	}

	public void setName(String name){
		System.out.println("aaaaaaaaaaaaaaaaa");
		this.name = name;
	}

}
