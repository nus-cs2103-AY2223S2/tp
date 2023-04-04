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

    private final String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ShowNextCommand.MESSAGE_USAGE);

    @Test
    public void parse_noArgs_returnsShowNextCommandOneUpcoming() {
        final ShowNextCommand expected = new ShowNextCommand(new UpcomingEventPredicate(SHOW_UPCOMING_COUNT_ONE));

        assertParseSuccess(parser, "", expected); // empty string
        assertParseSuccess(parser, " ", expected); // single space
        assertParseSuccess(parser, "   ", expected); // multiple space
    }

    @Test
    public void parse_validArgs_returnsShowNextCommandArbitraryUpcoming() {
        assertParseSuccess(parser, "5", new ShowNextCommand(new UpcomingEventPredicate(5)));
        assertParseSuccess(parser, " 7 ", new ShowNextCommand(new UpcomingEventPredicate(7))); // extra spaces
        assertParseSuccess(parser, " 33", new ShowNextCommand(new UpcomingEventPredicate(33))); // leading space
        assertParseSuccess(parser, "55 ", new ShowNextCommand(new UpcomingEventPredicate(55))); // trailing space
        assertParseSuccess(parser, "404", new ShowNextCommand(new UpcomingEventPredicate(404))); // large number
    }

    @Test
    public void parse_nanArgs_throwsParseException() {
        assertParseFailure(parser, "a", expectedErrorMessage);
        assertParseFailure(parser, "five", expectedErrorMessage);
        assertParseFailure(parser, "6C", expectedErrorMessage);
        assertParseFailure(parser, "F4", expectedErrorMessage);
    }

    @Test
    public void parse_notIntArgs_throwsParseException() {
        assertParseFailure(parser, "1.0", expectedErrorMessage);
        assertParseFailure(parser, "5.2", expectedErrorMessage);
        assertParseFailure(parser, "7.99", expectedErrorMessage);
        assertParseFailure(parser, "-2.0", expectedErrorMessage);
    }

    @Test
    public void parse_zeroArgs_throwsParseException() {
        assertParseFailure(parser, "0", expectedErrorMessage);
        assertParseFailure(parser, "000", expectedErrorMessage);
    }

    @Test void parse_negativeIntArgs_throwsParseException() {
        assertParseFailure(parser, "-1", expectedErrorMessage);
        assertParseFailure(parser, "-20", expectedErrorMessage);
    }

}
