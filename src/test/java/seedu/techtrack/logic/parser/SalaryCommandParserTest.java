package seedu.techtrack.logic.parser;

import static seedu.techtrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.techtrack.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.techtrack.logic.commands.SalaryCommand;

public class SalaryCommandParserTest {

    private SalaryCommandParser parser = new SalaryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SalaryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongArg_throwsParseException() {
        assertParseFailure(parser, "assec", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SalaryCommand.MESSAGE_USAGE));
    }
}
