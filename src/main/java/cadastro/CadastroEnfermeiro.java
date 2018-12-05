package cadastro;

import java.util.ArrayList;

import bancodados.EnfermeiroDAO;
import interfaces.Gerenciavel;
import modelo.Enfermeiro;
import util.Helper;

/**
 * This class aims to offer methods to allow the management of nurses ({@link Enfermeiro}). 
 * With this class it's possible to create, read and update nurses from a list.
 * This class extends the {@link CadastroFuncionario} that generalizes methods to be used
 * by another siblings classes like {@link CadastroMedico} and {@link CadastroAuxiliar}.
 */
public class CadastroEnfermeiro implements Gerenciavel<Enfermeiro>{
	private EnfermeiroDAO enfermeiroDAO;
	
	public CadastroEnfermeiro() {
		enfermeiroDAO = new EnfermeiroDAO();
	}
	
	/**
	 * This method registers a new nurse by calling the 'novo' method from class's father {@link CadastroFuncionario}.
	 * @param nome - The nurse's name
	 * @param coren - The nurse's COREN
	 * @param sex - The nurse's gender
	 * @param nat - The nurse's nationality
	 * @param bthDate - The nurse's birth date
	 * @param gradDate - The nurse's graduation date
	 * @param admDate - The nurse's admission date
	 * 
	 * @return A message indicating an error or success to create a new nurse
	 */
	public String novo(String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
		if(!coren.matches("[0-9]{2,6}")) {
			return "Identificador inválido";
		}
		if (existe(coren)) {
			return("ERRO! COREN Já existente!");			
		}
		
		ArrayList<String> atts = new ArrayList<String>();
		atts.add(nome);
		atts.add(nat);
		for (String att:atts) {
			String c = Helper.caracterInvalido(att);
			if (c != null)
				return("ERRO! Caracter '" + c + "' Invalido!");
		}
		
		if(!sex.matches("F|M|O")) {
			return "Campo Sexo inválido";
		}
		if(!nome.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
			return "Campo Nome inválido";
		}
		if(!nat.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
			return "Campo Nacionalidade inválido";
		}
				
		
		// Checks if any date is invalid
		if(Helper.dataInvalida(bthDate) || Helper.dataInvalida(admDate) || Helper.dataInvalida(gradDate))
			return("ERRO! Data Inválida!");
		
		// Checks if graduation comes after admission
		if(Helper.gradAfterAdm(gradDate, admDate))
			return ("ERRO! Inconsistencia de datas: Formatura posterior a admissão!");
		
		// Instantiates a new Functionary
		Enfermeiro enfermeiro = new Enfermeiro(nome, sex, coren, nat, bthDate, admDate, gradDate);
		
		// After all tests, adds the nurse to the list
		inserir(enfermeiro);
		
		return "Enfermeiro inserido!";
	}
	
	/**
	 * This method finds a nurse in nurses list by calling the 'encontraFuncionario' method from class's father {@link CadastroFuncionario}.
	 * @param atributo - The name or COREN of a nurse
	 * @return A string containing all information of the nurse found.<br>
	 * 		If the nurse wasn't found, the method returns ""
	 */
	@Override
	public String encontra(String atributo) {
		if(atributo.matches("[ ]*")) {
			return "Atributo inválido";
		}
		
		Enfermeiro enfermeiro;
		
		if(atributo.matches("[0-9]{2,6}")) {
			enfermeiro = enfermeiroDAO.selecionarEnfermeiroCoren(atributo);
		}else {
			enfermeiro = enfermeiroDAO.selecionarEnfermeiroNome(atributo);
		}
		if(enfermeiro != null) {
			return(enfermeiro.getNome()+"%"+enfermeiro.getSex()+"%"+enfermeiro.getCoren()
					+"%"+enfermeiro.getNat()+"%"+enfermeiro.getBthDate()+"%"
					+enfermeiro.getAdmDate()+"%"+enfermeiro.getGradDate());
		}else {
			return "";
		}
	}
	
	/**
	 * This method modify an existent nurse by calling the 'alteraFuncionario' method from class's father {@link CadastroFuncionario}.
	 * @param coren - The nurse's COREN
	 * @param atributo - A nurse's attribute (Nome, COREN, Sexo...)
	 * @param valor - The value to subscribe the old attribute value
	 * @return A message indicating an error or a success.
	 */
	@Override
	public String altera(String coren, String atributo, String valor) {
		Enfermeiro e = enfermeiroDAO.selecionarEnfermeiroCoren(coren);
		boolean altera = false;
		if(e == null) {
			return("Enfermeiro não cadastrado");
		}
		switch(atributo) {
			case "Nome":
				if(!valor.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
					return "Campo Nome inválido";
				}
				altera = true;
				break;
			case "Sexo":
				if(!valor.matches("F|M|O")) {
					return "Campo Sexo inválido";
				}
				altera = true;
				break;
			case "Nacionalidade":
				if(!valor.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
					return "Campo Nacionalidade inválido";
				}
				altera = true;
				break;
			case "DtNasc":
				altera = true;
				break;
			case "DtAdmiss":
				if(Helper.gradAfterAdm(e.getGradDate(), valor))
					return("ERRO! Inconsistencia de datas: Formatura posterior a admissão!");
				altera = true;
				break;
			case "DtFormatura":
				if(Helper.gradAfterAdm(valor, e.getAdmDate()))
					return("ERRO! Inconsistencia de datas: Formatura posterior a admissão!");
				altera = true;
				break;
			default:
				return("Atributo inválido!");
		}
		if(altera) {
			enfermeiroDAO.alterarEnfermeiro(coren, atributo, valor);
			return("Alteracao executada com sucesso!");
		}
		return("");
	}
	
	/**
	 * This method checks if the functionary already exists
	 * @param ident - The functionary's identification
	 * @return <code>true</code>: If the functionary already exists<br>
	 * 		   <code>false</code>: If the functionary don't exists yet
	 */
	@Override
	public boolean existe(String coren) {
		Enfermeiro enfermeiro = enfermeiroDAO.selecionarEnfermeiroCoren(coren);
		if(enfermeiro != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void inserir(Enfermeiro enfermeiro) {
		enfermeiroDAO.inserirEnfermeiro(enfermeiro);
	}
}
