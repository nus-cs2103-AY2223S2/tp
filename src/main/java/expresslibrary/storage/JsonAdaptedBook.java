package expresslibrary.storage;

import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.commons.util.DateUtil;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;
import expresslibrary.model.person.Person;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedBook {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Book's %s field is missing!";

    private final String author;
    private final String isbn;
    private final String title;
    private final String borrowDate;
    private final String dueDate;
    private final Boolean isBorrowed;

    /**
     * Constructs a {@code JsonAdaptedBook} with the given book details.
     */
    @JsonCreator
    public JsonAdaptedBook(@JsonProperty("author") String author, @JsonProperty("isbn") String isbn,
            @JsonProperty("title") String title, @JsonProperty("borrowDate") String borrowDate,
            @JsonProperty("dueDate") String dueDate, @JsonProperty("isBorrowed") Boolean isBorrowed) {
        this.author = author;
        this.isbn = isbn;
        this.title = title;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isBorrowed = isBorrowed;
    }

    /**
     * Converts a given {@code Book} into this class for Jackson use.
     */
    public JsonAdaptedBook(Book source) {
        author = source.getAuthor().name;
        isbn = source.getIsbn().isbn;
        title = source.getTitle().title;
        isBorrowed = source.getIsBorrowed();

        if (!isBorrowed) {
            borrowDate = "";
            dueDate = "";
            return;
        }

        borrowDate = DateUtil.formatDate(source.getBorrowDate());
        dueDate = DateUtil.formatDate(source.getDueDate());
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's
     * {@code Book} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted book.
     */
    public Book toModelType() throws IllegalValueException {

        if (author == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName()));
        }
        if (!Author.isValidAuthor(author)) {
            throw new IllegalValueException(Author.MESSAGE_CONSTRAINTS);
        }
        final Author modelAuthor = new Author(author);
        if (isbn == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Isbn.class.getSimpleName()));
        }
        if (!Isbn.isValidIsbn(isbn)) {
            throw new IllegalValueException(Isbn.MESSAGE_CONSTRAINTS);
        }
        final Isbn modelIsbn = new Isbn(isbn);
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (isBorrowed == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isBorrowed"));
        }

        if (borrowDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "borrowDate"));
        }

        if (dueDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "dueDate"));
        }

        if (!isBorrowed) {
            return new Book(modelTitle, modelAuthor, modelIsbn);
        }
        try {
            Date modelBorrowDate = DateUtil.parseDate(borrowDate);
            Date modelDueDate = DateUtil.parseDate(dueDate);
            return new Book(modelTitle, modelAuthor, modelIsbn, modelBorrowDate, modelDueDate);
        } catch (ParseException e) {
            throw new IllegalValueException("Date format is invalid");
        }
    }

}
