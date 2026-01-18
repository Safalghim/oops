package model;

/**
 * The {@code Staff} class represents an employee working in the organisation.
 * <p>
 * This class extends the {@link Person} abstract class and adds staff-specific
 * information such as job position and department association.
 * </p>
 *
 * <p>
 * Each staff member belongs to exactly one department, identified by
 * a department ID. This relationship represents an association between
 * {@code Staff} and {@code Department}.
 * </p>
 *
 * <p>
 * This class is used throughout the application in:
 * <ul>
 *   <li>Database operations via {@code StaffDAO}</li>
 *   <li>Business logic via {@code DirectoryService}</li>
 *   <li>REST API endpoints</li>
 * </ul>
 * </p>
 *
 * @author Safal ghimire
 * @version 1.0
 * @since 2025
 */
public class Staff extends Person {

    /** The job position of the staff member (e.g., Manager, Developer). */
    private String position;

    /** The ID of the department this staff member belongs to. */
    private int departmentId;

    /**
     * Constructs a new {@code Staff} object with all attributes.
     *
     * @param id           the unique identifier of the staff member
     * @param name         the full name of the staff member
     * @param email        the email address of the staff member
     * @param position     the job position of the staff member
     * @param departmentId the ID of the associated department
     */
    public Staff(int id, String name, String email, String position, int departmentId) {
        super(id, name, email);
        this.position = position;
        this.departmentId = departmentId;
    }

    /**
     * Returns the job position of the staff member.
     *
     * @return the staff member's position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the job position of the staff member.
     *
     * @param position the new job position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Returns the department ID associated with this staff member.
     *
     * @return the department ID
     */
    public int getDepartmentId() {
        return departmentId;
    }

}
