package fasttrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fasttrack.model.Budget;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.UserPrefs;
import fasttrack.testutil.TypicalCategories;

public class SetBudgetCommandTest {
    private Budget budget = new Budget(1000);
    private Budget differentBudget = new Budget(2000);
    private Model model = new ModelManager(TypicalCategories.getTypicalExpenseTracker(), 
        new UserPrefs());

    @Test
    public void equals() {
        SetBudgetCommand setBudgetCommand = new SetBudgetCommand(budget);
        SetBudgetCommand setBudgetCommandCopy = new SetBudgetCommand(budget);
        SetBudgetCommand setBudgetCommandDifferentBudget = new SetBudgetCommand(differentBudget);

        // same object -> returns true
        assertTrue(setBudgetCommand.equals(setBudgetCommand));

        // same values -> returns true
        assertTrue(setBudgetCommand.equals(setBudgetCommandCopy));

        // null -> returns false
        assertFalse(setBudgetCommand.equals(null));

        // different budget -> returns false
        assertFalse(setBudgetCommand.equals(setBudgetCommandDifferentBudget));
    }

    @Test
    public void testHashCode() {
        SetBudgetCommand setBudgetCommand = new SetBudgetCommand(budget);
        SetBudgetCommand setBudgetCommandCopy = new SetBudgetCommand(budget);
        SetBudgetCommand setBudgetCommandDifferentBudget = new SetBudgetCommand(differentBudget);

        // same object -> returns same hashcode
        assertEquals(setBudgetCommand.hashCode(), setBudgetCommand.hashCode());

        // same values -> returns same hashcode
        assertEquals(setBudgetCommand.hashCode(), setBudgetCommandCopy.hashCode());

        // different budget -> returns different hashcode
        assertNotEquals(setBudgetCommand.hashCode(), setBudgetCommandDifferentBudget.hashCode());
    }

    @Test
    public void testToString() {
        SetBudgetCommand setBudgetCommand = new SetBudgetCommand(budget);
        assertEquals("SetBudgetCommand{budget=1000.0}", setBudgetCommand.toString());
    }

    @Test
    public void execute_validBudget_success() {
        SetBudgetCommand setBudgetCommand = new SetBudgetCommand(budget);
        String expectedMessage = SetBudgetCommand.MESSAGE_SUCCESS + budget.toString();
        Model expectedModel = new ModelManager(model.getExpenseTracker(), new UserPrefs());
        CommandResult message = setBudgetCommand.execute(expectedModel);
        assertEquals(expectedMessage, message.getFeedbackToUser());
    }

}
