package seedu.internship.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internship.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.internship.testutil.Assert.assertThrows;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;



import org.junit.jupiter.api.Test;

import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.internship.Company;
import seedu.internship.model.internship.Position;
import seedu.internship.model.internship.Status;


public class ParserUtilTest {
    private static final String INVALID_POSITION = "Softwa**re Engin**ring";
    private static final String INVALID_COMPANY = "**Google";
    private static final String INVALID_STATUS = "50";

    private static final String VALID_POSITION = "Softwaare Engineering";
    private static final String VALID_COMPANY = "Google";
    private static final String VALID_STATUS = "0";

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
        assertEquals(INDEX_FIRST_INTERNSHIP, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_INTERNSHIP, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parsePosition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePosition((String) null));
    }

    @Test
    public void parsePosition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePosition(INVALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithoutWhitespace_returnsName() throws Exception {
        Position expectedPostion = new Position(VALID_POSITION);
        assertEquals(expectedPostion, ParserUtil.parsePosition(VALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String positionWithWhitespace = WHITESPACE + VALID_POSITION + WHITESPACE;
        Position expectedName = new Position(VALID_POSITION);
        assertEquals(expectedName, ParserUtil.parsePosition(positionWithWhitespace));
    }

    @Test
    public void parseCompany_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompany((String) null));
    }

    @Test
    public void parseCompany_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompany(INVALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Company expectedPhone = new Company(VALID_COMPANY);
        assertEquals(expectedPhone, ParserUtil.parseCompany(VALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String companyWithWhitespace = WHITESPACE + VALID_COMPANY + WHITESPACE;
        Company expectedPhone = new Company(VALID_COMPANY);
        assertEquals(expectedPhone, ParserUtil.parseCompany(companyWithWhitespace));
    }

    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus((String) null));
    }

    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Status expectedStatus = new Status(Integer.valueOf(VALID_STATUS));
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String statusWithWhitespace = WHITESPACE + VALID_STATUS + WHITESPACE;
        Status expectedAddress = new Status(Integer.valueOf(VALID_STATUS));
        assertEquals(expectedAddress, ParserUtil.parseStatus(statusWithWhitespace));
    }

    // Emails and Tags don;t need to be tested , same goes for Descirpiton,
//    @Test
//    public void parseEmail_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
//    }
//
//    @Test
//    public void parseEmail_invalidValue_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
//    }
//
//    @Test
//    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
//        Email expectedEmail = new Email(VALID_EMAIL);
//        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
//    }
//
//    @Test
//    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
//        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
//        Email expectedEmail = new Email(VALID_EMAIL);
//        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
//    }
//
//    @Test
//    public void parseTag_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
//    }
//
//    @Test
//    public void parseTag_invalidValue_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
//    }
//
//    @Test
//    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
//        Tag expectedTag = new Tag(VALID_TAG_1);
//        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
//    }
//
//    @Test
//    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
//        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
//        Tag expectedTag = new Tag(VALID_TAG_1);
//        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
//    }
//
//    @Test
//    public void parseTags_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
//    }
//
//    @Test
//    public void parseTags_collectionWithInvalidTags_throwsParseException() {
//        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
//    }
//
//    @Test
//    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
//        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
//    }
//
//    @Test
//    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
//        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
//        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
//
//        assertEquals(expectedTagSet, actualTagSet);
//    }
}
