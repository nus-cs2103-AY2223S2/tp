package seedu.sudohr.model.department;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DepartmentNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DepartmentName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new DepartmentName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> DepartmentName.isValidName(null));

        // invalid name
        assertFalse(DepartmentName.isValidName("")); // empty string
        assertFalse(DepartmentName.isValidName(" ")); // spaces only
        assertFalse(DepartmentName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(DepartmentName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(DepartmentName.isValidName("peter jack")); // alphabets only
        assertTrue(DepartmentName.isValidName("12345")); // numbers only
        assertTrue(DepartmentName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(DepartmentName.isValidName("Capital Tan")); // with capital letters
        assertTrue(DepartmentName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
