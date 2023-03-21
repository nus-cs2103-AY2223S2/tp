package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.logging.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;
import seedu.address.model.util.AnalyticsType;

/**
 * Represents the in-memory model of user analytics from the expense tracker
 */
public class AnalyticModelManager implements AnalyticModel {
    private static final Logger logger = LogsCenter.getLogger(AnalyticModelManager.class);

    private final ObservableList<Expense> filteredExpenses;
    private final ObservableList<Category> filteredCategories;

    //TODO link this to Budget class
    private final double budget = 1000;

    private DoubleProperty monthlySpent;
    private DoubleProperty monthlyRemaining;
    private DoubleProperty weeklySpent;
    private DoubleProperty weeklyRemaining;
    private DoubleProperty monthlyChange;
    private DoubleProperty weeklyChange;
    private DoubleProperty totalSpent;
    private DoubleProperty budgetPercentage;

    /**
     * Initializes an AnalyticModelManager with the given expense list data.
     */
    public AnalyticModelManager(Logic logic) {
        requireNonNull(logic);
        filteredExpenses = logic.getFilteredExpenseList();
        filteredCategories = logic.getFilteredCategoryList();
        monthlySpent = new SimpleDoubleProperty();
        monthlyRemaining = new SimpleDoubleProperty();
        weeklySpent = new SimpleDoubleProperty();
        weeklyRemaining = new SimpleDoubleProperty();
        weeklyChange = new SimpleDoubleProperty();
        monthlyChange = new SimpleDoubleProperty();
        totalSpent = new SimpleDoubleProperty();
        budgetPercentage = new SimpleDoubleProperty();
        updateAllStatistics();
        filteredExpenses.addListener((ListChangeListener<Expense>) expenseChange -> {
            updateAllStatistics();
        });

    }

    private DoubleProperty getMonthlySpent() {
        double total = 0;
        int currentMonth = LocalDate.now().getMonthValue();
        for (Expense expense: filteredExpenses) {
            if (expense.getDate().getMonthValue() == currentMonth) {
                total += expense.getAmount();
            }
        }
        monthlySpent.set(total);
        return monthlySpent;
    }

    private DoubleProperty getMonthlyRemaining() {
        double remaining = budget - monthlySpent.get();
        monthlyRemaining.set(remaining);
        return monthlyRemaining;
    }

    private DoubleProperty getWeeklySpent() {
        double total = 0;
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate weekEnd = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        for (Expense expense: filteredExpenses) {
            LocalDate expenseDate = expense.getDate();
            if (!expenseDate.isBefore(weekStart) && !expenseDate.isAfter(weekEnd)) {
                total += expense.getAmount();
            }
        }
        weeklySpent.set(total);
        return weeklySpent;
    }

    private DoubleProperty getWeeklyRemaining() {
        double remaining = (budget / 4) - weeklySpent.get();
        weeklyRemaining.set(remaining);
        return weeklyRemaining;
    }

    private DoubleProperty getWeeklyChange() {
        double previousWeekTotal = 0;
        LocalDate currentDate = LocalDate.now();
        LocalDate previousWeekStart = currentDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).minusWeeks(1);
        LocalDate previousWeekEnd = currentDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).minusDays(1);
        for (Expense expense: filteredExpenses) {
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

    private DoubleProperty getMonthlyChange() {
        double previousMonthTotal = 0;
        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthStart = currentDate.withDayOfMonth(1).minusMonths(1);
        LocalDate previousMonthEnd = previousMonthStart.with(TemporalAdjusters.lastDayOfMonth());
        for (Expense expense: filteredExpenses) {
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

    private DoubleProperty getTotalSpent() {
        double amount = filteredExpenses.stream().mapToDouble(Expense::getAmount).sum();
        totalSpent.set(amount);
        return totalSpent;
    }

    private DoubleProperty getBudgetPercentage() {
        double percentage = (totalSpent.get() / budget) * 100;
        if (percentage > 100) {
            percentage = 100;
        }
        budgetPercentage.set(percentage);
        return budgetPercentage;
    }

    @Override
    public DoubleProperty getAnalyticsData(AnalyticsType type) {
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


    private void updateAllStatistics() {
        getMonthlySpent();
        getMonthlyRemaining();
        getWeeklySpent();
        getWeeklyRemaining();
        getWeeklyChange();
        getMonthlyChange();
        getTotalSpent();
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
        return  filteredExpenses.equals(other.filteredExpenses)
                && filteredCategories.equals(other.filteredCategories);
    }
}


