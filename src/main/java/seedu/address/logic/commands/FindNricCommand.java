package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NricMatchesKeywordsPredicate;

// The current format: find_nric S1234567A
public class FindNricCommand extends Command {
    public static final String COMMAND_WORD = "find_nric";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the person whose nric matches "
            + "the input nric (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: NRIC...\n"
            + "Example: " + COMMAND_WORD + " "
            + "S1234567A";

    private final NricMatchesKeywordsPredicate predicate;

    public FindNricCommand(NricMatchesKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredElderlyList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getFilteredElderlyList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindNricCommand
                && predicate.equals(((FindNricCommand) other).predicate));
    }

}
