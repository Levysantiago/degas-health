package modelo;

/**
 * This class represents an Assistant. This assistant has the following attributes:</br>
 * <b>nome</b> - The assistant's name</br>
 * <b>sex</b> - The assistant's gender</br>
 * <b>coren</b> - The assistant's COREN</br>
 * <b>nat</b> - The assistant's nationality</br>
 * <b>bthDate</b> - The assistant's bith date</br>
 * <b>admDate</b> - The assistant's admission date</br>
 * <b>gradDate</b> - The assistant's graduation date
 *
 */
public class Auxiliar{
	
	private String nome, sex, coren, nat, bthDate, gradDate, admDate;
	private Long id;
	
	/**
	 * This method sets all assistent's attributes.
	 * @param nome - The assistent's name
	 * @param sex - The assistent's gender
	 * @param coren - The assistent's COREN
	 * @param nat - The assistent's nationality
	 * @param bthDate - The assistent's birth date
	 * @param admDate - The assistent's admission date
	 * @param gradDate - The assistent's graduation date
	 */
	public Auxiliar(String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
		this.nome = nome;
		this.sex = sex;
		this.coren = coren;
		this.nat = nat;
		this.bthDate = bthDate;
		this.gradDate = gradDate;
		this.admDate = admDate;
	}
	
	public Auxiliar(Long id, String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
		this.id = id;
		this.nome = nome;
		this.sex = sex;
		this.coren = coren;
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

	public String getCoren() {
		return coren;
	}

	public void setCoren(String coren) {
		this.coren = coren;
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
