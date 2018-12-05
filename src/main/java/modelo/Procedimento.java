package modelo;

public class Procedimento {
	private Long id;
	private String codigo, descricao, custo;

	public Procedimento(Long id, String codigo, String descricao, String custo) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.custo = custo;
	}

	public Procedimento(String codigo, String descricao, String custo) {
		super();
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
