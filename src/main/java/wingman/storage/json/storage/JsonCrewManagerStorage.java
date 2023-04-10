package wingman.storage.json.storage;

import java.nio.file.Path;

import wingman.model.ReadOnlyItemManager;
import wingman.model.crew.Crew;
import wingman.storage.json.JsonItemStorage;
import wingman.storage.json.adapted.JsonAdaptedCrew;
import wingman.storage.json.serializable.JsonSerializableCrewManager;

/**
 * Represents a storage for {@link Crew}s.
 */
public class JsonCrewManagerStorage
        extends JsonItemStorage<Crew, JsonAdaptedCrew, JsonSerializableCrewManager> {
    /**
     * Creates a new JsonIdentifiableStorage object.
     *
     * @param filePath the path to the file to be read from and written to.
     */
    public JsonCrewManagerStorage(Path filePath) {
        super(filePath);
    }

    @Override
    protected Class<JsonSerializableCrewManager> getManagerClass() {
        return JsonSerializableCrewManager.class;
    }

    @Override
    protected JsonSerializableCrewManager createManager(
            ReadOnlyItemManager<Crew> modelManager
    ) {
        return JsonSerializableCrewManager.from(modelManager);
    }
}
