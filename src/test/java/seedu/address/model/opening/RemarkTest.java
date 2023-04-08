package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.ultron.model.opening.Remark;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

//    @Test
//    public void constructor_invalidRemarkName_throwsIllegalArgumentException() {
//        String invalidRemarkName = "";
//        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemarkName));
//    }

    @Test
    public void isValidRemark() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid remark
        assertFalse(Remark.isValidRemark("")); // empty string
        assertFalse(Remark.isValidRemark(" ")); // spaces only

        // valid remark
        assertTrue(Remark.isValidRemark("favourite")); // alphabets only
        assertTrue(Remark.isValidRemark("Best")); // with capital letters
        assertTrue(Remark.isValidRemark("data-analyst ideal")); // with non-alphanumeric characters
        assertTrue(Remark.isValidRemark("software engineer for integrated processing")); // long remarks
    }

}
