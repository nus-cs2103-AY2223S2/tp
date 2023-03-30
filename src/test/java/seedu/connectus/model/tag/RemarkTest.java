package seedu.connectus.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemarkName_throwsIllegalArgumentException() {
        String invalidRemarkName = "";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemarkName));
    }

    @Test
    public void isValidRemarkName_null_throwsNullPointerException() {
        // null remark name
        assertThrows(NullPointerException.class, () -> Remark.isValidRemarkName(null));
    }

    @Test
    public void isValidRemarkName_invalidRemarks_returnsFalse() {
        // invalid remarks
        assertFalse(Remark.isValidRemarkName("")); // empty string
        assertFalse(Remark.isValidRemarkName(" ")); // spaces only
    }

    @Test
    public void isValidRemarkName_validRemarks_returnsTrue() {
        // valid remarks
        assertTrue(Remark.isValidRemarkName("We Are Friends 123"));
        assertTrue(Remark.isValidRemarkName("1")); // one character
        assertTrue(Remark.isValidRemarkName("1 ")); // one character with whitespace
        assertTrue(Remark.isValidRemarkName("a very very very very very very very "
                + "very very very long remark")); // long remark
    }

}
