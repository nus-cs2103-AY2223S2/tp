package seedu.internship.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null name
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid role
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(CompanyName.isValidCompanyName("This company name has more than fifty characters hm"));

        // valid role

        assertTrue(Role.isValidRole("^")); // only non-alphanumeric characters
        assertTrue(Role.isValidRole("developer*")); // contains non-alphanumeric characters
        assertTrue(Role.isValidRole("front end developer")); // alphabets only
        assertTrue(Role.isValidRole("12345")); // numbers only
        assertTrue(Role.isValidRole("developer 1st")); // alphanumeric characters
        assertTrue(Role.isValidRole("Software Developer")); // with capital letters
        assertTrue(Role.isValidRole("Full Stack Developer Specialising in Front end")); // long names
        assertTrue(CompanyName.isValidCompanyName("This company "
                + "name has more than fifty characters h")); //50 characters
    }
}
