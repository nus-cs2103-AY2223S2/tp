package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.Person;
import seedu.address.model.todo.InternshipTodo;
import seedu.address.model.todo.Note;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<InternshipApplication> filteredInternships;
    private final FilteredList<InternshipTodo> filteredTodo;
    private final FilteredList<Note> filteredNote;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredInternships = new FilteredList<>(this.addressBook.getInternshipList());
        filteredTodo = new FilteredList<>(this.addressBook.getTodoList());
        filteredNote = new FilteredList<>(this.addressBook.getNoteList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setInternEase(ReadOnlyAddressBook internEase) {
        this.addressBook.resetData(internEase);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasApplication(InternshipApplication application) {
        requireNonNull(application);
        return addressBook.hasApplication(application);
    }

    @Override
    public boolean hasTodo(InternshipTodo todo) {
        requireNonNull(todo);
        return addressBook.hasTodo(todo);
    }

    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return addressBook.hasNote(note);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteInternship(InternshipApplication application) {
        addressBook.removeApplication(application);
    }

    @Override
    public void deleteTodo(InternshipTodo target) {
        addressBook.removeTodo(target);
    }

    @Override
    public void deleteNote(Note target) {
        addressBook.removeNote(target);
    }

    @Override
    public void clearTodo(ReadOnlyAddressBook internEase) {
        addressBook.clearTodo(internEase);
    }

    @Override
    public void clearNote(ReadOnlyAddressBook internEase) {
        addressBook.clearNote(internEase);
    }

    @Override
    public void addApplication(InternshipApplication application) {
        addressBook.addApplication(application);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_APPLICATIONS);
    }

    @Override
    public void addTodo(InternshipTodo todo) {
        addressBook.addTodo(todo);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_APPLICATIONS);
    }

    @Override
    public void addNote(Note note) {
        addressBook.addNote(note);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_APPLICATIONS);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setApplication(InternshipApplication target, InternshipApplication editedApplication) {
        requireAllNonNull(target, editedApplication);

        addressBook.setApplication(target, editedApplication);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setTodo(InternshipTodo target, InternshipTodo editedTodo) {
        requireAllNonNull(target, editedTodo);

        addressBook.setTodo(target, editedTodo);
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        addressBook.setNote(target, editedNote);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<InternshipApplication> getFilteredInternshipList() {
        return filteredInternships;
    }

    @Override
    public void updateFilteredInternshipList(Predicate<InternshipApplication> predicate) {
        requireNonNull(predicate);
        filteredInternships.setPredicate(predicate);
    }

    @Override
    public ObservableList<InternshipTodo> getFilteredTodoList() {
        return filteredTodo;
    }

    @Override
    public void updateFilteredTodoList(Predicate<InternshipTodo> predicate) {
        requireNonNull(predicate);
        filteredTodo.setPredicate(predicate);
    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return filteredNote;
    }

    @Override
    public void updateFilteredNoteList(Predicate<Note> predicate) {
        requireNonNull(predicate);
        filteredNote.setPredicate(predicate);
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
