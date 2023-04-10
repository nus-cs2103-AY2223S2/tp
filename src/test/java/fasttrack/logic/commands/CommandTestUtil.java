package fasttrack.logic.commands;

import static fasttrack.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_END_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_NAME;
import static fasttrack.logic.parser.CliSyntax.PREFIX_PRICE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_START_DATE;
import static fasttrack.logic.parser.CliSyntax.PREFIX_SUMMARY;
import static fasttrack.logic.parser.CliSyntax.PREFIX_TIMESPAN;
import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.ExpenseTracker;
import fasttrack.model.Model;
import fasttrack.model.expense.Expense;
import fasttrack.ui.ScreenType;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_APPLE = "Apple";
    public static final String VALID_NAME_BANANA = "Banana";
    public static final String VALID_NAME_CHERRY = "Cherry";
    public static final String VALID_NAME_DURIAN = "Durian";
    public static final String VALID_NAME_ELDERBERRY = "Elderberry";
    public static final String VALID_NAME_FIG = "Fig";
    public static final String VALID_NAME_GRAPE = "Grape";

    public static final double VALID_PRICE_APPLE = 1.50;
    public static final double VALID_PRICE_BANANA = 1.00;
    public static final double VALID_PRICE_CHERRY = 0.20;
    public static final double VALID_PRICE_DURIAN = 15.0;
    public static final double VALID_PRICE_ELDERBERRY = 4.0;
    public static final double VALID_PRICE_FIG = 1000.0;
    public static final double VALID_PRICE_GRAPE = 10.0;

    public static final String VALID_CATEGORY_FOOD = "food";
    public static final String VALID_SUMMARY_FOOD = "For consumable expenses";
    public static final String VALID_CATEGORY_TECH = "tech";
    public static final String VALID_CATEGORY_SCHOOL = "school";


    public static final String VALID_DATE_APPLE = "1/3/2023";
    public static final String VALID_DATE_BANANA = "2/3/2023";
    public static final String VALID_DATE_CHERRY = "1/3/2023";
    public static final String VALID_DATE_DURIAN = "15/3/2023";
    public static final String VALID_DATE_ELDERBERRY = "1/1/2023";
    public static final String VALID_DATE_FIG = "15/2/2023";
    public static final String VALID_DATE_GRAPE = "17/3/2023";


    public static final String VALID_INTERVAL_DAY = " " + PREFIX_TIMESPAN + "day";
    public static final String VALID_INTERVAL_WEEK = " " + PREFIX_TIMESPAN + "week";
    public static final String VALID_INTERVAL_MONTH = " " + PREFIX_TIMESPAN + "month";
    public static final String VALID_INTERVAL_YEAR = " " + PREFIX_TIMESPAN + "year";
    public static final String INVALID_INTERVAL = " " + PREFIX_TIMESPAN + "biweekly";


    public static final String VALID_START_DATE = " " + PREFIX_START_DATE + "1/3/2023";
    public static final String VALID_END_DATE = " " + PREFIX_END_DATE + "2/3/2024";
    public static final String INVALID_DATE = " " + PREFIX_START_DATE + "50/13/2023";
    public static final String INVALID_START_DATE = " " + PREFIX_END_DATE + "1/3/2023";
    public static final String INVALID_END_DATE = " " + PREFIX_START_DATE + "2/3/2024";



    public static final String DESC_APPLE = " " + PREFIX_NAME + VALID_NAME_APPLE;
    public static final String AMT_APPLE = " " + PREFIX_PRICE + VALID_PRICE_APPLE;
    public static final String DATE_APPLE = " " + PREFIX_DATE + VALID_DATE_APPLE;
    public static final String CAT_APPLE = " " + PREFIX_CATEGORY + VALID_CATEGORY_FOOD;
    public static final String SUM_APPLE = " " + PREFIX_SUMMARY + VALID_SUMMARY_FOOD;


    public static final String DESC_BANANA = " " + PREFIX_NAME + VALID_NAME_BANANA;
    public static final String AMT_BANANA = " " + PREFIX_PRICE + VALID_PRICE_BANANA;
    public static final String DATE_BANANA = " " + PREFIX_DATE + VALID_DATE_BANANA;
    public static final String CAT_BANANA = " " + PREFIX_CATEGORY + VALID_CATEGORY_FOOD;


    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_PRICE + "10x";
    // non-numeric character not allowed in amount
    public static final String INVALID_DATE_FORMAT_DESC = " " + PREFIX_DATE + "2023/04/10";
    // invalid date format
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + "food@";
    // symbols in category not allowed
    public static final String INVALID_CATEGORY_SUM = " " + PREFIX_SUMMARY + "For consumable expenses@";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualDataModel,
                                            CommandResult expectedCommandResult,
                                            Model expectedDataModel) {
        try {
            CommandResult result = command.execute(actualDataModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedDataModel, actualDataModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualDataModel, String expectedMessage,
                                            Model expectedDataModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, ScreenType.EXPENSE_SCREEN);
        assertCommandSuccess(command, actualDataModel, expectedCommandResult, expectedDataModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExpenseTracker expectedExpenseTracker = new ExpenseTracker(actualModel.getExpenseTracker());
        List<Expense> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExpenseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedExpenseTracker, actualModel.getExpenseTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredExpenseList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the expense at the given
     * {@code targetIndex} in the
     * {@code model}'s ExpenseTracker.
     */
    public static void showExpenseAtIndex(Model dataModel, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < dataModel.getFilteredExpenseList().size());

        Expense expense = dataModel.getFilteredExpenseList().get(targetIndex.getZeroBased());
        final String name = expense.getName();
        //TODO update predicates here when created
        //model.updateFilteredExpensesList(new NameContainsKeywordsPredicate(Arrays.asList(name)));
        assertEquals(1, dataModel.getFilteredExpenseList().size());
    }

}
