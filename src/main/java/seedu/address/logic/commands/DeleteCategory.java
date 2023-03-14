package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCategory extends Command {

    public static final String COMMAND_WORD = "delcat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the category identified by the index number used in the displayed category list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CATEGORY_SUCCESS = "Deleted Category: %1$s";

    private final Index targetIndex;

    public DeleteCategory(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Category> lastShownList = model.getFilteredCategoryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Category categoryToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCategory(categoryToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CATEGORY_SUCCESS, categoryToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCategory // instanceof handles nulls
                && targetIndex.equals(((DeleteCategory) other).targetIndex)); // state check
    }
}
