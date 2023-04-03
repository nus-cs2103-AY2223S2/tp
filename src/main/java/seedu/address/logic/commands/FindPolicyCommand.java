package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PolicyExistInPersonPredicate;

/**
 * Finds people with specified policy tag
 */
public class FindPolicyCommand extends Command {
    public static final String COMMAND_WORD = "findPolicy";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": finds clients that have the policy specified\n"
        + "If no such policy exists, no clients will appear on the right panel\n"
        + "Parameters: POLICY_NAME [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " Insurance";
    public static final String MESSAGE_SUCCESS = "Found %s with given policy";

    private PolicyExistInPersonPredicate predicate;

    /**
     * Finds clients with policy specified by user
     * @param predicate policy specified by user
     */
    public FindPolicyCommand(PolicyExistInPersonPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes findPolicy command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult Object
     * @throws CommandException when index specified is invalid or out of range
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindPolicyCommand // instanceof handles nulls
            && predicate.equals(((FindPolicyCommand) other).predicate)); // state check
    }

    private String getSuccessMessage(int size) {
        if (size == 1) {
            return String.format(MESSAGE_SUCCESS, "1 person");
        } else {
            String people = size + " people";
            return String.format(MESSAGE_SUCCESS, people);
        }
    }


}
