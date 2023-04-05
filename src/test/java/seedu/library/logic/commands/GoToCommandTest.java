package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.library.logic.commands.CommandTestUtil.showBookmarkAtIndex;
import static seedu.library.testutil.TypicalBookmarks.getTypicalLibrary;
import static seedu.library.testutil.TypicalIndexes.INDEX_FIRST_BOOKMARK;
import static seedu.library.testutil.TypicalIndexes.INDEX_SECOND_BOOKMARK;

import org.junit.jupiter.api.Test;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.Tags;
import seedu.library.model.UserPrefs;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code GoToCommand}.
 */
public class GoToCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs(), new Tags());

    //    @Test
    //    public void execute_validIndexUnfilteredList_success() {
    //        Bookmark bookmarkToView = model.getFilteredBookmarkList().get(INDEX_FIRST_BOOKMARK.getZeroBased());
    //        GoToCommand goToCommand = new GoToCommand(INDEX_FIRST_BOOKMARK);
    //        String expectedMessage = String.format(GoToCommand.MESSAGE_GOTO_BOOKMARK_SUCCESS, bookmarkToView);
    //        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs(), new Tags());
    //        expectedModel.viewBookmark(bookmarkToView);
    //
    //        assertCommandSuccess(goToCommand, model, expectedMessage, expectedModel);
    //    }
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookmarkList().size() + 1);
        GoToCommand goToCommand = new GoToCommand(outOfBoundIndex);

        assertCommandFailure(goToCommand, model, Messages.MESSAGE_INVALID_BOOKMARK_DISPLAYED_INDEX);
    }

    //    @Test
    //    public void execute_validIndexFilteredList_success() {
    //        showBookmarkAtIndex(model, INDEX_FIRST_BOOKMARK);
    //        Bookmark bookmarkToView = model.getFilteredBookmarkList().get(INDEX_FIRST_BOOKMARK.getZeroBased());
    //        GoToCommand goToCommand = new GoToCommand(INDEX_FIRST_BOOKMARK);
    //        String expectedMessage = String.format(GoToCommand.MESSAGE_GOTO_BOOKMARK_SUCCESS, bookmarkToView);
    //        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs(), new Tags());
    //        showBookmarkAtIndex(expectedModel, INDEX_FIRST_BOOKMARK);
    //        expectedModel.viewBookmark(bookmarkToView);
    //        assertCommandSuccess(goToCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBookmarkAtIndex(model, INDEX_FIRST_BOOKMARK);

        Index outOfBoundIndex = INDEX_SECOND_BOOKMARK;
        // ensures that outOfBoundIndex is still in bounds of bookmark list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getBookmarkList().size());

        GoToCommand goToCommand = new GoToCommand(outOfBoundIndex);

        assertCommandFailure(goToCommand, model, Messages.MESSAGE_INVALID_BOOKMARK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GoToCommand goToFirstCommand = new GoToCommand(INDEX_FIRST_BOOKMARK);
        GoToCommand goToSecondCommand = new GoToCommand(INDEX_SECOND_BOOKMARK);

        // same object -> returns true
        assertTrue(goToFirstCommand.equals(goToFirstCommand));

        // same values -> returns true
        GoToCommand goToFirstCommandCopy = new GoToCommand(INDEX_FIRST_BOOKMARK);
        assertTrue(goToFirstCommand.equals(goToFirstCommandCopy));

        // different types -> returns false
        assertFalse(goToFirstCommand.equals(1));

        // null -> returns false
        assertFalse(goToFirstCommand.equals(null));

        // different goTo -> returns false
        assertFalse(goToFirstCommand.equals(goToSecondCommand));
    }

}
