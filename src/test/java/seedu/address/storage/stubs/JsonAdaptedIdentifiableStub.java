package seedu.address.storage.stubs;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.storage.json.JsonAdaptedModel;

/**
 * Represents the stub for a {@code JsonAdaptedModel} object.
 */
public class JsonAdaptedIdentifiableStub
        implements JsonAdaptedModel<IdentifiableStub> {
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
    public IdentifiableStub toModelType() throws IllegalValueException {
        return new IdentifiableStub(this.id);
    }
}
