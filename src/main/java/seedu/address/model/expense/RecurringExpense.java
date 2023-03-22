package seedu.address.model.expense;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.model.category.Category;

/**
 * Represents a Recurring Expense in the Expense Tracker.
 * Expenses are repeated at a monthly interval.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 * @author shirsho-12
 * @version milestone v1.3
 */
public class RecurringExpense extends Expense {
    static final String MESSAGE_CONSTRAINTS = "Recurring Expenses should be repeated at a monthly interval.";
    private LocalDate startDate = null;
    private LocalDate endDate = null;
    

    /**
     * Constructor for RecurringExpense class.
     * @param description Name of the expense
     * @param amount      Amount of the expense
     * @param date        Date of the expense
     * @param category    Category of the expense
     */
    public RecurringExpense(String description, double amount, LocalDate date, Category category) {
        super(description, amount, date, category);
        this.startDate = date;
    }

    /**
     * Constructor for RecurringExpense class.
     * @param description Name of the expense
     * @param amount      Amount of the expense
     * @param date        Date of the expense
     * @param category    Category of the expense
     * @param endDate     End date of the expense
     */
    public RecurringExpense(String description, double amount, LocalDate date,
            Category category, LocalDate endDate) {
        super(description, amount, date, category);
        this.startDate = date;
        this.endDate = endDate;
    }

    /**
     * Constructor for RecurringExpense class.
     * @param description Name of the expense
     * @param amount      Amount of the expense
     * @param date        Date of the expense
     * @param category    Category of the expense
     * @param startDate   Start date of the expense
     * @param endDate     End date of the expense
     */
    public RecurringExpense(String description, double amount, LocalDate date,
            Category category, LocalDate startDate, LocalDate endDate) {
        super(description, amount, date, category);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getCurrentDate() {
        return super.getDate();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        super.setDate(currentDate);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecurringExpense)) {
            return false;
        }

        RecurringExpense otherExpense = (RecurringExpense) other;
        return otherExpense.getName().equals(getName())
                && otherExpense.getAmount() == getAmount()
                && otherExpense.getDate().equals(getDate())
                && otherExpense.getCategory().equals(getCategory())
                && otherExpense.getStartDate().equals(getStartDate())
                && otherExpense.getEndDate().equals(getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStartDate(), getEndDate());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate())
                .append(" Category: ")
                .append(getCategory())
                .append(" Start Date: ")
                .append(getStartDate())
                .append(" End Date: ")
                .append(getEndDate());
        return builder.toString();
    }


    // public Stream<RecurringExpense> genereateExpenses() {
    //     LocalDate today = LocalDate.now();
    //     LocalDate startDate = this.getStartDate();
    //     LocalDate endDate = this.getEndDate();
    //     // create a stream of expenses, starting from the start date of the recurring expense
    //     // to either the end date of the recurring expense or today's date, whichever is earlier
    //     return Stream.iterate(startDate, d -> d.plusMonths(1))
    //             .limit(startDate.until(endDate == null ? today : endDate,
    //                     java.time.temporal.ChronoUnit.MONTHS) + 1)
    //             .map(d -> new RecurringExpense(this.getName(),
    //                     this.getAmount(), d, this.getCategory(),
    //                     this.getStartDate(), this.getEndDate()));
    // }
}
