package seedu.sudohr.logic.commands.department;

import static seedu.sudohr.commons.core.Messages.MESSAGE_DEPARTMENTS_LISTED_OVERVIEW;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.department.DepartmentContainsEmployeePredicate;
import seedu.sudohr.model.employee.Id;

public class ListEmployeeDepartmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
        expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
    }

    @Test
    public void execute_listIsFiltered_shows2Departments() {
        expectedModel.updateFilteredDepartmentList(new DepartmentContainsEmployeePredicate(new Id("102")));
        assertCommandSuccess(new ListEmployeeDepartmentCommand(new DepartmentContainsEmployeePredicate(new Id("102"))),
                model, String.format(MESSAGE_DEPARTMENTS_LISTED_OVERVIEW, 2), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_shows1Departmentt() {
        expectedModel.updateFilteredDepartmentList(new DepartmentContainsEmployeePredicate(new Id("101")));
        assertCommandSuccess(new ListEmployeeDepartmentCommand(new DepartmentContainsEmployeePredicate(new Id("101"))),
                model, String.format(MESSAGE_DEPARTMENTS_LISTED_OVERVIEW, 1), expectedModel);
    }

    @Test
    public void execute_nonExistentEmployee_throwsException() {
        ListEmployeeDepartmentCommand command =
                new ListEmployeeDepartmentCommand(new DepartmentContainsEmployeePredicate(new Id("110")));
        assertCommandFailure(command, model, Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
    }
}
