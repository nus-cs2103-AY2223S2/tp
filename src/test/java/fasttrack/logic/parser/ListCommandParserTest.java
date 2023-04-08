package fasttrack.logic.parser;

import static fasttrack.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fasttrack.logic.parser.ParserUtil.Timespan.YEAR;
import static fasttrack.testutil.TypicalCategories.FOOD;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.list.ListExpensesCommand;
import fasttrack.model.expense.ExpenseInCategoryPredicate;
import fasttrack.model.expense.ExpenseInTimespanPredicate;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_categoryFieldPresent_success() {
        ExpenseInCategoryPredicate predicate = new ExpenseInCategoryPredicate(FOOD);
        ListExpensesCommand expectedCommand = new ListExpensesCommand(Optional.of(predicate), Optional.empty());

        // whitespace only preamble
        // assertParseSuccess(parser,
        //         ListExpensesCommand.COMMAND_WORD + PREAMBLE_WHITESPACE + PREFIX_CATEGORY + FOOD.getCategoryName(),
        //         expectedCommand);

        // no preamble
        // String command = PREFIX_CATEGORY + FOOD.getCategoryName();
        // assertParseSuccess(parser, command, expectedCommand);
    }

    @Test
    public void parse_timespanFieldPresent_success() {
        ExpenseInTimespanPredicate predicate = new ExpenseInTimespanPredicate(YEAR);
        ListExpensesCommand expectedCommand = new ListExpensesCommand(Optional.empty(), Optional.of(predicate));

        // whitespace only preamble
        // assertParseSuccess(parser, PREAMBLE_WHITESPACE + PREFIX_TIMESPAN + "year", expectedCommand);

        // no preamble
        // assertParseSuccess(parser, PREFIX_TIMESPAN + "year", expectedCommand);
    }

    @Test
    public void parse_categoryTimespanPresent_success() {
        ExpenseInTimespanPredicate timespanPredicate = new ExpenseInTimespanPredicate(YEAR);
        ExpenseInCategoryPredicate categoryPredicate = new ExpenseInCategoryPredicate(FOOD);
        ListExpensesCommand expectedCommand = new ListExpensesCommand(Optional.of(categoryPredicate),
                Optional.of(timespanPredicate));

        // whitespace only preamble
        //    assertParseSuccess(parser,
        //            PREAMBLE_WHITESPACE + PREFIX_TIMESPAN + "year" + PREFIX_CATEGORY + FOOD.getCategoryName(),
        //            expectedCommand);
        //    assertParseSuccess(parser,
        //            PREAMBLE_WHITESPACE + PREFIX_CATEGORY + FOOD.getCategoryName() + PREFIX_TIMESPAN + "year",
        //            expectedCommand);

        //     no preamble
        //    assertParseSuccess(parser, PREFIX_TIMESPAN + "year" + PREFIX_CATEGORY + FOOD.getCategoryName(),
        //            expectedCommand);
        //    assertParseSuccess(parser, PREFIX_CATEGORY + FOOD.getCategoryName() + PREFIX_TIMESPAN + "year",
        //            expectedCommand);
    }

    @Test
    public void parse_noFields_success() {
        ListExpensesCommand expectedCommand = new ListExpensesCommand(Optional.empty(), Optional.empty());

        // whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE, expectedCommand);

        // no preamble
        assertParseSuccess(parser, "", expectedCommand);
    }

}
