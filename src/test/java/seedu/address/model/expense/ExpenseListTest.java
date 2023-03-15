package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseList;

public class ExpenseListTest {
    private final ExpenseList expenseList = new ExpenseList();
    private final LocalDate date = LocalDate.now();
    private final LocalDate newDate = LocalDate.of(2020, 1, 1);
    private final UserDefinedCategory category = new UserDefinedCategory("test", "description");
    private final MiscellaneousCategory miscellaneousCategory = new MiscellaneousCategory();

    @Test
    public void addExpense() {
        Expense expense = new Expense("test", 1.0, date, category);
        expenseList.add(expense);
        assertEquals(expenseList.asUnmodifiableList().get(0), expense);
    }

    @Test
    public void removeExpense() {
        Expense expense = new Expense("test", 1.0, date, category);
        expenseList.add(expense);
        expenseList.remove(expense);
        assertEquals(expenseList.asUnmodifiableList().size(), 0);
    }

    @Test
    public void setExpenseList() {
        Expense expense = new Expense("test", 1.0, date, category);
        Expense newExpense = new Expense("test", 1.0, newDate, miscellaneousCategory);
        expenseList.add(expense);
        ExpenseList newExpenseList = new ExpenseList();
        newExpenseList.add(newExpense);
        expenseList.setExpenseList(newExpenseList);
        assertEquals(expenseList.asUnmodifiableList().get(0), newExpense);
    }

    @Test
    public void asUnmodifiableList() {
        Expense expense = new Expense("test", 1.0, date, category);
        expenseList.add(expense);
        assertEquals(expenseList.asUnmodifiableList().get(0), expense);
    }

    @Test
    public void getExpenseListSize() {
        Expense expense = new Expense("test", 1.0, date, category);
        expenseList.add(expense);
        assertEquals(expenseList.getSize(), 1);
    }

    @Test
    public void getExpenseListTotal() {
        Expense expense = new Expense("test", 1.0, date, category);
        expenseList.add(expense);
        assertEquals(expenseList.getTotalAmount(), 1.0);
    }

    @Test
    public void getExpenseListTotalWithMultipleExpenses() {
        Expense expense = new Expense("test", 1.0, date, category);
        Expense expense2 = new Expense("test", 2.0, date, category);
        expenseList.add(expense);
        expenseList.add(expense2);
        assertEquals(expenseList.getTotalAmount(), 3.0);
    }
    
}
