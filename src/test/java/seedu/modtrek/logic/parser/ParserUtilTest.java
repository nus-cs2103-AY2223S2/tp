package seedu.modtrek.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.CodePrefix;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_CODE_PREFIX = "C@";
    private static final String INVALID_CODE = "CS@3230";
    private static final String INVALID_CREDIT = "+4";
    private static final String INVALID_GRADE = "A++";
    private static final String INVALID_SEMYEAR = "Year 2 Sem 2";
    private static final String INVALID_TAG = "#computer science breath and depth";

    private static final String VALID_CODE_PREFIX = "CS";
    private static final String VALID_CODE = "CS3230";
    private static final String VALID_CREDIT = "4";
    private static final String VALID_GRADE = "A+";
    private static final String VALID_SEMYEAR = "Y2S2";
    private static final String VALID_TAG_1 = "computer science breadth and depth";
    private static final String VALID_TAG_2 = "computer science foundation";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseCodePrefix_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCodePrefix((String) null));
    }

    @Test
    public void parseCodePrefix_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCodePrefix(INVALID_CODE_PREFIX));
    }

    @Test
    public void parseCodePrefix_validValueWithoutWhitespace_returnsCode() throws Exception {
        CodePrefix expectedCode = new CodePrefix(VALID_CODE_PREFIX);
        assertEquals(expectedCode, ParserUtil.parseCodePrefix(VALID_CODE_PREFIX));
    }

    @Test
    public void parseCodePrefix_validValueWithWhitespace_returnsTrimmedCode() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_CODE_PREFIX + WHITESPACE;
        CodePrefix expectedCode = new CodePrefix(VALID_CODE_PREFIX);
        assertEquals(expectedCode, ParserUtil.parseCodePrefix(nameWithWhitespace));
    }

    @Test
    public void parseCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCode((String) null));
    }

    @Test
    public void parseCode_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCode(INVALID_CODE));
    }

    @Test
    public void parseCode_validValueWithoutWhitespace_returnsCode() throws Exception {
        Code expectedCode = new Code(VALID_CODE);
        assertEquals(expectedCode, ParserUtil.parseCode(VALID_CODE));
    }

    @Test
    public void parseCode_validValueWithWhitespace_returnsTrimmedCode() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_CODE + WHITESPACE;
        Code expectedCode = new Code(VALID_CODE);
        assertEquals(expectedCode, ParserUtil.parseCode(nameWithWhitespace));
    }

    @Test
    public void parseCredit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCredit((String) null));
    }

    @Test
    public void parseCredit_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCredit(INVALID_CREDIT));
    }

    @Test
    public void parseCredit_validValueWithoutWhitespace_returnsCredit() throws Exception {
        Credit expectedCredit = new Credit(VALID_CREDIT);
        assertEquals(expectedCredit, ParserUtil.parseCredit(VALID_CREDIT));
    }

    @Test
    public void parseCredit_validValueWithWhitespace_returnsTrimmedCredit() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_CREDIT + WHITESPACE;
        Credit expectedCredit = new Credit(VALID_CREDIT);
        assertEquals(expectedCredit, ParserUtil.parseCredit(phoneWithWhitespace));
    }

    @Test
    public void parseGrade_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGrade((String) null));
    }

    @Test
    public void parseGrade_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGrade(INVALID_GRADE));
    }

    @Test
    public void parseGrade_validValueWithoutWhitespace_returnsGrade() throws Exception {
        Grade expectedGrade = new Grade(VALID_GRADE);
        assertEquals(expectedGrade, ParserUtil.parseGrade(VALID_GRADE));
    }

    @Test
    public void parseGrade_validValueWithWhitespace_returnsTrimmedGrade() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_GRADE + WHITESPACE;
        Grade expectedGrade = new Grade(VALID_GRADE);
        assertEquals(expectedGrade, ParserUtil.parseGrade(addressWithWhitespace));
    }

    @Test
    public void parseSemYear_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSemYear((String) null));
    }

    @Test
    public void parseSemYear_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSemYear(INVALID_SEMYEAR));
    }

    @Test
    public void parseSemYear_validValueWithoutWhitespace_returnsSemYear() throws Exception {
        SemYear expectedSemYear = new SemYear(VALID_SEMYEAR);
        assertEquals(expectedSemYear, ParserUtil.parseSemYear(VALID_SEMYEAR));
    }

    @Test
    public void parseSemYear_validValueWithWhitespace_returnsTrimmedSemYear() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_SEMYEAR + WHITESPACE;
        SemYear expectedSemYear = new SemYear(VALID_SEMYEAR);
        assertEquals(expectedSemYear, ParserUtil.parseSemYear(emailWithWhitespace));
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
