package fasttrack.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.category.UserDefinedCategory;

public class ExpenseTest {
    private final Price price = new Price("1.0");
    private final Expense expense = new Expense("test", price, null, new MiscellaneousCategory());

    @Test
    public void getName() {
        assertEquals("test", expense.getName());
    }

    @Test
    public void getAmount() {
        assertEquals(1.0, expense.getAmount());
    }

    @Test
    public void getDate() {
        assertEquals(null, expense.getDate());
    }

    @Test
    public void getCategory() {
        assertEquals(new MiscellaneousCategory(), expense.getCategory());
    }

    @Test
    public void toStringTest() {
        assertEquals("Name: test, Amount: $1.0, Date: null, Category: Miscellaneous", expense.toString());
    }

    @Test
    public void setCategory() {
        expense.setCategory(new UserDefinedCategory("new", "bleh"));
        assertEquals(new UserDefinedCategory("new", "bleh"), expense.getCategory());
    }

    @Test
    public void setAmount() {
        expense.setAmount("2.0");
        assertEquals(2.0, expense.getAmount());
    }

    @Test
    public void setName() {
        expense.setName("test2");
        assertEquals("test2", expense.getName());
    }

    @Test
    public void setDate() {
        expense.setDate(null);
        assertEquals(null, expense.getDate());
    }

    @Test
    public void equals() {
        Expense expense2 = new Expense("test", 1.0, null, new MiscellaneousCategory());
        assertEquals(expense, expense2);
    }

    @Test
    public void hashCodeTest() {
        Expense expense2 = new Expense("test", 1.0, null, new MiscellaneousCategory());
        assertEquals(expense.hashCode(), expense2.hashCode());
    }
}
