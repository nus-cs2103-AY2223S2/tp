package seedu.address.model.student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String title;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Every field must be present and not null.
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
