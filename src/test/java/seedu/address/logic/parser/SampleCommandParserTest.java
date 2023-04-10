package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SampleCommand;

public class SampleCommandParserTest {
    private final SampleCommandParser parser = new SampleCommandParser();

    @Test
    public void parse_negativeSize_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(SampleCommandParser.MESSAGE_INVALID_SIZE));
    }

    @Test
    public void parse_exceedSize_throwsParseException() {
        assertParseFailure(parser, "101", String.format(SampleCommandParser.MESSAGE_INVALID_SIZE));
    }

    @Test
    public void parse_nonIntSize_throwsParseException() {
        assertParseFailure(parser, "em", String.format(ParserUtil.MESSAGE_INVALID_INT));
    }

    @Test
    public void parse_validSize_success() {
        SampleCommand sampleCommand = new SampleCommand(24);
        assertParseSuccess(parser, "24", sampleCommand);
    }
}
