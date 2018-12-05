package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Equipamento;
import modelo.Tombo;

public class EquipamentoDAO {
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
