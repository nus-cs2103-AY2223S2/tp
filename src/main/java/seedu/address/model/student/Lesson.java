package seedu.address.model.student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Student's assignment in the TutorPro.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String title;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Creates a new Homework with the given description and deadline.
     *
     * @param title The title of the lesson.
     * @param startTime The time when the lesson starts.
     * @param endTime The time when the lesson ends.
     */
    public Lesson(String title, LocalDateTime startTime, LocalDateTime endTime) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);

        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns the start time of the lesson in a String format.
     *
     * @return The start time of the lesson in a String format.
     */
    public String getStartTimeString() {
        return startTime.format(formatter);
    }

    /**
     * Returns the end time of the lesson in a String format.
     *
     * @return
     */
    public String getEndTimeString() {
        return endTime.format(formatter);
    }

    /**
     * Returns true if the 2 lessons' timeslot conflict with each other.
     * @param otherLesson The other lesson to compare with.
     * @return True if the lessons' timeslots overlap each other.
     */
    public boolean isSameTimeLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
            && otherLesson.getStartTime().isBefore(getEndTime()) && otherLesson.getEndTime().isAfter(getStartTime());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getTitle().equals(getTitle())
            && otherLesson.getStartTime().equals(getStartTime())
            && otherLesson.getEndTime().equals(getEndTime());
    }

    /**
     * Returns true if the lesson has ended.
     *
     * @return True if the lesson has ended.
     */
    public boolean isPastLesson() {
        return getEndTime().isBefore(LocalDateTime.now());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getStartTime(), getEndTime());
    }

    @Override
    public String toString() {
        return String.format("%s, starts: %s, ends: %s", title,
            startTime.format(formatter), endTime.format(formatter));
    }
}
