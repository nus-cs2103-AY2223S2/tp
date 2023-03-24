package seedu.task.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.task.commons.exceptions.IllegalValueException;
import seedu.task.model.task.Description;
import seedu.task.model.task.Name;
import seedu.task.model.task.Subtask;




/**
 * Jackson-friendly version of {@link Subtask}.
 */
public class JsonAdaptedSubtask {


    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedHomework} with the given homework details.
     */
    @JsonCreator
    public JsonAdaptedSubtask(@JsonProperty("name") String name,
                               @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Converts a given {@code Homework} into this class for Jackson use.
     */
    public JsonAdaptedSubtask(Subtask source) {
        this.name = source.getName().fullName;
        this.description = source.getDescription().value;

    }

    @JsonProperty("name")
    public String getDeadline() {
        return name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }



    /**
     * Converts this Jackson-friendly adapted homework object into the model's {@code Homework} object.
     */
    public Subtask toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);


        Subtask subtask = new Subtask(modelName, modelDescription);
        return subtask;
    }

}
