package seedu.sudohr.testutil;

import static seedu.sudohr.testutil.TypicalPersons.ALICE;
import static seedu.sudohr.testutil.TypicalPersons.BENSON;
import static seedu.sudohr.testutil.TypicalPersons.CARL;
import static seedu.sudohr.testutil.TypicalPersons.DANIEL;
import static seedu.sudohr.testutil.TypicalPersons.ELLE;
import static seedu.sudohr.testutil.TypicalPersons.FIONA;
import static seedu.sudohr.testutil.TypicalPersons.GEORGE;
import static seedu.sudohr.testutil.TypicalPersons.HOON;
import static seedu.sudohr.testutil.TypicalPersons.IDA;

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
            .withEmployees(GEORGE, HOON, IDA).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDepartments() {} // prevents instantiation

    /**
     * Returns an {@code SudoHr} with all the typical persons and departments.
     */
    public static SudoHr getTypicalSudoHr() {
        SudoHr sh = new SudoHr();
        for (Employee person : TypicalPersons.getTypicalPersons()) {
            sh.addEmployee(person);
        }

        for (Department department : getTypicalDepartments()) {
            sh.addDepartment(department);
        }
        return sh;
    }

    public static List<Department> getTypicalDepartments() {
        return new ArrayList<>(Arrays.asList(HUMAN_RESOURCES, ENGINEERING, SALES));
    }
}
