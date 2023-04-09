package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_PREMIUM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_START_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;
import seedu.address.model.client.policy.UniquePolicyList;

/**
 * Edits the details of an existing policy in an existing client in the address book.
 */
public class EditPolicyCommand extends Command {

    public static final String COMMAND_WORD = "editPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the policy identified by the index number used in the displayed policy list "
            + "from the client identified by the index number used in the display client list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_POLICY_INDEX + "POLICY_INDEX "
            + "[" + PREFIX_POLICY_NAME + "POLICY_NAME] "
            + "[" + PREFIX_POLICY_START_DATE + "START_DATE] "
            + "[" + PREFIX_POLICY_PREMIUM + "PREMIUM] "
            + "[" + PREFIX_POLICY_FREQUENCY + "FREQUENCY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POLICY_INDEX + "1 "
            + PREFIX_POLICY_NAME + "Travel Insurance "
            + PREFIX_POLICY_PREMIUM + "2000";
    public static final String MESSAGE_EDIT_POLICY_SUCCESS = "Edited Policy: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_DUPLICATE_POLICY = "This policy already exists in this client.";

    private final Index clientIndex;
    private final Index policyIndex;
    private final EditPolicyDescriptor editPolicyDescriptor;


    /**
     * Creates an EditPolicyCommand to edit the specified {@code Policy} given the {@code Client} index.
     *
     * @param clientIndex          The index of the client in the client list.
     * @param policyIndex          The index of the policy display from the client.
     * @param editPolicyDescriptor The details to edit the policy with.
     */
    public EditPolicyCommand(Index clientIndex, Index policyIndex, EditPolicyDescriptor editPolicyDescriptor) {
        requireAllNonNull(clientIndex, policyIndex);
        requireNonNull(editPolicyDescriptor);

        this.clientIndex = clientIndex;
        this.policyIndex = policyIndex;
        this.editPolicyDescriptor = editPolicyDescriptor;
    }

    private static Policy createEditedPolicy(Policy policyToEdit, EditPolicyDescriptor editPolicyDescriptor) {
        assert policyToEdit != null;

        PolicyName updatedPolicyName = editPolicyDescriptor.getPolicyName().orElse(policyToEdit.getPolicyName());
        CustomDate updatedCustomDate = editPolicyDescriptor.getStartDate().orElse(policyToEdit.getStartDate());
        Premium updatedPremium = editPolicyDescriptor.getPremium().orElse(policyToEdit.getPremium());
        Frequency updatedFrequency = editPolicyDescriptor.getFrequency().orElse(policyToEdit.getFrequency());

        return new Policy(updatedPolicyName, updatedCustomDate, updatedPremium, updatedFrequency);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();
        if (clientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        assert clientIndex.getOneBased() > 0;
        Client clientToEditPolicy = lastShownList.get(clientIndex.getZeroBased());
        Client editedPolicyClient = clientToEditPolicy.cloneClient();

        List<Policy> lastShownPolicyList = editedPolicyClient.getFilteredPolicyList();
        if (policyIndex.getZeroBased() >= lastShownPolicyList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
        }


        assert policyIndex.getOneBased() > 0;
        Policy policyToEdit = lastShownPolicyList.get(policyIndex.getZeroBased());
        Policy editedPolicy = createEditedPolicy(policyToEdit, editPolicyDescriptor);

        UniquePolicyList clientPolicyList = editedPolicyClient.getPolicyList();
        if (!policyToEdit.isSamePolicy(editedPolicy) && clientPolicyList.contains(editedPolicy)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        clientPolicyList.setPolicy(policyToEdit, editedPolicy);
        model.setClient(clientToEditPolicy, editedPolicyClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(generateSuccessMessage(clientToEditPolicy, editedPolicy));
    }

    private String generateSuccessMessage(Client client, Policy policy) {
        return String.format(
                MESSAGE_EDIT_POLICY_SUCCESS, policy) + " from Client: "
                + client.getName();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditPolicyCommand)) {
            return false;
        }

        EditPolicyCommand e = (EditPolicyCommand) other;
        return policyIndex.equals(e.policyIndex)
                && clientIndex.equals(e.clientIndex)
                && editPolicyDescriptor.equals(e.editPolicyDescriptor);
    }

    /**
     * Stores the details to edit the policy with. Each non-empty value will replace the corresponding field value of
     * the client.
     */
    public static class EditPolicyDescriptor {
        private PolicyName policyName;
        private CustomDate startDate;
        private Premium premium;
        private Frequency frequency;

        /**
         * Creates an EditPolicyDescriptor object.
         */
        public EditPolicyDescriptor() {

        }

        /**
         * Copy constructor.
         */
        public EditPolicyDescriptor(EditPolicyDescriptor toCopy) {
            setPolicyName(toCopy.policyName);
            setStartDate(toCopy.startDate);
            setPremium(toCopy.premium);
            setFrequency(toCopy.frequency);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(policyName, startDate, premium, frequency);
        }

        public Optional<PolicyName> getPolicyName() {
            return Optional.ofNullable(policyName);
        }

        public void setPolicyName(PolicyName policyName) {
            this.policyName = policyName;
        }

        public Optional<CustomDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setStartDate(CustomDate startDate) {
            this.startDate = startDate;
        }

        public Optional<Premium> getPremium() {
            return Optional.ofNullable(premium);
        }

        public void setPremium(Premium premium) {
            this.premium = premium;
        }

        public Optional<Frequency> getFrequency() {
            return Optional.ofNullable(frequency);
        }

        public void setFrequency(Frequency frequency) {
            this.frequency = frequency;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditPolicyDescriptor)) {
                return false;
            }

            EditPolicyDescriptor e = (EditPolicyDescriptor) other;

            return getPolicyName().equals(e.getPolicyName())
                    && getStartDate().equals(e.getStartDate())
                    && getPremium().equals(e.getPremium())
                    && getFrequency().equals(e.getFrequency());
        }

    }
}
