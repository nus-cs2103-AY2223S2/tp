package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Comment;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tasks = new UniqueTaskList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
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
     * Replaces the contents of the task list with {@code tasks}.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTasks(newData.getTaskList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Returns true if a task with the same index as {@code taskIndex} exists in the address book.
     * @param taskIndex
     * @return
     */
    public boolean hasTaskIndex(Index taskIndex) {
        requireNonNull(taskIndex);
        return tasks.checkIndex(taskIndex);
    }

    /**
     * Returns true if a person with the same index as {@code personIndex} exists in the address book.
     * @param personIndex
     * @return
     */
    public boolean hasPersonIndex(Index personIndex) {
        requireNonNull(personIndex);
        return persons.checkIndex(personIndex);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a task to the address book.
     * The task must not already exist in the address book.
     */
    public void addTask(Task p) {
        tasks.add(p);
    }

    /**
     * Marks the given task {@code task} as done.
     */
    public void markTask(Task task) {
        task.mark();
    }

    /**
     * Unmarks the given task {@code task} as not done.
     */
    public void unmarkTask(Task task) {
        task.unmark();
    }

    /**
     * Adds given comment to the specified task.
     */
    public void commentOnTask(Comment comment, Task toReceiveComment) {
        toReceiveComment.setTaskComment(comment);
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

    // /**
    //  * Assigns a task to a person.
    //  * @param taskIndex
    //  * @param personIndex
    //  */
    // public void assignTask(Index taskIndex, Index personIndex) {
    //     String personName = persons.getPerson(personIndex).getName().toString();
    //     System.out.println(personName);
    //     tasks.assignTask(taskIndex, personIndex, personName);
    // }

    public void assignTask(Task taskToAssign, Task assignedTask, Index taskIndex) {
        tasks.assignTask(taskToAssign, assignedTask, taskIndex);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
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
