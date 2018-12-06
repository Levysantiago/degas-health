package interfaces;

import modelo.Enfermeiro;
import modelo.Medico;

/**
 * 
 *	Esta interface abstrai funções essenciais de toda classe gerenciável.
 *	Esta define uma generalização "Entity" que pode ser qualquer tipo de
 *	classe. Esta generalização é utilizada para facilitar a abstração do
 *	método "inserir", que objetiva inserir um objeto de alguma classe
 *	em alguma base de dados.
 *
 * @param <Entity>
 */
public interface Gerenciavel<Entity> {
	/**
	 * Este método abstrai um método que realiza uma busca em alguma base
	 * de dados por um registro e retorna as informações deste em uma String
	 * a partir de um atributo de identificação.
	 * @param atributo - O atributo de identificação para encontrar o registro
	 * @return Uma String contendo informações sobre este registro
	 */
	public String encontra(String atributo);
	
	/**
	 * Este método abstrai um método que realiza uma alteração de algum valor
	 * de um registro a partir da identificação do mesmo, de um atributo que
	 * representa a coluna/tupla do registro que será modificada e enfim o
	 * valor a substituí-la; 
	 * @param identificacao - Identificação única do registro
	 * @param atributo - Coluna/Tupla do dado a ser alterado do registro
	 * @param valor - O valor a atualizar a tupla
	 * @return Uma String informando se a atualização foi realizada com sucesso
	 * ou não
	 */
	public String altera(String identificacao, String atributo, String valor);
	
	/**
	 * Um método que abstrai um método responsável por verificar a existência
	 * de um registro em alguma base de dados a partir de sua identificação.
	 * @param identificacao - A identificação única do registro
	 * @return <code>true</code> - Se o registro existe<br>
	 * 		<code>false</code> - Se o registro não existe
	 */
	public boolean existe(String identificacao);
	
	/**
	 * Este método abstrai um método responsável por inserir um novo registro
	 * em alguma base de dados. O método recebe um objeto, neste caso uma Entity
	 * genérica (representa um {@link Medico}, {@link Enfermeiro} e outros), e
	 * a partir deste, extrai as informações e insere na base de dados utilizada.
	 * @param obj - O objeto da classe definida na declaração da classe que implementa
	 * a inferface {@link Gerenciavel}. 
	 */
	public void inserir(Entity obj);
}
