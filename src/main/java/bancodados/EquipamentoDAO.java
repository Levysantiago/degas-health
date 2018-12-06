package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Equipamento;
import modelo.Tombo;

/**
 * Esta classe é responsável por fazer comunicações com o banco de dados para adicionar, editar e listar
 * equipamentos.
 */
public class EquipamentoDAO {
	
	/**
	 * Este método é responsável por inserir um novo equipamento no banco de dados.
	 * @param equipamento - O novo equipamento a ser cadastrado
	 * @return <code>true</code> Se o cadastro foi realizado com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
	public boolean inserirEquipamento(Equipamento equipamento){
    	String query = "{CALL inserirEquipamento(?,?,?)}";
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, equipamento.getCodigo());
    			stmt.setString(2, equipamento.getDescricao());
    			stmt.setString(3, equipamento.getValor());
    			
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
	 * Este método é responsável por selecionar um equipamento no banco de dados
	 * a partir do Código.
	 * @param codigo - O código do equipamento
	 * @return O objeto {@link Equipamento} instanciado
	 */
	public Equipamento selecionarEquipamentoCodigo(String codigo) {
    	String query = "{CALL selectionarEquipamentoCodigo(?)}";
    	Equipamento equipamento = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, codigo);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				equipamento = new Equipamento(rs.getLong("id"), rs.getString("Codigo"), 
    						rs.getString("Descricao"), 
    						rs.getString("Valor"));
    				rs.close();
    			}
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return null;
    		}
    		
    	}
    	
    	Conector.finalizarConeccao();
		return equipamento;
    }
	
	/**
	 * Este método é responsável por selecionar um equipamento no banco de dados
	 * a partir da Descrição.
	 * @param descrição - A descrição do equipamento
	 * @return O objeto {@link Equipamento} instanciado
	 */
	public Equipamento selecionarEquipamentoDescricao(String descricao) {
    	String query = "{CALL selectionarEquipamentoDescricao(?)}";
    	Equipamento equipamento = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, descricao);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				equipamento = new Equipamento(rs.getLong("id"), rs.getString("Codigo"), 
    						rs.getString("Descricao"), 
    						rs.getString("Valor"));
    				rs.close();
    			}
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return null;
    		}
    		
    	}
    	
    	Conector.finalizarConeccao();
		return equipamento;
    }
	
	/**
	 * Este método é responsável por realizar uma requisição de alteração de um dado
	 * de um equipamento.
	 * @param código - O Código do equipamento
	 * @param campo - O campo onde será realizada a alteração
	 * @param valor - O novo valor do dado
	 * @return <code>true</code> Se a alteração foi realizada com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
	public boolean alterarEquipamento(String codigo, String campo, String valor) {
    	PreparedStatement stmt;
    	String query;
    	
    	if(campo.equals("Descricao")) {
    		query = "UPDATE Equipamento SET "+ campo +" = '"+valor+"' WHERE Codigo = '"+codigo+"';";
    	}else {
    		query = "UPDATE Equipamento SET "+ campo +" = CAST('"+ valor +"' AS DECIMAL(10,2)) WHERE Codigo = '"+codigo+"';";
    	}
    	
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
	
	/**
	 * Este método é responsável por cadastrar um tombo em um equipamento no
	 * banco de dados.
	 * @param tombo - O objeto Tombo contendo o código do equipamento e do tombo
	 * @return <code>true</code> Se o cadastro foi realizada com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
	public boolean tombaEquipamento(Tombo tombo) {
		Equipamento equipamento = this.selecionarEquipamentoCodigo(tombo.getCodigoEquipamento());
		String query = "{CALL inserirTombo(?, ?)}";
    	
		if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, tombo.getValor());
    			stmt.setLong(2, equipamento.getId());
    			
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
	 * Este método é responsável por verificar se um tombo existe em
	 * determinado equipamento.
	 * @param tombo - O tombo a ser verificado
	 * @return <code>true</code> Se o tombo existe<br>
	 * 			<code>false</code> Se o tombo não existe
	 */
	public boolean existeTombo(Tombo tombo) {
		Equipamento equipamento = this.selecionarEquipamentoCodigo(tombo.getCodigoEquipamento());
		String query = "{CALL selectionarEquipamentoTombo(?, ?)}";
    	
		if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setLong(1, equipamento.getId());
    			stmt.setString(2, tombo.getValor());
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				rs.close();
    				Conector.finalizarConeccao();
    				return true;
    			}else {
    				rs.close();
    				Conector.finalizarConeccao();
    				return false;
    			}
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return false;
    		}
    		
    	}else {
    		return false;
    	}
	}
}
