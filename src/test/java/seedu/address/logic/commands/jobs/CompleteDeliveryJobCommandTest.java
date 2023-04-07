package seedu.address.logic.commands.jobs;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompleteDeliveryJobCommandTest {
    @Test
    public void constructor_nullJobID_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompleteDeliveryJobCommand(null, true));
    }

    @Test
    public void constructor_nullDeliveredStatus_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompleteDeliveryJobCommand("ABCDEFGH", (Boolean) null));
    }

}
