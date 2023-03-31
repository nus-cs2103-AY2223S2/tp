package seedu.address.model.platform;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class PlatformNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PlatformName(null));
    }

    @Test
    public void constructor_invalidPlatformName_throwsIllegalArgumentException() {
        String invalidPlatformName = "";
        assertThrows(IllegalArgumentException.class, () -> new PlatformName(invalidPlatformName));
    }

    @Test
    public void isValidPlatformName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.applicant.Name.isValidName(null));

        // invalid name
        assertFalse(seedu.address.model.platform.PlatformName.isValidName("")); // empty string
        assertFalse(seedu.address.model.platform.PlatformName.isValidName(" ")); // spaces only
        assertFalse(seedu.address.model.platform.PlatformName.isValidName("^")); // only non-alphanumeric
        assertFalse(seedu.address.model.platform.PlatformName.isValidName("link*")); // contains non-alphanumeric

        // valid name
        assertTrue(seedu.address.model.platform.PlatformName.isValidName("linkedin")); // alphabets only
        assertTrue(seedu.address.model.platform.PlatformName.isValidName("12345")); // numbers only
        assertTrue(seedu.address.model.platform.PlatformName.isValidName("2nd linkedin")); // alphanumeric
        assertTrue(seedu.address.model.platform.PlatformName.isValidName("Glints")); // with capital letters
        assertTrue(seedu.address.model.platform.PlatformName.isValidName("Jobstreet X Indeed Collab")); // long
    }
}
