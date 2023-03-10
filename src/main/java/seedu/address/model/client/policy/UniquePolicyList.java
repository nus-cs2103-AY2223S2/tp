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
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Client#isSameClient(Client)
 */
public class UniquePolicyList implements Iterable<Policy> {

    private final ObservableList<Policy> internalList = FXCollections.observableArrayList();
    private final ObservableList<Policy> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

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
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
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

    public void setPolicies(UniquePolicyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPolicies(List<Policy> policies) {
        requireAllNonNull(policies);
        if (!policiesAreUnique(policies)) {
            throw new DuplicatePolicyException();
        }

        internalList.setAll(policies);
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

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean policiesAreUnique(List<Policy> policies) {
        for (int i = 0; i < policies.size() - 1; i++) {
            for (int j = i + 1; j < policies.size(); j++) {
                if (policies.get(i).isSamePolicy(policies.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
