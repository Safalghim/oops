package service;

import dao.DepartmentDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code DirectoryService} class provides business logic for managing
 * staff and departments.
 * <p>
 * It acts as a service layer between the DAO layer and the REST API.
 * </p>
 *
 * <p>
 * This class follows the Single Responsibility Principle (SRP) by
 * separating business rules from data access logic.
 * </p>
 *
 * @author safal ghimire
 * @version 1.0
 * @since 2025
 */
public class DirectoryService {

    private final StaffDAO staffDAO = new StaffDAO();
    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    /**
     * Creates a new staff member after validating the email.
     *
     * @param staff the staff object to create
     * @return the created staff object
     * @throws IllegalArgumentException if email is invalid
     */
    public Staff createStaff(Staff staff) {
        if (staff.getEmail() == null || !staff.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        return staffDAO.create(staff);
    }

    /**
     * Returns a staff member by ID.
     *
     * @param id staff ID
     * @return staff object or null if not found
     */
    public Staff getStaff(int id) {
        return staffDAO.readById(id);
    }

    /**
     * Returns all staff members.
     *
     * @return list of staff
     */
    public List<Staff> listStaff() {
        return staffDAO.readAll();
    }

    /**
     * Searches staff by name, email, or position.
     *
     * @param query search keyword
     * @return filtered list of staff
     */
    public List<Staff> searchStaff(String query) {
        return listStaff().stream()
                .filter(s ->
                        s.getName().toLowerCase().contains(query.toLowerCase()) ||
                        s.getEmail().toLowerCase().contains(query.toLowerCase()) ||
                        s.getPosition().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Returns all departments.
     *
     * @return list of departments
     */
    public List<Department> listDepartments() {
        return departmentDAO.readAll();
    }
}
