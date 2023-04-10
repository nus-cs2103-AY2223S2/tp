package seedu.address.model.person.student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a homework that is given to the Student.
 */
public class Homework extends Assignment {
    public static final String MESSAGE_CONSTRAINTS = "Homework must be letters or numbers";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private boolean isDone;

    /**
     * Returns a Homework object that stores information about the homework description.
     *
     * @param assignmentName Homework name.
     * @param deadline Due date for the homework.
     * @param weightage Weightage of the homework.
     * @param isDone Boolean value indicating the completion status of the homework.
     */
    public Homework(String assignmentName, LocalDate deadline, int weightage, int score, boolean isDone) {
        super(assignmentName, deadline, weightage, score);
        this.isDone = isDone;
    }

    /**
     * Checks if a particular task to do is a Homework object.
     *
     * @param test String description of the task to check if it is a Homework object.
     * @return Boolean value indicating whether the task is a Homework object.
     */
    public static boolean isValidHomework(String test) {
        if (test.equals("Insert student homework here!")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a trimmed homeworkdone.
     * @param homeworkDone
     * @param trimmedHomework
     * @return String
     * @throws ParseException
     */
    public static String getTrimmedHomeworkDone(String homeworkDone, String trimmedHomework) throws ParseException {
        String homeworkDoneTrimmed = homeworkDone.trim();
        if (!homeworkDoneTrimmed.matches("^(true|false)$")
                && !homeworkDoneTrimmed.equals("Insert student homework done here!")) {
            throw new ParseException("Homework done must be 'true' or 'false'");
        }
        if (trimmedHomework.equals("Insert student homework done here!")) {
            homeworkDoneTrimmed = "false";
        }
        return homeworkDoneTrimmed;
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
     * Returns a trimmed score.
     * @param score
     * @return String
     * @throws ParseException
     */
    public static String getTrimmedScore(String score) throws ParseException {
        String trimmedScore = score.trim();
        if (!trimmedScore.matches("^([0-9]|[1-9][0-9]|100)$") //check if score is less than 100
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
        return super.assignmentName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Homework// instanceof handles nulls
                && super.assignmentName.equals(((Homework) other).assignmentName)); // state check
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
    public boolean getIsDone() {
        return this.isDone;
    }


    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
