package seedu.address.model.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s title matches the given subject.
 */
public class LessonDonePredicate implements Predicate<Lesson> {
    private final String done;

    /**
     * Creates a predicate to test if a Homework's title matches the specified subject
     * @param done The String that represents date
     */
    public LessonDonePredicate(String done) {
        this.done = done;
    }

    /**
     * Tests if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param lesson The Lesson to test.
     * @return True if the lesson's title matches the given subject.
     */
    @Override
    public boolean test(Lesson lesson) {
        if (done.equals("done")) {
            return lesson.getStartTime().isBefore(LocalDateTime.now());
        }
        return lesson.getStartTime().isAfter((LocalDateTime.now()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LessonDonePredicate // instanceof handles nulls
            && done.equals(((LessonDonePredicate) other).done)); // date check
    }
}
