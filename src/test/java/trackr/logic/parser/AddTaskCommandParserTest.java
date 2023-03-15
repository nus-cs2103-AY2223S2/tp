package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_STATUS_DESC;
import static trackr.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static trackr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static trackr.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_2024;
import static trackr.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_2100;
import static trackr.logic.commands.CommandTestUtil.TASK_NAME_DESC_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.TASK_NAME_DESC_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.TASK_STATUS_DESC_DONE;
import static trackr.logic.commands.CommandTestUtil.TASK_STATUS_DESC_NOT_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.AddTaskCommand;
import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;
import trackr.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(SORT_INVENTORY_N).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_SORT_INVENTORY
                + TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE, new AddTaskCommand(expectedTask));

        // multiple task names - last task name accepted
        assertParseSuccess(parser, TASK_NAME_DESC_BUY_FLOUR + TASK_NAME_DESC_SORT_INVENTORY
                + TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE, new AddTaskCommand(expectedTask));

        // multiple task deadlines - last task deadline accepted
        assertParseSuccess(parser, TASK_NAME_DESC_SORT_INVENTORY
                + TASK_DEADLINE_DESC_2100 + TASK_DEADLINE_DESC_2024
                + TASK_STATUS_DESC_NOT_DONE, new AddTaskCommand(expectedTask));

        // multiple task status - last task status accepted
        assertParseSuccess(parser, TASK_NAME_DESC_SORT_INVENTORY + TASK_DEADLINE_DESC_2024
                + TASK_STATUS_DESC_DONE + TASK_STATUS_DESC_NOT_DONE, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no status
        Task expectedTask = new TaskBuilder(SORT_INVENTORY_N).withTaskStatus().build();
        assertParseSuccess(parser, TASK_NAME_DESC_SORT_INVENTORY + TASK_DEADLINE_DESC_2024,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TASK_NAME_SORT_INVENTORY + TASK_DEADLINE_DESC_2024
                        + TASK_STATUS_DESC_NOT_DONE, expectedMessage);

        // missing name
        assertParseFailure(parser, TASK_DEADLINE_DESC_2024
                + TASK_STATUS_DESC_NOT_DONE, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, TASK_NAME_DESC_SORT_INVENTORY + VALID_TASK_DEADLINE_2100
                        + TASK_STATUS_DESC_DONE, expectedMessage);

        // missing deadline
        assertParseFailure(parser, TASK_NAME_DESC_SORT_INVENTORY
                + TASK_STATUS_DESC_DONE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TASK_NAME_SORT_INVENTORY
                        + VALID_TASK_DEADLINE_2100 + VALID_TASK_STATUS_DONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + TASK_DEADLINE_DESC_2024
                + TASK_STATUS_DESC_DONE, TaskName.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, TASK_NAME_DESC_BUY_FLOUR + INVALID_TASK_DEADLINE_DESC
                + TASK_STATUS_DESC_DONE, TaskDeadline.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, TASK_NAME_DESC_BUY_FLOUR + TASK_DEADLINE_DESC_2024
                + INVALID_TASK_STATUS_DESC, TaskStatus.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + TASK_DEADLINE_DESC_2024 + INVALID_TASK_STATUS_DESC,
                TaskName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TASK_NAME_DESC_BUY_FLOUR
                        + TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_DONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
