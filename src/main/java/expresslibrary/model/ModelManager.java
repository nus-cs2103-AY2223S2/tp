package expresslibrary.model;

import static expresslibrary.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import expresslibrary.commons.core.GuiSettings;
import expresslibrary.commons.core.LogsCenter;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the expressLibrary data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ExpressLibrary expressLibrary;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Book> filteredBooks;

    /**
     * Initializes a ModelManager with the given expressLibrary and userPrefs.
     */
    public ModelManager(ReadOnlyExpressLibrary expressLibrary, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(expressLibrary, userPrefs);

        logger.fine("Initializing with express library: " + expressLibrary + " and user prefs " + userPrefs);

        this.expressLibrary = new ExpressLibrary(expressLibrary);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.expressLibrary.getPersonList());
        filteredBooks = new FilteredList<>(this.expressLibrary.getBookList());
    }

    public ModelManager() {
        this(new ExpressLibrary(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

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
    public Path getExpressLibraryFilePath() {
        return userPrefs.getExpressLibraryFilePath();
    }

    @Override
    public void setExpressLibraryFilePath(Path expressLibraryFilePath) {
        requireNonNull(expressLibraryFilePath);
        userPrefs.setExpressLibraryFilePath(expressLibraryFilePath);
    }

    // =========== ExpressLibrary
    // ================================================================================

    @Override
    public void setExpressLibrary(ReadOnlyExpressLibrary expressLibrary) {
        this.expressLibrary.resetData(expressLibrary);
    }

    @Override
    public ReadOnlyExpressLibrary getExpressLibrary() {
        return expressLibrary;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return expressLibrary.hasPerson(person);
    }

    @Override
    public void addPerson(Person person) {
        expressLibrary.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        expressLibrary.setPerson(target, editedPerson);
    }

    @Override
    public void deletePerson(Person target) {
        expressLibrary.deletePerson(target);
    }

    @Override
    public Person getPerson(Person person) {
        return expressLibrary.getPerson(person);
    }

    @Override
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return expressLibrary.hasBook(book);
    }

    @Override
    public void addBook(Book book) {
        expressLibrary.addBook(book);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
    }

    @Override
    public void setBook(Book target, Book editedBook) {
        requireNonNull(editedBook);

        expressLibrary.setBook(target, editedBook);
    }

    @Override
    public void deleteBook(Book key) {
        expressLibrary.deleteBook(key);
    }

    @Override
    public Book getBook(Book book) {
        return expressLibrary.getBook(book);
    }

    // =========== Filtered Person List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of
     * {@code versionedExpressLibrary}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    // =========== Filtered Book List Accessors
    // =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Book} backed by the
     * internal list of {@code versionedExpressLibrary}
     */
    @Override
    public ObservableList<Book> getFilteredBookList() {
        return filteredBooks;
    }

    @Override
    public void updateFilteredBookList(Predicate<Book> predicate) {
        requireNonNull(predicate);
        filteredBooks.setPredicate(predicate);
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
        return expressLibrary.equals(other.expressLibrary)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredBooks.equals(other.filteredBooks);
    }
}
