package cadastro;

import bancodados.EspecialidadeDAO;
import interfaces.Gerenciavel;
import modelo.Especialidade;

/**
 * Esta classe objetiva ofertar metodos para permitir o gerenciamento de especialidades ({@link Especialidade}).
 * Com essa classe é possível criar, ler, e atualizar especialidades no banco de dados.
 * Esta classe implementa a interface {@link Gerenciavel} que abstrai métodos essenciais.
 */
public class CadastroEspecialidade implements Gerenciavel<Especialidade>{
private EspecialidadeDAO especialidadeDAO;
	
	/**
	 * Este é o construtor da classe, ele simplesmente instancia um {@link EspecialidadeDAO} para
	 * fazer requisições ao banco de dados.
	 */
	public CadastroEspecialidade(){
		especialidadeDAO = new EspecialidadeDAO();
	}
	
	/**
	 * Este método recebe e valida os atributos de uma nova especialidade para então inserí-la
	 * @param codigo - O código da especialidade
	 * @param descricao - A descrição desta
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção da especialidade no banco
	 */
	public String novo(String codigo, String descricao){
		if(!descricao.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
			return "Campo Descricao inválido";
		}
		if(!codigo.matches("[0-9]{2,4}")) {
			return "Codigo inválido";
		}
		if (existe(codigo))
			return  "Especialidade já cadastrada";
		
		else if (descricaoExiste(descricao))
			return "Especialidade já cadastrada";
		
		Especialidade especialidade = new Especialidade(codigo, descricao);
		inserir(especialidade);
		
		return "Especialidade Registrada com sucesso";
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para encontrar uma especialidade no banco de dados.
	 * Este método é uma implementação método "encontra" da interface {@link Gerenciavel}.
	 * @param atributo - A descrição da especialidade
	 * @return Uma String contendo toda a informação da especialidade encontrada
	 * 		Se a especialidade não foi encontrada, o método retorna "" (Uma String vazia)
	 */
	@Override
	public String encontra(String atributo) {
		if(atributo.matches("[ ]*")) {
			return "Atributo inválido";
		}
		
		Especialidade especialidade;
		
		if(atributo.matches("[0-9]{2,4}")) {
			especialidade = especialidadeDAO.selecionarEspecialidadeCodigo(atributo);
		}else {
			especialidade = especialidadeDAO.selecionarEspecialidadeDescricao(atributo);
		}
		if(especialidade != null) {
			return(especialidade.getCodigo()+"%"+especialidade.getDescricao());
		}else {
			return("Especialidade não cadastrada");
		}
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para alterar um registro da especialidade no banco de dados.
	 * Ele implementa o método "altera" da interface {@link Gerenciavel}.
	 * @param codigo - O codigo da especialidade
	 * @param atributo - O campo o qual o dado será alterado (Descrição...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	@Override
	public String altera(String codigo, String atributo, String valor) {
		boolean altera = false;
		Especialidade e = especialidadeDAO.selecionarEspecialidadeCodigo(codigo);
		if(e == null) {
			return("Especialidade não cadastrada");
		}
		
		switch (atributo) {
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
			especialidadeDAO.alterarEspecialidade(codigo, atributo, valor);
			return "Especialidade alterado com sucesso";
		}
		return "";
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para verificar no banco se uma especialidade existe.
	 * Ele é a implementação do método "existe" da interface {@link Gerenciavel}.
	 * @param codigo - O código da especialidade
	 * @return <code>true</code>: Se a especialidade existe<br>
	 * 		   <code>false</code>: Se a especialidade não existe
	 */
	@Override
	public boolean existe(String codigo) {
		Especialidade especialidade = especialidadeDAO.selecionarEspecialidadeCodigo(codigo);
		if(especialidade != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para inserir uma nova especialidade no banco de dados.
	 * @param especialidade - A nova especialidade a ser inserida ({@link Especialidade})
	 */
	@Override
	public void inserir(Especialidade especialidade) {
		especialidadeDAO.inserirEspecialidade(especialidade);
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para verificar se alguma descrição já existe no banco.
	 * @param descricao - A descrição da especialidade
	 * @return <code>true</code> Caso esta descrição exista no banco.<br>
	 * 			<code>false</code> Caso esta descrição não exista no banco.
	 */
	public boolean descricaoExiste(String descricao) {
		Especialidade especialidade = especialidadeDAO.selecionarEspecialidadeDescricao(descricao);
		if(especialidade != null) {
			return true;
		}
		return false;
	}
	
}
