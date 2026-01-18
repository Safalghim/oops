package dao;

import model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Department entity.
 * Handles all database operations related to departments.
 */
public class DepartmentDAO {

    // ===================== CREATE =====================
    public Department create(Department d) {
        String sql = "INSERT INTO departments (name, location) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, d.getName());
            stmt.setString(2, d.getLocation());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                d.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    // ===================== READ BY ID =====================
    public Department readById(int id) {
        String sql = "SELECT * FROM departments WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Department(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===================== READ ALL =====================
    public List<Department> readAll() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Department(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===================== UPDATE =====================
    public boolean update(Department d) {
        String sql = "UPDATE departments SET name = ?, location = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, d.getName());
            stmt.setString(2, d.getLocation());
            stmt.setInt(3, d.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===================== DELETE =====================
    public boolean delete(int id) {
        String sql = "DELETE FROM departments WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
