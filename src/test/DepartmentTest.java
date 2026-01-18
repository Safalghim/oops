package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Department;

public class DepartmentTest {

    @Test
    void testDepartmentConstructor() {
        Department d = new Department(1, "IT", "Block A");

        assertEquals(1, d.getId());
        assertEquals("IT", d.getName());
        assertEquals("Block A", d.getLocation());
    }
}
