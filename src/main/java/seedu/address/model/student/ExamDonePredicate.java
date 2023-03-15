package seedu.address.model.student;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s title matches the given subject.
 */
public class ExamDonePredicate implements Predicate<Exam> {
    private final String done;

    /**
     * Creates a predicate to test if a Homework's title matches the specified subject
     * @param done The String that represents date
     */
    public ExamDonePredicate(String done) {
        this.done = done;
    }

    /**
     * Tests if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param exam The Exam to test.
     * @return True if the lesson's title matches the given subject.
     */
    @Override
    public boolean test(Exam exam) {
        if (done.equals("done")) {
            return exam.getStartTime().isBefore(LocalDateTime.now());
        }
        return exam.getStartTime().isAfter((LocalDateTime.now()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExamDonePredicate // instanceof handles nulls
            && done.equals(((ExamDonePredicate) other).done)); // date check
    }
}