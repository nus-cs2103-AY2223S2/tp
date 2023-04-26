package seedu.medinfo.logic.parser;

import static seedu.medinfo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medinfo.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.medinfo.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.NRIC_DESC_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.medinfo.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.medinfo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medinfo.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.medinfo.testutil.TypicalPatients.AMY;
import static seedu.medinfo.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.medinfo.logic.commands.AddCommand;
import seedu.medinfo.model.patient.Name;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.testutil.PatientBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
            + NRIC_DESC_BOB, new AddCommand(expectedPatient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY
            + NAME_DESC_BOB + NRIC_DESC_BOB, new AddCommand(expectedPatient));

        // multiple nrics - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NRIC_DESC_AMY
            + NRIC_DESC_BOB, new AddCommand(expectedPatient));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Patient expectedPatient = new PatientBuilder(AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + NRIC_DESC_AMY, new AddCommand(expectedPatient));
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
        assertParseFailure(parser, INVALID_NAME_DESC + NRIC_DESC_AMY, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + NRIC_DESC_BOB,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
