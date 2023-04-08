package fasttrack.model.expense;

import static fasttrack.testutil.TypicalCategories.FOOD;
import static fasttrack.testutil.TypicalCategories.TECH;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.category.UserDefinedCategory;

public class ExpenseInCategoryPredicateTest {

    @Test
    public void equals() {

        ExpenseInCategoryPredicate firstPredicate = new ExpenseInCategoryPredicate(FOOD);
        ExpenseInCategoryPredicate secondPredicate = new ExpenseInCategoryPredicate(TECH);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseInCategoryPredicate firstPredicateCopy = new ExpenseInCategoryPredicate(FOOD);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expenseInCategory_returnsTrue() {
        Category category1 = new UserDefinedCategory("Category1", "Description1");
        Category miscCat = new MiscellaneousCategory();

        ExpenseInCategoryPredicate predicate = new ExpenseInCategoryPredicate(category1);
        assertTrue(predicate.test(
                new Expense("Apple", 1.5, LocalDate.of(2023, 3, 15), category1)));

        predicate = new ExpenseInCategoryPredicate(miscCat);
        assertTrue(predicate.test(
                new Expense("Hello", 1.0, LocalDate.now(), miscCat)
        ));
    }

    @Test
    public void test_expenseNotInCategory_returnsFalse() {
        Category category1 = new UserDefinedCategory("Category1", "Description1");
        Category category2 = new UserDefinedCategory("Category2", "Description2");
        Category miscCat = new MiscellaneousCategory();

        Expense expense = new Expense("test", 1, LocalDate.EPOCH, category1);

        // Wrong Category
        ExpenseInCategoryPredicate predicate = new ExpenseInCategoryPredicate(category2);
        assertFalse(predicate.test(expense));

        expense = new Expense("test2", 2, LocalDate.EPOCH, miscCat);
        assertFalse(predicate.test(expense));

        predicate = new ExpenseInCategoryPredicate(miscCat);
        expense = new Expense("test3", 3, LocalDate.now(), category1);
        assertFalse(predicate.test(expense));


    }
}
