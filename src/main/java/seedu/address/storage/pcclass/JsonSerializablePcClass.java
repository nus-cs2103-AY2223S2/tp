package seedu.address.storage.pcclass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Class;
import seedu.address.model.person.PcClass;
import seedu.address.model.person.ReadOnlyPcClass;



/**
 * An Immutable PCClass that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
public class JsonSerializablePcClass {
    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedClass> jsonAdaptedClasses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePCClass} with the given students.
     */
    @JsonCreator
    public JsonSerializablePcClass(@JsonProperty("jsonAdaptedClasses") List<JsonAdaptedClass> jsonAdaptedClass) {
        this.jsonAdaptedClasses.addAll(jsonAdaptedClass);
    }

    /**
     * Converts a given {@code ReadOnlyPCClass} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePCClass}.
     */
    public JsonSerializablePcClass(ReadOnlyPcClass source) {
        this.jsonAdaptedClasses.addAll(source.getClassList().stream().map(JsonAdaptedClass::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this serializable class into the model's {@code PCClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PcClass toModelType() throws IllegalValueException {
        PcClass pcClass = new PcClass();
        for (JsonAdaptedClass jsonAdaptedClass : this.jsonAdaptedClasses) {
            Class classToAdd = jsonAdaptedClass.toModelType();
            if (pcClass.hasClass(classToAdd)) {
                continue;
            }
            pcClass.addClass(classToAdd);
        }
        return pcClass;
    }
}
