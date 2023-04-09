package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_NO_EMPLOYEES_FILTERED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalEmployees.CARL;
import static seedu.address.testutil.TypicalEmployees.DANIEL;
import static seedu.address.testutil.TypicalEmployees.ELLE;
import static seedu.address.testutil.TypicalEmployees.FIONA;
import static seedu.address.testutil.TypicalEmployees.GEORGE;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.FilterByLeaveCountPredicate;
import seedu.address.model.employee.FilterByPayrollPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());

    @Test
    public void equals() {
        boolean[] possibleOperators = {false, true, false};
        FilterByLeaveCountPredicate firstPredicate =
                new FilterByLeaveCountPredicate(1500, possibleOperators);
        FilterByLeaveCountPredicate secondPredicate =
                new FilterByLeaveCountPredicate(1000, possibleOperators);

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertEquals(filterFirstCommand, filterFirstCommand);

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertEquals(filterFirstCommand, filterFirstCommandCopy);

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_payrollGreaterThan_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        boolean[] possibleOperators = {true, false, false};
        int comparisonAmount = 1000;
        FilterByPayrollPredicate predicate = new FilterByPayrollPredicate(comparisonAmount, possibleOperators);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE, FIONA), model.getFilteredEmployeeList());
    }

    @Test
    public void execute_payrollGreaterThan_noEmployeeFound() {
        String expectedMessage = String.format(MESSAGE_NO_EMPLOYEES_FILTERED);
        boolean[] possibleOperators = {true, false, false};
        int comparisonAmount = 1500;
        FilterByPayrollPredicate predicate = new FilterByPayrollPredicate(comparisonAmount, possibleOperators);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_payrollEqualTo_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 1);
        boolean[] possibleOperators = {false, false, true};
        int comparisonAmount = 1000;
        FilterByPayrollPredicate predicate = new FilterByPayrollPredicate(comparisonAmount, possibleOperators);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_payrollLesserThan_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 3);
        boolean[] possibleOperators = {false, true, false};
        int comparisonAmount = 1000;
        FilterByPayrollPredicate predicate = new FilterByPayrollPredicate(comparisonAmount, possibleOperators);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, GEORGE), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_leaveCountEqualTo_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 2);
        boolean[] possibleOperators = {false, false, true};
        int comparisonAmount = 17;
        FilterByLeaveCountPredicate predicate = new FilterByLeaveCountPredicate(comparisonAmount, possibleOperators);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredEmployeeList());
    }
    @Test
    public void execute_leaveCountGreaterThan_multipleEmployeesFound() {
        String expectedMessage = String.format(MESSAGE_NO_EMPLOYEES_FILTERED);
        boolean[] possibleOperators = {false, true, false};
        int comparisonAmount = 0;
        FilterByPayrollPredicate predicate = new FilterByPayrollPredicate(comparisonAmount, possibleOperators);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredEmployeeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEmployeeList());
    }
}
