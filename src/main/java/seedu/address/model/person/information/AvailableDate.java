package seedu.address.model.person.information;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents the availability dates for a person in FriendlyLink.
 * Guarantees: immutable;
 */
public class AvailableDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Please ensure the specified dates follow this format: YYYY-MM-DD";
    public static final String INVALID_DATES_SPECIFIED = "Invalid dates specified.";
    public static final String INVALID_NUMBER_OF_DATES =
            "Invalid format for date ranges. Format should be <YYYY-MM-DD>, <YYYY-MM-DD>";
    public static final String VALIDATION_REGEX =
            "^(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$";

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Initializes the AvailableDate constructor.
     *
     * @param startDate Starting date.
     * @param endDate Ending date.
     */
    public AvailableDate(String startDate, String endDate) {
        requireAllNonNull(startDate, endDate);
        checkArgument(isValidDate(startDate, endDate), MESSAGE_CONSTRAINTS);

        try {
            this.startDate = LocalDate.parse(startDate);
            this.endDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(INVALID_DATES_SPECIFIED);
        }

        if (this.startDate.isAfter(this.endDate)) {
            throw new IllegalArgumentException(INVALID_DATES_SPECIFIED);
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns true if a given strings is are valid dates.
     *
     * @param dates Dates to be tested.
     * @return True if {@code dates} contain only valid dates and false otherwise.
     */
    public static boolean isValidDate(String ...dates) {
        return Arrays.stream(dates).allMatch(date -> date.matches(VALIDATION_REGEX));
    }

    /**
     * Returns true if the date ranges intersect.
     *
     * @param ostartDate Other start date.
     * @param oendDate Other end date.
     * @return True if the date ranges intersect and false otherwise.
     */
    public boolean isIntersect(LocalDate ostartDate, LocalDate oendDate) {
        return !endDate.isBefore(ostartDate) && !oendDate.isBefore(startDate);
    }

    @Override
    public String toString() {
        return startDate.toString() + " to " + endDate.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AvailableDate // instanceof handles nulls
                && startDate.equals(((AvailableDate) other).startDate))
                && endDate.equals(((AvailableDate) other).endDate);
    }

    @Override
    public int hashCode() {
        return startDate.hashCode() + endDate.hashCode();
    }
}
