package seedu.address.model.student;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s title matches the given subject.
 */
public class LessonSubjectPredicate implements Predicate<Lesson> {
    private final String subject;

    /**
     * Creates a predicate to test if a Homework's title matches the specified subject
     * @param subject The String to test against.
     */
    public LessonSubjectPredicate(String subject) {
        this.subject = subject;
    }

    /**
     * Tests if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param lesson The Lesson to test.
     * @return True if the lesson's title matches the given subject.
     */
    @Override
    public boolean test(Lesson lesson) {
        return lesson.getTitle().contains(subject);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LessonSubjectPredicate // instanceof handles nulls
            && subject.equals(((LessonSubjectPredicate) other).subject)); // date check
    }
}