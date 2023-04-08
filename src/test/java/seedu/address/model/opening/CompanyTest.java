package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.ultron.model.opening.Company;

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
        // null company
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        // invalid company
        assertFalse(Company.isValidCompany("")); // empty string
        assertFalse(Company.isValidCompany(" ")); // spaces only
        assertFalse(Company.isValidCompany("&")); // only non-alphanumeric characters
        assertFalse(Company.isValidCompany("a&w")); // with non-alphanumeric characters
        assertFalse(Company.isValidCompany("7-eleven")); // with non-alphanumeric characters

        // valid company
        assertTrue(Company.isValidCompany("shopee")); // alphabets only
        assertTrue(Company.isValidCompany("123")); // numbers only
        assertTrue(Company.isValidCompany("21st century fox")); // alphanumeric characters
        assertTrue(Company.isValidCompany("Google")); // with capital letters
        assertTrue(Company.isValidCompany("international consolidated airlines group sa")); // long companies
    }
}
