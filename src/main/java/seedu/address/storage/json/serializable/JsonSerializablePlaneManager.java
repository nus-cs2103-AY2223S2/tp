package seedu.address.storage.json.serializable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.plane.Plane;
import seedu.address.storage.json.JsonIdentifiableManager;
import seedu.address.storage.json.adapted.JsonAdaptedPlane;

/**
 * Represents a serializable manager of planes.
 */
@JsonRootName(value = "planemanager")
public class JsonSerializablePlaneManager extends JsonIdentifiableManager<Plane, JsonAdaptedPlane> {
    @JsonCreator
    public JsonSerializablePlaneManager(
            @JsonProperty("items") List<JsonAdaptedPlane> planes) {
        this.items.addAll(planes);
    }

    /**
     * Creates a new JsonSerializablePlaneManager from the given manager.
     *
     * @param manager the manager to create the JsonSerializablePlaneManager
     *                from, it should be a ReadOnlyIdentifiableManager
     *                &lt;Plane&gt;
     * @return        a new JsonSerializablePilotManager.
     */
    public static JsonSerializablePlaneManager from(ReadOnlyIdentifiableManager<Plane> manager) {
        final JsonSerializablePlaneManager res =
                new JsonSerializablePlaneManager(new ArrayList<>());
        res.readFromManager(manager);
        return res;
    }

    @Override
    protected JsonAdaptedPlane getJsonAdaptedModel(Plane item) {
        return new JsonAdaptedPlane(item);
    }
}
