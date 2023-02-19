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
        String validTagName = "CS2103T";
        assertTrue(ModuleTag.isValidTagName(validTagName));

        validTagName = "ACC2101GTX";
        assertTrue(ModuleTag.isValidTagName(validTagName));
    }

    @Test
    public void isValidTagName_invalidTagName_false() {
        String invalidTagName = "CS50";
        assertFalse(ModuleTag.isValidTagName(invalidTagName));

        invalidTagName = "C2100";
        assertFalse(ModuleTag.isValidTagName(invalidTagName));
    }
}
