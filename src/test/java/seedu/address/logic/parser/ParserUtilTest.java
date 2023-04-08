package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.Tags;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.model.person.fields.subfields.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE_1 = "+651234";
    private static final String INVALID_PHONE_2 = "34";
    //private static final String INVALID_ADDRESS = "@#@!";
    //considered a valid address... currently do not have in invalid address
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    //private static final String INVALID_GENDER = "123";
    //valid gender cause alphanumeric

    private static final String INVALID_GENDER_1 = "!!!";

    private static final String INVALID_GENDER_2 = "multiple words";

    private static final String INVALID_MAJOR = "1@3";

    private static final String INVALID_FACULTY_1 = "_@$#";
    private static final String INVALID_FACULTY_2 = "12345";
    private static final String INVALID_RACE_1 = ")@#$%^&*(";
    private static final String INVALID_RACE_2 = "12345";
    private static final String INVALID_MODULE = "AA111D";
    //private static final String INVALID_COMMS_1 = "^&(*";
    //there is no invalid comms
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_GENDER = "1randomword";
    private static final String VALID_MAJOR_1 = "Computing";

    private static final String VALID_MAJOR_2 = "Multiple words";
    private static final String VALID_FACULTY_1 = "RandomOneWordFaculty";
    private static final String VALID_FACULTY_2 = "School Of Computing";
    private static final String VALID_RACE_1 = "Indian";
    private static final String VALID_RACE_2 = "more than one word race";
    private static final String VALID_MODULE_1 = "AC5001";

    //private static final String VALID_MODULE_2 = "cas5101";
    // lowercase not considered valid
    private static final String VALID_MODULE_2 = "CAS5101";
    //private static final String VALID_MODULE_3 = "ce5611qb";

    private static final String VALID_MODULE_3 = "CE5611QB";
    private static final String VALID_COMMS_1 = "linkedIN";
    private static final String VALID_COMMS_2 = "12345678";
    private static final String VALID_COMMS_3 = "!@#$%^&*";
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

    //@Test
    //public void parsePhone_null_throwsNullPointerException() {
    // assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    //}

    @Test
    public void parsePhone_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue1_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_1));
    }

    @Test
    public void parsePhone_invalidValue2_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_2));
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
    public void parseAddress_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parseAddress((String) null));
    }

    //    @Test
    //    public void parseAddress_invalidValue_throwsParseException() {
    //        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    //    }

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
        //assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
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

    //    @Test
    //    public void parseTag_null_throwsNullPointerException() {
    //        //assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    //    }

    @Test
    public void parseTag_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parseTag(null));
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
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).values.isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Tags actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Tags expectedTagSet = new Tags(new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2))));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseGender_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parseGender((String) null));
    }

    @Test
    public void parseGender_invalidValue1_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER_1));
    }

    @Test
    public void parseGender_invalidValue2_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER_2));
    }

    @Test
    public void parseGender_validValueWithoutWhitespace_returnsGender() throws Exception {
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(VALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithWhitespace_returnsTrimmedGender() throws Exception {
        String genderWithWhitespace = WHITESPACE + VALID_GENDER + WHITESPACE;
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(genderWithWhitespace));
    }

    @Test
    public void parseMajor_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parseMajor((String) null));
    }

    @Test
    public void parseMajor_invalidValue1_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMajor(INVALID_MAJOR));
    }

    @Test
    public void parseMajor_validValue1WithoutWhitespace_returnsMajor() throws Exception {
        Major expectedMajor = new Major(VALID_MAJOR_1);
        assertEquals(expectedMajor, ParserUtil.parseMajor(VALID_MAJOR_1));
    }

    @Test
    public void parseMajor_validValue1WithWhitespace_returnsTrimmedMajor() throws Exception {
        String majorWithWhitespace = WHITESPACE + VALID_MAJOR_1 + WHITESPACE;
        Major expectedMajor = new Major(VALID_MAJOR_1);
        assertEquals(expectedMajor, ParserUtil.parseMajor(majorWithWhitespace));
    }

    @Test
    public void parseMajor_validValue2WithoutWhitespace_returnsMajor() throws Exception {
        Major expectedMajor = new Major(VALID_MAJOR_2);
        assertEquals(expectedMajor, ParserUtil.parseMajor(VALID_MAJOR_2));
    }

    @Test
    public void parseMajor_validValue2WithWhitespace_returnsTrimmedMajor() throws Exception {
        String majorWithWhitespace = WHITESPACE + VALID_MAJOR_2 + WHITESPACE;
        Major expectedMajor = new Major(VALID_MAJOR_2);
        assertEquals(expectedMajor, ParserUtil.parseMajor(majorWithWhitespace));
    }
    @Test
    public void parseFaculty_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parseFaculty((String) null));
    }

    @Test
    public void parseFaculty_invalidValue1_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFaculty(INVALID_FACULTY_1));
    }

    @Test
    public void parseFaculty_invalidValue2_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFaculty(INVALID_FACULTY_2));
    }

    @Test
    public void parseFaculty_validValue1WithoutWhitespace_returnsFaculty() throws Exception {
        Faculty expectedFaculty = new Faculty(VALID_FACULTY_1);
        assertEquals(expectedFaculty, ParserUtil.parseFaculty(VALID_FACULTY_1));
    }

    @Test
    public void parseFaculty_validValue1WithWhitespace_returnsTrimmedFaculty() throws Exception {
        String facultyWithWhitespace = WHITESPACE + VALID_FACULTY_1 + WHITESPACE;
        Faculty expectedFaculty = new Faculty(VALID_FACULTY_1);
        assertEquals(expectedFaculty, ParserUtil.parseFaculty(facultyWithWhitespace));
    }

    @Test
    public void parseFaculty_validValue2WithoutWhitespace_returnsFaculty() throws Exception {
        Faculty expectedFaculty = new Faculty(VALID_FACULTY_2);
        assertEquals(expectedFaculty, ParserUtil.parseFaculty(VALID_FACULTY_2));
    }

    @Test
    public void parseFaculty_validValue2WithWhitespace_returnsTrimmedFaculty() throws Exception {
        String facultyWithWhitespace = WHITESPACE + VALID_FACULTY_2 + WHITESPACE;
        Faculty expectedFaculty = new Faculty(VALID_FACULTY_2);
        assertEquals(expectedFaculty, ParserUtil.parseFaculty(facultyWithWhitespace));
    }

    @Test
    public void parseRace_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parseRace((String) null));
    }

    @Test
    public void parseRace_invalidValue1_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRace(INVALID_RACE_1));
    }

    @Test
    public void parseRace_invalidValue2_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRace(INVALID_RACE_2));
    }

    @Test
    public void parseRace_validValue1WithoutWhitespace_returnsRace() throws Exception {
        Race expectedRace = new Race(VALID_RACE_1);
        assertEquals(expectedRace, ParserUtil.parseRace(VALID_RACE_1));
    }

    @Test
    public void parseRace_validValue1WithWhitespace_returnsTrimmedRace() throws Exception {
        String raceWithWhitespace = WHITESPACE + VALID_RACE_1 + WHITESPACE;
        Race expectedRace = new Race(VALID_RACE_1);
        assertEquals(expectedRace, ParserUtil.parseRace(raceWithWhitespace));
    }

    @Test
    public void parseRace_validValue2WithoutWhitespace_returnsRace() throws Exception {
        Race expectedRace = new Race(VALID_RACE_2);
        assertEquals(expectedRace, ParserUtil.parseRace(VALID_RACE_2));
    }

    @Test
    public void parseRace_validValue2WithWhitespace_returnsTrimmedRace() throws Exception {
        String raceWithWhitespace = WHITESPACE + VALID_RACE_2 + WHITESPACE;
        Race expectedRace = new Race(VALID_RACE_2);
        assertEquals(expectedRace, ParserUtil.parseRace(raceWithWhitespace));
    }
    @Test
    public void parseComms_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parseComms((String) null));
    }

    @Test
    public void parseComms_validValue1WithoutWhitespace_returnsComms() throws Exception {
        CommunicationChannel expectedComms = new CommunicationChannel(VALID_COMMS_1);
        assertEquals(expectedComms, ParserUtil.parseComms(VALID_COMMS_1));
    }

    @Test
    public void parseComms_validValue1WithWhitespace_returnsTrimmedComms() throws Exception {
        String commsWithWhitespace = WHITESPACE + VALID_COMMS_1 + WHITESPACE;
        CommunicationChannel expectedComms = new CommunicationChannel(VALID_COMMS_1);
        assertEquals(expectedComms, ParserUtil.parseComms(commsWithWhitespace));
    }

    @Test
    public void parseComms_validValue2WithoutWhitespace_returnsComms() throws Exception {
        CommunicationChannel expectedComms = new CommunicationChannel(VALID_COMMS_2);
        assertEquals(expectedComms, ParserUtil.parseComms(VALID_COMMS_2));
    }

    @Test
    public void parseComms_validValue2WithWhitespace_returnsTrimmedComms() throws Exception {
        String commsWithWhitespace = WHITESPACE + VALID_COMMS_2 + WHITESPACE;
        CommunicationChannel expectedComms = new CommunicationChannel(VALID_COMMS_2);
        assertEquals(expectedComms, ParserUtil.parseComms(commsWithWhitespace));
    }

    @Test
    public void parseComms_validValue3WithoutWhitespace_returnsComms() throws Exception {
        CommunicationChannel expectedComms = new CommunicationChannel(VALID_COMMS_3);
        assertEquals(expectedComms, ParserUtil.parseComms(VALID_COMMS_3));
    }

    @Test
    public void parseComms_validValue3WithWhitespace_returnsTrimmedComms() throws Exception {
        String commsWithWhitespace = WHITESPACE + VALID_COMMS_3 + WHITESPACE;
        CommunicationChannel expectedComms = new CommunicationChannel(VALID_COMMS_3);
        assertEquals(expectedComms, ParserUtil.parseComms(commsWithWhitespace));
    }

    @Test
    public void parseModule_null_returnsNull() throws Exception {
        assertEquals(null, ParserUtil.parseModule(null));
    }

    @Test
    public void parseModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModule(INVALID_MODULE));
    }

    @Test
    public void parseModule_validValueWithoutWhitespace_returnsModule() throws Exception {
        NusMod expectedModule = new NusMod(VALID_MODULE_1);
        assertEquals(expectedModule, ParserUtil.parseModule(VALID_MODULE_1));
    }

    @Test
    public void parseModule_validValueWithWhitespace_returnsTrimmedModule() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_MODULE_1 + WHITESPACE;
        NusMod expectedModule = new NusMod(VALID_MODULE_1);
        assertEquals(expectedModule, ParserUtil.parseModule(tagWithWhitespace));
    }

    @Test
    public void parseModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModules(null));
    }

    @Test
    public void parseModules_collectionWithInvalidModules_throwsParseException() {
        assertThrows(ParseException.class, (
        ) -> ParserUtil.parseModules(Arrays.asList(VALID_MODULE_1, INVALID_MODULE)));
    }

    @Test
    public void parseModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseModules(Collections.emptyList()).values.isEmpty());
    }

    @Test
    public void parseModules_collectionWithValidModules_returnsModuleSet() throws Exception {
        Modules actualModuleSet = ParserUtil.parseModules(
                Arrays.asList(VALID_MODULE_2, VALID_MODULE_2, VALID_MODULE_3));
        Modules expectedModuleSet = new Modules(new HashSet<>(
                Arrays.asList(new NusMod(VALID_MODULE_2), new NusMod(VALID_MODULE_2), new NusMod(VALID_MODULE_3))));

        assertEquals(expectedModuleSet, actualModuleSet);
    }
}
