package seedu.address.storage.adaptedassignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.student.Assignment;
import seedu.address.model.person.student.Test;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
public class JsonAdaptedTest extends JsonAdaptedAssignment {

    @JsonCreator
    public JsonAdaptedTest(String assignmentName) {
        super(assignmentName, "No Deadline", -100, -100);
    }

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code assignmentName}.
     */
    public JsonAdaptedTest(String assignmentName, String deadline, int weightage, int score) {
        super(assignmentName, deadline, weightage, score);
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedTest(Assignment source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Assignment.
     */
    public Test toModelType() throws IllegalValueException {
        if (!Assignment.isValidAssignmentName(super.getAssignmentName())) {
            throw new IllegalValueException(Assignment.MESSAGE_CONSTRAINTS);
        }
        if (!super.getDeadline().equals("No Deadline")) {
            LocalDate deadline = LocalDate.parse(super.getDeadline(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return new Test(super.getAssignmentName(), deadline, super.getWeightage(), super.getScore());
        } else {
            return new Test(super.getAssignmentName(), null, super.getWeightage(), super.getScore());
        }
    }

}
