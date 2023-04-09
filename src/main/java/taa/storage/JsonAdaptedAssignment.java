package taa.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import taa.commons.exceptions.IllegalValueException;
import taa.model.assignment.Assignment;

/** Jackson-friendly version of {@link Assignment}.*/
public class JsonAdaptedAssignment {
    private final String name;
    private final String totalMarks;

    /** Constructs a {@link JsonAdaptedAssignment} with the given fields in JSON file. Called when reading from JSON. */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("name") String name, @JsonProperty("totalMarks") String totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
    }

    /** Constructs a {@link JsonAdaptedAssignment} from assignment object fields. Called when saving to JSON. */
    public JsonAdaptedAssignment(Assignment assignment) {
        name = assignment.getName();
        totalMarks = Integer.toString(assignment.getTotalMarks());
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@link Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException("An assignment in JSON file has name value missing.");
        }
        if (totalMarks == null) {
            throw new IllegalValueException("An assignment in JSON file has totalMarks value missing.");
        }
        int assignmentMarks;
        try {
            assignmentMarks = Integer.parseInt(totalMarks);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Invalid total marks value \"" + totalMarks + "\" in JSON file.");
        }
        if (!Assignment.isValidAssignmentMarks(assignmentMarks)) {
            throw new IllegalValueException("Invalid total marks value \"" + totalMarks + "\" in JSON file.");
        }
        if (!Assignment.isValidAssignmentName(name)) {
            throw new IllegalValueException("Invalid name \"" + name + "\" in JSON file.");
        }
        return new Assignment(name, assignmentMarks);
    }
}
