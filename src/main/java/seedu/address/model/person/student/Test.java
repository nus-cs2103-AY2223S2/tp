package seedu.address.model.person.student;

import java.time.LocalDate;

public class Test extends Assignment{

    public static final String MESSAGE_CONSTRAINTS = "Test must be numbers";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public String value;

    public Test(String assignmentName, LocalDate deadline, int weightage, int maxScore, int score) {
        super(assignmentName, deadline, weightage, maxScore);
        this.value = String.valueOf(score);
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
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Test// instanceof handles nulls
                && value.equals(((Test) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getScore() {
        return value;
    }

    public void setScore(String score) {
        this.value = score;
    }
}
