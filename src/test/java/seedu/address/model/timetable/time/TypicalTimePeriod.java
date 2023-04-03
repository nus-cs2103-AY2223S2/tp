package seedu.address.model.timetable.time;

import static seedu.address.model.timetable.util.TypicalTime.EIGHT_AM;
import static seedu.address.model.timetable.util.TypicalTime.EIGHT_PM;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.FIVE_PM;
import static seedu.address.model.timetable.util.TypicalTime.FOUR_PM;
import static seedu.address.model.timetable.util.TypicalTime.NINE_AM;
import static seedu.address.model.timetable.util.TypicalTime.NINE_PM;
import static seedu.address.model.timetable.util.TypicalTime.ONE_PM;
import static seedu.address.model.timetable.util.TypicalTime.SEVEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.SIX_PM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.THREE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWELVE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWO_PM;

import seedu.address.model.time.Day;
import seedu.address.model.time.TimePeriod;

/**
 * Typical Time Periods found in EduMate.
 * Thursday has the full set of time periods, while other days are restricted to 3hr blocks.
 */
public class TypicalTimePeriod {
    public static final TimePeriod TIME_PERIOD =
            new TimePeriodBuilder().build();

    public static final TimePeriod MON_8AM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_8AM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_8AM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_9AM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(NINE_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_9AM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(NINE_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_9AM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(NINE_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_10AM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_10AM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_10AM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_11AM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_11AM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_11AM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_12PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_12PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_12PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_1PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(ONE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_1PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(ONE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_1PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(ONE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_2PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TWO_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_2PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TWO_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_2PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TWO_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_3PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(THREE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_3PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(THREE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_3PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(THREE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_4PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(FOUR_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_4PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(FOUR_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_4PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(FOUR_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_5PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(FIVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_5PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(FIVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_5PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(FIVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_6PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(SIX_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_6PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(SIX_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_6PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(SIX_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_7PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_7PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_7PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_8PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_8PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_8PM_3HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod MON_9PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(NINE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod MON_9PM_2HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(NINE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod MON_10PM_1HR = new TimePeriodBuilder()
            .withDay(Day.MONDAY)
            .withStartTime(TEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_8AM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_8AM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_8AM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_9AM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(NINE_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_9AM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(NINE_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_9AM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(NINE_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_10AM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_10AM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_10AM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_11AM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_11AM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_11AM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_12PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_12PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_12PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_1PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(ONE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_1PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(ONE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_1PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(ONE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_2PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TWO_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_2PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TWO_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_2PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TWO_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_3PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(THREE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_3PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(THREE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_3PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(THREE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_4PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(FOUR_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_4PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(FOUR_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_4PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(FOUR_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_5PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(FIVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_5PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(FIVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_5PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(FIVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_6PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(SIX_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_6PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(SIX_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_6PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(SIX_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_7PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_7PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_7PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_8PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_8PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_8PM_3HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod TUE_9PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(NINE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod TUE_9PM_2HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(NINE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod TUE_10PM_1HR = new TimePeriodBuilder()
            .withDay(Day.TUESDAY)
            .withStartTime(TEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_8AM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_8AM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_8AM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_9AM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(NINE_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_9AM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(NINE_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_9AM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(NINE_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_10AM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_10AM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_10AM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_11AM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_11AM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_11AM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_12PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_12PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_12PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_1PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(ONE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_1PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(ONE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_1PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(ONE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_2PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TWO_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_2PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TWO_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_2PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TWO_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_3PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(THREE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_3PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(THREE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_3PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(THREE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_4PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(FOUR_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_4PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(FOUR_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_4PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(FOUR_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_5PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(FIVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_5PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(FIVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_5PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(FIVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_6PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(SIX_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_6PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(SIX_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_6PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(SIX_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_7PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_7PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_7PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_8PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_8PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_8PM_3HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod WED_9PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(NINE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod WED_9PM_2HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(NINE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod WED_10PM_1HR = new TimePeriodBuilder()
            .withDay(Day.WEDNESDAY)
            .withStartTime(TEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_8AM_1HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_8AM_2HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_8AM_3HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_8AM_4HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_8AM_5HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_8AM_6HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_8AM_7HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_8AM_8HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(8)
            .build();

    public static final TimePeriod THU_8AM_9HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_8AM_10HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(10)
            .build();

    public static final TimePeriod THU_8AM_11HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(11)
            .build();

    public static final TimePeriod THU_8AM_12HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(12)
            .build();

    public static final TimePeriod THU_8AM_13HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(13)
            .build();

    public static final TimePeriod THU_8AM_14HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(14)
            .build();

    public static final TimePeriod THU_8AM_15HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_AM)
            .withDuration(15)
            .build();

    public static final TimePeriod THU_9AM_1HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_9AM_2HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_9AM_3HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_9AM_4HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_9AM_5HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_9AM_6HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_9AM_7HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_9AM_8HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_9AM_9HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_9AM_10HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(10)
            .build();

    public static final TimePeriod THU_9AM_11HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(11)
            .build();

    public static final TimePeriod THU_9AM_12HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(12)
            .build();

    public static final TimePeriod THU_9AM_13HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(13)
            .build();

    public static final TimePeriod THU_9AM_14HR = new TimePeriodBuilder()
            .withStartTime(NINE_AM)
            .withDuration(14)
            .build();

    public static final TimePeriod THU_10AM_1HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_10AM_2HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_10AM_3HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_10AM_4HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_10AM_5HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_10AM_6HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_10AM_7HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_10AM_8HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_10AM_9HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_10AM_10HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(10)
            .build();

    public static final TimePeriod THU_10AM_11HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(11)
            .build();

    public static final TimePeriod THU_10AM_12HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(12)
            .build();

    public static final TimePeriod THU_10AM_13HR = new TimePeriodBuilder()
            .withStartTime(TEN_AM)
            .withDuration(13)
            .build();

    public static final TimePeriod THU_11AM_1HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_11AM_2HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_11AM_3HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_11AM_4HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_11AM_5HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_11AM_6HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_11AM_7HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_11AM_8HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_11AM_9HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_11AM_10HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(10)
            .build();

    public static final TimePeriod THU_11AM_11HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(11)
            .build();

    public static final TimePeriod THU_11AM_12HR = new TimePeriodBuilder()
            .withStartTime(ELEVEN_AM)
            .withDuration(12)
            .build();

    public static final TimePeriod THU_12PM_1HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_12PM_2HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_12PM_3HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_12PM_4HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_12PM_5HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_12PM_6HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_12PM_7HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_12PM_8HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_12PM_9HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_12PM_10HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(10)
            .build();

    public static final TimePeriod THU_12PM_11HR = new TimePeriodBuilder()
            .withStartTime(TWELVE_PM)
            .withDuration(11)
            .build();

    public static final TimePeriod THU_1PM_1HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_1PM_2HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_1PM_3HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_1PM_4HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_1PM_5HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_1PM_6HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_1PM_7HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_1PM_8HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_1PM_9HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_1PM_10HR = new TimePeriodBuilder()
            .withStartTime(ONE_PM)
            .withDuration(10)
            .build();

    public static final TimePeriod THU_2PM_1HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_2PM_2HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_2PM_3HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_2PM_4HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_2PM_5HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_2PM_6HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_2PM_7HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_2PM_8HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_2PM_9HR = new TimePeriodBuilder()
            .withStartTime(TWO_PM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_3PM_1HR = new TimePeriodBuilder()
            .withStartTime(THREE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_3PM_2HR = new TimePeriodBuilder()
            .withStartTime(THREE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_3PM_3HR = new TimePeriodBuilder()
            .withStartTime(THREE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_3PM_4HR = new TimePeriodBuilder()
            .withStartTime(THREE_PM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_3PM_5HR = new TimePeriodBuilder()
            .withStartTime(THREE_PM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_3PM_6HR = new TimePeriodBuilder()
            .withStartTime(THREE_PM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_3PM_7HR = new TimePeriodBuilder()
            .withStartTime(THREE_PM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_3PM_8HR = new TimePeriodBuilder()
            .withStartTime(THREE_PM)
            .withDuration(9)
            .build();

    public static final TimePeriod THU_4PM_1HR = new TimePeriodBuilder()
            .withStartTime(FOUR_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_4PM_2HR = new TimePeriodBuilder()
            .withStartTime(FOUR_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_4PM_3HR = new TimePeriodBuilder()
            .withStartTime(FOUR_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_4PM_4HR = new TimePeriodBuilder()
            .withStartTime(FOUR_PM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_4PM_5HR = new TimePeriodBuilder()
            .withStartTime(FOUR_PM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_4PM_6HR = new TimePeriodBuilder()
            .withStartTime(FOUR_PM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_4PM_7HR = new TimePeriodBuilder()
            .withStartTime(FOUR_PM)
            .withDuration(7)
            .build();

    public static final TimePeriod THU_5PM_1HR = new TimePeriodBuilder()
            .withStartTime(FIVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_5PM_2HR = new TimePeriodBuilder()
            .withStartTime(FIVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_5PM_3HR = new TimePeriodBuilder()
            .withStartTime(FIVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_5PM_4HR = new TimePeriodBuilder()
            .withStartTime(FIVE_PM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_5PM_5HR = new TimePeriodBuilder()
            .withStartTime(FIVE_PM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_5PM_6HR = new TimePeriodBuilder()
            .withStartTime(FIVE_PM)
            .withDuration(6)
            .build();

    public static final TimePeriod THU_6PM_1HR = new TimePeriodBuilder()
            .withStartTime(SIX_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_6PM_2HR = new TimePeriodBuilder()
            .withStartTime(SIX_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_6PM_3HR = new TimePeriodBuilder()
            .withStartTime(SIX_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_6PM_4HR = new TimePeriodBuilder()
            .withStartTime(SIX_PM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_6PM_5HR = new TimePeriodBuilder()
            .withStartTime(SIX_PM)
            .withDuration(5)
            .build();

    public static final TimePeriod THU_7PM_1HR = new TimePeriodBuilder()
            .withStartTime(SEVEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_7PM_2HR = new TimePeriodBuilder()
            .withStartTime(SEVEN_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_7PM_3HR = new TimePeriodBuilder()
            .withStartTime(SEVEN_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_7PM_4HR = new TimePeriodBuilder()
            .withStartTime(SEVEN_PM)
            .withDuration(4)
            .build();

    public static final TimePeriod THU_8PM_1HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_8PM_2HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_8PM_3HR = new TimePeriodBuilder()
            .withStartTime(EIGHT_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod THU_9PM_1HR = new TimePeriodBuilder()
            .withStartTime(NINE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod THU_9PM_2HR = new TimePeriodBuilder()
            .withStartTime(NINE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod THU_10PM_1HR = new TimePeriodBuilder()
            .withStartTime(TEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_8AM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_8AM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_8AM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(EIGHT_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_9AM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(NINE_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_9AM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(NINE_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_9AM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(NINE_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_10AM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_10AM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_10AM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_11AM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_11AM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_11AM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(ELEVEN_AM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_12PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_12PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_12PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TWELVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_1PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(ONE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_1PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(ONE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_1PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(ONE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_2PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TWO_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_2PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TWO_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_2PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TWO_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_3PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(THREE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_3PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(THREE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_3PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(THREE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_4PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(FOUR_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_4PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(FOUR_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_4PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(FOUR_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_5PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(FIVE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_5PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(FIVE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_5PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(FIVE_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_6PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(SIX_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_6PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(SIX_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_6PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(SIX_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_7PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_7PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_7PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(SEVEN_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_8PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_8PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_8PM_3HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(EIGHT_PM)
            .withDuration(3)
            .build();

    public static final TimePeriod FRI_9PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(NINE_PM)
            .withDuration(1)
            .build();

    public static final TimePeriod FRI_9PM_2HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(NINE_PM)
            .withDuration(2)
            .build();

    public static final TimePeriod FRI_10PM_1HR = new TimePeriodBuilder()
            .withDay(Day.FRIDAY)
            .withStartTime(TEN_PM)
            .withDuration(1)
            .build();
}
