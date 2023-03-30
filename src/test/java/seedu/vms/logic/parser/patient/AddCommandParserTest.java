package seedu.vms.logic.parser.patient;

import static seedu.vms.commons.core.Messages.MESSAGE_MISSING_PARAMETER_FORMAT;
import static seedu.vms.logic.commands.CommandTestUtil.ALLERGY_DESC_GLUTEN;
import static seedu.vms.logic.commands.CommandTestUtil.ALLERGY_DESC_SEAFOOD;
import static seedu.vms.logic.commands.CommandTestUtil.BLOODTYPE_DESC_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.BLOODTYPE_DESC_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.DOB_DESC_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.DOB_DESC_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.INVALID_ALLERGY_DESC;
import static seedu.vms.logic.commands.CommandTestUtil.INVALID_BLOODTYPE_DESC;
import static seedu.vms.logic.commands.CommandTestUtil.INVALID_DOB_DESC;
import static seedu.vms.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.vms.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.vms.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_ALLERGY_GLUTEN;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_ALLERGY_SEAFOOD;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;
import static seedu.vms.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.vms.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.vms.testutil.TypicalPatients.AMY;
import static seedu.vms.testutil.TypicalPatients.BOB;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.patient.AddCommand;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.Phone;
import seedu.vms.testutil.PatientBuilder;
import seedu.vms.testutil.TestUtil;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).withAllergies(VALID_ALLERGY_GLUTEN).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + DOB_DESC_BOB
                + BLOODTYPE_DESC_BOB + ALLERGY_DESC_GLUTEN, new AddCommand(expectedPatient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + DOB_DESC_BOB
                + BLOODTYPE_DESC_BOB + ALLERGY_DESC_GLUTEN, new AddCommand(expectedPatient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + DOB_DESC_BOB
                + BLOODTYPE_DESC_BOB + ALLERGY_DESC_GLUTEN, new AddCommand(expectedPatient));

        // multiple dobs - last dob accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DOB_DESC_AMY + DOB_DESC_BOB
                + BLOODTYPE_DESC_BOB + ALLERGY_DESC_GLUTEN, new AddCommand(expectedPatient));

        // multiple bloodtypees - last bloodtype accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DOB_DESC_BOB + BLOODTYPE_DESC_AMY
                + BLOODTYPE_DESC_BOB + ALLERGY_DESC_GLUTEN, new AddCommand(expectedPatient));

        // multiple allergy - all accepted
        Patient expectedPatientMultipleAllergies = new PatientBuilder(BOB)
                .withAllergies(VALID_ALLERGY_GLUTEN, VALID_ALLERGY_SEAFOOD)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DOB_DESC_BOB + BLOODTYPE_DESC_BOB
                + ALLERGY_DESC_SEAFOOD + ALLERGY_DESC_GLUTEN,
                new AddCommand(expectedPatientMultipleAllergies));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero allergy
        Patient expectedPatient = new PatientBuilder(AMY).withAllergies().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + DOB_DESC_AMY + BLOODTYPE_DESC_AMY,
                new AddCommand(expectedPatient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_MISSING_PARAMETER_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + DOB_DESC_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + DOB_DESC_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing dob prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_BLOODTYPE_BOB + BLOODTYPE_DESC_BOB,
                expectedMessage);

        // missing bloodtype prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DOB_DESC_BOB + VALID_BLOODTYPE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_BLOODTYPE_BOB + VALID_BLOODTYPE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + DOB_DESC_BOB + BLOODTYPE_DESC_BOB
                + ALLERGY_DESC_SEAFOOD + ALLERGY_DESC_GLUTEN, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + DOB_DESC_BOB + BLOODTYPE_DESC_BOB
                + ALLERGY_DESC_SEAFOOD + ALLERGY_DESC_GLUTEN, Phone.MESSAGE_CONSTRAINTS);

        // invalid dob
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_DOB_DESC + BLOODTYPE_DESC_BOB
                + ALLERGY_DESC_SEAFOOD + ALLERGY_DESC_GLUTEN, Dob.MESSAGE_CONSTRAINTS);

        // invalid bloodtype
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DOB_DESC_BOB + INVALID_BLOODTYPE_DESC
                + ALLERGY_DESC_SEAFOOD + ALLERGY_DESC_GLUTEN, BloodType.MESSAGE_CONSTRAINTS);

        // invalid allergy
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + DOB_DESC_BOB + BLOODTYPE_DESC_BOB
                + INVALID_ALLERGY_DESC + VALID_ALLERGY_GLUTEN, GroupName.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + DOB_DESC_BOB + INVALID_BLOODTYPE_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }
    @Test
    public void execute_groupOverLimit_exceptionThrown() {
        HashSet<GroupName> allergyOverLimitSet = TestUtil.generateGroupSet(Patient.LIMIT_ALLERGIES + 1);
        Optional<String> allergyErrorMessage = Patient.validateParams(Optional.of(allergyOverLimitSet),
                Optional.empty());
        assertParseFailure(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + DOB_DESC_AMY + BLOODTYPE_DESC_AMY
                        + TestUtil.toParseStrings(allergyOverLimitSet, PREFIX_ALLERGY),
                allergyErrorMessage.get());

        HashSet<GroupName> vaccineOverLimitSet = TestUtil.generateGroupSet(Patient.LIMIT_VACCINES + 1);
        Optional<String> vaccineErrorMessage = Patient.validateParams(Optional.empty(),
                Optional.of(vaccineOverLimitSet));
        assertParseFailure(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + DOB_DESC_AMY + BLOODTYPE_DESC_AMY
                        + TestUtil.toParseStrings(vaccineOverLimitSet, PREFIX_VACCINATION),
                vaccineErrorMessage.get());

        Optional<String> bothErrorMessage = Patient.validateParams(Optional.of(allergyOverLimitSet),
                Optional.of(vaccineOverLimitSet));
        assertParseFailure(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + DOB_DESC_AMY + BLOODTYPE_DESC_AMY
                        + TestUtil.toParseStrings(vaccineOverLimitSet, PREFIX_VACCINATION)
                        + TestUtil.toParseStrings(allergyOverLimitSet, PREFIX_ALLERGY),
                bothErrorMessage.get());
    }
}
