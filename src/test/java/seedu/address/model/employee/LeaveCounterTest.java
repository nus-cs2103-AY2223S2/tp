package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;

public class LeaveCounterTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidLeaveCounter_throwsIllegalArgumentException() {
        String invalidLeaveCounter = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidLeaveCounter));
    }

    @Test
    public void isValidLeaveCounter() {
        // null name
        assertFalse(LeaveCounter.isValidLeaveCount(null));

        // valid name
        assertTrue(LeaveCounter.isValidLeaveCount("1"));
        assertTrue(LeaveCounter.isValidLeaveCount("12"));
        assertTrue(LeaveCounter.isValidLeaveCount("0"));
        assertTrue(LeaveCounter.isValidLeaveCount("365"));

        // invalid name
        assertFalse(LeaveCounter.isValidLeaveCount("-1"));
        assertFalse(LeaveCounter.isValidLeaveCount("366"));
        assertFalse(LeaveCounter.isValidLeaveCount("2000000000000000"));
        assertFalse(LeaveCounter.isValidLeaveCount("abcde"));
    }

    @Test
    public void defaultLeaveCounter_default_returnsLeaveCounter() throws CommandException {
        LeaveCounter leaveCounter = new LeaveCounter();
        assert (new LeaveCounter("21").equals(leaveCounter));
    }

    @Test
    public void takeLeave_enoughLeave_returnsLeaveCounter() throws CommandException {
        LeaveCounter leaveCounter = new LeaveCounter("10");
        assert (new LeaveCounter("1").equals(leaveCounter.takeLeave(9)));
    }

    @Test
    public void takeLeave_notEnoughLeave_throwsCommandException() {
        LeaveCounter leaveCounter = new LeaveCounter("10");
        assertThrows(CommandException.class, () -> leaveCounter.takeLeave(11));
    }
}
