package api;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import dao.StaffDAO;
import dao.DepartmentDAO;
import model.Staff;
import model.Department;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class DirectoryServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        server.createContext("/staff", exchange -> {
            StaffDAO dao = new StaffDAO();
            List<Staff> list = dao.readAll();
            String json = JsonUtil.toJsonStaffList(list);
            sendResponse(exchange, json);
        });

        server.createContext("/departments", exchange -> {
            DepartmentDAO dao = new DepartmentDAO();
            List<Department> list = dao.readAll();
            String json = JsonUtil.toJsonDepartmentList(list);
            sendResponse(exchange, json);
        });

        server.start();
        System.out.println("Server running on http://localhost:8081/");
    }

    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
