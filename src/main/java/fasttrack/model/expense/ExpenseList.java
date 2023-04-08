package fasttrack.model.expense;

import static fasttrack.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of expenses that enforces uniqueness between its elements and
 * does not allow nulls.
 */
public class ExpenseList implements Iterable<Expense> {

    private final ObservableList<Expense> internalListOfExpenses = FXCollections.observableArrayList();
    private final ObservableList<Expense> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalListOfExpenses);
    private final Category misc = new MiscellaneousCategory();

    /**
     * Adds an expense to the internal list of expenses
     * @param newExpense Expense to add
     */
    public void add(Expense newExpense) {
        requireNonNull(newExpense);
        internalListOfExpenses.add(newExpense);
    }

    /**
     * Removes an expense from the internal list of expenses
     * @param toRemove Expense to remove
     */
    public void remove(Expense toRemove) {
        requireNonNull(toRemove);
        internalListOfExpenses.remove(toRemove);
    }

    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);
        int index = internalListOfExpenses.indexOf(target);

        internalListOfExpenses.set(index, editedExpense);
    }

    public void set(int index, Expense newExpense) {
        internalListOfExpenses.set(index, newExpense);
    }

    /**
     * Replace expenses with {@code target} category with Misc object
     * @param target
     */
    public void replaceDeletedCategory(Category target) {
        requireNonNull(target);
        internalListOfExpenses.forEach(expense -> {
            if (expense.getCategory().equals(target)) {
                expense.setCategory(misc);
            }
        });
    }

    /**
     * Sets an internal list of expenses with a new list of expenses
     * @param replacementList List of expenses to replace the current list
     */
    public void setExpenseList(ExpenseList replacementList) {
        requireNonNull(replacementList);
        internalListOfExpenses.setAll(replacementList.internalListOfExpenses);
    }

    /**
     * Sets an internal list of expenses with a new list of expenses
     * @param listOfExpenses List of expenses to replace the current list
     */
    public void setExpenseList(List<Expense> listOfExpenses) {
        requireAllNonNull(listOfExpenses);
        internalListOfExpenses.setAll(listOfExpenses);
    }

    /**
     * Returns the size of the internal list of expenses
     * @return Size of the internal list of expenses
     */
    public int getSize() {
        return internalListOfExpenses.size();
    }

    /**
     * Returns the total amount of the internal list of expenses
     * @return Total amount of the internal list of expenses
     */
    public double getTotalAmount() {
        double totalAmount = 0;
        for (Expense expense : internalListOfExpenses) {
            totalAmount += expense.getAmount();
        }
        return totalAmount;
    }

    /**
     * Delete all expense.
     */
    public void clear() {
        internalListOfExpenses.clear();
        internalUnmodifiableList.clear();
    }

    /**
     * Sorts the internal list of expenses by date.
     */
    public void sortList() {
        internalListOfExpenses.sort((o1, o2) -> o1.getDate().isAfter(o2.getDate()) ? -1
                : o1.getDate().isEqual(o2.getDate()) ? 0 : 1);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Expense> asUnmodifiableList() {
        return this.internalUnmodifiableList;
    }

    @Override
    public Iterator<Expense> iterator() {
        return this.internalListOfExpenses.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExpenseList)) {
            return false;
        }
        ExpenseList otherInUniqueList = (ExpenseList) other;
        return this.internalListOfExpenses.equals(otherInUniqueList.internalListOfExpenses);
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     * @param expense Expense to check
     */
    public boolean contains(Expense expense) {
        requireNonNull(expense);
        return internalListOfExpenses.stream().anyMatch(expense::equals);
    }

    @Override
    public int hashCode() {
        return internalListOfExpenses.hashCode();
    }
}
