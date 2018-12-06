package cadastro;

import bancodados.EquipamentoDAO;
import interfaces.Gerenciavel;
import modelo.Equipamento;
import modelo.Tombo;
import util.Helper;

/**
 * Esta classe objetiva ofertar metodos para permitir o gerenciamento de equipamentos ({@link Equipamento}).
 * Com essa classe é possível criar, ler, e atualizar equipamentos no banco de dados.
 * Esta classe implementa a interface {@link Gerenciavel} que abstrai métodos essenciais.
 */
public class CadastroEquipamento implements Gerenciavel<Equipamento>{
	private EquipamentoDAO equipamentoDAO;
	
	/**
	 * Este é o construtor da classe, ele simplesmente instancia um {@link EquipamentoDAO} para
	 * fazer requisições ao banco de dados.
	 */
	public CadastroEquipamento(){
		equipamentoDAO = new EquipamentoDAO();
	}
	
	/**
	 * Este método recebe e valida os atributos de um novo equipamento para então inserí-lo
	 * @param codigo - O código do equipamento
	 * @param descricao - A descrição do mesmo
	 * @param valor - O preço do equipamento
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção do equipamento no banco
	 */
	public String novo(String codigo, String descricao, String valor) {
		if(!descricao.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
			return "Campo Descricao inválido";
		}
		if(!codigo.matches("[0-9]{2,4}")) {
			return "Codigo inválido";
		}
		if (existe(codigo))
			return  "Código de Equipamento Ja Cadastrado";
		
		else if (descricaoExiste(descricao))
			return "Descrição de Equipamento Ja Cadastrado";
		
		else if (!Helper.valorValido(valor))
			return "Erro: valor de custo inválido";
		
		Equipamento equipamento = new Equipamento(codigo, descricao, valor);
		inserir(equipamento);
		
		return "Equipamento cadastrado com sucesso";
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para encontrar um equipamento no banco de dados.
	 * Este método é uma implementação método "encontra" da interface {@link Gerenciavel}.
	 * @param atributo - A descrição ou Valor do equipamento
	 * @return Uma String contendo toda a informação do equipamento encontrado
	 * 		Se o equipamento não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	@Override
	public String encontra(String atributo) {
		if(atributo.matches("[ ]*")) {
			return "Atributo inválido";
		}
		
		Equipamento equipamento;
		
		if(atributo.matches("[0-9]{2,8}")) {
			equipamento = equipamentoDAO.selecionarEquipamentoCodigo(atributo);
		}else {
			equipamento = equipamentoDAO.selecionarEquipamentoDescricao(atributo);
		}
		if(equipamento != null) {
			return(equipamento.getCodigo()+"%"+equipamento.getDescricao()+"%"+equipamento.getValor());
		}else {
			return("Equipamento não cadastrado");
		}
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para alterar um registro do equipamento no banco de dados.
	 * Ele implementa o método "altera" da interface {@link Gerenciavel}.
	 * @param codigo - O Codigo do equipamento
	 * @param atributo - O campo o qual o dado será alterado (Descrição, Valor...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	@Override
	public String altera(String codigo, String atributo, String valor) {
		boolean altera = false;
		Equipamento e = equipamentoDAO.selecionarEquipamentoCodigo(codigo);
		if(e == null) {
			return("Equipamento não cadastrado");
		}
		
		switch (atributo) {
			case "Valor":{
				if(Helper.valorValido(valor)) {
					return "Erro: valor de custo inválido";
				}
				altera = true;
				break;
			}
			case "Descricao":{
				if(!valor.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
					return "Campo Descricao inválido";
				}
				altera = true;
				break;
			}
			default:{
				return("Atributo inválido!");
			}
		}
		if(altera) {
			equipamentoDAO.alterarEquipamento(codigo, atributo, valor);
			return "Equipamento alterado com sucesso";
		}
		return "";
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para verificar no banco se um equipamento existe.
	 * Ele é a implementação do método "existe" da interface {@link Gerenciavel}.
	 * @param codigo - O Codigo do equipamento
	 * @return <code>true</code>: Se o equipamento existe<br>
	 * 		   <code>false</code>: Se o equipamento não existe
	 */
	@Override
	public boolean existe(String codigo) {
		Equipamento equipamento = equipamentoDAO.selecionarEquipamentoCodigo(codigo);
		if(equipamento != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para inserir um novo equipamento no banco de dados.
	 * @param equipamento - O novo equipamento a ser inserido ({@link Equipamento})
	 */
	@Override
	public void inserir(Equipamento equipamento) {
		equipamentoDAO.inserirEquipamento(equipamento);
	}
	
	/**
	 * Este método realiza requisições à classe DAO para tombar um equipamento e atualizar essa informação
	 * no banco.
	 * @param codigoEquipamento - O código do equipamento (ver {@link Equipamento})
	 * @param codigoTombo - O código do tombo (ver {@link Tombo})
	 * @return Uma String informando o sucesso ou não do cadastro do tombo do equipamento.
	 */
	public String tombaEquipamento(String codigoEquipamento, String codigoTombo){
		if(codigoTombo.length() > 8 || codigoTombo.length() < 0) {
			return "Codigo de tombo inválido";
		}
		if(!codigoEquipamento.matches("[0-9]{2,4}")) {
			return "Codigo de equipamento inválido";
		}
		Tombo tombo = new Tombo(codigoEquipamento, codigoTombo);
		if(equipamentoDAO.existeTombo(tombo)) {
			return "Erro: Tombo já existente";
		}
		if(equipamentoDAO.tombaEquipamento(tombo)) {
			return "Equipamento tombado com sucesso"; 
		}
		return "ERRO ao tombar equipamento";
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para verificar se alguma descrição já existe no banco.
	 * @param descricao - A descrição de equipamento
	 * @return <code>true</code> Caso esta descrição exista no banco.<br>
	 * 			<code>false</code> Caso esta descrição não exista no banco.
	 */
	public boolean descricaoExiste(String descricao) {
		Equipamento equipamento = equipamentoDAO.selecionarEquipamentoDescricao(descricao);
		if(equipamento != null) {
			return true;
		}
		return false;
	}
}