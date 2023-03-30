package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.CCA_DESC_ICS;
import static seedu.connectus.logic.commands.CommandTestUtil.CCA_DESC_NES;
import static seedu.connectus.logic.commands.CommandTestUtil.MAJOR_DESC_COMPUTER_SCIENCE;
import static seedu.connectus.logic.commands.CommandTestUtil.MAJOR_DESC_BBA;
import static seedu.connectus.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_CCA_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.INVALID_REMARK_DESC;
import static seedu.connectus.logic.commands.CommandTestUtil.MODULE_DESC_CS2101;
import static seedu.connectus.logic.commands.CommandTestUtil.MODULE_DESC_CS2103T;
import static seedu.connectus.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.connectus.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.connectus.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.connectus.logic.commands.CommandTestUtil.REMARK_DESC_FRIEND;
import static seedu.connectus.logic.commands.CommandTestUtil.REMARK_DESC_HUSBAND;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_ICS;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_CCA_NES;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MAJOR_COMPUTER_SCIENCE;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MAJOR_BBA;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2101;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_MODULE_CS2103T;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_REMARK_FRIEND;
import static seedu.connectus.logic.commands.CommandTestUtil.VALID_REMARK_HUSBAND;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.connectus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.connectus.testutil.TypicalPersons.AMY;
import static seedu.connectus.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.connectus.logic.commands.AddCommand;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.Major;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Remark;
import seedu.connectus.testutil.PersonBuilder;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB)
                .withRemarks(VALID_REMARK_FRIEND)
                .withModules(VALID_MODULE_CS2101).withCcas(VALID_CCA_NES)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + REMARK_DESC_FRIEND + MODULE_DESC_CS2101
                + CCA_DESC_NES + MAJOR_DESC_COMPUTER_SCIENCE, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + REMARK_DESC_FRIEND + MODULE_DESC_CS2101
                + CCA_DESC_NES + MAJOR_DESC_COMPUTER_SCIENCE, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + REMARK_DESC_FRIEND + MODULE_DESC_CS2101
                + CCA_DESC_NES + MAJOR_DESC_COMPUTER_SCIENCE, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + REMARK_DESC_FRIEND + MODULE_DESC_CS2101
                + CCA_DESC_NES + MAJOR_DESC_COMPUTER_SCIENCE, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + REMARK_DESC_FRIEND + MODULE_DESC_CS2101
                + CCA_DESC_NES + MAJOR_DESC_COMPUTER_SCIENCE, new AddCommand(expectedPerson));

        // multiple remarks - all accepted
        Person expectedPersonMultipleRemarks = new PersonBuilder(BOB)
                .withRemarks(VALID_REMARK_FRIEND, VALID_REMARK_HUSBAND)
                .withModules(VALID_MODULE_CS2101).withCcas(VALID_CCA_NES)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND + MODULE_DESC_CS2101
                + CCA_DESC_NES + MAJOR_DESC_COMPUTER_SCIENCE, new AddCommand(expectedPersonMultipleRemarks));

        // multiple modules - all accepted
        Person expectedPersonMultipleModules = new PersonBuilder(BOB)
                .withRemarks(VALID_REMARK_FRIEND)
                .withModules(VALID_MODULE_CS2103T, VALID_MODULE_CS2101)
                .withCcas(VALID_CCA_NES)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + REMARK_DESC_FRIEND + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                + CCA_DESC_NES + MAJOR_DESC_COMPUTER_SCIENCE,
                new AddCommand(expectedPersonMultipleModules));

        // multiple ccas - all accepted
        Person expectedPersonMultipleCcas = new PersonBuilder(BOB)
                .withRemarks(VALID_REMARK_FRIEND)
                .withModules(VALID_MODULE_CS2103T).withCcas(VALID_CCA_NES, VALID_CCA_ICS)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + REMARK_DESC_FRIEND + MODULE_DESC_CS2103T
                        + CCA_DESC_NES + CCA_DESC_ICS + MAJOR_DESC_COMPUTER_SCIENCE,
                new AddCommand(expectedPersonMultipleCcas));

        // multiple ccaPositions - all accepted
        Person expectedPersonMultipleMajors = new PersonBuilder(BOB)
                .withRemarks(VALID_REMARK_FRIEND)
                .withModules(VALID_MODULE_CS2103T).withCcas(VALID_CCA_NES)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE, VALID_MAJOR_BBA).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + REMARK_DESC_FRIEND + MODULE_DESC_CS2103T
                        + CCA_DESC_NES + MAJOR_DESC_COMPUTER_SCIENCE + MAJOR_DESC_BBA,
                new AddCommand(expectedPersonMultipleMajors));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPersonZeroTags = new PersonBuilder(AMY).withRemarks().withModules()
                .withCcas().withMajors().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPersonZeroTags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                + CCA_DESC_ICS + MAJOR_DESC_COMPUTER_SCIENCE,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                + CCA_DESC_ICS + MAJOR_DESC_COMPUTER_SCIENCE,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                + CCA_DESC_ICS + MAJOR_DESC_COMPUTER_SCIENCE,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                + CCA_DESC_ICS + MAJOR_DESC_COMPUTER_SCIENCE,
                Address.MESSAGE_CONSTRAINTS);

        // invalid remark
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + REMARK_DESC_HUSBAND + INVALID_REMARK_DESC + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                + CCA_DESC_ICS + MAJOR_DESC_COMPUTER_SCIENCE,
                Remark.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND + INVALID_MODULE_DESC + MODULE_DESC_CS2101
                + CCA_DESC_ICS + MAJOR_DESC_COMPUTER_SCIENCE,
                Module.MESSAGE_CONSTRAINTS);

        // invalid cca
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + REMARK_DESC_HUSBAND + VALID_REMARK_FRIEND + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                        + INVALID_CCA_DESC + VALID_MAJOR_COMPUTER_SCIENCE,
                Cca.MESSAGE_CONSTRAINTS);

        // invalid cca position
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + REMARK_DESC_HUSBAND + VALID_REMARK_FRIEND + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                        + CCA_DESC_ICS + INVALID_MAJOR_DESC,
                Major.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + REMARK_DESC_HUSBAND + REMARK_DESC_FRIEND
                + MODULE_DESC_CS2103T + MODULE_DESC_CS2101
                + CCA_DESC_ICS + MAJOR_DESC_COMPUTER_SCIENCE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
