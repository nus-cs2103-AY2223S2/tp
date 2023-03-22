package seedu.internship.model.event;

import static seedu.internship.commons.util.AppUtil.checkArgument;

import static java.util.Objects.requireNonNull;

public class End {
    public static final String MESSAGE_CONSTRAINTS =
            "Date needs to be of the format DD/MM/YYYY HHMM";

    private static final String DATE_TIME_PATTERN = "E,dd'%s' MMMM,yyyy ha";
    private static final String DATE_PARSE_FORMAT = "d/M/y";
    private static final String TIME_PARSE_FORMAT = "HHMM";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\p{Digit}/\\p{Digit}/\\p{Digit} \\p{Digit}";

    private final String endDateTime;

    private final TimeParser tp = new TimeParser(DATE_TIME_PATTERN, DATE_PARSE_FORMAT, TIME_PARSE_FORMAT);

    /**
     * Constructs a {@code Position}.
     *
     * @param endDateTime A valid input string for EndDateTime
     */
    public End(String endDateTime) {
        requireNonNull(endDateTime);
        this.endDateTime = endDateTime;
        this.tp.parse(endDateTime);
        checkArgument(isValidEnd(), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid endDateTime.
     */
    public boolean isValidEnd() {
        return this.tp.isValidTimeParser();
    }


    @Override
    public String toString() {
        return tp.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof End // instanceof handles nulls
                && this.tp.equals(((End) other).tp)); // state check
    }

    @Override
    public int hashCode() {
        return endDateTime.hashCode();
    }

}
