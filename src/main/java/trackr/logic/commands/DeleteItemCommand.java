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

    private final Index targetIndex;
    private ModelEnum modelEnum;

    /**
     * Creates an DeleteItemCommand to delete the specified {@code Item} at the target index.
     *
     * @param targetIndex The index of the target item to be deleted.
     * @param modelEnum A representation of the name of the list where item is deleted.
     */
    public DeleteItemCommand(Index targetIndex, ModelEnum modelEnum) {
        requireAllNonNull(targetIndex, modelEnum);
        this.targetIndex = targetIndex;
        this.modelEnum = modelEnum;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<? extends Item> lastShownList = model.getFilteredItemList(modelEnum);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                    modelEnum.toString().toLowerCase()));
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
