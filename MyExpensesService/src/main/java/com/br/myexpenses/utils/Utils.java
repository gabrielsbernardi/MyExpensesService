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
	
	public static String getMesPorExtenso(String data) {
		String[] dataSeparada = data.split("/");
		String mes = dataSeparada[0];
		String ano = dataSeparada[1].substring(2, dataSeparada[1].length());
		if ("01".equals(mes)) {
			return "JAN/" + ano;
		} else if ("02".equals(mes)) {
			return "FEV/" + ano;
		} else if ("03".equals(mes)) {
			return "MAR/" + ano;
		} else if ("04".equals(mes)) {
			return "ABR/" + ano;
		} else if ("05".equals(mes)) {
			return "MAI/" + ano;
		} else if ("06".equals(mes)) {
			return "JUN/" + ano;
		} else if ("07".equals(mes)) {
			return "JUL/" + ano;
		} else if ("08".equals(mes)) {
			return "AGO/" + ano;
		} else if ("09".equals(mes)) {
			return "SET/" + ano;
		} else if ("10".equals(mes)) {
			return "OUT/" + ano;
		} else if ("11".equals(mes)) {
			return "NOV/" + ano;
		}
		return "DEZ/" + ano;
	}
}
