package seedu.address.model.employee;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Class represents an employee's payroll details in the database.
 */
public class Payroll {
    public static final String MESSAGE_NEGATIVE_SALARY =
            "Salary has to be a non negative integer, please try again.";

    public final int salary;
    public final int dayOfPayment;

    /**
     * Constructs a {@code Payroll} with the given salary and date of payment.
     */
    public Payroll(int salary, int dayOfPayment) {
        requireAllNonNull(salary, dayOfPayment);
        checkArgument(salary >= 0, MESSAGE_NEGATIVE_SALARY);
        this.salary = salary;
        this.dayOfPayment = dayOfPayment;
    }

    /**
     * Gets the employee's salary.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Gets the employee's date of payment.
     */
    public int getDayOfPayment() {
        return this.dayOfPayment;
    }
    @Override
    public String toString() {
        return this.salary + " paid on " + this.dayOfPayment;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Payroll
                && this.salary == ((Payroll) other).getSalary()
                && this.dayOfPayment == ((Payroll) other).getDayOfPayment());
    }
}
