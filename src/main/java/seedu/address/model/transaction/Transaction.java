package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * Represents a Transaction in the sales book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Transaction {

    private final String description;
    private final BigDecimal value;
    private final TxnStatus status;

    /**
     * Constuctor
     * @param description description for txn
     * @param value txn amount
     */

    public Transaction(String description, BigDecimal value) {
        requireAllNonNull(description, value);
        this.description = description;
        this.value = value;
        this.status = new TxnStatus();
    }

    /**
     * Constructor with the additional specification of whether the transaction is closed.
     */
    private Transaction(String description, BigDecimal value, boolean isClosed) {
        requireAllNonNull(description, value);
        this.description = description;
        this.value = value;
        this.status = new TxnStatus(isClosed);
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TxnStatus getStatus() {
        return status;
    }

    /**
     * Returns true if the other transaction has the same description and value.
     */
    public boolean isSameTransaction(Transaction other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getDescription().equals(description)
                && other.getValue().equals(value);
    }

    /**
     * Returns true if both transactions have the same identity and data fields.
     * This defines a stronger notion of equality between two transactions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return otherTransaction.getDescription().equals(getDescription())
                && otherTransaction.getValue().equals(getValue())
                && otherTransaction.getStatus().equals(getStatus());
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, value, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Transaction: ")
                .append(getDescription())
                .append("; Value: ")
                .append(getValue())
                .append("; Status: ")
                .append(getStatus());
        return builder.toString();
    }
}

