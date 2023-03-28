package seedu.address.storage.json.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliverySlot;
import seedu.address.model.jobs.Earning;

/**
 * JsonAdaptedDeliveryJob
 */
public class JsonAdaptedDeliveryJob extends JsonAdapted<DeliveryJob> {

    private final String jobId;
    private final String recipient;
    private final String sender;
    private final String deliveryDate;
    private final String deliverySlot;
    private final String earning;
    private final boolean isDelivered;
    private final String description;

    /**
     * JsonAdaptedDeliveryJob
     *
     * @param jobId
     * @param recipient
     * @param sender
     * @param deliverySlot
     * @param earning
     */
    public JsonAdaptedDeliveryJob(
            @JsonProperty("jobId") String jobId,
            @JsonProperty("recipientId") String recipient,
            @JsonProperty("senderId") String sender,
            @JsonProperty("date") String deliveryDate,
            @JsonProperty("slot") String deliverySlot,
            @JsonProperty("earning") String earning,
            @JsonProperty("isDelivered") boolean isDelivered,
            @JsonProperty("description") String description) {
        this.jobId = jobId;
        this.recipient = recipient;
        this.sender = sender;
        this.deliveryDate = deliveryDate;
        this.deliverySlot = deliverySlot;
        this.earning = earning;
        this.isDelivered = isDelivered;
        this.description = description;
    }

    /**
     * JsonAdaptedDeliveryJob.
     *
     * @param source
     */
    public JsonAdaptedDeliveryJob(DeliveryJob source) {
        this.jobId = source.getJobId();
        this.recipient = source.getRecipientId();
        this.sender = source.getSenderId();
        this.deliveryDate = source.getDeliveryDate().orElseGet(() -> DeliveryDate.placeholder()).date;
        this.deliverySlot = source.getDeliverySlot().orElseGet(() -> DeliverySlot.placeholder()).value;
        this.earning = source.getEarning().orElseGet(() -> Earning.placeholder()).value;
        this.isDelivered = source.getDeliveredStatus();
        this.description = source.getDescription();
    }

    @Override
    public DeliveryJob toModelType() throws IllegalValueException {
        if (deliveryDate.equals(DeliveryDate.placeholder().date)
                || deliverySlot.equals(DeliverySlot.placeholder().value)) {
            return new DeliveryJob(jobId, recipient, sender, Optional.empty(),
                    Optional.empty(),
                    Optional.of(new Earning(earning)), isDelivered, description);
        }
        return new DeliveryJob(jobId, recipient, sender, Optional.of(new DeliveryDate(deliveryDate)),
                Optional.of(new DeliverySlot(deliverySlot)),
                Optional.of(new Earning(earning)), isDelivered, description);
    }

}
