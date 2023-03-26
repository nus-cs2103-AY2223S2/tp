package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TASK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.person.Task;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();
    private final String nonEmptyTask = "Some task.";

    @Test
    public void parse_indexSpecified_success() {
        // have task
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_ADD_TASK + nonEmptyTask;
        AddTaskCommand expectedCommand = new AddTaskCommand(INDEX_FIRST_PERSON,
                new Task(nonEmptyTask));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no task
        userInput = targetIndex.getOneBased() + " " + PREFIX_ADD_TASK;
        expectedCommand = new AddTaskCommand(INDEX_FIRST_PERSON, new Task(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AddTaskCommand.COMMAND_WORD + " " + nonEmptyTask, expectedMessage);
    }
}
