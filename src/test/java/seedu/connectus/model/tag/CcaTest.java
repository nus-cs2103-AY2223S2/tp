package seedu.connectus.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertThrows(IllegalArgumentException.class, () -> new Cca(invalidCcaName));
    }

    @Test
    public void isValidCcaName_null_throwsNullPointerException() {
        // null CCA name
        assertThrows(NullPointerException.class, () -> Cca.isValidCcaName(null));
    }

    @Test
    public void isValidCcaName_invalidCcas_returnsFalse() {
        // invalid cca
        assertFalse(Cca.isValidCcaName("")); // empty string
        assertFalse(Cca.isValidCcaName(" ")); // spaces only
        assertFalse(Cca.isValidCcaName("CCA CCA&CCA")); // cca whitespace pos no whitespace
        assertFalse(Cca.isValidCcaName("CCA&CCA CCA")); // cca no whitespace pos whitespace
        assertFalse(Cca.isValidCcaName("CCA&CCA")); // with spaces long
        assertFalse(Cca.isValidCcaName("CCA CCA&CCA CCA")); // with space short
    }

    @Test
    public void isValidCcaName_validCcas_returnsTrue() {
        // valid cca
        assertTrue(Cca.isValidCcaName("CCA")); // cca name only
        assertTrue(Cca.isValidCcaName("SEEEEEEEEEEEEEEEEEEEESEEEEEEEEEEEEEEAAAAAAAAAAAAAAAA")); // very long cca name
        assertTrue(Cca.isValidCcaName("SEEEEEEEEEEEEEEEEEEEEEEEEEE"
                + "SEEEEEEEEEEEEEEEEEEEEEEEEEEEEE AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")); // very long cca name ws
        assertTrue(Cca.isValidCcaName("CCA#CCAposition")); // cca name and position no whitespaces
        assertTrue(Cca.isValidCcaName("CCA thiscca#CCA Position")); //cca name and cca position with whitespaces
        assertTrue(Cca.isValidCcaName("CCA thiscca#CCAposition")); // cca name whitespace, cca pos no whitespace
        assertTrue(Cca.isValidCcaName("CCA#CCA Position")); // cca name no whitespace, cca pos whitespace
    }
}
