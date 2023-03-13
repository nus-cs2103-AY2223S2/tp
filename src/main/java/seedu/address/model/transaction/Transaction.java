package seedu.address.model.transaction;

import java.math.BigDecimal;
import java.util.Comparator;

public class Transaction {

    private final String description;
    private final BigDecimal value;
    private final TxnStatus status;

    public Transaction(String description, BigDecimal value) {
        this.description = description;
        this.value = value;
        this.status = new TxnStatus();
    }

    /**
     * Constructor with the additional specification of whether the transaction is closed.
     */
    private Transaction(String description, BigDecimal value, boolean isClosed) {
        this.description = description;
        this.value = value;
        this.status = new TxnStatus(isClosed);
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

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TxnStatus getStatus() {
        return status;
    }
}
