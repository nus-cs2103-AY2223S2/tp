package seedu.sprint.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a deadline for a task.
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be a valid calendar date of the format DD-MM-YYYY.";
    public static final String DEADLINE_HAS_PASSED =
            "Warning: sprINT detected a deadline that has already passed. Please check and "
                    + "update task details accordingly.\n";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public final LocalDate deadline;
    private boolean isOutdated;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDate(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDate.parse(deadline, INPUT_FORMAT);
        this.isOutdated = !isValidDeadline(deadline);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate testDate = LocalDate.parse(test, INPUT_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid deadline.
     * A deadline is valid only if it is not earlier than today's date.
     */
    public static boolean isValidDeadline(String test) {
        LocalDate testDate = LocalDate.parse(test, INPUT_FORMAT);
        return testDate.compareTo(LocalDate.now()) >= 0;
    }

    /**
     * Checks if deadline of task has passed.
     * @return true if deadline is before today's date, false otherwise.
     */
    public boolean isOutdated() {
        return isOutdated;
    }

    /**
     * Checks if this deadline is before another given deadline.
     * @param otherDeadline the other deadline to compare to.
     * @return true if the deadline is before another given deadline.
     */
    public boolean isBefore(Deadline otherDeadline) {
        return this.deadline.isBefore(otherDeadline.deadline);
    }

    /**
     * Checks if this deadline is after another given deadline.
     * @param otherDeadline the other deadline to compare to.
     * @return true if the deadline is after another given deadline.
     */
    public boolean isAfter(Deadline otherDeadline) {
        return this.deadline.isAfter(otherDeadline.deadline);
    }

    public String toDisplayString() {
        return deadline.format(OUTPUT_FORMAT);
    }

    @Override
    public String toString() {
        return deadline.format(INPUT_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
