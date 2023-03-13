package tfifteenfour.clipboard.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RemarkTest {
    private static final Remark REMARK_ONE = new Remark("First remark");
    private static final Remark REMARK_TWO = new Remark("Second remark");


    @Test
    void testEquals() {
        // same object -> returns true
        assertTrue(REMARK_ONE.equals(REMARK_ONE));

        // same values -> returns true
        Remark remarkOneCopy = new Remark("First remark");
        assertTrue(REMARK_ONE.equals(remarkOneCopy));

        // null -> returns false
        assertFalse(REMARK_ONE.equals(null));

        // different type -> returns false
        assertFalse(REMARK_ONE.equals(5));

        // different remark -> returns false
        assertFalse(REMARK_ONE.equals(REMARK_TWO));
    }
}
