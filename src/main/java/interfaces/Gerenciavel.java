package interfaces;

public interface Gerenciavel<Entity> {
	public String encontra(String atributo);
	public String altera(String identificacao, String atributo, String valor);
	public boolean existe(String identificacao);
	public void inserir(Entity obj);
}
