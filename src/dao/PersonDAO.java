package dao;
import model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonDAO {


public Person create(Person p) {
String sql = "INSERT INTO persons (name, email) VALUES (?, ?)";
try (Connection conn = DatabaseConnection.getConnection();
PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


stmt.setString(1, p.getName());
stmt.setString(2, p.getEmail());
stmt.executeUpdate();


ResultSet rs = stmt.getGeneratedKeys();
if (rs.next()) p.setId(rs.getInt(1));
} catch (Exception e) {
e.printStackTrace();
}
return p;
}


public Person readById(int id) {
String sql = "SELECT * FROM persons WHERE id = ?";
try (Connection conn = DatabaseConnection.getConnection();
PreparedStatement stmt = conn.prepareStatement(sql)) {


stmt.setInt(1, id);
ResultSet rs = stmt.executeQuery();

if (rs.next()) {
return new Person(
rs.getInt("id"),
rs.getString("name"),
rs.getString("email")
);
}
} catch (Exception e) {
e.printStackTrace();
}
return null;
}


public List<Person> readAll() {
List<Person> list = new ArrayList<>();
String sql = "SELECT * FROM persons";


try (Connection conn = DatabaseConnection.getConnection();
PreparedStatement stmt = conn.prepareStatement(sql);
ResultSet rs = stmt.executeQuery()) {


while (rs.next()) {
list.add(new Person(
rs.getInt("id"),
rs.getString("name"),
rs.getString("email")
));
}
} catch (Exception e) {
e.printStackTrace();
}
return list;
}


public boolean update(Person p) {
String sql = "UPDATE persons SET name=?, email=? WHERE id=?";
try (Connection conn = DatabaseConnection.getConnection();
PreparedStatement stmt = conn.prepareStatement(sql)) {


stmt.setString(1, p.getName());
stmt.setString(2, p.getEmail());
stmt.setInt(3, p.getId());
return stmt.executeUpdate() > 0;


} catch (Exception e) {
e.printStackTrace();
return false;
}
}


public boolean delete(int id) {
String sql = "DELETE FROM persons WHERE id=?";
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