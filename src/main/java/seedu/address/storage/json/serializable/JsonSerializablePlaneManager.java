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
