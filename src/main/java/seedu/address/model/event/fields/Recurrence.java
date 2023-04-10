package seedu.address.model.event.fields;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import seedu.address.model.event.enums.Interval;

/**
 * Represents an Events's Recurrence in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRecurrence(String)}
 */
public class Recurrence {

    public static final String MESSAGE_CONSTRAINTS = "Interval must be either: "
            + "Daily, Weekly, Monthly, Yearly or None";

    public static final String NONE_CASE = "none";
    public static final String DAILY_CASE = "daily";
    public static final String WEEKLY_CASE = "weekly";
    public static final String MONTHLY_CASE = "monthly";
    public static final String YEARLY_CASE = "yearly";

    public final Interval interval;

    /**
     * Constructs a {@code Recurrence}.
     *
     * @param interval A valid name.
     */
    public Recurrence(String interval) {
        switch (interval.trim().toLowerCase()) {
        case NONE_CASE:
            this.interval = Interval.NONE;
            break;
        case DAILY_CASE:
            this.interval = Interval.DAILY;
            break;
        case WEEKLY_CASE:
            this.interval = Interval.WEEKLY;
            break;
        case MONTHLY_CASE:
            this.interval = Interval.MONTHLY;
            break;
        case YEARLY_CASE:
            this.interval = Interval.YEARLY;
            break;
        default:
            throw new IllegalArgumentException(Recurrence.MESSAGE_CONSTRAINTS + "; Received: " + interval);
        }
    }

    /**
     * Constructs a {@code Recurrence} with the given {@code interval}.
     */
    public Recurrence(Interval interval) {
        Objects.requireNonNull(interval);
        this.interval = interval;
    }

    public ChronoUnit getIncrementUnit() {
        switch (interval) {
        case DAILY:
            return ChronoUnit.DAYS;
        case WEEKLY:
            return ChronoUnit.WEEKS;
        case MONTHLY:
            return ChronoUnit.MONTHS;
        case YEARLY:
            return ChronoUnit.YEARS;
        case NONE:
        default:
            return ChronoUnit.MINUTES;
        }
    }

    /**
     * Returns if a given string is a valid Recurrence.
     */
    public static boolean isValidRecurrence(String trimmedInterval) {
        String lowerCaseInterval = trimmedInterval.toLowerCase(Locale.ROOT);
        return List.of(NONE_CASE, DAILY_CASE, WEEKLY_CASE, MONTHLY_CASE, YEARLY_CASE)
                .contains(lowerCaseInterval);
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
