package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MEDICATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnprescribeCommand;
import seedu.address.model.person.Nric;
import seedu.address.model.prescription.Medication;

class UnprescribeCommandParserTest {

    private UnprescribeCommandParser parser = new UnprescribeCommandParser();
    private Nric validNric = new Nric(VALID_NRIC_AMY);

    @Test
    public void parse_validArgs_returnsUnprescribeCommand() {
        assertParseSuccess(parser, NRIC_DESC_AMY + MEDICATION_DESC_AMY,
                new UnprescribeCommand(validNric, new Medication(VALID_MEDICATION_AMY)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, MEDICATION_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnprescribeCommand.MESSAGE_USAGE));
    }
}
