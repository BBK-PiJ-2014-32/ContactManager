package contactManager;

public class FileObjectsImpl implements FileObjects{

	private Object theObject;
	
	public FileObjectsImpl(Object anObject){
		theObject = anObject;
	}
	
	@Override
	public Object getObject() {
		return theObject;
	}

}
