package seedu.address.model.employee;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Class represents an employee's payroll details in the database.
 */
public class Payroll {
    public static final String MESSAGE_NEGATIVE_SALARY =
            "Salary has to be a non negative integer, please try again.";
    public static final String MESSAGE_INVALID_DATE_OF_PAYMENT =
            "Date of payment has to be an integer between 1 and 28 (inclusive), please try again.";
    public static final String MESSAGE_CONSTRAINTS =
            "Payroll should contain two integers separated by a space";
    public static final String FILTER_PARAMETER = "pr";
    public final int salary;
    public final int dayOfPayment;

    /**
     * Constructs a {@code Payroll} with the given salary and date of payment.
     */
    public Payroll(int salary, int dayOfPayment) {
        requireAllNonNull(salary, dayOfPayment);
        checkArgument(salary >= 0, MESSAGE_NEGATIVE_SALARY);
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
        String[] parts = test.split(" ");
        if (parts.length == 2) {
            try {
                int salary = Integer.parseInt(parts[0]);
                int dateOfPayment = Integer.parseInt(parts[1]);
                return true;
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
