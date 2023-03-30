package wingman.logic.toplevel.delete;

import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.item.Item;

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
