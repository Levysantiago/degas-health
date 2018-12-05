package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Auxiliar;

public class AuxiliarDAO {
	public boolean inserirAuxiliar(Auxiliar auxiliar){
    	String query = "{CALL inserirAuxiliar(?,?,?,?,?,?,?)}";
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, auxiliar.getNome());
    			stmt.setString(2, auxiliar.getSex());
    			stmt.setString(3, auxiliar.getCoren());
    			stmt.setString(4, auxiliar.getNat());
    			stmt.setString(5, auxiliar.getBthDate());
    			stmt.setString(6, auxiliar.getAdmDate());
    			stmt.setString(7, auxiliar.getGradDate());
    			
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
	
	public Auxiliar selecionarAuxiliarCoren(String coren) {
    	String query = "{CALL selectionarAuxiliarCoren(?)}";
    	Auxiliar auxiliar = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, coren);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				auxiliar = new Auxiliar(rs.getLong("id"), rs.getString("Nome"), 
    						rs.getString("Sexo"), rs.getString("Coren"), 
    						rs.getString("Nacionalidade"), 
    						rs.getString("DtNasc"), 
    						rs.getString("DtAdmiss"), 
    						rs.getString("DtFormatura"));
    				rs.close();
    			}
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return null;
    		}
    	}
    	
    	Conector.finalizarConeccao();
    	return auxiliar;
    }
	
	public Auxiliar selecionarAuxiliarNome(String nome) {
    	String query = "{CALL selectionarAuxiliarNome(?)}";
    	Auxiliar auxiliar = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, nome);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				auxiliar = new Auxiliar(rs.getLong("id"), rs.getString("Nome"), 
    						rs.getString("Sexo"), rs.getString("Coren"), 
    						rs.getString("Nacionalidade"), 
    						rs.getString("DtNasc"), 
    						rs.getString("DtAdmiss"), 
    						rs.getString("DtFormatura"));
    				rs.close();
    			}
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return null;
    		}
    		
    	}
    	
    	Conector.finalizarConeccao();
		return auxiliar;
    }
	
	public boolean alterarAuxiliar(String coren, String campo, String valor) {
    	PreparedStatement stmt;
    	String query = "UPDATE Auxiliar SET "+ campo +" = '"+valor+"' WHERE Coren = '"+coren+"';";
    	
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
