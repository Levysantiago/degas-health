package cadastro;

import java.util.ArrayList;

import bancodados.ProcedimentoDAO;
import interfaces.Gerenciavel;
import modelo.Equipamento;
import modelo.Material;
import modelo.Procedimento;
import util.Helper;

public class CadastroProcedimento implements Gerenciavel<Procedimento>{
	private ProcedimentoDAO procedimentoDAO;
	
	public CadastroProcedimento(){
		procedimentoDAO = new ProcedimentoDAO();
	}
	
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

	@Override
	public boolean existe(String codigo) {
		Procedimento procedimento = procedimentoDAO.selecionarProcedimentoCodigo(codigo);
		if(procedimento != null) {
			return true;
		}
		return false;
	}
	
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
	
	public boolean descricaoExiste(String descricao) {
		Procedimento procedimento = procedimentoDAO.selecionarProcedimentoDescricao(descricao);
		if(procedimento != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void inserir(Procedimento procedimento) {
		procedimentoDAO.inserirProcedimento(procedimento);
	}
}