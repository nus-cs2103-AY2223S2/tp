package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_JOINING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYROLL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExecutiveProDb;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_EMPLOYEE_ID_AMY = "111";
    public static final String VALID_EMPLOYEE_ID_BOB = "222";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_DEPARTMENT_AMY = "Finance";
    public static final String VALID_DEPARTMENT_BOB = "Marketing";
    public static final String VALID_PAYROLL_AMY = "1000 15";
    public static final String VALID_PAYROLL_BOB = "2500 20";
    public static final String VALID_LEAVE_COUNTER_AMY = "10";
    public static final String VALID_LEAVE_COUNTER_BOB = "15";
    public static final String VALID_DATE_OF_BIRTH_AMY = "2000-01-01";
    public static final String VALID_DATE_OF_BIRTH_BOB = "1998-02-09";
    public static final String VALID_DATE_OF_JOINING_AMY = "2022-01-01";
    public static final String VALID_DATE_OF_JOINING_BOB = "2023-01-01";
    public static final String VALID_PICTURE_PATH_AMY = "data/employeepictures/default.png";
    public static final String VALID_PICTURE_PATH_BOB = "data/employeepictures/default.png";
    public static final String VALID_TAG_SOFTWARE_ENGINEER = "software engineer";
    public static final String VALID_TAG_MANAGER = "manager";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String DEPARTMENT_DESC_AMY = " " + PREFIX_DEPARTMENT + VALID_DEPARTMENT_AMY;
    public static final String DEPARTMENT_DESC_BOB = " " + PREFIX_DEPARTMENT + VALID_DEPARTMENT_BOB;

    public static final String PAYROLL_DESC_AMY = " " + PREFIX_PAYROLL + VALID_PAYROLL_AMY;
    public static final String PAYROLL_DESC_BOB = " " + PREFIX_PAYROLL + VALID_PAYROLL_BOB;
    public static final String LEAVE_COUNTER_DESC_AMY = " " + PREFIX_LEAVE_COUNT + VALID_LEAVE_COUNTER_AMY;
    public static final String LEAVE_COUNTER_DESC_BOB = " " + PREFIX_LEAVE_COUNT + VALID_LEAVE_COUNTER_BOB;
    public static final String DATE_OF_BIRTH_DESC_AMY = " " + PREFIX_DATE_OF_BIRTH + VALID_DATE_OF_BIRTH_AMY;
    public static final String DATE_OF_BIRTH_DESC_BOB = " " + PREFIX_DATE_OF_BIRTH + VALID_DATE_OF_BIRTH_BOB;
    public static final String DATE_OF_JOINING_DESC_AMY = " " + PREFIX_DATE_OF_JOINING + VALID_DATE_OF_JOINING_AMY;
    public static final String DATE_OF_JOINING_DESC_BOB = " " + PREFIX_DATE_OF_JOINING + VALID_DATE_OF_JOINING_BOB;
    public static final String TAG_DESC_MANAGER = " " + PREFIX_TAG + VALID_TAG_MANAGER;
    public static final String TAG_DESC_SOFTWARE_ENGINEER = " " + PREFIX_TAG + VALID_TAG_SOFTWARE_ENGINEER;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + "  ";
    // empty string not allowed for addresses
    public static final String INVALID_DEPARTMENT_DESC = " " + PREFIX_DEPARTMENT;
    // empty string not allowed for departments
    public static final String INVALID_PAYROLL_DESC = " " + PREFIX_PAYROLL + "-1 90";
    public static final String INVALID_LEAVE_COUNTER_DESC = " " + PREFIX_LEAVE_COUNT + "-3";
    public static final String INVALID_DATE_OF_BIRTH_DESC = " " + PREFIX_DATE_OF_BIRTH + "2003/12/10";
    public static final String INVALID_DATE_OF_JOINING_DESC = " " + PREFIX_DATE_OF_JOINING + "2003-14-13";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEmployeeDescriptor DESC_AMY;
    public static final EditCommand.EditEmployeeDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withDepartment(VALID_DEPARTMENT_AMY).withPayroll(CommandTestUtil.VALID_PAYROLL_AMY)
                .withLeaveCounter(CommandTestUtil.VALID_LEAVE_COUNTER_AMY)
                .withDateOfBirth(CommandTestUtil.VALID_DATE_OF_BIRTH_AMY)
                .withDateOfJoining(CommandTestUtil.VALID_DATE_OF_JOINING_AMY).withTags(VALID_TAG_MANAGER).build();
        DESC_BOB = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withDepartment(VALID_DEPARTMENT_BOB).withPayroll(CommandTestUtil.VALID_PAYROLL_AMY)
                .withLeaveCounter(CommandTestUtil.VALID_LEAVE_COUNTER_AMY)
                .withDateOfBirth(CommandTestUtil.VALID_DATE_OF_BIRTH_AMY)
                .withDateOfJoining(CommandTestUtil.VALID_DATE_OF_JOINING_AMY)
                .withTags(VALID_TAG_SOFTWARE_ENGINEER, VALID_TAG_MANAGER).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered employee list and selected employee in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExecutiveProDb expectedExecutiveProDb = new ExecutiveProDb(actualModel.getExecutiveProDb());
        List<Employee> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEmployeeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedExecutiveProDb, actualModel.getExecutiveProDb());
        assertEquals(expectedFilteredList, actualModel.getFilteredEmployeeList());
    }

}
