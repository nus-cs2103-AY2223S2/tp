package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUMMARY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;

/**
 * Adds a person to the address book.
 */
public class AddCategory extends Command {

    public static final String COMMAND_WORD = "addcat";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a category to FastTrack. "
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY_NAME"
            + PREFIX_SUMMARY + "CATEGORY_SUMMARY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "groceries"
            + PREFIX_SUMMARY + "all expenses related to groceries\n";

    public static final String MESSAGE_SUCCESS = "New category added: %1$s";
    public static final String MESSAGE_DUPLICATE_CATEGORY = "This category already exists in FastTrack";

    private final Category toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCategory(Category category) {
        requireNonNull(category);
        toAdd = category;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasCategory(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CATEGORY);
        }
        model.addCategory(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCategory // instanceof handles nulls
                && toAdd.equals(((AddCategory) other).toAdd));
    }
}
