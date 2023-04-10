package seedu.address.model.video;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a timestamp of a video.<p>
 * Guarantees: immutable, is valid as validated in {@link #validateTimestamp(String)}
 */
public class VideoTimestamp {

    /** The default timestamp. */
    public static final String DEFAULT_TIMESTAMP = "00:00:00";

    /** Message that specifies the format constraints. */
    public static final String MESSAGE_FORMAT_CONSTRAINTS =
            "Timestamp should be of the format 'HH:mm:ss' where 'HH' is the number of hours, 'mm' is the number of "
                    + "minutes, and 'ss' is number of seconds, each integer being 2 digits long";

    /** Message that specifies the range constraints. */
    public static final String MESSAGE_RANGE_CONSTRAINTS =
            "Timestamp hours should be between 0 (inclusive) and 99 (inclusive), while minutes and seconds "
                    + "should be between 0 (inclusive) and 59 (inclusive)";

    /** The regex for validating the video timestamp. */
    public static final String VALIDATION_REGEX = "(\\d{2}):(\\d{2}):(\\d{2})";

    private static final Pattern VALIDATION_PATTERN = Pattern.compile(VALIDATION_REGEX);

    private static final int MIN_HOURS = 0;
    private static final int MAX_HOURS = 99;
    private static final int MIN_MINUTES = 0;
    private static final int MAX_MINUTES = 59;
    private static final int MIN_SECONDS = 0;
    private static final int MAX_SECONDS = 59;

    public final int hours;
    public final int minutes;
    public final int seconds;

    /**
     * Constructs a {@code VideoTimestamp} with default values, {@link #DEFAULT_TIMESTAMP}.
     */
    public VideoTimestamp() {
        this(DEFAULT_TIMESTAMP);
    }

    /**
     * Constructs a {@code VideoTimestamp}.
     *
     * @param timestamp A valid timestamp.
     * @throws IllegalArgumentException Indicates that {@code timestamp} is an invalid video timestamp. The exception's
     *                                  message contains the reason for the invalidity.
     */
    public VideoTimestamp(String timestamp) {
        requireNonNull(timestamp);
        validateTimestamp(timestamp);

        int[] hoursMinutesSeconds = extractHoursMinutesSeconds(timestamp);
        hours = hoursMinutesSeconds[0];
        minutes = hoursMinutesSeconds[1];
        seconds = hoursMinutesSeconds[2];
    }

    /**
     * Validate if {@code test} is a valid video timestamp.
     *
     * @param test The string to check if it is a valid video timestamp.
     * @throws IllegalArgumentException Indicates that {@code test} is an invalid video timestamp. The exception's
     *                                  message contains the reason for the invalidity.
     */
    public static void validateTimestamp(String test) {
        requireNonNull(test);

        if (!test.matches(VALIDATION_REGEX)) {
            throw new IllegalArgumentException(MESSAGE_FORMAT_CONSTRAINTS);
        }

        int[] hoursMinutesSeconds = extractHoursMinutesSeconds(test);

        if (hoursMinutesSeconds == null) {
            throw new IllegalArgumentException(MESSAGE_RANGE_CONSTRAINTS);
        }

        int hours = hoursMinutesSeconds[0];
        int minutes = hoursMinutesSeconds[1];
        int seconds = hoursMinutesSeconds[2];

        if (hours < MIN_HOURS
                || hours > MAX_HOURS
                || minutes < MIN_MINUTES
                || minutes > MAX_MINUTES
                || seconds < MIN_SECONDS
                || seconds > MAX_SECONDS) {
            throw new IllegalArgumentException(MESSAGE_RANGE_CONSTRAINTS);
        }
    }

    private static int[] extractHoursMinutesSeconds(String timestamp) {
        Matcher timestampMatcher = VALIDATION_PATTERN.matcher(timestamp);
        timestampMatcher.find();

        String hoursStr = timestampMatcher.group(1);
        String minutesStr = timestampMatcher.group(2);
        String secondsStr = timestampMatcher.group(3);

        int[] hoursMinutesSeconds = new int[3];
        try {
            hoursMinutesSeconds[0] = Integer.parseInt(hoursStr);
            hoursMinutesSeconds[1] = Integer.parseInt(minutesStr);
            hoursMinutesSeconds[2] = Integer.parseInt(secondsStr);
        } catch (NumberFormatException nfe) {
            return null;
        }

        return hoursMinutesSeconds;
    }


    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VideoTimestamp)) {
            return false;
        }

        VideoTimestamp otherTimestamp = (VideoTimestamp) other;

        return otherTimestamp.hours == hours
            && otherTimestamp.minutes == minutes
            && otherTimestamp.seconds == seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds);
    }
}
