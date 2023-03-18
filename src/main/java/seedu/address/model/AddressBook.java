package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueApplicationList;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.todo.InternshipTodo;
import seedu.address.model.todo.Note;
import seedu.address.model.todo.UniqueNoteList;
import seedu.address.model.todo.UniqueTodoList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueApplicationList applications;
    private final UniqueTodoList todos;
    private final UniqueNoteList notes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        applications = new UniqueApplicationList();
        todos = new UniqueTodoList();
        notes = new UniqueNoteList();
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
     * Replaces the contents of the person list with {@code applications}.
     * {@code applications} must not contain duplicate applications.
     */
    public void setApplications(List<InternshipApplication> applications) {
        this.applications.setApplications(applications);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the todo list with {@code todos}.
     * {@code persons} must not contain duplicate todos.
     */
    public void setTodo(List<InternshipTodo> todos) {
        this.todos.setTodo(todos);
    }

    /**
     * Replaces the contents of the note list with {@code notes}.
     * {@code persons} must not contain duplicate notes.
     */
    public void setNote(List<Note> notes) {
        this.notes.setNotes(notes);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setApplications(newData.getInternshipList());
    }

    /// application-level operations
    /**
     * Returns true if an internship application with the same identity
     * as {@code internshipApplication} exists in the address book.
     */
    public boolean hasApplication(InternshipApplication application) {
        requireNonNull(application);
        return applications.contains(application);
    }

    /**
     * Returns true if a todo with the same identity
     * as {@code todo} exists in the tracker.
     */
    public boolean hasTodo(InternshipTodo todo) {
        requireNonNull(todo);
        return todos.containsTodo(todo);
    }

    /**
     * Returns true if a todo with the same identity
     * as {@code todo} exists in the tracker.
     */
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return notes.containsNote(note);
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
     * Adds an internship application to the tracker.
     * The application must not already exist in the tracker.
     */
    public void addApplication(InternshipApplication application) {
        applications.add(application);
    }

    /**
     * Adds a todo to the tracker.
     * The todo must not already exist in the tracker.
     */
    public void addTodo(InternshipTodo todo) {
        todos.addTodo(todo);
    }

    /**
     * Adds a note to the tracker.
     * The note must not already exist in the tracker.
     */
    public void addNote(Note note) {
        notes.addNote(note);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the address book.
     * The application identity of {@code editedApplication}
     * must not be the same as another existing application in the address book.
     */
    public void setApplication(InternshipApplication target, InternshipApplication editedApplication) {
        requireNonNull(editedApplication);

        applications.setApplication(target, editedApplication);
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
     * Replaces the given todo {@code target} with {@code editedTodo}.
     * {@code target} must exist in the tracker.
     * The identity of {@code editedTodo} must not be the same as another existing todo in the tracker.
     */
    public void setTodo(InternshipTodo target, InternshipTodo editedTodo) {
        requireNonNull(editedTodo);

        todos.setTodo(target, editedTodo);
    }

    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in the tracker.
     * The identity of {@code editedNote} must not be the same as another existing note in the tracker.
     */
    public void setNote(Note target, Note editedNote) {
        requireNonNull(editedNote);

        notes.setNotes(target, editedNote);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeApplication(InternshipApplication key) {
        applications.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTodo(InternshipTodo key) {
        todos.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeNote(Note key) {
        notes.remove(key);
    }

    /**
     * Clear todo list.
     */
    public void clearTodo(ReadOnlyAddressBook newData) {
        setTodo(newData.getTodoList());
    }

    /**
     * Clear note list.
     */
    public void clearNote(ReadOnlyAddressBook newData) {
        setNote(newData.getNoteList());
    }

    //// util methods

    @Override
    public String toString() {
        return applications.asUnmodifiableObservableList().size() + " applications";
        // TODO: refine later
    }

    @Override
    public ObservableList<InternshipApplication> getInternshipList() {
        return applications.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<InternshipTodo> getTodoList() {
        return todos.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Note> getNoteList() {
        return notes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && applications.equals(((AddressBook) other).applications));
    }

    @Override
    public int hashCode() {
        return applications.hashCode();
    }
}
