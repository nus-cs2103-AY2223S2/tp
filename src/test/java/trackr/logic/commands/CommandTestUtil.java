package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static trackr.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static trackr.logic.parser.CliSyntax.PREFIX_EMAIL;
import static trackr.logic.parser.CliSyntax.PREFIX_NAME;
import static trackr.logic.parser.CliSyntax.PREFIX_PHONE;
import static trackr.logic.parser.CliSyntax.PREFIX_STATUS;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;
import static trackr.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trackr.commons.core.index.Index;
import trackr.logic.commands.exceptions.CommandException;
import trackr.model.AddressBook;
import trackr.model.Model;
import trackr.model.person.NameContainsKeywordsPredicate;
import trackr.model.person.Person;
import trackr.model.task.Task;
import trackr.model.task.TaskContainsKeywordsPredicate;
import trackr.model.task.TaskDescriptor;
import trackr.testutil.EditPersonDescriptorBuilder;
import trackr.testutil.TaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

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

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    //task fields
    public static final String VALID_TASK_NAME_SORT_INVENTORY = "Sort Inventory";
    public static final String VALID_TASK_NAME_BUY_FLOUR = "Buy Flour";

    public static final String VALID_TASK_DEADLINE_2100 = "01/01/2100";
    public static final String VALID_TASK_DEADLINE_2024 = "01/01/2024";
    public static final String VALID_TASK_STATUS_DONE = "D";
    public static final String VALID_TASK_STATUS_NOT_DONE = "N";

    public static final String TASK_NAME_DESC_SORT_INVENTORY =
            " " + PREFIX_NAME + VALID_TASK_NAME_SORT_INVENTORY;
    public static final String TASK_NAME_DESC_BUY_FLOUR =
            " " + PREFIX_NAME + VALID_TASK_NAME_BUY_FLOUR;
    public static final String TASK_DEADLINE_DESC_2100 =
            " " + PREFIX_DEADLINE + VALID_TASK_DEADLINE_2100;
    public static final String TASK_DEADLINE_DESC_2024 =
            " " + PREFIX_DEADLINE + VALID_TASK_DEADLINE_2024;
    public static final String TASK_STATUS_DESC_DONE =
            " " + PREFIX_STATUS + VALID_TASK_STATUS_DONE;
    public static final String TASK_STATUS_DESC_NOT_DONE =
            " " + PREFIX_STATUS + VALID_TASK_STATUS_NOT_DONE;

    public static final String INVALID_TASK_NAME_DESC =
            " " + PREFIX_NAME + "Buy eggs & flour"; // '&' not allowed in names
    public static final String INVALID_TASK_DEADLINE_DESC =
            " " + PREFIX_DEADLINE + "aa/01/2025"; // alphabets not allowed in deadlines
    public static final String INVALID_TASK_STATUS_DESC =
            " " + PREFIX_STATUS + "d2"; // status can only be d / D / n / N

    public static final TaskDescriptor DESC_SORT_INVENTORY;
    public static final TaskDescriptor DESC_BUY_FLOUR;

    static {
        DESC_SORT_INVENTORY = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY)
                .withTaskDeadline(VALID_TASK_DEADLINE_2024)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();

        DESC_BUY_FLOUR = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
    }

    //order fields
    public static final String VALID_ORDER_NAME_CHOCOLATE_COOKIES = "Chocolate Cookies";
    public static final String VALID_ORDER_DEADLINE_2100 = "01/01/2100";
    public static final String VALID_ORDER_STATUS_DONE = "D";
    public static final String VALID_ORDER_STATUS_NOT_DONE = "N";
    public static final String VALID_ORDER_STATUS_IN_PROGRESS = "I";
    public static final String VALID_ORDER_QUANTITY_ONE = "1";
    public static final String VALID_ORDER_QUANTITY_TWO = "12";
    public static final String VALID_ORDER_QUANTITY_THREE = "123";
    public static final String VALID_CUSTOMER_NAME = "Nigel Wong";
    public static final String VALID_CUSTOMER_PHONE = "91234567";
    public static final String VALID_CUSTOMER_ADDRESS = "Woodlands Street 43";

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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered task list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task list.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitTaskName = task.getTaskName().fullTaskName.split("\\s+");
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setTaskNameKeywords(Arrays.asList(splitTaskName[0]));
        model.updateFilteredTaskList(predicate);

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
