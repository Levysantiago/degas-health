package cadastro;

import java.util.ArrayList;

import bancodados.MedicoDAO;
import interfaces.Gerenciavel;
import modelo.Medico;
import util.Helper;

/**
 * This class aims to offer methods to allow the management of doctors ({@link Medico}). 
 * With this class it's possible to create, read and update doctors from a list.
 * This class extends the {@link CadastroFuncionario} that generalizes methods to be used
 * by another siblings classes like {@link CadastroEnfermeiro} e {@link CadastroAuxiliar}.
 *
 */

public class CadastroMedico implements Gerenciavel<Medico>{
	
	//private ArrayList<Medico> listaMedicos;
	private MedicoDAO medicoDAO;
	
	public CadastroMedico() {
		//listaMedicos = new ArrayList<Medico>();
		medicoDAO = new MedicoDAO();
	}
	
	/**
	 * This method registers a new doctor by calling the 'novo' method from class's father {@link CadastroFuncionario}.
	 * @param nome - The doctor's name
	 * @param crm - The doctor's CRM
	 * @param sex - The doctor's gender
	 * @param nat - The doctor's nationality
	 * @param bthDate - The doctor's birth date
	 * @param gradDate - The doctor's graduation date
	 * @param admDate - The doctor's admission date
	 * 
	 * @return A message indicating an error or success to create a new doctor
	 */
	public String novo(String nome, String sex, String crm, String nat, String bthDate, String admDate, String gradDate) {
		if(!crm.matches("[0-9]{2,6}")) {
			return "Identificador inválido";
		}
		// Checks if the doctor already exists
		if (existe(crm)) {
			return("ERRO! CRM Já existente!");			
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
		
		// Instantiates a new Doctor
		Medico medico = new Medico(nome, sex, crm, nat, bthDate, admDate, gradDate);
		
		// After all tests, adds the doctor to the database
		inserir(medico);
		
		return "Medico inserido!";
	}

	/**
	 * This method finds a doctor in doctors list by calling the 'encontraFuncionario' method from class's father {@link CadastroFuncionario}.
	 * @param atributo - The name or CRM of a doctor
	 * @return A string containing all information of the doctor found.<br>
	 * 		If the doctor wasn't found, the method returns ""
	 */
	@Override
	public String encontra(String atributo) {
		if(atributo.matches("[ ]*")) {
			return "Atributo inválido";
		}
		Medico medico;
		
		if(atributo.matches("[0-9]{2,6}")) {
			medico = medicoDAO.selecionarMedicoCrm(atributo);
		}else {
			medico = medicoDAO.selecionarMedicoNome(atributo);
		}
		if(medico != null) {
			return(medico.getNome()+"%"+medico.getSex()+"%"+medico.getCrm()
			+"%"+medico.getNat()+"%"+medico.getBthDate()+"%"+medico.getAdmDate()+"%"+medico.getGradDate());
		}else {
			return "Medico não cadastrado";
		}
	}
	
	/**
	 * This method modify an existent doctor by calling the 'alteraFuncionario' method from class's father {@link CadastroFuncionario}.
	 * @param crm - The doctor's CRM
	 * @param atributo - A doctor's attribute (Nome, CRM, Sexo...)
	 * @param valor - The value to subscribe the old attribute value
	 * @return A message indicating an error or a success.
	 */
	@Override
	public String altera(String crm, String atributo, String valor) {
		boolean altera = false;
		Medico m = medicoDAO.selecionarMedicoCrm(crm);
		if(m == null) {
			return("Médico não cadastrado");
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
				if(Helper.gradAfterAdm(m.getGradDate(), valor))
					return("ERRO! Inconsistencia de datas: Formatura posterior a admissão!");
				altera = true;
				break;
			case "DtFormatura":
				if(Helper.gradAfterAdm(valor, m.getAdmDate()))
					return("ERRO! Inconsistencia de datas: Formatura posterior a admissão!");
				altera = true;
				break;
			default:
				return("Atributo inválido!");
		}
		if(altera) {
			medicoDAO.alterarMedico(crm, atributo, valor);
			return("Alteracao executada com sucesso!");			
		}
		return "";
	}
	
	/**
	 * This method checks if the functionary already exists
	 * @param ident - The functionary's identification
	 * @return <code>true</code>: If the functionary already exists<br>
	 * 		   <code>false</code>: If the functionary don't exists yet
	 */
	@Override
	public boolean existe(String crm) {
		Medico medico = medicoDAO.selecionarMedicoCrm(crm);
		if(medico != null) {
			return true;
		}
		return false;
		
		/*for (Medico m:listaMedicos)
			if (ident.equals(m.getCrm()))
				return true;
		return false;*/
	}
	
	@Override
	public void inserir(Medico medico) {
		medicoDAO.inserirMedico(medico);
	}
}
