package seedu.address.logic.commands.task;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.ContentContainsKeywordsPredicate;
import seedu.address.model.task.TitleContainsKeywordsPredicate;

/**
 * Finds and lists all todos and notes in record whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all todos and notes whose company names or "
            + "contents contain any of the specified keywords (case-insensitive) "
            + "and displays them as lists with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " google software engineer intern";

    private static final TaskType type = TaskType.BOTH;

    private final TitleContainsKeywordsPredicate titlePredicate;
    private final ContentContainsKeywordsPredicate contentPredicate;

    /**
     * Creates a FindTaskCommand instance.
     * Finds todo and content that match {@code titlePredicate} or {@code contentPredicate}.
     */
    public FindTaskCommand(TitleContainsKeywordsPredicate titlePredicate,
                           ContentContainsKeywordsPredicate contentPredicate) {
        this.titlePredicate = titlePredicate;
        this.contentPredicate = contentPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        assert model != null;
        model.updateFilteredTodoList(titlePredicate);
        model.updateFilteredNoteList(contentPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_RESULT_LISTED_OVERVIEW,
                        model.getFilteredNoteList().size() + model.getFilteredTodoList().size()), type);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && titlePredicate.equals(((FindTaskCommand) other).titlePredicate)
                && contentPredicate.equals(((FindTaskCommand) other).contentPredicate)); // state check
    }
}
