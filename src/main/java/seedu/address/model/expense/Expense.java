package seedu.address.model.expense;

import java.util.Date;
import java.util.Locale.Category;

/**
 * Represents an Expense in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 * 
 * @author shirsho-12
 * @version 1.0
 */
public class Expense {
    private String name;
    private double amount;
    private Date date;
    private Category category;

    /**
     * Constructor for Expense class.
     * 
     * @param name     Name of the expense
     * @param amount   Amount of the expense
     * @param date     Date of the expense
     * @param category Category of the expense
     */
    public Expense(String name, double amount, Date date, Category category) {
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

    public Date getDate() {
        return date;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
