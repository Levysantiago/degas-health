package cadastro;

import java.util.ArrayList;

import bancodados.MedicoDAO;
import interfaces.Gerenciavel;
import modelo.Medico;
import util.Helper;

/**
 * Esta classe objetiva ofertar metodos para permitir o gerenciamento de médicos ({@link Medico}).
 * Com essa classe é possível criar, ler, e atualizar médicos no banco de dados.
 * Esta classe implementa a interface {@link Gerenciavel} que abstrai métodos essenciais.
 *
 */

public class CadastroMedico implements Gerenciavel<Medico>{
	private MedicoDAO medicoDAO;
	
	/**
	 * Este é o construtor da classe, ele simplesmente instancia um {@link MedicoDAO} para
	 * fazer requisições ao banco de dados.
	 */
	public CadastroMedico() {
		medicoDAO = new MedicoDAO();
	}
	
	/**
	 * Este método recebe e valida os atributos de um novo médico para então inserí-lo
	 * no banco de dados. 
	 * @param nome - O nome do médico
	 * @param sex - Sexo do médico
	 * @param crm - CRM do médico
	 * @param nat - Nationalidade
	 * @param bthDate - Data de nascimento
	 * @param admDate - Data de admissão
	 * @param gradDate - Data de formatura
	 * @return Uma mensagem informando o sucesso ou não da inserção do médico no banco
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
	 * Este método faz uma requisição à classe DAO para encontrar um médico no banco de dados.
	 * Este método é uma implementação método "encontra" da interface {@link Gerenciavel}.
	 * @param atributo - O nome ou CRM do médico
	 * @return Uma String contendo toda a informação do médico encontrado
	 * 		Se o médico não foi encontrado, o método retorna "" (Uma String vazia)
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
	 * Este método faz uma requisição à classe DAO para alterar um registro do médico no banco de dados.
	 * Ele implementa o método "altera" da interface {@link Gerenciavel}.
	 * @param crm - O CRM do médico
	 * @param atributo - O campo o qual o dado será alterado (Nome, CRM, Sexo...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
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
	 * Este método realiza uma requisição à classe DAO para verificar no banco se um médico existe.
	 * Ele é a implementação do método "existe" da interface {@link Gerenciavel}.
	 * @param crm - O CRM do médico
	 * @return <code>true</code>: Se o médico existe<br>
	 * 		   <code>false</code>: Se o médico não existe
	 */
	@Override
	public boolean existe(String crm) {
		Medico medico = medicoDAO.selecionarMedicoCrm(crm);
		if(medico != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para inserir um novo médico no banco de dados.
	 * @param medico - O novo médico a ser inserido ({@link Medico})
	 */
	@Override
	public void inserir(Medico medico) {
		medicoDAO.inserirMedico(medico);
	}
}
