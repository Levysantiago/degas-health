package cadastro;

import bancodados.MaterialDAO;
import interfaces.Gerenciavel;
import modelo.Material;
import util.Helper;

/**
 * Esta classe objetiva ofertar metodos para permitir o gerenciamento de materiais ({@link Material}).
 * Com essa classe é possível criar, ler, e atualizar materiais no banco de dados.
 * Esta classe implementa a interface {@link Gerenciavel} que abstrai métodos essenciais.
 */
public class CadastroMaterial implements Gerenciavel<Material>{
private MaterialDAO materialDAO;
	
	/**
	 * Este é o construtor da classe, ele simplesmente instancia um {@link MaterialDAO} para
	 * fazer requisições ao banco de dados.
	 */
	public CadastroMaterial(){
		materialDAO = new MaterialDAO();
	}
	
	/**
	 * Este método recebe e valida os atributos de um novo material para então inserí-lo
	 * @param codigo - O código do material
	 * @param descricao - A descrição do mesmo
	 * @param valor - O preço do material
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção do material no banco
	 */
	public String novo(String codigo, String descricao, String custo){
		if(!descricao.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
			return "Campo Descricao inválido";
		}
		if(!codigo.matches("[0-9]{2,5}")) {
			return "Codigo inválido";
		}
		if (existe(codigo))
			return  "Código de Material Ja Cadastrado";
		
		else if (descricaoExiste(descricao))
			return "Descrição de Material Ja Cadastrado";
		
		else if (!Helper.valorValido(custo))
			return "Erro: valor de custo inválido";
		
		Material material = new Material(codigo, descricao, custo);
		inserir(material);
		
		return "Material Cadastrado com sucesso";
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para encontrar um material no banco de dados.
	 * Este método é uma implementação método "encontra" da interface {@link Gerenciavel}.
	 * @param atributo - A descrição ou valor do material
	 * @return Uma String contendo toda a informação do material encontrado
	 * 		Se o material não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	@Override
	public String encontra(String atributo) {
		if(atributo.matches("[ ]*")) {
			return "Atributo inválido";
		}
		
		Material material;
		
		if(atributo.matches("[0-9]{2,8}")) {
			material = materialDAO.selecionarMaterialCodigo(atributo);
		}else {
			material = materialDAO.selecionarMaterialDescricao(atributo);
		}
		if(material != null) {
			return(material.getCodigo()+"%"+material.getDescricao()+"%"+material.getValor());
		}else {
			return("Material não cadastrado");
		}
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para alterar um registro do material no banco de dados.
	 * Ele implementa o método "altera" da interface {@link Gerenciavel}.
	 * @param codigo - O Código do material
	 * @param atributo - O campo o qual o dado será alterado (Descrição, Valor...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	@Override
	public String altera(String codigo, String atributo, String valor) {
		boolean altera = false;
		Material m = materialDAO.selecionarMaterialCodigo(codigo);
		if(m == null) {
			return("Material não cadastrado");
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
			materialDAO.alterarMaterial(codigo, atributo, valor);
			return "Material alterado com sucesso";
		}
		return "";
	}

	/**
	 * Este método realiza uma requisição à classe DAO para verificar no banco se um material existe.
	 * Ele é a implementação do método "existe" da interface {@link Gerenciavel}.
	 * @param codigo - O Código do material
	 * @return <code>true</code>: Se o material existe<br>
	 * 		   <code>false</code>: Se o material não existe
	 */
	@Override
	public boolean existe(String codigo) {
		Material material = materialDAO.selecionarMaterialCodigo(codigo);
		if(material != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para inserir um novo material no banco de dados.
	 * @param material - O novo material a ser inserido ({@link Material})
	 */
	@Override
	public void inserir(Material material) {
		materialDAO.inserirMaterial(material);
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para verificar se alguma descrição já existe no banco.
	 * @param descricao - A descrição de material
	 * @return <code>true</code> Caso este material exista no banco.<br>
	 * 			<code>false</code> Caso este material não exista no banco.
	 */
	public boolean descricaoExiste(String descricao) {
		Material material = materialDAO.selecionarMaterialDescricao(descricao);
		if(material != null) {
			return true;
		}
		return false;
	}
	
}