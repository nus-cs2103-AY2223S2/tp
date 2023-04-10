package seedu.address.model.employee;

import java.util.function.Predicate;

/**
 * Tests that a {@code Employee}'s {@code Payroll} satisfies the criteria for filtering.
 */
public class FilterByPayrollPredicate implements Predicate<Employee> {

    private final int comparisonAmount;
    private final boolean[] possibleOperators;
    private final boolean isGreaterThan;
    private final boolean isLesserThan;
    private final boolean isEqualTo;

    /**
     * Creates a {@code FilterByPayrollPredicate} object and initialises values.
     * @param comparisonAmount the amount to be compared to.
     * @param possibleOperators the type of relation to be checked.
     */
    public FilterByPayrollPredicate(int comparisonAmount, boolean[] possibleOperators) {
        this.comparisonAmount = comparisonAmount;
        this.possibleOperators = possibleOperators;
        this.isGreaterThan = possibleOperators[0];
        this.isLesserThan = possibleOperators[1];
        this.isEqualTo = possibleOperators[2];
    }

    /**
     * Sets up the {@code Predicate} to check that {@code Employee}'s {@code Payroll} satisfies the criteria given.
     * @param employee the employee to be checked.
     * @return whether the condition is satisfied.
     */
    @Override
    public boolean test(Employee employee) {
        if (isGreaterThan) {
            return employee.getPayroll().salary > comparisonAmount;
        } else if (isLesserThan) {
            return employee.getPayroll().salary < comparisonAmount;
        } else {
            return employee.getPayroll().salary == comparisonAmount;
        }
    }

    /**
     * Checks if two instances of {@code FilterByPayrollPredicate} are equal.
     * @param other the other instance.
     * @return a boolean value.
     */
    @Override
    public boolean equals(Object other) {

        return other == this
                || (other instanceof FilterByPayrollPredicate
                && (this.comparisonAmount == ((FilterByPayrollPredicate) other).comparisonAmount
                && this.isEqualTo == ((FilterByPayrollPredicate) other).isEqualTo
                && this.isGreaterThan == ((FilterByPayrollPredicate) other).isGreaterThan
                && this.isLesserThan == ((FilterByPayrollPredicate) other).isLesserThan));
    }
}
