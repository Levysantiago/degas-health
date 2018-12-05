package facade;

import cadastro.CadastroAuxiliar;
import cadastro.CadastroEnfermeiro;
import cadastro.CadastroEquipamento;
import cadastro.CadastroEspecialidade;
import cadastro.CadastroMaterial;
import cadastro.CadastroMedico;
import cadastro.CadastroProcedimento;
import modelo.Auxiliar;
import modelo.Enfermeiro;
import modelo.Medico;

/**
 * This class works as a middleware. It'll get all the requests and redirect to the right class method.
 * It defines three attributes: cadastroMedico, cadastroEnfermeiro and cadastroAuxiliar, they are
 * specialist objects to create, read and update {@link Medico}, {@link Enfermeiro} and {@link Auxiliar}
 * objects.
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
	 * This method is Facade Constructor.
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
	 * This method registers a new doctor by redirecting to {@link CadastroMedico}.
	 * @param nome - The doctor's name
	 * @param sex - The doctor's gender
	 * @param crm - The doctor's CRM
	 * @param nat - The doctor's nationality
	 * @param bthDate - The doctor's birth date
	 * @param admDate - The doctor's admission date
	 * @param gradDate - The doctor's graduation date
	 * @return A message indicating an error or success to create a new doctor
	 */
	public String novoMedico(String nome, String sex, String crm, String nat, String bthDate, String admDate, String gradDate) {
		return cadastroMedico.novo(nome, sex, crm, nat, bthDate, admDate, gradDate);
	}
	
	/**
	 * This method finds a doctor by redirecting to {@link CadastroMedico}.
	 * @param atributo - The name or CRM of a doctor
	 * @return A string containing all information of the doctor found.<br>
	 * 		If none doctor was found, the method returns ""
	 */
	public String encontraMedico(String atributo) {
		return cadastroMedico.encontra(atributo);
	}
	
	/**
	 * This method modifies an existent doctor by redirecting to {@link CadastroMedico}.
	 * @param crm - The doctor's CRM
	 * @param atributo - A doctor's attribute (Nome, CRM, Sexo...)
	 * @param valor - The value to subscribe the old attribute value
	 * @return A message indicating an error or a success.
	 */
	public String alteraMedico(String crm, String atributo, String valor) {
		return cadastroMedico.altera(crm, atributo, valor);
	}
	
	/**
	 * This method registers a new nurse by redirecting to {@link CadastroEnfermeiro}.
	 * @param nome - The nurse's name
	 * @param coren - The nurse's COREN
	 * @param sex - The nurse's gender
	 * @param nat - The nurse's nationality
	 * @param bthDate - The nurse's birth date
	 * @param gradDate - The nurse's graduation date
	 * @param admDate - The nurse's admission date
	 * 
	 * @return A message indicating an error or success to create a new nurse
	 */
	public String novoEnfermeiro(String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
		return cadastroEnfermeiro.novo(nome, sex, coren, nat, bthDate, admDate, gradDate);
	}
	
	/**
	 * This method finds a nurse in nurses list by redirecting to {@link CadastroEnfermeiro}.
	 * @param atributo - The name or COREN of a nurse
	 * @return A string containing all information of the nurse found.<br>
	 * 		If the nurse wasn't found, the method returns ""
	 */
	public String encontraEnfermeiro(String atributo) {
		return cadastroEnfermeiro.encontra(atributo);
	}
	
	/**
	 * This method modify an existent nurse by redirecting to {@link CadastroEnfermeiro}.
	 * @param coren - The nurse's COREN
	 * @param atributo - A nurse's attribute (Nome, COREN, Sexo...)
	 * @param valor - The value to subscribe the old attribute value
	 * @return A message indicating an error or a success.
	 */
	public String alteraEnfermeiro(String coren, String atributo, String valor) {
		return cadastroEnfermeiro.altera(coren, atributo, valor);
	}
	
	/**
	 * This method registers a new assistant by redirecting to {@link CadastroAuxiliar}.
	 * @param nome - The assistant's name
	 * @param coren - The assistant's COREN
	 * @param sex - The assistant's gender
	 * @param nat - The assistant's nationality
	 * @param bthDate - The assistant's birth date
	 * @param gradDate - The assistant's graduation date
	 * @param admDate - The assistant's admission date
	 * 
	 * @return A message indicating an error or success to create a new assistant
	 */
	public String novoAuxiliar(String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
		return cadastroAuxiliar.novo(nome, sex, coren, nat, bthDate, admDate, gradDate);
	}
	
	/**
	 * This method finds an assistant in assistants list by redirecting to {@link CadastroAuxiliar}.
	 * @param atributo - The name or COREN of an assistant
	 * @return A string containing all information of the assistant found.<br>
	 * 		If the assistant wasn't found, the method returns ""
	 */
	public String encontraAuxiliar(String atributo) {
		return cadastroAuxiliar.encontra(atributo);
	}
	
	/**
	 * This method modify an existent assistant by redirecting to {@link CadastroAuxiliar}.
	 * @param coren - The assistant's COREN
	 * @param atributo - An assistant's attribute (Nome, COREN, Sexo...)
	 * @param valor - The value to subscribe the old attribute value
	 * @return A message indicating an error or a success.
	 */
	public String alteraAuxiliar(String crm, String atributo, String valor) {
		return cadastroAuxiliar.altera(crm, atributo, valor);
	}
	
	public static String novoProcedimento(String codigo, String atributo, String valor) {
		return cadastroProcedimento.novo(codigo, atributo, valor);
	}
	
	public static String alteraProcedimento(String codigo, String atributo, String valor) {
		return cadastroProcedimento.altera(codigo, atributo, valor);
	}
	
	public static String encontraProcedimento(String atributo) {
		return cadastroProcedimento.encontra(atributo);
	}
	
	public static String materialProcedimento(String codProcedimento, String codMaterial) {
		return cadastroProcedimento.addMaterial(codProcedimento, codMaterial);
	}
	
	public static String equipamentoProcedimento(String codProcedimento, String codEquipamento) {
		return cadastroProcedimento.addEquipamento(codProcedimento, codEquipamento);
	}
	
	public String listMateriaisProcedimento(String codProcedimento) {
		return cadastroProcedimento.listMateriais(codProcedimento);
	}
	
	public String listEquipamentosProcedimento(String codProcedimento) {
		return cadastroProcedimento.listEquipamentos(codProcedimento);
	}
	
	public static String novaEspecialidade(String codigo, String atributo) {
		return cadastroEspecialidade.novo(codigo, atributo);
	}
	
	public static String alteraEspecialidade(String codigo, String atributo, String valor) {
		return cadastroEspecialidade.altera(codigo, atributo, valor);
	}
	
	public static String encontraEspecialidade(String atributo) {
		return cadastroEspecialidade.encontra(atributo);
	}
	
	public static String novoEquipamento(String codigo, String atributo, String valor) {
		return cadastroEquipamento.novo(codigo, atributo, valor);
	}
	
	public static String alteraEquipamento(String codigo, String atributo, String valor) {
		return cadastroEquipamento.altera(codigo, atributo, valor);
	}
	
	public static String encontraEquipamento(String atributo) {
		return cadastroEquipamento.encontra(atributo);
	}
	
	public static String tombaEquipamento(String codEquipamento, String codTombo) {
		return cadastroEquipamento.tombaEquipamento(codEquipamento, codTombo);
	}
	
	public static String novoMaterial(String codigo, String atributo, String valor) {
		return cadastroMaterial.novo(codigo, atributo, valor);
	}
	
	public static String alteraMaterial(String codigo, String atributo, String valor) {
		return cadastroMaterial.altera(codigo, atributo, valor);
	}
	
	public static String encontraMaterial(String atributo) {
		return cadastroMaterial.encontra(atributo);
	}
	
}
