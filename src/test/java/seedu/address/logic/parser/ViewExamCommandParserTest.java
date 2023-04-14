package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exam.ViewExamCommand;
import seedu.address.logic.parser.exam.ViewExamCommandParser;

class ViewExamCommandParserTest {
    private ViewExamCommandParser parser = new ViewExamCommandParser();
    @Test
    public void parse_wrongPrefixes_failure() {
        String input = " index/1";
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        ViewExamCommand.MESSAGE_USAGE);
        assertParseFailure(parser, input, expected);
    }
}
