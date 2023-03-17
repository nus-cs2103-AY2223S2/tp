package seedu.address.model.timetable.util;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;
import seedu.address.model.timetable.Lesson;
import seedu.address.model.timetable.Module;
import seedu.address.model.timetable.SchoolDay;


/**
 * Represents some of Typical Lessons.
 */
public class TypicalLesson {

    public static final Lesson MONDAY_FIRST_LESSON = new Lesson(new Module("CS2103T"),
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            SchoolDay.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson MONDAY_ANOTHER_FIRST_LESSON = new Lesson(new Module("CS2103T"),
            new LocalTime(8, 0),
            new LocalTime(10, 0),
            SchoolDay.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson SECOND_LESSON = new Lesson(new Module("CS2109S"),
            new LocalTime(10, 0),
            new LocalTime(12, 0),
            SchoolDay.MONDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_NIGHT_LESSON = new Lesson(new Module("MA3252"),
            new LocalTime(19, 0),
            new LocalTime(22, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_NINE_AM_1_HR_LESSON = new Lesson(new Module("CS1101S"),
            new LocalTime(9, 0),
            new LocalTime(10, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_NINE_AM_2_HR_LESSON = new Lesson(new Module("CS1231S"),
            new LocalTime(9, 0),
            new LocalTime(11, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.34, 103.7));

    public static final Lesson FRIDAY_MORNING_LESSON = new Lesson(new Module("MA2104"),
            new LocalTime(8, 0),
            new LocalTime(9, 0),
            SchoolDay.FRIDAY,
            new Location("NUS", 1.34, 103.7));
}
