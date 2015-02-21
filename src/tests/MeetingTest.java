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
		Meeting testMeet = new MeetingImpl(12,4,2015);
		int output = testMeet.getId();
		int expected = 1;
		assertEquals(expected, output);
	}
	
	@Test
	public void getDateTest(){
		Meeting testMeet = new MeetingImpl(12,4,2015);
		Calendar output = testMeet.getDate();
		Calendar expected = Calendar.getInstance();
		expected.set(Calendar.YEAR, 2015);
		expected.set(Calendar.MONTH, 4);
		expected.set(Calendar.DAY_OF_MONTH, 12);
		assertEquals(expected, output);
		
	}

}
