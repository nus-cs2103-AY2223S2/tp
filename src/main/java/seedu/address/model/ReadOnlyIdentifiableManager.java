package seedu.address.model;

import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.model.item.Identifiable;

/**
 * Unmodifiable view of an identifiable manager.
 *
 * @param <T> the type of the identifiable items
 */
public interface ReadOnlyIdentifiableManager<T extends Identifiable> {
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
    Optional<T> getItem(String id);

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
