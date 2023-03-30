package wingman.storage.stubs;

import wingman.commons.exceptions.IllegalValueException;
import wingman.storage.json.JsonAdaptedModel;

/**
 * Represents the stub for a {@code JsonAdaptedModel} object.
 */
public class JsonAdaptedIdentifiableStub
        implements JsonAdaptedModel<ItemStub> {
    /**
     * The id of the {@code JsonAdaptedModel} object.
     */
    private final String id;

    /**
     * Creates a new {@code JsonAdaptedIdentifiableStub} object with the
     * given id.
     *
     * @param id The id of the {@code JsonAdaptedModel} object.
     */
    public JsonAdaptedIdentifiableStub(String id) {
        this.id = id;
    }

    @Override
    public ItemStub toModelType() throws IllegalValueException {
        return new ItemStub(this.id);
    }
}
