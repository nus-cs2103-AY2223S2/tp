package seedu.address.model.expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents a List of Recurring Expenses in the Expense Tracker.
 */
public class RecurringExpenseList {

    Logger logger = Logger.getLogger("test");
    private final ObservableList<RecurringExpenseManager> recurringExpenseList = FXCollections.observableArrayList();

    private final ObservableList<RecurringExpenseManager> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(recurringExpenseList);

    /*
    public RecurringExpenseList() {
        recurringExpenseList = new ArrayList<>();
    }
    */

    public void addRecurringExpense(RecurringExpenseManager recurringExpense) {
        recurringExpenseList.add(recurringExpense);

    }

    public void removeRecurringExpense(RecurringExpenseManager recurringExpense) {
        recurringExpenseList.remove(recurringExpense);
    }

    public ObservableList<RecurringExpenseManager> getRecurringExpenseList() {
        return recurringExpenseList;
    }

    public ObservableList<RecurringExpenseManager> asUnmodifiableList() {
        return this.internalUnmodifiableList;
    }

    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        for (RecurringExpenseManager recurringExpense : recurringExpenseList) {
            expenses.addAll(recurringExpense.getExpenses());
        }
        return expenses;
    }

    public void setRecurringExpenseList(RecurringExpenseList replacementList) {
        recurringExpenseList.clear();
        recurringExpenseList.addAll(replacementList.getRecurringExpenseList());
    }

    public void setRecurringExpenseList(List<RecurringExpenseManager> replacementList) {
        requireAllNonNull(replacementList);
        recurringExpenseList.setAll(replacementList);
    }

    public int getSize() {
        return recurringExpenseList.size();
    }

    public boolean contains(RecurringExpenseManager recurringExpense) {
        return recurringExpenseList.contains(recurringExpense);
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (RecurringExpenseManager recurringExpense : recurringExpenseList) {
            totalAmount += recurringExpense.getTotalAmount();
        }
        return totalAmount;
    }

    public void clear() {
        recurringExpenseList.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (RecurringExpenseManager recurringExpense : recurringExpenseList) {
            sb.append(recurringExpense.toString());
        }
        return sb.toString();
    }
}
