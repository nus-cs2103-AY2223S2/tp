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
     * @param maxScore Maximum score attainable for the test.
     * @param score Score attained for the test.
     */
    public Test(String assignmentName, LocalDate deadline, int weightage, int maxScore, int score) {
        super(assignmentName, deadline, weightage, maxScore, score);
    }
    /**
     * Returns true if a given string is a valid email.
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
                && super.score == ((Test) other).score); // state check
    }



    public int getScore() {
        return super.score;
    }

    public void setScore(int score) {
        super.score = score;
    }
}
