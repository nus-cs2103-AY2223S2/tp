package seedu.address.model.tutee.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a Tutee's schedule in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSchedule(String)}
 */
public class Schedule {
    public static final String MESSAGE_CONSTRAINTS =
            "Schedule should only be: " + weekBuilder() + "(case insensitive)";

    public static final DateTimeFormatter SCHED_FORMATTER = new DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ofPattern("EEEE"))
        .appendOptional(DateTimeFormatter.ofPattern("E"))
        .toFormatter();

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */

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

    private static String toUpperFirst(String str) {
        char first = Character.toUpperCase(str.charAt(0));
        return String.format("%s%s", first, str.substring(1).toLowerCase());
    }

    /**
     * Helper method used for creating the MESSAGE_CONSTRAINTS variable
     */
    private static String weekBuilder() {
        return Arrays.stream(DayOfWeek.values())
            .map(x -> {
                String str = x.toString();
                return toUpperFirst(str);
            }).collect(Collectors.joining(", "));
    }

    /**
     * Returns true if a given string is a valid day of the week.
     */
    public static boolean isValidSchedule(String test) {
        try {
            SCHED_FORMATTER.parse(toUpperFirst(test));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
