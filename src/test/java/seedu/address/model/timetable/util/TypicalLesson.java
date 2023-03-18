package seedu.address.model.timetable.util;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;
import seedu.address.model.timetable.Lesson;
import seedu.address.model.timetable.Module;
import seedu.address.model.timetable.time.SchoolDay;


/**
 * Represents some of Typical Lessons.
 */
public class TypicalLesson {

    public static final Lesson MONDAY_8AM_2HR_LESSON = new Lesson(new Module("CS2103T"),
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            SchoolDay.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson MONDAY_9AM_1HR_LESSON = new Lesson(new Module("CS2103T"),
            new LocalTime(9, 0),
            new LocalTime(10, 0),
            SchoolDay.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson MONDAY_ANOTHER_8AM_2HR_LESSON = new Lesson(new Module("CS2101"),
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            SchoolDay.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson MONDAY_10AM_2HR_LESSON = new Lesson(new Module("CS2109S"),
            new LocalTime(10, 0),
            new LocalTime(12, 0),
            SchoolDay.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_8AM_2HR_LESSON = new Lesson(new Module("CS2106"),
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            SchoolDay.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_10AM_2HR_LESSON = new Lesson(new Module("CS2102"),
            new LocalTime(10, 0),
            new LocalTime(12, 0),
            SchoolDay.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_10AM_1HR_LESSON = new Lesson(new Module("CS2108"),
            new LocalTime(10, 0),
            new LocalTime(11, 0),
            SchoolDay.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_2PM_2HR_LESSON = new Lesson(new Module("CS2104"),
            new LocalTime(14, 0),
            new LocalTime(16, 0),
            SchoolDay.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson TUESDAY_4PM_1HR_LESSON = new Lesson(new Module("CS3263"),
            new LocalTime(16, 0),
            new LocalTime(17, 0),
            SchoolDay.TUESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson WEDNESDAY_10AM_3HR_LESSON = new Lesson(new Module("MA2101S"),
            new LocalTime(10, 0),
            new LocalTime(13, 0),
            SchoolDay.WEDNESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson WEDNESDAY_2PM_2HR_LESSON = new Lesson(new Module("CS2108"),
            new LocalTime(14, 0),
            new LocalTime(16, 0),
            SchoolDay.WEDNESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson WEDNESDAY_4PM_1HR_LESSON = new Lesson(new Module("MA2214"),
            new LocalTime(16, 0),
            new LocalTime(17, 0),
            SchoolDay.WEDNESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson WEDNESDAY_6PM_1HR_LESSON = new Lesson(new Module("CS3263"),
            new LocalTime(18, 0),
            new LocalTime(19, 0),
            SchoolDay.WEDNESDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_8AM_2HR_LESSON = new Lesson(new Module("MA3236"),
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            SchoolDay.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_12PM_2HR_LESSON = new Lesson(new Module("MA3236"),
            new LocalTime(12, 0),
            new LocalTime(14, 0),
            SchoolDay.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_10AM_3HR_LESSON = new Lesson(new Module("MA3236"),
            new LocalTime(10, 0),
            new LocalTime(13, 0),
            SchoolDay.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_1PM_3HR_LESSON = new Lesson(new Module("MA3236"),
            new LocalTime(13, 0),
            new LocalTime(16, 0),
            SchoolDay.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_4PM_2HR_LESSON = new Lesson(new Module("MA3236"),
            new LocalTime(16, 0),
            new LocalTime(18, 0),
            SchoolDay.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_11AM_2HR_LESSON = new Lesson(new Module("CS3230"),
            new LocalTime(11, 0),
            new LocalTime(13, 0),
            SchoolDay.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_5PM_4HR_LESSON = new Lesson(new Module("CS3230"),
            new LocalTime(17, 0),
            new LocalTime(21, 0),
            SchoolDay.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson THURSDAY_9PM_1HR_LESSON = new Lesson(new Module("CS3230"),
            new LocalTime(21, 0),
            new LocalTime(22, 0),
            SchoolDay.THURSDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_7PM_3HR_LESSON = new Lesson(new Module("MA3252"),
            new LocalTime(19, 0),
            new LocalTime(22, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_9AM_1HR_LESSON = new Lesson(new Module("CS1101S"),
            new LocalTime(9, 0),
            new LocalTime(10, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_9AM_2HR_LESSON = new Lesson(new Module("CS1231S"),
            new LocalTime(9, 0),
            new LocalTime(11, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_8AM_1HR_LESSON = new Lesson(new Module("MA2104"),
            new LocalTime(8, 0),
            new LocalTime(9, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_ANOTHER_8AM_1HR_LESSON = new Lesson(new Module("MA2104"),
            new LocalTime(8, 0),
            new LocalTime(9, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.341, 103.71));
}
