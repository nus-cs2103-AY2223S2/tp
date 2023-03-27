package seedu.address.model.expense;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.address.model.category.Category;

/**
 * Represents a Recurring Expense in the Expense Tracker.
 */
public class RecurringExpenseManager {
    private String expenseName;
    private double expenseAmount;
    private Category expenseCategory;
    private int numberOfExpenses = 0;
    private LocalDate nextExpenseDate = null;
    private LocalDate startDate;
    private LocalDate endDate = null;
    private RecurringExpenseType recurringExpenseType;

    /**
     * The constructor for the RecurringExpenseManager class with a start and end date.
     * @param expenseName The name of the recurring expense.
     * @param expenseAmount The amount of the recurring expense.
     * @param expenseCategory The category of the recurring expense.
     * @param startDate The start date of the recurring expense.
     * @param endDate The end date of the recurring expense.
     * @param recurringExpenseType The type of the recurring expense.
     */
    public RecurringExpenseManager(String expenseName, double expenseAmount,
            Category expenseCategory, LocalDate startDate, LocalDate endDate,
            RecurringExpenseType recurringExpenseType) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseCategory = expenseCategory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recurringExpenseType = recurringExpenseType;
    }

    /**
     * The constructor for the RecurringExpenseManager class with no end date.
     * @param expenseName The name of the recurring expense.
     * @param expenseAmount The amount of the recurring expense.
     * @param expenseCategory The category of the recurring expense.
     * @param startDate The start date of the recurring expense.
     * @param recurringExpenseType The type of the recurring expense.
     */
    public RecurringExpenseManager(String expenseName, double expenseAmount,
            Category expenseCategory, LocalDate startDate, RecurringExpenseType recurringExpenseType) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseCategory = expenseCategory;
        this.startDate = startDate;
        this.recurringExpenseType = recurringExpenseType;
        // Get the next expense date from recurringExpenseType
        this.nextExpenseDate = recurringExpenseType.getNextExpenseDate(startDate);
    }


    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        LocalDate currentExpense = startDate;
        LocalDate lastDate = endDate == null ? nextExpenseDate : endDate;
        while (currentExpense.isBefore(lastDate)) {
            expenses.add(new Expense(expenseName, expenseAmount, currentExpense, expenseCategory));
            currentExpense = recurringExpenseType.getNextExpenseDate(currentExpense);
        }
        nextExpenseDate = recurringExpenseType.getNextExpenseDate(nextExpenseDate);
        if (LocalDate.now().isAfter(nextExpenseDate)) {
            nextExpenseDate = recurringExpenseType.getNextExpenseDate(nextExpenseDate);
            Expense nextExpense = new Expense(expenseName, expenseAmount, nextExpenseDate, expenseCategory);
            // add the next expense if it is not already in the list
            if (!expenses.contains(nextExpense)) {
                expenses.add(nextExpense);
            }
        }
        startDate = nextExpenseDate;
        numberOfExpenses = expenses.size();
        return expenses;
    }

    public void editRecurringExpenseName(String name) {
        expenseName = name;
    }

    public void editRecurringExpenseAmount(double amount) {
        expenseAmount = amount;
    }

    public void editRecurringExpenseCategory(Category category) {
        expenseCategory = category;
    }

    public int getNumberOfExpenses() {
        return numberOfExpenses;
    }

    public double getTotalAmount() {
        return expenseAmount * numberOfExpenses;
    }

    @Override
    public String toString() {
        return "Recurring Expense: " + expenseName + " Amount: " + expenseAmount + " Category: "
                + expenseCategory + " Start Date: " + startDate + " End Date: " + endDate
                + " Recurring Expense Type: " + recurringExpenseType;
    }
}
