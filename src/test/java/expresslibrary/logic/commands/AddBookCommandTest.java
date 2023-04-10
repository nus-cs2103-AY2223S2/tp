package expresslibrary.logic.commands;

import static expresslibrary.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.core.GuiSettings;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.Model;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.ReadOnlyUserPrefs;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import expresslibrary.testutil.BookBuilder;
import javafx.collections.ObservableList;

public class AddBookCommandTest {

    @Test
    public void constructor_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBookCommand(null));
    }

    @Test
    public void execute_bookAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBookAdded modelStub = new ModelStubAcceptingBookAdded();
        Book validBook = new BookBuilder().build();

        CommandResult commandResult = new AddBookCommand(validBook).execute(modelStub);

        assertEquals(String.format(AddBookCommand.MESSAGE_SUCCESS, validBook), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBook), modelStub.booksAdded);
    }

    @Test
    public void execute_duplicateBook_throwsCommandException() {
        Book validBook = new BookBuilder().build();
        AddBookCommand addBookCommand = new AddBookCommand(validBook);
        ModelStub modelStub = new ModelStubWithBook(validBook);

        assertThrows(CommandException.class, AddBookCommand.MESSAGE_DUPLICATE_BOOK, (
        ) -> addBookCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Book harry = new BookBuilder().withTitle("Harry Potter").build();
        Book beloved = new BookBuilder().withTitle("Beloved").build();
        AddBookCommand addHarryCommand = new AddBookCommand(harry);
        AddBookCommand addBelovedCommand = new AddBookCommand(beloved);

        // same object -> returns true
        assertTrue(addHarryCommand.equals(addHarryCommand));

        // same values -> returns true
        AddBookCommand addAliceCommandCopy = new AddBookCommand(harry);
        assertTrue(addHarryCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addHarryCommand.equals(1));

        // null -> returns false
        assertFalse(addHarryCommand.equals(null));

        // different title -> returns false
        assertFalse(addHarryCommand.equals(addBelovedCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getExpressLibraryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpressLibraryFilePath(Path expressLibraryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpressLibrary(ReadOnlyExpressLibrary newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyExpressLibrary getExpressLibrary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBook(Book book) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBook(Book book) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBook(Book target, Book editedBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBook(Book target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Book getBook(Book book) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Book> getFilteredBookList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBookList(Predicate<Book> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single book.
     */
    private class ModelStubWithBook extends ModelStub {
        private final Book book;

        ModelStubWithBook(Book book) {
            requireNonNull(book);
            this.book = book;
        }

        @Override
        public boolean hasBook(Book book) {
            requireNonNull(book);
            return this.book.isSameBook(book);
        }
    }

    /**
     * A Model stub that always accept the book being added.
     */
    private class ModelStubAcceptingBookAdded extends ModelStub {
        final ArrayList<Book> booksAdded = new ArrayList<>();

        @Override
        public boolean hasBook(Book book) {
            requireNonNull(book);
            return booksAdded.stream().anyMatch(book::isSameBook);
        }

        @Override
        public void addBook(Book book) {
            requireNonNull(book);
            booksAdded.add(book);
        }

        @Override
        public ReadOnlyExpressLibrary getExpressLibrary() {
            return new ExpressLibrary();
        }
    }

}

