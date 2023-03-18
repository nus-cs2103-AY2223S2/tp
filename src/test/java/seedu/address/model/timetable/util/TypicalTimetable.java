package seedu.address.model.timetable.util;

import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_MORNING_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_NIGHT_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_NINE_AM_1_HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.FRIDAY_NINE_AM_2_HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_ANOTHER_FIRST_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_FIRST_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.MONDAY_SECOND_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.THURSDAY_11AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.THURSDAY_4PM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.THURSDAY_5PM_4HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.TUESDAY_10AM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.TUESDAY_2PM_2HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.TUESDAY_4PM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.TUESDAY_FIRST_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.WEDNESDAY_10AM_3HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.WEDNESDAY_4PM_1HR_LESSON;
import static seedu.address.model.timetable.util.TypicalLesson.WEDNESDAY_6PM_1HR_LESSON;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.timetable.Lesson;
import seedu.address.model.timetable.Timetable;

/**
 * Composes of all the Typical Timetables
 */
public class TypicalTimetable {

    public static final Timetable TIMETABLE_A = new TimetableBuilder()
            .withLesson(MONDAY_FIRST_LESSON)
            .withLesson(MONDAY_SECOND_LESSON)
            .withLesson(TUESDAY_10AM_2HR_LESSON)
            .withLesson(TUESDAY_4PM_1HR_LESSON)
            .withLesson(THURSDAY_4PM_2HR_LESSON)
            .withLesson(FRIDAY_MORNING_LESSON)
            .withLesson(FRIDAY_NIGHT_LESSON)
            .build();

    public static final Timetable TIMETABLE_B = new TimetableBuilder()
            .withLesson(MONDAY_SECOND_LESSON)
            .withLesson(TUESDAY_4PM_1HR_LESSON)
            .withLesson(WEDNESDAY_6PM_1HR_LESSON)
            .withLesson(THURSDAY_5PM_4HR_LESSON)
            .withLesson(FRIDAY_NINE_AM_2_HR_LESSON)
            .build();

    public static final Timetable TIMETABLE_C = new TimetableBuilder()
            .withLesson(MONDAY_ANOTHER_FIRST_LESSON)
            .withLesson(TUESDAY_FIRST_LESSON)
            .withLesson(TUESDAY_2PM_2HR_LESSON)
            .withLesson(WEDNESDAY_10AM_3HR_LESSON)
            .withLesson(THURSDAY_11AM_2HR_LESSON)
            .withLesson(FRIDAY_NINE_AM_1_HR_LESSON)
            .build();

    public static final Timetable TIMETABLE_D = new TimetableBuilder()
            .withLesson(TUESDAY_2PM_2HR_LESSON)
            .withLesson(TUESDAY_4PM_1HR_LESSON)
            .withLesson(WEDNESDAY_4PM_1HR_LESSON)
            .withLesson(FRIDAY_NINE_AM_2_HR_LESSON)
            .build();

    public static final Timetable TIMETABLE_E = new TimetableBuilder()
            .withLesson(MONDAY_SECOND_LESSON)
            .withLesson(TUESDAY_10AM_2HR_LESSON)
            .withLesson(THURSDAY_11AM_2HR_LESSON)
            .withLesson(FRIDAY_NIGHT_LESSON)
            .build();

}

/**
 * Creates a new timetable with lessons.
 */
class TimetableBuilder {

    private List<Lesson> lessons;

    public TimetableBuilder() {
        lessons = new ArrayList<>();
    }

    public TimetableBuilder withLesson(Lesson lesson) {
        lessons.add(lesson);
        return this;
    }

    public Timetable build() {
        Timetable timetable = new Timetable();
        lessons.stream().forEach(timetable::addLesson);
        return timetable;
    }

}
