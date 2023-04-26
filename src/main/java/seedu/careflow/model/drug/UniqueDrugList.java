package seedu.careflow.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.careflow.model.drug.exceptions.DrugNotFoundException;
import seedu.careflow.model.drug.exceptions.DuplicateDrugException;


/**
 * A list of drugs that enforces uniqueness between its elements and does not allow nulls.
 * A drug is considered unique by comparing using {@code Drug#isSameDrug(Drug)}. As such, adding and updating of
 * drug uses Drug#isSameDrug(Drug) for equality so as to ensure that the drug being added or updated is
 * unique in terms of identity in the UniqueDrugList. However, the removal of a drug uses Drug#equals(Object) so
 * as to ensure that the drug with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Drug#isSameDrug(Drug)
 */
public class UniqueDrugList implements Iterable<Drug> {
    private final ObservableList<Drug> internalList = FXCollections.observableArrayList();
    private final ObservableList<Drug> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent drug as the given argument.
     */
    public boolean contains(Drug toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDrug);
    }

    /**
     * Adds a drug to the list.
     * The drug must not already exist in the list.
     */
    public void add(Drug toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDrugException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the drug {@code target} in the list with {@code editedDrug}.
     * {@code target} must exist in the list.
     * The drug identity of {@code editedDrug} must not be the same as another existing drug in the list.
     */
    public void setDrug(Drug target, Drug editedDrug) {
        requireAllNonNull(target, editedDrug);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DrugNotFoundException();
        }

        if (!target.isSameDrug(editedDrug) && contains(editedDrug)) {
            throw new DuplicateDrugException();
        }

        internalList.set(index, editedDrug);
    }

    /**
     * Removes the equivalent drug from the list.
     * The drug must exist in the list.
     */
    public void remove(Drug toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DrugNotFoundException();
        }
    }

    public void setDrugs(UniqueDrugList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code drugs}.
     * {@code drugs} must not contain duplicate persons.
     */
    public void setDrugs(List<Drug> drugs) {
        requireAllNonNull(drugs);
        if (!drugsAreUnique(drugs)) {
            throw new DuplicateDrugException();
        }

        internalList.setAll(drugs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Drug> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Drug> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDrugList // instanceof handles nulls
                && internalList.equals(((UniqueDrugList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code drugs} contains only unique drugs.
     */
    private boolean drugsAreUnique(List<Drug> drugs) {
        for (int i = 0; i < drugs.size() - 1; i++) {
            for (int j = i + 1; j < drugs.size(); j++) {
                if (drugs.get(i).isSameDrug(drugs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
