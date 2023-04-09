package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.LeaveCounter;

public class JsonAdaptedLeaveCounterTest {
    private static final String INVALID_LEAVE_COUNTER = "-15";
    private static final String VALID_LEAVE_COUNTER = "15";
    @Test
    public void toModelType_validLeaveCounter_returnsLeaveCounter() throws Exception {
        JsonAdaptedLeaveCounter leaveCounter = new JsonAdaptedLeaveCounter(VALID_LEAVE_COUNTER);
        assertEquals(new LeaveCounter(VALID_LEAVE_COUNTER), leaveCounter.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedLeaveCounter leaveCounter = new JsonAdaptedLeaveCounter(INVALID_LEAVE_COUNTER);
        String expectedMessage = LeaveCounter.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, leaveCounter::toModelType);
    }
}
