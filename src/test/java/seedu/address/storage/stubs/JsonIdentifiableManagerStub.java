package seedu.address.storage.stubs;

import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.storage.json.JsonIdentifiableManager;

/**
 * Represents the stub for a {@code JsonIdentifiableManager} object.
 */
public class JsonIdentifiableManagerStub extends
        JsonIdentifiableManager<IdentifiableStub, JsonAdaptedIdentifiableStub> {

    /**
     * Creates a new {@code JsonIdentifiableManagerStub} object.
     * @param modelManager The {@code ReadOnlyIdentifiableManager} object
     * @return The {@code JsonIdentifiableManagerStub} object
     */
    static JsonIdentifiableManagerStub from(
            ReadOnlyIdentifiableManager<IdentifiableStub> modelManager) {
        final JsonIdentifiableManagerStub res = new JsonIdentifiableManagerStub();
        res.readFromManager(modelManager);
        return res;
    }

    @Override
    protected JsonAdaptedIdentifiableStub getJsonAdaptedModel(
            IdentifiableStub item) {
        return new JsonAdaptedIdentifiableStub(item.getId());
    }
}
