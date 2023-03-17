package seedu.address.model.link;

import java.util.Optional;

import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.item.Item;

/**
 * The interface that resolves a single link to an item.
 *
 * @param <T> the item to resolve to.
 */
public class LinkResolver<T extends Item> {
    private final ReadOnlyItemManager<T> manager;

    /**
     * Creates a new link resolver that resolves links using the items of the
     * given manager.
     *
     * @param manager the manager with this the links are resolved.
     */
    public LinkResolver(ReadOnlyItemManager<T> manager) {
        this.manager = manager;
    }

    /**
     * Returns the item that corresponds to the given link.
     *
     * @param id the id of the item.
     * @return Optional of the item with the given id. If no item with the
     *     given id if found, then we resolve to `Optional.none`.
     */
    public Optional<T> resolve(String id) {
        for (T item : manager.getItemList()) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
}
