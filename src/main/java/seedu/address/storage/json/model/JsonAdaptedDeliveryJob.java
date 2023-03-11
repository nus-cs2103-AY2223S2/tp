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
    private final String slot;
    private final double earning;

    /**
     * JsonAdaptedDeliveryJob
     *
     * @param jobId
     * @param recepient
     * @param deliverySlot
     * @param earning
     */
    public JsonAdaptedDeliveryJob(
        @JsonProperty("jobid") String jobId,
        @JsonProperty("recepient") JsonAdaptedPerson recepient,
        @JsonProperty("slot") String deliverySlot,
        @JsonProperty("earning") double earning
    ) {
        this.jobId = jobId;
        this.recepient = recepient;
        this.slot = deliverySlot;
        this.earning = earning;
    }

    /**
     * JsonAdaptedDeliveryJob.
     *
     * @param source
     */
    public JsonAdaptedDeliveryJob(DeliveryJob source) {
        this.jobId = source.getJobId();
        this.recepient = new JsonAdaptedPerson(source.getRecepient());
        this.slot = source.getDeliverSlot();
        this.earning = source.getEarning();
    }

    @Override
    public DeliveryJob toModelType() throws IllegalValueException {
        // TODO: refine later
        return new DeliveryJob(jobId, recepient.toModelType(), slot, earning);
    }

}
