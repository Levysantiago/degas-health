package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Medico;

/**
 * Esta classe é responsável por fazer comunicações com o banco de dados para adicionar, editar e listar
 * médicos.
 */
public class MedicoDAO {
	
	/**
	 * Este método é responsável por inserir um novo médico no banco de dados.
	 * @param medico - O novo médico a ser cadastrado
	 * @return <code>true</code> Se o cadastro foi realizado com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
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
	
	/**
	 * Este método é responsável por selecionar um médico no banco de dados
	 * a partir do CRM.
	 * @param crm - O CRM do médico
	 * @return O objeto {@link Medico} instanciado
	 */
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
	
	/**
	 * Este método é responsável por selecionar um médico no banco de dados
	 * a partir do Nome.
	 * @param nome - O nome do médico
	 * @return O objeto {@link Medico} instanciado
	 */
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
	
	/**
	 * Este método é responsável por realizar uma requisição de alteração de um dado
	 * de um médico.
	 * @param crm - O CRM do médico
	 * @param campo - O campo onde será realizada a alteração
	 * @param valor - O novo valor do dado
	 * @return <code>true</code> Se a alteração foi realizada com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
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
