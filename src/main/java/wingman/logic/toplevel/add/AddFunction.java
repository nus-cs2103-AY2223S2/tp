package wingman.logic.toplevel.add;

import wingman.model.Model;
import wingman.model.item.Item;

/**
 * The interface that's responsible for adding the item to the given model.
 *
 * @param <T> the type of the item.
 */
@FunctionalInterface
public interface AddFunction<T extends Item> {
    /**
     * Adds the given item to the model.
     *
     * @param model the model.
     * @param item  the item to be added.
     */
    void add(Model model, T item);
}
