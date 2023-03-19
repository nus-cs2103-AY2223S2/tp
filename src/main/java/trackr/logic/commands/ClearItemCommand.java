package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Clears the order list.
 */
public abstract class ClearItemCommand<T extends Item> extends Command {

    public static final String MESSAGE_SUCCESS = "%s list has been cleared!";

    public final String commandWord;
    public final String commandWordShortcut;

    private final ModelEnum modelEnum;

    /**
     * Creates an ClearItemCommand to clear the specified item list.
     */
    public ClearItemCommand(String commandWord, String commandWordShortcut, ModelEnum modelEnum) {
        requireAllNonNull(commandWord, commandWordShortcut, modelEnum);
        this.commandWord = commandWord;
        this.commandWordShortcut = commandWordShortcut;
        this.modelEnum = modelEnum;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setItemList(modelEnum);
        return new CommandResult(String.format(MESSAGE_SUCCESS, modelEnum));
    }
}
