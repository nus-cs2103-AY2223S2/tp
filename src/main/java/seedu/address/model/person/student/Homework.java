package seedu.address.model.person.student;

import java.time.LocalDate;

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

    public boolean isDue() {
        return LocalDate.now().isAfter(super.deadline);
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
