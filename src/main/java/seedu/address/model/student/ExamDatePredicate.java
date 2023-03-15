package seedu.address.model.student;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s date matches the given date.
 */
public class ExamDatePredicate implements Predicate<Exam> {
    private final LocalDate targetDate;

    /**
     * Creates a predicate to test if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     * @param targetDate The LocalDate to test against.
     */
    public ExamDatePredicate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * Tests if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param lesson The homework to test.
     * @return True if the lesson's date matches the given date.
     */
    @Override
    public boolean test(Exam lesson) {
        return lesson.getStartTime().toLocalDate().isEqual(targetDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExamDatePredicate // instanceof handles nulls
            && targetDate == ((ExamDatePredicate) other).targetDate); // date check
    }
}
