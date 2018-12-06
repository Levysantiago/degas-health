package bancodados;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Material;

/**
 * Esta classe é responsável por fazer comunicações com o banco de dados para adicionar, editar e listar
 * materiais.
 */
public class MaterialDAO {
	
	/**
	 * Este método é responsável por inserir um novo material no banco de dados.
	 * @param material - O novo material a ser cadastrado
	 * @return <code>true</code> Se o cadastro foi realizado com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
	public boolean inserirMaterial(Material material){
    	String query = "{CALL inserirMaterial(?,?,?)}";
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, material.getCodigo());
    			stmt.setString(2, material.getDescricao());
    			stmt.setString(3, material.getValor());
    			
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
	 * Este método é responsável por selecionar um material no banco de dados
	 * a partir do Código.
	 * @param codigo - O codigo do material
	 * @return O objeto {@link Material} instanciado
	 */
	public Material selecionarMaterialCodigo(String codigo) {
    	String query = "{CALL selectionarMaterialCodigo(?)}";
    	Material material = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, codigo);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				material = new Material(rs.getLong("id"), rs.getString("Codigo"), 
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
		return material;
    }
	
	/**
	 * Este método é responsável por selecionar um material no banco de dados
	 * a partir da Descrição.
	 * @param codigo - O codigo do material
	 * @return O objeto {@link Material} instanciado
	 */
	public Material selecionarMaterialDescricao(String descricao) {
    	String query = "{CALL selectionarMaterialDescricao(?)}";
    	Material material = null;
    	
    	if(Conector.iniciarConexao()) {
    		try {
    			CallableStatement stmt = Conector.getCon().prepareCall(query);
    			stmt.setString(1, descricao);
    			
    			ResultSet rs = stmt.executeQuery();
    			if(rs.next()) {
    				material = new Material(rs.getLong("id"), rs.getString("Codigo"), 
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
		return material;
    }
	
	/**
	 * Este método é responsável por realizar uma requisição de alteração de um dado
	 * de um material.
	 * @param codigo - O Código do material
	 * @param campo - O campo onde será realizada a alteração
	 * @param valor - O novo valor do dado
	 * @return <code>true</code> Se a alteração foi realizada com sucesso<br>
	 * 			<code>false</code> Se houve algum problema
	 */
	public boolean alterarMaterial(String codigo, String campo, String valor) {
    	PreparedStatement stmt;
    	String query;
    	
    	if(campo.equals("Descricao")) {
    		query = "UPDATE Material SET "+ campo +" = '"+valor+"' WHERE Codigo = '"+codigo+"';";
    	}else {
    		query = "UPDATE Material SET "+ campo +" = CAST('"+ valor +"' AS DECIMAL(10,2)) WHERE Codigo = '"+codigo+"';";
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
}
