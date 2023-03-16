package seedu.socket.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.socket.model.person.Person.CATEGORY_ADDRESS;
import static seedu.socket.model.person.Person.CATEGORY_EMAIL;
import static seedu.socket.model.person.Person.CATEGORY_GITHUB;
import static seedu.socket.model.person.Person.CATEGORY_NAME;
import static seedu.socket.model.person.Person.CATEGORY_PHONE;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.socket.model.person.exceptions.DuplicatePersonException;
import seedu.socket.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
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
     */
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
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
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts the list by given {@code category}.
     * @param category
     */
    public void sort(String category) {
        if (category.equals(CATEGORY_NAME)) {
            internalList.sort(Comparator.comparing((Person a) -> a.getName().toString().toLowerCase()));
        } else if (category.equals(CATEGORY_PHONE)) {
            internalList.sort((Person a, Person b) -> {
                if (a.getPhone().toString().isEmpty() && b.getPhone().toString().isEmpty()) {
                    return a.getName().toString().toLowerCase().compareTo(b.getName().toString().toLowerCase());
                } else if (a.getPhone().toString().isEmpty()) {
                    return 1;
                } else if (b.getPhone().toString().isEmpty()) {
                    return -1;
                } else {
                    return a.getPhone().toString().toLowerCase().compareTo(b.getPhone().toString().toLowerCase());
                }
            });
        } else if (category.equals(CATEGORY_EMAIL)) {
            internalList.sort((Person a, Person b) -> {
                if (a.getEmail().toString().isEmpty() && b.getEmail().toString().isEmpty()) {
                    return a.getName().toString().toLowerCase().compareTo(b.getName().toString().toLowerCase());
                } else if (a.getEmail().toString().isEmpty()) {
                    return 1;
                } else if (b.getEmail().toString().isEmpty()) {
                    return -1;
                } else {
                    return a.getEmail().toString().toLowerCase().compareTo(b.getEmail().toString().toLowerCase());
                }
            });
        } else if (category.equals(CATEGORY_ADDRESS)) {
            internalList.sort((Person a, Person b) -> {
                if (a.getAddress().toString().isEmpty() && b.getAddress().toString().isEmpty()) {
                    return a.getName().toString().toLowerCase().compareTo(b.getName().toString().toLowerCase());
                } else if (a.getAddress().toString().isEmpty()) {
                    return 1;
                } else if (b.getAddress().toString().isEmpty()) {
                    return -1;
                } else {
                    return a.getAddress().toString().toLowerCase().compareTo(b.getAddress().toString().toLowerCase());
                }
            });
        } else if (category.equals(CATEGORY_GITHUB)) {
            internalList.sort((Person a, Person b) -> {
                if (a.getProfile().toString().isEmpty() && b.getProfile().toString().isEmpty()) {
                    return a.getName().toString().toLowerCase().compareTo(b.getName().toString().toLowerCase());
                } else if (a.getProfile().toString().isEmpty()) {
                    return 1;
                } else if (b.getProfile().toString().isEmpty()) {
                    return -1;
                } else {
                    return a.getProfile().toString().toLowerCase().compareTo(b.getProfile().toString().toLowerCase());
                }
            });
        }
    }
}
