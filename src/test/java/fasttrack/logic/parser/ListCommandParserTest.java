package fasttrack.logic.parser;

import static fasttrack.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_TIMESPAN;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fasttrack.logic.parser.ParserUtil.Timespan.MONTH;
import static fasttrack.logic.parser.ParserUtil.Timespan.WEEK;
import static fasttrack.logic.parser.ParserUtil.Timespan.YEAR;
import static fasttrack.testutil.TypicalCategories.FOOD;
import static fasttrack.testutil.TypicalCategories.TECH;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.list.ListExpensesCommand;
import fasttrack.model.expense.ExpenseInCategoryPredicate;
import fasttrack.model.expense.ExpenseInTimespanPredicate;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_categoryFieldPresent_success() {
        ExpenseInCategoryPredicate predicateFood = new ExpenseInCategoryPredicate(FOOD);
        ExpenseInCategoryPredicate predicateTech = new ExpenseInCategoryPredicate(TECH);
        ListExpensesCommand expectedCommandFood = new ListExpensesCommand(Optional.of(predicateFood),
                Optional.empty());
        ListExpensesCommand expectedCommandTech = new ListExpensesCommand(Optional.of(predicateTech),
                Optional.empty());


        assertParseSuccess(parser, " " + PREFIX_CATEGORY + FOOD.getCategoryName(), expectedCommandFood);
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + TECH.getCategoryName(), expectedCommandTech);
    }

    @Test
    public void parse_timespanFieldPresent_success() {
        ExpenseInTimespanPredicate predicateWeek = new ExpenseInTimespanPredicate(WEEK);
        ExpenseInTimespanPredicate predicateMonth = new ExpenseInTimespanPredicate(MONTH);
        ExpenseInTimespanPredicate predicateYear = new ExpenseInTimespanPredicate(YEAR);

        ListExpensesCommand expectedCommandWeek =
                new ListExpensesCommand(Optional.empty(), Optional.of(predicateWeek));
        ListExpensesCommand expectedCommandMonth =
                new ListExpensesCommand(Optional.empty(), Optional.of(predicateMonth));
        ListExpensesCommand expectedCommandYear =
                new ListExpensesCommand(Optional.empty(), Optional.of(predicateYear));

        // spelt in full, lowercase
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "week", expectedCommandWeek);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "month", expectedCommandMonth);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "year", expectedCommandYear);

        // spelt in full, UPPERCASE
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "WEEK", expectedCommandWeek);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "MONTH", expectedCommandMonth);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "YEAR", expectedCommandYear);

        // spelt in full, Capitalized
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "Week", expectedCommandWeek);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "Month", expectedCommandMonth);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "Year", expectedCommandYear);

        // abbreviation, lowercase
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "w", expectedCommandWeek);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "m", expectedCommandMonth);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "y", expectedCommandYear);

        // abbreviation, uppercase
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "W", expectedCommandWeek);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "M", expectedCommandMonth);
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "Y", expectedCommandYear);
    }

    @Test
    public void parse_categoryTimespanPresent_success() {
        ExpenseInTimespanPredicate predicateWeek = new ExpenseInTimespanPredicate(WEEK);
        ExpenseInTimespanPredicate predicateMonth = new ExpenseInTimespanPredicate(MONTH);
        ExpenseInTimespanPredicate predicateYear = new ExpenseInTimespanPredicate(YEAR);

        ExpenseInCategoryPredicate categoryPredicateFood = new ExpenseInCategoryPredicate(FOOD);
        ExpenseInCategoryPredicate categoryPredicateTech = new ExpenseInCategoryPredicate(TECH);

        ListExpensesCommand expectedCommandFoodYear = new ListExpensesCommand(Optional.of(categoryPredicateFood),
                Optional.of(predicateYear));
        ListExpensesCommand expectedCommandTechWeek = new ListExpensesCommand(Optional.of(categoryPredicateTech),
                Optional.of(predicateWeek));
        ListExpensesCommand expectedCommandFoodMonth = new ListExpensesCommand(Optional.of(categoryPredicateFood),
                Optional.of(predicateMonth));


        // List by Food, Year
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "year" + " "
                        + PREFIX_CATEGORY + FOOD.getCategoryName(),
                expectedCommandFoodYear);
        assertParseSuccess(parser, " " + PREFIX_CATEGORY
                        + FOOD.getCategoryName() + " " + PREFIX_TIMESPAN + "year",
                expectedCommandFoodYear);

        // List by Tech, Week
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "week" + " "
                        + PREFIX_CATEGORY + TECH.getCategoryName(),
                expectedCommandTechWeek);
        assertParseSuccess(parser, " " + PREFIX_CATEGORY
                        + TECH.getCategoryName() + " " + PREFIX_TIMESPAN + "w",
                expectedCommandTechWeek);

        // List by Food, Month
        assertParseSuccess(parser, " " + PREFIX_TIMESPAN + "m" + " "
                        + PREFIX_CATEGORY + FOOD.getCategoryName(),
                expectedCommandFoodMonth);
        assertParseSuccess(parser, " " + PREFIX_CATEGORY
                        + FOOD.getCategoryName() + " " + PREFIX_TIMESPAN + "MONTH",
                expectedCommandFoodMonth);
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
