package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.employee.FilterByLeaveCountPredicate;
import seedu.address.model.employee.FilterByPayrollPredicate;



public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_wrongCommand_throwsParseException() {
        assertParseFailure(parser, "filter department > 1000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_wrongSyntax_throwsParseException() {
        assertParseFailure(parser, "filter department",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_validArgsPayrollPredicate_returnsFilterCommand() {
        // no leading and trailing whitespaces
        int comparisonAmount = 1000;
        boolean[] possibleOperators = {false, true, false};
        FilterCommand expectedFilterCommand =
                new FilterCommand(new FilterByPayrollPredicate(comparisonAmount, possibleOperators));
        assertParseSuccess(parser, "pr < 1000", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n pr \n \t <  \t 1000", expectedFilterCommand);
    }
    @Test
    public void parse_validArgsLeaveCountPredicate_returnsFilterCommand() {
        // no leading and trailing whitespaces
        int comparisonAmount = 1500;
        boolean[] possibleOperators = {true, false, false};
        FilterCommand expectedFilterCommand =
                new FilterCommand(new FilterByLeaveCountPredicate(comparisonAmount, possibleOperators));
        assertParseSuccess(parser, "l > 1500", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n l \n \t >  \t 1500", expectedFilterCommand);
    }
}
