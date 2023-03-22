package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.logic.commands.project.FindProjectCommand;
import arb.logic.parser.exceptions.ParseException;

public class FindProjectCommandParserTest {

    private FindProjectCommandParser parser = new FindProjectCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProjectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindProjectCommand() {
        try {
            assertTrue(new FindProjectCommandParser()
                    .parse(" n/Sky Painting t/painting") instanceof FindProjectCommand);
            assertTrue(new FindProjectCommandParser()
                    .parse(" \n n/Sky Painting \n \t t/painting  \t") instanceof FindProjectCommand);
        } catch (ParseException e) {
            assert false : e.getMessage();
        }
    }

}
