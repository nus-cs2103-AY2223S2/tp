package seedu.address.model.fish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.date.DateUtil.parseFeedingIntervalDays;
import static seedu.address.model.date.DateUtil.parseFeedingIntervalHours;

/**
 * Represents a Fish's feeding interval in the fish address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFeedingInterval(String)}
 */
public class FeedingInterval {

    public static final String MESSAGE_CONSTRAINTS = "Feeding interval should be in the format '<days>d<hours>h',"
            + " and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\d+d\\d+h$";

    public final String value;

    public final String days;

    public final String hours;

    private final int daysInteger;

    private final int hoursInteger;

    /**
     * Constructs an {@code FeedingInterval}.
     *
     * @param feedingInterval A valid feedingInterval.
     */
    public FeedingInterval(String feedingInterval) {
        requireNonNull(feedingInterval);
        checkArgument(isValidFeedingInterval(feedingInterval), MESSAGE_CONSTRAINTS);
        value = feedingInterval;
        daysInteger = parseFeedingIntervalDays(feedingInterval);
        hoursInteger = parseFeedingIntervalHours(feedingInterval);
        days = String.valueOf(daysInteger);
        hours = String.valueOf(hoursInteger);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidFeedingInterval(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return days + " days, " + hours + " hours";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FeedingInterval // instanceof handles nulls
                && value.equals(((FeedingInterval) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
