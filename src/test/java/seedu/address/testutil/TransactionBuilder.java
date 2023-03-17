package seedu.address.testutil;

import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Owner;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TxnStatus;
import seedu.address.model.transaction.Value;

//import javax.lang.model.util.AbstractAnnotationValueVisitor6;

/**
 * A utility class to help with building Transaction objects.
 */
public class TransactionBuilder {

    public static final String DEFAULT_DESCRIPTION = "Coffee cost.";
    public static final String DEFAULT_OWNER = "Coffeeeeeeee Inc";
    public static final String DEFAULT_VALUE = "10203040";
    public static final String DEFAULT_STATUS = "closed";

    private Description description;
    private Owner owner;
    private TxnStatus status;
    private Value value;

    /**
     * Creates a {@code TransactionBuilder} with the default details.
     */
    public TransactionBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        owner = new Owner(DEFAULT_OWNER);
        status = new TxnStatus(DEFAULT_STATUS);
        value = new Value(DEFAULT_VALUE);
    }

    /**
     * Initializes the TransactionBuilder with the data of {@code txnToCopy}.
     */
    public TransactionBuilder(Transaction txnToCopy) {
        description = txnToCopy.getDescription();
        owner = txnToCopy.getOwner();
        status = txnToCopy.getStatus();
        value = txnToCopy.getValue();
    }


    /**
     * Sets the {@code Description} of the {@code Transaction} that we are building.
     */
    public TransactionBuilder withName(String description) {
        this.description = new Description(description);
        return this;
    }

    public Transaction build() {
        return new Transaction(description, value, status, owner);
    }
}
