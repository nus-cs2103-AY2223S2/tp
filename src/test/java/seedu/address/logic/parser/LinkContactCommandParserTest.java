package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LinkContactCommandParserTest {

    private LinkContactCommandParser parser = new LinkContactCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsLinkContactCommand() {
        // no leading and trailing whitespaces
        Index targetIndex = INDEX_FIRST_EVENT;
        LinkContactCommand expectedLinkContactCommand =
                new LinkContactCommand(targetIndex, "91234567");
        assertParseSuccess(parser, "1 91234567", expectedLinkContactCommand);

        //Leading and trailing white space
        assertParseSuccess(parser, "   1 91234567   ", expectedLinkContactCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkContactCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkContactCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("0"));
        assertThrows(ParseException.class, () -> parser.parse("-1"));
    }

}
