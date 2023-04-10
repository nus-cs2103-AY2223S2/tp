package fasttrack.model.expense;

import java.time.LocalDate;
/**
 * Enum for Recurring Expense Type.
 */
public enum RecurringExpenseType {
    MONTHLY,
    WEEKLY,
    DAILY,
    YEARLY;

    /**
     * Returns the next expense date based on the recurring expense type.
     * @param currentDate The current date.
     * @return The next expense date.
     */
    public LocalDate getNextExpenseDate(LocalDate currentDate) {
        switch (this) {
        case MONTHLY:
            currentDate = currentDate.plusMonths(1);
            break;
        case WEEKLY:
            currentDate = currentDate.plusWeeks(1);
            break;
        case DAILY:
            currentDate = currentDate.plusDays(1);
            break;
        case YEARLY:
            currentDate = currentDate.plusYears(1);
            break;
        default:
            break;
        }
        return currentDate;
    }
}
