package seedu.internship.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's end timing (time and date).
 */
public class End extends TimeParser {
    public static final String MESSAGE_CONSTRAINTS =
            "Date needs to be of the format DD/MM/YYYY HHMM";

    public static final String END_DATE_TIME_PATTERN = "E,dd'%s' MMMM,yyyy ha";
    public static final String END_DATE_PARSE_FORMAT = "d/M/y";
    public static final String END_TIME_PARSE_FORMAT = "HHMM";

    public final String endDateTime;

    /**
     * Constructs a {@code End}.
     *
     * @param endDateTime A valid endDateTime for an End.
     */
    public End(String endDateTime) {
        super(END_DATE_TIME_PATTERN, END_DATE_PARSE_FORMAT, END_TIME_PARSE_FORMAT);
        requireNonNull(endDateTime);
        this.endDateTime = endDateTime;
        this.parse(endDateTime);
        checkArgument(isValidEnd(), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid endDateTime.
     */
    public boolean isValidEnd() {
        return this.isValidTimeParser();
    }

    @Override
    public int hashCode() {
        return endDateTime.hashCode();
    }

}
