package fasttrack.logic.parser;

import static fasttrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.SetBudgetCommand;
import fasttrack.model.Budget;
import fasttrack.model.expense.Price;

class SetBudgetParserTest {

    private final SetBudgetParser parser = new SetBudgetParser();

    @Test
    void parse_validArgs_returnsSetBudgetCommand() {
        String input1 = " p/1000";
        SetBudgetCommand expected1 = new SetBudgetCommand(new Budget(1000));
        assertParseSuccess(parser, input1, expected1);
    }

    @Test
    void parse_invalidValue_throwsParseException() {
        String input1 = " p/-1000";
        assertParseFailure(parser, input1, Price.MESSAGE_CONSTRAINTS);

        String input2 = " ";
        assertParseFailure(parser, input2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE));
    }
}
