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
     * FilterByPayroll constructor.
     * @param comparisonAmount
     * @param possibleOperators
     */
    public FilterByPayrollPredicate(int comparisonAmount, boolean[] possibleOperators) {
        this.comparisonAmount = comparisonAmount;
        this.possibleOperators = possibleOperators;
        this.isGreaterThan = possibleOperators[0];
        this.isLesserThan = possibleOperators[1];
        this.isEqualTo = possibleOperators[2];
    }

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
