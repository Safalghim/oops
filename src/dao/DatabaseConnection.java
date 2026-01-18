package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/digital_directory?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "lafas#123456789"; // change if needed

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.err.println("❌ DATABASE CONNECTION FAILED");
            e.printStackTrace();
            return null;
        }
    }
}
