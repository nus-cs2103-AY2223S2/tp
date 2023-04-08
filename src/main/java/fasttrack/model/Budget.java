package fasttrack.model;

/**
 * Represents a budget which users of FastTrack can set.
 */
public class Budget {
    private double monthBudget;

    public Budget(double budget) {
        this.monthBudget = budget;
    }

    /**
     * Returns the monthlyBudget.
     * @return double
     */
    public double getMonthlyBudget() {
        return this.monthBudget;
    }

    /**
     * Returns the weekly budget.
     * @return double
     */
    public double getWeeklyBudget() {
        return this.monthBudget / 4;
    }

    /**
     * Set monthlyBudget.
     * @param monthBudget
     */
    public void setMonthlyBudget(double monthBudget) {
        this.monthBudget = monthBudget;
    }

    @Override
    public String toString() {
        return Double.toString(this.monthBudget);
    }
}
