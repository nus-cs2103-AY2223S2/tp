package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    private final ArrayList<ReadOnlyAddressBook> addressBookStateList = new ArrayList<>();

    private int currentStatePointer;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
        addressBookStateList.add(toBeCopied);
        currentStatePointer = 0;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    //// person-level operations

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same identity as any person in {@code newPersons} exists in the address book.
     */
    public boolean hasPersons(List<Person> newPersons) {
        requireAllNonNull(newPersons);
        return persons.containsAny(newPersons);
    }

    /**
     * Returns the index of the first duplicate found between {@code newPersons} and the address book.
     * Returns -1 if none are found.
     */
    public int getDuplicateIndex(List<Person> newPersons) {
        requireAllNonNull(newPersons);
        return persons.getDuplicateIndex(newPersons);
    }

    /**
     * Returns the duplicated field that was found between {@code duplicatedPerson} and the address book.
     */
    public String getDuplicateString(Person duplicatedPerson) {
        return persons.getDuplicatedString(duplicatedPerson);
    }

    /**
     * Returns the duplicates field that was found between {@code duplicatedEditedPerson} and the address book,
     * not considering the {@code notCounted}.
     */
    public String getDuplicateStringForEdit(Person duplicateEditedPerson, Person notCounted) {
        return persons.getDuplicateStringExceptFor(duplicateEditedPerson, notCounted);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.addPerson(p);
    }

    /**
     * Adds a list of persons to the address book.
     * Each person in the list must not already exist in the address book.
     */
    public void addPersons(List<Person> p) {
        persons.addPersons(p);
    }

    /**
     * Checks if the {@code editedPerson} is a valid Person to replace the {@code target}, and that it is not a
     * duplicate of another existing person in the address book.
     */
    public boolean canEdit(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        return persons.canEdit(target, editedPerson);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Saves the current address book state to history.
     *
     * @param currentState The current state of the address book.
     */
    public void commit(ReadOnlyAddressBook currentState) {
        if (currentStatePointer < addressBookStateList.size() - 1) {
            // need to remove all states to the right
            int numStatesToRemove = addressBookStateList.size() - 1 - currentStatePointer;
            for (int i = 0; i < numStatesToRemove; i++) {
                addressBookStateList.remove(addressBookStateList.size() - 1);
            }
        }
        addressBookStateList.add(new AddressBook(currentState));
        currentStatePointer += 1;
    }

    /**
     * Restores the previous address book state from history.
     */
    public void undo() {
        ReadOnlyAddressBook previousState = addressBookStateList.get(currentStatePointer - 1);
        resetData(previousState);
        currentStatePointer -= 1;
    }

    /**
     * Checks whether there are old address book states in history to undo.
     *
     * @return A boolean indicating if there are old address book states in history.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    //// util methods

    @Override
    public String toString() {
        return persons.toString();
        // return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
