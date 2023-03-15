package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of expenses
 */
public class ExpenseList implements Iterable<Expense> {

    private final ObservableList<Expense> internalListOfExpenses = FXCollections.observableArrayList();

    private final ObservableList<Expense> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalListOfExpenses);

    /**
     * Adds an expense to the internal list of expenses
     */
    public void add(Expense newExpense) {
        requireNonNull(newExpense);
        internalListOfExpenses.add(newExpense);
    }

    /**
     * Removes an expense from the internal list of expenses
     */
    public void remove(Expense toRemove) {
        requireNonNull(toRemove);
        internalListOfExpenses.remove(toRemove);
    }

    /**
     * Sets an internal list of expenses with a new list of expenses
     */
    public void setExpenseList(ExpenseList replacementList) {
        requireNonNull(replacementList);
        internalListOfExpenses.setAll(replacementList.internalListOfExpenses);
    }

    /**
     * Sets an internal list of expenses with a new list of expenses
     */
    public void setExpenseList(List<Expense> listOfExpenses) {
        requireAllNonNull(listOfExpenses);
        internalListOfExpenses.setAll(listOfExpenses);
    }

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

    @Override
    public int hashCode() {
        return internalListOfExpenses.hashCode();
    }
}
