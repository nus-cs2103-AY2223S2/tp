package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.Person;
import seedu.address.model.todo.InternshipTodo;
import seedu.address.model.todo.Note;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<InternshipApplication> PREDICATE_SHOW_ALL_APPLICATIONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<InternshipTodo> PREDICATE_SHOW_ALL_TODO = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Note> PREDICATE_SHOW_ALL_NOTES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code internEase}.
     */
    void setInternEase(ReadOnlyAddressBook internEase);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if an internship application with the same identity as
     * {@code internshipApplication} exists in the address book.
     */
    boolean hasApplication(InternshipApplication person);

    /**
     * Returns true if a todo with the same identity as
     * {@code todo} exists in the tracker.
     */
    boolean hasTodo(InternshipTodo todo);

    /**
     * Returns true if a note with the same identity as
     * {@code note} exists in the tracker.
     */
    boolean hasNote(Note note);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given internship application.
     * The application must exist in the address book.
     */
    void deleteInternship(InternshipApplication application);

    /**
     * Deletes the given todo.
     * The todo must exist in the todo list.
     */
    void deleteTodo(InternshipTodo target);

    /**
     * Deletes the given note.
     * The note must exist in the note list.
     */
    void deleteNote(Note target);

    /**
     * Clears todo list.
     */
    void clearTodo(ReadOnlyAddressBook internEase);

    /**
     * Clears note list.
     */
    void clearNote(ReadOnlyAddressBook internEase);

    /**
     * Adds the given application.
     * {@code InternshipApplication} must not already exist in the tracker.
     */
    void addApplication(InternshipApplication application);

    /**
     * Adds the given todo.
     * {@code todo} must not already exist in the tracker.
     */
    void addTodo(InternshipTodo todo);

    /**
     * Adds the given note.
     * {@code note} must not already exist in the tracker.
     */
    void addNote(Note note);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);
    /**
     * Replaces the given person {@code target} with {@code editedApplication}.
     * {@code target} must exist in the address book.
     * The application identity of {@code editedApplication} must not be the
     * same as another existing application in the address book.
     */
    void setApplication(InternshipApplication target, InternshipApplication editedApplication);
    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given todo {@code target} with {@code editedTodo}.
     * {@code target} must exist in the tracker.
     * The identity of {@code editedTodo} must not be the same as another existing todo in the tracker.
     */
    void setTodo(InternshipTodo target, InternshipTodo editedTodo);

    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in the tracker.
     * The identity of {@code editedNote} must not be the same as another existing note in the tracker.
     */
    void setNote(Note target, Note editedNote);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<InternshipApplication> getFilteredInternshipList();

    /** Returns an unmodifiable view of the filtered todo list */
    ObservableList<InternshipTodo> getFilteredTodoList();

    /** Returns an unmodifiable view of the filtered note list */
    ObservableList<Note> getFilteredNoteList();

    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<InternshipApplication> predicate);

    /**
     * Updates the filter of the filtered todo list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTodoList(Predicate<InternshipTodo> predicate);

    /**
     * Updates the filter of the filtered note list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredNoteList(Predicate<Note> predicate);

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
