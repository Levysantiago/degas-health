package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Especialidade;

public class EspecialidadeDAO {
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
