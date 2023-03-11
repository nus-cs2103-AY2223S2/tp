package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class containing a list of {@code LocalDateTime} objects to be used in tests.
 */
public class SampleDateTimeUtil {
    private static final DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final LocalDateTime VALID_START_DATETIME = LocalDateTime.parse("09/03/2023 14:00", newFormatter);
    public static final LocalDateTime VALID_END_DATETIME = LocalDateTime.parse("09/03/2023 15:00", newFormatter);

    public static final LocalDateTime VALID_START_DATETIME_2 = LocalDateTime.parse("09/03/2023 16:00", newFormatter);
    public static final LocalDateTime VALID_END_DATETIME_2 = LocalDateTime.parse("09/03/2023 18:00", newFormatter);

    public static final LocalDateTime ONE_PM_START_DATETIME = LocalDateTime.parse("09/03/2023 13:00", newFormatter);
    public static final LocalDateTime THREE_PM_END_DATETIME = LocalDateTime.parse("09/03/2023 15:00", newFormatter);
}

