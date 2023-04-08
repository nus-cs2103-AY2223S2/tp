package fasttrack.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import fasttrack.commons.core.Messages;
import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.category.Category;
import fasttrack.ui.ScreenType;

/**
 * Deletes a category identified using it's displayed index from the expense tracker.
 */
public class DeleteCategoryCommand implements DeleteCommand {

    public static final String COMMAND_WORD = "delcat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the category identified by the index number used in the displayed category list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CATEGORY_SUCCESS = "Deleted Category: %1$s";

    private final Index targetIndex;

    /**
     * Creates an DeleteCategory to delete the specified {@code Category}
     * @param targetIndex index of the category in the filtered category list to delete
     */
    public DeleteCategoryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model dataModel) throws CommandException {
        requireNonNull(dataModel);
        List<Category> lastShownList = dataModel.getFilteredCategoryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CATEGORY_DISPLAYED_INDEX);
        }

        Category categoryToDelete = lastShownList.get(targetIndex.getZeroBased());
        dataModel.deleteCategory(categoryToDelete);
        return new CommandResult(
            String.format(MESSAGE_DELETE_CATEGORY_SUCCESS, categoryToDelete),
            ScreenType.CATEGORY_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCategoryCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCategoryCommand) other).targetIndex)); // state check
    }
}
