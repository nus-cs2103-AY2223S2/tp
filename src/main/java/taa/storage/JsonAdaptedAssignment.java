package taa.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import taa.commons.exceptions.IllegalValueException;
import taa.model.assignment.Assignment;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
public class JsonAdaptedAssignment {
    private final String name;
    private final String totalMarks;

    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("name")String name, @JsonProperty("totalMarks")String totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
    }

    public JsonAdaptedAssignment(Assignment assignment){
        name= assignment.getName();
        totalMarks= Integer.toString(assignment.getTotalMarks());
    }

    public Assignment toModelType() throws IllegalValueException {
        try {
            return new Assignment(name, Integer.parseInt(totalMarks));
        }catch (NumberFormatException e){
            throw new IllegalValueException("Invalid total marks value \""+totalMarks+"\" in JSON file.");
        }
    }
}
