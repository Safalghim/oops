package api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DepartmentDAO;
import model.Department;

import java.io.*;
import java.util.List;

public class DepartmentEndpoint implements HttpHandler {

    private DepartmentDAO dao = new DepartmentDAO();

    @Override
    public void handle(HttpExchange ex) throws IOException {

        ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        List<Department> list = dao.readAll();
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < list.size(); i++) {
            Department d = list.get(i);
            sb.append("{\"id\":").append(d.getId())
              .append(",\"name\":\"").append(d.getName())
              .append("\"}");
            if (i < list.size() - 1) sb.append(",");
        }

        sb.append("]");
        send(ex, sb.toString());
    }

    private void send(HttpExchange ex, String response) throws IOException {
        ex.sendResponseHeaders(200, response.length());
        ex.getResponseBody().write(response.getBytes());
        ex.close();
    }
}
