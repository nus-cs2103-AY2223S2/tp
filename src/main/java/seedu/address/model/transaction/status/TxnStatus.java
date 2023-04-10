package seedu.address.model.transaction.status;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.transaction.status.TxnStatusName.CLOSED;
import static seedu.address.model.transaction.status.TxnStatusName.OPEN;
import static seedu.address.model.transaction.status.TxnStatusName.isValidStatusName;

import java.time.Duration;
import java.time.Instant;

import seedu.address.model.Status;

/**
 * Status of Transaction
 */
public class TxnStatus extends Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Transaction status should only be " + OPEN + " or " + CLOSED + ", and should not be blank.";

    private final TxnStatusName statusName;
    private final Instant timestamp;

    /**
     * Construct a {@code TxnStatus}, given a valid String key for a TxnStatusName.
     */
    public TxnStatus(String statusName) {
        requireNonNull(statusName);
        checkArgument(isValidStatusName(statusName), MESSAGE_CONSTRAINTS);
        this.statusName = TxnStatusName.get(statusName);
        this.timestamp = Instant.now();
    }

    /**
     * Construct a {@code TxnStatus}, with a pre-defined timestamp, given a valid String key for a TxnStatusName.
     */
    public TxnStatus(String statusName, String timestampInIso) {
        requireAllNonNull(statusName, timestampInIso);
        checkArgument(isValidStatusName(statusName), MESSAGE_CONSTRAINTS);
        checkArgument(isValidTimestamp(timestampInIso), MESSAGE_CONSTRAINTS);
        this.statusName = TxnStatusName.get(statusName);
        this.timestamp = Instant.parse(timestampInIso);
    }

    /**
     * Get the status name of the TxnStatus.
     * @return True if the transaction is closed.
     */
    public TxnStatusName getStatusName() {
        return statusName;
    }


    /**
     * Returns the timestamp of the TxnStatus
     */
    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public Duration getDurationSinceLastUpdate() {
        return Duration.between(timestamp, Instant.now());
    }

    /**
     * Returns the ISO8601 representation of the timestamp of the TxnStatus
     */
    @Override
    public String getInstantInIso() {
        return timestamp.toString();
    }

    /**
     *  Returns the String representation pair of a TxnStatus name and timestamp forms a valid
     *  TxnStatus.
     */
    public static boolean isValidTxnStatus(String name, String timestamp) {
        return TxnStatusName.isValidStatusName(name)
                && Status.isValidTimestamp(timestamp);
    }


    @Override
    public String toString() {
        return statusName.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TxnStatus // instanceof handles nulls
                && statusName.equals(((TxnStatus) other).statusName)); // state check
    }

    @Override
    public int hashCode() {
        return statusName.hashCode();
    }

}
