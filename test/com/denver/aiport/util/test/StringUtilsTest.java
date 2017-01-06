package com.denver.aiport.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.denver.airport.util.StringUtils;

public class StringUtilsTest {
	@Test
	public void testGetSubstring_ReturnNull_whenPassedNull() {
		String subString = StringUtils.getSubstring(null, null);
		assertNull(subString);
	}

	@Test
	public void testGetSubstring_ReturnNull_whenPassedSrcStringNull() {
		String subString = StringUtils.getSubstring(null, "dest");
		assertNull(subString);
	}
	
	@Test
	public void testGetSubstring_ReturnNull_whenPassedDestStringNull() {
		String subString = StringUtils.getSubstring("src",null);
		assertNull(subString);
	}
	
	@Test
	public void testGetSubstring_ReturnNull_whenPassedEmptyString() {
		String subString = StringUtils.getSubstring("", "");
		assertNull(subString);
	}

	@Test
	public void testGetSubstring_ReturnNull_whenPassedSrcStringEmpty() {
		String subString = StringUtils.getSubstring("", "dest");
		assertNull(subString);
	}
	
	@Test
	public void testGetSubstring_ReturnNull_whenPassedDestStringEmpty() {
		String subString = StringUtils.getSubstring("src","");
		assertNull(subString);
	}
	
	@Test
	public void testGetSubstring_ReturnCorrectValue_whenPassedValidInput() {
		String subString = StringUtils.getSubstring("src","srcdest");
		assertEquals(subString, "dest");
	}
	
	@Test
	public void testisEmpty_ReturnTrue_whenPassedNull() {
		boolean isEmpty = StringUtils.isEmpty(null);
		assertTrue(isEmpty);
	}
	
	@Test
	public void testisEmpty_ReturnTrue_whenPassedEmptyString() {
		boolean isEmpty = StringUtils.isEmpty("");
		assertTrue(isEmpty);
	}
	
	@Test
	public void testisEmpty_ReturnFalse_whenPassedValidString() {
		boolean isEmpty = StringUtils.isEmpty("valid");
		assertFalse(isEmpty);
	}
}
