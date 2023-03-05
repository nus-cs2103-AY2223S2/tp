package seedu.address.model.event.fields;

import java.util.Locale;
import java.util.Objects;

import seedu.address.model.event.enums.Interval;

/**
 * Represents an Events's Recurrence in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterval(String)}
 */
public class Recurrence {

    public static final String MESSAGE_CONSTRAINTS = "Interval must be either: "
            + "Daily, Weekly, Monthly or None";

    public final Interval interval;

    /**
     * Constructs a {@code Recurrence}.
     *
     * @param interval A valid name.
     */
    public Recurrence(String interval) {
        switch (interval.toLowerCase(Locale.ROOT)) {

        case "none":
            this.interval = Interval.NONE;
            break;
        case "daily":
            this.interval = Interval.DAILY;
            break;
        case "weekly":
            this.interval = Interval.WEEKLY;
            break;
        case "monthly":
            this.interval = Interval.MONTHLY;
            break;
        case "yearly":
            this.interval = Interval.YEARLY;
            break;
        default:
            throw new IllegalArgumentException(Recurrence.MESSAGE_CONSTRAINTS + " " + interval);
        }
    }

    /**
     * Returns if a given string is a valid Recurrence.
     */
    public static boolean isValidInterval(String trimmedInterval) {
        String lowerCaseInterval = trimmedInterval.toLowerCase(Locale.ROOT);
        return Objects.equals(lowerCaseInterval, "daily")
                || Objects.equals(lowerCaseInterval, "weekly") || Objects.equals(lowerCaseInterval, "monthly");
    }

    public boolean isRecurring() {
        return !interval.equals(Interval.NONE);
    }

    @Override
    public String toString() {
        return interval.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Recurrence // instanceof handles nulls
                && this.interval.equals(((Recurrence) other).interval)); // state check
    }
}
