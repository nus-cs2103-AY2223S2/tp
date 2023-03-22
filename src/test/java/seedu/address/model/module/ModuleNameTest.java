package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ModuleNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "&";
        assertThrows(IllegalArgumentException.class, () -> new ModuleName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ModuleName.isValidName(null));

        // invalid name
        assertFalse(ModuleName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(ModuleName.isValidName("Lorem Ipsum 123*")); // contains non-alphanumeric or space characters

        // valid name
        assertTrue(ModuleName.isValidName("")); // empty string
        assertTrue(ModuleName.isValidName(" ")); // spaces only
        assertTrue(ModuleName.isValidName("lorem ipsum")); // alphabets only
        assertTrue(ModuleName.isValidName("42")); // numbers only
        assertTrue(ModuleName.isValidName("lorem ipsum 01")); // alphanumeric characters
        assertTrue(ModuleName.isValidName("Lorem Ipsum")); // with capital letters
    }

}
