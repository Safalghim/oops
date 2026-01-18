package api;

import model.Department;
import model.Staff;

import java.util.List;

public class JsonUtil {

    // ---------------- STAFF JSON ----------------

    public static String toJsonStaff(Staff s) {
        return "{"
                + "\"id\":" + s.getId() + ","
                + "\"name\":\"" + s.getName() + "\","
                + "\"email\":\"" + s.getEmail() + "\","
                + "\"position\":\"" + s.getPosition() + "\","
                + "\"departmentId\":" + s.getDepartmentId()
                + "}";
    }

    public static String toJsonStaffList(List<Staff> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(toJsonStaff(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static Staff parseStaff(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] parts = json.split(",");

        String name = "";
        String email = "";
        String position = "";
        int departmentId = 0;
        int id = 0;

        for (String p : parts) {
            String[] kv = p.split(":");
            switch (kv[0].trim()) {
                case "id": id = Integer.parseInt(kv[1]); break;
                case "name": name = kv[1]; break;
                case "email": email = kv[1]; break;
                case "position": position = kv[1]; break;
                case "departmentId": departmentId = Integer.parseInt(kv[1]); break;
            }
        }

        Staff s;
        if (id > 0) s = new Staff(id, name, email, position, departmentId);
        else s = new Staff(id,name, email, position, departmentId);

        return s;
    }

    // ---------------- DEPARTMENT JSON ----------------

    public static String toJsonDepartment(Department d) {
        return "{"
                + "\"id\":" + d.getId() + ","
                + "\"name\":\"" + d.getName() + "\","
                + "\"location\":\"" + d.getLocation() + "\""
                + "}";
    }

    public static String toJsonDepartmentList(List<Department> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(toJsonDepartment(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static Department parseDepartment(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] parts = json.split(",");

        int id = 0;
        String name = "";
        String location = "";

        for (String p : parts) {
            String[] kv = p.split(":");
            switch (kv[0].trim()) {
                case "id": id = Integer.parseInt(kv[1]); break;
                case "name": name = kv[1]; break;
                case "location": location = kv[1]; break;
            }
        }

        if (id > 0) return new Department(id, name, location);
        return new Department(id, name, location);
    }
}
