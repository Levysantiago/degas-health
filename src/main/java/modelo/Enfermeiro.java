package modelo;

/**
 * This class represents a Nurse. This nurse has the following attributes:</br>
 * <b>nome</b> - The nurse's name</br>
 * <b>sex</b> - The nurse's gender</br>
 * <b>coren</b> - The nurse's COREN</br>
 * <b>nat</b> - The nurse's nationality</br>
 * <b>bthDate</b> - The nurse's bith date</br>
 * <b>admDate</b> - The nurse's admission date</br>
 * <b>gradDate</b> - The nurse's graduation date
 *
 */
public class Enfermeiro{
	
	private String nome, sex, coren, nat, bthDate, gradDate, admDate;
	private Long id;
	
	/**
	 * This method sets all nurse's attributes.
	 * @param nome - The nurse's name
	 * @param sex - The nurse's gender
	 * @param coren - The nurse's COREN
	 * @param nat - The nurse's nationality
	 * @param bthDate - The nurse's birth date
	 * @param admDate - The nurse's admission date
	 * @param gradDate - The nurse's graduation date
	 */
	public Enfermeiro(String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
		this.nome = nome;
		this.sex = sex;
		this.coren = coren;
		this.nat = nat;
		this.bthDate = bthDate;
		this.gradDate = gradDate;
		this.admDate = admDate;
	}
	
	public Enfermeiro(Long id, String nome, String sex, String coren, String nat, String bthDate, String admDate, String gradDate) {
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
