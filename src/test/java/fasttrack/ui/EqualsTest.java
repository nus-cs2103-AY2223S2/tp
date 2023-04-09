package fasttrack.ui;

import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.testutil.TypicalCategories;
import fasttrack.testutil.TypicalExpenses;

/**
 * Check equality for every UI component.
 */
public class EqualsTest {

    @Test
    public void categoryCardEquals() {
        Category category = TypicalCategories.FOOD;
        CategoryCard categoryCard = new CategoryCard(category, 0, 0);
        CategoryCard categoryCardCopy = new CategoryCard(category, 0, 0);
        assertEquals(categoryCard, categoryCardCopy);
    }

    @Test
    public void expenseCardEquals() {
        Expense expense = TypicalExpenses.APPLE;
        ExpenseCard expenseCard = new ExpenseCard(expense, 0);
        ExpenseCard expenseCardCopy = new ExpenseCard(expense, 0);
        assertEquals(expenseCard, expenseCardCopy);
    }

    @Test
    public void categoryListPanelEquals() {
        CategoryListPanel categoryListPanel = new CategoryListPanel(TypicalCategories.TYPICAL_CATEGORIES,
                TypicalExpenses.TYPICAL_EXPENSES);
        CategoryListPanel categoryListPanelCopy = new CategoryListPanel(TypicalCategories.TYPICAL_CATEGORIES,
                TypicalExpenses.TYPICAL_EXPENSES);
        assertEquals(categoryListPanel, categoryListPanelCopy);
    }

    @Test
    public void expenseListPanelEquals() {
        ExpenseListPanel expenseListPanel = new ExpenseListPanel(TypicalExpenses.TYPICAL_EXPENSES);
        ExpenseListPanel expenseListPanelCopy = new ExpenseListPanel(TypicalExpenses.TYPICAL_EXPENSES);
        assertEquals(expenseListPanel, expenseListPanelCopy);
    }
}
