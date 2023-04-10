package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SaveCommand;

public class SaveCommandParserTest {

    private final SaveCommandParser parser = new SaveCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SaveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validName_success() {
        SaveCommand expectedSaveCommand = new SaveCommand(Paths.get("data", "edumate.json"));
        assertParseSuccess(parser, "edumate", expectedSaveCommand);
    }
}
