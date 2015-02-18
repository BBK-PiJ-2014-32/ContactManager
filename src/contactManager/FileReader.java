package contactManager;

/**
 * The FileReader checks if a file where the contacts and meeting are stored already exists,
 * if it does not it will use a method from the FileWriter class and create a new file.
 */
public interface FileReader {

	
/**
 * Checks if a file for contacts and meeting already exists.
 *
 * @return true, if file exists.
 */
boolean checkForFile();	

/**
 * Gets the contact name.
 *
 * @param id of the contact being retreived.
 * @return the contact name
 */
Contact getContact(int id);

}
