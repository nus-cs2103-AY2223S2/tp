package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ModuleTracker;
import seedu.address.model.ReadOnlyModuleTracker;
import seedu.address.model.module.Module;

/**
 * An Immutable ModuleTracker that is serializable to JSON format.
 */
@JsonRootName(value = "moduletracker")
class JsonSerializableModuleTracker {

    public static final String MESSAGE_DUPLICATE_MODULE = "Modules list contains duplicate module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableModuleTracker} with the given modules.
     */
    @JsonCreator
    public JsonSerializableModuleTracker(@JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyModuleTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableModuleTracker}.
     */
    public JsonSerializableModuleTracker(ReadOnlyModuleTracker source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this module tracker into the model's {@code ModuleTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleTracker toModelType() throws IllegalValueException {
        ModuleTracker moduleTracker = new ModuleTracker();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (moduleTracker.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            moduleTracker.addModule(module);
        }
        return moduleTracker;
    }

}
