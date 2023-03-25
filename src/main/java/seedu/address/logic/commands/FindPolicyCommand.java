package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindPolicyCommand extends Command{
    public static final String COMMAND_WORD = "findPolicy";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": finds clients that have the policy specified\n"
        + "If no such policy exists, no clients will appear on the right panel\n"
        + "Parameters: POLICY_NAME [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " Insurance";
    public static final String MESSAGE_SUCCESS = "Found %s with given policy";

    private NameContainsKeywordsPredicate predicate;

    /**
     * Finds clients with policy specified by user
     * @param predicate policy specified by user
     */
    public FindPolicyCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getSuccessMessage(model.getFilteredPersonList().size()));
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
