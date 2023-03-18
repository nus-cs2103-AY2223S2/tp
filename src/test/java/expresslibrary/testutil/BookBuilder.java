package expresslibrary.testutil;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

import expresslibrary.commons.core.LogsCenter;
import expresslibrary.commons.util.DateUtil;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;

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
    private Date borrowDate;
    private Date dueDate;

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
     * Sets the {@code borrowDate} and {@code returnDate} of the {@code Book} that we are building.
     */
    public BookBuilder withBorrowAndReturnDate(String borrowDate, String returnDate) {
        try {
            this.borrowDate = DateUtil.parseDate(borrowDate);
            this.dueDate = DateUtil.parseDate(returnDate);
        } catch (ParseException e) {
            logger.info("Date format is invalid");
            e.printStackTrace();
        }
        return this;
    }


    /**
     * Builds the book object.
     */
    public Book build() {
        if (borrowDate == null || dueDate == null) {
            return new Book(title, author, isbn);
        }
        return new Book(title, author, isbn, borrowDate, dueDate);
    }

}
