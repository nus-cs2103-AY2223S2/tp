package seedu.address.model.expense;

import java.util.ArrayList;

/**
 * Represents a List of Recurring Expenses in the Expense Tracker.
 */
public class RecurringExpenseList {

    private final ArrayList<RecurringExpenseManager> recurringExpenseList;

    public RecurringExpenseList() {
        recurringExpenseList = new ArrayList<>();
    }

    public void addRecurringExpense(RecurringExpenseManager recurringExpense) {
        recurringExpenseList.add(recurringExpense);
    }

    public void removeRecurringExpense(RecurringExpenseManager recurringExpense) {
        recurringExpenseList.remove(recurringExpense);
    }

    public ArrayList<RecurringExpenseManager> getRecurringExpenseList() {
        return recurringExpenseList;
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
