package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FieldsMatchRegexPredicate;

/**
 * Finds and lists all persons in the address book whose field entries each match at least one of the provided regexes.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons whose every field matches "
            + "at least one respective regex filter, and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_NAME + "NAME] [" + PREFIX_NAME + "MORE_NAMES] [" + PREFIX_PHONE + "PHONE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Al "
            + PREFIX_ADDRESS + "[0-9] "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "colleagues";

    private final FieldsMatchRegexPredicate predicate;

    public FilterCommand(FieldsMatchRegexPredicate predicate) {
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
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
