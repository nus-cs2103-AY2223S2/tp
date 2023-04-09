package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.model.timetable.util.TypicalTime.EIGHT_AM;
import static seedu.address.model.timetable.util.TypicalTime.EIGHT_PM;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.NINE_AM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Station;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.time.Day;

public class ParserUtilTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_STATION = "Bodok North";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM = "hellothere";
    private static final String INVALID_GROUP = "#friend";
    private static final String INVALID_MODULE = "CS50";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_STATION = "Rochor";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TELEGRAM = "@rachelwalker";
    private static final String VALID_GROUP_1 = "friend";
    private static final String VALID_GROUP_2 = "neighbour";
    private static final String VALID_MODULE_1 = "CS2103T";
    private static final String VALID_MODULE_2 = "CFG2002";
    private static final String VALID_LESSON_INFO_1 = "THURSDAY 9 10";
    private static final String VALID_LESSON_INFO_2 = "FRIDAY 8 20";
    private static final String VALID_NONBASIC_MODULE_1 = VALID_MODULE_1 + " " + VALID_LESSON_INFO_1;
    private static final String VALID_NONBASIC_MODULE_2 = VALID_MODULE_2 + " " + VALID_LESSON_INFO_2;

    private static final String WHITESPACE = " \t\r\n";

    private static final Lesson VALID_LESSON_1 =
            new Lesson(VALID_MODULE_1, NINE_AM, TEN_AM, Day.THURSDAY, Location.NUS);
    private static final Lesson VALID_LESSON_2 =
            new Lesson(VALID_MODULE_2, EIGHT_AM, EIGHT_PM, Day.FRIDAY, Location.NUS);

    private static final ModuleTag VALID_MODULE_TAG_1 =
            new ModuleTag(VALID_MODULE_1, VALID_LESSON_1);
    private static final ModuleTag VALID_MODULE_TAG_2 =
            new ModuleTag(VALID_MODULE_2, VALID_LESSON_2);

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
    public void parseIndex_parseNegative_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactIndex("-1"));
    }

    @Test
    public void parseIndex_parseZero_success() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactIndex("0"));
    }

    @Test
    public void parseIndex_parsePositive_success() throws Exception {
        assertEquals(ParserUtil.parseContactIndex("1"), new ContactIndex(1));
    }

    @Test
    public void parseInt_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInt("10 a"));
    }

    @Test
    public void parseInt_pureInvalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInt("a"));
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
    public void parseStation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStation((String) null));
    }

    @Test
    public void parseStation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStation(INVALID_STATION));
        assertThrows(ParseException.class, () -> ParserUtil.parseStation("NUS"));
    }

    @Test
    public void parseStation_validValue_returnsStation() throws Exception {
        Station expectedStation = new Station(VALID_STATION);
        assertEquals(expectedStation, ParserUtil.parseStation(VALID_STATION));

        // Partial matches another
        expectedStation = new Station("Bedok");
        assertEquals(expectedStation, ParserUtil.parseStation("Bedok"));
    }

    @Test
    public void parseStation_validValueWithWhitespace_returnsTrimmedStation() throws Exception {
        String stationWithWhitespace = WHITESPACE + VALID_STATION + WHITESPACE;
        Station expectedStation = new Station(VALID_STATION);
        assertEquals(expectedStation, ParserUtil.parseStation(stationWithWhitespace));
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
    public void parseModuleTag_validBasic_returnsTag() throws Exception {
        ModuleTag expectedModuleTag = new ModuleTag(VALID_MODULE_1);
        assertEquals(expectedModuleTag, ParserUtil.parseModuleTag(VALID_MODULE_1));
    }

    @Test
    public void parseModuleTag_validBasicWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_MODULE_1 + WHITESPACE;
        ModuleTag expectedModuleTag = new ModuleTag(VALID_MODULE_1);
        assertEquals(expectedModuleTag, ParserUtil.parseModuleTag(tagWithWhitespace));
    }

    @Test
    public void parseModuleTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModuleTags(null));
    }

    @Test
    public void parseModuleTags_invalidBasic_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(INVALID_MODULE));
    }

    @Test
    public void parseModuleTags_collectionInvalidBasic_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTags(
                Arrays.asList(VALID_MODULE_1, INVALID_MODULE)));
    }

    @Test
    public void parseModuleTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseModuleTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseModuleTags_collectionWithValidBasic_returnsTagSet() throws Exception {
        Set<ModuleTag> actualModuleTagSet = ParserUtil.parseModuleTags(Arrays.asList(VALID_MODULE_1, VALID_MODULE_2));
        Set<ModuleTag> expectedModuleTagSet = new HashSet<ModuleTag>(Arrays.asList(new ModuleTag(VALID_MODULE_1),
                new ModuleTag(VALID_MODULE_2)));

        assertEquals(expectedModuleTagSet, actualModuleTagSet);
    }

    @Test
    public void parseModuleTag_validNonBasic_returnsTag() throws Exception {
        ModuleTag actual = ParserUtil.parseModuleTag(VALID_NONBASIC_MODULE_1);
        assertEquals(VALID_MODULE_TAG_1, actual);

        actual = ParserUtil.parseModuleTag(VALID_NONBASIC_MODULE_2);
        assertEquals(VALID_MODULE_TAG_2, actual);
    }

    @Test
    public void parseModuleTag_validEarlyStartTime_returnsTag() throws Exception {
        String userInput = VALID_MODULE_1 + " THURSDAY 8 10";
        ModuleTag actual = ParserUtil.parseModuleTag(userInput);

        Lesson expectedLesson = new Lesson(VALID_MODULE_1, EIGHT_AM, TEN_AM, Day.THURSDAY, Location.NUS);
        ModuleTag expected = new ModuleTag(VALID_MODULE_1, expectedLesson);

        assertEquals(expected, actual);
    }

    @Test
    public void parseModuleTag_validLateEndTime_returnsTag() throws Exception {
        String userInput = VALID_MODULE_1 + " THURSDAY 9 23";
        ModuleTag actual = ParserUtil.parseModuleTag(userInput);

        Lesson expectedLesson = new Lesson(VALID_MODULE_1, NINE_AM, ELEVEN_PM, Day.THURSDAY, Location.NUS);
        ModuleTag expected = new ModuleTag(VALID_MODULE_1, expectedLesson);

        assertEquals(expected, actual);
    }

    @Test
    public void parseModuleTag_validDayShortForm_returnsTag() throws Exception {
        String userInput = VALID_MODULE_1 + " THURS 9 10";
        ModuleTag actual = ParserUtil.parseModuleTag(userInput);

        assertEquals(VALID_MODULE_TAG_1, actual);
    }

    @Test
    public void parseModuleTag_validDayLowerCase_returnsTag() throws Exception {
        String userInput = VALID_MODULE_1 + " thursday 9 10";
        ModuleTag actual = ParserUtil.parseModuleTag(userInput);

        assertEquals(VALID_MODULE_TAG_1, actual);
    }

    @Test
    public void parseModuleTag_validDayPartialLowerCase_returnsTag() throws Exception {
        String userInput = VALID_MODULE_1 + " ThUrSdAy 9 10";
        ModuleTag actual = ParserUtil.parseModuleTag(userInput);

        assertEquals(VALID_MODULE_TAG_1, actual);
    }

    @Test
    public void parseModuleTag_validExtraWhitespace_returnsTag() throws Exception {
        String userInput = WHITESPACE + VALID_MODULE_1
                + WHITESPACE + "THURSDAY"
                + WHITESPACE + "9"
                + WHITESPACE + "10"
                + WHITESPACE;
        ModuleTag actual = ParserUtil.parseModuleTag(userInput);

        assertEquals(VALID_MODULE_TAG_1, actual);
    }

    @Test
    public void parseModuleTags_validBasicNonBasic_returnsTagSet() throws Exception {
        Set<ModuleTag> actual = ParserUtil.parseModuleTags(Set.of(VALID_NONBASIC_MODULE_1, VALID_MODULE_2));
        Set<ModuleTag> expected = Set.of(VALID_MODULE_TAG_1, new ModuleTag(VALID_MODULE_2));

        assertEquals(expected, actual);
    }

    @Test
    public void parseModuleTags_validNonBasicNonBasic_returnsTagSet() throws Exception {
        Set<ModuleTag> actual = ParserUtil.parseModuleTags(Set.of(VALID_NONBASIC_MODULE_1, VALID_NONBASIC_MODULE_2));
        Set<ModuleTag> expected = Set.of(VALID_MODULE_TAG_1, VALID_MODULE_TAG_2);

        assertEquals(expected, actual);
    }

    @Test
    public void parseModuleTags_duplicateBasicNonBasic_returnsTagSet() throws Exception {
        Set<ModuleTag> actual = ParserUtil.parseModuleTags(Set.of(VALID_NONBASIC_MODULE_1, VALID_MODULE_1));
        Set<ModuleTag> expected = Set.of(VALID_MODULE_TAG_1, new ModuleTag(VALID_MODULE_1));

        assertEquals(expected, actual);
    }

    @Test
    public void parseModuleTags_multipleLessonsSameModuleCode_returnsTagSet() throws Exception {
        // supposed to merge
        String otherValidNonBasicModuleTagString = VALID_MODULE_1 + " FRIDAY 9 10";
        Lesson lesson = new Lesson(VALID_MODULE_1, NINE_AM, TEN_AM, Day.FRIDAY, Location.NUS);
        ModuleTag otherValidNonBasicModuleTag = new ModuleTag(VALID_MODULE_1, lesson);

        Set<ModuleTag> actual =
                ParserUtil.parseModuleTags(Set.of(VALID_NONBASIC_MODULE_1, otherValidNonBasicModuleTagString));
        Set<ModuleTag> expected =
                Set.of(new ModuleTag(VALID_MODULE_1, lesson, VALID_LESSON_1));

        assertTrue(actual.contains(otherValidNonBasicModuleTag));
        assertTrue(actual.contains(VALID_MODULE_TAG_1));

        assertFalse(actual.contains(VALID_MODULE_TAG_1.mergeWith(otherValidNonBasicModuleTag)));
        assertNotEquals(actual, expected);
    }

    @Test
    public void parseModuleTag_invalidNonBasicModuleCode_throwsParseException() {
        String userInput = INVALID_MODULE + VALID_LESSON_INFO_1;
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidSpaces_throwsParseException() {
        String userInput = "CS2103T THURSDAY 9 10 11";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidStartTimeNonInt_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY a 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidEndTimeNonInt_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY 7 b";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidStartTimeNegative_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY -1 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidEndTimeNegative_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY 8 -1";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidStartTimeEarly_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY 7 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidStartTimeLate_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY 23 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidEndTimeEarly_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY 9 8";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidEndTimeLate_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY 8 24";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_endTimeBeforeStartTime_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY 10 9";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_startTimeSameAsEndTime_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY 10 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_invalidDay_throwsParseException() {
        String userInput = VALID_MODULE_1 + " SUNDAY 8 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_ambiguousDay_throwsParseException() {
        // Can be either thurs or tues
        String userInput = VALID_MODULE_1 + " T 8 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_dayMissing_throwsParseException() {
        String userInput = VALID_MODULE_1 + "  8 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_startTimeMissing_throwsParseException() {
        String userInput = VALID_MODULE_1 + " THURSDAY  10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTag_endTimeMissing_throwsParseException() {
        String userInput = VALID_MODULE_1 + " FREEDAY 8 ";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTag(userInput));
    }

    @Test
    public void parseModuleTags_collectionInvalidNonBasic_throwsParseException() {
        String invalidUserInput = VALID_MODULE_1 + " THURSDAY 8 100";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTags(
                List.of(invalidUserInput)));
    }

    @Test
    public void parseModuleTags_collectionInvalidMultipleNonBasic_throwsParseException() {
        String invalidUserInput = VALID_MODULE_1 + " THURSDAY 8 100";
        String invalidUserInput2 = VALID_MODULE_2 + " THURSDAY 11 10";
        assertThrows(ParseException.class, () -> ParserUtil.parseModuleTags(
                List.of(invalidUserInput, invalidUserInput2)));
    }
}
