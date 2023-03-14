package seedu.address.storage.stubs;

import seedu.address.model.ReadOnlyItemManager;
import seedu.address.storage.json.JsonItemManager;

/**
 * Represents the stub for a {@code JsonItemManager} object.
 */
public class JsonItemManagerStub extends
        JsonItemManager<ItemStub, JsonAdaptedIdentifiableStub> {

    /**
     * Creates a new {@code JsonItemManagerStub} object.
     * @param modelManager The {@code ReadOnlyItemManager} object
     * @return The {@code JsonItemManagerStub} object
     */
    static JsonItemManagerStub from(
            ReadOnlyItemManager<ItemStub> modelManager) {
        final JsonItemManagerStub res = new JsonItemManagerStub();
        res.readFromManager(modelManager);
        return res;
    }

    @Override
    protected JsonAdaptedIdentifiableStub getJsonAdaptedModel(
            ItemStub item) {
        return new JsonAdaptedIdentifiableStub(item.getId());
    }
}
