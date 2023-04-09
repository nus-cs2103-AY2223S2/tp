package seedu.address.model.employee;

import java.util.function.Predicate;

/**
 * FilterByLeave class.
 */
public class FilterByLeaveCountPredicate implements Predicate<Employee> {

    private final int comparisonAmount;
    private final boolean[] possibleOperators;
    private final boolean isGreaterThan;
    private final boolean isLesserThan;
    private final boolean isEqualTo;

    /**
     * FilterByLeave constructor.
     */
    public FilterByLeaveCountPredicate(int comparisonAmount, boolean[] possibleOperators) {
        this.comparisonAmount = comparisonAmount;
        this.possibleOperators = possibleOperators;
        this.isGreaterThan = possibleOperators[0];
        this.isLesserThan = possibleOperators[1];
        this.isEqualTo = possibleOperators[2];
    }

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
