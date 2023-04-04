package ezschedule.model.event;

import static java.util.Objects.requireNonNull;

import ezschedule.commons.util.AppUtil;

/**
 * Represents the factor {day, week, month} to recur an event.
 */
public class RecurFactor {

    public static final String MESSAGE_CONSTRAINTS =
            "RecurFactor should only be {day, week, month}";

    public static final String VALIDATION_REGEX_DAY = "day";
    public static final String VALIDATION_REGEX_WEEK = "week";
    public static final String VALIDATION_REGEX_MONTH = "month";

    private final String factor;

    /**
     * Constructs a {@code RecurFactor}.
     *
     * @param factor A valid factor.
     */
    public RecurFactor(String factor) {
        requireNonNull(factor);
        AppUtil.checkArgument(isValidRecurFactor(factor), MESSAGE_CONSTRAINTS);
        this.factor = factor;
    }

    /**
     * Tests for valid recur factor strictly in {day, week, month}.
     * @param test the string to test
     * @return boolean result of validity
     */
    public static boolean isValidRecurFactor(String test) {
        return (test.matches(VALIDATION_REGEX_DAY)
                | test.matches(VALIDATION_REGEX_WEEK)
                | test.matches(VALIDATION_REGEX_MONTH));
    }

    @Override
    public String toString() {
        return factor;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecurFactor // instanceof handles nulls
                && factor.equals(((RecurFactor) other).factor)); // state check
    }

    @Override
    public int hashCode() {
        return factor.hashCode();
    }
}
