package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BillCommand;
import seedu.address.model.person.Nric;

class BillCommandParserTest {

    private BillCommandParser parser = new BillCommandParser();
    private Nric validNric = new Nric(VALID_NRIC_AMY);

    @Test
    public void parse_validArgs_returnsBillCommand() {
        assertParseSuccess(parser, NRIC_DESC_AMY, new BillCommand(validNric));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, COST_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BillCommand.MESSAGE_USAGE));
    }
}
