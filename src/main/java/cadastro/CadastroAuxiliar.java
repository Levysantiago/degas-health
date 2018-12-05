package cadastro;

import java.util.ArrayList;

import bancodados.AuxiliarDAO;
import interfaces.Gerenciavel;
import modelo.Auxiliar;
import util.Helper;

/**
 * This class aims to offer methods to allow the management of assistants ({@link Auxiliar}). 
 * With this class it's possible to create, read and update assistants from a list.
 * This class extends the {@link CadastroFuncionario} that generalizes methods to be used
 * by another siblings classes like {@link CadastroMedico} and {@link CadastroEnfermeiro}.
 */
public class CadastroAuxiliar implements Gerenciavel<Auxiliar>{
	private AuxiliarDAO auxiliarDAO;
	
	public CadastroAuxiliar() {
		auxiliarDAO = new AuxiliarDAO();
	}
	
	/**
	 * This method registers a new assistant by calling the 'novo' method from class's father {@link CadastroFuncionario}.
	 * @param nome - The assistant's name
	 * @param coren - The assistant's COREN
	 * @param sex - The assistant's gender
	 * @param nat - The assistant's nationality
	 * @param bthDate - The assistant's birth date
	 * @param gradDate - The assistant's graduation date
	 * @param admDate - The assistant's admission date
	 * 
	 * @return A message indicating an error or success to create a new assistant
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
		Auxiliar auxiliar = new Auxiliar(nome, sex, coren, nat, bthDate, admDate, gradDate);
		
		// After all tests, adds the functionary to the list
		inserir(auxiliar);
		
		return "Auxiliar/Técnico inserido!";
	}

	/**
	 * This method finds an assistant in assistants list by calling the 'encontraFuncionario' method from class's father {@link CadastroFuncionario}.
	 * @param atributo - The name or COREN of an assistant
	 * @return A string containing all information of the assistant found.<br>
	 * 		If the assistant wasn't found, the method returns ""
	 */
	@Override
	public String encontra(String atributo) {
		if(atributo.matches("[ ]*")) {
			return "Atributo inválido";
		}
		
		Auxiliar auxiliar;
		
		if(atributo.matches("[0-9]{2,6}")) {
			auxiliar = auxiliarDAO.selecionarAuxiliarCoren(atributo);
		}else {
			auxiliar = auxiliarDAO.selecionarAuxiliarNome(atributo);
		}
		if(auxiliar != null) {
			return(auxiliar.getNome()+"%"+auxiliar.getSex()+"%"+auxiliar.getCoren()
					+"%"+auxiliar.getNat()+"%"+auxiliar.getBthDate()+"%"
					+auxiliar.getAdmDate()+"%"+auxiliar.getGradDate());
		}else {
			return "";
		}
	}
	
	/**
	 * This method modify an existent assistant by calling the 'alteraFuncionario' method from class's father {@link CadastroFuncionario}.
	 * @param coren - The assistant's COREN
	 * @param atributo - An assistant's attribute (Nome, COREN, Sexo...)
	 * @param valor - The value to subscribe the old attribute value
	 * @return A message indicating an error or a success.
	 */
	@Override
	public String altera(String coren, String atributo, String valor) {
		Auxiliar a = auxiliarDAO.selecionarAuxiliarCoren(coren);
		boolean altera = false;
		if(a == null) {
			return("Auxiliar não cadastrado");
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
				if(Helper.gradAfterAdm(a.getGradDate(), valor))
					return("ERRO! Inconsistencia de datas: Formatura posterior a admissão!");
				altera = true;
				break;
			case "DtFormatura":
				if(Helper.gradAfterAdm(valor, a.getAdmDate()))
					return("ERRO! Inconsistencia de datas: Formatura posterior a admissão!");
				altera = true;
				break;
			default:
				return("Atributo inválido!");
		}
		if(altera) {
			auxiliarDAO.alterarAuxiliar(coren, atributo, valor);
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
		Auxiliar auxiliar = auxiliarDAO.selecionarAuxiliarCoren(coren);
		if(auxiliar != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void inserir(Auxiliar auxiliar) {
		auxiliarDAO.inserirAuxiliar(auxiliar);
	}
}

