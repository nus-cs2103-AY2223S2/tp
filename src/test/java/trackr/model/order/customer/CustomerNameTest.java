package trackr.model.order.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import trackr.model.person.CustomerName;

public class CustomerNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CustomerName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CustomerName(invalidName));
    }

    @Test
    public void isValidName() {
        assertThrows(NullPointerException.class, () -> CustomerName.isValidName(null));

        // invalid name
        assertFalse(CustomerName.isValidName("")); // empty string
        assertFalse(CustomerName.isValidName(" ")); // spaces only
        assertFalse(CustomerName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(CustomerName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(CustomerName.isValidName("peter jack")); // alphabets only
        assertTrue(CustomerName.isValidName("12345")); // numbers only
        assertTrue(CustomerName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(CustomerName.isValidName("Capital Tan")); // with capital letters
        assertTrue(CustomerName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
