package wingman.storage.json.storage;

import java.nio.file.Path;

import wingman.model.ReadOnlyItemManager;
import wingman.model.pilot.Pilot;
import wingman.storage.json.JsonItemStorage;
import wingman.storage.json.adapted.JsonAdaptedPilot;
import wingman.storage.json.serializable.JsonSerializablePilotManager;

/**
 * Represents a storage for {@link Pilot}s.
 */
public class JsonPilotManagerStorage
        extends JsonItemStorage<Pilot, JsonAdaptedPilot, JsonSerializablePilotManager> {
    /**
     * Creates a new JsonIdentifiableStorage object.
     *
     * @param filePath the path to the file to be read from and written to.
     */
    public JsonPilotManagerStorage(Path filePath) {
        super(filePath);
    }

    @Override
    protected Class<JsonSerializablePilotManager> getManagerClass() {
        return JsonSerializablePilotManager.class;
    }

    @Override
    protected JsonSerializablePilotManager createManager(
            ReadOnlyItemManager<Pilot> modelManager
    ) {
        return JsonSerializablePilotManager.from(modelManager);
    }
}
