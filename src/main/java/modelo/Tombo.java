package modelo;

public class Tombo {
	String id, valor, codigoEquipamento;

	public Tombo(String id, String codigoEquipamento, String valor) {
		this.id = id;
		this.valor = valor;
		this.codigoEquipamento = codigoEquipamento;
	}

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
