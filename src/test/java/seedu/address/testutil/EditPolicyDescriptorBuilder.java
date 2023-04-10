package seedu.address.testutil;

import seedu.address.logic.commands.EditPolicyCommand;
import seedu.address.model.client.policy.CustomDate;
import seedu.address.model.client.policy.Frequency;
import seedu.address.model.client.policy.Policy;
import seedu.address.model.client.policy.PolicyName;
import seedu.address.model.client.policy.Premium;

/**
 * A utility class to help with building EditPolicyDescriptor objects.
 */
public class EditPolicyDescriptorBuilder {
    private EditPolicyCommand.EditPolicyDescriptor descriptor;
    public EditPolicyDescriptorBuilder() {
        descriptor = new EditPolicyCommand.EditPolicyDescriptor();
    }

    public EditPolicyDescriptorBuilder(EditPolicyCommand.EditPolicyDescriptor descriptor) {
        this.descriptor = new EditPolicyCommand.EditPolicyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPolicyDescriptor} with fields containing {@code policy}'s details
     */
    public EditPolicyDescriptorBuilder(Policy policy) {
        descriptor = new EditPolicyCommand.EditPolicyDescriptor();
        descriptor.setPolicyName(policy.getPolicyName());
        descriptor.setStartDate(policy.getStartDate());
        descriptor.setPremium(policy.getPremium());
        descriptor.setFrequency(policy.getFrequency());
    }

    /**
     * Sets the {@code PolicyName} of the {@code EditPolicyDescriptor} that we are building.
     */
    public EditPolicyDescriptorBuilder withPolicyName(String policyName) {
        descriptor.setPolicyName(new PolicyName(policyName));
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code EditPolicyDescriptor} that we are building.
     */
    public EditPolicyDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new CustomDate(startDate));
        return this;
    }

    /**
     * Sets the {@code Premium} of the {@code EditPolicyDescriptor} that we are building.
     */
    public EditPolicyDescriptorBuilder withPremium(String premium) {
        descriptor.setPremium(new Premium(premium));
        return this;
    }


    /**
     * Sets the {@code Frequency} of the {@code EditPolicyDescriptor} that we are building.
     */
    public EditPolicyDescriptorBuilder withFrequency(String frequency) {
        descriptor.setFrequency(new Frequency(frequency));
        return this;
    }

    public EditPolicyCommand.EditPolicyDescriptor build() {
        return descriptor;
    }
}

