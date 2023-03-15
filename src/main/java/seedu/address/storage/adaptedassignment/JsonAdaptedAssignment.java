package seedu.address.storage.adaptedassignment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.student.Assignment;

import java.time.format.DateTimeFormatter;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
abstract class JsonAdaptedAssignment {

    private final String assignmentName;
    private final String deadline;
    private final int weightage;
    private final int score;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code assignmentName}.
     */
    @JsonCreator
    public JsonAdaptedAssignment(String assignmentName, String deadline, int weightage, int score) {
        this.assignmentName = assignmentName;
        this.deadline = deadline;
        this.weightage = weightage;
        this.score = score;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        this.assignmentName = source.getAssignmentName();
        this.deadline = source.getDeadline().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.weightage = source.getWeightage();
        this.score = source.getScore();
    }

    @JsonValue
    public String getAssignmentName() {
        return assignmentName;
    }

    @JsonValue
    public String getDeadline() {
        return deadline;
    }

    @JsonValue
    public int getWeightage() {
        return weightage;
    }

    /**
     * Returns the score of the assignment.
     * @return score of the assignment.
     */
    @JsonValue
    public int getScore() {
        return score;
    }


    
    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Assignment.
     */
    abstract public Assignment toModelType() throws IllegalValueException;

}
/*
        if (!Assignment.isValidAssignmentName(assignmentName)) {
            throw new IllegalValueException(Assignment.MESSAGE_CONSTRAINTS);
        }
        LocalDate deadline = LocalDate.parse(this.deadline, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return new Assignment(assignmentName, deadline, weightage, score);
 */