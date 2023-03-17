package seedu.address.storage.adaptedassignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.student.Assignment;
import seedu.address.model.person.student.Homework;



/**
 * Jackson-friendly version of {@link Assignment}.
 */
public class JsonAdaptedHomework extends JsonAdaptedAssignment {

    private final boolean isDone;
    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code assignmentName}.
     */
    public JsonAdaptedHomework(String assignmentName, String deadline, int weightage, int score, boolean isDone) {
        super(assignmentName, deadline, weightage, score);
        this.isDone = isDone;
    }

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code assignmentName}.
     * @param assignmentName
     */
    @JsonCreator
    public JsonAdaptedHomework(String assignmentName) {
        super(assignmentName, "No Deadline", -100, -100);
        this.isDone = false;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedHomework(Homework source) {
        super(source);
        this.isDone = source.getIsDone();
    }


    /**
     * Returns the score of the assignment.
     * @return score of the assignment.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Assignment.
     */
    public Homework toModelType() throws IllegalValueException {
        if (!Assignment.isValidAssignmentName(super.getAssignmentName())) {
            throw new IllegalValueException(Assignment.MESSAGE_CONSTRAINTS);
        }
        if (!super.getDeadline().equals("No Deadline")) {
            LocalDate deadline = LocalDate.parse(super.getDeadline(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return new Homework(super.getAssignmentName(), deadline, super.getWeightage(),
                    super.getScore(), this.isDone);
        } else {
            return new Homework(super.getAssignmentName(), null, super.getWeightage(),
                    super.getScore(), this.isDone);
        }
    }

}
