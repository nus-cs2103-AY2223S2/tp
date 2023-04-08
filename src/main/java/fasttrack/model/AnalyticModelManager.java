package fasttrack.model;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.util.AnalyticsType;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * The AnalyticModelManager class represents the in-memory model of user analytics from the expense tracker.
 * It provides various methods for calculating and retrieving statistics related to user expenses.
 */
public class AnalyticModelManager implements AnalyticModel {
    private final ObservableList<Expense> allExpenses;
    private final ObservableList<Category> allCategories;

    private final DoubleProperty monthlySpent;
    private final DoubleProperty monthlyRemaining;
    private final DoubleProperty weeklySpent;
    private final DoubleProperty weeklyRemaining;
    private final DoubleProperty monthlyChange;
    private final DoubleProperty weeklyChange;
    private final DoubleProperty totalSpent;
    private final DoubleProperty budgetPercentage;
    private final DoubleProperty monthlyBudget;
    private final DoubleProperty weeklyBudget;
    private final LocalDate currentDate;

    /**
     * Initializes an AnalyticModelManager with the given expense tracker data
     * and a given date which serves as a point of reference from which analytics
     * are generated
     */
    public AnalyticModelManager(ReadOnlyExpenseTracker expenseTracker, LocalDate referenceDate) {
        requireNonNull(expenseTracker);
        requireNonNull(referenceDate);
        allExpenses = expenseTracker.getExpenseList();
        allCategories = expenseTracker.getCategoryList();
        monthlyBudget = new SimpleDoubleProperty(0);
        weeklyBudget = new SimpleDoubleProperty(0);
        monthlySpent = new SimpleDoubleProperty(0);
        monthlyRemaining = new SimpleDoubleProperty(0);
        weeklySpent = new SimpleDoubleProperty(0);
        weeklyRemaining = new SimpleDoubleProperty(0);
        weeklyChange = new SimpleDoubleProperty(0);
        monthlyChange = new SimpleDoubleProperty(0);
        totalSpent = new SimpleDoubleProperty(0);
        budgetPercentage = new SimpleDoubleProperty(0);
        currentDate = referenceDate;

        updateAllStatistics();
        updateMonthlyBudgetProperty(expenseTracker.getBudgetForStats().get());
        updateWeeklyBudgetProperty(expenseTracker.getBudgetForStats().get());

        allExpenses.addListener((ListChangeListener<Expense>) expenseChange -> {
            updateAllStatistics();
        });
        expenseTracker.getBudgetForStats().addListener((observable, oldValue, newValue) -> {
            updateMonthlyBudgetProperty(newValue);
            updateWeeklyBudgetProperty(newValue);
            updateAllStatistics();
        });
    }

    /**
     * Initializes an AnalyticModelManager with the given expense tracker data
     * Date reference for analytics is taken to be the current date and time of construction
     */
    public AnalyticModelManager(ReadOnlyExpenseTracker expenseTracker) {
        this(expenseTracker, LocalDate.now());
    }


