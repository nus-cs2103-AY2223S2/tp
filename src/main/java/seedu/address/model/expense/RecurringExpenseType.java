package seedu.address.model.expense;

import java.time.LocalDate;
/**
 * Enum for Recurring Expense Type.
 */
enum RecurringExpenseType {
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
        while (currentDate.isBefore(LocalDate.now())) {
            switch (this) {
                case MONTHLY:
                currentDate = currentDate.plusMonths(1);
                case WEEKLY:
                currentDate = currentDate.plusWeeks(1);
                case DAILY:
                currentDate = LocalDate.now().plusDays(1);
                case YEARLY:
                currentDate = currentDate.plusYears(1);
                }
            } 
        return currentDate;
    }
}
