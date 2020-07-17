package Pachong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo {

    String url = "jdbc:mysql://localhost:3306/pachong" ;
    String username = "root" ;
    String password = "root" ;
    public static Connection connection;
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/pachong?useSSL=false" ;
        String username = "root" ;
        String password = "root" ;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
