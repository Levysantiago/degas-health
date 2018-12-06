package modelo;

/**
 * Essa classe representa um equipamento que possui os seguintes atributos:</br>
 * <b>id</b> - Id do equipamento no banco de dados</br>
 * <b>codigo</b> - Um código único</br>
 * <b>descrição</b> - Descrição do equipamento</br>
 * <b>valor</b> - O preço de custo
 *
 */
public class Equipamento {
	private String codigo, descricao, valor;
	
	private Long id;
	
	/**
	 * Este é o construtor parcial da classe, esta não define o valor do atributo id
	 * @param codigo - O código do equipamento
	 * @param descricao - A descrição do mesmo
	 * @param valor - O preço do equipamento
	 */
	public Equipamento(String codigo, String descricao, String valor) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
	}
	
	/**
	 * Este é o construtor completo da classe, define o valor de todos os atributos
	 * @param id - Id do equipamento no banco de dados
	 * @param codigo - O código do equipamento
	 * @param descricao - A descrição do mesmo
	 * @param valor - O preço do equipamento
	 */
	public Equipamento(Long id, String codigo, String descricao, String valor) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
