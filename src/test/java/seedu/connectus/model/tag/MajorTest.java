package seedu.connectus.model.tag;

import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidCcaName_throwsIllegalArgumentException() {
        String invalidMajor = "";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidMajor));
    }

    @Test
    public void isValidCcaName() {
        // null module name
        assertThrows(NullPointerException.class, () -> Major.isValidMajorName(null));
    }

}
