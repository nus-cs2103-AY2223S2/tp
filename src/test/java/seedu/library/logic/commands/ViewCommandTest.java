package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import seedu.library.model.bookmark.Bookmark;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {



    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs(), new Tags());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Bookmark bookmarkToView = model.getFilteredBookmarkList().get(INDEX_FIRST_BOOKMARK.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_BOOKMARK);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_VIEWED_SUCCESS, bookmarkToView);

        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs(), new Tags());
        expectedModel.viewBookmark(bookmarkToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookmarkList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_BOOKMARK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBookmarkAtIndex(model, INDEX_FIRST_BOOKMARK);

        Bookmark bookmarkToView = model.getFilteredBookmarkList().get(INDEX_FIRST_BOOKMARK.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_BOOKMARK);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_VIEWED_SUCCESS, bookmarkToView);


        ModelManager expectedModel = new ModelManager(model.getLibrary(), new UserPrefs(), new Tags());
        showBookmarkAtIndex(expectedModel, INDEX_FIRST_BOOKMARK);
        expectedModel.viewBookmark(bookmarkToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBookmarkAtIndex(model, INDEX_FIRST_BOOKMARK);

        Index outOfBoundIndex = INDEX_SECOND_BOOKMARK;
        // ensures that outOfBoundIndex is still in bounds of bookmark list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getBookmarkList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_BOOKMARK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_BOOKMARK);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_BOOKMARK);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_BOOKMARK);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different view -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}
