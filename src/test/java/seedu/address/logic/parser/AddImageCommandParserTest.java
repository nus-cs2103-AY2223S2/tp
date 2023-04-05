package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddImageCommand;


public class AddImageCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddImageCommand.MESSAGE_USAGE);
    private AddImageCommandParser parser = new AddImageCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // All fields present
        assertParseSuccess(parser, "1 ai//stringPath", new AddImageCommand(Index.fromZeroBased(0), "/stringPath"));

        // White space
        assertParseSuccess(parser, "1 ai/      /stringPath",
                new AddImageCommand(Index.fromZeroBased(0), "/stringPath"));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "ai//stringPath", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
        // syntax specified but not text
        assertParseFailure(parser, "1 ai/", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 ai//stringPath", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 ai//stringPath", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ /string", MESSAGE_INVALID_FORMAT);
    }
    //    @Test
    //    public void parse_invalidPath_failure() {
    //        // Not absolute path
    //        assertParseFailure(parser, "1 ai/not/absolute/path");
    //    }
}
