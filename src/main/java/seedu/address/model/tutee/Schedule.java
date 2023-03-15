package seedu.address.model.tutee;


import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutee's schedule in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSchedule(String)}
 */
public class Schedule {

    public static String[] daysOfWeek = {"monday", "tuesday", "wednesday"
        ,"thursday", "friday", "saturday", "sunday"};
    public static List<String> daysOfWeekList = Arrays.asList(daysOfWeek);

    public static String weekBuilder() {
        final StringBuilder builder = new StringBuilder();
        for (String days: daysOfWeek) {
            builder.append(days).append(", ");
        }
        return builder.toString();
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Subjects should only be: " + weekBuilder();

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
     * Returns true if a given string is a valid day of the week.
     */
    public static boolean isValidSchedule(String test) {
        return test.matches(VALIDATION_REGEX)
                && daysOfWeekList.contains(test);
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
