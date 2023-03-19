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

    public final String commandWord;
    public final String commandWordShortcut;
    public final String messageUsage;

    private final ModelEnum modelEnum;
    private final T toAdd;

    /**
     * Creates an AddItemCommand to add the specified {@code Item}
     */
    public AddItemCommand(T item, String commandWord, String commandWordShortcut, String messageUsage) {
        requireAllNonNull(item, commandWord, commandWordShortcut, messageUsage);
        this.commandWord = commandWord;
        this.messageUsage = messageUsage;
        this.commandWordShortcut = commandWordShortcut;
        toAdd = item;
        modelEnum = item.getModelEnum();
    }

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
