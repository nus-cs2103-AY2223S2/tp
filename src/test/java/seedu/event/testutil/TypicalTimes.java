package seedu.event.testutil;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

/**
 * A utility class containing a list of {@code LocalDateTime} objects to be used in tests.
 */
public class TypicalTimes {
    // March 1 2023, 11:00
    public static final LocalDateTime TIME_NOW = LocalDateTime.of(
            2023, Month.MARCH, 1, 11, 0, 0, 0);
    public static final Clock CLOCK_FIXED_AT_TIME_NOW = Clock
            .fixed(TIME_NOW.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    public static final Integer TYPICAL_DAYS = 3;
}
