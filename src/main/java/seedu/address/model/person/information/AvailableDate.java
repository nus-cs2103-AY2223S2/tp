package seedu.address.model.person.information;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Represents the availability dates for a person.
 * Guarantees: immutable;
 */
public class AvailableDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Please ensure the specified dates follow this format: YYYY-MM-DD";
    public static final String INVALID_DATES_SPECIFIED = "Invalid dates specified. "
            + "Please ensure that your start date is before the end date";
    public static final String VALIDATION_REGEX =
            "^(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$";

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Initializes the AvailableDate constructor
     *
     * @param startDate starting date
     * @param endDate ending date
     */
    public AvailableDate(String startDate, String endDate) {
        requireNonNull(startDate, endDate);
        checkArgument(isValidDate(startDate, endDate), MESSAGE_CONSTRAINTS);

        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);

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

    public static boolean isValidDate(String ...dates) {
        return Arrays.stream(dates).allMatch(date -> date.matches(VALIDATION_REGEX));
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
