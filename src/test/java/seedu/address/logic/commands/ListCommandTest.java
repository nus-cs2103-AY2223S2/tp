package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.testutil.TypicalCategories.FOOD;
import static seedu.address.testutil.TypicalCategories.TECH;
import static seedu.address.testutil.TypicalExpenses.CHERRY;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListExpensesCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.ExpenseInCategoryPredicate;
import seedu.address.model.expense.ExpenseInTimespanPredicate;
import seedu.address.testutil.TypicalExpenses;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalExpenses.getTypicalExpenseTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getExpenseTracker(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {

        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 7);
        ListExpensesCommand command = new ListExpensesCommand(Optional.empty(), Optional.empty());
        expectedModel.updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(
                Arrays.asList(TypicalExpenses.APPLE, TypicalExpenses.BANANA, CHERRY, TypicalExpenses.DURIAN,
                        TypicalExpenses.ELDERBERRY, TypicalExpenses.FIG, TypicalExpenses.GRAPE),
                model.getFilteredExpenseList());
    }

    @Test
    public void execute_listFilterByCategory_showsCategory() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 2);
        ExpenseInCategoryPredicate predicate = new ExpenseInCategoryPredicate(FOOD);

        ListExpensesCommand command = new ListExpensesCommand(Optional.of(predicate), Optional.empty());
        expectedModel.updateFilteredExpensesList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalExpenses.APPLE, TypicalExpenses.BANANA), model.getFilteredExpenseList());
    }

    @Test
    public void execute_listFilterByTimespan_showsExpenses() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 2);
        ExpenseInTimespanPredicate predicate = new ExpenseInTimespanPredicate(LocalDate.of(2023, 3, 13));

        ListExpensesCommand command = new ListExpensesCommand(Optional.empty(), Optional.of(predicate));
        expectedModel.updateFilteredExpensesList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalExpenses.GRAPE, TypicalExpenses.DURIAN), model.getFilteredExpenseList());
    }

    @Test
    public void execute_listFilterByCategoryByTimespan_showsExpense() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 1);
        ExpenseInTimespanPredicate timespanPredicate =
                new ExpenseInTimespanPredicate(LocalDate.of(2023, 3, 13));
        ExpenseInCategoryPredicate categoryPredicate = new ExpenseInCategoryPredicate(TECH);

        ListExpensesCommand command = new ListExpensesCommand(Optional.of(categoryPredicate),
            Optional.of(timespanPredicate));
        expectedModel.updateFilteredExpensesList(categoryPredicate);
        expectedModel.updateFilteredExpensesList(timespanPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalExpenses.DURIAN), model.getFilteredExpenseList());
    }

    private ExpenseInCategoryPredicate prepareCategoryPredicate(String userInput) throws ParseException {
        return new ExpenseInCategoryPredicate(ParserUtil.parseCategory(userInput));
    }

    private ExpenseInTimespanPredicate prepareTimespanPredicate(String userInput) throws ParseException {
        return new ExpenseInTimespanPredicate(ParserUtil.parseTimespan(userInput));
    }
}
