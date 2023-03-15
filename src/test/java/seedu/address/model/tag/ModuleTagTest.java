package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTagTest {
    private static final String MODULE_TAG_STRING = "CS2103T";
    private static final ModuleTag MODULE_TAG = new ModuleTag(MODULE_TAG_STRING);

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
        assertFalse(ModuleTag.isValidTagName("DSAIS2100")); // long module prefix
        assertFalse(ModuleTag.isValidTagName("DSA2100GTFO")); // long module suffix
        assertFalse(ModuleTag.isValidTagName("CS 2100")); // space in between
        assertFalse(ModuleTag.isValidTagName("CS2103/T")); // non-alphanumeric character
        assertFalse(ModuleTag.isValidTagName("23CSIT")); // numbers and letters are swapped
        assertFalse(ModuleTag.isValidTagName("2100CS")); // wrong order of module details
    }

    @Test
    public void isValidTagName_validTagValidTarget_true() {
        assertTrue(MODULE_TAG.isValidTagName("CS2103T", "CS2100"));
    }

    @Test
    public void equals() {
        ModuleTag otherModuleTag = new ModuleTag("CS2103T");

        assertEquals(MODULE_TAG, MODULE_TAG);
        assertEquals(MODULE_TAG, otherModuleTag);
    }

    @Test
    public void hashCode_validTag_success() {
        assertEquals(MODULE_TAG_STRING.hashCode(), MODULE_TAG.hashCode());
    }

    @Test
    public void toString_validTag_success() {
        assertEquals(String.format("%s", MODULE_TAG_STRING), MODULE_TAG.toString());
    }
}
