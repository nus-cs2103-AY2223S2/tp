package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.score.Date;
import seedu.address.model.score.Label;
import seedu.address.model.score.ScoreValue;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_PHONE_TOO_LONG = "9374610240496946";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TAG_TOO_LONG = "abcdefghijklmnopqrstuvwxyz";
    private static final String INVALID_TASK_NAME = "Do homework now__";
    private static final String INVALID_SCORE_LABEL = "Math_Paper";
    private static final String INVALID_SCORE_VALUE = "123";
    private static final String INVALID_SCORE_DATE = "20220309";
    private static final String INVALID_SCORE_DATE_FUTURE_DATE = "2300-09-09";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_TASK_NAME = "Complete Exercise 1";
    private static final String VALID_SCORE_LABEL = "Math Paper";
    private static final String VALID_SCORE_VALUE = "63";
    private static final String VALID_SCORE_DATE = "2020-04-04";

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
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseFirstIndex_invalidInput_throwsParseException() {
        // More than 2 indexes
        assertThrows(ParseException.class, () -> ParserUtil.parseFirstIndex("10 2 3"));

        // Not integer
        assertThrows(ParseException.class, () -> ParserUtil.parseFirstIndex("b 2"));
    }

    @Test
    public void parseFirstIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseFirstIndex("1 2"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseFirstIndex("  1  2    "));

        // Valid first index only
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseFirstIndex("1  a"));
    }

    @Test
    public void parseSecondIndex_invalidInput_throwsParseException() {
        // More than 2 indexes
        assertThrows(ParseException.class, () -> ParserUtil.parseSecondIndex("10 2 3"));

        // Not integer
        assertThrows(ParseException.class, () -> ParserUtil.parseSecondIndex("1 b"));
    }

    @Test
    public void parseSecondIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_SECOND_TASK, ParserUtil.parseSecondIndex("1 2"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_SECOND_TASK, ParserUtil.parseSecondIndex("  1  2    "));

        // Valid first index only
        assertEquals(INDEX_SECOND_TASK, ParserUtil.parseSecondIndex("a  2"));
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
    public void parsePhone_invalidValuePhoneTooLong_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_TOO_LONG));
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
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_invalidValueTagTooLong_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_TOO_LONG));
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
    public void parseTaskName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskName(null));
    }

    @Test
    public void parseTaskName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskName(INVALID_TASK_NAME));
    }

    @Test
    public void parseTaskName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedTaskName = new Name(VALID_TASK_NAME);
        assertEquals(expectedTaskName, ParserUtil.parseTaskName(VALID_TASK_NAME));
    }

    @Test
    public void parseTaskName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String taskNameWithWhitespace = WHITESPACE + VALID_TASK_NAME + WHITESPACE;
        Name expectedTaskName = new Name(VALID_TASK_NAME);
        assertEquals(expectedTaskName, ParserUtil.parseTaskName(taskNameWithWhitespace));
    }

    @Test
    public void parseScoreLabel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseScoreLabel(null));
    }

    @Test
    public void parseScoreLabel_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScoreLabel(INVALID_SCORE_LABEL));
    }

    @Test
    public void parseScoreLabel_validValueWithoutWhitespace_returnsLabel() throws Exception {
        Label expectedLabel = new Label(VALID_SCORE_LABEL);
        assertEquals(expectedLabel, ParserUtil.parseScoreLabel(VALID_SCORE_LABEL));
    }

    @Test
    public void parseScoreLabel_validValueWithWhitespace_returnsTrimmedLabel() throws Exception {
        String scoreLabelWithWhitespace = WHITESPACE + VALID_SCORE_LABEL + WHITESPACE;
        Label expectedLabel = new Label(VALID_SCORE_LABEL);
        assertEquals(expectedLabel, ParserUtil.parseScoreLabel(scoreLabelWithWhitespace));
    }

    @Test
    public void parseScoreValue_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseScoreValue(null));
    }

    @Test
    public void parseScoreValue_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScoreValue(INVALID_SCORE_VALUE));
    }

    @Test
    public void parseScoreValue_validValueWithoutWhitespace_returnsScoreValue() throws Exception {
        ScoreValue expectedScoreValue = new ScoreValue(VALID_SCORE_VALUE);
        assertEquals(expectedScoreValue, ParserUtil.parseScoreValue(VALID_SCORE_VALUE));
    }

    @Test
    public void parseScoreValue_validValueWithWhitespace_returnsTrimmedScoreValue() throws Exception {
        String scoreValueWithWhitespace = WHITESPACE + VALID_SCORE_VALUE + WHITESPACE;
        ScoreValue expectedScoreValue = new ScoreValue(VALID_SCORE_VALUE);
        assertEquals(expectedScoreValue, ParserUtil.parseScoreValue(scoreValueWithWhitespace));
    }

    @Test
    public void parseScoreDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseScoreDate(null));
    }

    @Test
    public void parseScoreDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScoreDate(INVALID_SCORE_DATE));
    }

    @Test
    public void parseScoreDate_invalidValueDateInFuture_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScoreDate(INVALID_SCORE_DATE_FUTURE_DATE));
    }

    @Test
    public void parseScoreDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedScoreDate = new Date(VALID_SCORE_DATE);
        assertEquals(expectedScoreDate, ParserUtil.parseScoreDate(VALID_SCORE_DATE));
    }

    @Test
    public void parseScoreDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String scoreDateWithWhitespace = WHITESPACE + VALID_SCORE_DATE + WHITESPACE;
        Date expectedScoreDate = new Date(VALID_SCORE_DATE);
        assertEquals(expectedScoreDate, ParserUtil.parseScoreDate(scoreDateWithWhitespace));
    }

    @Test
    public void parseFilePath_invalidCharInPath() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFilePath(Optional.of("???")));
    }

    @Test
    public void parseFilePath_validValueWithoutWhitespace_returnsString() throws Exception {
        String expectedFilePath = System.getProperty("user.home");
        Optional<String> filePathOpt = Optional.of(System.getProperty("user.home"));
        assertEquals(expectedFilePath, ParserUtil.parseFilePath(filePathOpt));
    }

    @Test
    public void parseFilePath_validValueWithWhitespace_returnsTrimmedString() throws Exception {
        String filePathWithWhitespace = WHITESPACE + System.getProperty("user.home") + WHITESPACE;
        Optional<String> filePathOpt = Optional.of(filePathWithWhitespace);
        String expectedFilePath = System.getProperty("user.home");
        assertEquals(expectedFilePath, ParserUtil.parseFilePath(filePathOpt));
    }
}
