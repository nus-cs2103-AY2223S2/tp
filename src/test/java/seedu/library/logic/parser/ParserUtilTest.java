package seedu.library.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalIndexes.INDEX_FIRST_BOOKMARK;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.library.logic.parser.exceptions.ParseException;
import seedu.library.model.bookmark.Author;
import seedu.library.model.bookmark.Genre;
import seedu.library.model.bookmark.Progress;
import seedu.library.model.bookmark.Title;
import seedu.library.model.tag.Tag;

public class ParserUtilTest {
    public static final String INVALID_TITLE_LENGTH = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    private static final String INVALID_TITLE = " ";
    private static final String INVALID_PROGRESS = "+651234";
    private static final String INVALID_AUTHOR = " ";
    private static final String INVALID_GENRE = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TITLE = "Rachel Walker";
    private static final String VALID_PROGRESS_INPUT = "1 57 25";
    private static final String[] VALID_PROGRESS_ARRAY = new String[]{"1", "57", "25"};
    private static final String VALID_AUTHOR = "123 Main Street #0505";
    private static final String VALID_GENRE = "Fantasy";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_BOOKMARK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_BOOKMARK, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_invalidLength_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE_LENGTH));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTrimmedTitle() throws Exception {
        String titleWithWhitespace = WHITESPACE + VALID_TITLE + WHITESPACE;
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(titleWithWhitespace));
    }

    @Test
    public void parseProgress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseProgress((String) null));
    }

    @Test
    public void parseProgress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseProgress(INVALID_PROGRESS));
    }

    @Test
    public void parseProgress_validValueWithoutWhitespace_returnsProgress() throws Exception {
        Progress expectedProgress = new Progress(VALID_PROGRESS_ARRAY);
        assertEquals(expectedProgress, ParserUtil.parseProgress(VALID_PROGRESS_INPUT));
    }

    @Test
    public void parseProgress_validValueWithWhitespace_returnsTrimmedProgress() throws Exception {
        String progressWithWhitespace = WHITESPACE + VALID_PROGRESS_INPUT + WHITESPACE;
        Progress expectedProgress = new Progress(VALID_PROGRESS_ARRAY);
        assertEquals(expectedProgress, ParserUtil.parseProgress(progressWithWhitespace));
    }

    @Test
    public void parseAuthor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAuthor((String) null));
    }

    @Test
    public void parseAuthor_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAuthor(INVALID_AUTHOR));
    }

    @Test
    public void parseAuthor_validValueWithoutWhitespace_returnsAuthor() throws Exception {
        Author expectedAuthor = new Author(VALID_AUTHOR);
        assertEquals(expectedAuthor, ParserUtil.parseAuthor(VALID_AUTHOR));
    }

    @Test
    public void parseAuthor_validValueWithWhitespace_returnsTrimmedAuthor() throws Exception {
        String authorWithWhitespace = WHITESPACE + VALID_AUTHOR + WHITESPACE;
        Author expectedAuthor = new Author(VALID_AUTHOR);
        assertEquals(expectedAuthor, ParserUtil.parseAuthor(authorWithWhitespace));
    }

    @Test
    public void parseGenre_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGenre((String) null));
    }

    @Test
    public void parseGenre_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGenre(INVALID_GENRE));
    }

    @Test
    public void parseGenre_validValueWithoutWhitespace_returnsGenre() throws Exception {
        Genre expectedGenre = new Genre(VALID_GENRE);
        assertEquals(expectedGenre, ParserUtil.parseGenre(VALID_GENRE));
    }

    @Test
    public void parseGenre_validValueWithWhitespace_returnsTrimmedGenre() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_GENRE + WHITESPACE;
        Genre expectedGenre = new Genre(VALID_GENRE);
        assertEquals(expectedGenre, ParserUtil.parseGenre(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
