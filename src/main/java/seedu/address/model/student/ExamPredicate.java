package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson}'s title matches the given subject.
 */
public class ExamPredicate implements Predicate<Exam> {
    private final String examName;

    /**
     * Creates a predicate to test if a Homework's title matches the specified subject
     * @param examName The String to test against.
     */
    public ExamPredicate(String examName) {
        this.examName = examName;
    }

    /**
     * Tests if a {@code Homework}'s {@code isCompleted} matches the given boolean.
     *
     * @param exam The Exam to test.
     * @return True if the lesson's title matches the given subject.
     */
    @Override
    public boolean test(Exam exam) {
        return exam.getDescription().contains(examName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExamPredicate // instanceof handles nulls
            && examName.equals(((ExamPredicate) other).examName)); // date check
    }
}
