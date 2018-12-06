package modelo;

/**
 * Essa classe representa o tombo de um equipamento ({@link Equipamento}) e possui os seguintes atributos:</br>
 * <b>id</b> - Id do tombo no banco de dados</br>
 * <b>valor</b> - O valor/código do tombo</br>
 * <b>codigoEquipamento</b> - O código do equipamento o qual o tombo está cadastrado
 *
 */
public class Tombo {
	String id, valor, codigoEquipamento;

	/**
	 * Este é o construtor completo da classe, define o valor de todos os atributos
	 * @param id - Id do tombo no banco de dados
	 * @param valor - O valor/código do tombo</br>
	 * @param codigoEquipamento - O código do equipamento o qual o tombo está cadastrado
	 */
	public Tombo(String id, String codigoEquipamento, String valor) {
		this.id = id;
		this.valor = valor;
		this.codigoEquipamento = codigoEquipamento;
	}

	/**
	 * Este é o construtor parcial da classe, esta não define o valor do atributo id
	 * @param id - Id do tombo no banco de dados
	 * @param valor - O valor/código do tombo</br>
	 * @param codigoEquipamento - O código do equipamento o qual o tombo está cadastrado
	 */
	public Tombo(String codigoEquipamento, String valor) {
		this.codigoEquipamento = codigoEquipamento;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCodigoEquipamento() {
		return codigoEquipamento;
	}

	public void setCodigoEquipamento(String codigoEquipamento) {
		this.codigoEquipamento = codigoEquipamento;
	}
}
