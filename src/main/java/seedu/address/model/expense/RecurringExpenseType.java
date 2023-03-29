package seedu.address.model.expense;

import java.time.LocalDate;
/**
 * Enum for Recurring Expense Type.
 */
public enum RecurringExpenseType {
    MONTHLY,
    WEEKLY,
    DAILY,
    YEARLY;

//
//    public static RecurringExpenseType fromType(String type) {
//        if (type == "month") {
//            return MONTHLY;
//        } else if (type == "week") {
//            return WEEKLY;
//        } else if (type == "day") {
//            return DAILY;
//        } else if (type == "year") {
//            return YEARLY;
//        }
//        throw new IllegalArgumentException("message");
//    }

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
                break;
            case WEEKLY:
                currentDate = currentDate.plusWeeks(1);
                break;
            case DAILY:
                currentDate = LocalDate.now().plusDays(1);
                break;
            case YEARLY:
                currentDate = currentDate.plusYears(1);
                break;
            default:
                break;
            }
        }
        return currentDate;
    }

//    @Override
//    public String toString() {
//        return type;
//    }
}
