package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.Tracker;
import seedu.address.model.module.Module;

/**
 * An Immutable Tracker that is serializable to JSON format.
 */
@JsonRootName(value = "tracker")
class JsonSerializableTracker {

    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTracker} with the given modules.
     */
    @JsonCreator
    public JsonSerializableTracker(@JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTracker}.
     */
    public JsonSerializableTracker(ReadOnlyTracker source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this tracker into the model's {@code Tracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Tracker toModelType() throws IllegalValueException {
        Tracker tracker = new Tracker();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();

            if (tracker.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }

            tracker.addModule(module);
        }
        return tracker;
    }

}
