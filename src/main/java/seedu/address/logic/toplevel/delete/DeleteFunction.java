package seedu.address.logic.toplevel.delete;

import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;

/**
 * The function that deletes an item.
 *
 * @param <T> the type of the item to be deleted.
 */
@FunctionalInterface
public interface DeleteFunction<T extends Item> {
    /**
     * Deletes the item with the given id and returns the deleted item.
     *
     * @param item: the item to be deleted.
     * @throws CommandException if deletion fails.
     */
    void delete(Model model, T item) throws CommandException;
}
