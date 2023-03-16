package seedu.address.model.person.student;

import java.time.LocalDate;

public class Test extends Assignment {

    public static final String MESSAGE_CONSTRAINTS = "Test must be numbers";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Returns a Test object that stores information about the test description.
     *
     * @param assignmentName Test name.
     * @param deadline Due date for the test.
     * @param weightage Weightage of the test.
     * @param score Score attained for the test.
     */
    public Test(String assignmentName, LocalDate deadline, int weightage, int score) {
        super(assignmentName, deadline, weightage, score);
    }
    /**
     * Returns true if a given string is a valid test.
     */
    public static boolean isValidTest(String test) {
        if (test.equals("Insert student test here!")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Integer.toString(super.score);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Test// instanceof handles nulls
                && super.assignmentName.equals(((Test) other).assignmentName)); // state check
    }


    public String getName() {
        return super.assignmentName;
    }
    public int getScore() {
        return super.score;
    }
    public int getWeightage() {
        return super.weightage;
    }
    public LocalDate getDeadline() {
        return super.deadline;
    }

    public void setScore(int score) {
        super.score = score;
    }
}
