package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_emptyString_accepted() {
        new ModuleName("");
    }
}
