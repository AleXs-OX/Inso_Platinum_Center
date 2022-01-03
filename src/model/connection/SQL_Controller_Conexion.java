package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_Controller_Conexion {

    /*Variables used to connect to the database*/

    private String url = "";
    private String user = "";
    private String password = "";

    private Connection connection = null;

    public SQL_Controller_Conexion(){


        try {
            /*Connection with the database*/
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Conexion exitosa con la base de datos");
            }

        }catch (SQLException e) {
            e.printStackTrace();
            // handler any errors
            System.out.println("ERROR DATABASE CONNECTION SQLException: " + e.getMessage());
            System.out.println("ERROR DATABASE CONNECTION SQLState: " + e.getSQLState());
            System.out.println("ERROR DATABASE CONNECTION VendorError: " + e.getErrorCode());
        }
    }
    /*Method returns the connection*/
    public Connection getConnection(){
        return connection;
    }

}
