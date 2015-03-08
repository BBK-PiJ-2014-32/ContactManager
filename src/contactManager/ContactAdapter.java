package contactManager;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ContactAdapter extends XmlAdapter<AdaptedContact, ContactImpl>{

	@Override
	public ContactImpl unmarshal(AdaptedContact ac) throws Exception {
		System.out.println("aaaaaaaaaaaaaaaaa");
		return new ContactImpl(ac.getName());
	}

	@Override
	public AdaptedContact marshal(ContactImpl c) throws Exception {
		System.out.println("aaaaaaaaaaaaaaaaa");
		AdaptedContact ac = new AdaptedContact();
		ac.setName(c.getName());
		return ac;
	}

}
