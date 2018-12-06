package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Enfermeiro;

/**
 * Esta classe é responsável por fazer comunicações com o banco de dados para adicionar, editar e listar
 * enfermeiros.
 */
public class EnfermeiroDAO {
	/**
	 * Este método é responsável por inserir um novo enfermeiro no banco de dados.
	 * @param enfermeiro - O novo enfermeiro a ser cadastrado
	 * @return <code>true</code> Se o cadastro foi realizado com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
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
	
	/**
	 * Este método é responsável por selecionar um enfermeiro no banco de dados
	 * a partir do Coren.
	 * @param coren - O Coren do enfermeiro
	 * @return O objeto {@link Enfermeiro} instanciado
	 */
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
	
	/**
	 * Este método é responsável por selecionar um enfermeiro no banco de dados
	 * a partir do Nome.
	 * @param nome - O Nome do enfermeiro
	 * @return O objeto {@link Enfermeiro} instanciado
	 */
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
	
	/**
	 * Este método é responsável por realizar uma requisição de alteração de um dado
	 * de um enfermeiro.
	 * @param coren - O Coren do enfermeiro
	 * @param campo - O campo onde será realizada a alteração
	 * @param valor - O novo valor do dado
	 * @return <code>true</code> Se a alteração foi realizada com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
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
