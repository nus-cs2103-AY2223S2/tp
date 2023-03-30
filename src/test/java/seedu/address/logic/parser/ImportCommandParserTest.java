package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE);

    private static final String COMMAND = "import";

    private static final String COMMAND_KEYWORD_RESET = "reset";

    private static final String COMMAND_KEYWORD_COMBINE = "combine";

    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_invalidKeyword_failure() {
        // wrong keyword
        assertParseFailure(parser, "random", MESSAGE_INVALID_FORMAT);

        // wrong keyword plus valid keyword
        assertParseFailure(parser, String.format("%s random", COMMAND_KEYWORD_COMBINE), MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, String.format("hi %s", COMMAND_KEYWORD_RESET), MESSAGE_INVALID_FORMAT);

        // multiple keywords
        assertParseFailure(parser,
                String.format("%s %s", COMMAND_KEYWORD_COMBINE, COMMAND_KEYWORD_RESET), MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                String.format("%s %s", COMMAND_KEYWORD_RESET, COMMAND_KEYWORD_COMBINE), MESSAGE_INVALID_FORMAT);

        // repeated same keyword
        assertParseFailure(parser,
                String.format("%s %s %s", COMMAND_KEYWORD_COMBINE, COMMAND_KEYWORD_COMBINE, COMMAND_KEYWORD_COMBINE),
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser,
                String.format("%s %s %s %s",
                        COMMAND_KEYWORD_RESET, COMMAND_KEYWORD_RESET, COMMAND_KEYWORD_RESET, COMMAND_KEYWORD_RESET),
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_noKeyword_success() {
        String userInput = "";

        ImportCommand expectedCommand = new ImportCommand(false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_shownKeyword_success() {
        String userInput = COMMAND_KEYWORD_COMBINE;

        ImportCommand expectedCommand = new ImportCommand(false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allKeyword_success() {
        String userInput = COMMAND_KEYWORD_RESET;

        ImportCommand expectedCommand = new ImportCommand(true);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_addedWhitespace_success() {
        String userInput = String.format("    %s   ", COMMAND_KEYWORD_COMBINE);

        ImportCommand expectedCommand = new ImportCommand(true);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
