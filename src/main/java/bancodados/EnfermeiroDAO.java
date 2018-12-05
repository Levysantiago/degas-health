package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Enfermeiro;

public class EnfermeiroDAO {
	public boolean inserirEnfermeiro(Enfermeiro enfermeiro){
    	String query = "{CALL inserirEnfermeiro(?,?,?,?,?,?,?)}";
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, enfermeiro.getNome());
    			stmt.setString(2, enfermeiro.getSex());
    			stmt.setString(3, enfermeiro.getCoren());
    			stmt.setString(4, enfermeiro.getNat());
    			stmt.setString(5, enfermeiro.getBthDate());
    			stmt.setString(6, enfermeiro.getAdmDate());
    			stmt.setString(7, enfermeiro.getGradDate());
    			
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
	
	public Enfermeiro selecionarEnfermeiroCoren(String coren) {
    	String query = "{CALL selectionarEnfermeiroCoren(?)}";
    	Enfermeiro enfermeiro = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, coren);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				enfermeiro = new Enfermeiro(rs.getLong("id"), rs.getString("Nome"), 
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
		return enfermeiro;
    }
	
	public Enfermeiro selecionarEnfermeiroNome(String nome) {
    	String query = "{CALL selectionarEnfermeiroNome(?)}";
    	Enfermeiro enfermeiro = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, nome);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				enfermeiro = new Enfermeiro(rs.getLong("id"), rs.getString("Nome"), 
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
		return enfermeiro;
    }
	
	public boolean alterarEnfermeiro(String coren, String campo, String valor) {
    	PreparedStatement stmt;
    	String query = "UPDATE Enfermeiro SET "+ campo +" = '"+valor+"' WHERE Coren = '"+coren+"';";
    	
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
