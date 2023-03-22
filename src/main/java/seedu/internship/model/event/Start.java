package seedu.internship.model.event;

import static seedu.internship.commons.util.AppUtil.checkArgument;

import static java.util.Objects.requireNonNull;

public class Start {
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

    private final String startDateTime;

    private final TimeParser tp = new TimeParser(DATE_TIME_PATTERN, DATE_PARSE_FORMAT, TIME_PARSE_FORMAT);

    /**
     * Constructs a {@code Position}.
     *
     * @param startDateTime A valid name for a Position.
     */
    public Start(String startDateTime) {
        requireNonNull(startDateTime);
        this.startDateTime = startDateTime;
        this.tp.parse(startDateTime);
        checkArgument(isValidStart(), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid startDateTime.
     */
    public boolean isValidStart() {
        return this.tp.isValidTimeParser();
    }


    @Override
    public String toString() {
        return tp.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Start // instanceof handles nulls
                && this.tp.equals(((Start) other).tp)); // state check
    }

    @Override
    public int hashCode() {
        return startDateTime.hashCode();
    }

}
