package expresslibrary.logic.commands;

import static expresslibrary.logic.commands.CommandTestUtil.DESC_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_AUTHOR_ROWLING;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_ISBN_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.VALID_TITLE_HARRY;
import static expresslibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static expresslibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static expresslibrary.logic.commands.CommandTestUtil.showBookAtIndex;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;
import static expresslibrary.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.logic.commands.EditBookCommand.EditBookDescriptor;
import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;
import expresslibrary.model.book.Book;
import expresslibrary.testutil.BookBuilder;
import expresslibrary.testutil.EditBookDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditBookCommand.
 */
public class EditBookCommandTest {

    private Model model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Book editedBook = new BookBuilder().build();
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder(editedBook).build();
        EditBookCommand editBookCommand = new EditBookCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditBookCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new ExpressLibrary(model.getExpressLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        assertCommandSuccess(editBookCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBook = Index.fromOneBased(model.getFilteredBookList().size());
        Book lastBook = model.getFilteredBookList().get(indexLastBook.getZeroBased());

        BookBuilder bookInList = new BookBuilder(lastBook);
        Book editedBook = bookInList.withTitle(VALID_TITLE_HARRY).withAuthor(VALID_AUTHOR_ROWLING)
                .withIsbn(VALID_ISBN_HARRY).build();

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY)
                .withAuthor(VALID_AUTHOR_ROWLING).withIsbn(VALID_ISBN_HARRY).build();
        EditBookCommand editBookCommand = new EditBookCommand(indexLastBook, descriptor);

        String expectedMessage = String.format(EditBookCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new ExpressLibrary(model.getExpressLibrary()), new UserPrefs());
        expectedModel.setBook(lastBook, editedBook);

        assertCommandSuccess(editBookCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBookCommand editBookCommand = new EditBookCommand(INDEX_FIRST, new EditBookDescriptor());
        Book editedBook = model.getFilteredBookList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new ExpressLibrary(model.getExpressLibrary()), new UserPrefs());

        assertCommandSuccess(editBookCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBookAtIndex(model, INDEX_FIRST);

        Book bookInFilteredList = model.getFilteredBookList().get(INDEX_FIRST.getZeroBased());
        Book editedBook = new BookBuilder(bookInFilteredList).withTitle(VALID_TITLE_HARRY).build();
        EditBookCommand editBookCommand = new EditBookCommand(INDEX_FIRST,
                new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY).build());

        String expectedMessage = String.format(EditBookCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new ExpressLibrary(model.getExpressLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        assertCommandSuccess(editBookCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateBookUnfilteredList_failure() {
        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST.getZeroBased());
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder(firstBook).build();
        EditBookCommand editBookCommand = new EditBookCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editBookCommand, model, EditBookCommand.MESSAGE_DUPLICATE_BOOK);
    }

    @Test
    public void execute_duplicateBookFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST);

        // edit book in filtered list into a duplicate in express library
        Book bookInList = model.getExpressLibrary().getBookList().get(INDEX_SECOND.getZeroBased());
        EditBookCommand editBookCommand = new EditBookCommand(INDEX_FIRST,
                new EditBookDescriptorBuilder(bookInList).build());

        assertCommandFailure(editBookCommand, model, EditBookCommand.MESSAGE_DUPLICATE_BOOK);
    }

    @Test
    public void execute_invalidBookIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY).build();
        EditBookCommand editBookCommand = new EditBookCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBookCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of express library
     */
    @Test
    public void execute_invalidBookIndexFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of express library list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpressLibrary().getBookList().size());

        EditBookCommand editBookCommand = new EditBookCommand(outOfBoundIndex,
                new EditBookDescriptorBuilder().withTitle(VALID_TITLE_HARRY).build());

        assertCommandFailure(editBookCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBookCommand standardCommand = new EditBookCommand(INDEX_FIRST, DESC_HARRY);

        // same values -> returns true
        EditBookDescriptor copyDescriptor = new EditBookDescriptor(DESC_HARRY);
        EditBookCommand commandWithSameValues = new EditBookCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBookCommand(INDEX_SECOND, DESC_HARRY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBookCommand(INDEX_FIRST, DESC_HARRY)));
    }
}
