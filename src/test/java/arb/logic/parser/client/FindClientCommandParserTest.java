package arb.logic.parser.client;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.logic.commands.client.FindClientCommand;
import arb.logic.parser.exceptions.ParseException;

public class FindClientCommandParserTest {

    private FindClientCommandParser parser = new FindClientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindClientCommand() {
        try {
            assertTrue(new FindClientCommandParser().parse(" n/Alice t/friend") instanceof FindClientCommand);
            assertTrue(new FindClientCommandParser()
                    .parse(" \n n/Alice \n \t t/friend  \t") instanceof FindClientCommand);
        } catch (ParseException e) {
            assert false : e.getMessage();
        }
    }

}
