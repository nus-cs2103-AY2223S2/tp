package wingman.model;

import static java.util.Objects.requireNonNull;
import static wingman.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import wingman.model.exception.IndexOutOfBoundException;
import wingman.model.item.Item;
import wingman.model.item.UniqueList;

/**
 * Implementation of a read-only item manager, this has other methods
 * that allows for the modification of the manager.
 */
public class ItemManager<T extends Item> implements
                                         ReadOnlyItemManager<T> {

    /**
     * The internal list responsible for holding the items.
     */
    private final UniqueList<T> items;

    {
        items = new UniqueList<>();
    }

    /**
     * Initializes an empty {@code ItemManager}.
     */
    public ItemManager() {
    }

    /**
     * Creates a {@code ItemManager} from the given list of items.
     * Please make sure that the items in the list are unique.
     *
     * @param toBeCopied the list of items out of which the {@code
     *                   ItemManager} will be created.
     */
    public ItemManager(ReadOnlyItemManager<T> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     *
     * @param items the list of items to replace the current list with.
     */
    public void setItems(List<T> items) {
        this.items.setItems(items);
    }

    /**
     * Resets the existing data of this {@code ItemManager} with
     * {@code newData}.
     *
     * @param newData the new data to be used.
     */
    public void resetData(ReadOnlyItemManager<T> newData) {
        requireNonNull(newData);
        setItems(newData.getItemList());
    }


    /**
     * Checks if the item is in the list.
     *
     * @param item the item to check
     * @return true if the item is in the list, false otherwise.
     */
    @Override
    public boolean hasItem(T item) {
        requireNonNull(item);
        return items.contains(item);
    }

    /**
     * Checks if the item with the given ID is in the list.
     *
     * @param id the ID of the item to check
     * @return true if the item is in the list, false otherwise.
     */
    @Override
    public boolean hasItem(String id) {
        return items.contains(id);
    }

    /**
     * Adds an item to the list.
     *
     * @param item the item to add
     */
    public void addItem(T item) {
        items.add(item);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     *
     * @param target     the item to be replaced
     * @param editedItem the item to replace with
     */
    public void setItem(T target, T editedItem) {
        requireAllNonNull(target, editedItem);
        items.setItem(target, editedItem);
    }

    /**
     * Removes {@code key} from this {@code ItemManager}.
     *
     * @param item the item to remove
     */
    public void removeItem(T item) {
        items.remove(item);
    }

    public void removeItem(String id) {
        items.remove(id);
    }

    /**
     * Remove an item in the list by the
     * given index.
     *
     * @param index the index of the item to remove
     * @throws IndexOutOfBoundException when the given index larger than size
     */
    public void removeItemByIndex(int index) throws IndexOutOfBoundException {
        T itemToRemove = getItem(index);
        removeItem(itemToRemove.getId());
    }

    @Override
    public Optional<T> getItemOptional(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> getItemOptional(int index) {
        try {
            return Optional.of(items.get(index));
        } catch (IndexOutOfBoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public T getItem(int index) throws IndexOutOfBoundException {
        return items.get(index);
    }

    @Override
    public T getItem(String id) {
        return items.get(id);
    }

    @Override
    public int size() {
        return this.items.size();
    }

    @Override
    public ObservableList<T> getItemList() {
        return this.items.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        // TODO: refine later
        return this.size() + " items";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ItemManager<?>)) {
            return false;
        }
        ItemManager<?> otherItemManager =
                (ItemManager<?>) other;
        return items.equals(otherItemManager.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

}
