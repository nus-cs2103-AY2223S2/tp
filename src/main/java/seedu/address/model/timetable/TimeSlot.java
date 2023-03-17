package seedu.address.model.timetable;

import java.util.Optional;

import org.joda.time.Hours;
import org.joda.time.LocalTime;

import seedu.address.model.timetable.exceptions.LessonClashException;
import seedu.address.model.timetable.exceptions.WrongTimeException;


/**
 * Represents an hour timeslot in a Timetable.
 */
public class TimeSlot extends TimePeriod {

    public static final String WRONG_TIME_MESSAGE = "Timing does not match!";
    public static final String ALREADY_FILLED_MESSAGE = "Slot is already filled by a class!";

    private Optional<Lesson> lesson = Optional.empty();

    public TimeSlot(LocalTime startTime, SchoolDay schoolDay) {
        super(startTime, startTime.plusHours(1), schoolDay);
    }

    public TimeSlot(TimeSlot timeSlot) {
        super(timeSlot.getStartTime(), timeSlot.getEndTime(), timeSlot.getSchoolDay());
    }

    public LocalTime getStartTime() {
        return super.getStartTime();
    }

    public LocalTime getEndTime() {
        return super.getEndTime();
    }

    @Override
    public TimeBlock merge(TimePeriod timePeriod) {
        if (this.isConsecutiveWith(timePeriod)) {
            if (this.getStartTime().isBefore(timePeriod.getStartTime())
                    && getSchoolDay().equals(timePeriod.getSchoolDay())) {
                return new TimeBlock(this.getStartTime(), timePeriod.getEndTime(), getSchoolDay());
            } else {
                return new TimeBlock(timePeriod.getStartTime(), this.getEndTime(), getSchoolDay());
            }
        } else {
            throw new WrongTimeException("Must be consecutive timeblocks together!");
        }
    }

    @Override
    public Hours getHoursBetween() {
        return Hours.ONE;
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
        if (getStartTime().isEqual(lessonStartTime) || getEndTime().isEqual(lessonEndTime)) {
            return true;
        }
        if (getStartTime().isAfter(lessonStartTime) && getEndTime().isBefore(lessonEndTime)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]\nClass: %s",
                getStartTime(), getEndTime(),
                lesson);
    }
}
