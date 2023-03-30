
package seedu.roles.logic.parser;

import static seedu.roles.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.roles.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.roles.logic.commands.DeadlineCommand;

public class DeadlineCommandParserTest {

    private DeadlineCommandParser parser = new DeadlineCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "    ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeadlineCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongArg_throwsParseException() {
        assertParseFailure(parser, "assec", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeadlineCommand.MESSAGE_USAGE));
    }
}
