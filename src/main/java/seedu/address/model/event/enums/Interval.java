package seedu.address.model.event.enums;

import static java.util.Objects.requireNonNull;

import seedu.address.model.event.fields.Recurrence;

/**
 * Contains list of Intervals to be specified in the Calendar.
 */
public enum Interval {

    NONE(Recurrence.NONE_CASE),
    DAILY(Recurrence.DAILY_CASE),
    WEEKLY(Recurrence.WEEKLY_CASE),
    MONTHLY(Recurrence.MONTHLY_CASE),
    YEARLY(Recurrence.YEARLY_CASE);

    private static final String NONE_STRING = "one-time";

    private final String value;

    Interval(String value) {
        requireNonNull(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (this == Interval.NONE) {
            return NONE_STRING;
        }
        return value;
    }
}
