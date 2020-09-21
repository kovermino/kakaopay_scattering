package com.jongwoo.kakao.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class TimeUtilTest {
	
	@Test
	void getSpecificTimeTest() {
		Timestamp time1 = TimeUtil.getSpecificTime(2020, 9, 26, 13, 4, 54);
		Timestamp time2 = TimeUtil.getSpecificTime(1991, 2, 20, 7, 38, 22);
		Timestamp time3 = TimeUtil.getSpecificTime(2005, 1, 17, 5, 5, 5);
		Timestamp time4 = TimeUtil.getSpecificTime(2020, 12, 3, 10, 4, 30);
		Timestamp time5 = TimeUtil.getSpecificTime(1900, 9, 26, 13, 4, 54);
		
		assertEquals("2020-09-26 13:04:54.0", time1.toString());
		assertEquals("1991-02-20 07:38:22.0", time2.toString());
		assertEquals("2005-01-17 05:05:05.0", time3.toString());
		assertEquals("2020-12-03 10:04:30.0", time4.toString());
		assertEquals("1900-09-26 13:04:54.0", time5.toString());
	}
	
	@Test
	void getDaysBetweenTest() {
		Timestamp time1 = TimeUtil.getSpecificTime(2020, 9, 26, 13, 4, 54);
		Timestamp time2 = TimeUtil.getSpecificTime(2020, 9, 28, 7, 38, 22);
		Timestamp time3 = TimeUtil.getSpecificTime(2020, 12, 3, 10, 4, 30);
		
		assertEquals(1, TimeUtil.getDaysBetween(time1, time2));
		assertEquals(67, TimeUtil.getDaysBetween(time1, time3));
		assertEquals(66, TimeUtil.getDaysBetween(time2, time3));
	}
	
	@Test
	void getMinutesBetweenTest() {
		Timestamp time1 = TimeUtil.getSpecificTime(2020, 9, 26, 13, 4, 54);
		Timestamp time2 = TimeUtil.getSpecificTime(2020, 9, 28, 7, 38, 22);
		Timestamp time3 = TimeUtil.getSpecificTime(2020, 12, 3, 10, 4, 30);
		Timestamp time4 = TimeUtil.getSpecificTime(2020, 12, 3, 10, 19, 45);
		
		assertEquals(2553, TimeUtil.getMinutesBetween(time1, time2));
		assertEquals(97739, TimeUtil.getMinutesBetween(time1, time3));
		assertEquals(95186, TimeUtil.getMinutesBetween(time2, time3));
		assertEquals(15, TimeUtil.getMinutesBetween(time3, time4));
	}

}
