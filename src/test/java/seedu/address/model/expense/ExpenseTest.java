package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExpenseTest {

    private final Expense expense = new Expense("test", 1.0, null, "test");

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
        assertEquals("test", expense.getCategory());
    }

    @Test
    public void toStringTest() {
        assertEquals("Expense{name='test', amount=1.0, date=null, category='test'}", expense.toString());
    }

    @Test
    public void setCategory() {
        expense.setCategory("test2");
        assertEquals("test2", expense.getCategory());
    }

    @Test
    public void setAmount() {
        expense.setAmount(2.0);
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
}
