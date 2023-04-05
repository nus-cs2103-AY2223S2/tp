package seedu.address.model.timetable.util;

import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_10AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_10AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_11AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_12PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_2PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_3PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_4PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_6PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_7PM_3HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_8AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.FRI_9AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_10AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_11AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_12PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_12PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_1PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_2PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_2PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_3PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_4PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_6PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_8AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_8AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.MON_9AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_10AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_10AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_11AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_12PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_12PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_1PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_2PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_2PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_3PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_3PM_3HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_4PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_4PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_5PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_6PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_8AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_9AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_9AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TUE_10AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TUE_12PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TUE_2PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TUE_2PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TUE_4PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TUE_6PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TUE_8AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TUE_9AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_10AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_10AM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_11AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_12PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_12PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_1PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_2PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_2PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_3PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_3PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_4PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_4PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_5PM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_6PM_2HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_8AM_1HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.WED_9AM_1HR;

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
            .withTimePeriod(TUE_2PM_2HR)
            .build();

    public static final Lesson CS1010J_WED_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS1010J")
            .withTimePeriod(WED_2PM_2HR)
            .build();

    public static final Lesson CS1101S_MON_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withTimePeriod(MON_12PM_2HR)
            .build();

    public static final Lesson CS1101S_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withTimePeriod(TUE_12PM_2HR)
            .build();

    public static final Lesson CS1101S_WED_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withTimePeriod(WED_10AM_2HR)
            .build();

    public static final Lesson CS1101S_WED_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withTimePeriod(WED_12PM_2HR)
            .build();

    public static final Lesson CS1101S_THU_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withTimePeriod(THU_11AM_1HR)
            .build();

    public static final Lesson CS1101S_FRI_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS1101S")
            .withTimePeriod(FRI_10AM_2HR)
            .build();

    public static final Lesson CS1231S_WED_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1231S")
            .withTimePeriod(WED_12PM_2HR)
            .build();

    public static final Lesson CS1231S_THU_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS1231S")
            .withTimePeriod(THU_12PM_2HR)
            .build();

    public static final Lesson CS1231S_FRI_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS1231S")
            .withTimePeriod(FRI_3PM_1HR)
            .build();

    public static final Lesson CS2030S_MON_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withTimePeriod(MON_12PM_2HR)
            .build();

    public static final Lesson CS2030S_THU_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withTimePeriod(THU_10AM_2HR)
            .build();

    public static final Lesson CS2030S_THU_1PM_1HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withTimePeriod(THU_1PM_1HR)
            .build();

    public static final Lesson CS2030S_THU_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withTimePeriod(THU_2PM_2HR)
            .build();

    public static final Lesson CS2030S_WED_8AM_1HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withTimePeriod(WED_8AM_1HR)
            .build();

    public static final Lesson CS2030S_THU_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS2030S")
            .withTimePeriod(THU_12PM_2HR)
            .build();

    public static final Lesson CS2040S_MON_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withTimePeriod(MON_4PM_2HR)
            .build();

    public static final Lesson CS2040S_TUE_9AM_2HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withTimePeriod(TUE_9AM_2HR)
            .build();

    public static final Lesson CS2040S_TUE_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withTimePeriod(TUE_10AM_2HR)
            .build();

    public static final Lesson CS2040S_WED_2PM_1HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withTimePeriod(WED_2PM_1HR)
            .build();

    public static final Lesson CS2040S_WED_3PM_2HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withTimePeriod(WED_3PM_2HR)
            .build();

    public static final Lesson CS2040S_THU_9AM_1HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withTimePeriod(THU_9AM_1HR)
            .build();

    public static final Lesson CS2040S_THU_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withTimePeriod(THU_10AM_1HR)
            .build();

    public static final Lesson CS2040S_FRI_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS2040S")
            .withTimePeriod(FRI_10AM_1HR)
            .build();

    public static final Lesson CS2100_TUE_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withTimePeriod(TUE_4PM_2HR)
            .build();

    public static final Lesson CS2100_WED_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withTimePeriod(WED_11AM_1HR)
            .build();

    public static final Lesson CS2100_WED_12PM_1HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withTimePeriod(WED_12PM_1HR)
            .build();

    public static final Lesson CS2100_FRI_9AM_1HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withTimePeriod(FRI_9AM_1HR)
            .build();

    public static final Lesson CS2100_FRI_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS2100")
            .withTimePeriod(FRI_11AM_1HR)
            .build();

    public static final Lesson CS2101_MON_8AM_2HR = new LessonBuilder()
            .withModuleCode("CS2101")
            .withTimePeriod(MON_8AM_2HR)
            .build();

    public static final Lesson CS2101_TUE_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2101")
            .withTimePeriod(TUE_4PM_2HR)
            .build();

    public static final Lesson CS2101_THU_8AM_2HR = new LessonBuilder()
            .withModuleCode("CS2101")
            .withTimePeriod(THU_8AM_2HR)
            .build();

    public static final Lesson CS2101_FRI_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2101")
            .withTimePeriod(FRI_4PM_2HR)
            .build();

    public static final Lesson CS2102_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS2102")
            .withTimePeriod(TUE_12PM_2HR)
            .build();

    public static final Lesson CS2102_FRI_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS2102")
            .withTimePeriod(FRI_10AM_1HR)
            .build();

    public static final Lesson CS2103T_WED_2PM_1HR = new LessonBuilder()
            .withModuleCode("CS2103T")
            .withTimePeriod(WED_2PM_1HR)
            .build();

    public static final Lesson CS2103T_THU_1PM_1HR = new LessonBuilder()
            .withModuleCode("CS2103T")
            .withTimePeriod(THU_1PM_1HR)
            .build();

    public static final Lesson CS2103T_FRI_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS2103T")
            .withTimePeriod(FRI_2PM_2HR)
            .build();

    public static final Lesson CS2105_MON_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS2105")
            .withTimePeriod(MON_3PM_1HR)
            .build();

    public static final Lesson CS2105_THU_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS2105")
            .withTimePeriod(THU_10AM_1HR)
            .build();

    public static final Lesson CS2105_THU_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2105")
            .withTimePeriod(THU_4PM_2HR)
            .build();

    public static final Lesson CS2106_MON_1PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(MON_1PM_1HR)
            .build();

    public static final Lesson CS2106_WED_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(WED_10AM_2HR)
            .build();

    public static final Lesson CS2106_WED_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(WED_3PM_1HR)
            .build();

    public static final Lesson CS2106_WED_4PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(WED_4PM_1HR)
            .build();

    public static final Lesson CS2106_THU_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(THU_11AM_1HR)
            .build();

    public static final Lesson CS2106_THU_2PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(THU_2PM_1HR)
            .build();

    public static final Lesson CS2106_THU_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(THU_3PM_1HR)
            .build();

    public static final Lesson CS2106_THU_4PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(THU_4PM_1HR)
            .build();

    public static final Lesson CS2106_THU_5PM_1HR = new LessonBuilder()
            .withModuleCode("CS2106")
            .withTimePeriod(THU_5PM_1HR)
            .build();

    public static final Lesson CS2109S_TUE_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withTimePeriod(TUE_4PM_2HR)
            .build();

    public static final Lesson CS2109S_WED_12PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withTimePeriod(WED_12PM_1HR)
            .build();

    public static final Lesson CS2109S_WED_3PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withTimePeriod(WED_3PM_1HR)
            .build();

    public static final Lesson CS2109S_WED_5PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withTimePeriod(WED_5PM_1HR)
            .build();

    public static final Lesson CS2109S_THU_12PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withTimePeriod(THU_12PM_1HR)
            .build();

    public static final Lesson CS2108_TUE_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS2108")
            .withTimePeriod(TUE_10AM_2HR)
            .build();

    public static final Lesson CS2108_THU_5PM_1HR = new LessonBuilder()
            .withModuleCode("CS2108")
            .withTimePeriod(THU_5PM_1HR)
            .build();

    public static final Lesson CS2109S_MON_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withTimePeriod(MON_2PM_2HR)
            .build();

    public static final Lesson CS2109S_WED_1PM_1HR = new LessonBuilder()
            .withModuleCode("CS2109S")
            .withTimePeriod(WED_1PM_1HR)
            .build();

    public static final Lesson CS3223_THU_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS3223")
            .withTimePeriod(THU_10AM_1HR)
            .build();

    public static final Lesson CS3223_FRI_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS3223")
            .withTimePeriod(FRI_10AM_2HR)
            .build();

    public static final Lesson CS3230_TUE_10AM_2HR = new LessonBuilder()
            .withModuleCode("CS3230")
            .withTimePeriod(TUE_10AM_2HR)
            .build();

    public static final Lesson CS3230_WED_10AM_1HR = new LessonBuilder()
            .withModuleCode("CS3230")
            .withTimePeriod(WED_10AM_1HR)
            .build();

    public static final Lesson CS3245_THU_11AM_1HR = new LessonBuilder()
            .withModuleCode("CS3245")
            .withTimePeriod(THU_11AM_1HR)
            .build();

    public static final Lesson CS3245_THU_12PM_1HR = new LessonBuilder()
            .withModuleCode("CS3245")
            .withTimePeriod(THU_12PM_1HR)
            .build();

    public static final Lesson CS3245_FRI_12PM_2HR = new LessonBuilder()
            .withModuleCode("CS3245")
            .withTimePeriod(FRI_12PM_2HR)
            .build();

    public static final Lesson CS4225_THU_2PM_2HR = new LessonBuilder()
            .withModuleCode("CS4225")
            .withTimePeriod(THU_2PM_2HR)
            .build();

    public static final Lesson CS4225_THU_4PM_1HR = new LessonBuilder()
            .withModuleCode("CS4225")
            .withTimePeriod(THU_4PM_1HR)
            .build();

    public static final Lesson CS4230_TUE_2PM_1HR = new LessonBuilder()
            .withModuleCode("CS4230")
            .withTimePeriod(TUE_2PM_1HR)
            .build();

    public static final Lesson CS4230_THU_4PM_2HR = new LessonBuilder()
            .withModuleCode("CS4230")
            .withTimePeriod(THU_4PM_2HR)
            .build();

    public static final Lesson BT1101_MON_12PM_1HR = new LessonBuilder()
            .withModuleCode("BT1101")
            .withTimePeriod(MON_12PM_1HR)
            .build();

    public static final Lesson BT1101_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("BT1101")
            .withTimePeriod(TUE_12PM_2HR)
            .build();

    public static final Lesson BT1101_WED_4PM_2HR = new LessonBuilder()
            .withModuleCode("BT1101")
            .withTimePeriod(WED_4PM_2HR)
            .build();

    public static final Lesson BT2102_MON_10AM_2HR = new LessonBuilder()
            .withModuleCode("BT2102")
            .withTimePeriod(MON_10AM_2HR)
            .build();

    public static final Lesson BT2102_TUE_2PM_2HR = new LessonBuilder()
            .withModuleCode("BT2102")
            .withTimePeriod(TUE_2PM_2HR)
            .build();

    public static final Lesson IS1108_MON_2PM_2HR = new LessonBuilder()
            .withModuleCode("IS1108")
            .withTimePeriod(MON_2PM_2HR)
            .build();

    public static final Lesson IS1108_THU_9AM_2HR = new LessonBuilder()
            .withModuleCode("IS1108")
            .withTimePeriod(THU_9AM_2HR)
            .build();

    public static final Lesson IS2218_MON_9AM_2HR = new LessonBuilder()
            .withModuleCode("IS2218")
            .withTimePeriod(MON_9AM_2HR)
            .build();

    public static final Lesson MA1521_MON_8AM_1HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withTimePeriod(MON_8AM_1HR)
            .build();

    public static final Lesson MA1521_WED_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withTimePeriod(WED_10AM_2HR)
            .build();

    public static final Lesson MA1521_WED_6PM_2HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withTimePeriod(WED_6PM_2HR)
            .build();

    public static final Lesson MA1521_THU_2PM_1HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withTimePeriod(THU_2PM_1HR)
            .build();

    public static final Lesson MA1521_FRI_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withTimePeriod(FRI_10AM_2HR)
            .build();

    public static final Lesson MA1521_FRI_6PM_2HR = new LessonBuilder()
            .withModuleCode("MA1521")
            .withTimePeriod(FRI_6PM_2HR)
            .build();

    public static final Lesson MA2001_MON_8AM_2HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withTimePeriod(MON_8AM_2HR)
            .build();

    public static final Lesson MA2001_WED_2PM_1HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withTimePeriod(WED_2PM_1HR)
            .build();

    public static final Lesson MA2001_THU_8AM_2HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withTimePeriod(THU_8AM_2HR)
            .build();

    public static final Lesson MA2001_FRI_12PM_2HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withTimePeriod(FRI_12PM_2HR)
            .build();

    public static final Lesson MA2001_FRI_3PM_1HR = new LessonBuilder()
            .withModuleCode("MA2001")
            .withTimePeriod(FRI_3PM_1HR)
            .build();

    public static final Lesson MA2101_WED_1PM_1HR = new LessonBuilder()
            .withModuleCode("MA2101")
            .withTimePeriod(WED_1PM_1HR)
            .build();

    public static final Lesson MA2101_WED_4PM_2HR = new LessonBuilder()
            .withModuleCode("MA2101")
            .withTimePeriod(WED_4PM_2HR)
            .build();

    public static final Lesson MA2101_FRI_4PM_2HR = new LessonBuilder()
            .withModuleCode("MA2101")
            .withTimePeriod(FRI_4PM_2HR)
            .build();

    public static final Lesson MA2104_MON_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withTimePeriod(MON_10AM_2HR)
            .build();

    public static final Lesson MA2104_MON_11AM_1HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withTimePeriod(MON_11AM_1HR)
            .build();

    public static final Lesson MA2104_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withTimePeriod(TUE_12PM_2HR)
            .build();

    public static final Lesson MA2104_WED_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withTimePeriod(WED_10AM_2HR)
            .build();

    public static final Lesson MA2104_WED_12PM_1HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withTimePeriod(WED_12PM_1HR)
            .build();

    public static final Lesson MA2104_THU_3PM_1HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withTimePeriod(THU_3PM_1HR)
            .build();

    public static final Lesson MA2104_FRI_10AM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withTimePeriod(FRI_10AM_2HR)
            .build();

    public static final Lesson MA2104_FRI_12PM_2HR = new LessonBuilder()
            .withModuleCode("MA2104")
            .withTimePeriod(FRI_12PM_2HR)
            .build();

    public static final Lesson MA2108_TUE_2PM_2HR = new LessonBuilder()
            .withModuleCode("MA2108")
            .withTimePeriod(TUE_2PM_2HR)
            .build();

    public static final Lesson MA2108_THU_9AM_1HR = new LessonBuilder()
            .withModuleCode("MA2108")
            .withTimePeriod(THU_9AM_1HR)
            .build();

    public static final Lesson MA2108_FRI_2PM_2HR = new LessonBuilder()
            .withModuleCode("MA2108")
            .withTimePeriod(FRI_2PM_2HR)
            .build();

    public static final Lesson MA3252_WED_10AM_1HR = new LessonBuilder()
            .withModuleCode("MA3252")
            .withTimePeriod(WED_10AM_1HR)
            .build();

    public static final Lesson MA3252_WED_9AM_1HR = new LessonBuilder()
            .withModuleCode("MA3252")
            .withTimePeriod(WED_9AM_1HR)
            .build();

    public static final Lesson MA3252_FRI_7PM_3HR = new LessonBuilder()
            .withModuleCode("MA3252")
            .withTimePeriod(FRI_7PM_3HR)
            .build();

    public static final Lesson ST2131_TUE_6PM_2HR = new LessonBuilder()
            .withModuleCode("ST2131")
            .withTimePeriod(TUE_6PM_2HR)
            .build();

    public static final Lesson ST2131_FRI_6PM_2HR = new LessonBuilder()
            .withModuleCode("ST2131")
            .withTimePeriod(FRI_6PM_2HR)
            .build();

    public static final Lesson ST2334_MON_2PM_1HR = new LessonBuilder()
            .withModuleCode("ST2334")
            .withTimePeriod(MON_2PM_1HR)
            .build();

    public static final Lesson ST2334_TUE_12PM_2HR = new LessonBuilder()
            .withModuleCode("ST2334")
            .withTimePeriod(TUE_12PM_2HR)
            .build();

    public static final Lesson ST2334_FRI_12PM_2HR = new LessonBuilder()
            .withModuleCode("ST2334")
            .withTimePeriod(FRI_12PM_2HR)
            .build();

    public static final Lesson GEC1030_MON_8AM_2HR = new LessonBuilder()
            .withModuleCode("GEC1030")
            .withTimePeriod(MON_8AM_2HR)
            .build();

    public static final Lesson GEC1030_TUE_4PM_2HR = new LessonBuilder()
            .withModuleCode("GEC1030")
            .withTimePeriod(TUE_4PM_2HR)
            .build();

    public static final Lesson GEA1000_THU_3PM_3HR = new LessonBuilder()
            .withModuleCode("GEA1000")
            .withTimePeriod(THU_3PM_3HR)
            .build();

    public static final Lesson GESS1019_MON_10AM_2HR = new LessonBuilder()
            .withModuleCode("GESS1019")
            .withTimePeriod(MON_10AM_2HR)
            .build();

    public static final Lesson GESS1019_TUE_2PM_2HR = new LessonBuilder()
            .withModuleCode("GESS1019")
            .withTimePeriod(TUE_2PM_2HR)
            .build();

    public static final Lesson GEN2050_FRI_4PM_2HR = new LessonBuilder()
            .withModuleCode("GEN2050")
            .withTimePeriod(FRI_4PM_2HR)
            .build();

    public static final Lesson ES2660_MON_2PM_2HR = new LessonBuilder()
            .withModuleCode("ES2660")
            .withTimePeriod(MON_2PM_2HR)
            .build();

    public static final Lesson ES2660_THU_2PM_2HR = new LessonBuilder()
            .withModuleCode("ES2660")
            .withTimePeriod(THU_2PM_2HR)
            .build();

    public static final Lesson ES2660_TUE_8AM_2HR = new LessonBuilder()
            .withModuleCode("ES2660")
            .withTimePeriod(TUE_8AM_2HR)
            .build();

    public static final Lesson ES2660_FRI_8AM_2HR = new LessonBuilder()
            .withModuleCode("ES2660")
            .withTimePeriod(FRI_8AM_2HR)
            .build();

    public static final Lesson LAJ1201_MON_6PM_2HR = new LessonBuilder()
            .withModuleCode("LAJ1201")
            .withTimePeriod(MON_6PM_2HR)
            .build();

    public static final Lesson LAJ1201_TUE_6PM_2HR = new LessonBuilder()
            .withModuleCode("LAJ1201")
            .withTimePeriod(TUE_6PM_2HR)
            .build();

    public static final Lesson LAJ1201_THU_6PM_2HR = new LessonBuilder()
            .withModuleCode("LAJ1201")
            .withTimePeriod(THU_6PM_2HR)
            .build();
}

