package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.listing.TitleContainsKeywordsPredicate;

/**
 * Finds and lists all listings in listing book which title contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all listings whose titles contain any of "
            + "the specified keywords (case-insensitive).\n\t  Displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "chicken rice";

    private final TitleContainsKeywordsPredicate predicate;

    public FindCommand(TitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredListingBook(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_LISTINGS_LISTED_OVERVIEW, model.getDisplayedListingBook().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
