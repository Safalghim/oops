package test;

import java.sql.Connection;
import dao.DatabaseConnection;

public class TestDBConnection {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Connected to MySQL successfully!");
        } else {
            System.out.println("❌ Failed to connect to MySQL.");
        }
    }
}
