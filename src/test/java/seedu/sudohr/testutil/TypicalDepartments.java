package seedu.sudohr.testutil;

import static seedu.sudohr.testutil.TypicalEmployees.ALICE;
import static seedu.sudohr.testutil.TypicalEmployees.BENSON;
import static seedu.sudohr.testutil.TypicalEmployees.CARL;
import static seedu.sudohr.testutil.TypicalEmployees.DANIEL;
import static seedu.sudohr.testutil.TypicalEmployees.ELLE;
import static seedu.sudohr.testutil.TypicalEmployees.FIONA;
import static seedu.sudohr.testutil.TypicalEmployees.GEORGE;
import static seedu.sudohr.testutil.TypicalEmployees.HOON;
import static seedu.sudohr.testutil.TypicalEmployees.IDA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.employee.Employee;

/**
 * A utility class containing a list of {@code Employee} objects to be used in tests.
 */
public class TypicalDepartments {

    public static final Department HUMAN_RESOURCES = new DepartmentBuilder().withDepartmentName("Human Resources")
            .withEmployees(ALICE, BENSON, CARL).build();
    public static final Department ENGINEERING = new DepartmentBuilder().withDepartmentName("Engineering")
            .withEmployees(DANIEL, ELLE, FIONA).build();
    public static final Department SALES = new DepartmentBuilder().withDepartmentName("Sales")
            .withEmployees(BENSON, GEORGE, HOON, IDA).build();

    public static final Employee EMPLOYEE_IN_HUMAN_RESOURCES = ALICE;
    public static final Department EMPTY_DEPARTMENT = new DepartmentBuilder()
            .withDepartmentName("Empty Department").build();

    public static final Employee EMPLOYEE_IN_HUMAN_RESOURCES_AND_SALES = BENSON;

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDepartments() {} // prevents instantiation

    /**
     * Returns an {@code SudoHr} with all the typical employees and departments.
     */
    public static SudoHr getTypicalSudoHr() {
        SudoHr sh = new SudoHr();
        for (Employee employee : TypicalEmployees.getTypicalEmployees()) {
            sh.addEmployee(new EmployeeBuilder(employee).build());
        }

        for (Department department : getTypicalDepartments()) {
            sh.addDepartment(new DepartmentBuilder(department).build());
        }
        return sh;
    }

    public static List<Department> getTypicalDepartments() {
        return new ArrayList<>(Arrays.asList(HUMAN_RESOURCES, ENGINEERING, SALES, EMPTY_DEPARTMENT));
    }
}
