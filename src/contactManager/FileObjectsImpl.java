package contactManager;

/**
 * The Class FileObjectsImpl which implements the FileObjects interface
 * @see contactManager.FileObjects
 */
public class FileObjectsImpl implements FileObjects{

	/** The object. */
	private Object theObject;
	
	/**
	 * Instantiates a new file objects.
	 *
	 * @param anObject the object to wrapped.
	 */
	public FileObjectsImpl(Object anObject){
		theObject = anObject;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Object getObject() {
		return theObject;
	}

}
