package seedu.address.model.employee;

import java.util.function.Predicate;

/**
 * Tests that a {@code Employee}'s {@code Payroll} satisfies the criteria for filtering.
 */
public class FilterByPayrollPredicate implements Predicate<Employee> {

    private final int comparisonAmount;
    private final boolean isGreaterThan;

    public FilterByPayrollPredicate(int comparisonAmount, boolean isGreaterThan) {
        this.comparisonAmount = comparisonAmount;
        this.isGreaterThan = isGreaterThan;
    }

    @Override
    public boolean test(Employee employee) {
        if (isGreaterThan) {
            return employee.getPayroll().salary > comparisonAmount;
        } else {
            return employee.getPayroll().salary < comparisonAmount;
        }
    }

}
