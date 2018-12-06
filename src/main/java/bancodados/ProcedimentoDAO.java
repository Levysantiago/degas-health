package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Equipamento;
import modelo.Material;
import modelo.Procedimento;

/**
 * Esta classe é responsável por fazer comunicações com o banco de dados para adicionar, editar e listar
 * procedimentos.
 */
public class ProcedimentoDAO {
	
	/**
	 * Este método é responsável por inserir um novo procedimento no banco de dados.
	 * @param procedimento - O novo procedimento a ser cadastrado
	 * @return <code>true</code> Se o cadastro foi realizado com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
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
	
	/**
	 * Este método é responsável por selecionar um procedimento no banco de dados
	 * a partir do codigo.
	 * @param codigo - O codigo do procedimento
	 * @return O objeto {@link Procedimento} instanciado
	 */
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
	
	/**
	 * Este método é responsável por selecionar um procedimento no banco de dados
	 * a partir da descrição.
	 * @param descrição - A descrição do procedimento
	 * @return O objeto {@link Procedimento} instanciado
	 */
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
	
	/**
	 * Este método é responsável por realizar uma requisição de alteração de um dado
	 * de um procedimento.
	 * @param codigo - O código do procedimento
	 * @param campo - O campo onde será realizada a alteração
	 * @param valor - O novo valor do dado
	 * @return <code>true</code> Se a alteração foi realizada com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
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
	
	/**
	 * Este método é responsável por cadastrar um material em um procedimento
	 * a partir do código do material e código do procedimento 
	 * @param codProcedimento - Código do procedimento (ver {@link Procedimento})
	 * @param codMaterial - Código do material (ver {@link Material})
	 * @return <code>true</code> Se o cadastro foi realizado com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
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
	
	/**
	 * Este método é responsável por recuperar uma lista de materiais de
	 * um procedimento dado o código do mesmo.
	 * @param codProcedimento - O código do procedimento
	 * @return Um {@link ArrayList} de {@link Material} contendo todos os materiais
	 * encontrados
	 */
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
	
	/**
	 * Este método é responsável por cadastrar um equipamento em um procedimento
	 * a partir do código do equipamento e código do procedimento 
	 * @param codProcedimento - Código do procedimento (ver {@link Procedimento})
	 * @param codEquipamento - Código do equipamento (ver {@link Equipamento})
	 * @return <code>true</code> Se o cadastro foi realizado com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
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
	
	/**
	 * Este método é responsável por recuperar uma lista de equipamentos de
	 * um procedimento dado o código do mesmo.
	 * @param codProcedimento - O código do procedimento
	 * @return Um {@link ArrayList} de {@link Equipamento} contendo todos os equipamentos
	 * encontrados
	 */
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
