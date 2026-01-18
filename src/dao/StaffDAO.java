package dao;

import model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    // CREATE
    public Staff create(Staff s) {
        String sql = "INSERT INTO staff (name, email, position, department_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, s.getName());
            stmt.setString(2, s.getEmail());
            stmt.setString(3, s.getPosition());
            stmt.setInt(4, s.getDepartmentId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                s.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    // READ BY ID
    public Staff readById(int id) {
        String sql = "SELECT * FROM staff WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Staff(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("position"),
                        rs.getInt("department_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Staff> readAll() {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM staff";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Staff(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("position"),
                        rs.getInt("department_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Staff s) {
        String sql = "UPDATE staff SET name=?, email=?, position=?, department_id=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getName());
            stmt.setString(2, s.getEmail());
            stmt.setString(3, s.getPosition());
            stmt.setInt(4, s.getDepartmentId());
            stmt.setInt(5, s.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM staff WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
