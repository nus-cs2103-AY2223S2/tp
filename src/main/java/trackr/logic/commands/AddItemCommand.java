package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Adds an Item to the item list.
 */
public abstract class AddItemCommand<T extends Item> extends Command {
    public static final String MESSAGE_SUCCESS = "New %s added: %s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This %s already exists in the %s list";

    private final ModelEnum modelEnum;
    private final T toAdd;

    /**
     * Creates an AddItemCommand to add the specified {@code Item} to its respective item list.
     *
     * @param item The item to be added.
     * @param modelEnum A representation of the name of the list we add to.
     */
    public AddItemCommand(T item, ModelEnum modelEnum) {
        requireAllNonNull(item, modelEnum);
        toAdd = item;
        this.modelEnum = modelEnum;
    }

    public T getItemToAdd() {
        return toAdd;
    }

    /**
     * Adds the item {@code toAdd} to its respective item list.
     * @param model {@code Model} which the command should operate on.
     * @return Success message of the add operation for display.
     * @throws CommandException If item to be added is considered to be duplicates
     *                          with any of the other existing items in the list.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(toAdd, modelEnum)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ITEM, modelEnum, modelEnum));
        }

        model.addItem(toAdd, modelEnum);
        return new CommandResult(String.format(MESSAGE_SUCCESS, modelEnum, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItemCommand // instanceof handles nulls
                && toAdd.equals(((AddItemCommand) other).toAdd));
    }
}
