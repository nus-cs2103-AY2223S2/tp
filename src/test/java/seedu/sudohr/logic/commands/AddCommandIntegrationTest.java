package seedu.sudohr.logic.commands;

import static seedu.sudohr.logic.commands.CommandTestUtil.*;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalPersons.BOB;
import static seedu.sudohr.testutil.TypicalPersons.getTypicalSudoHr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
    }

    @Test
    public void execute_newEmployee_success() {
        Employee validEmployee = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
        expectedModel.addEmployee(validEmployee);

        assertCommandSuccess(new AddCommand(validEmployee), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEmployee), expectedModel);
    }

    @Test
    public void execute_duplicateEmployee_throwsCommandException() {
        Employee employeeInList = model.getSudoHr().getEmployeeList().get(0);
        assertCommandFailure(new AddCommand(employeeInList), model, AddCommand.MESSAGE_DUPLICATE_EMPLOYEE);
    }

    @Test
    public void execute_duplicateIdOnly_throwsCommandException() {
        Employee employeeInList = model.getSudoHr().getEmployeeList().get(0);
        Employee duplicateIdEmployee = new PersonBuilder(BOB).withId(employeeInList.getId().value)
                .build();
        assertCommandFailure(new AddCommand(duplicateIdEmployee), model, AddCommand.MESSAGE_DUPLICATE_EMPLOYEE);
    }

    @Test
    public void execute_duplicateEmailOnly_throwsCommandException() {
        Employee employeeInList = model.getSudoHr().getEmployeeList().get(0);
        Employee duplicateEmailEmployee = new PersonBuilder(BOB).withEmail(employeeInList.getEmail().value)
                .build();
        assertCommandFailure(new AddCommand(duplicateEmailEmployee), model, AddCommand.MESSAGE_DUPLICATE_EMAIL);
    }

    @Test
    public void execute_duplicatePhoneNumberOnly_throwsCommandException() {
        Employee employeeInList = model.getSudoHr().getEmployeeList().get(0);
        Employee duplicatePhoneEmployee = new PersonBuilder(BOB).withPhone(employeeInList.getPhone().value)
                .build();
        assertCommandFailure(new AddCommand(duplicatePhoneEmployee), model, AddCommand.MESSAGE_DUPLICATE_PHONE);
    }

    // Duplicate phone number should be identified first
    @Test
    public void execute_differentIdOnly_throwsCommandException() {
        Employee employeeInList = model.getSudoHr().getEmployeeList().get(0);
        Employee differentIdEmployee = new PersonBuilder(employeeInList).withId(VALID_ID_BOB)
                .build();
        assertCommandFailure(new AddCommand(differentIdEmployee), model, AddCommand.MESSAGE_DUPLICATE_PHONE);
    }

}
