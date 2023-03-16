package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

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

    private void setDeliveryJobs(List<DeliveryJob> jobs) {
        this.jobs.setDeliveryJobs(jobs);
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
     * Returns true if a delivery job with the same identity as {@code delivery job} exists in the address book.
     */
    public boolean hasDeliveryJob(DeliveryJob job) {
        requireNonNull(job);
        return jobs.contains(job);
    }

    /**
     * @return number of jobs in list
     */
    public int size() {
        return jobs.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryJobSystem // instanceof handles nulls
                && jobs.equals(((DeliveryJobSystem) other).jobs));
    }

    @Override
    public String toString() {
        return jobs.asUnmodifiableObservableList().size() + " delivery jobs";
    }

    @Override
    public int hashCode() {
        return jobs.hashCode();
    }
}
