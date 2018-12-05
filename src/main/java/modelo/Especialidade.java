package modelo;

public class Especialidade {
	private String codigo, descricao;
	private Long id;

	public Especialidade(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
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
