package expresslibrary.model.book;

import static expresslibrary.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import expresslibrary.model.book.exceptions.BookNotFoundException;
import expresslibrary.model.book.exceptions.DuplicateBookException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of books that enforces uniqueness between its elements and does not
 * allow nulls.
 * A book is considered unique by comparing using {@code Book#isSameBook(Book)}.
 * As such, adding and updating of
 * books uses Book#isSameBook(Book) for equality so as to ensure that the book
 * being added or updated is
 * unique in terms of identity in the UniqueBookList. However, the removal of a
 * book uses Book#equals(Object) so
 * as to ensure that the book with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Book#isSameBook(Book)
 */
public class UniqueBookList implements Iterable<Book> {

    private final ObservableList<Book> internalList = FXCollections.observableArrayList();
    private final ObservableList<Book> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent book as the given argument.
     */
    public boolean contains(Book toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBook);
    }

    /**
     * Returns the book that matches the given argument, or null if no such book
     * exists.
     */
    public Book get(Book book) {
        requireNonNull(book);
        for (Book b : internalList) {
            if (b.isSameBook(book)) {
                return b;
            }
        }
        throw new BookNotFoundException();
    }

    /**
     * Adds a book to the list.
     * The Book must not already exist in the list.
     */
    public void add(Book toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBookException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the book {@code target} in the list with {@code editedBook}.
     * {@code target} must exist in the list.
     * The book identity of {@code editedBook} must not be the same as another
     * existing book in the list.
     */
    public void setBook(Book target, Book editedBook) {
        requireAllNonNull(target, editedBook);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookNotFoundException();
        }

        if (!target.isSameBook(editedBook) && contains(editedBook)) {
            throw new DuplicateBookException();
        }

        internalList.set(index, editedBook);
    }

    /**
     * Removes the equivalent book from the list.
     * The book must exist in the list.
     */
    public void remove(Book toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BookNotFoundException();
        }
    }

    public void setBooks(UniqueBookList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code books}.
     * {@code books} must not contain duplicate books.
     */
    public void setBooks(List<Book> books) {
        requireAllNonNull(books);
        if (!booksAreUnique(books)) {
            throw new DuplicateBookException();
        }

        internalList.setAll(books);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Book> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Book> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookList // instanceof handles nulls
                        && internalList.equals(((UniqueBookList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Books} contains only unique books.
     */
    private boolean booksAreUnique(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(i).isSameBook(books.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
