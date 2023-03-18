package expresslibrary.model;

import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a express library
 */
public interface ReadOnlyExpressLibrary {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the books list.
     * This list will not contain any duplicate books.
     */
    ObservableList<Book> getBookList();
}
