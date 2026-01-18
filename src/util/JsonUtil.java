package util;

import java.util.List;
import java.util.Map;

import model.Department;
import model.Staff;

public class JsonUtil {

    // --------- STAFF LIST TO JSON ----------
    public static String toJsonStaffList(List<Staff> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(toJsonStaff(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }

    // --------- ONE STAFF TO JSON ----------
    public static String toJsonStaff(Staff s) {
        return String.format(
            "{\"id\":%d,\"name\":\"%s\",\"email\":\"%s\",\"position\":\"%s\",\"departmentId\":%d}",
            s.getId(), s.getName(), s.getEmail(), s.getPosition(), s.getDepartmentId());
    }

    // --------- DEPT LIST TO JSON ----------
    public static String toJsonDepartmentList(List<Department> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(toJsonDepartment(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }

    // --------- ONE DEPT TO JSON ----------
    public static String toJsonDepartment(Department d) {
        return String.format(
            "{\"id\":%d,\"name\":\"%s\",\"location\":\"%s\"}",
            d.getId(), d.getName(), d.getLocation());
    }

    // --------- PARSE STAFF JSON ----------
    public static Staff parseStaff(String json) {
        Map<String, String> map = parseJsonMap(json);
        return new Staff(
            parseInt(map.get("id")),
            map.get("name"),
            map.get("email"),
            map.get("position"),
            parseInt(map.get("departmentId"))
        );
    }

    private static int parseInt(String s) {
        if (s == null || s.isEmpty()) return 0;
        return Integer.parseInt(s);
    }

    private static Map<String, String> parseJsonMap(String json) {
        Map<String, String> map = new java.util.HashMap<>();

        if (json == null) return map;

        json = json.trim();

        if (!json.startsWith("{") || !json.endsWith("}")) {
            return map;
        }

        json = json.substring(1, json.length() - 1).trim();

        if (json.isEmpty()) return map;

        // Split only on commas that are NOT inside quotes
        List<String> parts = new java.util.ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char c : json.toCharArray()) {
            if (c == '"') inQuotes = !inQuotes;

            if (c == ',' && !inQuotes) {
                parts.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        parts.add(current.toString());

        for (String part : parts) {
            String[] kv = part.split(":", 2); // split into 2 parts only

            if (kv.length < 2) continue;

            String key = kv[0].trim().replace("\"", "");
            String value = kv[1].trim().replace("\"", "");

            map.put(key, value);
        }

        return map;
    }
}