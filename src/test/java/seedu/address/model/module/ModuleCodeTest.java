package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidCode_throwsIllegalArgumentException() {
        String invalidCode = "Hello";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidCode));
    }

    @Test
    public void isValidCode_nullCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidCode(null));
    }

    @Test
    public void isValidCode_invalidCode_returnsFalse() {
        assertFalse(ModuleCode.isValidCode("")); // empty string
        assertFalse(ModuleCode.isValidCode(" ")); // spaces only
        assertFalse(ModuleCode.isValidCode("2040")); // only numerics
        assertFalse(ModuleCode.isValidCode("CS")); // only alphabets
        assertFalse(ModuleCode.isValidCode("2040S")); // begin with numerics
        assertFalse(ModuleCode.isValidCode("CS2040C2")); // numeric after last alphabet
        assertFalse(ModuleCode.isValidCode("CS2040$")); // non-alphanumeric characters
    }

    @Test
    public void isValidCode_validCode_returnsTrue() {
        assertTrue(ModuleCode.isValidCode("A1")); // single alphabet, then single numeric
        assertTrue(ModuleCode.isValidCode("A1B")); // single alphabet, then single numeric, then single alphabet
        assertTrue(ModuleCode.isValidCode("CS2040")); // multi alphabet, then multi numeric
        assertTrue(ModuleCode.isValidCode("CS2040SR")); // multi alphabet, then multi numeric, then multi alphabet
    }

}
