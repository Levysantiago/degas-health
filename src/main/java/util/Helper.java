package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
	/**
	 * This method checks if graduation date comes after admission date.
	 * @param grad - The graduation date
	 * @param adm - The admission date
	 * @return <code>true</code>: If the graduation date comes after admission date<br>
	 * 		   <code>false</code>: If the graduation date not comes after admission date
	 */
	public static boolean gradAfterAdm(String grad, String adm) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		
		try {
			Date gradDate = df.parse(grad);
			Date admDate = df.parse(adm);
			if(gradDate.after(admDate))
				return true;
			else
				return false;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * This method checks if any attribute contains an invalid character.
	 * The invalid characters are: '%', '@' and '#' 
	 * @param attribute - A functionary attribute, it can be a Name or Nationality
	 * @return The invalid character if it was found, or <code>null</code> if none were found
	 */
	public static String caracterInvalido(String attribute) {
		//Checks if there is an invalid char in attribute 
		if (attribute.contains("%"))
			return("%");
		if (attribute.contains("@"))
			return("@");
		if (attribute.contains("#"))
			return("#");
		return null;
	}
	
	/**
	 * This method checks if a date is invalid.
	 * @param date - A date in dd/MM/yyyy format
	 * @return <code>true</code>: If the date is invalid<br>
	 * 		   <code>false</code>: If the date is valid
	 */
	public static boolean dataInvalida(String date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		
		try {
			df.parse(date);
			return false;
		}
		catch(Exception e) {
			return true;
		}
	}
	
	public static boolean valorValido(String valor) {
		if(valor.length() < 3 && valor.matches("[0-9]{1,2}")) {
			return true;
		}
		if (valor.substring(0, 2).equals("R$") || valor.substring(0, 1).equals("$") ||  isStringDouble(valor)){
			if(valor.contains(".")) {
				return false;
			}
			
			return true;
		}
		return false;
	}
	
	public static boolean isStringDouble(String input){
		if(input.contains(","))
			input = input.replace(",", ".");
		try{
			Double.parseDouble(input);
			return true;
		}
		catch(NumberFormatException ex){
			return false;
		}
	}
}
