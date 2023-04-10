package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s title matches the given subject.
 */
public class LessonSubjectPredicate implements Predicate<Lesson> {
    private final String subject;

    /**
     * Creates a LessonSubjectPredicate to test if a {@code Lesson}'s title matches the given subject.
     *
     * @param subject The subject to test against.
     */
    public LessonSubjectPredicate(String subject) {
        this.subject = subject;
    }

    /**
     * Tests if a {@code Lesson}'s title matches the given subject.
     *
     * @param lesson The lesson to test.
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
