package api;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class HttpServerMain {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

            server.createContext("/staff", new StaffEndpoint());
            server.createContext("/departments", new DepartmentEndpoint());

            server.setExecutor(null);
            server.start();

            System.out.println("SERVER RUNNING at http://localhost:8081");
            System.out.println("Endpoints:");
            System.out.println("GET    http://localhost:8081/staff");
            System.out.println("POST   http://localhost:8081/staff");
            System.out.println("PUT    http://localhost:8081/staff");
            System.out.println("DELETE http://localhost:8081/staff?id=1");
            System.out.println("GET    http://localhost:8081/departments");

        } catch (Exception e) {
            System.out.println("❌ SERVER ERROR");
            e.printStackTrace();
        }
    }
}
