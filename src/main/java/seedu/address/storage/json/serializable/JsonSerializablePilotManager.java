package seedu.address.storage.json.serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.pilot.Pilot;
import seedu.address.storage.json.JsonIdentifiableManager;
import seedu.address.storage.json.adapted.JsonAdaptedPilot;

/**
 * Represents a serializable manager of pilots.
 */
@JsonRootName(value = "pilotmanager")
public class JsonSerializablePilotManager extends JsonIdentifiableManager<Pilot, JsonAdaptedPilot> {


    /**
     * Creates a new JsonSerializablePilotManager. This will be used by the
     * factory method {@link #from(ReadOnlyIdentifiableManager)}.
     */
    private JsonSerializablePilotManager() {
    }

    /**
     * Creates a new JsonSerializablePilotManager from the given manager.
     * This is intended to be used by Jackson.
     *
     * @param pilots the list of pilots to create the
     *               JsonSerializablePilotManager from.
     */
    public JsonSerializablePilotManager(
            @JsonProperty("pilots") List<JsonAdaptedPilot> pilots) {
        this.items.addAll(pilots);
    }

    /**
     * Creates a new JsonSerializablePilotManager from the given manager.
     *
     * @param manager the manager to create the JsonSerializablePilotManager
     *                from, it should be a ReadOnlyIdentifiableManager
     *                &lt;Pilot&gt;
     * @return a new JsonSerializablePilotManager
     */
    public static JsonSerializablePilotManager from(
            ReadOnlyIdentifiableManager<Pilot> manager) {
        final JsonSerializablePilotManager res =
                new JsonSerializablePilotManager();
        res.readFromManager(manager);
        return res;
    }

    @Override
    protected JsonAdaptedPilot getJsonAdaptedModel(Pilot item) {
        return new JsonAdaptedPilot(item);
    }
}
