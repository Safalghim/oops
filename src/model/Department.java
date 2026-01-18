package model;

/**
 * The {@code Department} class represents an organisational department.
 * <p>
 * A department has a unique identifier, a name, and a physical location.
 * Staff members are associated with departments using a foreign key
 * relationship in the database.
 * </p>
 *
 * <p>
 * This class is used by:
 * <ul>
 *   <li>{@code DepartmentDAO} for database operations</li>
 *   <li>{@code DirectoryService} for business logic</li>
 *   <li>REST API endpoints</li>
 * </ul>
 * </p>
 *
 * @author Safal ghimire
 * @version 1.0
 * @since 2025
 */
public class Department {

    /** Unique identifier for the department. */
    private int id;

    /** Name of the department. */
    private String name;

    /** Location of the department. */
    private String location;

    /**
     * Constructs a {@code Department} with all attributes.
     *
     * @param id       the department ID
     * @param name     the department name
     * @param location the department location
     */
    public Department(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    /**
     * Returns the department ID.
     *
     * @return department ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the department ID.
     *
     * @param id the new department ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the department name.
     *
     * @return department name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the department name.
     *
     * @param name new department name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the department location.
     *
     * @return department location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the department location.
     *
     * @param location new department location
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
