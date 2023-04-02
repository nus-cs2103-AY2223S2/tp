package seedu.address.model.timetable.util;

import org.joda.time.LocalTime;

import seedu.address.model.commitment.Lesson;
import seedu.address.model.location.Location;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimePeriod;

/**
 * Builds a lesson.
 */
public class LessonBuilder {
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final LocalTime DEFAULT_START_TIME = new LocalTime(10, 0);
    public static final LocalTime DEFAULT_END_TIME = new LocalTime(12, 0);
    public static final Day DEFAULT_DAY = Day.MONDAY;

    private String moduleCode;
    private LocalTime startTime;
    private LocalTime endTime;
    private Day day;

    /**
     * Constructs a LessonBuilder.
     */
    public LessonBuilder() {
        moduleCode = DEFAULT_MODULE_CODE;
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
        day = DEFAULT_DAY;
    }

    /**
     * Constructs a LessonBuilder with information from a lesson.
     */
    public LessonBuilder(Lesson lesson) {
        moduleCode = lesson.getModuleCode();
        startTime = lesson.getStartTime();
        endTime = lesson.getEndTime();
        day = lesson.getDay();
    }

    /**
     * Creates a new LessonBuilder with updated field.
     */
    public LessonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    /**
     * Creates a new LessonBuilder with updated field.
     */
    public LessonBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Creates a new LessonBuilder with updated field.
     */
    public LessonBuilder withStartTime(int startTimeAsInt) {
        this.startTime = new LocalTime(startTimeAsInt, 0);
        return this;
    }

    /**
     * Creates a new LessonBuilder with updated field.
     */
    public LessonBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Creates a new LessonBuilder with updated field.
     */
    public LessonBuilder withEndTime(int endTimeAsInt) {
        this.endTime = new LocalTime(endTimeAsInt, 0);
        return this;
    }

    /**
     * Creates a new LessonBuilder with updated field.
     * End time is calculated based on start time and duration.
     */
    public LessonBuilder withDuration(int durationInHours) {
        endTime = startTime.plusHours(durationInHours);
        return this;
    }

    /**
     * Creates a new LessonBuilder with updated time period.
     */
    public LessonBuilder withTimePeriod(TimePeriod timePeriod) {
        startTime = timePeriod.getStartTime();
        endTime = timePeriod.getEndTime();
        day = timePeriod.getSchoolDay();
        return this;
    }

    /**
     * Creates a new LessonBuilder with updated field.
     */
    public LessonBuilder withDay(Day day) {
        this.day = day;
        return this;
    }

    /**
     * Creates a Lesson.
     */
    public Lesson build() {
        return new Lesson(moduleCode, startTime,
                endTime, day, Location.NUS);
    }
}
