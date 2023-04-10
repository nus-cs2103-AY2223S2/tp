package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalaryTest {

    private Salary salary1;
    private Salary salary2;
    private Salary salary3;

    @BeforeEach
    void setUp() {
        salary1 = new Salary("1000 USD");
        salary2 = new Salary("2000 EUR");
        salary3 = new Salary("1000 USD");
    }

    @Test
    void testEquals() {
        assertEquals(salary1, salary1); // reflexivity
        assertEquals(salary1, salary3); // symmetry
        assertNotEquals(salary1, salary2); // inequality
        assertNotEquals(salary1, null); // null comparison
        assertNotEquals(salary1, "string"); // different class comparison
    }

    @Test
    void testHashCode() {
        assertEquals(salary1.hashCode(), salary1.hashCode()); // consistency
        assertEquals(salary1.hashCode(), salary3.hashCode()); // equals objects have same hash code
        assertNotEquals(salary1.hashCode(), salary2.hashCode()); // different objects have different hash codes
    }
}