    /**
     * Calculates the total amount spent in the current month based
     * on the filtered expenses list and updates the value of monthlySpent property
     * @return DoubleProperty representing the monthly spent amount
     */
    @Override
    public DoubleProperty getMonthlySpent() {
        double total = 0;
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        LocalDate startOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        LocalDate endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth());
        for (Expense expense: allExpenses) {
            LocalDate expenseDate = expense.getDate();
            if (!expenseDate.isBefore(startOfMonth) && !expenseDate.isAfter(endOfMonth)) {
                total += expense.getAmount();
            }
        }
        monthlySpent.set(total);
        return monthlySpent;
    }


    /**
     * Calculates remaining budget for the current month
     * and updates the value of monthlyRemaining property
     * @return DoubleProperty representing the remaining budget for the month
     */
    @Override
    public DoubleProperty getMonthlyRemaining() {
        // Do not calculate monthly remaining if budget is 0
        if (monthlyBudget.get() == 0) {
            monthlyRemaining.set(0);
            return monthlyRemaining;
        }
        double remaining = monthlyBudget.get() - monthlySpent.get();
        monthlyRemaining.set(remaining);
        return monthlyRemaining;
    }

    /**
     * Calculates total amount spent during the current week
     * based on the filtered expenses list and updates the value of weeklySpent property
     * @return DoubleProperty representing the total amount spent during the current week
     */
    @Override
    public DoubleProperty getWeeklySpent() {
        double total = 0;
        LocalDate weekStart = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate weekEnd = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        for (Expense expense: allExpenses) {
            LocalDate expenseDate = expense.getDate();
            if (!expenseDate.isBefore(weekStart) && !expenseDate.isAfter(weekEnd)) {
                total += expense.getAmount();
            }
        }
        weeklySpent.set(total);
        return weeklySpent;
    }

    /**
     * Calculates remaining budget for the current week
     * and updates the value of weeklyRemaining property
     * @return DoubleProperty representing the remaining budget for the week
     */
    @Override
    public DoubleProperty getWeeklyRemaining() {
        // Do not calculate weekly remaining if budget is 0
        if (monthlyBudget.get() == 0) {
            weeklyRemaining.set(0);
            return weeklyRemaining;
        }
        double remaining = weeklyBudget.get() - weeklySpent.get();
        weeklyRemaining.set(remaining);
        return weeklyRemaining;
    }

    /**
     * Calculates percentage change in spending from the previous week
     * to the current week and updates the value of weeklyChange property
     * @return DoubleProperty representing percentage change
     */
    @Override
    public DoubleProperty getWeeklyChange() {
        // Do not calculate weekly change if budget is 0
        if (monthlyBudget.get() == 0) {
            weeklyChange.set(0);
            return weeklyChange;
        }
        double previousWeekTotal = 0;
        LocalDate previousWeekStart = currentDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).minusWeeks(1);
        LocalDate previousWeekEnd = currentDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).minusDays(1);
        for (Expense expense: allExpenses) {
            LocalDate expenseDate = expense.getDate();
            if (!expenseDate.isBefore(previousWeekStart) && !expenseDate.isAfter(previousWeekEnd)) {
                previousWeekTotal += expense.getAmount();
            }
        }
        double change;
        if (previousWeekTotal == 0) {
            change = 0;
        } else {
            change = (weeklySpent.get() - previousWeekTotal) / previousWeekTotal;
        }
        weeklyChange.set(change * 100);
        return weeklyChange;
    }

    /**
     * Calculates percentage change in spending from the previous month
     * to the current month and updates the value of monthlyChange property.
     * @return DoubleProperty representing the percentage change
     */
    @Override
    public DoubleProperty getMonthlyChange() {
        // Do not calculate monthly change if budget is 0
        if (monthlyBudget.get() == 0) {
            monthlyChange.set(0);
            return monthlyChange;
        }
        double previousMonthTotal = 0;
        LocalDate previousMonthStart = currentDate.withDayOfMonth(1).minusMonths(1);
        LocalDate previousMonthEnd = previousMonthStart.with(TemporalAdjusters.lastDayOfMonth());
        for (Expense expense: allExpenses) {
            LocalDate expenseDate = expense.getDate();
            if (!expenseDate.isBefore(previousMonthStart) && !expenseDate.isAfter(previousMonthEnd)) {
                previousMonthTotal += expense.getAmount();
            }
        }
        double change;
        if (previousMonthTotal == 0) {
            change = 0;
        } else {
            change = (monthlySpent.get() - previousMonthTotal) / previousMonthTotal;
        }
        monthlyChange.set(change * 100);
        return monthlyChange;
    }

    /**
     * Calculates the total amount of money spent on all expenses
     * @return a DoubleProperty representing the total amount spent
     */
    @Override
    public DoubleProperty getTotalSpent() {
        double amount = allExpenses.stream().mapToDouble(Expense::getAmount).sum();
        totalSpent.set(amount);
        return totalSpent;
    }

    /**
     * Calculates and returns the percentage of the monthly budget that has been spent so far
     * The percentage is capped at 100% if it exceeds 100
     * @return a DoubleProperty representing the percentage of budget spent
     */
    @Override
    public DoubleProperty getBudgetPercentage() {
        // Do not calculate budget percentage if budget is 0
        if (monthlyBudget.get() == 0) {
            budgetPercentage.set(0);
            return budgetPercentage;
        }
        double percentage = (monthlySpent.get() / monthlyBudget.get()) * 100;
        if (percentage > 100) {
            percentage = 100;
        }
        budgetPercentage.set(percentage);
        return budgetPercentage;
    }

    /**
     * Updates the currently referenced monthly budget in the GUI
     * This method is called when the BudgetProperty in {@code ExpenseTracker} has changed.
     * @param newBudget the new Budget object value which changed
     */
    @Override
    public void updateMonthlyBudgetProperty(Budget newBudget) {
        monthlyBudget.set(newBudget.getMonthlyBudget());
    }

    /**
     * Updates the currently referenced weekly budget in the GUI
     * This method is called when the BudgetProperty in {@code ExpenseTracker} has changed.
     * @param newBudget the new Budget object value which changed
     */
    @Override
    public void updateWeeklyBudgetProperty(Budget newBudget) {
        weeklyBudget.set(newBudget.getWeeklyBudget());
    }

    /**
     * Returns the current value of the user's set monthly budget
     */
    @Override
    public double getBudget() {
        return monthlyBudget.get();
    }

    /**
     * Gets the Property of the users current monthly budget
     */
    @Override
    public DoubleProperty getMonthlyBudgetProperty() {
        return monthlyBudget;
    }

    /**
     * Returns a DoubleProperty representing analytics data for a given AnalyticsType.
     * @param type an AnalyticsType enum that specifies which type of analytics data to return
     * @return a DoubleProperty representing the requested analytics data
     * @throws IllegalArgumentException if the given analytics type is not supported
     */
    @Override
    public DoubleProperty getAnalyticsData(AnalyticsType type) throws IllegalArgumentException {
        switch(type) {
        case MONTHLY_SPENT:
            return getMonthlySpent();
        case MONTHLY_REMAINING:
            return getMonthlyRemaining();
        case WEEKLY_SPENT:
            return getWeeklySpent();
        case WEEKLY_REMAINING:
            return getWeeklyRemaining();
        case WEEKLY_CHANGE:
            return getWeeklyChange();
        case MONTHLY_CHANGE:
            return getMonthlyChange();
        case TOTAL_SPENT:
            return getTotalSpent();
        case BUDGET_PERCENTAGE:
            return getBudgetPercentage();
        default:
            throw new IllegalArgumentException("Unsupported analytics type");
        }
    }

    /**
     * A convenience method to re-calculate and update all statistics
     */
    @Override
    public void updateAllStatistics() {
        getTotalSpent();
        getMonthlySpent();
        getWeeklySpent();
        getMonthlyRemaining();
        getWeeklyRemaining();
        getWeeklyChange();
        getMonthlyChange();
        getBudgetPercentage();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof AnalyticModelManager)) {
            return false;
        }

        // state check
        AnalyticModelManager other = (AnalyticModelManager) obj;
        return allExpenses.equals(other.allExpenses)
                && allCategories.equals(other.allCategories)
                && currentDate.equals(other.currentDate);
    }
}


