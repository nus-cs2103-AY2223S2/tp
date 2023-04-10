package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FindContainsStatusPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindLeadStatusCommand extends Command {

    public static final String COMMAND_WORD = "findlead";

    public static final String MESSAGE_FORMAT_TABLE = "FORMAT: Lead status - 4 categories/acronyms:\n"
            + "UNCONTACTED: `Uncontacted` or `U`\n"
            + "WORKING: `Working` or `W`\n"
            + "QUALIFIED: `Qualified` or `Q`\n"
            + "UNQUALIFIED: `Unqualified` or `X`";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts that matches the lead status in "
            + "the specified keyword (case-insensitive) and displays as a list of contacts.\n"
            + "Parameters: [STATUS] \n"
            + MESSAGE_FORMAT_TABLE
            + "Example: " + COMMAND_WORD + " Uncontacted, " + COMMAND_WORD + " U";

    public static final String MESSAGE_INVALID_LEAD_STATUS = "Invalid lead status | No results in contact list \n"
            + MESSAGE_FORMAT_TABLE;

    private final FindContainsStatusPredicate predicate;

    public FindLeadStatusCommand(FindContainsStatusPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() == 0) {
            return new CommandResult(MESSAGE_INVALID_LEAD_STATUS);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindLeadStatusCommand // instanceof handles nulls
                        && predicate.equals(((FindLeadStatusCommand) other).predicate)); // state check
    }
}
