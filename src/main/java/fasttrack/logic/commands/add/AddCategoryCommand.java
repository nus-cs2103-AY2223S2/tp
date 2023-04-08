package fasttrack.logic.commands.add;

import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_SUMMARY;
import static java.util.Objects.requireNonNull;

import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.category.Category;
import fasttrack.ui.ScreenType;

/**
 * Adds a category to the Expense Tracker.
 */
public class AddCategoryCommand implements AddCommand {

    public static final String COMMAND_WORD = "addcat";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a category to FastTrack. "
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY_NAME "
            + "[" + PREFIX_SUMMARY + "CATEGORY_SUMMARY]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "groceries "
            + PREFIX_SUMMARY + "all expenses related to groceries\n";
    public static final String MESSAGE_SUCCESS = "New category added: %1$s";
    public static final String MESSAGE_DUPLICATE_CATEGORY = "This category already exists in FastTrack";

    private final Category toAdd;

    /**
     * Creates an AddCategoryCommand to add the specified {@code Category}
     */
    public AddCategoryCommand(Category category) {
        requireNonNull(category);
        toAdd = category;
    }

    @Override
    public CommandResult execute(Model dataModel) throws CommandException {
        requireNonNull(dataModel);
        if (dataModel.hasCategory(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CATEGORY);
        }
        dataModel.addCategory(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ScreenType.CATEGORY_SCREEN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCategoryCommand // instanceof handles nulls
                && toAdd.equals(((AddCategoryCommand) other).toAdd));
    }
}
