package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.library.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_PROGRESS;
import static seedu.library.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_URL;
import static seedu.library.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Library;
import seedu.library.model.Model;
import seedu.library.model.ReadOnlyTags;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.BookmarkContainsKeywordsPredicate;
import seedu.library.model.util.SampleDataUtil;
import seedu.library.testutil.EditBookmarkDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_AMY = "Attack on Titans";
    public static final String VALID_TITLE_BOB = "Blue Lock";
    public static final String VALID_PROGRESS_AMY = "1 32 7";
    public static final String VALID_PROGRESS_BOB = "2 20 12";
    public static final String VALID_GENRE_AMY = "Fantasy";
    public static final String VALID_GENRE_BOB = "Others";
    public static final String VALID_AUTHOR_AMY = "Hajime Isayama";
    public static final String VALID_AUTHOR_BOB = "Muneyuki Kaneshiro";
    public static final String VALID_TAG_HUSBAND = "MaleProtagonist";
    public static final String VALID_TAG_FRIEND = "FemaleProtagonist";
    public static final String VALID_TAG_PLANT = "plant";
    public static final String VALID_TAG_NOVEL = "novel";
    public static final ReadOnlyTags VALID_TAGS = SampleDataUtil.getSampleTagList();
    public static final String VALID_RATING_AMY = "3";
    public static final String VALID_RATING_BOB = "4";
    public static final String VALID_URL_AMY = "http://www.google.com";
    public static final String VALID_URL_BOB = "http://www.youtube.com";

    public static final String TITLE_DESC_AMY = " " + PREFIX_TITLE + VALID_TITLE_AMY;
    public static final String TITLE_DESC_BOB = " " + PREFIX_TITLE + VALID_TITLE_BOB;
    public static final String PROGRESS_DESC_AMY = " " + PREFIX_PROGRESS + VALID_PROGRESS_AMY;
    public static final String PROGRESS_DESC_BOB = " " + PREFIX_PROGRESS + VALID_PROGRESS_BOB;
    public static final String GENRE_DESC_AMY = " " + PREFIX_GENRE + VALID_GENRE_AMY;
    public static final String GENRE_DESC_BOB = " " + PREFIX_GENRE + VALID_GENRE_BOB;
    public static final String AUTHOR_DESC_AMY = " " + PREFIX_AUTHOR + VALID_AUTHOR_AMY;
    public static final String AUTHOR_DESC_BOB = " " + PREFIX_AUTHOR + VALID_AUTHOR_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TAG_DESC_PLANT = " " + PREFIX_TAG + VALID_TAG_PLANT;
    public static final String TAG_DESC_NOVEL = " " + PREFIX_TAG + VALID_TAG_NOVEL;
    public static final String RATING_DESC_AMY = " " + PREFIX_RATING + VALID_RATING_AMY;
    public static final String URL_DESC_AMY = " " + PREFIX_URL + VALID_URL_AMY;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE; // '&' not allowed in names
    public static final String INVALID_PROGRESS_DESC = " " + PREFIX_PROGRESS + "911*"; // '*' not allowed in progress
    public static final String INVALID_GENRE_DESC = " " + PREFIX_GENRE;
    public static final String INVALID_AUTHOR_DESC = " " + PREFIX_AUTHOR; // empty string not allowed for author
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_RATING_DESC = " " + PREFIX_RATING + "-1";
    public static final String INVALID_URL_DESC = " " + PREFIX_URL + "invalidurl";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditBookmarkDescriptor DESC_AMY;
    public static final EditCommand.EditBookmarkDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditBookmarkDescriptorBuilder().withTitle(VALID_TITLE_AMY)
                .withProgress(VALID_PROGRESS_AMY).withGenre(VALID_GENRE_AMY).withAuthor(VALID_AUTHOR_AMY)
                .withTags(VALID_TAG_FRIEND).withRating(VALID_RATING_AMY)
                .withUrl(VALID_URL_AMY).build();
        DESC_BOB = new EditBookmarkDescriptorBuilder().withTitle(VALID_TITLE_BOB)
                .withProgress(VALID_PROGRESS_BOB).withGenre(VALID_GENRE_BOB).withAuthor(VALID_AUTHOR_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withRating(VALID_RATING_BOB)
                .withUrl(VALID_URL_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the library, filtered bookmark list and selected bookmark in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Library expectedLibrary = new Library(actualModel.getLibrary());
        List<Bookmark> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBookmarkList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLibrary, actualModel.getLibrary());
        assertEquals(expectedFilteredList, actualModel.getFilteredBookmarkList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the bookmark at the given {@code targetIndex} in the
     * {@code model}'s library.
     */
    public static void showBookmarkAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBookmarkList().size());

        Bookmark bookmark = model.getFilteredBookmarkList().get(targetIndex.getZeroBased());
        final String[] splitName = bookmark.getTitle().value.split("\\s+");
        model.updateFilteredBookmarkList(new BookmarkContainsKeywordsPredicate(
                Arrays.asList(splitName[0]), null, null, null));

        assertEquals(1, model.getFilteredBookmarkList().size());
    }

}
