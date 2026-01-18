package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Staff;

public class StaffTest {

    @Test
    void testStaffConstructor() {
        Staff s = new Staff(1, "John", "john@test.com", "Manager", 2);

        assertEquals(1, s.getId());
        assertEquals("John", s.getName());
        assertEquals("john@test.com", s.getEmail());
        assertEquals("Manager", s.getPosition());
        assertEquals(2, s.getDepartmentId());
    }
}
