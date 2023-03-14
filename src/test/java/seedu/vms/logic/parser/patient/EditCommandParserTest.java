package seedu.vms.logic.parser.patient;

import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
import static seedu.vms.logic.commands.CommandTestUtil.INVALID_VACCINE_DESC;
import static seedu.vms.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_ALLERGY_GLUTEN;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_ALLERGY_SEAFOOD;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.vms.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.vms.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.vms.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.vms.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.vms.testutil.TypicalIndexes.INDEX_THIRD_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.patient.EditCommand;
import seedu.vms.logic.commands.patient.EditCommand.EditPatientDescriptor;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Phone;
import seedu.vms.testutil.EditPatientDescriptorBuilder;

public class EditCommandParserTest {

    private static final String ALLERGY_EMPTY = " " + DELIMITER + PREFIX_ALLERGY + " ";

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_DOB_DESC, Dob.MESSAGE_CONSTRAINTS); // invalid dob
        assertParseFailure(parser, "1" + INVALID_BLOODTYPE_DESC, BloodType.MESSAGE_CONSTRAINTS); // invalid allergy
        assertParseFailure(parser, "1" + INVALID_ALLERGY_DESC, GroupName.MESSAGE_CONSTRAINTS); // invalid allergy
        assertParseFailure(parser, "1" + INVALID_VACCINE_DESC, GroupName.MESSAGE_CONSTRAINTS); // invalid vaccine

        // invalid phone followed by valid dob
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + DOB_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_ALLERGY} alone will reset the allergies of the {@code Patient} being edited,
        // parsing it together with a valid allergy results in error
        assertParseFailure(parser, "1" + ALLERGY_DESC_GLUTEN + ALLERGY_DESC_SEAFOOD + ALLERGY_EMPTY,
                GroupName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ALLERGY_DESC_GLUTEN + ALLERGY_EMPTY + ALLERGY_DESC_SEAFOOD,
                GroupName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ALLERGY_EMPTY + ALLERGY_DESC_GLUTEN + ALLERGY_DESC_SEAFOOD,
                GroupName.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DOB_DESC + VALID_BLOODTYPE_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PATIENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + ALLERGY_DESC_SEAFOOD
                + DOB_DESC_AMY + BLOODTYPE_DESC_AMY + NAME_DESC_AMY + ALLERGY_DESC_GLUTEN;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withDob(VALID_DOB_AMY).withBloodType(VALID_BLOODTYPE_AMY)
                .withAllergies(VALID_ALLERGY_SEAFOOD, VALID_ALLERGY_GLUTEN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PATIENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + DOB_DESC_AMY;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withDob(VALID_DOB_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PATIENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // dob
        userInput = targetIndex.getOneBased() + DOB_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withDob(VALID_DOB_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // bloodType
        userInput = targetIndex.getOneBased() + BLOODTYPE_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withBloodType(VALID_BLOODTYPE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // allergies
        userInput = targetIndex.getOneBased() + ALLERGY_DESC_GLUTEN;
        descriptor = new EditPatientDescriptorBuilder().withAllergies(VALID_ALLERGY_GLUTEN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PATIENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + BLOODTYPE_DESC_AMY + DOB_DESC_AMY
                + ALLERGY_DESC_GLUTEN + PHONE_DESC_AMY + BLOODTYPE_DESC_AMY + DOB_DESC_AMY + ALLERGY_DESC_GLUTEN
                + PHONE_DESC_BOB + BLOODTYPE_DESC_BOB + DOB_DESC_BOB + ALLERGY_DESC_SEAFOOD;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withDob(VALID_DOB_BOB).withBloodType(VALID_BLOODTYPE_BOB)
                .withAllergies(VALID_ALLERGY_GLUTEN, VALID_ALLERGY_SEAFOOD)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PATIENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DOB_DESC_BOB + INVALID_PHONE_DESC + BLOODTYPE_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB).withDob(VALID_DOB_BOB)
                .withBloodType(VALID_BLOODTYPE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetAllergies_success() {
        Index targetIndex = INDEX_THIRD_PATIENT;
        String userInput = targetIndex.getOneBased() + ALLERGY_EMPTY;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withAllergies().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
