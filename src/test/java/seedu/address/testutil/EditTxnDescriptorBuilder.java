package seedu.address.testutil;

import seedu.address.logic.commands.txncommands.EditTxnCommand.EditTxnDescriptor;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TxnStatus;
import seedu.address.model.transaction.Value;

/**
 * A utility class to help with building EditTxnDescriptor objects.
 */
public class EditTxnDescriptorBuilder {
    private EditTxnDescriptor descriptor;
    public EditTxnDescriptorBuilder() {
        descriptor = new EditTxnDescriptor();
    }
    public EditTxnDescriptorBuilder(EditTxnDescriptor descriptor) {
        this.descriptor = new EditTxnDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTxnDescriptor} with fields containing {@code transaction}'s details
     */
    public EditTxnDescriptorBuilder(Transaction transaction) {
        descriptor = new EditTxnDescriptor();
        descriptor.setDescription(transaction.getDescription());
        descriptor.setOwner(transaction.getOwner());
        descriptor.setStatus(transaction.getStatus());
        descriptor.setValue(transaction.getValue());
    }

    /**
     * Sets the {@code Description} of the {@code EditTxnDescriptor} that we are building.
     */
    public EditTxnDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Owner} of the {@code EditTxnDescriptor} that we are building.
     */
    public EditTxnDescriptorBuilder withOwner(String owner) {
        descriptor.setOwner(new Owner(owner));
        return this;
    }

    /**
     * Sets the {@code TxnStatus} of the {@code EditTxnDescriptor} that we are building.
     */
    public EditTxnDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new TxnStatus(status));
        return this;
    }

    /**
     * Sets the {@code Value} of the {@code EditTxnDescriptor} that we are building.
     */
    public EditTxnDescriptorBuilder withValue(String value) {
        descriptor.setValue(new Value(value));
        return this;
    }

    public EditTxnDescriptor build() {
        return descriptor;
    }

}
