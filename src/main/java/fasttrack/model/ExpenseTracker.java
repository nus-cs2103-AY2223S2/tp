package fasttrack.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import fasttrack.model.category.Category;
import fasttrack.model.category.UniqueCategoryList;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.ExpenseList;
import fasttrack.model.expense.RecurringExpenseList;
import fasttrack.model.expense.RecurringExpenseManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;


/**
 * Wraps all data at the expense tracker level
 * Duplicate categories are not allowed (by .isSameCategory comparison)
 */
public class ExpenseTracker implements ReadOnlyExpenseTracker {

    private final UniqueCategoryList categories;
    private final ExpenseList expenses;
    private final RecurringExpenseList recurringGenerators;
    private final ObjectProperty<Budget> simpleBudget;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    {
        categories = new UniqueCategoryList();
        expenses = new ExpenseList();
        simpleBudget = new SimpleObjectProperty<>(new Budget(0));
        recurringGenerators = new RecurringExpenseList();
    }

    public ExpenseTracker() {
    }

    /**
     * Creates an ExpenseTracker using the data in the {@code toBeCopied}
     */
    public ExpenseTracker(ReadOnlyExpenseTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the category list with {@code categories}.
     * {@code categories} must not contain duplicate categories.
     */
    public void setCategories(List<Category> categories) {
        this.categories.setCategoryList(categories);
    }

    /**
     * Replaces the contents of the expense list with {@code expenses}.
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses.setExpenseList(expenses);
    }

    public void setBudget(Budget budget) {
        this.simpleBudget.set(budget);
    }

    public void setRecurringExpenseGenerators(List<RecurringExpenseManager> recurringExpenseGenerators) {
        this.recurringGenerators.setRecurringExpenseList(recurringExpenseGenerators);
    }

    /**
     * Resets the existing data of this {@code ExpenseTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyExpenseTracker newData) {
        requireNonNull(newData);
        setExpenses(newData.getExpenseList());
        setCategories(newData.getCategoryList());
        setBudget(newData.getBudget());
        setRecurringExpenseGenerators(newData.getRecurringExpenseGenerators());
        generateRetroactiveExpenses();
        expenses.sortList();
        cleanupExpiredRecurringExpenses();
    }

    /**
     * Adds expenses retroactively for recurring expenses which have starting dates that need to be added.
     */
    public void generateRetroactiveExpenses() {
        for (RecurringExpenseManager generators : recurringGenerators.getRecurringExpenseList()) {
            for (Expense expense : generators.getExpenses()) {
                addExpense(expense);
            }
        }
        expenses.sortList();
    }

    /**
     * Removes RecurringExpenseManager objects that are expired.
     */
    public void cleanupExpiredRecurringExpenses() {
        recurringGenerators.cleanupExpiredGenerators();
    }

    //// category-level operations
    /**
     * Returns true if the given category exists in the list.
     * @param category The category to check for existence in the list.
     * @return true if the category exists in the list and false otherwise.
     */
    public boolean hasCategory(Category category) {
        requireNonNull(category);
        return categories.contains(category);
    }

    /**
     * Adds a category to the expense tracker.
     * The category must not already exist in the expense tracker.
     */
    public void addCategory(Category toAdd) {
        categories.add(toAdd);
    }

    /**
     * Deletes the given category {@code key} in the UniqueCategoryList.
     * Replaces all expenses with {@code key} with the MiscellaneousCategory object.
     * @param key
     */
    public void removeCategory(Category key) {
        categories.remove(key);
        expenses.replaceDeletedCategory(key);
        recurringGenerators.replaceDeletedCategory(key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories, expenses);
    }

    public Category getCategoryInstance(Category category) {
        for (Category c : categories.asUnmodifiableList()) {
            if (category.equals(c)) {
                return c;
            }
        }
        return null;
    }

    //// util methods

    @Override
    public String toString() {
        return expenses.asUnmodifiableList().size() + " expenses";
    }

    @Override
    public ObservableList<Category> getCategoryList() {
        return categories.asUnmodifiableList();
    }

    //// expense-level operations

    @Override
    public ObservableList<Expense> getExpenseList() {
        return expenses.asUnmodifiableList();
    }

    @Override
    public ObservableList<RecurringExpenseManager> getRecurringExpenseGenerators() {
        return recurringGenerators.asUnmodifiableList();
    }
    @Override
    public Budget getBudget() {
        return this.simpleBudget.get();
    }

    @Override
    public ObjectProperty<Budget> getBudgetForStats() {
        return this.simpleBudget;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseTracker // instanceof handles nulls
                        && expenses.equals(((ExpenseTracker) other).expenses)
                        && categories.equals(((ExpenseTracker) other).categories));
    }

    /**
     * Adds an expense to the expense tracker.
     * @param expense to be added.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
        expenses.sortList();
    }

    /**
     * Deletes an expense from the expense tracker.
     * @param expense to be deleted.
     */
    public void removeExpense(Expense expense) {
        expenses.remove(expense);
        expenses.sortList();
    }

    /**
     * Sets an expense at the specified index.
     * @param index index to be used.
     * @param expense expense to be used to overwrite the previous expense.
     */
    public void setExpense(int index, Expense expense) {
        expenses.set(index, expense);
        expenses.sortList();
    }

    public void setExpense(Expense target, Expense editedExpense) {
        expenses.setExpense(target, editedExpense);
        expenses.sortList();
    }

    /**
     * Delete all Expense.
     */
    public void clearExpense() {
        expenses.clear();
    }

    public void clearCategory() {
        categories.clear();
    }

    public void clearRecurringExpense() {
        recurringGenerators.clear();
    }

    /**
     * Returns true if the given expense exists in the list.
     * @param expense The expense to check for existence in the list.
     * @return true if the expense exists in the list and false otherwise.
     */
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenses.contains(expense);
    }

    public boolean hasRecurringExpense(RecurringExpenseManager recurringExpenseManager) {
        return recurringGenerators.contains(recurringExpenseManager);
    }

    public void addRecurringGenerator(RecurringExpenseManager generator) {
        recurringGenerators.addRecurringExpense(generator);
    }


    public void removeRecurringExpense(RecurringExpenseManager recurringExpenseManager) {
        recurringGenerators.removeRecurringExpense(recurringExpenseManager);
    }
}
