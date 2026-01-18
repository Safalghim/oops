package api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.StaffDAO;
import model.Staff;
import util.JsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class StaffEndpoint implements HttpHandler {

    private final StaffDAO staffDAO = new StaffDAO();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();

        // ================= CORS HEADERS =================
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equalsIgnoreCase(method)) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        try {
            switch (method) {
                case "GET":
                    handleGet(exchange);
                    break;
                case "POST":
                    handlePost(exchange);
                    break;
                case "PUT":
                    handlePut(exchange);
                    break;
                case "DELETE":
                    handleDelete(exchange);
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, 500, "{\"error\":\"Internal Server Error\"}");
        }
    }

    // ================= GET =================
    private void handleGet(HttpExchange exchange) throws IOException {
        List<Staff> staffList = staffDAO.readAll();
        String json = JsonUtil.toJsonStaffList(staffList);
        sendResponse(exchange, 200, json);
    }

    // ================= POST =================
    private void handlePost(HttpExchange exchange) throws IOException {
        String body = readRequestBody(exchange);
        Staff staff = JsonUtil.parseStaff(body);

        staffDAO.create(staff);
        sendResponse(exchange, 201, "{\"message\":\"Staff created successfully\"}");
    }

    // ================= PUT =================
    private void handlePut(HttpExchange exchange) throws IOException {
        String body = readRequestBody(exchange);
        Staff staff = JsonUtil.parseStaff(body);

        boolean updated = staffDAO.update(staff);
        if (updated) {
            sendResponse(exchange, 200, "{\"message\":\"Staff updated successfully\"}");
        } else {
            sendResponse(exchange, 404, "{\"error\":\"Staff not found\"}");
        }
    }

    // ================= DELETE =================
    private void handleDelete(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();

        if (query == null || !query.startsWith("id=")) {
            sendResponse(exchange, 400, "{\"error\":\"Missing id parameter\"}");
            return;
        }

        int id = Integer.parseInt(query.split("=")[1]);
        boolean deleted = staffDAO.delete(id);

        if (deleted) {
            sendResponse(exchange, 200, "{\"message\":\"Staff deleted successfully\"}");
        } else {
            sendResponse(exchange, 404, "{\"error\":\"Staff not found\"}");
        }
    }

    // ================= UTIL METHODS =================
    private String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }

    private void sendResponse(HttpExchange exchange, int status, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
