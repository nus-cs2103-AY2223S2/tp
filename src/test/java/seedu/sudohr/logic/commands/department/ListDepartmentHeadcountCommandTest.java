package seedu.sudohr.logic.commands.department;

import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.DATE_TYPE_1;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.DATE_TYPE_2;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.DATE_TYPE_3;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.ENGINEERING;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.ENGINEERING_EMPLOYEES_PRESENT_ON_DATE_TYPE_2;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.ENGINEERING_EMPLOYEES_PRESENT_ON_DATE_TYPE_3;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.HR_EMPLOYEES_PRESENT_ON_DATE_TYPE_1;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.NON_EXISTENT_DEPARTMENT_NAME;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.SALES;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.SALES_EMPLOYEES_PRESENT_ON_DATE_TYPE_3;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.VALID_CURRENT_DATE;
import static seedu.sudohr.testutil.TypicalDepartmentLeave.getTypicalSudoHr;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sudohr.logic.commands.Command;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.department.DepartmentName;


public class ListDepartmentHeadcountCommandTest {


    private Model model;

    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
        expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
    }

    @Test
    public void execute_invalidDateGiven_failure() {
        DepartmentName engineering = ENGINEERING.getName();

        // 2 years in the past
        LocalDate currentDate = LocalDate.parse(VALID_CURRENT_DATE);
        LocalDate invalidDate = LocalDate.parse(VALID_CURRENT_DATE).minusYears(2);

        Command command = new ListDepartmentHeadcountCommand(currentDate, invalidDate, engineering);

        String expectedMessage = ListDepartmentHeadcountCommand.MESSAGE_INVALID_DATE_FOR_HEADCOUNT;

        assertCommandFailure(command, model, expectedMessage);

        // 2 years into the future
        LocalDate invalidDateFuture = LocalDate.parse(VALID_CURRENT_DATE).plusYears(2);

        Command command2 = new ListDepartmentHeadcountCommand(currentDate, invalidDateFuture, engineering);

        assertCommandFailure(command2, model, expectedMessage);
    }

    @Test
    public void execute_departmentDoesNotExist_failure() {
        DepartmentName nonExistentDepartmentName = NON_EXISTENT_DEPARTMENT_NAME;
        LocalDate currentDate = LocalDate.parse(VALID_CURRENT_DATE);
        LocalDate validDate = LocalDate.parse(DATE_TYPE_1);

        Command command = new ListDepartmentHeadcountCommand(currentDate, validDate, nonExistentDepartmentName);

        String expectedMessage = ListDepartmentHeadcountCommand.MESSAGE_DEPARTMENT_NOT_EXIST;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_singleEmployeeOneDepartmentOnLeave_success() {
        DepartmentName engineering = ENGINEERING.getName();
        Command command2 = new ListDepartmentHeadcountCommand(
                LocalDate.parse(VALID_CURRENT_DATE),
                LocalDate.parse(DATE_TYPE_2),
                engineering
        );

        expectedModel.updateFilteredEmployeeList(
                e -> ENGINEERING_EMPLOYEES_PRESENT_ON_DATE_TYPE_2.stream().anyMatch(e::isSameEmployee)
        );

        expectedModel.updateFilteredDepartmentList(d -> d.equals(ENGINEERING));


        String expectedMessage2 = String.format(ListDepartmentHeadcountCommand.MESSAGE_SUCCESS,
                ENGINEERING_EMPLOYEES_PRESENT_ON_DATE_TYPE_2.size(), DATE_TYPE_2, engineering);

        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
    }

    @Test
    public void execute_singleEmployeeMultipleDepartmentsOnLeave_success() {
        DepartmentName engineering = ENGINEERING.getName();
        DepartmentName sales = SALES.getName();

        Command command1 = new ListDepartmentHeadcountCommand(
                LocalDate.parse(VALID_CURRENT_DATE),
                LocalDate.parse(DATE_TYPE_3),
                engineering
        );

        String expectedMessage1 = String.format(ListDepartmentHeadcountCommand.MESSAGE_SUCCESS,
                ENGINEERING_EMPLOYEES_PRESENT_ON_DATE_TYPE_3.size(), DATE_TYPE_3, engineering);

        expectedModel.updateFilteredEmployeeList(
                e -> ENGINEERING_EMPLOYEES_PRESENT_ON_DATE_TYPE_3.stream().anyMatch(e::isSameEmployee)
        );

        expectedModel.updateFilteredDepartmentList(d -> d.equals(ENGINEERING));


        assertCommandSuccess(command1, model, expectedMessage1, expectedModel);



        Command command2 = new ListDepartmentHeadcountCommand(
                LocalDate.parse(VALID_CURRENT_DATE),
                LocalDate.parse(DATE_TYPE_3),
                sales
        );
        String expectedMessage2 = String.format(ListDepartmentHeadcountCommand.MESSAGE_SUCCESS,
                SALES_EMPLOYEES_PRESENT_ON_DATE_TYPE_3.size(), DATE_TYPE_3, sales);
        // refresh expected model
        expectedModel.updateFilteredEmployeeList(e -> true);

        expectedModel.updateFilteredEmployeeList(
                e -> SALES_EMPLOYEES_PRESENT_ON_DATE_TYPE_3.stream().anyMatch(e::isSameEmployee)
        );

        expectedModel.updateFilteredDepartmentList(d -> d.equals(SALES));


        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
    }

    @Test
    public void execute_multipleEmployeeSameDepartmentOnLeave_success() {
        DepartmentName hr = HUMAN_RESOURCES.getName();

        Command command = new ListDepartmentHeadcountCommand(
                LocalDate.parse(VALID_CURRENT_DATE),
                LocalDate.parse(DATE_TYPE_1),
                hr);

        expectedModel.updateFilteredEmployeeList(
                e -> HR_EMPLOYEES_PRESENT_ON_DATE_TYPE_1.stream().anyMatch(e::isSameEmployee)
        );

        expectedModel.updateFilteredDepartmentList(d -> d.equals(HUMAN_RESOURCES));


        String expectedMessage1 = String.format(ListDepartmentHeadcountCommand.MESSAGE_SUCCESS,
                HR_EMPLOYEES_PRESENT_ON_DATE_TYPE_1.size(), DATE_TYPE_1, hr);

        assertCommandSuccess(command, model, expectedMessage1, expectedModel);

    }






}
