package seedu.address.model.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.jobs.exceptions.DeliveryJobNotFoundException;
import seedu.address.model.jobs.exceptions.DuplicateDeliveryJobException;

/**
 * UniqueDeliveryList
 */
public class UniqueDeliveryList implements Iterable<DeliveryJob> {

    private final ObservableList<DeliveryJob> internalList = FXCollections.observableArrayList();
    private final ObservableList<DeliveryJob> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<DeliveryJob> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<DeliveryJob> iterator() {
        return internalList.iterator();
    }

    /**
     * Contains.
     */
    public boolean contains(DeliveryJob toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDeliveryJob);
    }

    /**
     * setDeliveryJobs
     *
     * @param deliveryJobs
     */
    public void setDeliveryJobs(ObservableList<DeliveryJob> deliveryJobs) {
        requireAllNonNull(deliveryJobs);
        if (!deliveryJobsAreUnique(deliveryJobs)) {
            throw new DuplicateDeliveryJobException();
        }

        internalList.setAll(deliveryJobs);
    }

    /**
     * setDeliveryJob
     *
     * @param target
     * @param editedPerson
     */
    public void setDeliveryJob(DeliveryJob target, DeliveryJob editedJob) {
        requireAllNonNull(target, editedJob);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DeliveryJobNotFoundException();
        }

        if (!target.isSameDeliveryJob(editedJob) && contains(editedJob)) {
            throw new DuplicateDeliveryJobException();
        }

        internalList.set(index, editedJob);
    }

    /**
     * Removes.
     */
    public void remove(DeliveryJob toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DeliveryJobNotFoundException();
        }
    }

    /**
     * add
     *
     * @param toAdd
     */
    public void add(DeliveryJob toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDeliveryJobException();
        }
        internalList.add(toAdd);
    }

    private boolean deliveryJobsAreUnique(ObservableList<DeliveryJob> jobs) {
        for (int i = 0; i < jobs.size() - 1; i++) {
            for (int j = i + 1; j < jobs.size(); j++) {
                if (jobs.get(i).isSameDeliveryJob(jobs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
