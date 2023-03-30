package wingman.storage.json.serializable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import wingman.model.ReadOnlyItemManager;
import wingman.model.location.Location;
import wingman.storage.json.JsonItemManager;
import wingman.storage.json.adapted.JsonAdaptedLocation;

/**
 * Represents a serializable manager of locations.
 */
@JsonRootName(value = "locationmanager")
public class JsonSerializableLocationManager
        extends JsonItemManager<Location, JsonAdaptedLocation> {

    @JsonCreator
    public JsonSerializableLocationManager(
            @JsonProperty("items") List<JsonAdaptedLocation> location
    ) {
        this.items.addAll(location);
    }

    /**
     * Creates a new JsonSerializablePilotManager from the given manager.
     *
     * @param manager the manager to create the JsonSerializablePilotManager
     *                from, it should be a ReadOnlyItemManager
     *                &lt;Pilot&gt;
     * @return a new JsonSerializablePilotManager
     */
    public static JsonSerializableLocationManager from(ReadOnlyItemManager<Location> manager) {
        final JsonSerializableLocationManager res =
                new JsonSerializableLocationManager(new ArrayList<>());
        res.readFromManager(manager);
        return res;
    }

    @Override
    protected JsonAdaptedLocation getJsonAdaptedModel(Location item) {
        return new JsonAdaptedLocation(item);
    }
}
