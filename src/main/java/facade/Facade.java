package facade;

import cadastro.CadastroAuxiliar;
import cadastro.CadastroEnfermeiro;
import cadastro.CadastroEquipamento;
import cadastro.CadastroEspecialidade;
import cadastro.CadastroMaterial;
import cadastro.CadastroMedico;
import cadastro.CadastroProcedimento;
import modelo.Equipamento;
import modelo.Material;
import modelo.Procedimento;
import modelo.Tombo;

/**
 * Esta classe trabalha como um middleware. Ela irá receber todos os requests e redirecionar para
 * o método de classe correto.
 */
public class Facade {

	private CadastroMedico cadastroMedico;
	private CadastroEnfermeiro cadastroEnfermeiro;
	private CadastroAuxiliar cadastroAuxiliar;
	private static CadastroProcedimento cadastroProcedimento;
	private static CadastroEspecialidade cadastroEspecialidade;
	private static CadastroEquipamento cadastroEquipamento;
	private static CadastroMaterial cadastroMaterial;
	
	/**
	 * Este é o contrutor da {@link Facade}. Ele instancia as classes de cadastro {@link CadastroMedico},
	 * {@link CadastroEnfermeiro}, {@link CadastroAuxiliar}, {@link CadastroProcedimento},
	 * {@link CadastroEspecialidade}, {@link CadastroEquipamento} e {@link CadastroMaterial}.
	 */
	public Facade() {
		 cadastroMedico = new CadastroMedico();
		 cadastroEnfermeiro = new CadastroEnfermeiro();
		 cadastroAuxiliar = new CadastroAuxiliar();
		 cadastroProcedimento = new CadastroProcedimento();
		 cadastroEspecialidade = new CadastroEspecialidade();
		 cadastroEquipamento = new CadastroEquipamento();
		 cadastroMaterial = new CadastroMaterial();
	}

	/**
	 * Este método registra um novo médico redirecionando para {@link CadastroMedico}.
	 * @param nome - O nome do médico
	 * @param sex - Sexo do médico
	 * @param crm - CRM do médico
	 * @param nat - Nationalidade
	 * @param bthDate - Data de nascimento
	 * @param admDate - Data de admissão
	 * @param gradDate - Data de formatura
	 * @return Uma mensagem informando o sucesso ou não da inserção do médico no banco
	 */
	public String novoMedico(String nome, String sex, String crm, String nat, String bthDate, String admDate, String gradDate) {
		return cadastroMedico.novo(nome, sex, crm, nat, bthDate, admDate, gradDate);
	}
	
	/**
	 * Este método encontra um médico a partir do atributo recebido, redirecionando para {@link CadastroMedico}.
	 * @param atributo - O nome ou CRM do médico
	 * @return Uma String contendo toda a informação do médico encontrado
	 * 		Se o médico não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	public String encontraMedico(String atributo) {
		return cadastroMedico.encontra(atributo);
	}
	
	/**
	 * Este método modifica um médico existente redirecionando a requisição para {@link CadastroMedico}.
	 * @param crm - O CRM do médico
	 * @param atributo - O campo o qual o dado será alterado (Nome, CRM, Sexo...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	public String alteraMedico(String crm, String atributo, String valor) {
		return cadastroMedico.altera(crm, atributo, valor);
	}
	
	/**
	 * Este método registra um novo enfermeiro redirecionando a requisição para {@link CadastroEnfermeiro}.
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
	public String novoEnfermeiro(String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
		return cadastroEnfermeiro.novo(nome, sex, coren, nat, bthDate, admDate, gradDate);
	}
	
	/**
	 * Este método encontra um enfermeiro no banco redirecionando a requisição para {@link CadastroEnfermeiro}.
	 * @param atributo - O nome ou Coren do enfermeiro
	 * @return Uma String contendo toda a informação do enfermeiro encontrado
	 * 		Se o enfermeiro não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	public String encontraEnfermeiro(String atributo) {
		return cadastroEnfermeiro.encontra(atributo);
	}
	
	/**
	 * Este método modifica um enfermeiro existente redirecionando a requisição para {@link CadastroEnfermeiro}.
	 * @param crm - O Coren do enfermeiro
	 * @param atributo - O campo o qual o dado será alterado (Nome, Coren, Sexo...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	public String alteraEnfermeiro(String coren, String atributo, String valor) {
		return cadastroEnfermeiro.altera(coren, atributo, valor);
	}
	
	/**
	 * Este método registra um novo auxiliar redirecionando a requisição para {@link CadastroAuxiliar}.
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
	public String novoAuxiliar(String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
		return cadastroAuxiliar.novo(nome, sex, coren, nat, bthDate, admDate, gradDate);
	}
	
	/**
	 * Este método encontra um auxiliar no banco redirecionando a requisição para {@link CadastroAuxiliar}.
	 * @param atributo - O nome ou Coren do auxiliar
	 * @return Uma String contendo toda a informação do auxiliar encontrado
	 * 		Se o auxiliar não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	public String encontraAuxiliar(String atributo) {
		return cadastroAuxiliar.encontra(atributo);
	}
	
	/**
	 * Este método modifica um auxiliar existente redirecionando a requisição para {@link CadastroAuxiliar}.
	 * @param crm - O Coren do auxiliar
	 * @param atributo - O campo o qual o dado será alterado (Nome, Coren, Sexo...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	public String alteraAuxiliar(String crm, String atributo, String valor) {
		return cadastroAuxiliar.altera(crm, atributo, valor);
	}
	
	/**
	 * Este método registra um novo procedimento redirecionando a requisição para {@link CadastroProcedimento}.
	 * @param codigo - O código do procedimento
	 * @param descricao - A descrição do mesmo
	 * @param custo - O custo do procedimento
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção do procedimento no banco
	 */
	public static String novoProcedimento(String codigo, String atributo, String valor) {
		return cadastroProcedimento.novo(codigo, atributo, valor);
	}
	
