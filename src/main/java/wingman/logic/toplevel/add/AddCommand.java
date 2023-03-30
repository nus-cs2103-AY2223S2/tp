package wingman.logic.toplevel.add;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.item.Item;

/**
 * The command that adds an item to the model.
 *
 * @param <T> the type of the item.
 */
public class AddCommand<T extends Item> implements Command {
    /**
     * The function that's responsible for adding the item. {@link AddFunction}.
     */
    private final AddFunction<T> addFunction;

    /**
     * The item to be added.
     */
    private final T item;

    /**
     * The name of the type of the item that's being added.
     */
    private final String typeName;

    /**
     * Creates a command that adds an item using the function.
     *
     * @param addFunction the function that adds the item.
     * @param item        the item to be added.
     * @param typeName    the type of the item to be added.
     */
    public AddCommand(AddFunction<T> addFunction, T item, String typeName) {
        this.addFunction = addFunction;
        this.item = item;
        this.typeName = typeName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        addFunction.add(model, item);
        return new CommandResult(String.format(
                "Added %s.",
                item.toString()
        ));
    }
}
