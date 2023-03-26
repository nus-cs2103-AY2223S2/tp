package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.status.LeadStatus;

/**
 * Jackson-friendly version of {@link LeadStatus}.
 */
public class JsonAdaptedLeadStatus {

    public static final String INVALID_FORMAT_MESSAGE = "LeadStatus is invalid!";

    private final String statusName;
    private final String timestamp;

    /**
     * Constructs a {@code JsonAdaptedLeadStatus} with the given {@code statusName} and {@code timestamp}.
     */
    @JsonCreator
    public JsonAdaptedLeadStatus(@JsonProperty("statusName") String statusName,
            @JsonProperty("timestamp") String timestamp) {
        this.statusName = statusName;
        this.timestamp = timestamp;
    }

    /**
     * Constructs a {@code JsonAdaptedLeadStatus} with the given {@code LeadStatus}.
     */
    public JsonAdaptedLeadStatus(LeadStatus ls) {
        statusName = ls.getStatusName().getLabel();
        timestamp = ls.getInstantInIso();
    }

    /**
     * Converts this Jackson-friendly adapted LeadStatus object into the model's {@code LeadStatus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted LeadStatus.
     */
    public LeadStatus toModelType() throws IllegalValueException {
        if (!LeadStatus.isValidLeadStatus(statusName, timestamp)) {
            throw new IllegalValueException(INVALID_FORMAT_MESSAGE);
        }
        return new LeadStatus(statusName, timestamp);
    }


}