	/**
	 * Este método modifica um procedimento existente redirecionando a requisição para {@link CadastroProcedimento}.
	 * @param codigo - O Código do procedimento
	 * @param atributo - O campo o qual o dado será alterado (Descrição, Custo...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	public static String alteraProcedimento(String codigo, String atributo, String valor) {
		return cadastroProcedimento.altera(codigo, atributo, valor);
	}
	
	/**
	 * Este método encontra um procedimento no banco redirecionando a requisição para {@link CadastroProcedimento}.
	 * @param atributo - A descrição ou custo do procedimento
	 * @return Uma String contendo toda a informação do procedimento encontrado
	 * 		Se o enfermeiro não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	public static String encontraProcedimento(String atributo) {
		return cadastroProcedimento.encontra(atributo);
	}
	
	/**
	 * Este método cadastra um material em um procedimento redirecionando a requisição para {@link CadastroProcedimento}.
	 * @param codProcedimento - O código do procedimento (ver {@link Procedimento})
	 * @param codMaterial - O código do material (ver {@link Material})
	 * @return Uma String informando o sucesso ou não na inserção do material.
	 */
	public static String materialProcedimento(String codProcedimento, String codMaterial) {
		return cadastroProcedimento.addMaterial(codProcedimento, codMaterial);
	}
	
	/**
	 * Este método cadastra um equipamento em um procedimento redirecionando a requisição para {@link CadastroProcedimento}.
	 * @param codProcedimento - O código do procedimento (ver {@link Procedimento})
	 * @param codEquipamento - O código do equipamento (ver {@link Equipamento})
	 * @return Uma String informando o sucesso ou não na inserção do equipamento.
	 */
	public static String equipamentoProcedimento(String codProcedimento, String codEquipamento) {
		return cadastroProcedimento.addEquipamento(codProcedimento, codEquipamento);
	}
	
	/**
	 * Este método recupera a descrição de materiais cadastrados em um procedimento redirecionando a 
	 * requisição para {@link CadastroProcedimento}.
	 * @param codProcedimento - O código do procedimento (ver {@link Procedimento})
	 * @return Caso existam materiais, é retornada uma String contendo as descrições do 
	 * mesmos. Caso contrário é retornado "" (uma String vazia).
	 */
	public String listMateriaisProcedimento(String codProcedimento) {
		return cadastroProcedimento.listMateriais(codProcedimento);
	}
	
	/**
	 * Este método recupera a descrição de equipamentos cadastrados em um procedimento redirecionando a 
	 * requisição para {@link CadastroProcedimento}.
	 * @param codProcedimento - O código do procedimento (ver {@link Procedimento})
	 * @return Caso existam equipamentos, é retornada uma String contendo as descrições do 
	 * mesmos. Caso contrário é retornado "" (uma String vazia).
	 */
	public String listEquipamentosProcedimento(String codProcedimento) {
		return cadastroProcedimento.listEquipamentos(codProcedimento);
	}
	
