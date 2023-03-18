package expresslibrary.model.book;

import static expresslibrary.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;

import expresslibrary.model.person.Person;

/**
 * Represents a book object.
 */
public class Book {
    private final Title title;
    private final Author author;
    private final Isbn isbn;
    private Person borrower = null;
    private LocalDate borrowDate = null;
    private LocalDate dueDate = null;

    /**
     * Constructor for book object if book is not borrowed.
     *
     * @param title  Title of book
     * @param author Author of book
     * @param isbn   ISBN of book
     */
    public Book(Title title, Author author, Isbn isbn) {
        requireAllNonNull(isbn, author, title);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Constructor for book object if book is borrowed.
     *
     * @param title        Title of book
     * @param author       Author of book
     * @param isbn         ISBN of book
     * @param borrowDate   Date book was borrowed
     * @param dueDate      Date book is due
     */
    public Book(Title title, Author author, Isbn isbn, LocalDate borrowDate, LocalDate dueDate) {
        requireAllNonNull(isbn, author, title, borrowDate, dueDate);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public Person getBorrower() {
        return borrower;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Returns true if book is borrowed.
     */
    public Boolean getIsBorrowed() {
        return this.borrower != null;
    }

    /**
     * Returns true if both Books have the same name.
     * This defines a weaker notion of equality between two Books.
     */
    public boolean isSameBook(Book otherBook) {
        if (otherBook == this) {
            return true;
        }

        return otherBook != null
                && otherBook.getIsbn().equals(getIsbn());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Book)) {
            return false;
        }

        Book otherBook = (Book) other;
        return otherBook.getTitle().equals(getTitle())
                && otherBook.getAuthor().equals(getAuthor())
                && otherBook.getIsbn().equals(getIsbn())
                && otherBook.getBorrower().equals(getBorrower())
                && otherBook.getBorrowDate().equals(getBorrowDate())
                && otherBook.getDueDate().equals(getDueDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, author, isbn, borrower, borrowDate, dueDate);
    }

    @Override
    public String toString() {
        return getTitle() + " | " + getAuthor();
    }
}
