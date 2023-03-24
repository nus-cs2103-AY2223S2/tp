package seedu.address.model.tutee.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Tutee's schedule in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSchedule(String)}
 */
public class Schedule {

    public static final String[] DAYS_OF_WEEK = {"monday", "tuesday", "wednesday",
        "thursday", "friday", "saturday", "sunday"};
    public static final List<String> DAYS_OF_WEEK_LIST = Arrays.asList(DAYS_OF_WEEK);

    public static final String MESSAGE_CONSTRAINTS =
            "Schedule should only be: " + weekBuilder();

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String schedule;

    /**
     * Constructs a {@code Schedule}.
     *
     * @param schedule A valid day of the week.
     */
    public Schedule(String schedule) {
        requireNonNull(schedule);
        checkArgument(isValidSchedule(schedule), MESSAGE_CONSTRAINTS);
        this.schedule = schedule;
    }

    /**
     * Helper method used for creating the MESSAGE_CONSTRAINTS variable
     */
    private static String weekBuilder() {
        final StringBuilder builder = new StringBuilder();
        for (String days: DAYS_OF_WEEK) {
            builder.append(days).append(", ");
        }
        return builder.toString();
    }

    /**
     * Returns true if a given string is a valid day of the week.
     */
    public static boolean isValidSchedule(String test) {
        return test.matches(VALIDATION_REGEX)
                && DAYS_OF_WEEK_LIST.contains(test);
    }

    @Override
    public String toString() {
        return schedule;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Schedule // instanceof handles nulls
                && schedule.equals(((Schedule) other).schedule)); // state check
    }

    @Override
    public int hashCode() {
        return schedule.hashCode();
    }

}
