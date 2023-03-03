package trackr.model.task;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

/**
 * Represents a Task's deadline in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDeadline(LocalDate)}
 */
public class TaskDeadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Task deadline should only contain numeric values in the format \"YYYY/MM/DD\""
                    + "and it should not be blank";

    public final LocalDate taskDeadline;

    /**
     * Constructs a {@code TaskDeadline}.
     *
     * @param deadline A valid deadline.
     */
    public TaskDeadline(LocalDate deadline) {
        requireNonNull(deadline);
        checkArgument(isValidTaskDeadline(deadline), MESSAGE_CONSTRAINTS);
        taskDeadline = deadline;
    }

    /**
     * Returns true if a given local date is a valid deadline,
     * meaning date is today's date or after today's date.
     */
    public static boolean isValidTaskDeadline(LocalDate test) {
        return test.isAfter(LocalDate.now());
    }

    /**
     * Returns the deadline stored in "01 JANUARY 2023" format.
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        int day = taskDeadline.getDayOfMonth();
        String month = taskDeadline.getMonth().toString();
        int year = taskDeadline.getYear();
        return String.format("%d %s %d", day, month, year);
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

