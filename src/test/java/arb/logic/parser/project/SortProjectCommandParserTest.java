package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.commands.CommandTestUtil.SORTING_OPTION_DESC;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static arb.testutil.TypicalProjectSortingOptions.BY_DEADLINE;

import org.junit.jupiter.api.Test;

import arb.logic.commands.project.SortProjectCommand;

public class SortProjectCommandParserTest {

    private SortProjectCommandParser parser = new SortProjectCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortProjectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortProjectCommand() {
        // no leading and trailing whitespaces
        SortProjectCommand expectedSortProjectCommand =
                new SortProjectCommand(BY_DEADLINE);
        assertParseSuccess(parser, SORTING_OPTION_DESC, expectedSortProjectCommand);
    }
}
