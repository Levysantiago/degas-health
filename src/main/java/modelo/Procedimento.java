package modelo;

/**
 * Essa classe representa um procedimento que possui os seguintes atributos:</br>
 * <b>id</b> - Id do procedimento no banco de dados</br>
 * <b>codigo</b> - Um código único</br>
 * <b>descrição</b> - Descrição do procedimento</br>
 * <b>custo</b> - O preço de custo
 *
 */
public class Procedimento {
	private Long id;
	private String codigo, descricao, custo;

	/**
	 * Este é o construtor completo da classe, define o valor de todos os atributos
	 * @param id - Id do procedimento no banco de dados
	 * @param codigo - O código do procedimento
	 * @param descricao - A descrição do mesmo
	 * @param custo - O custo do procedimento
	 */
	public Procedimento(Long id, String codigo, String descricao, String custo) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.custo = custo;
	}

	/**
	 * Este é o construtor parcial da classe, esta não define o valor do atributo id
	 * @param codigo - O código do procedimento
	 * @param descricao - A descrição do mesmo
	 * @param custo - O custo do procedimento
	 */
	public Procedimento(String codigo, String descricao, String custo) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.custo = custo;
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

	public String getCusto() {
		return custo;
	}

	public void setCusto(String custo) {
		this.custo = custo;
	}
}
