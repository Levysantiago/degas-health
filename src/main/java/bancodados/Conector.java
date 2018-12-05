package bancodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	private static Connection con = null;
	
	/**
     * Inicia uma conexão com o banco de dados.
     * @return <code>true</code> Se a conexão foi estabelecida com sucesso.<br>
     *         <code>false</code> Se ouve algum problema ou se já existia uma conexão.
     */
	public static boolean iniciarConexao(){
        //ESTABELECENDO CONEXÃO COM O BANCO DE DADOS        
        if(con != null){
            return false;
        }
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/degasHealth?autoReconnect=true&useSSL=false","adm","123456");
            return true;
        }catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }
    
    /**
     * Finaliza a conexão com o banco.
     * @return <code>true</code> Se a conexão foi finalizada com sucesso.<br>
     *         <code>false</code> Se ouve algum problema ou já não existia conexão.
     */
	public static boolean finalizarConeccao(){
        if(con == null){
            return false;
        }
        try{
            con.close();    
            con = null;
            return true;
        } catch(SQLException e){
            return false;
        }    
    }
	
	public static Connection getCon() {
		return con;
	}
    
}
