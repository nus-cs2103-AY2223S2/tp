package trackr.model.task;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDeadline(String)}
 */
public class TaskDeadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Task deadline should only contain numeric values in the format \"DD/MM/YYYY\""
                    + "and it should not be blank"
                    + "and deadline should be today or after today's date.";

    public static final String VALIDATION_REGEX = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";

    private static final DateTimeFormatter DTF_INPUT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DTF_OUTPUT_DATE = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    public final LocalDate taskDeadline;

    /**
     * Constructs a {@code TaskDeadline}.
     *
     * @param deadline A valid deadline.
     */
    public TaskDeadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidTaskDeadline(deadline), MESSAGE_CONSTRAINTS);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        taskDeadline = LocalDate.parse(deadline, dtf);
    }

    /**
     * Returns true if a given string is a valid deadline,
     * meaning string is of the format "dd/MM/yyyy" and
     * the parsed date is today's date or after today's date.
     */
    public static boolean isValidTaskDeadline(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            LocalDate.parse(test, DTF_INPUT_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the deadline stored in "dd/MM/yyyy" format for json storage.
     * @return A string representation of the deadline.
     */
    public String toJsonString() {
        return taskDeadline.format(DTF_INPUT_DATE);
    }

    /**
     * Returns the deadline stored in "01 JANUARY 2023" format.
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return taskDeadline.format(DTF_OUTPUT_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadline // instanceof handles nulls
                && taskDeadline.equals(((TaskDeadline) other).taskDeadline)); // state check
    }

    @Override
    public int hashCode() {
        return taskDeadline.hashCode();
    }

}

