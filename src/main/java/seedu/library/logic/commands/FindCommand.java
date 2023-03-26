package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.library.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.library.commons.core.Messages;
import seedu.library.model.Model;
import seedu.library.model.bookmark.BookmarkContainsKeywordsPredicate;

/**
 * Finds and lists all bookmarks in library whose title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all bookmarks whose information flagged by the prefixes contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX KEYWORD [MORE_PREFIX] [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TITLE + " Ranker's Chainsaw Solo " + PREFIX_GENRE + " Action";

    private final BookmarkContainsKeywordsPredicate predicate;

    public FindCommand(BookmarkContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookmarkList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKMARKS_LISTED_OVERVIEW, model.getFilteredBookmarkList().size()), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
