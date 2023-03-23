package seedu.sudohr.model.leave;

import static seedu.sudohr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LeaveDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LeaveDate(null));
    }
}
