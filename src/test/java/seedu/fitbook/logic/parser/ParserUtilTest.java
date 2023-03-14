package seedu.fitbook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fitbook.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.parser.exceptions.ParseException;
import seedu.fitbook.model.client.Address;
import seedu.fitbook.model.client.Appointment;
import seedu.fitbook.model.client.Calorie;
import seedu.fitbook.model.client.Email;
import seedu.fitbook.model.client.Gender;
import seedu.fitbook.model.client.Name;
import seedu.fitbook.model.client.Phone;
import seedu.fitbook.model.client.Weight;
import seedu.fitbook.model.routines.Exercise;
import seedu.fitbook.model.routines.RoutineName;
import seedu.fitbook.model.tag.Tag;

public class ParserUtilTest {
    //Client
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CALORIE = "012";

    private static final String INVALID_WEIGHT = "-23";
    private static final String INVALID_GENDER = "H";

    private static final String INVALID_APPOINTMENT = "235236";


    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_CALORIE = "2000";
    private static final String VALID_WEIGHT = "23";
    private static final String VALID_GENDER = "m";

    private static final String VALID_APPOINTMENT_1 = "10-10-2023";
    private static final String VALID_APPOINTMENT_2 = "11-12-2020";

    //Routine
    private static final String INVALID_ROUTINE = "@$!%%@";
    private static final String INVALID_EXERCISE = "%@!%#%";

    private static final String VALID_ROUTINE = "Cardio";
    private static final String VALID_EXERCISE_1 = "3x5 Jumping jacks";
    private static final String VALID_EXERCISE_2 = "4x5 Dumbbell curls";

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

    //Client
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
    public void parseCalorie_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCalorie((String) null));
    }

    @Test
    public void parseCalorie_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCalorie(INVALID_CALORIE));
    }

    @Test
    public void parseCalorie_validValueWithoutWhitespace_returnsCalorie() throws Exception {
        Calorie expectedCalorie = new Calorie(VALID_CALORIE);
        assertEquals(expectedCalorie, ParserUtil.parseCalorie(VALID_CALORIE));
    }

    @Test
    public void parseCalorie_validValueWithWhitespace_returnsTrimmedCalorie() throws Exception {
        String calorieWithWhitespace = WHITESPACE + VALID_CALORIE + WHITESPACE;
        Calorie expectedCalorie = new Calorie(VALID_CALORIE);
        assertEquals(expectedCalorie, ParserUtil.parseCalorie(calorieWithWhitespace));
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
    public void parseWeight_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWeight((String) null));
    }

    @Test
    public void parseWeight_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight(INVALID_WEIGHT));
    }

    @Test
    public void parseWeight_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Weight expectedWeight = new Weight(VALID_WEIGHT);
        assertEquals(expectedWeight, ParserUtil.parseWeight(VALID_WEIGHT));
    }

    @Test
    public void parseWeight_validValueWithWhitespace_returnsTrimmedWeight() throws Exception {
        String weightWithWhitespace = WHITESPACE + VALID_WEIGHT + WHITESPACE;
        Weight expectedWeight = new Weight(VALID_WEIGHT);
        assertEquals(expectedWeight, ParserUtil.parseWeight(weightWithWhitespace));
    }
    @Test
    public void parseGender_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGender((String) null));
    }

    @Test
    public void parseGender_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER));
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

    @Test
    public void parseAppointment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseAppointment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointment(INVALID_APPOINTMENT));
    }

    @Test
    public void parseAppointment_validValueWithoutWhitespace_returnsAppointment() throws Exception {
        Appointment expectedAppointment = new Appointment(VALID_APPOINTMENT_1);
        assertEquals(expectedAppointment, ParserUtil.parseAppointment(VALID_APPOINTMENT_1));
    }

    @Test
    public void parseAppointment_validValueWithWhitespace_returnsTrimmedAppointment() throws Exception {
        String appointmentWithWhitespace = WHITESPACE + VALID_APPOINTMENT_1 + WHITESPACE;
        Appointment expectedAppointment = new Appointment(VALID_APPOINTMENT_1);
        assertEquals(expectedAppointment, ParserUtil.parseAppointment(appointmentWithWhitespace));
    }

    @Test
    public void parseAppointments_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAppointments(null));
    }

    @Test
    public void parseAppointments_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAppointments(Arrays.asList(VALID_APPOINTMENT_1, INVALID_APPOINTMENT)));
    }

    @Test
    public void parseAppointments_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseAppointments(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseAppointments_collectionWithValidTags_returnsAppointmentSet() throws Exception {
        Set<Appointment> actualAppointmentSet =
                ParserUtil.parseAppointments(Arrays.asList(VALID_APPOINTMENT_1, VALID_APPOINTMENT_2));
        Set<Appointment> expectedAppointmentSet =
                new HashSet<Appointment>(Arrays.asList(new Appointment(VALID_APPOINTMENT_1),
                        new Appointment(VALID_APPOINTMENT_2)));

        assertEquals(actualAppointmentSet, expectedAppointmentSet);
    }

    //Routine

    @Test
    public void parseRoutineName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRoutineName((String) null));
    }

    @Test
    public void parseRoutineName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRoutineName(INVALID_ROUTINE));
    }

    @Test
    public void parseRoutineName_validValueWithoutWhitespace_returnsName() throws Exception {
        RoutineName expectedRoutineName = new RoutineName(VALID_ROUTINE);
        assertEquals(expectedRoutineName, ParserUtil.parseRoutineName(VALID_ROUTINE));
    }

    @Test
    public void parseRoutineName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String routineNameWithWhitespace = WHITESPACE + VALID_ROUTINE + WHITESPACE;
        RoutineName expectedRoutineName = new RoutineName(VALID_ROUTINE);
        assertEquals(expectedRoutineName, ParserUtil.parseRoutineName(routineNameWithWhitespace));
    }

    @Test
    public void parseExercise_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExercise(null));
    }

    @Test
    public void parseExercise_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExercise(INVALID_EXERCISE));
    }

    @Test
    public void parseExercise_validValueWithoutWhitespace_returnsAppointment() throws Exception {
        Exercise expectedExercise = new Exercise(VALID_EXERCISE_1);
        assertEquals(expectedExercise, ParserUtil.parseExercise(VALID_EXERCISE_1));
    }

    @Test
    public void parseExercise_validValueWithWhitespace_returnsTrimmedAppointment() throws Exception {
        String exerciseWithWhitespace = WHITESPACE + VALID_EXERCISE_1 + WHITESPACE;
        Exercise expectedExercise = new Exercise(VALID_EXERCISE_1);
        assertEquals(expectedExercise, ParserUtil.parseExercise(exerciseWithWhitespace));
    }

    @Test
    public void parseExercises_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExercises(null));
    }

    @Test
    public void parseExercises_collectionWithInvalidExercises_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseExercises(Arrays.asList(VALID_EXERCISE_1, INVALID_EXERCISE)));
    }

    @Test
    public void parseExercises_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseExercises(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseExercises_collectionWithValidExercises_returnsExerciseList() throws Exception {
        List<Exercise> actualExerciseList =
                ParserUtil.parseExercises(Arrays.asList(VALID_EXERCISE_1, VALID_EXERCISE_2));
        List<Exercise> expectedExerciseList =
                new ArrayList<>(Arrays.asList(new Exercise(VALID_EXERCISE_1),
                        new Exercise(VALID_EXERCISE_2)));

        assertEquals(actualExerciseList, expectedExerciseList);
    }
}
