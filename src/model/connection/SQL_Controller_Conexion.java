package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQL_Controller_Conexion {

    /*Variables used to connect to the database*/

    private String url;
    private String user;
    private String password;
    private String driverName;

    private Connection connection = null;

    public SQL_Controller_Conexion(){
    	//TODO
    	this.url = "";
    	this.user = "";
    	this.password = "";
    	this.driverName = "";
    }
    
    public void openConnection() throws Exception{
    	if(this.url == "" || this.user == "" || this.password == "" || this.driverName == "") {
    		throw new Exception("Error al abrir la conexi�n: uno o m�s campos est�n vac�os");
    	}else {
    		try {
    			Class.forName(this.driverName);
    			this.connection = DriverManager.getConnection(url, user, password);    			
    		}catch(Exception e) {
    			throw new Exception("Error al abrir la conexi�n: " + e.getMessage());
    		}
    		
    	}
    }
    
    public void closeConnection() throws Exception{
    	try {
    		this.connection.close();
    	}catch(Exception e) {
    		throw new Exception("Error al cerrar la conexi�n: " + e.getMessage());
    	}
    }
    
    public Connection getConnection() {
    	return this.connection;
    }
}
