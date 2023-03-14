package seedu.address.model.expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.category.Category;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class ExpenseList implements Iterable<Expense> {

    private final ObservableList<Expense> internalListOfExpenses = FXCollections.observableArrayList();

    private final ObservableList<Expense> internalUnmodifiableList = FXCollections.
            unmodifiableObservableList(internalListOfExpenses);


    public void add(Expense newExpense) {
        requireNonNull(newExpense);
        internalListOfExpenses.add(newExpense);
    }

    public void remove(Expense toRemove) {
        requireNonNull(toRemove);
        internalListOfExpenses.remove(toRemove);
    }

    public void setExpenseList(ExpenseList replacementList) {
        requireNonNull(replacementList);
        internalListOfExpenses.setAll(replacementList.internalListOfExpenses);
    }

    public void setExpenseList(List<Expense> listOfCategories) {
        requireAllNonNull(listOfCategories);
        internalListOfExpenses.setAll(listOfCategories);
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
