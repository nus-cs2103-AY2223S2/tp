package seedu.address.model.student;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Exam} is done or otherwise.
 */
public class ExamDonePredicate implements Predicate<Exam> {
    private final String done;

    /**
     * Creates a predicate to test if an Exam is done or otherwise
     * @param done The String that represents done-ness (valid values are "done" or "not done")
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
            && done.equals(((ExamDonePredicate) other).done)); 
    }
}
