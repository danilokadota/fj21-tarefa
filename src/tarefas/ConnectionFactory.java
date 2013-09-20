package tarefas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
		  
		try{
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21","root", "");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}