package seedu.address.storage.stubs;

import seedu.address.model.item.Identifiable;

/**
 * Represents the stub for an {@code Identifiable} object.
 */
public class IdentifiableStub implements Identifiable {
    /**
     * The id of the {@code Identifiable} object.
     */
    private final String id;

    /**
     * Creates a new {@code IdentifiableStub} object with the given id.
     *
     * @param id The id of the {@code Identifiable} object.
     */
    public IdentifiableStub(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
