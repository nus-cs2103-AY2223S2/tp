package wingman.storage.json.storage;

import java.nio.file.Path;

import wingman.model.ReadOnlyItemManager;
import wingman.model.plane.Plane;
import wingman.storage.json.JsonItemStorage;
import wingman.storage.json.adapted.JsonAdaptedPlane;
import wingman.storage.json.serializable.JsonSerializablePlaneManager;

/**
 * Represents a storage for {@link Plane}s.
 */
public class JsonPlaneManagerStorage
        extends JsonItemStorage<Plane, JsonAdaptedPlane, JsonSerializablePlaneManager> {
    public JsonPlaneManagerStorage(Path filePath) {
        super(filePath);
    }

    @Override
    protected Class<JsonSerializablePlaneManager> getManagerClass() {
        return JsonSerializablePlaneManager.class;
    }

    @Override
    protected JsonSerializablePlaneManager createManager(
            ReadOnlyItemManager<Plane> modelManager
    ) {
        return JsonSerializablePlaneManager.from(modelManager);
    }
}
