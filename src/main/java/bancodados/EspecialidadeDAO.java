package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Especialidade;

/**
 * Esta classe é responsável por fazer comunicações com o banco de dados para adicionar, editar e listar
 * especialidades.
 */
public class EspecialidadeDAO {
	/**
	 * Este método é responsável por inserir uma nova especialidade no banco de dados.
	 * @param especialidade - A nova especialidade a ser cadastrada
	 * @return <code>true</code> Se o cadastro foi realizado com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
	public boolean inserirEspecialidade(Especialidade especialidade){
    	String query = "{CALL inserirEspecialidade(?,?)}";
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, especialidade.getCodigo());
    			stmt.setString(2, especialidade.getDescricao());
    			
    			stmt.execute();
    			Conector.finalizarConeccao();
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return false;
    		}
    		
    	}else {
    		return false;
    	}
    	
    	return true;
    }
	
	/**
	 * Este método é responsável por selecionar uma especialidade no banco de dados
	 * a partir do Código.
	 * @param codigo - O Código da especialidade
	 * @return O objeto {@link Especialidade} instanciado
	 */
	public Especialidade selecionarEspecialidadeCodigo(String codigo) {
    	String query = "{CALL selectionarEspecialidadeCodigo(?)}";
    	Especialidade especialidade = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, codigo);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				especialidade = new Especialidade(rs.getLong("id"), rs.getString("Codigo"), 
    						rs.getString("Descricao"));
    				rs.close();
    			}
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return null;
    		}
    	}
    	
    	Conector.finalizarConeccao();
		return especialidade;
    }
	
	/**
	 * Este método é responsável por selecionar uma especialidade no banco de dados
	 * a partir da Descrição.
	 * @param descrição - A Descrição da especialidade
	 * @return O objeto {@link Especialidade} instanciado
	 */
	public Especialidade selecionarEspecialidadeDescricao(String descricao) {
    	String query = "{CALL selectionarEspecialidadeDescricao(?)}";
    	Especialidade especialidade = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, descricao);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				especialidade = new Especialidade(rs.getLong("id"), rs.getString("Codigo"), 
    						rs.getString("Descricao"));
    				rs.close();
    			}
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return null;
    		}
    		
    	}
    	
    	Conector.finalizarConeccao();
		return especialidade;
    }
	
	/**
	 * Este método é responsável por realizar uma requisição de alteração de um dado
	 * de uma especialidade.
	 * @param codigo - O Código da especialidade
	 * @param campo - O campo onde será realizada a alteração
	 * @param valor - O novo valor do dado
	 * @return <code>true</code> Se a alteração foi realizada com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
	public boolean alterarEspecialidade(String codigo, String campo, String valor) {
    	PreparedStatement stmt;
    	String query = "UPDATE Especialidade SET "+ campo +" = '"+valor+"' WHERE Codigo = '"+codigo+"';";
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			stmt = Conector.getCon().prepareStatement(query);
    			stmt.execute();
    			Conector.finalizarConeccao();
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return false;
    		}
    	}
    	
    	return true;
    }
}
