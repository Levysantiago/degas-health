package modelo;

/**
 * Essa classe representa um médico que possui os seguintes atributos:</br>
 * <b>id</b> - Id do médico no banco de dados</br>
 * <b>nome</b> - Nome do médico</br>
 * <b>sex</b> - Sexo do médico</br>
 * <b>crm</b> - CRM do médico</br>
 * <b>nat</b> - Nacionalidade do médico</br>
 * <b>bthDate</b> - Data de nascimento</br>
 * <b>admDate</b> - Data de admissão</br>
 * <b>gradDate</b> - Data de formatura
 *
 */

public class Medico {

	private String nome, sex, crm, nat, bthDate, gradDate, admDate;
	private Long id;

	/**
	 * Este é o construtor parcial da classe, esta não define o valor do atributo id
	 * @param nome - O nome do médico
	 * @param sex - Sexo do médico
	 * @param crm - CRM do médico
	 * @param nat - Nationalidade
	 * @param bthDate - Data de nascimento
	 * @param admDate - Data de admissão
	 * @param gradDate - Data de formatura
	 */
	public Medico(String nome, String sex, String crm, String nat, String bthDate, String admDate, String gradDate) {
		this.nome = nome;
		this.sex = sex;
		this.crm = crm;
		this.nat = nat;
		this.bthDate = bthDate;
		this.gradDate = gradDate;
		this.admDate = admDate;
	}
	
	/**
	 * Este é o construtor completo da classe, define o valor de todos os atributos
	 * @param id - O id do médico no banco de dados
	 * @param nome - O nome do médico
	 * @param sex - Sexo do médico
	 * @param crm - CRM do médico
	 * @param nat - Nationalidade
	 * @param bthDate - Data de nascimento
	 * @param admDate - Data de admissão
	 * @param gradDate - Data de formatura
	 */
	public Medico(Long id, String nome, String sex, String crm, String nat, String bthDate, String admDate, String gradDate) {
		this.id = id;
		this.nome = nome;
		this.sex = sex;
		this.crm = crm;
		this.nat = nat;
		this.bthDate = bthDate;
		this.gradDate = gradDate;
		this.admDate = admDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getNat() {
		return nat;
	}

	public void setNat(String nat) {
		this.nat = nat;
	}

	public String getBthDate() {
		return bthDate;
	}

	public void setBthDate(String bthDate) {
		this.bthDate = bthDate;
	}

	public String getGradDate() {
		return gradDate;
	}

	public void setGradDate(String gradDate) {
		this.gradDate = gradDate;
	}

	public String getAdmDate() {
		return admDate;
	}

	public void setAdmDate(String admDate) {
		this.admDate = admDate;
	}
}
