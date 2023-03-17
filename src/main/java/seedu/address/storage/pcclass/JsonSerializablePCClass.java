package seedu.address.storage.pcclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Class;
import seedu.address.model.person.PCClass;
import seedu.address.model.person.ReadOnlyPCClass;
import seedu.address.model.person.student.Student;
import seedu.address.storage.person.JsonAdaptedStudent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable PCClass that is serializable to JSON format.
 */
@JsonRootName(value = "class")
public class JsonSerializablePCClass {
    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedClass> jsonAdaptedClasses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePCClass} with the given students.
     */
    @JsonCreator
    public JsonSerializablePCClass(@JsonProperty("classes") List<JsonAdaptedClass> jsonAdaptedClass) {
         this.jsonAdaptedClasses.addAll(jsonAdaptedClass);
    }

    /**
     * Converts a given {@code ReadOnlyPCClass} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePCClass}.
     */
    public JsonSerializablePCClass(ReadOnlyPCClass source) {
        this.jsonAdaptedClasses.addAll(source.getClassList().stream().map(JsonAdaptedClass::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this serializable class into the model's {@code PCClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PCClass toModelType() throws IllegalValueException {
        PCClass pcClass = new PCClass();
        for (JsonAdaptedClass jsonAdaptedClass : this.jsonAdaptedClasses) {
            Class classToAdd = jsonAdaptedClass.toModelType();
            if (pcClass.hasClass(classToAdd)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            pcClass.addClass(classToAdd);
        }
        return pcClass;
    }
}
