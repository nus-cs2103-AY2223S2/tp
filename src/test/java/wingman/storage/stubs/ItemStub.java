package wingman.storage.stubs;

import wingman.model.item.Item;

/**
 * Represents the stub for an {@code Identifiable} object.
 */
public class ItemStub implements Item {
    /**
     * The id of the {@code Identifiable} object.
     */
    private final String id;

    /**
     * Creates a new {@code IdentifiableStub} object with the given id.
     *
     * @param id The id of the {@code Identifiable} object.
     */
    public ItemStub(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
