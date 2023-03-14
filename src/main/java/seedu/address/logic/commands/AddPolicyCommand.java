package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.Policy;


/**
 * Adds a policy to a specific client in the program
 */
public class AddPolicyCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Policy: %2$s";

    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to a client in the program. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "POLICY (must be a valid policy)"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + "pn/Fire Insurance"
            + "d/01-01-2021 "
            + "pr/1000"
            + "fr/YEARLY";

    public final Index index;
    public final Policy policy;

    /**
     * Creates an AddPolicyCommand to add the specified {@code Policy}
     * @param index of the person to add the policy to
     * @param policy to be added
     */
    public AddPolicyCommand(Index index, Policy policy) {
        requireAllNonNull(index, policy);
        this.index = index;
        this.policy = policy;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastshownList = model.getFilteredClientList();

        if (index.getZeroBased() >= lastshownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        Client clientToAddPolicy = lastshownList.get(index.getZeroBased());
        // handle duplicate policies
        if (clientToAddPolicy.getPolicyList().contains(policy)) {
            throw new CommandException("This policy already exists in the client's policy list");
        }
        clientToAddPolicy.getPolicyList().add(policy);
        model.setClient(clientToAddPolicy, clientToAddPolicy);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(generateSuccessMessage());
    }

    /**
     * Generates a command execution success message based on the policy added
     * @return success message
     */
    private String generateSuccessMessage() {
        return String.format("Added policy: %1$s to client: %2$s", policy, index.getOneBased());
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
        AddPolicyCommand otherPolicy = (AddPolicyCommand) other;
        return index.equals(otherPolicy.index)
                && policy.equals(otherPolicy.policy);
    }
}
