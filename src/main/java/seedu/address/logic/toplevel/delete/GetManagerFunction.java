package seedu.address.logic.toplevel.delete;

import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.item.Item;

/**
 * The function that gets the manager.
 *
 * @param <T> the type of item managed by the manager.
 */
@FunctionalInterface
public interface GetManagerFunction<T extends Item> {
    /**
     * Gets the manager.
     *
     * @return the manager.
     */
    ReadOnlyItemManager<T> get(Model model);
}
