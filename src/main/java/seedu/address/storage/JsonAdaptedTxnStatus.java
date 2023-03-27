package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.status.TxnStatus;

/**
 * Jackson-friendly version of {@link TxnStatus}.
 */
public class JsonAdaptedTxnStatus {

    public static final String INVALID_FORMAT_MESSAGE = "TxnStatus is invalid!";

    private final String statusName;
    private final String timestamp;

    /**
     * Constructs a {@code JsonAdaptedTxnStatus} with the given {@code statusName} and {@code timestamp}.
     */
    @JsonCreator
    public JsonAdaptedTxnStatus(@JsonProperty("statusName") String statusName,
                                 @JsonProperty("timestamp") String timestamp) {
        this.statusName = statusName;
        this.timestamp = timestamp;
    }

    /**
     * Constructs a {@code JsonAdaptedTxnStatus} from a {@code TxnStatus}.
     */
    public JsonAdaptedTxnStatus(TxnStatus ls) {
        statusName = ls.getStatusName().getLabel();
        timestamp = ls.getInstantInIso();
    }

    /**
     * Converts this Jackson-friendly adapted txn status object into the model's {@code TxnStatus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted txn status.
     */
    public TxnStatus toModelType() throws IllegalValueException {
        if (!TxnStatus.isValidTxnStatus(statusName, timestamp)) {
            throw new IllegalValueException(INVALID_FORMAT_MESSAGE);
        }
        return new TxnStatus(statusName, timestamp);
    }


}
