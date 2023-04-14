package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.LeaveCounter;

/**
 * Jackson-friendly version of {@link LeaveCounter}.
 */
public class JsonAdaptedLeaveCounter {
    private final String leaveCountStr;

    /**
     * Constructs a {@code JsonAdaptedLeaveCounter} with the given {@code leaveCount}.
     */
    @JsonCreator
    public JsonAdaptedLeaveCounter(String leaveCount) {
        this.leaveCountStr = leaveCount;
    }

    /**
     * Converts a given {@code LeaveCounter} into this class for Jackson use.
     */
    public JsonAdaptedLeaveCounter(LeaveCounter source) {
        this.leaveCountStr = String.valueOf(source.getLeaveCount());
    }

    @JsonValue
    public String getLeaveCount() {
        return leaveCountStr;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public LeaveCounter toModelType() throws IllegalValueException {
        int leaveCount = Integer.parseInt(this.leaveCountStr);
        if (!LeaveCounter.isValidLeaveCount(this.leaveCountStr)) {
            throw new IllegalValueException(LeaveCounter.MESSAGE_CONSTRAINTS);
        }
        return new LeaveCounter(leaveCount);
    }
}
