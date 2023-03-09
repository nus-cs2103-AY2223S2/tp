package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.UniqueDeliveryList;

/**
 * DeliveryJobSystem
 */
public class DeliveryJobSystem implements ReadOnlyDeliveryJobSystem {

    private final UniqueDeliveryList jobs;

    {
        jobs = new UniqueDeliveryList();
    }

    /**
     * DeliveryJobSystem
     */
    public DeliveryJobSystem() {}

    /**
     * DeliveryJobSystem
     *
     * @param deliveryJobSystem
     */
    public DeliveryJobSystem(ReadOnlyDeliveryJobSystem deliveryJobSystem) {
        this();
        //TODO: resetData(deliveryJobSystem)
    }

    /**
     * DeliveryJobSystem
     */
    public ObservableList<DeliveryJob> getDeliveryJobList() {
        return jobs.asUnmodifiableObservableList();
    }

    /**
     * addDeliveryJob
     *
     * @param job
     */
    public void addDeliveryJob(DeliveryJob job) {
        jobs.add(job);
    }
}
