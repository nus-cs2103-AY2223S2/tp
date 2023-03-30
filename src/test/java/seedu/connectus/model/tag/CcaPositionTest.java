package seedu.connectus.model.tag;

import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CcaPositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CcaPosition(null));
    }

    @Test
    public void constructor_invalidCcaName_throwsIllegalArgumentException() {
        String invalidCcaPositionName = "";
        assertThrows(IllegalArgumentException.class, () -> new CcaPosition(invalidCcaPositionName));
    }

    @Test
    public void isValidCcaName() {
        // null module name
        assertThrows(NullPointerException.class, () -> CcaPosition.isValidCcaPositionName(null));
    }

}
