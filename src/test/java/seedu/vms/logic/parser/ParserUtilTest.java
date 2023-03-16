package seedu.vms.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.vms.testutil.Assert.assertThrows;
import static seedu.vms.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_DOB = " ";
    private static final String INVALID_BLOODTYPE = "example.com";
    private static final String INVALID_GROUPNAME = "#potato";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_DOB = "2001-02-23";
    private static final String VALID_BLOODTYPE = "A+";
    private static final String VALID_GROUPNAME_1 = "moderna";
    private static final String VALID_GROUPNAME_2 = "chinavax";

    private static final String WHITESPACE = " \t\r\n";

    private static final LocalDateTime EXPECTED_DATE = LocalDateTime.of(2023, 3, 5, 4, 55);
    private static final LocalDateTime EXPECTED_DATE_ONLY = LocalDateTime.of(2023, 3, 5, 0, 0);

    private static final String VALID_DEFAULT_DATE = "2023-03-05T04:55";
    private static final String VALID_FULL_DATE = "2023-03-05 0455";
    private static final String VALID_DATE_ONLY = "2023-03-05";

    private static final String INVALID_DATE_RUBBISH = "RuBbisH";
    private static final String INVALID_DATE_MIN_OUT = "2023-03-05T04:99";
    private static final String INVALID_DATE_HRS_OUT = "2023-03-05T99:55";
    private static final String INVALID_DATE_DAY_OUT = "2023-03-99T04:55";
    private static final String INVALID_DATE_MTH_OUT = "2023-99-99T04:55";
    private static final String INVALID_DATE_ONLY_MTH_OUT = "2023-99-99";

    private static final List<String> EXPECTED_PARSED_LIST = List.of("UNCHI", "BANANA", "APPLE");

    private static final List<String> VALID_LIST_SYNTAXES = List.of(
            "UNCHI, BANANA, APPLE",
            "UNCHI,BANANA,APPLE",
            " UNCHI ,     BANANA,     APPLE     ");
    private static final List<String> VALID_PART_SYNTAXES = List.of(
            "UNCHI:: BANANA:: APPLE",
            "UNCHI::BANANA::APPLE",
            " UNCHI ::     BANANA::     APPLE     ");

    private static final List<String> INVALID_LIST_SYNTAXES = List.of(
            "",
            ",",
            ", UNCHI",
            "UNCHI,",
            ",,",
            ",UNCHI,BANANA,");
    private static final List<String> INVALID_PART_SYNTAXES = List.of(
            "",
            "::",
            ":: UNCHI",
            "UNCHI::",
            "::::",
            "::UNCHI,BANANA::");

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX,
                () -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("  1  "));
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
    public void parseDob_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDob((String) null));
    }

    @Test
    public void parseDob_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDob(INVALID_DOB));
    }

    @Test
    public void parseDob_validValueWithoutWhitespace_returnsDob() throws Exception {
        Dob expectedDob = new Dob(VALID_DOB);
        assertEquals(expectedDob, ParserUtil.parseDob(VALID_DOB));
    }

    @Test
    public void parseDob_validValueWithWhitespace_returnsTrimmedDob() throws Exception {
        String dobWithWhitespace = WHITESPACE + VALID_DOB + WHITESPACE;
        Dob expectedDob = new Dob(VALID_DOB);
        assertEquals(expectedDob, ParserUtil.parseDob(dobWithWhitespace));
    }

    @Test
    public void parseBloodType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBloodType((String) null));
    }

    @Test
    public void parseBloodType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBloodType(INVALID_BLOODTYPE));
    }

    @Test
    public void parseBloodType_validValueWithoutWhitespace_returnsBloodType() throws Exception {
        BloodType expectedBloodType = new BloodType(VALID_BLOODTYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(VALID_BLOODTYPE));
    }

    @Test
    public void parseBloodType_validValueWithWhitespace_returnsTrimmedBloodType() throws Exception {
        String bloodTypeWithWhitespace = WHITESPACE + VALID_BLOODTYPE + WHITESPACE;
        BloodType expectedBloodType = new BloodType(VALID_BLOODTYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(bloodTypeWithWhitespace));
    }

    @Test
    public void parseDate_validDefaultDate_returnDate() throws Exception {
        LocalDateTime actualDate = ParserUtil.parseDate(VALID_DEFAULT_DATE);
        assertEquals(EXPECTED_DATE, actualDate);
    }

    @Test
    public void parseDate_validFullDate_returnDate() throws Exception {
        assertEquals(EXPECTED_DATE, ParserUtil.parseDate(VALID_FULL_DATE));
    }

    @Test
    public void parseDate_validDateOnly_returnDate() throws Exception {
        assertEquals(EXPECTED_DATE_ONLY, ParserUtil.parseDate(VALID_DATE_ONLY));
    }

    @Test
    public void parseDate_rubbishDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_RUBBISH));
    }

    @Test
    public void parseDate_minutesOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_MIN_OUT));
    }

    @Test
    public void parseDate_hoursOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_HRS_OUT));
    }

    @Test
    public void parseDate_dayOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_DAY_OUT));
    }

    @Test
    public void parseDate_monthOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_MTH_OUT));
    }

    @Test
    public void parseDateOnly_monthOutOfRange_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE_ONLY_MTH_OUT));
    }

    @Test
    public void parseList_validList_listParsed() throws Exception {
        for (String listString : VALID_LIST_SYNTAXES) {
            assertEquals(EXPECTED_PARSED_LIST, ParserUtil.parseList(listString));
        }
    }

    @Test
    public void parseParts_validParts_partsParsed() throws Exception {
        for (String partString : VALID_PART_SYNTAXES) {
            assertEquals(EXPECTED_PARSED_LIST, ParserUtil.parseParts(partString));
        }
    }

    @Test
    public void parseList_invalidList_exceptionThrown() {
        for (String syntax : INVALID_LIST_SYNTAXES) {
            assertThrows(ParseException.class, () -> ParserUtil.parseList(syntax));
        }
    }

    @Test
    public void parseParts_invalidParts_exceptionThrown() {
        for (String syntax : INVALID_PART_SYNTAXES) {
            assertThrows(ParseException.class, () -> ParserUtil.parseParts(syntax));
        }
    }

    @Test
    public void parseInteger_valid_integerParsed() throws Exception {
        // small
        assertEquals(-2147483648, ParserUtil.parseInteger("-2147483648"));
        // big
        assertEquals(2147483647, ParserUtil.parseInteger("2147483647"));
    }

    @Test
    public void parseInteger_invalid_exceptionThrown() {
        // too small
        assertThrows(ParseException.class, () -> ParserUtil.parseInteger("-2147483649"));
        // too big
        assertThrows(ParseException.class, () -> ParserUtil.parseInteger("2147483648"));
        // empty
        assertThrows(ParseException.class, () -> ParserUtil.parseInteger(""));
        // blank
        assertThrows(ParseException.class, () -> ParserUtil.parseInteger(" "));
        // not a number
        assertThrows(ParseException.class, () -> ParserUtil.parseInteger("hanya??"));
    }

    @Test
    public void parseGroupName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupName(null));
    }

    @Test
    public void parseGroupName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroups(INVALID_GROUPNAME));
    }

    @Test
    public void parseGroupName_validValueWithoutWhitespace_returnsGroupName() throws Exception {
        GroupName expectedGroupName = new GroupName(VALID_GROUPNAME_1);
        assertEquals(expectedGroupName, ParserUtil.parseGroupName(VALID_GROUPNAME_1));
    }

    @Test
    public void parseGroupName_validValueWithWhitespace_returnsTrimmedGroupName() throws Exception {
        String groupNameWithWhitespace = WHITESPACE + VALID_GROUPNAME_1 + WHITESPACE;
        GroupName expectedGroupName = new GroupName(VALID_GROUPNAME_1);
        assertEquals(expectedGroupName, ParserUtil.parseGroupName(groupNameWithWhitespace));
    }

    @Test
    public void parseGroupNames_collectionWithInvalidGroupNames_throwsParseException() {
        assertThrows(ParseException.class,
                () -> ParserUtil.parseGroups(Arrays.asList(VALID_GROUPNAME_1, INVALID_GROUPNAME)));
    }

    @Test
    public void parseGroupNames_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseGroups(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseGroupNames_collectionWithValidGroupNames_returnsGroupNameSet() throws Exception {
        Set<GroupName> actualGroupNameSet = ParserUtil.parseGroups(Arrays.asList(VALID_GROUPNAME_1, VALID_GROUPNAME_2));
        Set<GroupName> expectedGroupNameSet = new HashSet<GroupName>(
                Arrays.asList(new GroupName(VALID_GROUPNAME_1), new GroupName(VALID_GROUPNAME_2)));

        assertEquals(expectedGroupNameSet, actualGroupNameSet);
    }

}
