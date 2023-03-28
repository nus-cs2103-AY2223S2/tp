package seedu.task.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.task.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.task.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.task.logic.parser.CliSyntax.PREFIX_SUBTASK_INDEX;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.task.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.task.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.task.commons.core.index.Index;
import seedu.task.commons.core.index.IndexList;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.TaskBook;
import seedu.task.model.task.NameContainsKeywordsPredicate;
import seedu.task.model.task.Task;
import seedu.task.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_DESCRIPTION_AMY = "Amy's description";
    public static final String VALID_DESCRIPTION_DEFAULT = "No Description";
    public static final String VALID_DESCRIPTION_BOB = "Bob's description";

    public static final String VALID_TASK_INDEX = "1";

    public static final String VALID_SUBTASK_INDEX = "1";

    public static final String INVALID_INDEX = "a";

    public static final String INDEX_1 = " " + VALID_SUBTASK_INDEX;
    public static final String INDEX_SUBTASK = " " + PREFIX_SUBTASK_INDEX + VALID_SUBTASK_INDEX;
    public static final String INVALID_SUBTASK_INDEX = " " + PREFIX_SUBTASK_INDEX;

    public static final String VALID_DESCRIPTION_BOTH = "Both description";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_FROM_DATE = "2023-01-01 1800";
    public static final String VALID_TO_DATE = "2023-01-02 1800";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;
    public static final String DESCRIPTION_DESC_DEFAULT = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_DEFAULT;
    public static final String DESCRIPTION_DESC_BOTH = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOTH;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String FROM_DESC_DEFAULT = " " + PREFIX_FROM + VALID_FROM_DATE;
    public static final String TO_DESC_DEFAULT = " " + PREFIX_TO + VALID_TO_DATE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String BLANK_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_FROM_DESC = " " + PREFIX_FROM + VALID_TO_DATE;
    public static final String INVALID_TO_DESC = " " + PREFIX_TO + VALID_FROM_DATE;
    public static final long VALID_EFFORT = 3;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_AMY;
    public static final EditCommand.EditTaskDescriptor DESC_BOB;

    public static final EditCommand.EditTaskDescriptor DESC_AMY_EVENT;

    public static final String VALID_DATE = "2023-03-30 2359";

    public static final String VALID_FROM = "2023-03-25 2359";

    public static final String VALID_TO = "2023-03-26 2359";

    static {
        DESC_AMY = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
                .withDescription(VALID_DESCRIPTION_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB)
                .withDescription(VALID_DESCRIPTION_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_AMY_EVENT = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
                .withDescription(VALID_DESCRIPTION_AMY)
                .withTags(VALID_TAG_FRIEND)
                .withFrom(VALID_FROM)
                .withTo(VALID_TO).build();
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
     * - the task book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TaskBook expectedTaskBook = new TaskBook(actualModel.getTaskBook());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTaskBook, actualModel.getTaskBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(task.getName().fullName));

        assertEquals(1, model.getFilteredTaskList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the tasks at the given {@code targetIndex} in the
     * {@code model}'s task book.
     */
    public static void showTaskAtIndexList(Model model, IndexList targetIndices) {
        int n = targetIndices.size();

        for (int i = 0; i < n; i++) {
            assertTrue(targetIndices.getZeroBasedIndex(i) < model.getFilteredTaskList().size());
            int curr = targetIndices.getZeroBasedIndex(i);
            Task task = model.getFilteredTaskList().get(curr);
            model.updateFilteredTaskList(new NameContainsKeywordsPredicate(task.getName().fullName));
        }

        assertEquals(n, model.getFilteredTaskList().size());
    }
}
