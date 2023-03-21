package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * A utility class containing a list of {@code LocalDateTime} objects to be used in tests.
 */
public class TypicalTimes {
    // March 1 2023, 11:00
    public static final LocalDateTime TIME_NOW = LocalDateTime.of(
            2023, Month.MARCH, 1, 11, 0);
    public static final Integer TYPICAL_DAYS = 3;
}
