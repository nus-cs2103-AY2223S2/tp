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
import seedu.vms.model.patient.Allergy;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Phone;
import seedu.vms.model.patient.Vaccine;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_DOB = " ";
    private static final String INVALID_BLOODTYPE = "example.com";
    private static final String INVALID_ALLERGY = "#potato";
    private static final String INVALID_VACCINE = "#ameravax";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_DOB = "2001-02-23";
    private static final String VALID_BLOODTYPE = "A+";
    private static final String VALID_ALLERGY_1 = "seafood";
    private static final String VALID_ALLERGY_2 = "gluten";
    private static final String VALID_VACCINE_1 = "moderna";
    private static final String VALID_VACCINE_2 = "chinavax";

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
        String addressWithWhitespace = WHITESPACE + VALID_DOB + WHITESPACE;
        Dob expectedDob = new Dob(VALID_DOB);
        assertEquals(expectedDob, ParserUtil.parseDob(addressWithWhitespace));
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
        String emailWithWhitespace = WHITESPACE + VALID_BLOODTYPE + WHITESPACE;
        BloodType expectedBloodType = new BloodType(VALID_BLOODTYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(emailWithWhitespace));
    }

    @Test
    public void parseAllergy_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAllergy(null));
    }

    @Test
    public void parseAllergy_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAllergy(INVALID_ALLERGY));
    }

    @Test
    public void parseAllergy_validValueWithoutWhitespace_returnsAllergy() throws Exception {
        Allergy expectedAllergy = new Allergy(VALID_ALLERGY_1);
        assertEquals(expectedAllergy, ParserUtil.parseAllergy(VALID_ALLERGY_1));
    }

    @Test
    public void parseAllergy_validValueWithWhitespace_returnsTrimmedAllergy() throws Exception {
        String allergyWithWhitespace = WHITESPACE + VALID_ALLERGY_1 + WHITESPACE;
        Allergy expectedAllergy = new Allergy(VALID_ALLERGY_1);
        assertEquals(expectedAllergy, ParserUtil.parseAllergy(allergyWithWhitespace));
    }

    @Test
    public void parseAllergies_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAllergies(null));
    }

    @Test
    public void parseAllergies_collectionWithInvalidAllergies_throwsParseException() {
        assertThrows(ParseException.class,
                () -> ParserUtil.parseAllergies(Arrays.asList(VALID_ALLERGY_1, INVALID_ALLERGY)));
    }

    @Test
    public void parseAllergies_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseAllergies(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseAllergies_collectionWithValidAllergies_returnsAllergySet() throws Exception {
        Set<Allergy> actualAllergySet = ParserUtil.parseAllergies(Arrays.asList(VALID_ALLERGY_1, VALID_ALLERGY_2));
        Set<Allergy> expectedAllergySet = new HashSet<Allergy>(
                Arrays.asList(new Allergy(VALID_ALLERGY_1), new Allergy(VALID_ALLERGY_2)));

        assertEquals(expectedAllergySet, actualAllergySet);
    }

    @Test
    public void parseVaccine_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVaccine(null));
    }

    @Test
    public void parseVaccine_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseVaccine(INVALID_VACCINE));
    }

    @Test
    public void parseVaccine_validValueWithoutWhitespace_returnsVaccine() throws Exception {
        Vaccine expectedVaccine = new Vaccine(VALID_VACCINE_1);
        assertEquals(expectedVaccine, ParserUtil.parseVaccine(VALID_VACCINE_1));
    }

    @Test
    public void parseVaccine_validValueWithWhitespace_returnsTrimmedVaccine() throws Exception {
        String vaccineWithWhitespace = WHITESPACE + VALID_VACCINE_1 + WHITESPACE;
        Vaccine expectedVaccine = new Vaccine(VALID_VACCINE_1);
        assertEquals(expectedVaccine, ParserUtil.parseVaccine(vaccineWithWhitespace));
    }

    @Test
    public void parseVaccines_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseVaccines(null));
    }

    @Test
    public void parseVaccines_collectionWithInvalidVaccines_throwsParseException() {
        assertThrows(ParseException.class,
                () -> ParserUtil.parseVaccines(Arrays.asList(VALID_VACCINE_1, INVALID_VACCINE)));
    }

    @Test
    public void parseVaccines_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseVaccines(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseVaccines_collectionWithValidVaccines_returnsVaccineSet() throws Exception {
        Set<Vaccine> actualVaccineSet = ParserUtil.parseVaccines(Arrays.asList(VALID_VACCINE_1, VALID_VACCINE_2));
        Set<Vaccine> expectedVaccineSet = new HashSet<Vaccine>(
                Arrays.asList(new Vaccine(VALID_VACCINE_1), new Vaccine(VALID_VACCINE_2)));

        assertEquals(expectedVaccineSet, actualVaccineSet);
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
}
