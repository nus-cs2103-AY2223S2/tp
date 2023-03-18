package expresslibrary.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import expresslibrary.model.book.Book;
import expresslibrary.model.book.UniqueBookList;
import expresslibrary.model.person.Person;
import expresslibrary.model.person.UniquePersonList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the express library level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ExpressLibrary implements ReadOnlyExpressLibrary {

    private final UniquePersonList persons;
    private final UniqueBookList books;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        books = new UniqueBookList();
    }

    public ExpressLibrary() {}

    /**
     * Creates an ExpressLibrary using the Persons and Books in the {@code toBeCopied}
     */
    public ExpressLibrary(ReadOnlyExpressLibrary toBeCopied) {
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
     * Replaces the contents of the book list with {@code books}.
     * {@code books} must not contain duplicate books.
     */
    private void setBooks(List<Book> books) {
        this.books.setBooks(books);
    }

    /**
     * Resets the existing data of this {@code ExpressLibrary} with {@code newData}.
     */
    public void resetData(ReadOnlyExpressLibrary newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setBooks(newData.getBookList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the express library.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the express library.
     * The person must not already exist in the express library.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the express library.
     * The person identity of {@code editedPerson} must not be the same as another existing person
     * in the express library.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code ExpressLibrary}.
     * {@code key} must exist in the express library.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// book-level operations

    /**
     * Returns true if a book with the same identity as {@code book} exists in the express library.
     */
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return books.contains(book);
    }

    /**
     * Adds a book to the express library.
     * The book must not already exist in the express library.
     */
    public void addBook(Book b) {
        books.add(b);
    }

    /**
     * Replaces the given book {@code target} in the list with {@code editedBook}.
     * {@code target} must exist in the express library.
     * The book identity of {@code editedBook} must not be the same as another existing book in the express library.
     */
    public void setBook(Book target, Book editedBook) {
        requireNonNull(editedBook);

        books.setBook(target, editedBook);
    }

    /**
     * Removes {@code key} from this {@code ExpressLibrary}.
     * {@code key} must exist in the express library.
     */
    public void removeBook(Book key) {
        books.remove(key);
    }
    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons | "
            + books.asUnmodifiableObservableList().size() + " books";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }
    @Override
    public ObservableList<Book> getBookList() {
        return books.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpressLibrary // instanceof handles nulls
                && persons.equals(((ExpressLibrary) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
