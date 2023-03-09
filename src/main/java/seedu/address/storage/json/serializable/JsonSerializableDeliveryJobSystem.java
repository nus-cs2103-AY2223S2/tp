package seedu.address.storage.json.serializable;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.DeliveryJobSystem;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.storage.json.model.JsonAdaptedDeliveryJob;

/**
 * JsonSerializableDeliveryJobSystem
 */
public class JsonSerializableDeliveryJobSystem {

    private final List<JsonAdaptedDeliveryJob> jobs = new ArrayList<>();

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
            // if (addressBook.hasPerson(person)) {
            //     throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            // }
            deliveryJobSystem.addDeliveryJob(job);
        }
        return deliveryJobSystem;
    }

}
