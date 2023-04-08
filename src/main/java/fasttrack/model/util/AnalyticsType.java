package fasttrack.model.util;

/**
 * Represents different types of analytics that can be calculated in FastTrack
 * MONTHLY_SPENT: Represents the total amount spent in a month.
 * MONTHLY_REMAINING: Represents the remaining budget for the month.
 * WEEKLY_SPENT: Represents the total amount spent in a week.
 * WEEKLY_REMAINING: Represents the remaining budget for the week.
 * WEEKLY_CHANGE: Represents the change in spending from the previous week.
 * MONTHLY_CHANGE: Represents the change in spending from the previous month.
 * TOTAL_SPENT: Represents the total amount spent overall.
 * BUDGET_PERCENTAGE: Represents the percentage of the budget that has been spent.
 */
public enum AnalyticsType {
    MONTHLY_SPENT,
    MONTHLY_REMAINING,
    WEEKLY_SPENT,
    WEEKLY_REMAINING,
    WEEKLY_CHANGE,
    MONTHLY_CHANGE,
    TOTAL_SPENT,
    BUDGET_PERCENTAGE
}
