package seedu.address.storage.json.serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.DeliveryJobSystem;
import seedu.address.model.ReadOnlyDeliveryJobSystem;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.storage.json.model.JsonAdaptedDeliveryJob;

/**
 * JsonSerializableDeliveryJobSystem
 */
@JsonRootName(value = "deliveryjobsystem")
public class JsonSerializableDeliveryJobSystem {

    public static final String MESSAGE_DUPLICATE_JOB = "Job list contains duplicate job(s).";

    private final List<JsonAdaptedDeliveryJob> jobs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDeliveryJobSystem} with the given persons and reminderList.
     */
    @JsonCreator
    public JsonSerializableDeliveryJobSystem(@JsonProperty("jobs") List<JsonAdaptedDeliveryJob> jobs) {
        this.jobs.addAll(jobs);
    }

    /**
     * Converts a given {@code ReadOnlyDeliveryJobSystem} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDeliveryJobSystem}.
     */
    public JsonSerializableDeliveryJobSystem(ReadOnlyDeliveryJobSystem source) {
        jobs.addAll(source.getDeliveryJobList().stream().map(JsonAdaptedDeliveryJob::new).collect(Collectors.toList()));
    }

    /**
     * ToModelType
     *
     * @return DeliveryJobSystem
     * @throws IllegalValueException
     */
    public DeliveryJobSystem toModelType() throws IllegalValueException {
        DeliveryJobSystem deliveryJobSystem = new DeliveryJobSystem();
        for (JsonAdaptedDeliveryJob jsonAdaptedDeliveryJob : jobs) {
            DeliveryJob job = jsonAdaptedDeliveryJob.toModelType();
            if (deliveryJobSystem.hasDeliveryJob(job)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_JOB);
            }
            deliveryJobSystem.addDeliveryJob(job);
        }
        return deliveryJobSystem;
    }

}
