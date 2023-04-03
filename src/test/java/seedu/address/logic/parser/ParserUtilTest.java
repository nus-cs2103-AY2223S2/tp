package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_MODULE_CODE_1 = "CS2040S";
    private static final String VALID_MODULE_CODE_2 = "EG2310";
    private static final String INVALID_MODULE_CODE = "#CS2030";


    private static final String WHITESPACE = " \t\r\n";

    // @Test
    // public void parseName_null_throwsNullPointerException() {
    //     assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    // }

    // @Test
    // public void parseName_invalidValue_throwsParseException() {
    //     assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    // }

    // @Test
    // public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
    //     Name expectedName = new Name(VALID_NAME);
    //     assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    // }

    // @Test
    // public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
    //     String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
    //     Name expectedName = new Name(VALID_NAME);
    //     assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    // }

    // @Test
    // public void parsePhone_null_throwsNullPointerException() {
    //     assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    // }

    // @Test
    // public void parsePhone_invalidValue_throwsParseException() {
    //     assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    // }

    // @Test
    // public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
    //     Phone expectedPhone = new Phone(VALID_PHONE);
    //     assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    // }

    // @Test
    // public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
    //     String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
    //     Phone expectedPhone = new Phone(VALID_PHONE);
    //     assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    // }

    // @Test
    // public void parseAddress_null_throwsNullPointerException() {
    //     assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    // }

    // @Test
    // public void parseAddress_invalidValue_throwsParseException() {
    //     assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    // }

    // @Test
    // public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
    //     Address expectedAddress = new Address(VALID_ADDRESS);
    //     assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    // }

    // @Test
    // public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
    //     String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
    //     Address expectedAddress = new Address(VALID_ADDRESS);
    //     assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    // }

    // @Test
    // public void parseEmail_null_throwsNullPointerException() {
    //     assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    // }

    // @Test
    // public void parseEmail_invalidValue_throwsParseException() {
    //     assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    // }

    // @Test
    // public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
    //     Email expectedEmail = new Email(VALID_EMAIL);
    //     assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    // }

    // @Test
    // public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
    //     String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
    //     Email expectedEmail = new Email(VALID_EMAIL);
    //     assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    // }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMultiTags((String) null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiTags(INVALID_TAG));
    }

    @Test
    public void parseTag_invalidValueWithWhitespace_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiTags(WHITESPACE + INVALID_TAG + WHITESPACE));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMultiTags((String) null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMultiTags((Collection<String>) null));
    }

    @Test
    public void parseTagsCollection_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMultiTags((Collection<String>) null));
    }

    @Test
    public void parseTags_stringWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiTags(INVALID_TAG));
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiTags(VALID_TAG_1 + ", " + INVALID_TAG));
    }

    @Test
    public void parseTags_listWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiTags(List.of(INVALID_TAG)));
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiTags(List.of(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyString_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseMultiTags(" ").isEmpty());
    }

    @Test
    public void parseTags_stringWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseMultiTags(VALID_TAG_1 + ", " + VALID_TAG_2);
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTags_listWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseMultiTags(List.of(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTags_stringWithValidTagsWithWhitespace_returnsTagSet() throws Exception {
        String firstTagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        String secondTagWithWhitespace = WHITESPACE + VALID_TAG_2 + WHITESPACE;
        Set<Tag> actualTagSet = ParserUtil.parseMultiTags(firstTagWithWhitespace + ", " + secondTagWithWhitespace);
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTags_listWithValidTagsWithWhitespace_returnsTagSet() throws Exception {
        String firstTagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        String secondTagWithWhitespace = WHITESPACE + VALID_TAG_2 + WHITESPACE;
        List<String> listOfTags = List.of(firstTagWithWhitespace, secondTagWithWhitespace);
        Set<Tag> actualTagSet = ParserUtil.parseMultiTags(listOfTags);
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseModuleCode_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleCode(null));
    }

    @Test
    public void parseModuleCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCode(INVALID_MODULE_CODE));
    }

    @Test
    public void parseModuleCode_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleCode(" "));
    }

    @Test
    public void parseModuleCode_validValue_returnModuleCode() throws ParseException {
        assertEquals(new ModuleCode(VALID_MODULE_CODE_1), ParserUtil.parseModuleCode(VALID_MODULE_CODE_1));
    }


    @Test
    public void parseMultiModuleCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMultiModuleCode(null));
    }

    @Test
    public void parseMultiModuleCode_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiModuleCode(" "));
    }

    @Test
    public void parseMultiModuleCode_invalidModuleCode_throwParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiModuleCode(INVALID_MODULE_CODE));
    }

    @Test
    public void parseMultiModuleCode_stringOfInvalidModuleCodes_throwParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultiModuleCode(
                VALID_MODULE_CODE_1 + ", " + INVALID_MODULE_CODE));
    }

    @Test
    public void parseMultiModuleCode_stringOfValidModuleCodes_returnSetOfModuleCodes() throws ParseException {
        assertEquals(new HashSet<>(List.of(new ModuleCode(VALID_MODULE_CODE_1), new ModuleCode(VALID_MODULE_CODE_2))),
                ParserUtil.parseMultiModuleCode(VALID_MODULE_CODE_1 + ", " + VALID_MODULE_CODE_2));
    }
}
