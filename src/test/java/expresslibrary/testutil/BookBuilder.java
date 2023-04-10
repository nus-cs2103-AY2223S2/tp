package expresslibrary.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import expresslibrary.commons.core.LogsCenter;
import expresslibrary.commons.util.DateUtil;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.model.person.Person;

/**
 * A utility class to help with building Book objects.
 */
public class BookBuilder {

    public static final String DEFAULT_TITLE = "The Adventures of Tom Sawyer";
    public static final String DEFAULT_AUTHOR = "Mark Twain";
    public static final String DEFAULT_ISBN = "9780141194936";
    private static final Logger logger = LogsCenter.getLogger(BookBuilder.class);

    private Title title;
    private Author author;
    private Isbn isbn;
    private Person borrower;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    /**
     * Creates a {@code BookBuilder} with the default details.
     */
    public BookBuilder() {
        title = new Title(DEFAULT_TITLE);
        author = new Author(DEFAULT_AUTHOR);
        isbn = new Isbn(DEFAULT_ISBN);
    }

    /**
     * Initializes the BookBuilder with the data of {@code bookToCopy}.
     */
    public BookBuilder(Book bookToCopy) {
        title = bookToCopy.getTitle();
        author = bookToCopy.getAuthor();
        isbn = bookToCopy.getIsbn();
        if (bookToCopy.getBorrower() != null) {
            borrower = bookToCopy.getBorrower();
            borrowDate = bookToCopy.getBorrowDate();
            dueDate = bookToCopy.getDueDate();
        }
    }

    /**
     * Sets the {@code title} of the {@code Book} that we are building.
     */
    public BookBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code author} of the {@code Book} that we are building.
     */
    public BookBuilder withAuthor(String author) {
        this.author = new Author(author);
        return this;
    }

    /**
     * Sets the {@code isbn} of the {@code Book} that we are building.
     */
    public BookBuilder withIsbn(String isbn) {
        this.isbn = new Isbn(isbn);
        return this;
    }

    /**
     * Sets the {@code borrower}, {@code borrowDate} and {@code returnDate} of the {@code Book} that we are building.
     */
    public BookBuilder withBorrower(Person borrower, String borrowDate, String returnDate) {
        try {
            this.borrowDate = DateUtil.parseDate(borrowDate);
            this.dueDate = DateUtil.parseDate(returnDate);
            this.borrower = borrower;
        } catch (DateTimeParseException e) {
            logger.info("Date format is invalid");
            e.printStackTrace();
        }
        return this;
    }


    /**
     * Builds the book object.
     */
    public Book build() {
        Book book = new Book(title, author, isbn);
        if (borrower != null) {
            borrower.borrowBook(book);
            book.loanBookTo(borrower, borrowDate, dueDate);
        }
        return book;
    }

}
