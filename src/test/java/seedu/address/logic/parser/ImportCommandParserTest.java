package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {

    private ImportCommandParser parser = new ImportCommandParser();
    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, "soc", new ImportCommand("soc"));
        assertParseSuccess(parser, "chs", new ImportCommand("chs"));
    }
    @Test
    public void parse_noFieldsPresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
    }


}

