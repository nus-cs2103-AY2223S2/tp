package seedu.address.model.person.student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;

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

    /**
     * Returns a trimmed deadline.
     * @param deadline
     * @return LocalDate
     * @throws ParseException
     */
    public static LocalDate getTrimmedDeadline(String deadline) throws ParseException {
        LocalDate localDate = null;
        if (deadline != "Insert student deadline here!") {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                localDate = LocalDate.parse(deadline, formatter);
            } catch (DateTimeParseException e) {
                throw new ParseException("Date must be in the format dd/mm/yyyy");
            }
        }
        return localDate;
    }

    /**
     * Returns a trimmed weightage.
     * @param weightage
     * @return String
     * @throws ParseException
     */
    public static String getTrimmedWeightage(String weightage) throws ParseException {
        String trimmedWeightage = weightage.trim();
        if (!trimmedWeightage.matches("^([0-9]|[1-9][0-9]|100)$") //check if weightage is less than 100
                && !trimmedWeightage.equals("Insert student weightage here!")) {
            throw new ParseException("Weightage(%) cannot be more than 100 and must contain numbers only");
        }
        if (trimmedWeightage.equals("Insert student weightage here!")) {
            trimmedWeightage = "-100";
        }
        return trimmedWeightage;
    }

    /**
     * Returns a trimmed score.
     * @param score
     * @return String
     * @throws ParseException
     */
    public static String getTrimmedScore(String score) throws ParseException {
        String trimmedScore = score.trim();
        if (!trimmedScore.matches("^([0-9]|[1-9][0-9]|100)$")
                && !trimmedScore.equals("Insert student score here!")) {
            throw new ParseException("Score cannot be more than 100 and must contain numbers only");
        }


        if (trimmedScore.equals("Insert student score here!")) {
            trimmedScore = "-100";
        }
        return trimmedScore;
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
