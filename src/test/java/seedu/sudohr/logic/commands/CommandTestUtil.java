package seedu.sudohr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_DEPARTMENT_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_EMPLOYEE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.sudohr.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.sudohr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.department.EditDepartmentCommand;
import seedu.sudohr.logic.commands.employee.EditCommand;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.NameContainsKeywordsPredicate;
import seedu.sudohr.testutil.EditDepartmentDescriptorBuilder;
import seedu.sudohr.testutil.EditEmployeeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ID_AMY = "001"; // treated as 1
    public static final String VALID_ID_BOB = "100";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String EID_DESC_AMY = " " + PREFIX_EMPLOYEE + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String EID_DESC_BOB = " " + PREFIX_EMPLOYEE + VALID_ID_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_ID_DESC = " " + PREFIX_ID + "help123"; // not allowed to have characters
    public static final String INVALID_ID_DESC_ZERO = " " + PREFIX_ID + "000"; // need at least 1 non-zero digit
    public static final String INVALID_EID_DESC = " " + PREFIX_EMPLOYEE + "help123"; // not allowed to have characters
    public static final String INVALID_EID_DESC_ZERO = " " + PREFIX_EMPLOYEE + "000"; // need at least 1 non-zero digit
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEmployeeDescriptor DESC_AMY;
    public static final EditCommand.EditEmployeeDescriptor DESC_BOB;

    public static final String VALID_DEPARTMENT_NAME_HUMAN_RESOURCES = "Human Resources";
    public static final String VALID_DEPARTMENT_NAME_ENGINEERING = "Engineering";

    public static final String DEPARTMENT_NAME_DESC_HUMAN_RESOURCES = " " + PREFIX_DEPARTMENT_NAME
            + VALID_DEPARTMENT_NAME_HUMAN_RESOURCES;
    public static final String DEPARTMENT_NAME_DESC_ENGINEERING = " " + PREFIX_DEPARTMENT_NAME
            + VALID_DEPARTMENT_NAME_ENGINEERING;

    // leave
    public static final String VALID_LEAVE_DATE_LEAVE_TYPE_2 = "2005-04-03";
    public static final String VALID_LEAVE_DATE_LEAVE_TYPE_1 = "2000-03-02";

    public static final String DATE_DESC_LEAVE_TYPE_2 = " " + PREFIX_DATE + VALID_LEAVE_DATE_LEAVE_TYPE_2;
    public static final String DATE_DESC_LEAVE_TYPE_1 = " " + PREFIX_DATE + VALID_LEAVE_DATE_LEAVE_TYPE_1;

    public static final String INVALID_LEAVE_DATE_DESC = " " + PREFIX_DATE + "12/23/24";
    public static final String INVALID_INDEX_DESC = " " + PREFIX_ID + "3k21309";

    public static final String VALID_START_LEAVE_DATE = "2022-03-02";
    public static final String VALID_SECOND_DAY_LEAVE_DATE = "2022-03-03";
    public static final String VALID_THIRD_DAY_LEAVE_DATE = "2022-03-04";
    public static final String VALID_END_LEAVE_DATE = "2022-03-05";
    public static final String INVALID_END_LEAVE_DATE_BEFORE_START = "2022-03-01";
    public static final String INVALID_END_LEAVE_DATE_MORE_THAN_SEVEN = "2022-03-09";

    public static final String DATE_DESC_START_LEAVE_DATE = " " + PREFIX_START_DATE + "2022-03-02";
    public static final String DATE_DESC_END_LEAVE_DATE = " " + PREFIX_END_DATE + "2022-03-05";
    public static final String INVALID_DESC_END_LEAVE_DATE_BEFORE_START = " " + PREFIX_END_DATE + "2022-03-01";
    public static final String INVALID_DESC_END_LEAVE_DATE_MORE_THAN_SEVEN = " " + PREFIX_END_DATE + "2022-03-09";
    public static final String INVALID_START_DATE_DESC = " " + PREFIX_START_DATE + "12/23/24";
    public static final String INVALID_END_DATE_DESC = " " + PREFIX_END_DATE + "12/23/24";

    // '&' not allowed in names
    public static final String INVALID_DEPARTMENT_NAME_DESC = " " + PREFIX_DEPARTMENT_NAME + "Engineering&";

    public static final EditDepartmentCommand.EditDepartmentDescriptor DESC_DEPARTMENT_ENGINEERING;
    public static final EditDepartmentCommand.EditDepartmentDescriptor DESC_DEPARTMENT_HUMAN_RESOURCES;

    static {
        DESC_AMY = new EditEmployeeDescriptorBuilder().withId(VALID_ID_AMY)
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditEmployeeDescriptorBuilder().withId(VALID_ID_BOB)
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_DEPARTMENT_ENGINEERING = new EditDepartmentDescriptorBuilder()
                .withName(new DepartmentName(VALID_DEPARTMENT_NAME_ENGINEERING)).build();
        DESC_DEPARTMENT_HUMAN_RESOURCES = new EditDepartmentDescriptorBuilder()
                .withName(new DepartmentName(VALID_DEPARTMENT_NAME_HUMAN_RESOURCES)).build();
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
     * - the SudoHR, filtered employee list and selected employee in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        SudoHr expectedSudoHr = new SudoHr(actualModel.getSudoHr());
        List<Employee> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEmployeeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedSudoHr, actualModel.getSudoHr());
        assertEquals(expectedFilteredList, actualModel.getFilteredEmployeeList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the employee at the given {@code targetIndex} in the
     * {@code model}'s SudoHR.
     */
    public static void showEmployeeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEmployeeList().size());

        Employee employee = model.getFilteredEmployeeList().get(targetIndex.getZeroBased());
        final String[] splitName = employee.getName().fullName.split("\\s+");
        model.updateFilteredEmployeeList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEmployeeList().size());
    }

}
