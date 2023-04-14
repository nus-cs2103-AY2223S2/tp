package seedu.address.model.employee;

import java.util.function.Predicate;

/**
 * Tests that a {@code Employee}'s {@code Payroll} satisfies the criteria for filtering.
 */
public class FilterByLeaveCountPredicate implements Predicate<Employee> {

    private final int comparisonAmount;
    private final boolean[] possibleOperators;
    private final boolean isGreaterThan;
    private final boolean isLesserThan;
    private final boolean isEqualTo;

    /**
     * Creates a {@code FilterByLeavePredicate} object and initialises values.
     * @param comparisonAmount the amount to be compared to.
     * @param possibleOperators the type of relation to be checked.
     */
    public FilterByLeaveCountPredicate(int comparisonAmount, boolean[] possibleOperators) {
        this.comparisonAmount = comparisonAmount;
        this.possibleOperators = possibleOperators;
        this.isGreaterThan = possibleOperators[0];
        this.isLesserThan = possibleOperators[1];
        this.isEqualTo = possibleOperators[2];
    }

    /**
     * Sets up the {@code Predicate} to check that {@code Employee}'s {@code LeaveCount} satisfies the criteria given.
     * @param employee the employee to be checked.
     * @return whether the condition is satisfied.
     */
    @Override
    public boolean test(Employee employee) {
        if (isGreaterThan) {
            return employee.getLeaveCount() > comparisonAmount;
        } else if (isLesserThan) {
            return employee.getLeaveCount() < comparisonAmount;
        } else {
            return employee.getLeaveCount() == comparisonAmount;
        }
    }

    /**
     * Checks if two instances of {@code FilterByLeaveCountPredicate} are equal.
     * @param other the other instance.
     * @return a boolean value.
     */
    @Override
    public boolean equals(Object other) {

        return other == this
                || (other instanceof FilterByLeaveCountPredicate
                && (this.comparisonAmount == ((FilterByLeaveCountPredicate) other).comparisonAmount
                && this.isEqualTo == ((FilterByLeaveCountPredicate) other).isEqualTo
                && this.isGreaterThan == ((FilterByLeaveCountPredicate) other).isGreaterThan
                && this.isLesserThan == ((FilterByLeaveCountPredicate) other).isLesserThan));
    }
}
