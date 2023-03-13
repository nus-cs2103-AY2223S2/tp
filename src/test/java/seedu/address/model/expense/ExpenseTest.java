package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.category.UserDefinedCategory;

public class ExpenseTest {

    private final Expense expense = new Expense("test", 1.0, null, new MiscellaneousCategory());

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
        assertEquals("Expense{name='test', amount=1.0, date=null, category='Miscellaneous'}", expense.toString());
    }

    @Test
    public void setCategory() {
        expense.setCategory(new UserDefinedCategory("new", "bleh"));
        assertEquals(new UserDefinedCategory("new", "bleh"), expense.getCategory());
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
