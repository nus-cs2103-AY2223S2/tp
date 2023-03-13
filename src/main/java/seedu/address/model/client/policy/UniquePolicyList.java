package seedu.address.model.client.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.exceptions.DuplicatePolicyException;
import seedu.address.model.client.exceptions.PolicyNotFoundException;


/**
 * Represents a list of policies that enforces no two policies in the list have the same identity.
 * A policy is considered the same as another policy in the list if they have the same identity.
 * Identity is defined by {@code Policy#isSamePolicy(Policy)}.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Policy#isSamePolicy(Policy)
 */
public class UniquePolicyList implements Iterable<Policy> {

    private final ObservableList<Policy> internalList = FXCollections.observableArrayList();
    private final ObservableList<Policy> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    static boolean policiesAreUnique(List<Policy> policies) {
        for (int i = 0; i < policies.size() - 1; i++) {
            for (int j = i + 1; j < policies.size(); j++) {
                if (policies.get(i).isSamePolicy(policies.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the list contains an equivalent policy as the given argument.
     */
    public boolean contains(Policy toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePolicy);
    }

    /**
     * Adds a policy to the list.
     * The policy must not already exist in the list.
     */
    public void add(Policy toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePolicyException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns true if the list is empty.
     *
     * @return true if the list is empty.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Replaces the policy {@code target} in the list with {@code editedPolicy}.
     * {@code target} must exist in the list.
     * The policy identity of {@code editedPolicy} must not be the same as another existing policy in the list.
     */
    public void setPolicy(Policy target, Policy editedPolicy) {
        requireAllNonNull(target, editedPolicy);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PolicyNotFoundException();
        }

        if (!target.isSamePolicy(editedPolicy) && contains(editedPolicy)) {
            throw new DuplicatePolicyException();
        }

        internalList.set(index, editedPolicy);
    }

    /*
    public void setPolicies(UniquePolicyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }
    */


    /*
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     *
    public void setPolicies(List<Policy> policies) {
        requireAllNonNull(policies);
        if (!policiesAreUnique(policies)) {
            throw new DuplicatePolicyException();
        }
        internalList.setAll(policies);
    }
    */

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Policy toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PolicyNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Policy> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Policy> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePolicyList // instanceof handles nulls
                && internalList.equals(((UniquePolicyList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
