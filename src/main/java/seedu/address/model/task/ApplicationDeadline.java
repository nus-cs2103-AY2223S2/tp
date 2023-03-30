package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an ApplicationDeadline in the planner.
 * Guarantees: immutable.
 */
public class ApplicationDeadline {

    public static final String MESSAGE_CONSTRAINTS = "Input date is invalid, it should be from today onwards!";

    public final LocalDate applicationDeadline;
    public final String fullName;

    /**
     * Constructs a {@code ApplicationDeadline}.
     *
     * @param date A valid date.
     */
    public ApplicationDeadline(LocalDate date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        applicationDeadline = date;
        fullName = DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE").format(date);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(LocalDate test) {
        return !test.isBefore(LocalDate.now());
    }

    /**
     * Getter for the respective application deadline.
     */
    public LocalDate getDeadline() {
        return applicationDeadline;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof seedu.address.model.task.ApplicationDeadline
                && applicationDeadline.equals(((
                        seedu.address.model.task.ApplicationDeadline) other).applicationDeadline));
    }

    @Override
    public int hashCode() {
        return applicationDeadline.hashCode();
    }

}
