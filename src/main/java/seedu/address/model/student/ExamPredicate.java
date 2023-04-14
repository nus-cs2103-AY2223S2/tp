package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Exam}'s title matches the given title.
 */
public class ExamPredicate implements Predicate<Exam> {
    private final String examName;

    /**
     * Creates a ExamPredicate to test if a {@code Exam}'s title matches the given title.
     *
     * @param examName The title to test against.
     */
    public ExamPredicate(String examName) {
        this.examName = examName;
    }

    /**
     * Tests if a {@code Exam}'s title matches the given title.
     *
     * @param exam The exam to test.
     * @return True if the exam's title matches the given title.
     */
    @Override
    public boolean test(Exam exam) {
        return exam.getDescription().contains(examName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExamPredicate // instanceof handles nulls
            && examName.equals(((ExamPredicate) other).examName));
    }
}
