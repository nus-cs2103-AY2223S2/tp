package seedu.address.storage.json.serializable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    @JsonCreator
    public JsonSerializablePilotManager(
        @JsonProperty("items") List<JsonAdaptedPilot> pilots) {
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
            new JsonSerializablePilotManager(new ArrayList<>());
        res.readFromManager(manager);
        return res;
    }

    @Override
    protected JsonAdaptedPilot getJsonAdaptedModel(Pilot item) {
        return new JsonAdaptedPilot(item);
    }
}
