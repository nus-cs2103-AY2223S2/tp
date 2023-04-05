package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.information.Nric;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object)
 * to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList<T extends Person> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains a person with the given nric.
     */
    public boolean contains(Nric nric) {
        requireNonNull(nric);
        return internalList.stream().anyMatch(person -> person.getNric().equals(nric));
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     *
     * @param toCheck Person to check against.
     * @return True if the person is in the list and false otherwise.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     *
     * @param toAdd Person to be added.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     *
     * @param target Person to edit.
     * @param editedPerson Replacement person.
     */
    public void setPerson(T target, T editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }
        assert index < internalList.size() : "Index should be in range";

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     *
     * @param toRemove Person to remove.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Retrieves one of the persons with the given nric from the list.
     * The person with such a nric must exist in the list.
     *
     * @param nric Nric of the person.
     * @return Person with the nric.
     */
    public T get(Nric nric) {
        requireNonNull(nric);
        FilteredList<T> filteredList = internalList.filtered((T person) -> person.getNric().equals(nric));
        T result = filteredList.size() == 0 ? null : filteredList.get(0);
        if (result == null) {
            throw new PersonNotFoundException();
        }
        return result;
    }

    /**
     * Replaces the contents of this list with the list in {@code replacement}.
     * The internal list of {@code replacement} must not contain duplicate persons.
     *
     * @param replacement Replacement containing a list of persons.
     */
    public void setPersons(UniquePersonList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     *
     * @param persons Replacement list of persons.
     */
    public void setPersons(List<T> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return Unmodifiable list of persons.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Creates and returns a copy of persons in the list as a Java List.
     *
     * @return Full list of persons.
     */
    public List<T> asList() {
        ArrayList<T> result = new ArrayList<>();
        for (T person : this) {
            result.add(person);
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList<?>) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<T> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
