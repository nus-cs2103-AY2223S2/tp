package seedu.connectus.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModuleName_throwsIllegalArgumentException() {
        String invalidModuleName = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModuleName));
    }

    @Test
    public void isValidModuleName_null_throwsNullPointerException() {
        // null module name
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));
    }

    @Test
    public void isValidModuleName_invalidTags_returnsFalse() {
        // invalid modules
        assertFalse(Module.isValidModuleName("")); // empty string
        assertFalse(Module.isValidModuleName(" ")); // spaces only
        assertFalse(Module.isValidModuleName("CS2103T Software Engineering")); // with spaces long
        assertFalse(Module.isValidModuleName("CS2103T ")); // with space short
    }

    @Test
    public void isValidModuleName_validTags_returnsTrue() {
        // valid modules
        assertTrue(Module.isValidModuleName("CS2101"));
        assertTrue(Module.isValidModuleName("CS2103T"));
        assertTrue(Module.isValidModuleName("MA1521R"));
    }

}
