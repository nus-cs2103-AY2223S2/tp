package seedu.address.model.student;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s title matches the given done predicate.
 */
public class LessonDonePredicate implements Predicate<Lesson> {
    private final String done;

    /**
     * Creates a LessonDonePredicate to test if a {@code Lesson}'s title matches the given done predicate.
     *
     * @param done The done predicate to test against.
     */
    public LessonDonePredicate(String done) {
        this.done = done;
    }

    /**
     * Tests if a {@code Lesson}'s title matches the given done predicate.
     *
     * @param lesson The lesson to test.
     * @return True if the lesson's title matches the given done predicate.
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
