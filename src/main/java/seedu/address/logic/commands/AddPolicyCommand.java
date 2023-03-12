package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.policy.PolicyName;


/**
 * Adds a policy to a specific client in the program
 */
public class AddPolicyCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, PolicyName: %2$s";

    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to a client in the program. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "POLICY_NAME (must be a valid policy name) "
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + "Fire Insurance";

    public final Index index;
    public final PolicyName policyName;

    /**
     * Creates an AddPolicyCommand to add the specified {@code Policy}
     * @param index of the person to add the policy to
     * @param policyName of the policy to be added
     */
    public AddPolicyCommand(Index index, PolicyName policyName) {
        requireAllNonNull(index, policyName);
        this.index = index;
        this.policyName = policyName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), policyName));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof AddPolicyCommand)) {
            return false;
        }
        // state check
        AddPolicyCommand policy = (AddPolicyCommand) other;
        return index.equals(policy.index)
                && policyName.equals(policy.policyName);
    }
}
