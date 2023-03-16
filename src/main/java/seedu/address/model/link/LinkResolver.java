package seedu.address.model.link;

import java.util.Optional;

import seedu.address.model.item.Item;

/**
 * The interface that resolves a single link to an item.
 *
 * @param <T> the item to resolve to.
 */
public interface LinkResolver<T extends Item> {
    /**
     * Returns the item that corresponds to the given link.
     *
     * @param id the id of the item.
     * @return Optional of the item with the given id. If no item with the
     *     given id if found, then we resolve to `Optional.none`.
     */
    Optional<T> resolve(String id);
}
