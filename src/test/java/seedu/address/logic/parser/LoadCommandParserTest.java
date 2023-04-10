package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LoadCommand;

public class LoadCommandParserTest {

    private final LoadCommandParser parser = new LoadCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoadCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validName_success() {
        LoadCommand expectedLoadCommand = new LoadCommand(Paths.get("data", "edumate.json"));
        assertParseSuccess(parser, "edumate", expectedLoadCommand);
    }
}
