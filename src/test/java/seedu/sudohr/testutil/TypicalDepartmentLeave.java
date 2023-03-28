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
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.leave.Leave;

/**
 * A utility class with employees, departments and leaves to be used for testing.
 */
public class TypicalDepartmentLeave {
    public static final Department HUMAN_RESOURCES = new DepartmentBuilder().withDepartmentName("Human Resources")
            .withEmployees(ALICE, BENSON, CARL).build();
    public static final Department ENGINEERING = new DepartmentBuilder().withDepartmentName("Engineering")
            .withEmployees(DANIEL, ELLE, FIONA).build();
    public static final Department SALES = new DepartmentBuilder().withDepartmentName("Sales")
            .withEmployees(BENSON, GEORGE, HOON, FIONA).build();

    public static final DepartmentName NON_EXISTENT_DEPARTMENT_NAME = new DepartmentName("NONE");

    public static final Employee FIRST_EMPLOYEE_IN_HR_ON_LEAVE_1 = ALICE;
    public static final Employee SECOND_EMPLOYEE_IN_HR_ON_LEAVE_1 = BENSON;
    public static final Employee THIRD_EMPLOYEE_IN_HR_NO_LEAVE = CARL;

    public static final List<Employee> HR_EMPLOYEES_PRESENT_ON_DATE_TYPE_1 =
            new ArrayList<>(Arrays.asList(THIRD_EMPLOYEE_IN_HR_NO_LEAVE));

    public static final Employee EMPLOYEE_IN_ENGINEERING_ON_LEAVE_2 = ELLE;

    public static final List<Employee> ENGINEERING_EMPLOYEES_PRESENT_ON_DATE_TYPE_2 =
            new ArrayList<>(Arrays.asList(DANIEL, FIONA));

    public static final int NUM_ENGINEERING_EMPLOYEE_PRESENT_ON_DATE_TYPE_2 = 2;
    public static final Employee EMPLOYEE_IN_SALES_AND_ENGINEERING_ON_LEAVE_3 = FIONA;

    public static final List<Employee> ENGINEERING_EMPLOYEES_PRESENT_ON_DATE_TYPE_3 =
            new ArrayList<>(Arrays.asList(DANIEL, ELLE));

    public static final List<Employee> SALES_EMPLOYEES_PRESENT_ON_DATE_TYPE_3 =
            new ArrayList<>(Arrays.asList(BENSON, GEORGE, HOON));

    public static final Employee EMPLOYEE_WITH_NO_DEPARTMENT_ON_LEAVE_4 = IDA;
    public static final Department EMPTY_DEPARTMENT = new DepartmentBuilder()
            .withDepartmentName("Empty Department").build();

    public static final String VALID_CURRENT_DATE = "2023-01-01";

    public static final String DATE_TYPE_1 = "2023-01-15";
    public static final String DATE_TYPE_2 = "2023-02-01";
    public static final String DATE_TYPE_3 = "2023-03-01";
    public static final String DATE_TYPE_4 = "2023-04-01";
    public static final String ALL_PRESENT_DATE = "2023-03-15";

    public static final String INVALID_DATE_FOR_LIST_HEADCOUNT_FUTURE = "2030-01-01";
    public static final String INVALID_DATE_FOR_LIST_HEADCOUNT_PAST = "1800-01-01";

    // two employees from HR takes leave on DATE_TYPE_1
    public static final Leave LEAVE_TWO_HR_TYPE_1 = new LeaveBuilder().withLeaveDate(DATE_TYPE_1)
            .withEmployees(FIRST_EMPLOYEE_IN_HR_ON_LEAVE_1, SECOND_EMPLOYEE_IN_HR_ON_LEAVE_1).build();

    // a single employee from Engineering takes leave on DATE_TYPE_2
    public static final Leave LEAVE_ONE_ENGINEERING_TYPE_2 = new LeaveBuilder().withLeaveDate(DATE_TYPE_2)
            .withEmployees(EMPLOYEE_IN_ENGINEERING_ON_LEAVE_2).build();

    // a single employee who belongs in multiple department takes leave on DATE_TYPE_3
    public static final Leave LEAVE_ONE_SALES_ENGINEERING_TYPE_3 = new LeaveBuilder().withLeaveDate(DATE_TYPE_3)
            .withEmployees(EMPLOYEE_IN_SALES_AND_ENGINEERING_ON_LEAVE_3).build();

    // a single employee with no department takes leave on DATE_TYPE_4
    public static final Leave LEAVE_NO_DEPARTMENT_TYPE_4 = new LeaveBuilder().withLeaveDate(DATE_TYPE_4)
            .withEmployees(EMPLOYEE_WITH_NO_DEPARTMENT_ON_LEAVE_4).build();


    private TypicalDepartmentLeave() {} // prevents instantiation

    /**
     * Returns an {@code SudoHr} with all the typical employees and departments.
     */
    public static SudoHr getTypicalSudoHr() {
        SudoHr sh = new SudoHr();
        for (Employee employee : getTypicalEmployees()) {
            sh.addEmployee(new EmployeeBuilder(employee).build());
        }

        for (Department department : getTypicalDepartments()) {
            sh.addDepartment(new DepartmentBuilder(department).build());
        }

        for (Leave leave: getTypicalLeaves()) {
            sh.addLeave(new LeaveBuilder(leave).build());
        }
        return sh;
    }

    public static List<Department> getTypicalDepartments() {
        return new ArrayList<>(Arrays.asList(HUMAN_RESOURCES, ENGINEERING, SALES, EMPTY_DEPARTMENT));
    }

    public static List<Leave> getTypicalLeaves() {
        return new ArrayList<>(
                Arrays.asList(
                LEAVE_TWO_HR_TYPE_1,
                LEAVE_ONE_ENGINEERING_TYPE_2,
                LEAVE_ONE_SALES_ENGINEERING_TYPE_3,
                LEAVE_NO_DEPARTMENT_TYPE_4));
    }
    public static List<Employee> getTypicalEmployees() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE, HOON));
    }

}
