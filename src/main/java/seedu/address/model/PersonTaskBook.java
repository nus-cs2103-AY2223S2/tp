package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.mapping.UniquePersonTaskList;


/**
 * Wraps all data at the personTask-book level
 * Duplicates are not allowed (by .equal comparison)
 */
public class PersonTaskBook implements ReadOnlyPersonTaskBook {

    private final UniquePersonTaskList personTasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        personTasks = new UniquePersonTaskList();
    }

    public PersonTaskBook() {}

    /**
     * Creates an PersonTaskBook using the PersonTasks in the {@code toBeCopied}
     */
    public PersonTaskBook(ReadOnlyPersonTaskBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the personTask list with {@code personTasks}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersonTasks(List<PersonTask> personTasks) {
        this.personTasks.setPersonTasks(personTasks);
    }

    /**
     * Resets the existing data of this {@code PersonTaskBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPersonTaskBook newData) {
        requireNonNull(newData);

        setPersonTasks(newData.getPersonTaskList());
    }

    //// person-level operations

    /**
     * Returns true if a personTask with the same identity as
     * {@code personTask} exists in the personTask book.
     */
    public boolean hasPersonTask(PersonTask personTask) {
        requireNonNull(personTask);
        return personTasks.contains(personTask);
    }

    /**
     * Adds a personTask to the personTask book.
     * The personTask must not already exist in the personTask book.
     */
    public void addPersonTask(PersonTask personTask) {
        personTasks.add(personTask);
    }


    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePersonTask(PersonTask key) {
        personTasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return personTasks.asUnmodifiableObservableList().size() + " personTasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<PersonTask> getPersonTaskList() {
        return personTasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PersonTaskBook // instanceof handles nulls
            && personTasks.equals(((PersonTaskBook) other).personTasks));
    }

    @Override
    public int hashCode() {
        return personTasks.hashCode();
    }
}
