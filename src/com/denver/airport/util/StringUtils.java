package com.denver.airport.util;

public final class StringUtils {
	private static final String EMPTY_STRING = "";
	
	private StringUtils() {
	}
	
	public static String getSubstring(String startStr, String destStr) {
		if (!isEmpty(startStr) && !isEmpty(destStr)) {
			return destStr.substring(startStr.length(), destStr.length());
		}
		return null;
	}
	
	public static boolean isEmpty(String srcStr) {
		if (srcStr != null && !srcStr.trim().equals(EMPTY_STRING)) {
			return false;
		}
		return true;
	}
}
