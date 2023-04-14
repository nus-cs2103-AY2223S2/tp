package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.CannotRedoAddressBookException;
import seedu.address.commons.exceptions.CannotUndoAddressBookException;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Subclass of AddressBook that stores the state of the AddressBook at a particular point in time.
 * This is used to implement the undo/redo feature.
 * Has an undo/redo history of AddressBook states.
 */
public class VersionedAddressBook extends AddressBook {

    private final UniquePersonList persons;
    private final List<ReadOnlyAddressBook> addressBookStateList;

    private int currentStatePointer;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    /**
     * Creates a VersionedAddressBook using an AddressBookStateList.
     * @param addressBookStateList List of AddressBook states
     */
    public VersionedAddressBook(List<ReadOnlyAddressBook> addressBookStateList) {
        super();
        this.addressBookStateList = addressBookStateList;
        currentStatePointer = addressBookStateList.size() - 1;
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     * @param toBeCopied AddressBook to be copied
     */
    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied) {
        super();
        resetData(toBeCopied);
        this.addressBookStateList = new ArrayList<>();
        this.currentStatePointer = -1;
        this.commit();
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied} and an AddressBookStateList.
     * @param toBeCopied AddressBook to be copied
     * @param addressBookStateList List of AddressBook states
     */
    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied, List<ReadOnlyAddressBook> addressBookStateList) {
        this(addressBookStateList);
        resetData(toBeCopied);
    }

    //// history operations

    /**
     * Saves a copy of the current {@code AddressBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        requireNonNull(addressBookStateList);
        removeStatesAfterCurrentPointer();
        AddressBook toAdd = new AddressBook(this);
        addressBookStateList.add(toAdd);
        currentStatePointer = addressBookStateList.size() - 1;
    }

    /**
     * Restores the address book to its previous state.
     */
    public void undo() throws CannotUndoAddressBookException {
        if (!canUndo()) {
            throw new CannotUndoAddressBookException("AddressBook cannot be undone anymore!");
        }
        currentStatePointer--;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Restores the address book to its previously undone state.
     */
    public void redo() throws CannotRedoAddressBookException {
        if (!canRedo()) {
            throw new CannotRedoAddressBookException("AddressBook cannot be redone anymore!");
        }
        currentStatePointer++;
        resetData(addressBookStateList.get(currentStatePointer));
    }

    /**
     * Removes all states after the current pointer.
     */
    private void removeStatesAfterCurrentPointer() {
        assert currentStatePointer < addressBookStateList.size();
        addressBookStateList.subList(currentStatePointer + 1, addressBookStateList.size()).clear();
    }

    /**
     * Returns true if {@code undo()} has address book states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has address book states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < addressBookStateList.size() - 1;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
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

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    public List<ReadOnlyAddressBook> getAddressBookStateList() {
        return addressBookStateList;
    }

    public Person findPersonByNric(Nric nric) {
        return persons.findPersonByNric(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VersionedAddressBook // instanceof handles nulls
                && persons.equals(((VersionedAddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

