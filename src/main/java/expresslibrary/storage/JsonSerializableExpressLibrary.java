package expresslibrary.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import expresslibrary.commons.exceptions.IllegalValueException;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;

/**
 * An Immutable ExpressLibrary that is serializable to JSON format.
 */
@JsonRootName(value = "expresslibrary")
class JsonSerializableExpressLibrary {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_BOOK = "Books list contains duplicate book(s).";
    public static final String MESSAGE_BORROWED_BOOK_NOT_FOUND = "Borrowed book was not found in book list.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedBook> books = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableExpressLibrary} with the given persons.
     */
    @JsonCreator
    public JsonSerializableExpressLibrary(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("books") List<JsonAdaptedBook> books) {
        this.persons.addAll(persons);
        this.books.addAll(books);
    }

    /**
     * Converts a given {@code ReadOnlyExpressLibrary} into this class for Jackson
     * use.
     *
     * @param source future changes to this will not affect the created
     *               {@code JsonSerializableExpressLibrary}.
     */
    public JsonSerializableExpressLibrary(ReadOnlyExpressLibrary source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        books.addAll(source.getBookList().stream()
                .map(JsonAdaptedBook::new).collect(Collectors.toList()));
    }

    /**
     * Converts this express library into the model's {@code ExpressLibrary} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ExpressLibrary toModelType() throws IllegalValueException {
        ExpressLibrary expressLibrary = new ExpressLibrary();

        for (JsonAdaptedBook jsonAdaptedBook : books) {
            Book book = jsonAdaptedBook.toModelType();
            if (expressLibrary.hasBook(book)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOK);
            }
            expressLibrary.addBook(book);
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (expressLibrary.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            expressLibrary.addPerson(person);
            if (person.borrowedAtLeastOneBook()) {
                Set<Book> books = person.getBooks();
                for (Book borrowedBook : books) {
                    if (borrowedBook == null) {
                        throw new IllegalValueException(JsonAdaptedBook.INVALID_FORMAT_MESSAGE_FORMAT);
                    }
                    if (!expressLibrary.hasBook(borrowedBook)) {
                        throw new IllegalValueException(MESSAGE_BORROWED_BOOK_NOT_FOUND);
                    }
                    Book origBook = expressLibrary.getBook(borrowedBook);
                    LocalDate borrowDate = borrowedBook.getBorrowDate();
                    LocalDate dueDate = borrowedBook.getDueDate();
                    if (borrowDate == null || dueDate == null) {
                        throw new IllegalValueException(JsonAdaptedBook.INVALID_FORMAT_MESSAGE_FORMAT);
                    }
                    borrowedBook.loanBookTo(person, borrowDate, dueDate);
                    expressLibrary.setBook(origBook, borrowedBook);
                }
            }
        }
        return expressLibrary;
    }

}
