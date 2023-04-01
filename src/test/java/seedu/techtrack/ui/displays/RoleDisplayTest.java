package seedu.techtrack.ui.displays;

import static seedu.techtrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleDisplayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> RoleDisplay.of(null));
    }
}
