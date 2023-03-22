package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.exceptions.DuplicateEmployeeException;
import seedu.address.testutil.EmployeeBuilder;

public class ExecutiveProDbTest {

    private final ExecutiveProDb executiveProDb = new ExecutiveProDb();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), executiveProDb.getEmployeeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> executiveProDb.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyExecutiveProDb_replacesData() {
        ExecutiveProDb newData = getTypicalExecutiveProDb();
        executiveProDb.resetData(newData);
        assertEquals(newData, executiveProDb);
    }

    @Test
    public void resetData_withDuplicateEmployees_throwsDuplicateEmployeeException() {
        // Two persons with the same identity fields
        Employee editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Employee> newEmployees = Arrays.asList(ALICE, editedAlice);
        ExecutiveProDbStub newData = new ExecutiveProDbStub(newEmployees);

        assertThrows(DuplicateEmployeeException.class, () -> executiveProDb.resetData(newData));
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> executiveProDb.hasEmployee(null));
    }

    @Test
    public void hasEmployee_employeeNotInExecutiveProDb_returnsFalse() {
        assertFalse(executiveProDb.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeInExecutiveProDb_returnsTrue() {
        executiveProDb.addEmployee(ALICE);
        assertTrue(executiveProDb.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeWithSameIdentityFieldsInExecutiveProDb_returnsTrue() {
        executiveProDb.addEmployee(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(executiveProDb.hasEmployee(editedAlice));
    }

    @Test
    public void getEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> executiveProDb.getEmployeeList().remove(0));
    }

    /**
     * A stub ReadOnlyExecutiveProDb whose persons list can violate interface constraints.
     */
    private static class ExecutiveProDbStub implements ReadOnlyExecutiveProDb {
        private final ObservableList<Employee> employees = FXCollections.observableArrayList();

        ExecutiveProDbStub(Collection<Employee> employees) {
            this.employees.setAll(employees);
        }

        @Override
        public ObservableList<Employee> getEmployeeList() {
            return employees;
        }
    }

}
