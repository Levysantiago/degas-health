package modelo;

public class Material {
	private Long id;
	private String codigo, descricao, valor;
	
	public Material(Long id, String codigo, String descricao, String valor) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Material(String codigo, String descricao, String valor) {
		super();
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
