package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Esta classe tem por objetivo oferecer métodos que realizam simples tarefas,
 * mas que ajudam na implementação.
 *
 */
public class Helper {
	/**
	 * Este método checa se a data de formatura vem depois da data de admisssao
	 * @param grad - Data de formatura
	 * @param adm - Data de admissao
	 * @return <code>true</code>: Se a data da formatura vem depois da data de admissão<br>
	 * 		   <code>false</code>: Se a data da formatura não vem depois da data de admissão
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
	 * Este método checa se uma string contém um caractere inválido.
	 * Os caracteres considerados inválidos são: '%', '@' e '#'
	 * @param attribute - Um atributo, uma string
	 * @return O caractere inválid encontrado ou <code>null</code> se nenhum foi encontrado
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
	 * Este método checa se a data é invalida.
	 * @param date - Uma data no formato dd/MM/yyyy
	 * @return <code>true</code>: Se data é inválida<br>
	 * 		   <code>false</code>: Se data não é inválida
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
	
	/**
	 * Este método verifica se um valor em dinheiro recebido como string é válido
	 * tanto com R$ ou $ no início
	 * @param valor - A quantia
	 * @return <code>true</code>: Se o valor é válido<br>
	 * 		   <code>false</code>: Se o valor não é válido
	 */
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
	
	/**
	 * Este método verifica se um valor em dinheiro recebido como string é válido
	 * @param valor - A quantia
	 * @return <code>true</code>: Se o valor é válido<br>
	 * 		   <code>false</code>: Se o valor não é válido
	 */
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
