package seedu.sprint.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.sprint.testutil.Assert.assertThrows;
import static seedu.sprint.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.parser.exceptions.ParseException;
import seedu.sprint.model.application.CompanyEmail;
import seedu.sprint.model.application.CompanyName;
import seedu.sprint.model.application.Role;
import seedu.sprint.model.application.Status;
import seedu.sprint.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_COMPANY_NAME = "@lphabet";
    private static final String INVALID_STATUS = "waitlisted";
    private static final String INVALID_ROLE = "";
    private static final String INVALID_COMPANY_EMAIL = "bob!yahoo";
    private static final String INVALID_TAG = "#highSalary";

    private static final String VALID_COMPANY_NAME = "ByteDance";
    private static final String VALID_STATUS = "OFFERED";
    private static final String VALID_ROLE = "Software Engineer";
    private static final String VALID_COMPANY_EMAIL = "tiktokhiring@bytedance.com";
    private static final String VALID_TAG_1 = "highSalary";
    private static final String VALID_TAG_2 = "talentConnect";

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
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseCompanyName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompanyName((String) null));
    }

    @Test
    public void parseCompanyName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompanyName(INVALID_COMPANY_NAME));
    }

    @Test
    public void parseCompanyName_validValueWithoutWhitespace_returnsCompanyName() throws Exception {
        CompanyName expectedCompanyName = new CompanyName(VALID_COMPANY_NAME);
        assertEquals(expectedCompanyName, ParserUtil.parseCompanyName(VALID_COMPANY_NAME));
    }

    @Test
    public void parseCompanyName_validValueWithWhitespace_returnsTrimmedCompanyName() throws Exception {
        String companyNameWithWhitespace = WHITESPACE + VALID_COMPANY_NAME + WHITESPACE;
        CompanyName expectedCompanyName = new CompanyName(VALID_COMPANY_NAME);
        assertEquals(expectedCompanyName, ParserUtil.parseCompanyName(companyNameWithWhitespace));
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
    public void parseStatus_validValueWithoutWhitespace_returnsStatus() throws Exception {
        Status expectedStatus = new Status(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithWhitespace_returnsTrimmedStatus() throws Exception {
        String statusWithWhitespace = WHITESPACE + VALID_STATUS + WHITESPACE;
        Status expectedStatus = new Status(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(statusWithWhitespace));
    }

    @Test
    public void parseRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRole((String) null));
    }

    @Test
    public void parseRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRole(INVALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithoutWhitespace_returnsRole() throws Exception {
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(VALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithWhitespace_returnsTrimmedRole() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_ROLE + WHITESPACE;
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(roleWithWhitespace));
    }

    @Test
    public void parseCompanyEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompanyEmail((String) null));
    }

    @Test
    public void parseCompanyEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompanyEmail(INVALID_COMPANY_EMAIL));
    }

    @Test
    public void parseCompanyEmail_validValueWithoutWhitespace_returnsCompanyEmail() throws Exception {
        CompanyEmail expectedEmail = new CompanyEmail(VALID_COMPANY_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseCompanyEmail(VALID_COMPANY_EMAIL));
    }

    @Test
    public void parseCompanyEmail_validValueWithWhitespace_returnsTrimmedCompanyEmail() throws Exception {
        String companyEmailWithWhitespace = WHITESPACE + VALID_COMPANY_EMAIL + WHITESPACE;
        CompanyEmail expectedCompanyEmail = new CompanyEmail(VALID_COMPANY_EMAIL);
        assertEquals(expectedCompanyEmail, ParserUtil.parseCompanyEmail(companyEmailWithWhitespace));
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
        assertThrows(ParseException.class, () -> ParserUtil
                .parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
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
