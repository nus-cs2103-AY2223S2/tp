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

    private Transaction(String description, BigDecimal value, boolean isClosed) {
        this.description = description;
        this.value = value;
        this.status = new TxnStatus();
    }

    public Transaction close() {
        return new Transaction(description, value, true);
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
