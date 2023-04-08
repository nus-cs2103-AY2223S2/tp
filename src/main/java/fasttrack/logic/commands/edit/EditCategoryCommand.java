package fasttrack.logic.commands.edit;

import static fasttrack.logic.commands.add.AddCategoryCommand.MESSAGE_DUPLICATE_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_SUMMARY;
import static java.util.Objects.requireNonNull;

import java.util.List;

import fasttrack.commons.core.Messages;
import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.ui.ScreenType;


/**
 * Edits a category in the ExpenseTracker
 */
public class EditCategoryCommand implements EditCommand {
    public static final String COMMAND_WORD = "edcat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the category identified by the index number used in the displayed category list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_SUMMARY + "SUMMARY] "
            + "Example: " + COMMAND_WORD + " edcat 1 c/food s/for meals";

    private final Index targetIndex;

    private final String newCategoryName;

    private final String newSummary;

    /**
     * Creates an EditCategory to edit the specified {@code Category}
     * @param targetIndex index of the expense in the filtered category list to edit.
     * @param newCategoryName String representation of the new category name to be edited to, if applicable.
     * @param newSummary String representation of the new summary to be edited to, if applicable.
     */
    public EditCategoryCommand(Index targetIndex, String newCategoryName, String newSummary) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.newCategoryName = newCategoryName;
        this.newSummary = newSummary;
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

        if (newCategoryName != null) {

            if (newCategoryName.isBlank()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CATEGORY_NAME);
            }
            for (Category category : lastShownList) {
                if (category.getCategoryName().equals(newCategoryName)) {
                    throw new CommandException(MESSAGE_DUPLICATE_CATEGORY);
                }
            }
            categoryToEdit.setCategoryName(newCategoryName.replaceAll("\\s+", " "));
        }
        if (newSummary != null) {
            categoryToEdit.setDescription(newSummary.replaceAll("\\s+", " "));
        }

        if (newCategoryName == null && newSummary == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_EDIT_FOR_CATEGORIES);
        }

        return new CommandResult(
                    String.format(Messages.MESSAGE_SUCCESSFULLY_EDITED_CATEGORY,
                            categoryToEdit), ScreenType.CATEGORY_SCREEN);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCategoryCommand // instanceof handles nulls
                && targetIndex.equals(((EditCategoryCommand) other).targetIndex)); // state check
    }
}
