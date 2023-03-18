package seedu.address.storage.academics;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.student.Assignment;
import seedu.address.storage.JsonAdapted;



/**
 * Jackson-friendly version of {@link Assignment}.
 */
abstract class JsonAdaptedAssignment implements JsonAdapted<Assignment> {

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
        if (source.getDeadline() != null) {
            this.deadline = source.getDeadline().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } else {
            this.deadline = "No Deadline";
        }
        this.weightage = source.getWeightage();
        this.score = source.getScore();
    }

    @JsonValue
    public String getAssignmentName() {
        return assignmentName;
    }

    public String getDeadline() {
        return deadline;
    }

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

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Assignment.
     */
    public abstract Assignment toModelType() throws IllegalValueException;

}

