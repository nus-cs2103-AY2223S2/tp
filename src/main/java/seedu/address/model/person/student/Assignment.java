package seedu.address.model.person.student;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an Assignment that is assigned to the Student.
 */
public abstract class Assignment {

    public static final String MESSAGE_CONSTRAINTS = "Assignment name should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    protected final String assignmentName;
    protected LocalDate deadline;
    protected final int weightage;
    protected int score;

    /**
     * Returns an Assignment object that stores information about the assignment description.
     *
     * @param assignmentName Assignment name.
     * @param deadline Due date for the assignment.
     * @param weightage Weightage of the assignment.
     */
    public Assignment(String assignmentName, LocalDate deadline, int weightage, int score) {
        this.assignmentName = assignmentName;
        this.deadline = deadline;
        this.weightage = weightage;
        this.score = score;
    }

    /**
     * Returns a boolean value to indicate if the assignment name is valid.
     * @param test Assignment name.
     * @return boolean value to indicate if the assignment name is valid.
     */
    public static boolean isValidAssignmentName(String test) {
        if (test.equals("Insert student homework here!") || test.equals("Insert student test here!")) {
            return true;
        }

        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the assignment name.
     * @return assignment name.
     */
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * Returns the deadline of the assignment.
     * @return deadline of the assignment.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Returns the weightage of the assignment.
     * @return weightage of the assignment.
     */
    public int getWeightage() {
        return weightage;
    }

    /**
     * Returns the score of the assignment.
     * @return score of the assignment.
     */
    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Assignment that = (Assignment) o;
        return weightage == that.weightage && assignmentName.equals(that.assignmentName)
                && Objects.equals(deadline, that.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentName, deadline, weightage);
    }
}
