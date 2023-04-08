package fasttrack.logic.commands.general;

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
 * Displays the summary of an expense
 */
public class CategorySummaryCommand implements GeneralCommand {

    public static final String COMMAND_WORD = "scat";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays summary of category "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "";

    private final Index targetIndex;

    /**
     * Creates an CategorySummaryCommand to display summary of category at the specified {@code Index}
     */
    public CategorySummaryCommand(Index index) {
        requireNonNull(index);
        targetIndex = index;
    }

    @Override
    public CommandResult execute(Model dataModel) throws CommandException {
        requireNonNull(dataModel);
        List<Category> lastShownList = dataModel.getFilteredCategoryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CATEGORY_DISPLAYED_INDEX);
        }

        Category targetCategory = lastShownList.get(targetIndex.getZeroBased());
        String toDisplay = targetCategory.getCategoryName() + " summary:\n" + targetCategory.getSummary();
        return new CommandResult(toDisplay, ScreenType.CATEGORY_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CategorySummaryCommand // instanceof handles nulls
                && targetIndex.equals(((CategorySummaryCommand) other).targetIndex)); // state check
    }
}
