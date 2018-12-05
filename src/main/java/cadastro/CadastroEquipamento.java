package cadastro;

import bancodados.EquipamentoDAO;
import interfaces.Gerenciavel;
import modelo.Equipamento;
import modelo.Tombo;
import util.Helper;

public class CadastroEquipamento implements Gerenciavel<Equipamento>{
	private EquipamentoDAO equipamentoDAO;
	
	public CadastroEquipamento(){
		equipamentoDAO = new EquipamentoDAO();
	}
	
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
	
	@Override
	public boolean existe(String codigo) {
		Equipamento equipamento = equipamentoDAO.selecionarEquipamentoCodigo(codigo);
		if(equipamento != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void inserir(Equipamento equipamento) {
		equipamentoDAO.inserirEquipamento(equipamento);
	}
	
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
	
	public boolean descricaoExiste(String descricao) {
		Equipamento equipamento = equipamentoDAO.selecionarEquipamentoDescricao(descricao);
		if(equipamento != null) {
			return true;
		}
		return false;
	}
}