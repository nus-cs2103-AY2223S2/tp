package seedu.address.model.transaction;

import java.time.LocalDateTime;

/**
 * Status of Transaction
 */
public class TxnStatus {

    private final boolean isClosed;
    private final LocalDateTime lastUpdate;

    /**
     * Default constructor, constructs an new open transaction
     */
    public TxnStatus() {
        this.isClosed = false;
        this.lastUpdate = LocalDateTime.now();
    }

    /**
     * Constructor to construct a transaction with specified status
     * @param isClosed boolean, if the transaction is done
     */
    public TxnStatus(boolean isClosed) {
        this.isClosed = isClosed;
        this.lastUpdate = LocalDateTime.now();
    }

    /**
     * Check if the transaction is closed
     * @return True if the transaction is closed.
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Get the time when the transaction is last updated.
     * @return time of last update
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
