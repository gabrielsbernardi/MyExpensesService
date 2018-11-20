package com.br.myexpenses.utils;

import java.util.List;

public class Utils {
	
	public static Boolean listEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}
	
	public static Boolean stringIsNull(String value) {
		return value == null || "".equals(value.trim());
	}
}
