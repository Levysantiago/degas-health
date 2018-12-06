package modelo;

/**
 * Essa classe representa uma especialidade que possui os seguintes atributos:</br>
 * <b>id</b> - Id da especialidade no banco de dados</br>
 * <b>codigo</b> - Um código único</br>
 * <b>descrição</b> - Descrição da especialidade</br>
 *
 */
public class Especialidade {
	private String codigo, descricao;
	private Long id;
	
	/**
	 * Este é o construtor parcial da classe, esta não define o valor do atributo id
	 * @param codigo - O código da especialidade
	 * @param descricao - A descrição desta
	 */
	public Especialidade(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	/**
	 * Este é o construtor completo da classe, define o valor de todos os atributos
	 * @param id - Id da especialidade no banco de dados
	 * @param codigo - O código da especialidade
	 * @param descricao - A descrição desta
	 */
	public Especialidade(Long id, String codigo, String descricao) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
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
}
