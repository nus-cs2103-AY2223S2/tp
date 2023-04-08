package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidCompany_throwsIllegalArgumentException() {
        String invalidCompany = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompany));
    }

    @Test
    public void isValidCompany() {
        // null Company
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));


        // invalid company
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only
        assertFalse(Company.isValidCompany("^")); // only non-alphanumeric characters
        assertFalse(Company.isValidCompany("singtel*")); // contains non-alphanumeric characters


        // valid company
        assertTrue(Company.isValidCompany("singtelcorp")); // alphabets only and no spaces
        assertTrue(Company.isValidCompany("12345")); // numbers only
        assertTrue(Company.isValidCompany("damiths boys")); // mix of punctuation and alphabets
        assertTrue(Company.isValidCompany("Carls jr")); // with capital letters
        assertTrue(Company.isValidCompany("software engineering is really tedious")); // long names

    }

}
