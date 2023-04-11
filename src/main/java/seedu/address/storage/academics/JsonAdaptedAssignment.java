package seedu.address.storage.academics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    public JsonAdaptedAssignment(@JsonProperty("name") String assignmentName, @JsonProperty("deadline") String deadline,
                                 @JsonProperty("weightage") int weightage, @JsonProperty("score") int score) {
        this.assignmentName = assignmentName;
        this.deadline = deadline;
        this.weightage = weightage;
        this.score = score;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        this(source.getAssignmentName(), JsonAdaptedAssignment.parseDeadline(source.getDeadline()),
            source.getWeightage(), source.getScore());
    }

    /**
     * Converts a given {@code LocalDate} into a String for Jackson use.
     */
    private static String parseDeadline(LocalDate deadline) {
        if (deadline == null) {
            return "no deadline";
        }
        return deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Returns the assignment name of the assignment.
     * @return assignment name of the assignment.
     */
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * Returns the deadline of the assignment.
     * @return deadline of the assignment.
     */
    public String getDeadline() {
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

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Assignment.
     */
    public abstract Assignment toModelType() throws IllegalValueException;

}

