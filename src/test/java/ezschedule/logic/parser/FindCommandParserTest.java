package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import ezschedule.logic.commands.FindCommand;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    // TODO: change FindCommandParserTest to be like EditCommandParserTest
    /*
    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindEventDescriptor findEventDescriptor = new FindEventDescriptor();
        findEventDescriptor.setName(new Name("A"));
        FindCommand expectedFindCommand = new FindCommand(findEventDescriptor);
        assertParseSuccess(parser, "A B", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/ \n A \n \t B  \t", expectedFindCommand);
    }
    */
}
