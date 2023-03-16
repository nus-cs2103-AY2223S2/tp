package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FISH;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_LAST_FED_DATE = "20 Mar 2023";
    private static final String INVALID_FEEDING_INTERVAL = " ";
    private static final String INVALID_SPECIES = "asd123 @!";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_LAST_FED_DATE = "04/01/2022";
    private static final String VALID_FEEDING_INTERVAL = "5d0h";
    private static final String VALID_SPECIES = "Guppy";
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
        assertEquals(INDEX_FIRST_FISH, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_FISH, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseLastFedDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLastFedDate((String) null));
    }

    @Test
    public void parseLastFedDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLastFedDate(INVALID_LAST_FED_DATE));
    }

    @Test
    public void parseLastFedDate_validValueWithoutWhitespace_returnsLastFedDate() throws Exception {
        LastFedDate expectedLastFedDate = new LastFedDate(VALID_LAST_FED_DATE);
        assertEquals(expectedLastFedDate, ParserUtil.parseLastFedDate(VALID_LAST_FED_DATE));
    }

    @Test
    public void parseLastFedDate_validValueWithWhitespace_returnsTrimmedLastFedDate() throws Exception {
        String lastFedDateWithWhitespace = WHITESPACE + VALID_LAST_FED_DATE + WHITESPACE;
        LastFedDate expectedLastFedDate = new LastFedDate(VALID_LAST_FED_DATE);
        assertEquals(expectedLastFedDate, ParserUtil.parseLastFedDate(lastFedDateWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFeedingInterval((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFeedingInterval(INVALID_FEEDING_INTERVAL));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        FeedingInterval expectedFeedingInterval = new FeedingInterval(VALID_FEEDING_INTERVAL);
        assertEquals(expectedFeedingInterval, ParserUtil.parseFeedingInterval(VALID_FEEDING_INTERVAL));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_FEEDING_INTERVAL + WHITESPACE;
        FeedingInterval expectedFeedingInterval = new FeedingInterval(VALID_FEEDING_INTERVAL);
        assertEquals(expectedFeedingInterval, ParserUtil.parseFeedingInterval(addressWithWhitespace));
    }

    @Test
    public void parseSpecies_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSpecies((String) null));
    }

    @Test
    public void parseSpecies_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSpecies(INVALID_SPECIES));
    }

    @Test
    public void parseSpecies_validValueWithoutWhitespace_returnsSpecies() throws Exception {
        Species expectedSpecies = new Species(VALID_SPECIES);
        assertEquals(expectedSpecies, ParserUtil.parseSpecies(VALID_SPECIES));
    }

    @Test
    public void parseSpecies_validValueWithWhitespace_returnsTrimmedSpecies() throws Exception {
        String speciesWithWhitespace = WHITESPACE + VALID_SPECIES + WHITESPACE;
        Species expectedSpecies = new Species(VALID_SPECIES);
        assertEquals(expectedSpecies, ParserUtil.parseSpecies(speciesWithWhitespace));
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
