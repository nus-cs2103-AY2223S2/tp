package seedu.techtrack.ui.displays;

import static seedu.techtrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StringDisplayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringDisplay.of(null));
    }

}
