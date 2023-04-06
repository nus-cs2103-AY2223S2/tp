package fasttrack.model.expense;

import static fasttrack.logic.parser.ParserUtil.Timespan.MONTH;
import static fasttrack.logic.parser.ParserUtil.Timespan.WEEK;
import static fasttrack.testutil.TypicalCategories.MISCCAT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.ExpenseInTimespanPredicate;

public class ExpenseInTimespanPredicateTest {

    @Test
    public void equals() {
        ExpenseInTimespanPredicate firstPredicate = new ExpenseInTimespanPredicate(WEEK);
        ExpenseInTimespanPredicate secondPredicate = new ExpenseInTimespanPredicate(MONTH);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseInTimespanPredicate firstPredicateCopy = new ExpenseInTimespanPredicate(WEEK);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseInTimespan_returnsTrue() {
        LocalDate now = LocalDate.of(2023, 3, 23);

        ExpenseInTimespanPredicate predicate =
                new ExpenseInTimespanPredicate(WEEK);
        assertTrue(predicate.test(
                new Expense("Apple", 1.5, now, MISCCAT)));

        predicate = new ExpenseInTimespanPredicate(MONTH);
        assertTrue(predicate.test(
                new Expense("Hello", 1.0, now.withDayOfMonth(1), MISCCAT)
        ));
    }

    @Test
    public void test_expenseNotInTimespan_returnsFalse() {
        Category category1 = new UserDefinedCategory("Category1", "Description1");
        Category category2 = new UserDefinedCategory("Category2", "Description2");
        Category miscCat = new MiscellaneousCategory();


        Expense expense = new Expense("test", 1, LocalDate.of(2023, 1, 1), category1);

        // Wrong Category
        ExpenseInTimespanPredicate predicate =
                new ExpenseInTimespanPredicate(MONTH);
        assertFalse(predicate.test(expense));

        expense = new Expense("test2", 2, LocalDate.of(2023, 1, 31), miscCat);
        assertFalse(predicate.test(expense));


    }
}
