package seedu.address.logic.commands.task;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.todo.ContentContainsKeywordsPredicate;
import seedu.address.model.todo.TitleContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all todos and notes in record whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find tn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all todos and notes whose names "
            + "contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " google software engineer intern";

    private final TitleContainsKeywordsPredicate titlePredicate;
    private final ContentContainsKeywordsPredicate contentPredicate;

    public FindTaskCommand(TitleContainsKeywordsPredicate titlePredicate,
                           ContentContainsKeywordsPredicate contentPredicate) {
        this.titlePredicate = titlePredicate;
        this.contentPredicate = contentPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(titlePredicate);
        model.updateFilteredNoteList(contentPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_RESULT_LISTED_OVERVIEW,
                        model.getFilteredNoteList().size() + model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && titlePredicate.equals(((FindTaskCommand) other).titlePredicate)
                && contentPredicate.equals(((FindTaskCommand) other).contentPredicate)); // state check
    }
}
