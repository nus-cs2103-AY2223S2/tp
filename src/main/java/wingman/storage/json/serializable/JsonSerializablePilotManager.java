package wingman.storage.json.serializable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import wingman.model.ReadOnlyItemManager;
import wingman.model.pilot.Pilot;
import wingman.storage.json.JsonItemManager;
import wingman.storage.json.adapted.JsonAdaptedPilot;

/**
 * Represents a serializable manager of pilots.
 */
@JsonRootName(value = "pilotmanager")
public class JsonSerializablePilotManager
        extends JsonItemManager<Pilot, JsonAdaptedPilot> {

    @JsonCreator
    public JsonSerializablePilotManager(
            @JsonProperty("items") List<JsonAdaptedPilot> pilots
    ) {
        this.items.addAll(pilots);
    }

    /**
     * Creates a new JsonSerializablePilotManager from the given manager.
     *
     * @param manager the manager to create the JsonSerializablePilotManager
     *                from, it should be a ReadOnlyItemManager
     *                &lt;Pilot&gt;
     * @return a new JsonSerializablePilotManager
     */
    public static JsonSerializablePilotManager from(
            ReadOnlyItemManager<Pilot> manager
    ) {
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
