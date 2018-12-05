package cadastro;

import bancodados.MaterialDAO;
import interfaces.Gerenciavel;
import modelo.Material;
import util.Helper;

public class CadastroMaterial implements Gerenciavel<Material>{
private MaterialDAO materialDAO;
	
	public CadastroMaterial(){
		materialDAO = new MaterialDAO();
	}
	
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

	@Override
	public boolean existe(String codigo) {
		Material material = materialDAO.selecionarMaterialCodigo(codigo);
		if(material != null) {
			return true;
		}
		return false;
	}
	
	public boolean descricaoExiste(String descricao) {
		Material material = materialDAO.selecionarMaterialDescricao(descricao);
		if(material != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void inserir(Material material) {
		materialDAO.inserirMaterial(material);
	}
}