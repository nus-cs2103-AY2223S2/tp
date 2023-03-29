package expresslibrary.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import expresslibrary.commons.core.GuiSettings;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Book> PREDICATE_SHOW_ALL_BOOKS = unused -> true;

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
     * Returns the user prefs' express library file path.
     */
    Path getExpressLibraryFilePath();

    /**
     * Sets the user prefs' express library file path.
     */
    void setExpressLibraryFilePath(Path expressLibraryFilePath);

    /**
     * Replaces express library data with the data in {@code expressLibrary}.
     */
    void setExpressLibrary(ReadOnlyExpressLibrary expressLibrary);

    /** Returns the ExpressLibrary */
    ReadOnlyExpressLibrary getExpressLibrary();

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the express library.
     */
    boolean hasPerson(Person person);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the express library.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the express library.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the express library.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Deletes the given person.
     * The person must exist in the express library.
     */
    void deletePerson(Person target);

    /**
     * Returns {@code person} from this {@code ExpressLibrary}.
     * {@code person} must exist in the express library.
     */
    Person getPerson(Person person);

    /**
     * Returns true if a book with the same identity as {@code book} exists in
     * the express library.
     */
    boolean hasBook(Book book);

    /**
     * Adds the given book.
     * {@code person} must not already exist in the express library.
     */
    void addBook(Book book);

    /**
     * Replaces the given book {@code target} with {@code editedBook}.
     * {@code target} must exist in the express library.
     * The book identity of {@code editedBook} must not be the same as another
     * existing book in the express library.
     */
    void setBook(Book target, Book editedBook);

    /**
     * Deletes the given book.
     * The book must exist in the express library.
     */
    void deleteBook(Book target);

    /**
     * Returns {@code book} from this {@code ExpressLibrary}.
     * {@code book} must exist in the express library.
     */
    Book getBook(Book book);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Returns an unmodifiable view of the filtered book list */
    ObservableList<Book> getFilteredBookList();

    /**
     * Updates the filter of the filtered book list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookList(Predicate<Book> predicate);
}
