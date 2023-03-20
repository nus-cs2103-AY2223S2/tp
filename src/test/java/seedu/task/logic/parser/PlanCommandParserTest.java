package seedu.task.logic.parser;

import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.task.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.PlanCommand;

public class PlanCommandParserTest {
    private PlanCommandParser parser = new PlanCommandParser();

    @Test
    public void parse_validArgs_returnsPlanCommand() {
        assertParseSuccess(parser, "5", new PlanCommand(5));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlanCommand.MESSAGE_USAGE));
    }
}
