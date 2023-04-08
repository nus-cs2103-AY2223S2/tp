package fasttrack.model;

import fasttrack.model.util.AnalyticsType;
import javafx.beans.property.DoubleProperty;

/**
 * The AnalyticModelManager class represents the in-memory model of user analytics from the expense tracker.
 * It provides various methods for calculating and retrieving statistics related to user expenses.
 */
public interface AnalyticModel {
    /**
     * Returns a DoubleProperty representing analytics data for a given AnalyticsType.
     * @param type an AnalyticsType enum that specifies which type of analytics data to return
     * @return a DoubleProperty representing the requested analytics data
     * @throws IllegalArgumentException if the given analytics type is not supported
     */
    DoubleProperty getAnalyticsData(AnalyticsType type) throws IllegalArgumentException;

    /**
     * Calculates the total amount spent in the current month based
     * on the filtered expenses list and updates the value of monthlySpent property
     * @return DoubleProperty representing the monthly spent amount
     */
    DoubleProperty getMonthlySpent();

    /**
     * Calculates remaining budget for the current month
     * and updates the value of monthlyRemaining property
     * @return DoubleProperty representing the remaining budget for the month
     */
    DoubleProperty getMonthlyRemaining();

    /**
     * Calculates total amount spent during the current week
     * based on the filtered expenses list and updates the value of weeklySpent property
     * @return DoubleProperty representing the total amount spent during the current week
     */
    DoubleProperty getWeeklySpent();

    /**
     * Calculates remaining budget for the current week
     * and updates the value of weeklyRemaining property
     * @return DoubleProperty representing the remaining budget for the week
     */
    DoubleProperty getWeeklyRemaining();

    /**
     * Calculates percentage change in spending from the previous week
     * to the current week and updates the value of weeklyChange property
     * @return DoubleProperty representing percentage change
     */
    DoubleProperty getWeeklyChange();

    /**
     * Calculates percentage change in spending from the previous month
     * to the current month and updates the value of monthlyChange property
     * @return DoubleProperty representing percentage change in spending
     */
    DoubleProperty getMonthlyChange();

    /**
     * Calculates the total amount of money spent on all expenses
     * @return a DoubleProperty representing the total amount spent
     */
    DoubleProperty getTotalSpent();

    /**
     * Calculates and returns the percentage of the monthly budget that has been spent so far
     * The percentage is capped at 100% if it exceeds 100
     * @return a DoubleProperty representing the percentage of budget spent
     */
    DoubleProperty getBudgetPercentage();

    /**
     * Updates the currently referenced monthly budget in the GUI
     * This method is called when the BudgetProperty in {@code ExpenseTracker} has changed.
     * @param newBudget the new Budget object value which changed
     */
    void updateMonthlyBudgetProperty(Budget newBudget);

    /**
     * Updates the currently referenced weekly budget in the GUI
     * This method is called when the BudgetProperty in {@code ExpenseTracker} has changed.
     * @param newBudget the new Budget object value which changed
     */
    void updateWeeklyBudgetProperty(Budget newBudget);

    /**
     * Gets the value of the users current monthly budget
     */
    double getBudget();

    /**
     * Gets the Property of the users current monthly budget
     */
    DoubleProperty getMonthlyBudgetProperty();

    /**
     * A convenience method to re-calculate and update all statistics
     */
    void updateAllStatistics();
}
