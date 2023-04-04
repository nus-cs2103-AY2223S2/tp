package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FindContainsAnythingPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAllCommand extends Command {

    public static final String COMMAND_WORD = "findall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns all contacts that matches any attribute from the "
            + "the specified keywords (case-insensitive)\n and displays as a list of contacts.\n"
            + "Parameters: Any attribute like [n/NAME],[e/EMAIL],[c/COMPANY] except [s/STATUS] & [t/TAG] \n"
            + "Example: " + COMMAND_WORD + " Blk 90, Company A, Bernice";

    private final FindContainsAnythingPredicate predicate;

    public FindAllCommand(FindContainsAnythingPredicate predicate) {
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
                || (other instanceof FindAllCommand // instanceof handles nulls
                        && predicate.equals(((FindAllCommand) other).predicate)); // state check
    }
}
