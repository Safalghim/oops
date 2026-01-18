package app;

import dao.DepartmentDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;

import java.util.List;
import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDAO departmentDAO = new DepartmentDAO();
        StaffDAO staffDAO = new StaffDAO();

        while (true) {
            System.out.println("\n===== DIGITAL OFFICE DIRECTORY =====");
            System.out.println("1. View Departments");
            System.out.println("2. View All Staff");
            System.out.println("3. Add Staff");
            System.out.println("4. Update Staff");
            System.out.println("5. Delete Staff");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Invalid input");
                continue;
            }

            switch (choice) {

                // ================= VIEW DEPARTMENTS =================
                case 1:
                    List<Department> departments = departmentDAO.readAll();
                    System.out.println("\n=== DEPARTMENTS ===");
                    for (Department d : departments) {
                        System.out.println(d.getId() + " - " + d.getName() + " (" + d.getLocation() + ")");
                    }
                    break;

                // ================= VIEW STAFF =================
                case 2:
                    List<Staff> staffList = staffDAO.readAll();
                    System.out.println("\n=== STAFF ===");
                    for (Staff s : staffList) {
                        System.out.println(
                                s.getId() + " | " +
                                s.getName() + " | " +
                                s.getEmail() + " | " +
                                s.getPosition() + " | Dept ID: " +
                                s.getDepartmentId()
                        );
                    }
                    break;

                // ================= ADD STAFF =================
                case 3:
                    try {
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Position: ");
                        String position = sc.nextLine();

                        System.out.print("Enter Department ID: ");
                        int deptId = Integer.parseInt(sc.nextLine());

                        Staff newStaff = new Staff(0, name, email, position, deptId);
                        staffDAO.create(newStaff);

                        System.out.println("✅ Staff added with ID: " + newStaff.getId());
                    } catch (Exception e) {
                        System.out.println("❌ Error adding staff. Check inputs.");
                    }
                    break;

                // ================= UPDATE STAFF =================
                case 4:
                    try {
                        System.out.print("Enter Staff ID to update: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter New Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter New Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter New Position: ");
                        String position = sc.nextLine();

                        System.out.print("Enter New Department ID: ");
                        int deptId = Integer.parseInt(sc.nextLine());

                        Staff updated = new Staff(id, name, email, position, deptId);

                        if (staffDAO.update(updated)) {
                            System.out.println("✅ Staff updated successfully");
                        } else {
                            System.out.println("❌ Staff not found");
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Invalid input");
                    }
                    break;

                // ================= DELETE STAFF =================
                case 5:
                    try {
                        System.out.print("Enter Staff ID to delete: ");
                        int id = Integer.parseInt(sc.nextLine());

                        if (staffDAO.delete(id)) {
                            System.out.println("✅ Staff deleted");
                        } else {
                            System.out.println("❌ Staff not found");
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Invalid input");
                    }
                    break;

                // ================= EXIT =================
                case 0:
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }
}
