package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;

import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Adds an Item to the task list.
 */
public abstract class AddItemCommand<T extends Item> extends Command {
    public static final String COMMAND_WORD = "add_task";
    public static final String COMMAND_WORD_SHORTCUT = "add_t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list. "
            + "Parameters: "
            + PREFIX_NAME + "TASK NAME "
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_STATUS + "STATUS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Sort Inventory "
            + PREFIX_DEADLINE + "01/01/2024 "
            + PREFIX_STATUS + "N ";

    public static final String MESSAGE_SUCCESS = "New %s added: %s";
    public static final String MESSAGE_DUPLICATE_TASK = "This %s already exists in the %s list";

    private final ModelEnum modelEnum;
    private final T toAdd;

    /**
     * Creates an AddItemCommand to add the specified {@code Item}
     */
    public AddItemCommand(T item) {
        requireNonNull(item);
        toAdd = item;
        modelEnum = item.getModelEnum();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(toAdd, modelEnum)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TASK, modelEnum, modelEnum));
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
