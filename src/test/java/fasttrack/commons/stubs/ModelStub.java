package fasttrack.commons.stubs;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;

import fasttrack.commons.core.GuiSettings;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.model.Budget;
import fasttrack.model.Model;
import fasttrack.model.ReadOnlyExpenseTracker;
import fasttrack.model.ReadOnlyUserPrefs;
import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A default model stub that have some methods failing.
 */
public class ModelStub implements Model {
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    private ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private ObservableList<RecurringExpenseManager> recurringGenerators = FXCollections.observableArrayList();

    @Override
    public void addCategory(Category toAdd) {
        requireNonNull(toAdd);
        categories.add(toAdd);
    }

    @Override
    public void addExpense(Expense toAdd) {
        requireNonNull(toAdd);
        expenses.add(toAdd);
    }

    // All other methods should throw an AssertionError
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getExpenseTrackerFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExpenseTrackerFilePath(Path expenseTrackerFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExpenseTracker(ReadOnlyExpenseTracker expenseTracker) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyExpenseTracker getExpenseTracker() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public SimpleObjectProperty<ParserUtil.Timespan> getAppliedTimeSpanFilter() {
        return null;
    }

    @Override
    public SimpleObjectProperty<Category> getAppliedCategoryFilter() {
        return null;
    }

    @Override
    public void updateTimeSpanFilter(ParserUtil.Timespan timeSpan) {

    }

    @Override
    public void updateCategoryFilter(Category category) {

    }


    @Override
    public void deleteExpense(Expense expense) {
        // Delete the expense from the list
        expenses.remove(expense);
    }

    @Override
    public void clearExpense() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        // set the expense in the list
        int index = expenses.indexOf(target);
        expenses.set(index, editedExpense);
    }

    @Override
    public void setExpense(int index, Expense newExpense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasExpense(Expense expense) {
        // Check if the expense is in the list
        return expenses.contains(expense);
    }

    @Override
    public void updateFilteredExpensesList(Predicate<Expense> predicate) {
        // update the list
        expenses.removeIf(predicate);
    }

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        // get the filtered list
        return expenses;
    }

    @Override
    public void deleteCategory(Category target) {
        // Delete the category from the list
        categories.remove(target);
        // change the category of the expenses in the list
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(target)) {
                expense.setCategory(new MiscellaneousCategory());
            }
        }
    }

    @Override
    public void clearCategory() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCategory(Category category) {
        // Check if the category is in the list
        return categories.contains(category);
    }

    @Override
    public ObservableList<Category> getFilteredCategoryList() {
        return categories;
    }

    @Override
    public void updateFilteredCategoryList(Predicate<Category> predicate) {
        // update the list
        categories.removeIf(predicate);
    }

    @Override
    public Category getCategoryInstance(Category categoryName) {
        // get the category instance
        return null;
    }

    @Override
    public void setBudget(Budget budget) {
        throw new AssertionError("This method should not be called.");
    }


    public boolean hasRecurringExpense(RecurringExpenseManager recurringExpenseManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addRecurringGenerator(RecurringExpenseManager recurringExpenseManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<RecurringExpenseManager> getRecurringExpenseGenerators() {
        return recurringGenerators;
    }

    @Override
    public void updateFilteredRecurringGenerators(Predicate<RecurringExpenseManager> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Delete all recurring expense generators.
     */
    @Override
    public void clearRecurringExpenseGenerator() {

    }

    @Override
    public void deleteRecurringExpense(RecurringExpenseManager recurringExpenseManager) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addRetroactiveExpenses() {
        throw new AssertionError("This method should not be called.");
    }

}
