package arb.logic.parser;

import static arb.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;
import static arb.testutil.TypicalProjectSortingOptions.BY_DEADLINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import arb.logic.parser.exceptions.ParseException;
import arb.model.client.Email;
import arb.model.client.Name;
import arb.model.client.Phone;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Status;
import arb.model.project.Title;
import arb.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_TITLE = "Sk! P@inting";
    private static final String INVALID_DEADLINE = "ocean";
    private static final String INVALID_STATUS = "accomplished";
    private static final String INVALID_PRICE = "and";
    private static final String INVALID_SORTING_OPTION = "sky";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String VALID_TITLE = "Sky Painting";
    private static final String VALID_DEADLINE = "3pm today";
    private static final String VALID_STATUS = "not done";
    private static final String VALID_PRICE = "3";
    private static final String VALID_SORTING_OPTION = "deadline";

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
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
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
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_emptyString_returnsNull() throws Exception {
        assertEquals(ParserUtil.parsePhone(""), null);
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_emptyString_returnsNull() throws Exception {
        assertEquals(ParserUtil.parseEmail(""), null);
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
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

    @Test
    public void parseTags_withCaseDifferences_returnsSameTagSet() throws Exception {
        Set<Tag> tagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> capitalTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1.toUpperCase(),
                VALID_TAG_2.toUpperCase()));

        assertEquals(tagSet, capitalTagSet);
    }

    @Test
    public void parseTagsForEdit_emptyCollection_returnsEmptyOptional() throws Exception {
        assertEquals(Optional.empty(), ParserUtil.parseTagsForEdit(Collections.emptyList()));
    }

    @Test
    public void parseTagsForEdit_emptyPrefix_returnsEmptySet() throws Exception {
        assertEquals(Optional.of(Collections.emptySet()), ParserUtil.parseTagsForEdit(Arrays.asList("")));
    }

    @Test
    public void parseTagsForEdit_nonEmptySet() throws Exception {
        List<String> listOfTagKeywords = Arrays.asList("a", "b");
        List<Tag> listOfTags = Arrays.asList(new Tag("a"), new Tag("b"));
        assertEquals(Optional.of(new HashSet<>(listOfTags)), ParserUtil.parseTagsForEdit(listOfTagKeywords));
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
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline(null));
    }

    @Test
    public void parseDeadline_emptyString_returnsNull() throws Exception {
        assertEquals(ParserUtil.parseDeadline(""), null);
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(deadlineWithWhitespace));
    }

    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus(null));
    }

    @Test
    public void parseStatus_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(""));
    }

    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithoutWhitespace_returnsStatus() throws Exception {
        Status expectedStatus = new Status(false); // produced by VALID_STATUS
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithWhitespace_returnsStatus() throws Exception {
        String statusWithWhitespace = WHITESPACE + VALID_STATUS + WHITESPACE;
        Status expectedStatus = new Status(false);
        assertEquals(expectedStatus, ParserUtil.parseStatus(statusWithWhitespace));
    }

    @Test
    public void parsePrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE));
    }

    @Test
    public void parsePrice_emptyString_returnsNull() throws Exception {
        assertEquals(ParserUtil.parsePrice(""), null);
    }

    @Test
    public void parsePrice_validValueWithoutWhitespace_returnsPrice() throws Exception {
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(VALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithWhitespace_returnsTrimmedPrice() throws Exception {
        String priceWithWhitespace = WHITESPACE + VALID_PRICE + WHITESPACE;
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(priceWithWhitespace));
    }

    @Test
    public void parseSortingOption_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSortingOption((String) null));
    }

    @Test
    public void parseSortingOption_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortingOption(INVALID_SORTING_OPTION));
    }

    @Test
    public void parseSortingOption_validValueWithoutWhitespace_returnsSortingOption() throws Exception {
        assertEquals(BY_DEADLINE, ParserUtil.parseSortingOption(VALID_SORTING_OPTION));
    }

    @Test
    public void parseSortingOption_validValueWithWhitespace_returnsSortingOption() throws Exception {
        String sortingOptionWithWhitespace = WHITESPACE + VALID_SORTING_OPTION + WHITESPACE;
        assertEquals(BY_DEADLINE, ParserUtil.parseSortingOption(sortingOptionWithWhitespace));
    }

    @Test
    public void parseSortingOption_withCaseDifferences_returnsSortingOption() throws Exception {
        String capitalSortingOption = VALID_SORTING_OPTION.toUpperCase();
        assertEquals(BY_DEADLINE, ParserUtil.parseSortingOption(capitalSortingOption));
    }
}
