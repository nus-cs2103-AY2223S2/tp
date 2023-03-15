package seedu.address.storage.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.jobs.DeliveryJob;

/**
 * JsonAdaptedDeliveryJob
 */
public class JsonAdaptedDeliveryJob extends JsonAdapted<DeliveryJob> {

    private final String jobId;
    private final String recepient;
    private final String sender;
    private final String slot;
    private final String earning;
    private final boolean isDelivered;

    /**
     * JsonAdaptedDeliveryJob
     *
     * @param jobId
     * @param recepient
     * @param sender
     * @param deliverySlot
     * @param earning
     */
    public JsonAdaptedDeliveryJob(
        @JsonProperty("jobId") String jobId,
        @JsonProperty("recepientId") String recepient,
        @JsonProperty("senderId") String sender,
        @JsonProperty("slot") String deliverySlot,
        @JsonProperty("earning") String earning,
        @JsonProperty("isDelivered") boolean isDelivered
    ) {
        this.jobId = jobId;
        this.recepient = recepient;
        this.sender = sender;
        this.slot = deliverySlot;
        this.earning = earning;
        this.isDelivered = isDelivered;
    }

    /**
     * JsonAdaptedDeliveryJob.
     *
     * @param source
     */
    public JsonAdaptedDeliveryJob(DeliveryJob source) {
        this.jobId = source.getJobId();
        this.recepient = source.getRecepientId();
        this.sender = source.getSenderId();
        this.slot = source.getDeliverSlot();
        this.earning = source.getEarning().value;
        this.isDelivered = source.getDeliveredStatus();
    }

    @Override
    public DeliveryJob toModelType() throws IllegalValueException {
        return new DeliveryJob(jobId, recepient, sender, slot, earning, isDelivered);
    }

}
