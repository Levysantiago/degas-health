package cadastro;

import java.util.ArrayList;

import bancodados.AuxiliarDAO;
import interfaces.Gerenciavel;
import modelo.Auxiliar;
import util.Helper;

/**
 * Esta classe objetiva ofertar metodos para permitir o gerenciamento de auxiliares ({@link Auxiliar}).
 * Com essa classe é possível criar, ler, e atualizar auxiliares no banco de dados.
 * Esta classe implementa a interface {@link Gerenciavel} que abstrai métodos essenciais.
 */
public class CadastroAuxiliar implements Gerenciavel<Auxiliar>{
	private AuxiliarDAO auxiliarDAO;
	
	/**
	 * Este é o construtor da classe, ele simplesmente instancia um {@link AuxiliarDAO} para
	 * fazer requisições ao banco de dados.
	 */
	public CadastroAuxiliar() {
		auxiliarDAO = new AuxiliarDAO();
	}
	
	/**
	 * Este método recebe e valida os atributos de um novo auxiliar para então inserí-lo
	 * @param nome - O nome do auxiliar
	 * @param sex - Sexo do auxiliar
	 * @param coren - Coren do auxiliar
	 * @param nat - Nationalidade
	 * @param bthDate - Data de nascimento
	 * @param admDate - Data de admissão
	 * @param gradDate - Data de formatura
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção do auxiliar no banco
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
		
		// Instantiates a new Auxiliar
		Auxiliar auxiliar = new Auxiliar(nome, sex, coren, nat, bthDate, admDate, gradDate);
		
		// After all tests, adds the assistent to the database
		inserir(auxiliar);
		
		return "Auxiliar/Técnico inserido!";
	}

	/**
	 * Este método faz uma requisição à classe DAO para encontrar um auxiliar no banco de dados.
	 * Este método é uma implementação método "encontra" da interface {@link Gerenciavel}.
	 * @param atributo - O nome ou Coren do auxiliar
	 * @return Uma String contendo toda a informação do auxiliar encontrado
	 * 		Se o auxiliar não foi encontrado, o método retorna "" (Uma String vazia)
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
	 * Este método faz uma requisição à classe DAO para alterar um registro do auxiliar no banco de dados.
	 * Ele implementa o método "altera" da interface {@link Gerenciavel}.
	 * @param crm - O Coren do auxiliar
	 * @param atributo - O campo o qual o dado será alterado (Nome, Coren, Sexo...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
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
	 * Este método realiza uma requisição à classe DAO para verificar no banco se um auxiliar existe.
	 * Ele é a implementação do método "existe" da interface {@link Gerenciavel}.
	 * @param crm - O Coren do auxiliar
	 * @return <code>true</code>: Se o auxiliar existe<br>
	 * 		   <code>false</code>: Se o auxiliar não existe
	 */
	@Override
	public boolean existe(String coren) {
		Auxiliar auxiliar = auxiliarDAO.selecionarAuxiliarCoren(coren);
		if(auxiliar != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para inserir um novo auxiliar no banco de dados.
	 * @param auxiliar - O novo auxiliar a ser inserido ({@link Axiliar})
	 */
	@Override
	public void inserir(Auxiliar auxiliar) {
		auxiliarDAO.inserirAuxiliar(auxiliar);
	}
}

