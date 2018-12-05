package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Medico;

public class MedicoDAO {
	public boolean inserirMedico(Medico medico){
    	String query = "{CALL inserirMedico(?,?,?,?,?,?,?)}";
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, medico.getNome());
    			stmt.setString(2, medico.getSex());
    			stmt.setString(3, medico.getCrm());
    			stmt.setString(4, medico.getNat());
    			stmt.setString(5, medico.getBthDate());
    			stmt.setString(6, medico.getAdmDate());
    			stmt.setString(7, medico.getGradDate());
    			
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
	
	public Medico selecionarMedicoCrm(String crm) {
    	String query = "{CALL selectionarMedicoCrm(?)}";
    	Medico medico = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, crm);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				medico = new Medico(rs.getLong("id"), rs.getString("Nome"), 
    						rs.getString("Sexo"), rs.getString("Crm"), 
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
		return medico;
    }
	
	public Medico selecionarMedicoNome(String nome) {
    	String query = "{CALL selectionarMedicoNome(?)}";
    	Medico medico = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, nome);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				medico = new Medico(rs.getLong("id"), rs.getString("Nome"), 
    						rs.getString("Sexo"), rs.getString("Crm"), 
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
		return medico;
    }
	
	public boolean alterarMedico(String crm, String campo, String valor) {
    	PreparedStatement stmt;
    	String query = "UPDATE Medico SET "+ campo +" = '"+valor+"' WHERE Crm = '"+crm+"';";
    	
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
