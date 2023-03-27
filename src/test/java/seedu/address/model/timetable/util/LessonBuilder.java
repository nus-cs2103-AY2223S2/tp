package seedu.address.model.timetable.util;

import org.joda.time.LocalTime;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.location.Location;
import seedu.address.model.time.Day;

class LessonBuilder {
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final LocalTime DEFAULT_START_TIME = new LocalTime(10, 0);
    public static final LocalTime DEFAULT_END_TIME = new LocalTime(12, 0);
    public static final Day DEFAULT_DAY = Day.MONDAY;

    private String moduleCode;
    private LocalTime startTime;
    private LocalTime endTime;
    private Day day;

    public LessonBuilder() {
        moduleCode = DEFAULT_MODULE_CODE;
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
        day = DEFAULT_DAY;
    }

    public LessonBuilder(Lesson lesson) {
        moduleCode = lesson.getModuleCode();
        startTime = lesson.getStartTime();
        endTime = lesson.getEndTime();
        day = lesson.getDay();
    }

    public LessonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public LessonBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LessonBuilder withStartTime(int startTimeAsInt) {
        this.startTime = new LocalTime(startTimeAsInt, 0);
        return this;
    }

    public LessonBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public LessonBuilder withEndTime(int endTimeAsInt) {
        this.endTime = new LocalTime(endTimeAsInt, 0);
        return this;
    }

    public LessonBuilder withDuration(int durationInHours) {
        endTime = startTime.plusHours(durationInHours);
        return this;
    }

    public LessonBuilder withDay(Day day) {
        this.day = day;
        return this;
    }

    public Lesson build() {
        return new Lesson(moduleCode, startTime,
                endTime, day, Location.NUS);
    }
}
