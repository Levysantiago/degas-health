package cadastro;

import java.util.ArrayList;

import bancodados.EnfermeiroDAO;
import interfaces.Gerenciavel;
import modelo.Enfermeiro;
import util.Helper;

/**
 * Esta classe objetiva ofertar metodos para permitir o gerenciamento de enfermeiros ({@link Enfermeiro}).
 * Com essa classe é possível criar, ler, e atualizar enfermeiros no banco de dados.
 * Esta classe implementa a interface {@link Gerenciavel} que abstrai métodos essenciais.
 */
public class CadastroEnfermeiro implements Gerenciavel<Enfermeiro>{
	private EnfermeiroDAO enfermeiroDAO;
	
	/**
	 * Este é o construtor da classe, ele simplesmente instancia um {@link EnfermeiroDAO} para
	 * fazer requisições ao banco de dados.
	 */
	public CadastroEnfermeiro() {
		enfermeiroDAO = new EnfermeiroDAO();
	}
	
	/**
	 * Este método recebe e valida os atributos de um novo enfermeiro para então inserí-lo
	 * @param nome - O nome do enfermeiro
	 * @param sex - Sexo do enfermeiro
	 * @param coren - Coren do enfermeiro
	 * @param nat - Nationalidade
	 * @param bthDate - Data de nascimento
	 * @param admDate - Data de admissão
	 * @param gradDate - Data de formatura
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção do enfermeiro no banco
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
		
		// Instantiates a new Nurse
		Enfermeiro enfermeiro = new Enfermeiro(nome, sex, coren, nat, bthDate, admDate, gradDate);
		
		// After all tests, adds the nurse to the database
		inserir(enfermeiro);
		
		return "Enfermeiro inserido!";
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para encontrar um enfermeiro no banco de dados.
	 * Este método é uma implementação método "encontra" da interface {@link Gerenciavel}.
	 * @param atributo - O nome ou Coren do enfermeiro
	 * @return Uma String contendo toda a informação do enfermeiro encontrado
	 * 		Se o enfermeiro não foi encontrado, o método retorna "" (Uma String vazia)
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
	 * Este método faz uma requisição à classe DAO para alterar um registro do enfermeiro no banco de dados.
	 * Ele implementa o método "altera" da interface {@link Gerenciavel}.
	 * @param crm - O Coren do enfermeiro
	 * @param atributo - O campo o qual o dado será alterado (Nome, Coren, Sexo...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
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
	 * Este método realiza uma requisição à classe DAO para verificar no banco se um enfermeiro existe.
	 * Ele é a implementação do método "existe" da interface {@link Gerenciavel}.
	 * @param crm - O Coren do enfermeiro
	 * @return <code>true</code>: Se o enfermeiro existe<br>
	 * 		   <code>false</code>: Se o enfermeiro não existe
	 */
	@Override
	public boolean existe(String coren) {
		Enfermeiro enfermeiro = enfermeiroDAO.selecionarEnfermeiroCoren(coren);
		if(enfermeiro != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para inserir um novo enfermeiro no banco de dados.
	 * @param enfermeiro - O novo enfermeiro a ser inserido ({@link Enfermeiro})
	 */
	@Override
	public void inserir(Enfermeiro enfermeiro) {
		enfermeiroDAO.inserirEnfermeiro(enfermeiro);
	}
}
