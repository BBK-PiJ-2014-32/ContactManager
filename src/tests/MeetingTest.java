package tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.*;
import java.time.*;


import org.junit.Test;

import contactManager.Meeting;
import contactManager.MeetingImpl;

public class MeetingTest {

	@Test
	public void getIdTest() {
		Meeting testMeet = new MeetingImpl();
		int output = testMeet.getId();
		int expected = 1;
		assertEquals(expected, output);
	}
	
	@Test
	public void getDateTest(){
		Meeting testMeet = new MeetingImpl(12,4,2015);
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar output = testMeet.getDate();
		Calendar expected = new GregorianCalendar(2015,03,12);
		assertEquals(expected, output);
		
	}

}
