package seedu.internship.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's start timing (time and date).
 */
public class Start extends TimeParser {
    public static final String MESSAGE_CONSTRAINTS =
            "Date needs to be of the format DD/MM/YYYY HHMM";

    public static final String START_DATE_TIME_PATTERN = "E,dd'%s' MMMM,yyyy ha";
    public static final String START_DATE_PARSE_FORMAT = "d/M/y";
    public static final String START_TIME_PARSE_FORMAT = "HHMM";

    public final String startDateTime;

    /**
     * Constructs a {@code Start}.
     *
     * @param startDateTime A valid startDateTime for a Start.
     */
    public Start(String startDateTime) {
        super(START_DATE_TIME_PATTERN, START_DATE_PARSE_FORMAT, START_TIME_PARSE_FORMAT);
        requireNonNull(startDateTime);
        this.startDateTime = startDateTime;
        this.parse(startDateTime);
        checkArgument(isValidStart(), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid startDateTime.
     */
    public boolean isValidStart() {
        return this.isValidTimeParser();
    }


    @Override
    public int hashCode() {
        return startDateTime.hashCode();
    }

}
