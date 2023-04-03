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
        this.nextExpenseDate = startDate;
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
        this.nextExpenseDate = startDate;
    }


    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        LocalDate newEndDate = LocalDate.now();
        if (endDate != null) {
            newEndDate = !endDate.isAfter(LocalDate.now()) ? endDate : LocalDate.now();
        }
        while (!nextExpenseDate.isAfter(newEndDate)) {
            expenses.add(new Expense(expenseName, expenseAmount, nextExpenseDate, expenseCategory));
            nextExpenseDate = recurringExpenseType.getNextExpenseDate(nextExpenseDate);
        }
        numberOfExpenses = expenses.size();
        return expenses;
    }

    public void setExpenseCategory(Category expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRecurringExpenseType(RecurringExpenseType recurringExpenseType) {
        this.recurringExpenseType = recurringExpenseType;
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

    public LocalDate getNextExpenseDate() {
        return nextExpenseDate;
    }

    public LocalDate getExpenseStartDate() {
        return startDate;
    }

    public RecurringExpenseType getRecurringExpenseType() {
        return recurringExpenseType;
    }

    public LocalDate getExpenseEndDate() {
        return endDate;
    }

    public String getExpenseName() {
        return expenseName;
    }



    public Category getExpenseCategory() {
        return expenseCategory;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setNextExpenseDate(LocalDate nextExpenseDate) {
        this.nextExpenseDate = nextExpenseDate;
    }

    @Override
    public String toString() {
        return "Recurring Expense: " + expenseName + " Amount: " + expenseAmount + " Category: "
                + expenseCategory + " Start Date: " + startDate + " End Date: " + endDate
                + " Recurring Expense Type: " + recurringExpenseType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        RecurringExpenseManager recurringExpense = (RecurringExpenseManager) object;

        if (!recurringExpense.startDate.isEqual(this.startDate)) {
            return false;
        }

        if (recurringExpense.expenseAmount != this.expenseAmount) {
            return false;
        }

        if (!recurringExpense.nextExpenseDate.isEqual(this.nextExpenseDate)) {
            return false;
        }

        if (recurringExpense.recurringExpenseType != this.recurringExpenseType) {
            return false;
        }

        if (!recurringExpense.expenseCategory.equals(this.expenseCategory)) {
            return false;
        }

        if (!recurringExpense.expenseName.equals(this.expenseName)) {
            return false;
        }

        if (recurringExpense.endDate == null) {
            return this.endDate == null;
        }

        if (!recurringExpense.endDate.isEqual(this.endDate)) {
            return false;
        }
        return true;
    }
}
