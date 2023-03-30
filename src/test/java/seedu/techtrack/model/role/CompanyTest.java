package seedu.techtrack.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidCompany = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompany));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid addresses
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only

        // valid addresses
        assertTrue(Company.isValidCompany("Facebook"));
        assertTrue(Company.isValidCompany("-")); // one character
        assertTrue(Company.isValidCompany("Lorem Ipsum Lorem Ipsum Lorem Ipsum")); // long company name
    }
}
