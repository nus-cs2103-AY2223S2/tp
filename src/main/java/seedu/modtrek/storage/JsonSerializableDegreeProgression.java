package seedu.modtrek.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.modtrek.commons.exceptions.IllegalValueException;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.module.Module;
/**
 * An Immutable DegreeProgression that is serializable to JSON format.
 */
@JsonRootName(value = "degreeprogression")
class JsonSerializableDegreeProgression {

    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDegreeProgression} with the given modules.
     */
    @JsonCreator
    public JsonSerializableDegreeProgression(@JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyDegreeProgression} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDegreeProgression}.
     */
    public JsonSerializableDegreeProgression(ReadOnlyDegreeProgression source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this degree progression into the model's {@code DegreeProgression} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DegreeProgression toModelType() throws IllegalValueException {
        DegreeProgression degreeProgression = new DegreeProgression();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (degreeProgression.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            degreeProgression.addModule(module);
        }
        return degreeProgression;
    }

}
