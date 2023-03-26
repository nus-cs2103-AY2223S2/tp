package expresslibrary.logic.commands;


import expresslibrary.commons.core.Messages;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.Model;
import expresslibrary.model.book.BookNameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Represents the findBook Command
 */
public class FindBookCommand extends Command {
    public static final String COMMAND_WORD = "findBook";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds books containing given keywords(case -insensitive)"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Call of the Wild";
    private final BookNameContainsKeywordsPredicate predicate;

    public FindBookCommand(BookNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredBookList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOK_FOUND_OVERVIEW, model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBookCommand // instanceof handles nulls
                && predicate.equals(((FindBookCommand) other).predicate)); // state check
    }
}
