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
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.leave.Leave;

/**
 * A utility class containing a list of {@code Leave} objects to be used in tests.
 */
public class TypicalLeave {
    public static final Leave LEAVE_TYPE_1 = new LeaveBuilder().withLeaveDate("2000-03-02")
            .withEmployees(ALICE, BENSON, CARL).build();
    public static final Leave LEAVE_TYPE_2 = new LeaveBuilder().withLeaveDate("2005-04-03")
            .withEmployees(DANIEL, ELLE, FIONA).build();
    public static final Leave LEAVE_TYPE_3 = new LeaveBuilder().withLeaveDate("2002-03-04")
            .withEmployees(GEORGE, HOON, IDA, FIONA).build();
    public static final Leave LEAVE_TYPE_EMPTY = new LeaveBuilder().build();

    public static final Employee EMPLOYEE_ON_LEAVE_TYPE_1 = CARL;
    public static final Employee EMPLOYEE_ON_LEAVE_TYPE_2_AND_3 = FIONA;

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalLeave() {} // prevents instantiation

    /**
     * Returns an {@code SudoHr} with all the typical employees and leaves.
     */
    public static SudoHr getTypicalSudoHr() {
        SudoHr sh = new SudoHr();
        for (Employee employee : TypicalEmployees.getTypicalEmployees()) {
            sh.addEmployee(new EmployeeBuilder(employee).build());
        }

        for (Leave leave : getTypicalLeaves()) {
            sh.addLeave(new LeaveBuilder(leave).build());
        }
        return sh;
    }

    public static List<Leave> getTypicalLeaves() {
        return new ArrayList<>(Arrays.asList(LEAVE_TYPE_1, LEAVE_TYPE_2, LEAVE_TYPE_3));
    }
}
