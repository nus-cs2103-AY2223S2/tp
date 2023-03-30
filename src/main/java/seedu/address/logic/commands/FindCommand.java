package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.ui.result.ResultDisplay;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE =
            ResultDisplay.formatMessage(COMMAND_WORD,
                    "Finds all contacts matching any of the specified keywords (case-insensitive)",
                    "in the specified fields, and displays them in the list.")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_PARAMETERS, "PREFIX/KEYWORD [PREFIX/KEYWORD]...")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_EXAMPLE, COMMAND_WORD, "n/alice mt/CS2103T")
            + ResultDisplay.formatMessage(ResultDisplay.KEYWORD_MORE_INFO,
                    "Note that the indices of the contacts in the resulting list may be different.",
                    "For more information on prefixes, refer to the user guide using the help command.");

    private final PersonContainsKeywordsPredicate predicate;

    public FindCommand(PersonContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
