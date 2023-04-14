package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.commands.CommandTestUtil.AUTHOR_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.AUTHOR_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.GENRE_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.GENRE_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_AUTHOR_DESC;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_GENRE_DESC;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_PROGRESS_DESC;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.library.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.library.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.library.logic.commands.CommandTestUtil.PROGRESS_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.PROGRESS_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.library.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.library.logic.commands.CommandTestUtil.TITLE_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.TITLE_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_AUTHOR_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_GENRE_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PROGRESS_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.library.testutil.TypicalBookmarks.AMY;
import static seedu.library.testutil.TypicalBookmarks.BOB;

import org.junit.jupiter.api.Test;

import seedu.library.logic.commands.AddCommand;
import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Title;
import seedu.library.model.tag.Tag;
import seedu.library.testutil.BookmarkBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Bookmark expectedBookmark = new BookmarkBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_BOB + PROGRESS_DESC_BOB + GENRE_DESC_BOB
                + AUTHOR_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedBookmark));

        // multiple names - last name accepted
        assertParseSuccess(parser, TITLE_DESC_AMY + TITLE_DESC_BOB + PROGRESS_DESC_BOB + GENRE_DESC_BOB
                + AUTHOR_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedBookmark));

        // multiple progresses - last progress accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + PROGRESS_DESC_AMY + PROGRESS_DESC_BOB + GENRE_DESC_BOB
                + AUTHOR_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedBookmark));

        // multiple emails - last email accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + PROGRESS_DESC_BOB + GENRE_DESC_AMY + GENRE_DESC_BOB
                + AUTHOR_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedBookmark));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, TITLE_DESC_BOB + PROGRESS_DESC_BOB + GENRE_DESC_BOB + AUTHOR_DESC_AMY
                + AUTHOR_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedBookmark));

        // multiple tags - all accepted
        Bookmark expectedBookmarkMultipleTags = new BookmarkBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, TITLE_DESC_BOB + PROGRESS_DESC_BOB + GENRE_DESC_BOB + AUTHOR_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedBookmarkMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // only title and genre present
        Bookmark expectedBookmark = new BookmarkBuilder(AMY).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_AMY + GENRE_DESC_AMY, new AddCommand(expectedBookmark));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TITLE_BOB + PROGRESS_DESC_BOB + GENRE_DESC_BOB + AUTHOR_DESC_BOB,
                expectedMessage);

        // missing genre prefix
        assertParseFailure(parser, TITLE_DESC_BOB + PROGRESS_DESC_BOB + VALID_GENRE_BOB + AUTHOR_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_BOB + VALID_PROGRESS_BOB + VALID_GENRE_BOB + VALID_AUTHOR_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TITLE_DESC + PROGRESS_DESC_BOB + GENRE_DESC_BOB + AUTHOR_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Title.MESSAGE_CONSTRAINTS);

        // invalid progress
        assertParseFailure(parser, TITLE_DESC_BOB + INVALID_PROGRESS_DESC + GENRE_DESC_BOB + AUTHOR_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Progress.MESSAGE_CONSTRAINTS);

        // invalid genre
        assertParseFailure(parser, TITLE_DESC_BOB + PROGRESS_DESC_BOB + INVALID_GENRE_DESC + AUTHOR_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Genre.MESSAGE_CONSTRAINTS);

        // invalid author
        assertParseFailure(parser, TITLE_DESC_BOB + PROGRESS_DESC_BOB + GENRE_DESC_BOB + INVALID_AUTHOR_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Author.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_BOB + PROGRESS_DESC_BOB + GENRE_DESC_BOB + AUTHOR_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + PROGRESS_DESC_BOB + GENRE_DESC_BOB + INVALID_AUTHOR_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_BOB + PROGRESS_DESC_BOB + GENRE_DESC_BOB
                + AUTHOR_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
