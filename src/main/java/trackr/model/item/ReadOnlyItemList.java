package trackr.model.item;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an item list.
 */
//@@author liumc-sg-reused
public interface ReadOnlyItemList<T extends Item> {

    /**
     * Returns an unmodifiable view of the items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<T> getItemList();
}
