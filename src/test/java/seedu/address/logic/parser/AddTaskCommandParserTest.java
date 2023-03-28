package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_TASK_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.student.Name;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

class AddTaskCommandParserTest {

    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index expectedIndex = INDEX_FIRST_TASK;
        Task expectedTask = new TaskBuilder(VALID_TASK_1).build();

        assertParseSuccess(parser, "1" + TASK_NAME_DESC_TASK_1,
                new AddTaskCommand(expectedIndex, expectedTask));
    }

    @Test
    public void parse_nameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing task name prefix
        assertParseFailure(parser, "1" + VALID_TASK_NAME_1, expectedMessage);

        // missing index
        assertParseFailure(parser, TASK_NAME_DESC_TASK_1, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid task name
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
    }
}
