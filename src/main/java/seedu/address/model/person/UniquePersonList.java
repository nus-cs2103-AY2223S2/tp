package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns size of the current list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Returns true if any of the {@code Person}s in the internal list are considered duplicates of any single one of
     * the {@code Person}s in the given list.
     */
    public boolean containsAny(List<Person> toCheck) {
        requireAllNonNull(toCheck);

        assert !PersonUtil.hasDuplicates(internalList) : "internal list should not contain duplicates";
        assert !PersonUtil.hasDuplicates(toCheck) : "given list should not contain duplicates";

        List<Person> combinedList = new ArrayList<>(List.copyOf(internalList));
        combinedList.addAll(toCheck);
        return PersonUtil.hasDuplicates(combinedList);
    }

    /**
     * Returns the index of the first duplicate found between {@code newPersons} and
     * the list. Given {@code newPersons} should not contain any duplicates.
     * Returns -1 if none are found.
     */
    public int getDuplicateIndex(List<Person> newPersons) {
        requireAllNonNull(newPersons);

        assert !PersonUtil.hasDuplicates(internalList) : "internal list should not contain duplicates";
        assert !PersonUtil.hasDuplicates(newPersons) : "given list should not contain duplicates";

        List<Person> combinedList = new ArrayList<>(List.copyOf(internalList));
        combinedList.addAll(newPersons);
        int index = PersonUtil.findDuplicates(combinedList).getValue();
        return index == -1 ? index : index - internalList.size();
    }

    /**
     * Returns the String representing the duplicate field found between {@code newPerson} and the list.
     * Gives an empty String if none are found.
     */
    public String getDuplicatedString(Person duplicatedPerson) {
        requireNonNull(duplicatedPerson);

        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).isSamePerson(duplicatedPerson)) {
                return PersonUtil.findDuplicateFieldString(duplicatedPerson, internalList.get(i));
            }
        }
        return "";
    }

    /**
     * Returns the {@code String} representation of the duplicate field found between the given
     * {@code duplicateEditedPerson} and the entire address book, not considering any duplicates found between the
     * {@code duplicateEditedPerson} and the {@code notCounted} entry.
     * Returns empty {@code String} if no duplicates are found.
     */
    public String getDuplicateStringExceptFor(Person duplicatePerson, Person exceptFor) {
        requireAllNonNull(duplicatePerson, exceptFor);

        List<Person> tempList = new ArrayList<>(List.copyOf(internalList));
        if (!tempList.remove(exceptFor)) {
            throw new PersonNotFoundException();
        }

        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).isSamePerson(duplicatePerson)) {
                return PersonUtil.findDuplicateFieldString(duplicatePerson, tempList.get(i));
            }
        }
        return "";
    }


    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void addPerson(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Adds a list of persons to the list.
     * Each person must not already exist in the list.
     */
    public void addPersons(List<Person> toAdd) {
        requireNonNull(toAdd);
        if (containsAny(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.addAll(toAdd);
    }

    /**
     * Checks if the given {@code editedPerson} is a valid Person to replace the {@code target}, and that it is not a
     * duplicate of another existing person in the address book.
     */
    public boolean canEdit(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        List<Person> tempList = new ArrayList<>(List.copyOf(internalList));
        // removes the target, to check if the new editedPerson is a duplicate of any other existing person
        tempList.remove(index);
        return !tempList.stream().anyMatch(editedPerson::isSamePerson);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!canEdit(target, editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }
        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        return !PersonUtil.hasDuplicates(persons);
    }

    @Override
    public String toString() {
        return internalList.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

}
