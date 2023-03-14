package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.AMY;
import static seedu.address.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.patient.*;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

public class AddCommandParserTest {
        private AddCommandParser parser = new AddCommandParser();

        @Test
        public void parse_allFieldsPresent_success() {
                Patient expectedPatient = new PatientBuilder(BOB).build();

                // whitespace only preamble
                assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB, new AddCommand(expectedPatient));

                // multiple names - last name accepted
                assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB, new AddCommand(expectedPatient));

                // multiple phones - last phone accepted
                assertParseSuccess(parser, NAME_DESC_BOB, new AddCommand(expectedPatient));

                // multiple emails - last email accepted
                assertParseSuccess(parser, NAME_DESC_BOB, new AddCommand(expectedPatient));

                // multiple addresses - last address accepted
                assertParseSuccess(parser, NAME_DESC_BOB, new AddCommand(expectedPatient));

                // multiple tags - all accepted
                Patient expectedPatientMultipleTags = new PatientBuilder(BOB).build();
                assertParseSuccess(parser, NAME_DESC_BOB, new AddCommand(expectedPatientMultipleTags));
        }

        @Test
        public void parse_optionalFieldsMissing_success() {
                // zero tags
                Patient expectedPatient = new PatientBuilder(AMY).build();
                assertParseSuccess(parser, NAME_DESC_AMY,
                                new AddCommand(expectedPatient));
        }

        @Test
        public void parse_compulsoryFieldMissing_failure() {
                String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

                // missing name prefix
                assertParseFailure(parser, VALID_NAME_BOB,
                                expectedMessage);

                // all prefixes missing
                assertParseFailure(parser, VALID_NAME_BOB,
                                expectedMessage);
        }

        @Test
        public void parse_invalidValue_failure() {
                // invalid name
                assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

                // non-empty preamble
                assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB,
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
}
