package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_DEADLINE_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_TASK_STATUS_DESC;
import static trackr.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_2024;
import static trackr.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_2100;
import static trackr.logic.commands.CommandTestUtil.TASK_NAME_DESC_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.TASK_NAME_DESC_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.TASK_STATUS_DESC_DONE;
import static trackr.logic.commands.CommandTestUtil.TASK_STATUS_DESC_NOT_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2024;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_NOT_DONE;
import static trackr.logic.parser.CliSyntax.PREFIX_TAG;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_THIRD_OBJECT;

import org.junit.jupiter.api.Test;

import trackr.commons.core.index.Index;
import trackr.logic.commands.EditTaskCommand;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskDescriptor;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;
import trackr.testutil.TaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TASK_NAME_SORT_INVENTORY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TASK_NAME_DESC_SORT_INVENTORY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TASK_NAME_DESC_SORT_INVENTORY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC,
                TaskName.MESSAGE_CONSTRAINTS); // invalid task name
        assertParseFailure(parser, "1" + INVALID_TASK_DEADLINE_DESC,
                TaskDeadline.MESSAGE_CONSTRAINTS); // invalid task deadline
        assertParseFailure(parser, "1" + INVALID_TASK_STATUS_DESC,
                TaskStatus.MESSAGE_CONSTRAINTS); // invalid task status

        // invalid task name followed by valid task deadline
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC
                + VALID_TASK_DEADLINE_2024, TaskName.MESSAGE_CONSTRAINTS);

        // valid value followed by invalid value.
        // The test case for invalid value followed by valid value
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TASK_NAME_DESC_BUY_FLOUR + INVALID_TASK_NAME_DESC,
                TaskName.MESSAGE_CONSTRAINTS); //task name

        assertParseFailure(parser, "1" + TASK_DEADLINE_DESC_2100 + INVALID_TASK_DEADLINE_DESC,
                TaskDeadline.MESSAGE_CONSTRAINTS); //task deadline

        assertParseFailure(parser, "1" + TASK_STATUS_DESC_NOT_DONE + INVALID_TASK_STATUS_DESC,
                TaskStatus.MESSAGE_CONSTRAINTS); //task status

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC + INVALID_TASK_DEADLINE_DESC + VALID_TASK_STATUS_DONE,
                TaskName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_OBJECT;
        String userInput = targetIndex.getOneBased() + TASK_NAME_DESC_SORT_INVENTORY
                + TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE;


        TaskDescriptor descriptor =
                new TaskDescriptorBuilder()
                        .withTaskName(VALID_TASK_NAME_SORT_INVENTORY)
                        .withTaskDeadline(VALID_TASK_DEADLINE_2024)
                        .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);


        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        //name and desc
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased()
                + TASK_NAME_DESC_SORT_INVENTORY + TASK_DEADLINE_DESC_2100;

        TaskDescriptor descriptor =
                new TaskDescriptorBuilder()
                        .withTaskName(VALID_TASK_NAME_SORT_INVENTORY)
                        .withTaskDeadline(VALID_TASK_DEADLINE_2100).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //name and status
        userInput = targetIndex.getOneBased() + TASK_NAME_DESC_BUY_FLOUR
                + TASK_STATUS_DESC_NOT_DONE;
        descriptor = new TaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // task name
        Index targetIndex = INDEX_THIRD_OBJECT;
        String userInput = targetIndex.getOneBased() + TASK_NAME_DESC_SORT_INVENTORY;
        TaskDescriptor descriptor = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task deadline
        userInput = targetIndex.getOneBased() + TASK_DEADLINE_DESC_2024;
        descriptor = new TaskDescriptorBuilder()
                .withTaskDeadline(VALID_TASK_DEADLINE_2024).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task status
        userInput = targetIndex.getOneBased() + TASK_STATUS_DESC_NOT_DONE;
        descriptor = new TaskDescriptorBuilder()
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + TASK_NAME_DESC_SORT_INVENTORY
                + TASK_DEADLINE_DESC_2024 + TASK_STATUS_DESC_NOT_DONE
                + TASK_NAME_DESC_SORT_INVENTORY + TASK_DEADLINE_DESC_2024
                + TASK_STATUS_DESC_NOT_DONE + TASK_NAME_DESC_BUY_FLOUR
                + TASK_DEADLINE_DESC_2100 + TASK_STATUS_DESC_DONE;

        TaskDescriptor descriptor =
                new TaskDescriptorBuilder()
                        .withTaskName(VALID_TASK_NAME_BUY_FLOUR)
                        .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                        .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        //no other valid values specified
        // invalid task name followed by valid task name
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + INVALID_TASK_NAME_DESC + TASK_NAME_DESC_BUY_FLOUR;
        TaskDescriptor descriptor =
                new TaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_BUY_FLOUR).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid task deadline followed by valid task deadline
        userInput = targetIndex.getOneBased() + INVALID_TASK_DEADLINE_DESC + TASK_DEADLINE_DESC_2100;
        descriptor = new TaskDescriptorBuilder().withTaskDeadline(VALID_TASK_DEADLINE_2100).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid task status followed by valid task status
        userInput = targetIndex.getOneBased() + INVALID_TASK_STATUS_DESC + TASK_STATUS_DESC_NOT_DONE;
        descriptor = new TaskDescriptorBuilder().withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        // invalid task name followed by valid task name
        userInput = targetIndex.getOneBased()
                + INVALID_TASK_NAME_DESC + TASK_STATUS_DESC_NOT_DONE
                + TASK_DEADLINE_DESC_2100 + TASK_NAME_DESC_BUY_FLOUR;
        descriptor = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid task deadline followed by valid task deadline
        userInput = targetIndex.getOneBased()
                + TASK_NAME_DESC_BUY_FLOUR + INVALID_TASK_DEADLINE_DESC
                + TASK_STATUS_DESC_NOT_DONE + TASK_DEADLINE_DESC_2100;
        descriptor = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid task status followed by valid task status
        userInput = targetIndex.getOneBased()
                + INVALID_TASK_STATUS_DESC + TASK_NAME_DESC_BUY_FLOUR
                + TASK_STATUS_DESC_NOT_DONE + TASK_DEADLINE_DESC_2100;
        descriptor = new TaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_BUY_FLOUR)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_differentInputOrder_success() {
        //task status, task deadline, task name
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + TASK_STATUS_DESC_DONE
                + TASK_DEADLINE_DESC_2100 + TASK_NAME_DESC_BUY_FLOUR;
        TaskDescriptor descriptor = new TaskDescriptorBuilder()
                .withTaskStatus(VALID_TASK_STATUS_DONE)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskName(VALID_TASK_NAME_BUY_FLOUR).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //task deadline, task status, task name
        userInput = targetIndex.getOneBased() + TASK_DEADLINE_DESC_2024
                + TASK_STATUS_DESC_NOT_DONE + TASK_NAME_DESC_BUY_FLOUR;
        descriptor = new TaskDescriptorBuilder()
                .withTaskDeadline(VALID_TASK_DEADLINE_2024)
                .withTaskStatus(VALID_TASK_STATUS_NOT_DONE)
                .withTaskName(VALID_TASK_NAME_BUY_FLOUR).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
