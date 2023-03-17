package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class SalaryTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "w200";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary() {
        // null name
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid name
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary("g273")); // characters

        // valid salary
        assertTrue(Salary.isValidSalary("3000")); // numbers only
        assertTrue(Salary.isValidSalary("4000")); // numbers only
        assertTrue(Salary.isValidSalary("2000")); // numbers only
    }
}
