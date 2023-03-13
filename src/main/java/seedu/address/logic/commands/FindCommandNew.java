package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.ModelNew;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all openings in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommandNew extends CommandNew {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all openings whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommandNew(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResultNew execute(ModelNew model) {
        requireNonNull(model);
        // model.updateFilteredOpeningList(predicate);
        return new CommandResultNew(
                String.format(Messages.MESSAGE_OPENING_LISTED_OVERVIEW, model.getFilteredOpeningList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommandNew // instanceof handles nulls
                && predicate.equals(((FindCommandNew) other).predicate)); // state check
    }
}
