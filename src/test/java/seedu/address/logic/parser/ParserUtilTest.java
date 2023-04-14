package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Address;
import seedu.address.model.module.Name;
import seedu.address.model.module.Resource;
import seedu.address.model.module.TimeSlot;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_RESOURCE = "        ";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TIMESLOT = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "CS4243";
    private static final String VALID_RESOURCE = "rachel@example.com";
    private static final String VALID_ADDRESS = "Com 1";
    private static final String VALID_TIMESLOT = "Tuesday 12:00 14:00";
    private static final String VALID_TAG_1 = "Tutorial";
    private static final String VALID_TAG_2 = "Lab";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_MODULE_DISPLAYED_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_MODULE, ParserUtil.parseIndex("  1  "));
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
    public void parseResource_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseResource((String) null));
    }

    @Test
    public void parseResource_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseResource(INVALID_RESOURCE));
    }

    @Test
    public void parseResource_validValueWithoutWhitespace_returnsResource() throws Exception {
        Resource expectedResource = new Resource(VALID_RESOURCE);
        assertEquals(expectedResource, ParserUtil.parseResource(VALID_RESOURCE));
    }

    @Test
    public void parseResource_validValueWithWhitespace_returnsTrimmedResource() throws Exception {
        String resourceWithWhitespace = WHITESPACE + VALID_RESOURCE + WHITESPACE;
        Resource expectedResource = new Resource(VALID_RESOURCE);
        assertEquals(expectedResource, ParserUtil.parseResource(resourceWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseTimeSlot_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTimeSlot((String) null));
    }

    @Test
    public void parseTimeSlot_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTimeSlot(INVALID_TIMESLOT));
    }

    @Test
    public void parseTimeSlot_validValueWithoutWhitespace_returnsTimeSlot() throws Exception {
        TimeSlot expectedTimeSlot = new TimeSlot(VALID_TIMESLOT);
        assertEquals(expectedTimeSlot, ParserUtil.parseTimeSlot(VALID_TIMESLOT));
    }

    @Test
    public void parseTimeSlot_validValueWithWhitespace_returnsTrimmedTimeSlot() throws Exception {
        String timeSlotWithWhitespace = WHITESPACE + VALID_TIMESLOT + WHITESPACE;
        TimeSlot expectedTimeSlot = new TimeSlot(VALID_TIMESLOT);
        assertEquals(expectedTimeSlot, ParserUtil.parseTimeSlot(timeSlotWithWhitespace));
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
