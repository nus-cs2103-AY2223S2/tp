package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's grade in the TutorPro.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Note: This class has a natural ordering that is inconsistent with equals.
 * This is because the natural ordering is based on the percentage score obtained,
 * while equals is based on the score and full marks.
 */
public class Grade {
    private final double score;
    private final double fullMarks;

    /**
     * Creates a new Grade with the given score and full marks.
     *
     * @param score the score obtained by the student
     * @param fullMarks the maximum score that could be obtained
     * @throws IllegalArgumentException if the score is negative or greater than full marks
     */
    public Grade(double score, double fullMarks) {
        if (score < 0 || score > fullMarks) {
            throw new IllegalArgumentException("Invalid grade score");
        }

        this.score = score;
        this.fullMarks = fullMarks;
    }

    /**
     * Returns the score obtained.
     * The score is guaranteed to be non-negative and less than or equal to the full marks.
     *
     * @return the score obtained
     */
    public double getScore() {
        return score;
    }

    /**
     * Returns the full marks.
     * The full marks is guaranteed to be non-negative.
     *
     * @return the full marks
     */
    public double getFullMarks() {
        return fullMarks;
    }

    /**
     * Calculates and returns the percentage score obtained.
     * The percentage score is guaranteed to be non-negative and less than or equal to 100.
     *
     * @return the percentage score obtained
     */
    public double getPercentageScore() {
        return score / fullMarks * 100;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Grade)) {
            return false;
        }

        Grade otherGrade = (Grade) other;
        return Double.compare(otherGrade.score, score) == 0
                && Double.compare(otherGrade.fullMarks, fullMarks) == 0;
    }

    @Override
    public int hashCode() {
        return requireNonNull(Double.valueOf(score)).hashCode()
                + requireNonNull(Double.valueOf(fullMarks)).hashCode();
    }

    @Override
    public String toString() {
        return String.format("%.2f / %.2f (%.2f%%)", score, fullMarks, getPercentageScore());
    }
}
