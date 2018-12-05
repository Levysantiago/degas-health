package cadastro;

import bancodados.EspecialidadeDAO;
import interfaces.Gerenciavel;
import modelo.Especialidade;

public class CadastroEspecialidade implements Gerenciavel<Especialidade>{
private EspecialidadeDAO especialidadeDAO;
	
	public CadastroEspecialidade(){
		especialidadeDAO = new EspecialidadeDAO();
	}
	
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

	@Override
	public boolean existe(String codigo) {
		Especialidade especialidade = especialidadeDAO.selecionarEspecialidadeCodigo(codigo);
		if(especialidade != null) {
			return true;
		}
		return false;
	}
	
	public boolean descricaoExiste(String descricao) {
		Especialidade especialidade = especialidadeDAO.selecionarEspecialidadeDescricao(descricao);
		if(especialidade != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void inserir(Especialidade especialidade) {
		especialidadeDAO.inserirEspecialidade(especialidade);
	}
}
