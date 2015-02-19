package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

}
