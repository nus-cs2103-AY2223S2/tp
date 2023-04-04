package ezschedule.logic.parser;

import static ezschedule.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ezschedule.logic.commands.ShowNextCommand.SHOW_UPCOMING_COUNT_ONE;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ezschedule.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import ezschedule.logic.commands.ShowNextCommand;
import ezschedule.model.event.UpcomingEventPredicate;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ShowNextCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ShowNextCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ShowNextCommandParserTest {

    private final ShowNextCommandParser parser = new ShowNextCommandParser();

    @Test
    public void parse_noArgs_returnsShowNextCommandOneUpcoming() {
        final ShowNextCommand expected = new ShowNextCommand(new UpcomingEventPredicate(SHOW_UPCOMING_COUNT_ONE));

        assertParseSuccess(parser, "", expected);
        assertParseSuccess(parser, "   ", expected);
    }

    @Test
    public void parse_validArgs_returnsShowNextCommandArbitraryUpcoming() {
        assertParseSuccess(parser, "5", new ShowNextCommand(new UpcomingEventPredicate(5)));
        assertParseSuccess(parser, " 7 ", new ShowNextCommand(new UpcomingEventPredicate(7)));
        assertParseSuccess(parser, " 33", new ShowNextCommand(new UpcomingEventPredicate(33)));
        assertParseSuccess(parser, "500", new ShowNextCommand(new UpcomingEventPredicate(500)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        final String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowNextCommand.MESSAGE_USAGE);

        // Input is NaN
        assertParseFailure(parser, "a", expectedErrorMessage);
        assertParseFailure(parser, "five", expectedErrorMessage);
        assertParseFailure(parser, "6C", expectedErrorMessage);
        assertParseFailure(parser, "F4", expectedErrorMessage);

        // Input is not integer number
        assertParseFailure(parser, "1.0", expectedErrorMessage);
        assertParseFailure(parser, "5.2", expectedErrorMessage);
        assertParseFailure(parser, "7.99", expectedErrorMessage);
        assertParseFailure(parser, "-2.0", expectedErrorMessage);

        // Input is zero
        assertParseFailure(parser, "0", expectedErrorMessage);
        assertParseFailure(parser, "000", expectedErrorMessage);

        // Input is negative integer
        assertParseFailure(parser, "-1", expectedErrorMessage);
        assertParseFailure(parser, "-20", expectedErrorMessage);
    }

}
