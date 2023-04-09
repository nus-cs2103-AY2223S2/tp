package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.application.InternshipApplication;
import seedu.address.model.task.InternshipTodo;
import seedu.address.model.task.Note;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<InternshipApplication> PREDICATE_SHOW_ALL_APPLICATIONS = unused -> true;

    /** {@code Predicate} that evaluate to true for all unarchived applications */
    Predicate<InternshipApplication> PREDICATE_SHOW_ONGOING_APPLICATIONS = internshipApplication ->
            !internshipApplication.isArchived();

    /** {@code Predicate} that evaluate to true for all archived internship applications */
    Predicate<InternshipApplication> PREDICATE_SHOW_ARCHIVED_APPLICATIONS = InternshipApplication::isArchived;

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
     * Returns the user prefs' todo list file path.
     */
    Path getTodoListFilePath();

    /**
     * Returns the user prefs' note list file path.
     */
    Path getNoteListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code internEase}.
     */
    void setInternEase(ReadOnlyAddressBook internEase);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the TodoList */
    ReadOnlyTodoList getTodoList();

    /** Returns the NoteList */
    ReadOnlyNote getNoteList();

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
    void clearTodo(ReadOnlyTodoList internEase);

    /**
     * Clears note list.
     */
    void clearNote(ReadOnlyNote internEase);

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
     * Adds the given applications.
     * {@code InternshipApplications} must not already exist in the tracker.
     */
    void addApplications(List<InternshipApplication> applications);

    /**
     * Replaces the given internshipApplication {@code target} with {@code editedApplication}.
     * {@code target} must exist in the address book.
     * The application identity of {@code editedApplication} must not be the
     * same as another existing application in the address book.
     */
    void setApplication(InternshipApplication target, InternshipApplication editedApplication);

    /**
     * Replaces the given todo {@code target} with {@code editedTodo}.
     * {@code target} must exist in the tracker.
     * The identity of {@code editedTodo} must not be the same as another existing todo in the tracker.
     */
    void setTodo(InternshipTodo target,
                 InternshipTodo editedTodo);

    /** Returns the InternshipApplication with the most imminent interview */
    InternshipApplication getReminder();

    /** Replaces current reminder with another InternshipApplication with an interview date that is earlier */
    void updateReminder();

    /**
     * Replaces the given note {@code target} with {@code editedNote}.
     * {@code target} must exist in the tracker.
     * The identity of {@code editedNote} must not be the same as another existing note in the tracker.
     */
    void setNote(Note target, Note editedNote);

    /**
     * Returns an unmodifiable view of the sorted filtered internship list
     */
    ObservableList<InternshipApplication> getSortedFilteredInternshipList();

    /** Returns an unmodifiable view of the filtered todo list */
    ObservableList<InternshipTodo> getFilteredTodoList();

    /** Returns an unmodifiable view of the filtered note list */
    ObservableList<Note> getFilteredNoteList();

    /**
     * Updates the comparator of the sorted filtered internship list to sort by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedFilteredInternshipList(Comparator<InternshipApplication> comparator);

    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     *
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
     * Returns an unmodifiable view of the cached internship list.
     */
    List<InternshipApplication> getCachedInternshipList();


    /**
     * Gets and remove item from the cached internship list.
     */
    InternshipApplication getAndRemoveCachedApplication();

    /**
     * Add current deleted internship application to the end of the cached internship list.
     */
    void addInternshipToCache(InternshipApplication application);

    /**
     * Add all cleared internship applications to the end of the cached internship list.
     */
    void addAllInternshipToCache(List<InternshipApplication> application);

    /**
     * Empties the internship cache list.
     */
    void setEmptyInternshipCacheList();
}
