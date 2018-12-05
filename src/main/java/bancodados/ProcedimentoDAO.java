package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Equipamento;
import modelo.Material;
import modelo.Procedimento;

public class ProcedimentoDAO {
	public boolean inserirProcedimento(Procedimento procedimento){
    	String query = "{CALL inserirProcedimento(?,?,?)}";
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, procedimento.getCodigo());
    			stmt.setString(2, procedimento.getDescricao());
    			stmt.setString(3, procedimento.getCusto());
    			
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
	
	public Procedimento selecionarProcedimentoCodigo(String codigo) {
    	String query = "{CALL selectionarProcedimentoCodigo(?)}";
    	Procedimento procedimento = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, codigo);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				procedimento = new Procedimento(rs.getLong("id"), rs.getString("Codigo"), 
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
		return procedimento;
    }
	
	public Procedimento selecionarProcedimentoDescricao(String descricao) {
    	String query = "{CALL selectionarProcedimentoDescricao(?)}";
    	Procedimento procedimento = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, descricao);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				procedimento = new Procedimento(rs.getLong("id"), rs.getString("Codigo"), 
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
		return procedimento;
    }
	
	public boolean alterarProcedimento(String codigo, String campo, String valor) {
    	PreparedStatement stmt;
    	String query;
    	
    	query = "UPDATE Procedimento SET "+ campo +" = '"+valor+"' WHERE Codigo = '"+codigo+"';";
    	
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
	
	public boolean materialProcedimento(String codProcedimento, String codMaterial) {
		MaterialDAO materialDAO = new MaterialDAO();
		Material material = materialDAO.selecionarMaterialCodigo(codMaterial);
		if(material == null) {
			return false;
		}
		
		Procedimento procedimento = this.selecionarProcedimentoCodigo(codProcedimento);
		if(procedimento == null) {
			return false;
		}
		
		String query = "{CALL inserirMaterialProcedimento(?,?)}";
		
		if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setLong(1, material.getId());
    			stmt.setLong(2, procedimento.getId());
    			
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
	
	public ArrayList<Material> selecionarMaterialProcedimento(String codProcedimento) {
    	String query = "{CALL selectionarMaterialProcedimento(?)}";
    	ArrayList<Material> materiais = new ArrayList<Material>();
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, codProcedimento);
    			
    			ResultSet rs = stmt.executeQuery();
    			while(rs.next()) {
    				materiais.add( new Material(rs.getLong("id"), rs.getString("Codigo"), 
    						rs.getString("Descricao"), 
    						rs.getString("Valor")) );
    			}
    			rs.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return null;
    		}
    		
    	}
    	Conector.finalizarConeccao();
		return materiais;
    }
	
	public boolean equipamentoProcedimento(String codProcedimento, String codEquipamento) {
		EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
		Equipamento equipamento = equipamentoDAO.selecionarEquipamentoCodigo(codEquipamento);
		if(equipamento == null) {
			return false;
		}
		
		Procedimento procedimento = this.selecionarProcedimentoCodigo(codProcedimento);
		if(procedimento == null) {
			return false;
		}
		
		String query = "{CALL inserirEquipamentoProcedimento(?,?)}";
		
		if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setLong(1, equipamento.getId());
    			stmt.setLong(2, procedimento.getId());
    			
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
	
	public ArrayList<Equipamento> selecionarEquipamentoProcedimento(String codProcedimento) {
    	String query = "{CALL selectionarEquipamentoProcedimento(?)}";
    	ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, codProcedimento);
    			
    			ResultSet rs = stmt.executeQuery();
    			while(rs.next()) {
    				equipamentos.add( new Equipamento(rs.getLong("id"), rs.getString("Codigo"), 
    						rs.getString("Descricao"), 
    						rs.getString("Valor")) );
    			}
    			rs.close();
    		}catch(SQLException e) {
    			e.printStackTrace();
    			Conector.finalizarConeccao();
    			return null;
    		}
    		
    	}
    	Conector.finalizarConeccao();
		return equipamentos;
    }
}
