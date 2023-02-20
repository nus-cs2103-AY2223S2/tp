package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleTag(invalidTagName));
    }

    @Test
    public void isValidTagName_null_throwsNullPointerException() {
        // null tag name
        assertThrows(NullPointerException.class, () -> ModuleTag.isValidTagName(null));
    }

    @Test
    public void isValidTagName_validTagName_true() {
        assertTrue(ModuleTag.isValidTagName("CS2101")); // standard module code
        assertTrue(ModuleTag.isValidTagName("CS2103T")); // module code with suffix
        assertTrue(ModuleTag.isValidTagName("DSA1101")); // long module code
        assertTrue(ModuleTag.isValidTagName("ACC2101GTX")); // module code with long prefix and suffix
    }

    @Test
    public void isValidTagName_invalidTagName_false() {
        assertFalse(ModuleTag.isValidTagName("CS50")); // short module number
        assertFalse(ModuleTag.isValidTagName("C2100")); // short module prefix
        assertFalse(ModuleTag.isValidTagName("DSAI2100")); // long module prefix
        assertFalse(ModuleTag.isValidTagName("DSA2100GTFO")); // long module suffix
        assertFalse(ModuleTag.isValidTagName("CS 2100")); // space in between
        assertFalse(ModuleTag.isValidTagName("CS2103/T")); // non-alphanumeric character
        assertFalse(ModuleTag.isValidTagName("23CSIT")); // numbers and letters are swapped
        assertFalse(ModuleTag.isValidTagName("2100CS")); // wrong order of module details
    }
}
