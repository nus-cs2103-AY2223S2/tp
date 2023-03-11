package seedu.address.model;

import static java.util.Objects.requireNonNull;

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
        resetData(deliveryJobSystem);
    }

    /**
     * resetData
     *
     * @param newData
     */
    public void resetData(ReadOnlyDeliveryJobSystem newData) {
        requireNonNull(newData);
        setDeliveryJobs(newData.getDeliveryJobList());
    }

    private void setDeliveryJobs(ObservableList<DeliveryJob> deliveryJobList) {
        this.jobs.setDeliveryJobs(deliveryJobList);
    }

    /**
     * setDeliveryJob
     *
     * @param target
     * @param editedJob
     */
    public void setDeliveryJob(DeliveryJob target, DeliveryJob editedJob) {
        requireNonNull(editedJob);

        jobs.setDeliveryJob(target, editedJob);
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

    /**
     * removeDeliveryJob
     *
     * @param key
     */
    public void removeDeliveryJob(DeliveryJob key) {
        jobs.remove(key);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasDeliveryJob(DeliveryJob job) {
        requireNonNull(job);
        return jobs.contains(job);
    }
}
