package contactManager;

public class ContactImpl implements Contact {

		private String name;
		
		public ContactImpl(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}
		
		
}
