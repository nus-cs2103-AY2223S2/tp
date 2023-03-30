package wingman.model;

import java.util.Optional;

import javafx.collections.ObservableList;
import wingman.model.item.Item;

/**
 * Unmodifiable view of an item manager.
 *
 * @param <T> the type of the item items
 */
public interface ReadOnlyItemManager<T extends Item> {
    /**
     * Returns true if an item with the same identity as {@code item} exists
     * in the address book.
     *
     * @param item the item to check
     * @return true if an item with the same identity as {@code item} exists
     */
    boolean hasItem(T item);

    /**
     * Returns true if an item with the given ID exists in the address book.
     *
     * @param id the ID of the item to check
     * @return true if an item with the given ID exists in the address book
     */
    boolean hasItem(String id);

    /**
     * Returns the item with the given ID.
     *
     * @param id the ID of the item to get
     * @return the item with the given ID
     */
    Optional<T> getItemOptional(String id);

    /**
     * Returns the item with the given index.
     *
     * @param index the index of the item to get.
     * @return the item at the given index.
     */
    Optional<T> getItemOptional(int index);

    /**
     * Returns the item at the given
     * index location.
     *
     * @param index the index of the item counting
     *              from zero
     * @return the item at the given index
     */
    T getItem(int index);

    /**
     * Returns the item with the given id.
     *
     * @param id the id of the item.
     * @return the item with the given id.
     */
    T getItem(String id);

    /**
     * Returns the number of items in the address book.
     *
     * @return the number of items in the address book
     */
    int size();

    /**
     * Returns an unmodifiable view of the items list. This list will not
     * contain any duplicate items.
     *
     * @return an unmodifiable view of the items list
     */
    ObservableList<T> getItemList();
}
