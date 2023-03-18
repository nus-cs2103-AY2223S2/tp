package seedu.address.model.todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an ApplicationDeadline in the planner.
 * Guarantees: immutable.
 */
public class ApplicationDeadline {

    public final static String MESSAGE_CONSTRAINTS = "Input date is invalid, it should be today(%s) onwards";

    public final LocalDate applicationDeadline;
    public final String fullName;

    /**
     * Constructs a {@code ApplicationDeadline}.
     *
     * @param date A valid date.
     */
    public ApplicationDeadline(LocalDate date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), String.format(MESSAGE_CONSTRAINTS, LocalDate.now()));
        applicationDeadline = date;
        fullName = DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE").format(date);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(LocalDate test) {
        return !test.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof seedu.address.model.todo.ApplicationDeadline
                && applicationDeadline.equals(((
                        seedu.address.model.todo.ApplicationDeadline) other).applicationDeadline));
    }

    @Override
    public int hashCode() {
        return applicationDeadline.hashCode();
    }

}
