package tests;

import static org.junit.Assert.*;
import contactManager.*;

import org.junit.Test;

public class XMLHandlerTest {

	@Test
	public void checkForFiletest() {
		XMLHandler testWTX = new XMLHandlerImpl();
		boolean output = testWTX.checkForFile();
		boolean expected = true;
		assertEquals(expected, output);
	}
	
	@Test
	public void contactWriteAndLastIdGetTest(){
		
	}
}
