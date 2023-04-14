package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DOCTOR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DOCTOR_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DRUG_ALLERGY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DRUG_ALLERGY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOCTOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DRUG_ALLERGY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NULL_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.NULL_STRING;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRUG_ALLERGY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_OSTEOPOROTIC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_DIABETIC).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NRIC_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple dates of birth - last date accepted
        assertParseSuccess(parser, NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_AMY
                + DATE_DESC_AMY + DATE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_AMY
                + DATE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB
                + DATE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB
                + DATE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple Doctors - last doctor accepted
        assertParseSuccess(parser, NRIC_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB
                + DATE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB
                + GENDER_DESC_BOB + DOCTOR_DESC_AMY + DOCTOR_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_DIABETIC, VALID_TAG_OSTEOPOROTIC)
            .build();
        assertParseSuccess(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + TAG_DESC_HUSBAND
                + GENDER_DESC_BOB + DOCTOR_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void nullStringEmail_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + NULL_EMAIL + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + NULL_STRING + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NRIC_DESC_AMY + NAME_DESC_AMY
                        + DATE_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + DRUG_ALLERGY_DESC_AMY + GENDER_DESC_AMY + DOCTOR_DESC_AMY,
            new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + DATE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing DOB prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_DATE_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + DATE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB
                + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        //missing drug allergy prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + VALID_DRUG_ALLERGY_BOB + GENDER_DESC_BOB, expectedMessage);

        //missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DATE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + DRUG_ALLERGY_DESC_BOB + VALID_GENDER_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + DATE_DESC_BOB
                        + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid nric
        assertParseFailure(parser, INVALID_NRIC_DESC + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Nric.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, NRIC_DESC_BOB + INVALID_NAME_DESC
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + INVALID_DATE_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, DateOfBirth.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid drug allergy
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_DRUG_ALLERGY_DESC + GENDER_DESC_BOB + DOCTOR_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, DrugAllergy.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + INVALID_GENDER_DESC + DOCTOR_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Gender.MESSAGE_CONSTRAINTS);

        // multiple gender
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + INVALID_GENDER_DESC_2 + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND + DOCTOR_DESC_BOB, Gender.MESSAGE_CONSTRAINTS);

        // invalid doctor
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + INVALID_DOCTOR_DESC + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Doctor.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NRIC_DESC_BOB + NAME_DESC_BOB
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB + INVALID_TAG_DESC
                + VALID_TAG_DIABETIC, Tag.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, NRIC_DESC_BOB + INVALID_NAME_DESC
                + DATE_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NRIC_DESC_BOB
                + DATE_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + DRUG_ALLERGY_DESC_BOB + GENDER_DESC_BOB + DOCTOR_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
