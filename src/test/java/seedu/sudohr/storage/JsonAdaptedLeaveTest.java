package seedu.sudohr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sudohr.storage.JsonAdaptedLeave.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_1;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.model.leave.LeaveDate;

public class JsonAdaptedLeaveTest {
    private static final String INVALID_LEAVE_DATE = "2091-91-23";

    private static final String VALID_DATE = LEAVE_TYPE_1.getDate().toString();
    private static final List<JsonAdaptedEmployee> VALID_EMPLOYEES = LEAVE_TYPE_1.getEmployees().stream()
            .map(JsonAdaptedEmployee::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validLeaveDetails_returnsLeave() throws Exception {
        JsonAdaptedLeave leave = new JsonAdaptedLeave(LEAVE_TYPE_1);
        assertEquals(LEAVE_TYPE_1, leave.toModelType());
    }

    @Test
    public void toModelType_invalidLeaveDate_throwsIllegalValueException() {
        JsonAdaptedLeave leave = new JsonAdaptedLeave(INVALID_LEAVE_DATE, VALID_EMPLOYEES);
        String expectedMessage = Messages.MESSAGE_INVALID_DATE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, leave::toModelType);
    }

    @Test
    public void toModelType_nullLeaveName_throwsIllegalValueException() {
        JsonAdaptedLeave leave = new JsonAdaptedLeave(null, VALID_EMPLOYEES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LeaveDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, leave::toModelType);
    }
}
