package modelo;

/**
 * This class represents a Doctor. This doctor has the following attributes:</br>
 * <b>nome</b> - The doctor's name</br>
 * <b>sex</b> - The doctor's gender</br>
 * <b>crm</b> - The doctor's CRM</br>
 * <b>nat</b> - The doctor's nationality</br>
 * <b>bthDate</b> - The doctor's bith date</br>
 * <b>admDate</b> - The doctor's admission date</br>
 * <b>gradDate</b> - The doctor's graduation date
 *
 */

public class Medico {

	private String nome, sex, crm, nat, bthDate, gradDate, admDate;
	private Long id;

	/**
	 * This method sets all doctor's attributes.
	 * @param nome - The doctor's name
	 * @param sex - The doctor's gender
	 * @param crm - The doctor's CRM
	 * @param nat - The doctor's nationality
	 * @param bthDate - The doctor's birth date
	 * @param admDate - The doctor's admission date
	 * @param gradDate - The doctor's graduation date
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
