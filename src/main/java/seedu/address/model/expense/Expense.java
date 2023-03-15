package seedu.address.model.expense;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.category.Category;

/**
 * Represents an Expense in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 * @author shirsho-12
 * @version 1.0
 */
public class Expense {

    public static final String MESSAGE_CONSTRAINTS = "Expense names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private String name;
    private double amount;
    private LocalDate date;
    private Category category;

    /**
     * Constructor for Expense class.
     * @param name     Name of the expense
     * @param amount   Amount of the expense
     * @param date     Date of the expense
     * @param category Category of the expense
     */
    public Expense(String name, double amount, LocalDate date, Category category) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Expense{"
                + "name='" + name + '\''
                + ", amount=" + amount
                + ", date=" + date
                + ", category='" + category + '\''
                + '}';
    }

    /**
     * Returns true if a given string is a valid expense name.
     * @param test String to be tested
     */
    public static boolean isValidCategory(Category category) {
        return Category.isValidCategoryName(category.getCategoryName());
    }

    /**
     * Returns true if a given string is a valid expense name.
     * @param test String to be tested
     */
    public static boolean isValidName(String name) {
        return name.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Expense expense = (Expense) o;
        if (Double.compare(expense.amount, amount) != 0) {
            return false;
        }
        if (name != null ? !name.equals(expense.name) : expense.name != null) {
            return false;
        }
        if (date != null ? !date.equals(expense.date) : expense.date != null) {
            return false;
        }
        return category != null ? category.equals(expense.category) : expense.category == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result
                + (int) (temp ^ (temp >>> 32));
        result = 31 * result
                + (date != null ? date.hashCode() : 0);
        result = 31 * result
                + (category != null ? category.hashCode() : 0);
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
