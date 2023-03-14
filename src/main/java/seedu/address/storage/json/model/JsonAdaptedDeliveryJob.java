package seedu.address.storage.json.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.jobs.DeliveryJob;

/**
 * JsonAdaptedDeliveryJob
 */
public class JsonAdaptedDeliveryJob extends JsonAdapted<DeliveryJob> {

    private final String jobId;
    private final JsonAdaptedPerson recepient;
    private final JsonAdaptedPerson sender;
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
        @JsonProperty("jobid") String jobId,
        @JsonProperty("recepient") JsonAdaptedPerson recepient,
        @JsonProperty("sender") JsonAdaptedPerson sender,
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
        this.recepient = new JsonAdaptedPerson(source.getRecepient());
        this.sender = new JsonAdaptedPerson(source.getSender());
        this.slot = source.getDeliverSlot();
        this.earning = source.getEarning().value;
        this.isDelivered = source.getDeliveredStatus();
    }

    @Override
    public DeliveryJob toModelType() throws IllegalValueException {
        // TODO: refine later
        return new DeliveryJob(jobId, recepient.toModelType(), sender.toModelType(), slot, earning, isDelivered);
    }

}
