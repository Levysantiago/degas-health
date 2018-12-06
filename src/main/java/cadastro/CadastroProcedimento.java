package cadastro;

import java.util.ArrayList;

import bancodados.ProcedimentoDAO;
import interfaces.Gerenciavel;
import modelo.Equipamento;
import modelo.Material;
import modelo.Procedimento;
import util.Helper;

/**
 * Esta classe objetiva ofertar metodos para permitir o gerenciamento de procedimentos ({@link Procedimento}).
 * Com essa classe é possível criar, ler, e atualizar procedimentos no banco de dados.
 * Esta classe implementa a interface {@link Gerenciavel} que abstrai métodos essenciais.
 */
public class CadastroProcedimento implements Gerenciavel<Procedimento>{
	private ProcedimentoDAO procedimentoDAO;
	
	/**
	 * Este é o construtor da classe, ele simplesmente instancia um {@link ProcedimentoDAO} para
	 * fazer requisições ao banco de dados.
	 */
	public CadastroProcedimento(){
		procedimentoDAO = new ProcedimentoDAO();
	}
	
	/**
	 * Este método recebe e valida os atributos de um novo procedimento para então inserí-lo
	 * @param codigo - O código do procedimento
	 * @param descricao - A descrição do mesmo
	 * @param custo - O custo do procedimento
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção do procedimento no banco
	 */
	public String novo(String codigo, String descricao, String custo){
		if(!descricao.matches("([a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ]+[ ]*)+")) {
			return "Campo Descricao inválido";
		}
		if(!codigo.matches("[0-9]{2,8}")) {
			return "Codigo inválido";
		}
		if (existe(codigo))
			return  "Código de Procedimento Ja Cadastrado";
		
		else if (descricaoExiste(descricao))
			return "Descrição de Procedimento Ja Cadastrado";
		
		else if (!Helper.valorValido(custo))
			return "Erro: valor de custo inválido";
		
		Procedimento procedimento = new Procedimento(codigo, descricao, custo);
		inserir(procedimento);
		
		return "Procedimento Incluido com Sucesso";
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para encontrar um procedimento no banco de dados.
	 * Este método é uma implementação método "encontra" da interface {@link Gerenciavel}.
	 * @param atributo - A descrição ou custo do procedimento
	 * @return Uma String contendo toda a informação do procedimento encontrado
	 * 		Se o enfermeiro não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	@Override
	public String encontra(String atributo) {
		if(atributo.matches("[ ]*")) {
			return "Atributo inválido";
		}
		
		Procedimento procedimento;
		
		if(atributo.matches("[0-9]{2,8}")) {
			procedimento = procedimentoDAO.selecionarProcedimentoCodigo(atributo);
		}else {
			procedimento = procedimentoDAO.selecionarProcedimentoDescricao(atributo);
		}
		if(procedimento != null) {
			return(procedimento.getCodigo()+"%"+procedimento.getDescricao()+"%"+procedimento.getCusto());
		}else {
			return("Procedimento não cadastrado");
		}
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para alterar um registro do procedimento no banco de dados.
	 * Ele implementa o método "altera" da interface {@link Gerenciavel}.
	 * @param codigo - O Código do procedimento
	 * @param atributo - O campo o qual o dado será alterado (Descrição, Custo...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	@Override
	public String altera(String codigo, String atributo, String valor) {
		boolean altera = false;
		Procedimento p = procedimentoDAO.selecionarProcedimentoCodigo(codigo);
		if(p == null) {
			return("Procedimento não cadastrado");
		}
		
		switch (atributo) {
			case "Valor":{
				if(!Helper.valorValido(valor)) {
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
			procedimentoDAO.alterarProcedimento(codigo, atributo, valor);
			return "Procedimento alterado com sucesso";
		}
		return "";
	}

	/**
	 * Este método realiza uma requisição à classe DAO para verificar no banco se um procedimento existe.
	 * Ele é a implementação do método "existe" da interface {@link Gerenciavel}.
	 * @param codigo - O código do procedimento
	 * @return <code>true</code>: Se o procedimento existe<br>
	 * 		   <code>false</code>: Se o procedimento não existe
	 */
	@Override
	public boolean existe(String codigo) {
		Procedimento procedimento = procedimentoDAO.selecionarProcedimentoCodigo(codigo);
		if(procedimento != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para inserir um novo procedimento no banco de dados.
	 * @param procedimento - O novo procedimento a ser inserido ({@link Procedimento})
	 */
	@Override
	public void inserir(Procedimento procedimento) {
		procedimentoDAO.inserirProcedimento(procedimento);
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para adicionar um novo material em um procedimento
	 * no banco de dados.
	 * @param codProcedimento - O código do procedimento (ver {@link Procedimento})
	 * @param codMaterial - O código do material (ver {@link Material})
	 * @return Uma String informando o sucesso ou não na inserção do material.
	 */
	public String addMaterial(String codProcedimento, String codMaterial) {
		if(!codProcedimento.matches("[0-9]{2,8}")) {
			return "Codigo Procedimento inválido";
		}
		if(!codMaterial.matches("[0-9]{2,5}")) {
			return "Codigo Material inválido";
		}
		if(procedimentoDAO.materialProcedimento(codProcedimento, codMaterial)) {
			return "Material Incluido com sucesso no procedimento";
		}
		
		return "Erro ao tentar adicionar Material";
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para adicionar um novo equipamento em um procedimento
	 * no banco de dados.
	 * @param codProcedimento - O código do procedimento (ver {@link Procedimento})
	 * @param codEquipamento - O código do equipamento (ver {@link Equipamento})
	 * @return Uma String informando o sucesso ou não na inserção do equipamento.
	 */
	public String addEquipamento(String codProcedimento, String codEquipamento) {
		if(!codProcedimento.matches("[0-9]{2,8}")) {
			return "Codigo Procedimento inválido";
		}
		if(!codEquipamento.matches("[0-9]{2,5}")) {
			return "Codigo Equipamento inválido";
		}
		if(procedimentoDAO.equipamentoProcedimento(codProcedimento, codEquipamento)) {
			return "Equipamento Incluido com sucesso no procedimento";
		}
		
		return "Erro ao tentar adicionar Equipamento";
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para recuperar a lista de materiais
	 * cadastrados em um procedimento específico.
	 * @param codProcedimento - O código do procedimento (ver {@link Procedimento})
	 * @return Caso existam materiais, é retornada uma String contendo as descrições do 
	 * mesmos. Caso contrário é retornado "" (uma String vazia).
	 */
	public String listMateriais(String codProcedimento) {
		String ret = "";
		ArrayList<Material> materiais = procedimentoDAO.selecionarMaterialProcedimento(codProcedimento);
		if(materiais.isEmpty()) {
			return ret;
		}
		for(Material m : materiais) {
			ret += m.getDescricao();
			ret += "%";
		}
		ret = ret.substring(0, ret.length()-1);
		
		return ret;
	}
	
	/**
	 * Este método realiza uma requisição à classe DAO para recuperar a lista de equipamentos
	 * cadastrados em um procedimento específico.
	 * @param codProcedimento - O código do procedimento (ver {@link Procedimento})
	 * @return Caso existam equipamentos, é retornada uma String contendo as descrições do 
	 * mesmos. Caso contrário é retornado "" (uma String vazia).
	 */
	public String listEquipamentos(String codProcedimento) {
		String ret = "";
		ArrayList<Equipamento> equipamentos = procedimentoDAO.selecionarEquipamentoProcedimento(codProcedimento);
		if(equipamentos.isEmpty()) {
			return ret;
		}
		for(Equipamento e : equipamentos) {
			ret += e.getDescricao();
			ret += "%";
		}
		ret = ret.substring(0, ret.length()-1);
		
		return ret;
	}
	
	/**
	 * Este método faz uma requisição à classe DAO para verificar se alguma descrição já existe no banco.
	 * @param descricao - A descrição do procedimento
	 * @return <code>true</code> Caso este procedimento exista no banco.<br>
	 * 			<code>false</code> Caso este procedimento não exista no banco.
	 */
	public boolean descricaoExiste(String descricao) {
		Procedimento procedimento = procedimentoDAO.selecionarProcedimentoDescricao(descricao);
		if(procedimento != null) {
			return true;
		}
		return false;
	}
	
}