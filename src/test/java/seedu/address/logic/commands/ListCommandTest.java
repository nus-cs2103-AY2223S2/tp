package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.testutil.TypicalExpenses.CHERRY;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.ExpenseInCategoryPredicate;
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
        ListCommand command = new ListCommand(Optional.empty(), Optional.empty());
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
        ExpenseInCategoryPredicate predicate = null;
        try {
            predicate = preparePredicate("food");
        } catch (ParseException e) {
            fail("Unexpected exception was thrown");
        }
        ListCommand command = new ListCommand(Optional.of(predicate), Optional.empty());
        expectedModel.updateFilteredExpensesList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalExpenses.APPLE, TypicalExpenses.BANANA), model.getFilteredExpenseList());
    }

    private ExpenseInCategoryPredicate preparePredicate(String userInput) throws ParseException {
        return new ExpenseInCategoryPredicate(ParserUtil.parseCategory(userInput));
    }
}
