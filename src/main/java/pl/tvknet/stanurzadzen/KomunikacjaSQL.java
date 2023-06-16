package pl.tvknet.stanurzadzen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KomunikacjaSQL {
    private static String HOST = "127.0.0.1";
    private static int PORT = 3306;
    private static  String DB_NAME = "urzadzenia";
    private static String USERNAME = "root";
    private static String PASSWORD = "";
    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        //connection = DriverManager.getConnection(String.format("jdbc:mysql://localhost:%s:%d/%s", HOST,PORT,DB_NAME,USERNAME,PASSWORD));
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/urzadzenia","root1","1" );
        return connection;
       // "jdbc:mysql://localhost:3306/bramka"
    }

}
