package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.internship.CompanyName;

public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidName));
    }

    @Test
    public void isValidCompanyName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidCompanyName(null));

        // invalid company name
        assertFalse(CompanyName.isValidCompanyName("")); // empty string
        assertFalse(CompanyName.isValidCompanyName(" ")); // spaces only
        assertFalse(CompanyName.isValidCompanyName("^")); // only non-alphanumeric characters
        assertFalse(CompanyName.isValidCompanyName("gojek*")); // contains non-alphanumeric characters

        // valid company name
        assertTrue(CompanyName.isValidCompanyName("big tech company")); // alphabets only
        assertTrue(CompanyName.isValidCompanyName("12345")); // numbers only
        assertTrue(CompanyName.isValidCompanyName("apple the 2nd")); // alphanumeric characters
        assertTrue(CompanyName.isValidCompanyName("Capital Apple")); // with capital letters
        assertTrue(CompanyName.isValidCompanyName("Apple which is 1st in Popularity")); // long names
    }
}
