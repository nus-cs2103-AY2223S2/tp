package seedu.address.model.employee;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Class represents an employee's payroll details in the database.
 */
public class Payroll {
    public static final int MAX_SALARY = 100000;
    public static final String MESSAGE_INVALID_SALARY =
            "Salary has to be an integer between 1 and " + MAX_SALARY + " (inclusive).";
    public static final String MESSAGE_INVALID_DATE_OF_PAYMENT =
            "Day of payment has to be an integer between 1 and 28 (inclusive).";
    public static final String MESSAGE_CONSTRAINTS =
            "Payroll should contain two integers (salary and day of payment) separated by a space.\n"
                    + MESSAGE_INVALID_SALARY + "\n" + MESSAGE_INVALID_DATE_OF_PAYMENT;
    public static final String FILTER_PARAMETER = "pr";
    public final int salary;
    public final int dayOfPayment;

    /**
     * Constructs a {@code Payroll} with the given salary and date of payment.
     */
    public Payroll(int salary, int dayOfPayment) {
        requireAllNonNull(salary, dayOfPayment);
        checkArgument((0 <= salary) && (salary <= MAX_SALARY), MESSAGE_INVALID_SALARY);
        checkArgument((1 <= dayOfPayment) && (dayOfPayment <= 28), MESSAGE_INVALID_DATE_OF_PAYMENT);
        this.salary = salary;
        this.dayOfPayment = dayOfPayment;
    }

    /**
     * Constructs a {@code Payroll} with the given salary and date of payment string.
     */
    public Payroll(String input) throws IllegalValueException {
        requireAllNonNull(input);
        if (!isValidPayroll(input)) {
            throw new IllegalValueException("Input is not valid");
        } else {
            String[] parts = input.split(" ");
            int salary = Integer.parseInt(parts[0]);
            int dayOfPayment = Integer.parseInt(parts[1]);
            this.salary = salary;
            this.dayOfPayment = dayOfPayment;
        }
    }

    /**
     * Returns true if a given payroll is valid .
     */
    public static boolean isValidPayroll(String test) {
        if (test == null) {
            return false;
        }
        String[] parts = test.split(" ");
        if (parts.length == 2) {
            try {
                long salary = Long.parseLong(parts[0]);
                long dateOfPayment = Long.parseLong(parts[1]);
                return (dateOfPayment >= 1 && dateOfPayment <= 28)
                        && (salary >= 0 && salary <= MAX_SALARY);
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return false;
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
        return this.salary + " " + this.dayOfPayment;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Payroll
                && this.salary == ((Payroll) other).getSalary()
                && this.dayOfPayment == ((Payroll) other).getDayOfPayment());
    }
}
