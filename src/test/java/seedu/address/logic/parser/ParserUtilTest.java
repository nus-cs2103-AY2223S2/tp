package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

public class ParserUtilTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM = "hellothere";
    private static final String INVALID_GROUP = "#friend";
    private static final String INVALID_MODULE = "CS50";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "Rochor";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TELEGRAM = "@rachelwalker";
    private static final String VALID_GROUP_1 = "friend";
    private static final String VALID_GROUP_2 = "neighbour";
    private static final String VALID_MODULE_1 = "CS2103T";
    private static final String VALID_MODULE_2 = "CFG2002";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseContactIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(new ContactIndex(1), ParserUtil.parseContactIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(new ContactIndex(1), ParserUtil.parseContactIndex("  1  "));
    }

    @Test
    public void parseIndex_empty_success() throws Exception {
        assertNull(ParserUtil.parseContactIndex(""));
    }

    @Test
    public void parseInt_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInt("10 a"));
    }

    @Test
    public void parseInt_empty_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseInt(""));
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
    public void parseTelegramHandle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTelegramHandle((String) null));
    }

    @Test
    public void parseTelegramHandle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegramHandle(INVALID_TELEGRAM));
    }

    @Test
    public void parseTelegramHandle_validValueWithoutWhitespace_returnsTelegramHandle() throws Exception {
        TelegramHandle expectedTelegramHandle = new TelegramHandle(VALID_TELEGRAM);
        assertEquals(expectedTelegramHandle, ParserUtil.parseTelegramHandle(VALID_TELEGRAM));
    }

    @Test
    public void parseTelegramHandle_validValueWithWhitespace_returnsTrimmedTelegramHandle() throws Exception {
        String telegramHandleWithWhitespace = WHITESPACE + VALID_TELEGRAM + WHITESPACE;
        TelegramHandle expectedTelegramHandle = new TelegramHandle(VALID_TELEGRAM);
        assertEquals(expectedTelegramHandle, ParserUtil.parseTelegramHandle(telegramHandleWithWhitespace));
    }

    @Test
    public void parseGroupTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupTag(null));
    }

    @Test
    public void parseGroupTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroupTag(INVALID_GROUP));
    }

    @Test
    public void parseGroupTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        GroupTag expectedGroupTag = new GroupTag(VALID_GROUP_1);
        assertEquals(expectedGroupTag, ParserUtil.parseGroupTag(VALID_GROUP_1));
    }

    @Test
    public void parseGroupTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_GROUP_1 + WHITESPACE;
        GroupTag expectedGroupTag = new GroupTag(VALID_GROUP_1);
        assertEquals(expectedGroupTag, ParserUtil.parseGroupTag(tagWithWhitespace));
    }

    @Test
    public void parseGroupTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupTags(null));
    }

    @Test
    public void parseGroupTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroupTags(
                Arrays.asList(VALID_GROUP_1, INVALID_GROUP)));
    }

    @Test
    public void parseGroupTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseGroupTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseGroupTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<GroupTag> actualGroupTagSet = ParserUtil.parseGroupTags(Arrays.asList(VALID_GROUP_1, VALID_GROUP_2));
        Set<GroupTag> expectedGroupTagSet = new HashSet<GroupTag>(Arrays.asList(new GroupTag(VALID_GROUP_1),
                new GroupTag(VALID_GROUP_2)));

        assertEquals(expectedGroupTagSet, actualGroupTagSet);
    }

    @Test
    public void parseModuleTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleTag(null));
    }

    @Test
    public void parseModuleTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(INVALID_GROUP));
    }

    @Test
    public void parseModuleTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        ModuleTag expectedModuleTag = new ModuleTag(VALID_MODULE_1);
        assertEquals(expectedModuleTag, ParserUtil.parseModuleTag(VALID_MODULE_1));
    }

    @Test
    public void parseModuleTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_MODULE_1 + WHITESPACE;
        ModuleTag expectedModuleTag = new ModuleTag(VALID_MODULE_1);
        assertEquals(expectedModuleTag, ParserUtil.parseModuleTag(tagWithWhitespace));
    }

    @Test
    public void parseModuleTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleTags(null));
    }

    @Test
    public void parseModuleTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTags(
                Arrays.asList(VALID_MODULE_1, INVALID_MODULE)));
    }

    @Test
    public void parseModuleTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseModuleTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseModuleTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<ModuleTag> actualModuleTagSet = ParserUtil.parseModuleTags(Arrays.asList(VALID_MODULE_1, VALID_MODULE_2));
        Set<ModuleTag> expectedModuleTagSet = new HashSet<ModuleTag>(Arrays.asList(new ModuleTag(VALID_MODULE_1),
                new ModuleTag(VALID_MODULE_2)));

        assertEquals(expectedModuleTagSet, actualModuleTagSet);
    }
}
