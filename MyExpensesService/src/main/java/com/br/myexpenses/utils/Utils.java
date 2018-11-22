package com.br.myexpenses.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class Utils {
	
	public static Boolean listEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}
	
	public static Boolean stringIsNull(String value) {
		return value == null || "".equals(value.trim());
	}
	
	public static Double duasCasasDecimais(Double valor) {
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
	    symbols.setDecimalSeparator('.');
	    DecimalFormat format = new DecimalFormat("#.##", symbols);
	    return Double.valueOf((format.format(valor)));
	}
	
	public static Boolean booleanIsTrue(Boolean bool) {
		return bool != null && bool;
	}
	
	public static String sqlAndEqualsPesquisaString(String column, String param) {
		return " AND UPPER(" + column + ") LIKE '%" + param.toUpperCase() + "%'";
	}
	
	public static String sqlAndEqualsPesquisaInteger(String column, Integer param) {
		return " AND " + column + " = " + param;
	}
}
