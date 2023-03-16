package seedu.address.storage.academics;

import com.fasterxml.jackson.annotation.JsonCreator;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.student.Assignment;
import seedu.address.model.person.student.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
public class JsonAdaptedTest extends JsonAdaptedAssignment {

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code assignmentName}.
     */
    @JsonCreator
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
        LocalDate deadline = LocalDate.parse(super.getDeadline(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return new Test(super.getAssignmentName(), deadline, super.getWeightage(), super.getScore());
    }

}