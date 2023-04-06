package seedu.address.logic.parser.task.todo;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.todo.AddTodoCommand;

/**
 * Test class for {@code AddTodoCommandParser}.
 */
public class AddTodoCommandParserTest {

    private AddTodoCommandParser parser = new AddTodoCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
    }
}
