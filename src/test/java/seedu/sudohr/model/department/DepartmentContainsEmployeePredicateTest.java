package seedu.sudohr.model.department;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.employee.Id;
import seedu.sudohr.testutil.TypicalDepartments;

public class DepartmentContainsEmployeePredicateTest {

    @Test
    public void equals() {
        Id firstId = new Id(VALID_ID_AMY);
        Id secondId = new Id(VALID_ID_BOB);

        DepartmentContainsEmployeePredicate firstPredicate = new DepartmentContainsEmployeePredicate(firstId);
        DepartmentContainsEmployeePredicate secondPredicate = new DepartmentContainsEmployeePredicate(secondId);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DepartmentContainsEmployeePredicate firstPredicateCopy = new DepartmentContainsEmployeePredicate(firstId);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different employee -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_departmentContainsEmployee_returnsTrue() {
        // Employee exists
        DepartmentContainsEmployeePredicate predicate = new DepartmentContainsEmployeePredicate(new Id("101"));
        assertTrue(predicate.test(TypicalDepartments.HUMAN_RESOURCES));
    }

    @Test
    public void test_departmentDoesNotContainEmployee_returnsFalse() {
        // Employee exists
        DepartmentContainsEmployeePredicate predicate = new DepartmentContainsEmployeePredicate(new Id("101"));
        assertFalse(predicate.test(TypicalDepartments.ENGINEERING));
    }
}
