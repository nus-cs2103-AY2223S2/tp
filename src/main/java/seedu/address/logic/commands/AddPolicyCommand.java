package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_PREMIUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_START_DATE;
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

    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to the client identified by "
            + "the index number used in the displayed client list." + "\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_POLICY_NAME + "POLICY_NAME "
            + PREFIX_POLICY_START_DATE + "POLICY_DATE "
            + PREFIX_POLICY_PREMIUM + "PREMIUM "
            + PREFIX_POLICY_FREQUENCY + "FREQUENCY " + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_POLICY_NAME + "Fire Insurance "
            + PREFIX_POLICY_START_DATE + "01.01.2021 "
            + PREFIX_POLICY_PREMIUM + "1000 "
            + PREFIX_POLICY_FREQUENCY + "yearly ";

    public static final String MESSAGE_SUCCESS = "Added policy: %1$s to client: %2$s";

    public static final String MESSAGE_DUPLICATE_POLICY = "This policy already exists in the client's policy list";

    public final Index index;
    public final Policy policy;

    /**
     * Creates an AddPolicyCommand to add the specified {@code Policy}
     *
     * @param index  of the person to add the policy to
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
        Client addedPolicyClient = clientToAddPolicy.cloneClient();
        // handle duplicate policies
        if (clientToAddPolicy.getPolicyList().contains(policy)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        addedPolicyClient.getPolicyList().add(policy);
        model.setClient(clientToAddPolicy, addedPolicyClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(generateSuccessMessage(addedPolicyClient));
    }


    /**
     * Generates a command execution success message based on the policy added
     *
     * @return success message
     */
    private String generateSuccessMessage(Client client) {
        return String.format(MESSAGE_SUCCESS, policy, client);
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
