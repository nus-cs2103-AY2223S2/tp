package seedu.address.model.timetable;

import java.util.Optional;

import org.joda.time.LocalTime;
import seedu.address.model.timetable.exceptions.LessonClashException;
import seedu.address.model.timetable.exceptions.WrongTimeException;


/**
 * Represents an hour timeslot in a Timetable.
 */
public class TimeSlot {

    public static final String WRONG_TIME_MESSAGE = "Timing does not match!";
    public static final String ALREADY_FILLED_MESSAGE = "Slot is already filled by a class!";

    private LocalTime startTime;
    private Optional<Lesson> lesson = Optional.empty();

    public TimeSlot(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return startTime.plusHours(1);
    }

    public Optional<Lesson> getLesson() {
        return lesson;
    }

    public boolean isFree() {
        return lesson.isEmpty();
    }

    /**
     * Sets this slot to hold the current lesson.
     * @param lesson
     */
    public void setLesson(Lesson lesson) {
        if (!isFree()) {
            throw new LessonClashException(ALREADY_FILLED_MESSAGE);
        } else if (canFitLesson(lesson)) {
            this.lesson = Optional.ofNullable(lesson);
        } else {
            throw new WrongTimeException(WRONG_TIME_MESSAGE);
        }
    }

    /**
     * Verifies if the timeslot is suitable to input that lesson.
     */
    public boolean canFitLesson(Lesson lesson) {
        // timeslot must either start match with start or end match with end
        // or that timeslot start is after lesson start AND timeslot end is before lesson end.
        LocalTime lessonStartTime = lesson.getStartTime();
        LocalTime lessonEndTime = lesson.getEndTime();
        if (startTime.isEqual(lessonStartTime) || getEndTime().isEqual(lessonEndTime)) {
            return true;
        }
        if (startTime.isAfter(lessonStartTime) && getEndTime().isBefore(lessonEndTime)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]\nClass: %s",
                startTime, getEndTime(),
                lesson);
    }
}
