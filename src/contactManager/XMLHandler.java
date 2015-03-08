package contactManager;

import java.util.Set;



// TODO: Auto-generated Javadoc
/**
 * The Interface WriteToXML deals with all the writing and retrieving from the.
 */
public interface XMLHandler {
	
/**
* Checks if a file for contacts and meeting already exists.
*
* @return true, if file exists.
*/
boolean checkForFile();	


/**
 * writes the all contacts stored in the contactSet to XML.
 *
 * @param contactSet the contact set
 */
void contactWrite(Set<Contact> contactSet);

/**
 * Gets the value of the id for the last contact stored.
 *
 * @return the last contact id
 */
int getLastContactId();
	
}

