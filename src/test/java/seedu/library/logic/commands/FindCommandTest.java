package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.commons.core.Messages.MESSAGE_BOOKMARKS_LISTED_OVERVIEW;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.library.testutil.TypicalBookmarks.CARL;
import static seedu.library.testutil.TypicalBookmarks.ELLE;
import static seedu.library.testutil.TypicalBookmarks.FIONA;
import static seedu.library.testutil.TypicalBookmarks.getTypicalLibrary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.Tags;
import seedu.library.model.UserPrefs;
import seedu.library.model.bookmark.BookmarkContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs(), new Tags());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs(), new Tags());

    @Test
    public void equals() {
        BookmarkContainsKeywordsPredicate firstPredicate =
                new BookmarkContainsKeywordsPredicate(
                        Collections.singletonList("first"), null, null, null);
        BookmarkContainsKeywordsPredicate secondPredicate =
                new BookmarkContainsKeywordsPredicate(
                        Collections.singletonList("second"), null, null, null);

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different bookmark -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneTitleKeywordsOneAuthorKeywords_noBookmarkFound() {
        String expectedMessage = String.format(MESSAGE_BOOKMARKS_LISTED_OVERVIEW, 0);
        BookmarkContainsKeywordsPredicate predicate =
                preparePredicate("Alice", null, null, "wall street");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookmarkList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookmarkList());
    }

    @Test
    public void execute_multipleKeywords_noBookmarksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKMARKS_LISTED_OVERVIEW, 0);
        BookmarkContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz", null, null, null);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookmarkList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookmarkList());
    }

    @Test
    public void execute_oneGenreKeywords_oneBookmarksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKMARKS_LISTED_OVERVIEW, 1);
        BookmarkContainsKeywordsPredicate predicate = preparePredicate(null, "Comedy", null, null);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookmarkList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredBookmarkList());
    }

    @Test
    public void execute_oneTitleKeywords_oneBookmarksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKMARKS_LISTED_OVERVIEW, 1);
        BookmarkContainsKeywordsPredicate predicate = preparePredicate("Fiona", null, null, null);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookmarkList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredBookmarkList());
    }

    @Test
    public void execute_oneAuthorKeywords_oneBookmarksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKMARKS_LISTED_OVERVIEW, 1);
        BookmarkContainsKeywordsPredicate predicate = preparePredicate(null, null, null, "michegan ave");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookmarkList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredBookmarkList());
    }

    /**
     * Parses {@code userInput} into a {@code TitleContainsKeywordsPredicate}.
     */
    private BookmarkContainsKeywordsPredicate preparePredicate(String titleInput, String genreInput,
                                                               String tagInput, String authorInput) {
        List<String> titleKeywords = null;
        List<String> genreKeywords = null;
        List<String> tagKeywords = null;
        List<String> authorKeywords = null;

        if (titleInput != null) {
            titleKeywords = Arrays.asList(titleInput.split("\\s+"));
        }
        if (genreInput != null) {
            genreKeywords = Arrays.asList(genreInput.split("\\s+"));
        }
        if (tagInput != null) {
            tagKeywords = Arrays.asList(tagInput.split("\\s+"));
        }
        if (authorInput != null) {
            authorKeywords = Arrays.asList(authorInput.split("\\s+"));
        }
        return new BookmarkContainsKeywordsPredicate(titleKeywords, genreKeywords, tagKeywords, authorKeywords);
    }
}
