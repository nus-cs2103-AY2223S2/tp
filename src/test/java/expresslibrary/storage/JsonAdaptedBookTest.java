package expresslibrary.storage;

import static expresslibrary.storage.JsonAdaptedBook.MISSING_FIELD_MESSAGE_FORMAT;
import static expresslibrary.testutil.Assert.assertThrows;
import static expresslibrary.testutil.TypicalBooks.BELOVED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.commons.util.DateUtil;
import expresslibrary.model.book.Author;
import expresslibrary.model.book.Book;
import expresslibrary.model.book.Isbn;
import expresslibrary.model.book.Title;

public class JsonAdaptedBookTest {
    private static final String INVALID_TITLE = "@11";
    private static final String INVALID_AUTHOR = "R@chel";
    private static final String INVALID_ISBN = "123908213012312123";
    private static final String VALID_TITLE = BELOVED.getTitle().title;
    private static final String VALID_AUTHOR = BELOVED.getAuthor().name;
    private static final String VALID_ISBN = BELOVED.getIsbn().isbn;
    private static final String TODAY = DateUtil.formatDate(LocalDate.now());
    private static final String FORTNIGHT = DateUtil.formatDate(LocalDate.now().plusDays(14));
    private static final JsonAdaptedBook VALID_BOOK =
            new JsonAdaptedBook("A Game of Thrones", "George RR Martin", "9780553103540",
                    TODAY, FORTNIGHT, true);

    @Test
    public void toModelType_validBookDetails_returnsBook() throws Exception {
        Book book = VALID_BOOK.toModelType();
        assertEquals(new Title("A Game of Thrones").title, book.getTitle().title);
        assertEquals(new Author("George RR Martin").name, book.getAuthor().name);
        assertEquals(new Isbn("9780553103540").isbn, book.getIsbn().isbn);
        assertEquals(TODAY, DateUtil.formatDate(book.getBorrowDate()));
        assertEquals(FORTNIGHT, DateUtil.formatDate(book.getDueDate()));
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(INVALID_TITLE, VALID_AUTHOR, VALID_ISBN, "", "", false);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(null, VALID_AUTHOR, VALID_ISBN, "", "", false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidAuthor_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_TITLE, INVALID_AUTHOR, VALID_ISBN, "", "", false);
        String expectedMessage = Author.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullAuthor_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_TITLE, null, VALID_ISBN, "", "", false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidIsbn_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, INVALID_ISBN, "", "", false);
        String expectedMessage = Isbn.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullIsbn_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, null, "", "", false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Isbn.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullBorrowDate_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, VALID_ISBN, null, "", false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "borrowDate");
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidBorrowDate_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, VALID_ISBN, "01/14/2023", "", true);
        String expectedMessage = "Date format is invalid";
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullDueDate_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, VALID_ISBN, "", null, true);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "dueDate");
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidDueDate_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_TITLE, VALID_AUTHOR, VALID_ISBN, "", "01/14/2023", true);
        String expectedMessage = "Date format is invalid";
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }
}
