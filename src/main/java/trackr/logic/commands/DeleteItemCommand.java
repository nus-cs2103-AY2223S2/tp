package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Deletes an item identified using it's displayed index from the item list.
 */
public abstract class DeleteItemCommand<T extends Item> extends Command {

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted %s: %s";

    public final String commandWord;
    public final String commandWordShortcut;
    public final String messageUsage;
    private final Index targetIndex;
    private ModelEnum modelEnum;

    /**
     * Deletes item from item list.
     */
    public DeleteItemCommand(Index targetIndex, String commandWord, String commandWordShortcut, String messageUsage,
                             ModelEnum modelEnum) {
        requireAllNonNull(targetIndex, commandWord, commandWordShortcut, messageUsage, modelEnum);
        this.targetIndex = targetIndex;
        this.commandWord = commandWord;
        this.commandWordShortcut = commandWordShortcut;
        this.messageUsage = messageUsage;
        this.modelEnum = modelEnum;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<? extends Item> lastShownList = model.getFilteredItemList(modelEnum);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, modelEnum));
        }

        Item itemToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteItem(itemToDelete, modelEnum);
        return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, modelEnum, itemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteItemCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteItemCommand) other).targetIndex)); // state check
    }
}
