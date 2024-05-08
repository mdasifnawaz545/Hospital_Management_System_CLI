package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLData;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection connection;
    public DatabaseConnection() {
        String url = "jdbc:mysql://localhost:3306/Hospital";
        String user = "root";
        String password = ""; // Enter Your Password of MySql if any.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver Loaded Successfully");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class Not Loaded - " + cnfe);
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected with the Database");
        } catch (SQLException sqe) {
            System.out.println("Not Connected with the Database - " + sqe);
        }

    }

}
