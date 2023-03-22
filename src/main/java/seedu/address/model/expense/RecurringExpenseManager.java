package seedu.address.model.expense;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.address.model.category.Category;

/**
 * Represents a Recurring Expense in the Expense Tracker.
 */
public class RecurringExpenseManager {
    RecurringExpense recurringExpense;
    ArrayList<Expense> recurringExpenseList;
    ArrayList<LocalDate> recurringExpenseDateList;
    ArrayList<LocalDate> ignoredDateList;

    /**
     * Constructor for RecurringExpense class.
     * @param recurringExpense RecurringExpense object
     */
    public RecurringExpenseManager(RecurringExpense recurringExpense) {
        this.recurringExpense = recurringExpense;
        recurringExpenseList = new ArrayList<>();
        recurringExpenseDateList = new ArrayList<>();
        populateDateList();
    }

    /**
     * Constructor for RecurringExpense class.
     * @param Expense     Expense object
     * @param startDate   Start date of the expense
     * @param endDate     End date of the expense
     */
    public RecurringExpenseManager(Expense expense, LocalDate startDate, LocalDate endDate) {
        recurringExpense = new RecurringExpense(expense.getName(), expense.getAmount(), 
                expense.getDate(), expense.getCategory(), startDate, endDate);
        recurringExpenseList = new ArrayList<>();
        recurringExpenseDateList = new ArrayList<>();
        populateDateList();
    }

    /**
     * Constructor for RecurringExpense class.
     * @param ignoredDateList List of ignored dates
     * @param recurringExpenseList List of recurring expenses
     * @param recurringExpenseDateList List of recurring expense dates
     * @param recurringExpense RecurringExpense object
     */
    public RecurringExpenseManager(ArrayList<LocalDate> recurringExpenseDateList, 
            RecurringExpense recurringExpense,
            ArrayList<LocalDate> ignoredDateList, 
            ArrayList<Expense> recurringExpenseList) {
        this.ignoredDateList = ignoredDateList;
        this.recurringExpenseList = recurringExpenseList;
        this.recurringExpenseDateList = recurringExpenseDateList;
        this.recurringExpense = recurringExpense;
    }

    private void populateDateList() {
        LocalDate currentDate = recurringExpense.getCurrentDate();
        LocalDate endDate = recurringExpense.getEndDate();
        // Populate the date list, excluding the ignored dates
        while (currentDate.isBefore(endDate)) {
            if (!ignoredDateList.contains(currentDate)) {
                addDate(currentDate);
                addExpense(new RecurringExpense(recurringExpense.getName(), 
                        recurringExpense.getAmount(), currentDate, 
                        recurringExpense.getCategory(), recurringExpense.getStartDate(),
                        recurringExpense.getEndDate()));
            }
            currentDate = currentDate.plusMonths(1);
        }
    }

    public void addExpense(Expense expense) {
        recurringExpenseList.add(expense);
        if (!recurringExpenseDateList.contains(expense.getDate())) {
            recurringExpenseDateList.add(expense.getDate());
        }
        if (ignoredDateList.contains(expense.getDate())) {
            ignoredDateList.remove(expense.getDate());
        }
    }

    public void addDate(LocalDate date) {
        recurringExpenseDateList.add(date);
    }

    public void removeDate(LocalDate date) {
        recurringExpenseDateList.remove(date);
    }

    public void removeExpense(Expense expense) {
        if (recurringExpenseList.contains(expense)) {
            removeDate(expense.getDate());
            recurringExpenseList.remove(expense);
        }
    }
    
    public ArrayList<Expense> getRecurringExpenseList() {
        return recurringExpenseList;
    }

    public void editRecurringExpenseName(String name) {
        recurringExpense.setName(name);
        for (Expense expense : recurringExpenseList) {
            expense.setName(name);
        }
    }

    public void editRecurringExpenseAmount(double amount) {
        recurringExpense.setAmount(amount);
        for (Expense expense : recurringExpenseList) {
            expense.setAmount(amount);
        }
    }

    public void editRecurringExpenseCategory(Category category) {
        recurringExpense.setCategory(category);
        for (Expense expense : recurringExpenseList) {
            expense.setCategory(category);
        }
    }

    public void editRecurringExpenseDate(LocalDate date) {
        recurringExpense.setDate(date);
        for (Expense expense : recurringExpenseList) {
            expense.setDate(date);
        }
    }
    
}
