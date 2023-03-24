package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a <code>TaskStatus</code> that holds the possible task statuses available.
 */
public enum TaskStatus {
    INPROGRESS,
    LATE,
    COMPLETE;

    public static final String MESSAGE_CONSTRAINTS =
            "Task status should only be any of the following three: INPROGRESS, LATE, COMPLETE";

    /**
     *  Returns true if a given string is a valid task status.
     */
    public static boolean isValidTaskStatus(String status) {
        try {
            TaskStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
