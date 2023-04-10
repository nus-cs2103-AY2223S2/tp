package seedu.techtrack.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.techtrack.testutil.Assert.assertThrows;
import static seedu.techtrack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;
import seedu.techtrack.model.role.Company;
import seedu.techtrack.model.role.Contact;
import seedu.techtrack.model.role.Deadline;
import seedu.techtrack.model.role.Email;
import seedu.techtrack.model.role.Experience;
import seedu.techtrack.model.role.Name;
import seedu.techtrack.model.role.Website;
import seedu.techtrack.model.util.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_CONTACT = "+651234";
    private static final String INVALID_COMPANY = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_WEBSITE = "#www.com";
    private static final String INVALID_DEADLINE = "2019-10-20";
    private static final String INVALID_EXPERIENCE = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_CONTACT = "123456";
    private static final String VALID_COMPANY = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_WEBSITE = "www.google.com";
    private static final String VALID_DEADLINE = "2023-10-20";
    private static final String VALID_EXPERIENCE = "Javascript - 1 Year";
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
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
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
    public void parseContact_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContact((String) null));
    }

    @Test
    public void parseContact_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContact(INVALID_CONTACT));
    }

    @Test
    public void parseContact_validValueWithoutWhitespace_returnsContact() throws Exception {
        Contact expectedContact = new Contact(VALID_CONTACT);
        assertEquals(expectedContact, ParserUtil.parseContact(VALID_CONTACT));
    }

    @Test
    public void parseContact_validValueWithWhitespace_returnsTrimmedContact() throws Exception {
        String contactWithWhitespace = WHITESPACE + VALID_CONTACT + WHITESPACE;
        Contact expectedContact = new Contact(VALID_CONTACT);
        assertEquals(expectedContact, ParserUtil.parseContact(contactWithWhitespace));
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
    public void parseCompany_validValueWithoutWhitespace_returnsCompany() throws Exception {
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(VALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithWhitespace_returnsTrimmedCompany() throws Exception {
        String companyWithWhitespace = WHITESPACE + VALID_COMPANY + WHITESPACE;
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(companyWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
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
    public void parseWebsite_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWebsite((String) null));
    }

    @Test
    public void parseWebsite_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWebsite(INVALID_WEBSITE));
    }

    @Test
    public void parseWebsite_validValueWithoutWhitespace_returnsWebsite() throws Exception {
        Website expectedWebsite = new Website(VALID_WEBSITE);
        assertEquals(expectedWebsite, ParserUtil.parseWebsite(VALID_WEBSITE));
    }

    @Test
    public void parseWebsite_validValueWithWhitespace_returnsTrimmedWebsite() throws Exception {
        String websiteWithWhitespace = WHITESPACE + VALID_WEBSITE + WHITESPACE;
        Website expectedWebsite = new Website(VALID_WEBSITE);
        assertEquals(expectedWebsite, ParserUtil.parseWebsite(websiteWithWhitespace));
    }

    @Test
    public void parseExperience_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExperience((String) null));
    }

    @Test
    public void parseExperience_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExperience(INVALID_EXPERIENCE));
    }

    @Test
    public void parseExperience_validValueWithoutWhitespace_returnsExperience() throws Exception {
        Experience expectedExperience = new Experience(VALID_EXPERIENCE);
        assertEquals(expectedExperience, ParserUtil.parseExperience(VALID_EXPERIENCE));
    }

    @Test
    public void parseExperience_validValueWithWhitespace_returnsTrimmedExperience() throws Exception {
        String experienceWithWhitespace = WHITESPACE + VALID_EXPERIENCE + WHITESPACE;
        Experience expectedExperience = new Experience(VALID_EXPERIENCE);
        assertEquals(expectedExperience, ParserUtil.parseExperience(experienceWithWhitespace));
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
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContact((String) null));
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_DEADLINE));
    }
    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsEmail() throws Exception {
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
