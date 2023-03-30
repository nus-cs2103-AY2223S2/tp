package seedu.address.model.timetable.util;

import org.joda.time.LocalTime;

import seedu.address.model.commitment.Lesson;
import seedu.address.model.location.Location;
import seedu.address.model.time.Day;


/**
 * Represents some of Typical Lessons.
 */
public class TypicalLesson {

    public static final Lesson MONDAY_8AM_2HR_LESSON = new Lesson("CS2103T",
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            Day.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson MONDAY_9AM_1HR_LESSON = new Lesson("CS2103T",
            new LocalTime(9, 0),
            new LocalTime(10, 0),
            Day.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson MONDAY_ANOTHER_8AM_2HR_LESSON = new Lesson("CS2101",
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            Day.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson MONDAY_10AM_2HR_LESSON = new Lesson("CS2109S",
            new LocalTime(10, 0),
            new LocalTime(12, 0),
            Day.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_8AM_2HR_LESSON = new Lesson("CS2106",
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            Day.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_10AM_2HR_LESSON = new Lesson("CS2102",
            new LocalTime(10, 0),
            new LocalTime(12, 0),
            Day.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_10AM_1HR_LESSON = new Lesson("CS2108",
            new LocalTime(10, 0),
            new LocalTime(11, 0),
            Day.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_2PM_2HR_LESSON = new Lesson("CS2104",
            new LocalTime(14, 0),
            new LocalTime(16, 0),
            Day.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_4PM_1HR_LESSON = new Lesson("CS3263",
            new LocalTime(16, 0),
            new LocalTime(17, 0),
            Day.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson WEDNESDAY_10AM_3HR_LESSON = new Lesson("MA2101S",
            new LocalTime(10, 0),
            new LocalTime(13, 0),
            Day.WEDNESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson WEDNESDAY_2PM_2HR_LESSON = new Lesson("CS2108",
            new LocalTime(14, 0),
            new LocalTime(16, 0),
            Day.WEDNESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson WEDNESDAY_4PM_1HR_LESSON = new Lesson("MA2214",
            new LocalTime(16, 0),
            new LocalTime(17, 0),
            Day.WEDNESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson WEDNESDAY_6PM_1HR_LESSON = new Lesson("CS3263",
            new LocalTime(18, 0),
            new LocalTime(19, 0),
            Day.WEDNESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_8AM_2HR_LESSON = new Lesson("MA3236",
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            Day.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_12PM_2HR_LESSON = new Lesson("MA3236",
            new LocalTime(12, 0),
            new LocalTime(14, 0),
            Day.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_10AM_3HR_LESSON = new Lesson("MA3236",
            new LocalTime(10, 0),
            new LocalTime(13, 0),
            Day.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_1PM_3HR_LESSON = new Lesson("MA3236",
            new LocalTime(13, 0),
            new LocalTime(16, 0),
            Day.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_4PM_2HR_LESSON = new Lesson("MA3236",
            new LocalTime(16, 0),
            new LocalTime(18, 0),
            Day.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_11AM_2HR_LESSON = new Lesson("CS3230",
            new LocalTime(11, 0),
            new LocalTime(13, 0),
            Day.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_5PM_4HR_LESSON = new Lesson("CS3230",
            new LocalTime(17, 0),
            new LocalTime(21, 0),
            Day.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_9PM_1HR_LESSON = new Lesson("CS3230",
            new LocalTime(21, 0),
            new LocalTime(22, 0),
            Day.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_7PM_3HR_LESSON = new Lesson("MA3252",
            new LocalTime(19, 0),
            new LocalTime(22, 0),
            Day.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_9AM_1HR_LESSON = new Lesson("CS1101S",
            new LocalTime(9, 0),
            new LocalTime(10, 0),
            Day.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_9AM_2HR_LESSON = new Lesson("CS1231S",
            new LocalTime(9, 0),
            new LocalTime(11, 0),
            Day.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_8AM_1HR_LESSON = new Lesson("MA2104",
            new LocalTime(8, 0),
            new LocalTime(9, 0),
            Day.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_ANOTHER_8AM_1HR_LESSON = new Lesson("MA2104",
            new LocalTime(8, 0),
            new LocalTime(9, 0),
            Day.FRIDAY,
            new Location("NUS", 1.341, 103.71));

    public static final Lesson CS1010J_TUE_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS1010J")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS1010J_WED_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS1010J")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS1101S_MON_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson CS1101S_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS1101S_WED_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS1101S_WED_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS1101S_THU_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withStartTime(11)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS1101S_FRI_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS1231S_WED_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1231S")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS1231S_THU_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1231S")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS1231S_FRI_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS1231S")
            .withStartTime(13)
            .withDuration(1)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS2030S_MON_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson CS2030S_THU_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2030S_THU_1PM_1HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withStartTime(13)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2030S_THU_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2030S_WED_8AM_1HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withStartTime(8)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2030S_THU_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2040S_MON_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson CS2040S_TUE_9AM_2HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withStartTime(9)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS2040S_TUE_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS2040S_WED_2PM_1HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withStartTime(14)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2040S_WED_3PM_2HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withStartTime(15)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2040S_THU_9AM_1HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withStartTime(9)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2040S_THU_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withStartTime(10)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2040S_FRI_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withStartTime(10)
            .withDuration(1)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS2100_TUE_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS2100_WED_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withStartTime(11)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2100_WED_12PM_1HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withStartTime(12)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2100_FRI_9AM_1HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withStartTime(9)
            .withDuration(1)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS2100_FRI_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withStartTime(11)
            .withDuration(1)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS2101_MON_8AM_2HR = new LessonBuilder()
            .withModuleCode("CS2101")
            .withStartTime(8)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson CS2101_TUE_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2101")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS2101_THU_8AM_2HR = new LessonBuilder()
            .withModuleCode("CS2101")
            .withStartTime(8)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2101_FRI_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2101")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS2102_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS2102")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS2102_FRI_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS2102")
            .withStartTime(10)
            .withDuration(1)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS2103T_WED_2PM_1HR = new LessonBuilder()
            .withModuleCode("CS2103T")
            .withStartTime(14)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2103T_THU_1PM_1HR = new LessonBuilder()
            .withModuleCode("CS2103T")
            .withStartTime(13)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2103T_FRI_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS2103T")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS2105_MON_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS2105")
            .withStartTime(15)
            .withDuration(1)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson CS2105_THU_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS2105")
            .withStartTime(10)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2105_THU_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2105")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2106_MON_1PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(13)
            .withDuration(1)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson CS2106_WED_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2106_WED_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(15)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2106_WED_4PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(16)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2106_THU_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(11)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2106_THU_2PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(14)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2106_THU_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(15)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2106_THU_4PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(16)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2106_THU_5PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withStartTime(17)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2109S_TUE_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS2109S_WED_12PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withStartTime(12)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2109S_WED_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withStartTime(15)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2109S_WED_5PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withStartTime(17)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS2109S_THU_12PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withStartTime(12)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2108_TUE_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS2108")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS2108_THU_5PM_1HR = new LessonBuilder()
            .withModuleCode("CS2108")
            .withStartTime(17)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS2109S_MON_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson CS2109S_WED_1PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withStartTime(13)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS3223_THU_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS3223")
            .withStartTime(10)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS3223_FRI_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS3223")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS3230_TUE_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS3230")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS3230_WED_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS3230")
            .withStartTime(10)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson CS3245_THU_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS3245")
            .withStartTime(11)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS3245_THU_12PM_1HR = new LessonBuilder()
            .withModuleCode("CS3245")
            .withStartTime(12)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS3245_FRI_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS3245")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson CS4225_THU_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS4225")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS4225_THU_4PM_1HR = new LessonBuilder()
            .withModuleCode("CS4225")
            .withStartTime(16)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson CS4230_TUE_2PM_1HR = new LessonBuilder()
            .withModuleCode("CS4230")
            .withStartTime(14)
            .withDuration(1)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson CS4230_THU_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS4230")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson BT1101_MON_12PM_1HR = new LessonBuilder()
            .withModuleCode("BT1101")
            .withStartTime(12)
            .withDuration(1)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson BT1101_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("BT1101")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson BT1101_WED_4PM_2HR = new LessonBuilder()
            .withModuleCode("BT1101")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson BT2102_MON_10AM_2HR = new LessonBuilder()
            .withModuleCode("BT2102")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson BT2102_TUE_2PM_2HR = new LessonBuilder()
            .withModuleCode("BT2102")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson IS1108_MON_2PM_2HR = new LessonBuilder()
            .withModuleCode("IS1108")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson IS1108_THU_9AM_2HR = new LessonBuilder()
            .withModuleCode("IS1108")
            .withStartTime(9)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson IS2218_MON_9AM_2HR = new LessonBuilder()
            .withModuleCode("IS2218")
            .withStartTime(9)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson MA1521_MON_8AM_1HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withStartTime(8)
            .withDuration(1)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson MA1521_WED_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA1521_WED_6PM_2HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withStartTime(18)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA1521_THU_2PM_1HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withStartTime(14)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson MA1521_FRI_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson MA1521_FRI_6PM_2HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withStartTime(18)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson MA2001_MON_8AM_2HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withStartTime(8)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson MA2001_WED_2PM_1HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withStartTime(14)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA2001_THU_8AM_2HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withStartTime(8)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson MA2001_FRI_12PM_2HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson MA2001_FRI_3PM_1HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withStartTime(15)
            .withDuration(1)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson MA2101_WED_1PM_1HR = new LessonBuilder()
            .withModuleCode("MA2101")
            .withStartTime(13)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA2101_WED_4PM_2HR = new LessonBuilder()
            .withModuleCode("MA2101")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA2101_FRI_4PM_2HR = new LessonBuilder()
            .withModuleCode("MA2101")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson MA2104_MON_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson MA2104_MON_11AM_1HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withStartTime(11)
            .withDuration(1)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson MA2104_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson MA2104_WED_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA2104_WED_12PM_1HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withStartTime(12)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA2104_THU_3PM_1HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withStartTime(15)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson MA2104_FRI_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson MA2104_FRI_12PM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson MA2108_TUE_2PM_2HR = new LessonBuilder()
            .withModuleCode("MA2108")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson MA2108_THU_9AM_1HR = new LessonBuilder()
            .withModuleCode("MA2108")
            .withStartTime(9)
            .withDuration(1)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson MA2108_FRI_2PM_2HR = new LessonBuilder()
            .withModuleCode("MA2108")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson MA3252_WED_10AM_1HR = new LessonBuilder()
            .withModuleCode("MA3252")
            .withStartTime(10)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA3252_WED_9AM_1HR = new LessonBuilder()
            .withModuleCode("MA3252")
            .withStartTime(9)
            .withDuration(1)
            .withDay(Day.WEDNESDAY)
            .build();

    public static final Lesson MA3252_FRI_7PM_3HR = new LessonBuilder()
            .withModuleCode("MA3252")
            .withStartTime(19)
            .withDuration(3)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson ST2131_TUE_6PM_2HR = new LessonBuilder()
            .withModuleCode("ST2131")
            .withStartTime(18)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson ST2131_FRI_6PM_2HR = new LessonBuilder()
            .withModuleCode("ST2131")
            .withStartTime(18)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson ST2334_MON_2PM_1HR = new LessonBuilder()
            .withModuleCode("ST2334")
            .withStartTime(14)
            .withDuration(1)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson ST2334_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("ST2334")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson ST2334_FRI_12PM_2HR = new LessonBuilder()
            .withModuleCode("ST2334")
            .withStartTime(12)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson GEC1030_MON_8AM_2HR = new LessonBuilder()
            .withModuleCode("GEC1030")
            .withStartTime(8)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson GEC1030_TUE_4PM_2HR = new LessonBuilder()
            .withModuleCode("GEC1030")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson GEA1000_THU_3PM_3HR = new LessonBuilder()
            .withModuleCode("GEA1000")
            .withStartTime(15)
            .withDuration(3)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson GESS1019_MON_10AM_2HR = new LessonBuilder()
            .withModuleCode("GESS1019")
            .withStartTime(10)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson GESS1019_TUE_2PM_2HR = new LessonBuilder()
            .withModuleCode("GESS1019")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson GEN2050_FRI_4PM_2HR = new LessonBuilder()
            .withModuleCode("GEN2050")
            .withStartTime(16)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson ES2660_MON_2PM_2HR = new LessonBuilder()
            .withModuleCode("ES2660")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson ES2660_THU_2PM_2HR = new LessonBuilder()
            .withModuleCode("ES2660")
            .withStartTime(14)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();

    public static final Lesson ES2660_TUE_8AM_2HR = new LessonBuilder()
            .withModuleCode("ES2660")
            .withStartTime(8)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson ES2660_FRI_8AM_2HR = new LessonBuilder()
            .withModuleCode("ES2660")
            .withStartTime(8)
            .withDuration(2)
            .withDay(Day.FRIDAY)
            .build();

    public static final Lesson LAJ1201_MON_6PM_2HR = new LessonBuilder()
            .withModuleCode("LAJ1201")
            .withStartTime(18)
            .withDuration(2)
            .withDay(Day.MONDAY)
            .build();

    public static final Lesson LAJ1201_TUE_6PM_2HR = new LessonBuilder()
            .withModuleCode("LAJ1201")
            .withStartTime(18)
            .withDuration(2)
            .withDay(Day.TUESDAY)
            .build();

    public static final Lesson LAJ1201_THU_6PM_2HR = new LessonBuilder()
            .withModuleCode("LAJ1201")
            .withStartTime(18)
            .withDuration(2)
            .withDay(Day.THURSDAY)
            .build();
}

