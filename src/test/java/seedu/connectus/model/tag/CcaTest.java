package seedu.connectus.model.tag;

import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CcaTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cca(null));
    }

    @Test
    public void constructor_invalidCcaName_throwsIllegalArgumentException() {
        String invalidCcaName = "";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidCcaName));
    }

    @Test
    public void isValidCcaName() {
        // null module name
        assertThrows(NullPointerException.class, () -> Cca.isValidCcaName(null));
    }

}
