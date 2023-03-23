package seedu.address.commons.stubs;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyExpenseTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.category.Category;
import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.expense.Expense;

/**
 * A default model stub that have some of the methods failing.
 */
public class ModelStub implements Model {
    private ArrayList<Category> categories = new ArrayList<Category>();
    private ArrayList<Expense> expenses = new ArrayList<Expense>();

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

    /*
     * get the list of categories
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /*
     * get the list of expenses
     */
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    /*
     * get the list of expenses in a category
     */
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    /*
     * get the list of expenses in a category
     */
    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    /*
     * set the category of an expense
     */
    public void setCategory(Category target, Category editedCategory) {
        int index = categories.indexOf(target);
        categories.set(index, editedCategory);
    }
    // All other methods should throw an UnsupportedOperationException

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUserPrefs'");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserPrefs'");
    }

    @Override
    public GuiSettings getGuiSettings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGuiSettings'");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGuiSettings'");
    }

    @Override
    public Path getExpenseTrackerFilePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExpenseTrackerFilePath'");
    }

    @Override
    public void setExpenseTrackerFilePath(Path expenseTrackerFilePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setExpenseTrackerFilePath'");
    }

    @Override
    public void setExpenseTracker(ReadOnlyExpenseTracker expenseTracker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setExpenseTracker'");
    }

    @Override
    public ReadOnlyExpenseTracker getExpenseTracker() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExpenseTracker'");
    }



    @Override
    public void deleteExpense(Expense expense) {
        // Delete the expense from the list
        expenses.remove(expense);
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        // set the expense in the list
        int index = expenses.indexOf(target);
        expenses.set(index, editedExpense);
    }

    @Override
    public boolean hasExpense(Expense expense) {
        // Check if the expense is in the list
        return expenses.contains(expense);
    }

    @Override
    public int getFilteredExpenseListCount() {
        // get the size of the list
        return expenses.size();
    }

    @Override
    public void updateFilteredExpensesList(Predicate<Expense> predicate) {
        // update the list
        expenses.removeIf(predicate);
    }

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        // get the filtered list
        return null;
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
    public boolean hasCategory(Category category) {
        // Check if the category is in the list
        return categories.contains(category);
    }

    @Override
    public ObservableList<Category> getFilteredCategoryList() {
        // filter the list
        return null;
    }

    @Override
    public void updateFilteredCategoryList(Predicate<Category> predicate) {
        // update the list
        categories.removeIf(predicate);
    }

    @Override
    public Category getCategoryInstance(String categoryName) {
        // get the category instance
        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

}
