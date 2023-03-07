package seedu.address.storage.json.storage;

import java.nio.file.Path;

import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.flight.Flight;
import seedu.address.storage.json.JsonIdentifiableStorage;
import seedu.address.storage.json.adapted.JsonAdaptedFlight;
import seedu.address.storage.json.serializable.JsonSerializableFlightManager;



/**
 * Represents a storage for {@link Flight}s.
 */
public class JsonFlightManagerStorage extends JsonIdentifiableStorage<Flight,
        JsonAdaptedFlight, JsonSerializableFlightManager> {
    /**
     * Creates a new JsonIdentifiableStorage object.
     *
     * @param filePath the path to the file to be read from and written to.
     */
    public JsonFlightManagerStorage(Path filePath) {
        super(filePath);
    }

    @Override
    protected Class<JsonSerializableFlightManager> getManagerClass() {
        return JsonSerializableFlightManager.class;
    }

    @Override
    protected JsonSerializableFlightManager createManager(ReadOnlyIdentifiableManager<Flight> modelManager) {
        return JsonSerializableFlightManager.from(modelManager);
    }
}
