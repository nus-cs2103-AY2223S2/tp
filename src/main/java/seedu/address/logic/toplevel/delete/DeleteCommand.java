package seedu.address.logic.toplevel.delete;


import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;

/**
 * The command that deletes an item.
 */
public class DeleteCommand<T extends Item> implements Command {
    /**
     * The item.
     */
    private final int itemIndex;

    /**
     * Delete function that deletes the item.
     */
    private final DeleteFunction<T> deleteFunction;

    /**
     * The function that gets the manager.
     */
    private final GetManagerFunction<T> getManagerFunction;

    /**
     * The command that deletes an item.
     *
     * @param itemIndex          the index of the item
     * @param getManagerFunction the function for getting the manager.
     * @param deleteFunction     the function for deleting the item.
     */
    public DeleteCommand(
            int itemIndex,
            GetManagerFunction<T> getManagerFunction,
            DeleteFunction<T> deleteFunction
    ) {
        this.itemIndex = itemIndex;
        this.deleteFunction = deleteFunction;
        this.getManagerFunction = getManagerFunction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        T toDelete;
        boolean isValidIndex = model.isIndexValid(itemIndex, getManagerFunction.get(model));

        if (!isValidIndex) {
            throw new CommandException(String.format(
                    "Index %s is out of bounds.\n"
                            + "Please enter a valid index.",
                    itemIndex
            ));
        } else {
            toDelete = getManagerFunction.get(model).getItem(itemIndex - 1);
        }

        deleteFunction.delete(model, toDelete);
        return new CommandResult(String.format(
                "Deleted %s.",
                toDelete.toString()
        ));
    }
}