	/**
	 * Este método registra uma nova especialidade redirecionando a requisição para {@link CadastroEspecialidade}.
	 * @param codigo - O código da especialidade
	 * @param descricao - A descrição desta
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção da especialidade no banco
	 */
	public static String novaEspecialidade(String codigo, String atributo) {
		return cadastroEspecialidade.novo(codigo, atributo);
	}
	
	/**
	 * Este método modifica uma especialidade existente redirecionando a requisição para {@link CadastroEspecialidade}.
	 * @param codigo - O codigo da especialidade
	 * @param atributo - O campo o qual o dado será alterado (Descrição...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	public static String alteraEspecialidade(String codigo, String atributo, String valor) {
		return cadastroEspecialidade.altera(codigo, atributo, valor);
	}
	
	/**
	 * Este método encontra uma especialidade no banco redirecionando a requisição para {@link CadastroEspecialidade}.
	 * @param atributo - A descrição da especialidade
	 * @return Uma String contendo toda a informação da especialidade encontrada
	 * 		Se a especialidade não foi encontrada, o método retorna "" (Uma String vazia)
	 */
	public static String encontraEspecialidade(String atributo) {
		return cadastroEspecialidade.encontra(atributo);
	}
	
	/**
	 * Este método registra um novo equipamento redirecionando a requisição para {@link CadastroEquipamento}.
	 * @param codigo - O código do equipamento
	 * @param descricao - A descrição do mesmo
	 * @param valor - O preço do equipamento
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção do equipamento no banco
	 */
	public static String novoEquipamento(String codigo, String atributo, String valor) {
		return cadastroEquipamento.novo(codigo, atributo, valor);
	}
	
	/**
	 * Este método modifica um equipamento existente redirecionando a requisição para {@link CadastroEquipamento}.
	 * @param codigo - O Codigo do equipamento
	 * @param atributo - O campo o qual o dado será alterado (Descrição, Valor...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	public static String alteraEquipamento(String codigo, String atributo, String valor) {
		return cadastroEquipamento.altera(codigo, atributo, valor);
	}
	
	/**
	 * Este método encontra um equipamento no banco redirecionando a requisição para {@link CadastroEquipamento}.
	 * @param atributo - A descrição ou Valor do equipamento
	 * @return Uma String contendo toda a informação do equipamento encontrado
	 * 		Se o equipamento não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	public static String encontraEquipamento(String atributo) {
		return cadastroEquipamento.encontra(atributo);
	}
	
	/**
	 * Este método cadastra um novo tombo em um equipamento redirecionando a requisição para {@link CadastroEquipamento}.
	 * @param codigoEquipamento - O código do equipamento (ver {@link Equipamento})
	 * @param codigoTombo - O código do tombo (ver {@link Tombo})
	 * @return Uma String informando o sucesso ou não do cadastro do tombo do equipamento.
	 */
	public static String tombaEquipamento(String codEquipamento, String codTombo) {
		return cadastroEquipamento.tombaEquipamento(codEquipamento, codTombo);
	}
	
	/**
	 * Este método registra um novo material redirecionando a requisição para {@link CadastroMaterial}.
	 * @param codigo - O código do material
	 * @param descricao - A descrição do mesmo
	 * @param valor - O preço do material
	 * 
	 * @return Uma mensagem informando o sucesso ou não da inserção do material no banco
	 */
	public static String novoMaterial(String codigo, String atributo, String valor) {
		return cadastroMaterial.novo(codigo, atributo, valor);
	}
	
	/**
	 * Este método modifica um material existente redirecionando a requisição para {@link CadastroMaterial}.
	 * @param codigo - O Código do material
	 * @param atributo - O campo o qual o dado será alterado (Descrição, Valor...)
	 * @param valor - O novo dado a sobrescrever o dado antigo
	 * @return Uma mensagem indicando um erro ou sucesso ao realizar a alteração
	 */
	public static String alteraMaterial(String codigo, String atributo, String valor) {
		return cadastroMaterial.altera(codigo, atributo, valor);
	}
	
	/**
	 * Este método encontra um material no banco redirecionando a requisição para {@link CadastroMaterial}.
	 * @param atributo - A descrição ou valor do material
	 * @return Uma String contendo toda a informação do material encontrado
	 * 		Se o material não foi encontrado, o método retorna "" (Uma String vazia)
	 */
	public static String encontraMaterial(String atributo) {
		return cadastroMaterial.encontra(atributo);
	}
	
}
