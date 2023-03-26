package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MEDICATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MEDICATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PrescribeCommand;
import seedu.address.model.prescription.Medication;
import seedu.address.model.person.Nric;

class PrescribeCommandParserTest {

    private PrescribeCommandParser parser = new PrescribeCommandParser();
    private Nric validNric = new Nric(VALID_NRIC_AMY);


    @Test
    public void parse_validArgs_returnsPrescribeCommand() {
        assertParseSuccess(parser, NRIC_DESC_AMY + MEDICATION_DESC_AMY,
                new PrescribeCommand(validNric, new Medication(VALID_MEDICATION_AMY, )));
        assertParseSuccess(parser, NRIC_DESC_AMY + MEDICATION_DESC_BOB,
                new PrescribeCommand(validNric, new Medication(VALID_MEDICATION_BOB, )));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Incorrect format for command, left out nric
        assertParseFailure(parser, MEDICATION_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrescribeCommand.MESSAGE_USAGE));
        // Incorrect format for Medication
        assertParseFailure(parser, NRIC_DESC_AMY + " m/a", Medication.MESSAGE_CONSTRAINTS);
    }
}
