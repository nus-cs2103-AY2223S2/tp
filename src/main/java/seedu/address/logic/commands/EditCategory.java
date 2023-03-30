package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.ui.ScreenType;


/**
 * Edits a category in the ExpenseTracker
 */
public class EditCategory extends Command {
    public static final String COMMAND_WORD = "edcat";

    private final Index targetIndex;

    private final String newCategoryName;

    private final String newSummaryName;

    /**
     * Creates an EditCategory to edit the specified {@code Category}
     * @param targetIndex index of the expense in the filtered category list to edit.
     * @param newCategoryName String representation of the new category name to be edited to, if applicable.
     * @param newSummaryName String representation of the new summary name to be edited to, if applicable.
     */
    public EditCategory(Index targetIndex, String newCategoryName, String newSummaryName) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.newCategoryName = newCategoryName;
        this.newSummaryName = newSummaryName;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Category> lastShownList = model.getFilteredCategoryList();

        //Check whether targetIndex is a valid index.
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CATEGORY_DISPLAYED_INDEX);
        }

        UserDefinedCategory categoryToEdit = (UserDefinedCategory) lastShownList.get(targetIndex.getZeroBased());
        if (newCategoryName == null && newSummaryName == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EDIT_FOR_CATEGORIES);
        }

        if (newCategoryName != null && newSummaryName != null) {
            categoryToEdit.setCategoryName(newCategoryName);
            categoryToEdit.setDescription(newSummaryName);
            return new CommandResult(
                    String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_CATEGORY,
                            categoryToEdit), ScreenType.CATEGORY_SCREEN);
        }

        if (newCategoryName != null) {
            categoryToEdit.setCategoryName(newCategoryName);
            return new CommandResult(
                    String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_CATEGORY,
                            categoryToEdit), ScreenType.CATEGORY_SCREEN);
        }

        //Only other possible outcome is that summary is the only field being changed.
        categoryToEdit.setDescription(newSummaryName);
        return new CommandResult(
                String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_CATEGORY,
                        categoryToEdit), ScreenType.CATEGORY_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCategory // instanceof handles nulls
                && targetIndex.equals(((EditCategory) other).targetIndex)); // state check
    }
}
