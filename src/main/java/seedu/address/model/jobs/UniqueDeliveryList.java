package seedu.address.model.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

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
     * Contains.
     */
    public boolean contains(DeliveryJob toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDeliveryJob);
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

    /**
     * setDeliveryJob
     *
     * @param target
     * @param editedJob
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
     * setDeliveryJobs
     *
     * @param replacement
     */
    public void setDeliveryJobs(UniqueDeliveryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * setDeliveryJobs
     *
     * @param jobs
     */
    public void setDeliveryJobs(List<DeliveryJob> jobs) {
        requireAllNonNull(jobs);
        if (!deliveryJobsAreUnique(jobs)) {
            throw new DuplicateDeliveryJobException();
        }

        internalList.setAll(jobs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<DeliveryJob> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * @return number of jobs
     */
    public int size() {
        return internalList.size();
    }

    @Override
    public Iterator<DeliveryJob> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDeliveryList // instanceof handles nulls
                && internalList.equals(((UniqueDeliveryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    private boolean deliveryJobsAreUnique(List<DeliveryJob> jobs) {
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
