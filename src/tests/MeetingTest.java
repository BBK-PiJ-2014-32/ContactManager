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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar outputCal = testMeet.getDate();
		String output = sdf.format(outputCal.getTime());
		Calendar expectedCal = sdf.getCalendar();
		expectedCal.set(Calendar.YEAR, 2015);
		expectedCal.set(Calendar.MONTH, 4);
		expectedCal.set(Calendar.DAY_OF_MONTH, 12);
		String expected = sdf.format(expectedCal.getTime());
		assertEquals(expected, output);
		
	}

}
