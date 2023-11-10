package com.rafael.util;

public class Util {

	public double convertToDouble(String strNumber) {
		if (strNumber == null)
			return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) {
			return Double.parseDouble(number);
		}
		return 0D;
	}

	public boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		try {
			Double.parseDouble(number);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
